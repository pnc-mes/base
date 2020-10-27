$(function () {
    var ruid=0;
    $("#select").click(function(){
        var PackCode=$("#Code").val().trim();
        if(PackCode==""||PackCode==null){
            toastr.warning("箱号不能为空");
            return;
        }
        var data={};
        data.packcode=PackCode;
        $.get( getBasePath() + "/Disassoc/isRePack",data,function (data) {
            if(data==""){
                $("#remark").prop("checked",false);
            }else {
                $("#remark").prop("checked",true);
            }
            toastr.success("查询成功");
            $("#newremark").prop("checked",false);
        });
    });
    $("#save").click(function(){


        var newremark=$("#Code").val().trim();
        var data={};

        data.remark=newremark;
        var aa="";
        if($("#newremark").is(":checked")){
            aa="00"
        }
        data.check=aa;
        $.get( getBasePath() + "/Disassoc/UpdataisRePack",data,function (data) {
            if(data>=1){
                toastr.success("操作成功");
                $("#Code").val("");
            }else {
                toastr.success("该信息不存在:"+$("#Code").val());
            }
            $("#remark").prop("checked",false);
            $("#newremark").prop("checked",false);
        });
    });




});