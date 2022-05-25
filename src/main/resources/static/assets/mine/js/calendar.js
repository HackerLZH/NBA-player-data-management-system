$(function(){
    $.ajax({
        url: '/match/getCalendar',
        // url: '/assets/calendar.json',
        type: 'get',
        dataType: 'json',
        success: function(res){
            let json = res.data;
            // let json = res;
            const mind = {
                /* 元数据，定义思维导图的名称、作者、版本等信息 */
                "meta": {
                    "name": "NBA近期赛程",
                    "author": "HackerLzh"
                },
                /* 数据格式声明 */
                "format": "node_array",
                /* 数据内容 */
                "data": [
                    {"id": "root", "isroot": true, "topic": "NBA近期赛程", "background-color": "red"}
                ]
            };
            const options = {
                container: 'jsmind_container',
                editable: true,
                theme:'primary',
                support_html : true
            };
            const jm = jsMind.show(options, mind);

            jm.add_node(jm.get_root(), 'title1', json.result.title, {"background-color": "black"});
            jm.add_node(jm.get_root(), 'title2', json.result.duration + '赛季', {"background-color": "black"});
            let title1 = jm.get_node('title1');
            jm.add_node(title1, 'return', '<a id=\"return\">navigation</a>');

            let matches = json.result.matchs;
            for (let i = 0; i < matches.length; i++){
                jm.add_node(jm.get_root(), 'child_date' + i, matches[i].date + '(' + matches[i].week + ')', {"background-color": "orange"});
                let list = matches[i].list;
                for (let j = 0; j < list.length; j++){
                    jm.add_node(
                        'child_date' + i,
                        'child_table' + i + j,
                        "<table class='mailTable'  cellspacing='0' cellpadding='0'>" +
                        "<tr>" +
                        "<td class='column'>开赛时间</td>" +
                        "<td>" + list[j].time_start + "</td>" +
                        "</tr>" +
                        "<tr>" +
                        "<td class='column'>比赛状态</td>" +
                        "<td>" + list[j].status_text + "</td>" +
                        "</tr>" +
                        "<tr>" +
                        "<td class='column'>队伍1</td>" +
                        "<td>" + list[j].team1 + "</td>" +
                        "</tr>" +
                        "<tr>" +
                        "<td class='column'>队伍2</td>" +
                        "<td>" + list[j].team2 + "</td>" +
                        "</tr>" +
                        "<tr>" +
                        "<td class='column'>比分</td>" +
                        "<td>" + list[j].team1_score + "-" + list[j].team2_score + "</td>" +
                        "</tr>" +
                        "</table>"
                    );
                }
            }
            for (let i = 0; i < matches.length; i++){
                jm.collapse_node('child_date' + i);
            }

            $('#return').click(function(){
                $.Notify({
                    caption: '稍等',
                    content: '正在前往...',
                    icon: "<span class='mif-vpn-publ'></span>"
                });
                setTimeout("window.location = '/navigation';", 1000);
            });
        }
    });
})