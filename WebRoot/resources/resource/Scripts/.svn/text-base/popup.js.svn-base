/* @update: 2016-2-25 23:19:19 */
define('popup', function (require, i, o) {
    o.exports = {
        showLayer: function (i) {
            if (!i[0]) return !1;
            var o = $(window).height(),
            n = $(window).scrollTop(),
            e = i.height(),
            a = i.width();
            i.css({
                top: n + Math.max(0, (o - e) / 2) + 'px',
                'margin-left': - a / 2 | '0px'
            }).show();
            var d = $('<div class="windowmask"></div>');
            $('body').append(d)
        },
        hideLayer: function (i) {
            $('.windowmask').remove(),
            i.hide()
        }
    }
});
