layui.use(['laypage', 'layer', 'table'], function () {
    const laypage = layui.laypage
        , layer = layui.layer
        , table = layui.table
        , $ = layui.$;

    table.render({
        elem: '#table-page1',
        url: '/player/profile/table',
        height: 473, //容器高度
        width: 1019, //容器宽度
        method: 'post',
        page: true, //是否显示分页
        limit: 10,  //注意：请务必确保 limit 参数（默认：10）是与你服务端限定的数据条数一致
        limits: [10, 20, 30], //每页条数的选择项
        cols: [[ //表头
            // {checkbox: true}
            {field: 'name', title: '球员名', width: 180}
            , {field: 'age', title: '年龄', width: 80, sort: true}
            , {field: 'birthday', title: '生日', width: 110, sort: true}
            , {field: 'birthPlace', title: '出生地', width: 240}
            , {field: 'team', title: '效力球队', width: 90}
            , {field: 'enterTime', title: '选秀时间', width: 110, sort: true}
            , {field: 'playedTime', title: '已打时间', width: 110, sort: true}
            , {field: 'retired', title: '是否退役', width: 90}
        ]],
        // field 的值和实体类属性名称保持一致!!!如果数据表格没有渲染，可以看看浏览器解析后的名称
    });

    table.render({
        elem: '#table-page2',
        url: '/player/profile/table',
        height: 473, //容器高度
        width: 738, //容器宽度
        method: 'post',
        page: true, //是否显示分页
        limit: 10,  //注意：请务必确保 limit 参数（默认：10）是与你服务端限定的数据条数一致
        limits: [10, 20, 30], //每页条数的选择项
        cols: [[ //表头
            // {checkbox: true}
            {field: 'name', title: '球员名', width: 180}
            , {field: 'score', title: '得分', width: 110, sort: true}
            , {field: 'rebound', title: '篮板', width: 110, sort: true}
            , {field: 'assist', title: '助攻', width: 110, sort: true}
            , {field: 'steal', title: '抢断', width: 110, sort: true}
            , {field: 'block', title: '篮板', width: 110, sort: true}
        ]],
    });

    $("#return").click(function(){
        layer.load(0, {time: 1000});
        setTimeout(function(){
            window.location = "/navigation";
        }, 1000);
    })
});