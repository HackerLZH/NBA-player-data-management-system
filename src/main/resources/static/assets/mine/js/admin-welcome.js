layui.use('layer', function(){
    const layer = layui.layer, $ = layui.$;
    $("#return").click(function(){
        layer.load(0, {time: 1000});
        setTimeout("window.location='/navigation'", 1000);
    });
    $("#add").click(function(){
        layer.load(0, {time: 1000});
        setTimeout("window.location='/admin/player/add'", 1000);
    });
    $("#update").click(function(){
        layer.open({
            title: "选择",
            shade: 0.2,
            content: "<input id='input_name' type='text' placeholder='请输入球员名' autocomplete='off' autofocus='autofocus'>",
            yes: function(index){
                let name = $("#input_name").val();
                $.ajax({
                    url: "/player/profile/check?name=" + name,
                    type: "get",
                    contentType: "application/json;charset=utf-8",
                    dataType: "json",
                    beforeSend: function(){
                        layer.msg("加载中", {
                            icon: 16,
                            time: 500
                        })
                    },
                    success: function(data){
                        setTimeout(function(){
                            if (data.code === 555){
                                layer.msg(data.msg, {
                                    icon: 5,
                                    time: 1000
                                })
                            }else{
                                layer.msg(data.msg,{
                                    icon: 6,
                                    time: 1000
                                }, function(){
                                    window.location = "/admin/player/update?name=" + name;
                                })
                            }
                        }, 500);
                    }
                });
                layer.close(index);
            }
        })
    });
    $("#delete").click(function(){
        layer.open({
            title: "选择",
            shade: 0.2,
            content: "<input id='input_name' type='text' placeholder='请输入球员名' autocomplete='off' autofocus='autofocus'>",
            yes: function(index){
                let name = $("#input_name").val();
                $.ajax({
                    url: "/admin/player/doDelete?name=" + name,
                    type: "delete",
                    dataType: "json",
                    beforeSend: function(){
                        layer.msg("加载中", {
                            icon: 16,
                            time: 500
                        })
                    },
                    success: function(data){
                        setTimeout(function(){
                            if (data.code === 555){
                                layer.msg(data.msg, {
                                    icon: 5,
                                    time: 1000
                                }, function(){
                                    window.location = "/admin/welcome"
                                })
                            }else{
                                layer.msg(data.msg,{
                                    icon: 6,
                                    time: 1000
                                }, function(){
                                    window.location = "/admin/welcome"
                                })
                            }
                        }, 500);
                    }
                });
                layer.close(index);
            }
        })
    });
});
