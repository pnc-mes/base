/**
 * Created by test on 2017/9/19.
 */
$(function () {
    $('.cSelect').on('click', function () {
        layer.open({
            btn: ['确认', '取消'],
            type: 2,
            title: '过滤条件',
            shadeClose: true,
            shade: 0.8,
            area: ['380px', '90%'],
            content: '/WEB-INF/views/selectAll.jsp'
        });
    })
})
