/**
 * Created by PNC on 2017/7/28.
 */

$(function () {
    var IQCGd=window.sessionStorage.getItem("IQCGd");
    $("#files").change(function () {
        var fileName = getFileName($("#files").val());
        $("#fileName").val(fileName);

    });

    function getFileName(o){
        var pos=o.lastIndexOf("\\");
        return o.substring(pos+1);
    }

    //确认
    $(".aSubmit").click(function(){
        //[0]表示获取原生对象
        var formData = new FormData();//($("#iqcAdd")[0]);
        formData.append('upload', document.getElementById('files').files[0]);
        /*request({url:"/Spec/SaveSpecInfo",data: {
                "ExecType": "04",
                "busData": JSON.stringify(newData3)
            }},function (Body) {

        });*/
        formData.append("IQCGd",IQCGd);
        uploadRequest({url:  "/IQC/addIqcFileAdd",  data:formData}, function (Body) {
            alertSuccess("上传成功");
        });

        /* uploadRequest({url:  "/IQC/addIqcFileAdd"+isRadio,  data:formData}, function (Body) {
             alertSuccess("上传成功");
         });*/

      /*  $.ajax({
            url:getBasePath()+"/IQC/addIqcFileAdd",
            type : 'POST',
            data : formData,
            // 告诉jQuery不要去处理发送的数据
            processData : false,
            // 告诉jQuery不要去设置Content-Type请求头
            contentType : false,
            beforeSend:function(){
                console.log("正在进行，请稍候");
            },
            success : function(responseStr) {
                if(responseStr == "00"){
                    alertSuccess("上传成功");
                }else{
                    alertError("上传失败");
                }

            },
            error : function(responseStr) {
                alertError("上传失败");
            }
        });*/
    });




});
