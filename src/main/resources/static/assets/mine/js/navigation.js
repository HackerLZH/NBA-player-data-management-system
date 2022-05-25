(function ($) {
    $.StartScreen = function () {
        var plugin = this;
        var width = (window.innerWidth > 0) ? window.innerWidth : screen.width;

        plugin.init = function () {
            setTilesAreaSize();
            if (width > 640) addMouseWheel();
        };

        var setTilesAreaSize = function () {
            var groups = $(".tile-group");
            var tileAreaWidth = 80;
            $.each(groups, function (i, t) {
                if (width <= 640) {
                    tileAreaWidth = width;
                } else {
                    tileAreaWidth += $(t).outerWidth() + 80;
                }
            });
            $(".tile-area").css({
                width: tileAreaWidth
            });
        };

        var addMouseWheel = function () {
            $("body").mousewheel(function (event, delta) {
                var page = $(document);
                var scroll_value = delta * 50;
                page.scrollLeft(page.scrollLeft() - scroll_value);
                return false;
            });
        };

        plugin.init();
    }
})(jQuery);

$(function () {
    $.StartScreen();

    var tiles = $(".tile, .tile-small, .tile-sqaure, .tile-wide, .tile-large, .tile-big, .tile-super");

    $.each(tiles, function () {
        var tile = $(this);
        setTimeout(function () {
            tile.css({
                opacity: 1,
                "-webkit-transform": "scale(1)",
                "transform": "scale(1)",
                "-webkit-transition": ".3s",
                "transition": ".3s"
            });
        }, Math.floor(Math.random() * 500));
    });

    $(".tile-group").animate({
        left: 0
    });
});

$(function () {
    var current_tile_area_scheme = localStorage.getItem('tile-area-scheme') || "tile-area-scheme-dark";
    $(".tile-area").removeClass(function (index, css) {
        return (css.match(/(^|\s)tile-area-scheme-\S+/g) || []).join(' ');
    }).addClass(current_tile_area_scheme);

    $(".schemeButtons .button").hover(
        function () {
            var b = $(this);
            var scheme = "tile-area-scheme-" + b.data('scheme');
            $(".tile-area").removeClass(function (index, css) {
                return (css.match(/(^|\s)tile-area-scheme-\S+/g) || []).join(' ');
            }).addClass(scheme);
        },
        function () {
            $(".tile-area").removeClass(function (index, css) {
                return (css.match(/(^|\s)tile-area-scheme-\S+/g) || []).join(' ');
            }).addClass(current_tile_area_scheme);
        }
    );

    $(".schemeButtons .button").on("click", function () {
        var b = $(this);
        var scheme = "tile-area-scheme-" + b.data('scheme');

        $(".tile-area").removeClass(function (index, css) {
            return (css.match(/(^|\s)tile-area-scheme-\S+/g) || []).join(' ');
        }).addClass(scheme);

        current_tile_area_scheme = scheme;
        localStorage.setItem('tile-area-scheme', scheme);

        showSettings();
    });
});

//跳转
$(function () {
    $('#userProfile').click(function () {
        $.Notify({
            caption: '稍等',
            content: '正在进入...',
            // type: 'info',
            icon: "<span class='mif-vpn-publ'></span>"
        });
        setTimeout("window.location='/user/profile'", 1000);
    });
    $('#admin').click(function () {
        $.Notify({
            caption: '稍等',
            content: '正在进入...',
            icon: "<span class='mif-vpn-publ'></span>"
        });
        setTimeout("window.location='/admin/welcome'", 1000);
    });
    $('#playerProfile').click(function () {
        $.Notify({
            caption: '稍等',
            content: '正在进入...',
            icon: "<span class='mif-vpn-publ'></span>"
        });
        setTimeout("window.location='/player/profile'", 1000);
    });
    $('#playerGraph').click(function () {
        $.Notify({
            caption: '稍等',
            content: '正在进入...',
            icon: "<span class='mif-vpn-publ'></span>"
        });
        setTimeout("window.location='/player/data/graph'", 1000);
    });
    $('#calendar').click(function () {
        $.Notify({
            caption: '稍等',
            content: '正在进入...',
            icon: "<span class='mif-vpn-publ'></span>"
        });
        setTimeout("window.location='/match/calendar'", 1000);
    });
    $('#news').click(function () {
        $.Notify({
            caption: '稍等',
            content: '正在进入...',
            icon: "<span class='mif-vpn-publ'></span>"
        });
        setTimeout("window.location='/match/news'", 1000);
    });
});