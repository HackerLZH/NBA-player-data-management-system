layui.use(['laydate', 'form', 'layer'], function() {
    const laydate = layui.laydate
        , form = layui.form
        , layer = layui.layer
        , $ = layui.$;

    //日期组件
    laydate.render({
        elem: '#birthday'
    });
    laydate.render({
        elem: '#enter_time',
        type: 'year'
    });

    //监听添加
    form.on('submit(saveBtn)', function (data) {
        data = data.field;
        $.ajax({
            url: "/admin/player/doUpdate",
            data: JSON.stringify(data),
            type: "put",
            dataType: "json",
            contentType: "application/json;charset=utf-8",
            success: function (data) {
                if (data.code === 200) {
                    layer.msg(data.msg, {
                        icon: 6,
                        time: 1000
                    }, function () {
                        window.location = "/admin/welcome";
                    })
                }
            },
        });
        return false; //一定要加上！！！----》停止监听
    });

    $("#return").click(function(){
        window.location = "/admin/welcome";
    })
})