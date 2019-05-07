/**
 * Created by lisp on 2015/12/29.
 */
(function ($) {
    $.fn.mouseEvent = function (fn) {
        obj = this;
        if (window.navigator.userAgent.toLowerCase().indexOf("firefox") != -1) {
            obj[0].addEventListener("DOMMouseScroll", fnWheel, false);
        } else {
            obj[0].onmousewheel = fnWheel;
        }

        function fnWheel(ev) {
            var oEvent = ev || event;
            var bDown = true;
            if (oEvent.wheelDelta) {//ie chrome
                bDown = oEvent.wheelDelta > 0 ? false : true;
            } else {//ff
                bDown = oEvent.detail > 0 ? true : false;
            }

            fn && fn(bDown);
            oEvent.preventDefault && oEvent.preventDefault();
            return false;
        }
    }
})(jQuery);
(function ($) {
    var defaults = {
        left: "0",
        top:"0"
    }

    $.fn.extend({
        mouseScrollCont: function (options) {
            var opts = $.extend({}, defaults, options);
            return this.each(function () {
                var oMscroll = new ScrollDialog($(this));
                oMscroll.fnInit(opts);
            });
        }
    });

    function ScrollDialog(obj) {
        this.oMain = $(obj);
        this.oConMainBar = this.oMain.find(".reward-scroll");        //滚动条的包裹框
        this.oConScroll = this.oMain.find(".reward-scroll-bar");          //滚动条
        this.oConWrap = this.oMain.find(".dtl-cont");          //内容的包裹框（超出隐藏）
        this.oConMain = this.oConWrap.find(".dtl-main");    //内容

        this.bMouseOn = true;
        this.bAnimate = true;
    }

    ScrollDialog.prototype.fnInit = function (options) {

        if (parseInt(this.oConWrap.height()) >= parseInt(this.oConMain.height())) return;
        this.oConMainBar.show();
        var h = (parseInt(this.oConWrap.height()) / parseInt(this.oConMain.height())) * parseInt(this.oConMainBar.height());
        this.oConScroll.css({ "height": h });

        if (this.fnCss3CanPlay()) {
            this.oConMain.css({ "transition": "0.3s all ease-out" });
            this.oConScroll.css({ "transform": "translate3d(" + options.left + "px," + options.top + "px,0)" });
            this.oConMain.css({ "transform": "translate3d(0,0,0)" });
        } else {
            this.oConScroll.css({ "position": "absolute", "left": options.left + "px", "top": options.top + "px" });
            this.oConMain.css({ "position": "absolute", "left": "0", "top": "0" });
        }
        this.fnScrollMove(options.left, options.top);

    }

    ScrollDialog.prototype.fnSetTop = function (t, initLeft, initTop) {

        var maxTop = this.oConMainBar.height() - this.oConScroll.height();
        if (t < parseInt(initTop)) {
            t = parseInt(initTop);
        } else if (t > maxTop) {
            t = maxTop;
        }
        var scale = (t - parseInt(initTop)) / (maxTop - parseInt(initTop));
        if (this.fnCss3CanPlay()) {
            this.fnSetCss3Style(this.oConMain[0], "transform", "translate3d(0," + (-scale * (this.oConMain.outerHeight() - this.oConWrap.outerHeight())) + "px,0)");
            this.fnSetCss3Style(this.oConScroll[0], "transform", "translate3d(" + initLeft + "px," + t + "px,0)");
        } else {
            if (this.bAnimate) {
                this.oConMain.stop().animate({ "top": -scale * (this.oConMain.outerHeight() - this.oConWrap.outerHeight()) }, "50");
            } else {
                this.oConMain.css({ top: -scale * (this.oConMain.outerHeight() - this.oConWrap.outerHeight()) + "px" });
            }
            this.oConScroll.css({ top: t + "px" });
        }

    }

    ScrollDialog.prototype.fnCss3CanPlay = function () {
        var browserInfo = window.navigator.userAgent.toLowerCase();
        if (browserInfo.indexOf("chrome") != -1 || browserInfo.indexOf("firefox") != -1 || browserInfo.indexOf("rv:11.0") != -1 || browserInfo.indexOf("msie 10.0") != -1) {
            return true;
        } else {
            return false;
        }
    }

    ScrollDialog.prototype.fnSetCss3Style = function (obj, name, value) {
        var str = name.charAt(0).toUpperCase() + name.substring(1);
        obj.style['Webkit' + str] = value;
        obj.style['Moz' + str] = value;
        obj.style['ms' + str] = value;
        obj.style['O' + str] = value;
        obj.style.name = value;
    }

    ScrollDialog.prototype.fnScrollMove = function (initLeft, initTop) {
        var _this = this;

        this.oConScroll.mousedown(function (ev) {
            var disY = ev.clientY - _this.oConScroll.position().top;

            if (_this.fnCss3CanPlay()) {
                _this.fnSetCss3Style(_this.oConMain[0], "transition", "0s all ease-out");
            } else {
                _this.bAnimate = false;
            }

            function move(ev) {
                var t = ev.clientY - disY;
                _this.fnSetTop(t, initLeft, initTop);
            }

            function up(ev) {
                $(document).unbind("mousemove", move);
                $(document).unbind("mouseup", up);
                _this.oConScroll[0].releaseCapture && _this.oConScroll[0].releaseCapture();

                if (_this.fnCss3CanPlay()) {
                    _this.fnSetCss3Style(_this.oConMain[0], "transition", "0.3s all ease-out");
                } else {
                    _this.bAnimate = true;
                }
            }

            $(document).mousemove(move);
            $(document).mouseup(up);

            _this.oConScroll[0].setCapture && _this.oConScroll[0].setCapture();
            return false;
        });

        this.oConWrap.mouseEvent(function (bDown) {
            if (!_this.bMouseOn) return;
            var t = _this.oConScroll.position().top;
            if (bDown) {
                t += 10;
            } else {
                t -= 10;
            }
            _this.fnSetTop(t, initLeft, initTop);
        });
    }

})(jQuery);