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
        $.get( getBasePath() + "/Disassoc/GetRemark",data,function (data) {
            if(data==""){
                toastr.warning("该箱号不存在");
            }else {
                ruid=data.ruid;
                toastr.success("查询成功");
                $("#remark").val(data.remark);
            }
        });
    });
    $("#save").click(function(){
        if(parseInt(ruid)<=0){
            toastr.warning("保存失败，箱号不存在");
        }

        var newremark=$("#newremark").val().trim();
        var data={};
        data.ruid=ruid;
        data.remark=newremark;
        $.get( getBasePath() + "/Disassoc/UpdataRemark",data,function (data) {
            if(data>=1){
                toastr.success("操作成功");
                $("#Code").val("");
                $("#remark").val("");
                $("#newremark").val("");
            }
        });
    });




});