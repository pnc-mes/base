(function($){
  $.expr[":"].searchableSelectContains = $.expr.createPseudo(function(arg) {
    return function( elem ) {
      return $(elem).text().toUpperCase().indexOf(arg.toUpperCase()) >= 0;
    };
  });
  $.fn.searchableDropdown = function(options){
    var sS;
    this.each(function(){
      sS = new $sS($(this), options);
    });
    return sS;
  };
  $.searchableDropdown = function(element, options) {
    this.element = element;
    this.options = options || {};
    var _this = this;
    _this.init();
    this.element.on("mouseover", function (event) {
      $(this).css("cursor","pointer");
      _this.createowm();
      if(_this.data.length==0){
        return;
      }
      var mouseCoords= function (ev) {
        return {
          x: ev.clientX + document.body.scrollLeft - document.body.clientLeft-50,
          y: ev.clientY + document.body.scrollTop - document.body.clientTop+6
        };
      };
      var mousePoint=mouseCoords(event);
      _this.searchableElement.css("left",mousePoint.x+"px");
      _this.searchableElement.css("top",mousePoint.y+"px");
      _this.searchableElement.on('mouseleave', function (event) {
        _this.searchableElement.remove();
      });
       _this.searchableElement.show();
      _this.input.on('keydown', function (event) {
        event.stopPropagation();
        if (event.which === 13) {
          event.preventDefault();
          _this.selectCurrentHoverItem();
          _this.hide();
        } else if (event.which == 27) {
          _this.hide();
        } else if (event.which == 40) {
          _this.hoverNextItem();
        } else if (event.which == 38) {
          _this.hoverPreviousItem();
        }
      }).on('keyup', function (event) {
        if (event.which != 13 && event.which != 27 && event.which != 38 && event.which != 40)
          _this.filter();
      })
    });
  }
  var $sS = $.searchableDropdown;
  $sS.fn = $sS.prototype = {
    version: '0.0.2'
  };
  $sS.fn.extend = $sS.extend = $.extend;
  $sS.fn.extend({
    data:[],
    init: function() {
      var _this = this;
      _this.data = typeof(_this.options) != "object" || _this.options.data.length==0 ? [] : _this.options.data;
      var num = _this.data.length == 0 ? 0 : _this.data.length;
      _this.setnum(num);
      _this.createowm();
    },
    createowm: function() {
      $(".searchable-select",$("body")).remove();
      var _this = this;
      this.searchableElement = $('<div tabindex="0" class="searchable-select"></div>');
      this.showmsg=$('<span class="org_bot_cor"></span>');
      this.dropdown = $('<div class="searchable-select-dropdown"></div>');
      this.input = $('<input type="text" class="searchable-select-input" />');
      this.items = $('<div class="searchable-select-items"></div>');
      this.scrollPart = $('<div class="searchable-scroll"></div>');
      this.hasPrivious = $('<div class="searchable-has-privious">...</div>');
      this.hasNext = $('<div class="searchable-has-next">...</div>');
      this.hasNext.on('mouseenter', function () {
        _this.hasNextTimer = null;
        var f = function () {
          var scrollTop = _this.items.scrollTop();
          _this.items.scrollTop(scrollTop + 20);
          _this.hasNextTimer = setTimeout(f, 50);
        };
        f();
      }).on('mouseleave', function (event) {
        clearTimeout(_this.hasNextTimer);
      });
      this.hasPrivious.on('mouseenter', function () {
        _this.hasPriviousTimer = null;
        var f = function () {
          var scrollTop = _this.items.scrollTop();
          _this.items.scrollTop(scrollTop - 20);
          _this.hasPriviousTimer = setTimeout(f, 50);
        };
        f();
      }).on('mouseleave', function (event) {
        clearTimeout(_this.hasPriviousTimer);
      });

      this.searchableElement.append(this.showmsg);
      this.dropdown.append(this.input);
      this.dropdown.append(this.scrollPart);
      this.scrollPart.append(this.hasPrivious);
      this.scrollPart.append(this.items);
      this.scrollPart.append(this.hasNext);
      this.searchableElement.append(this.dropdown);
      _this.searchableElement.hide();
      $("body").append(this.searchableElement);
      this.buildItems();
      this.setPriviousAndNextVisibility();
    },
    setnum:function(num){
      this.element.attr("class", "circle");
      this.element.html(num);
    },
    filter: function(){
      var text = this.input.val();
      this.items.find('.searchable-select-item').addClass('searchable-select-hide');
      this.items.find('.searchable-select-item:searchableSelectContains('+text+')').removeClass('searchable-select-hide');
      if(this.currentSelectedItem.hasClass('searchable-select-hide') && this.items.find('.searchable-select-item:not(.searchable-select-hide)').length > 0){
        this.hoverFirstNotHideItem();
      }

      this.setPriviousAndNextVisibility();
    },
    hoverFirstNotHideItem: function(){
      this.hoverItem(this.items.find('.searchable-select-item:not(.searchable-select-hide)').first());
    },
    add:function(val){
      this.items.append(this.createItem(val));
      this.setnum(this.items.children().length);
      this.data.push(val);
    },
    selectCurrentHoverItem: function(){
      if(!this.currentHoverItem.hasClass('searchable-select-hide'))
        this.selectItem(this.currentHoverItem);
    },
    hoverPreviousItem: function(){
      if(!this.hasCurrentHoverItem())
        this.hoverFirstNotHideItem();
      else{
        var prevItem = this.currentHoverItem.prevAll('.searchable-select-item:not(.searchable-select-hide):first')
        if(prevItem.length > 0)
          this.hoverItem(prevItem);
      }
    },
    hoverNextItem: function(){
      if(!this.hasCurrentHoverItem())
        this.hoverFirstNotHideItem();
      else{
        var nextItem = this.currentHoverItem.nextAll('.searchable-select-item:not(.searchable-select-hide):first')
        if(nextItem.length > 0)
          this.hoverItem(nextItem);
      }
    },
    createItem:function(value){
      var _this = this;
      var item = $('<div class="searchable-select-item" data-key="'+value.Key+'" data-value="' + value.Value + '">' + value.Value+'('+value.Key+')' + '<span class="item_remove">-</span></div>');
      if (this.selected) {
        _this.selectItem(item);
        _this.hoverItem(item);
      }
      item.on('mouseenter', function () {
        $(this).addClass('hover');
      }).on('mouseleave', function () {
        $(this).removeClass('hover');
      });
      item.find(".item_remove").on("click",function() {
        for (var i=0;i<_this.data.length;i++) {
          if (_this.data[i].Key == $(this).parent().attr("data-key")) {
            $(this).parent().remove();
            _this.data.splice(i, 1);
            if (_this.options.callback != undefined && _this.options.callback.removeItem != undefined) {
                var data = {
                    "key": $(this).parent().attr("data-key"),
                    "value": $(this).parent().attr("data-value")
                }
                _this.options.callback.removeItem(_this, data);
            }
            break;
          }
        }
        _this.setnum(_this.items.children().length);
        if (_this.items.children().length == 0) {
          _this.searchableElement.hide();
        }
      });
      return item;
    },
    buildItems: function() {
      var _this = this;
      if(_this.data.length>0) {
        for (var i=0;i<_this.data.length;i++) {
          var item = _this.createItem(_this.data[i]);
          _this.items.append(item);
        }
        this.items.on('scroll', function () {
          _this.setPriviousAndNextVisibility();
        })
      }
    },
      getdata:function() {
          return this.data;
      },
    show: function(){
      this.searchableElement.removeClass('searchable-select-hide');
      this.input.focus();
      this.status = 'show';
      this.setPriviousAndNextVisibility();
    },
    hide: function(){
      this.input.val('');
      this.searchableElement.trigger('focus');
      this.status = 'hide';
    },
    hasCurrentSelectedItem: function(){
      return this.currentSelectedItem && this.currentSelectedItem.length > 0;
    },
    selectItem: function(item){
      if(this.hasCurrentSelectedItem()) {
        this.currentSelectedItem.removeClass('selected');
      }
      this.currentSelectedItem = item;
      item.addClass('selected');
      this.hoverItem(item);
      if(this.options.afterSelectItem) {
        this.options.afterSelectItem.apply(this);
      }
    },
    hasCurrentHoverItem: function(){
      return this.currentHoverItem && this.currentHoverItem.length > 0;
    },
    hoverItem: function(item) {
      if (this.hasCurrentHoverItem()) {
        this.currentHoverItem.removeClass('hover');
      }
      if (item.outerHeight() + item.position().top > this.items.height())
        this.items.scrollTop(this.items.scrollTop() + item.outerHeight() + item.position().top - this.items.height());
      else if (item.position().top < 0)
        this.items.scrollTop(this.items.scrollTop() + item.position().top);
      this.currentHoverItem = item;
      item.addClass('hover');
    },
    setPriviousAndNextVisibility: function(){
      if(this.items.scrollTop() === 0){
        this.hasPrivious.addClass('searchable-select-hide');
        this.scrollPart.removeClass('has-privious');
      } else {
        this.hasPrivious.removeClass('searchable-select-hide');
        this.scrollPart.addClass('has-privious');
      }
      if(this.items.scrollTop() + this.items.innerHeight() >= this.items[0].scrollHeight){
        this.hasNext.addClass('searchable-select-hide');
        this.scrollPart.removeClass('has-next');
      } else {
        this.hasNext.removeClass('searchable-select-hide');
        this.scrollPart.addClass('has-next');
      }
    }
  });
})(jQuery);
