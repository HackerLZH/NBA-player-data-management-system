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
    form.on('submit(addBtn)', function (data) {
        data = data.field;
        $.ajax({
            url: "/admin/player/doAdd",
            data: JSON.stringify(data),
            type: "post",
            dataType: "json",
            contentType: "application/json;charset=utf-8",
            beforeSend: function(){
                layer.msg('保存中', {icon: 16, time: 1000});
            },
            success: function (data) {
                setTimeout(function(){
                    if (data.code === 555 || data.code > 500000) {
                        layer.msg(data.msg, {
                            icon: 5,
                            time: 1000
                        }, function () {
                            window.location = "/admin/player/add"
                        })
                    } else {
                        layer.msg(data.msg, {
                            icon: 6,
                            time: 1000
                        }, function () {
                            window.location = "/admin/player/add"
                        })
                    }
                }, 1000);
            },
            error: function (data) {
                console.log(data);
                layer.msg("出错啦", {
                    icon: 2,
                    time: 1000
                }, function () {
                    window.location = "/admin/player/add"
                })
            }
        });
        return false; //一定要加上！！！----》停止监听
    });

    $("#return").click(function(){
        layer.load(0, {time: 1000});
        setTimeout("window.location = \"/admin/welcome\"", 1000);
    })
});