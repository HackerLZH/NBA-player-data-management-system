layui.use(['layer'], function(){
    const layer = layui.layer, $ = layui.$;
    $.ajax({
        url: "/match/getNews",
        // url: '/assets/news.json',
        type: "get",
        dataType: 'json',
        success: function (res) {
            let list = res.data.newslist;
            // let list = res.newslist;
            let a = "";
            for (let i = 0; i < list.length; i++) {
                a += "<div class='dc'>\n";
                a += "<div class='dc_top'><a target='_blank' href=" + list[i].url + "><h2><strong>" + (i + 1) + "、" + list[i].title + "</strong>></h2></a></div>\n";
                a += "<div class='dc_down'>";
                a += "<div class='dc_left'>\n";
                a += "<img src=" + list[i].picUrl + " height=100% width=100% />\n";
                a += "</div>\n";
                a += "<div class='dc_right'>\n" + "<div>\n" + list[i].description + "</div>\n";
                a += "<div class='foot'>\n";
                a += "<span class='ctime'>" + list[i].ctime + "</span>";
                a += "<i style='margin-left: 260px'>";
                a += "来源：<a id='source'>" + list[i].source + "</a></i>\n";
                a += "</div>\n" + "</div>\n" + "</div>\n" + "</div>\n";
            }
            $(".d_center").append(a);

            $('.d_left').hover(function(){
                $('#return').show();
                $('#returnTop').show();
            }, function(){
                $('#return').hide();
                $('#returnTop').hide();
            });
            $('.dc .dc_top a').hover(function(){
                $('.dc .dc_top a').css('color', '#A52A2A');
            }, function(){
                $('.dc .dc_top a').css('color', '#4F4F4F');
            });

            $('#return').click(function(){
                layer.load(0, {time: 2000});
                setTimeout("window.location = '/navigation';", 1000);
            });
            $('#returnTop').click(function(){
                layer.load(2, {time: 500});
                setTimeout("document.body.scrollTop = document.documentElement.scrollTop = 0;", 500);
            })
        }
    });
});