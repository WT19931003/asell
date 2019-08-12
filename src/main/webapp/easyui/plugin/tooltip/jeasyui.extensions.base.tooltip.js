/**
* jQuery EasyUI 1.4.3
* Copyright (c) 2009-2015 www.jeasyui.com. All rights reserved.
*
* Licensed under the GPL license: http://www.gnu.org/licenses/gpl.txt
* To use it on other terms please contact us at info@jeasyui.com
* http://www.jeasyui.com/license_commercial.php
*
* jQuery EasyUI 通用插件基础库 扩展
* jeasyui.extensions.base.tooltip.js
* 开发 流云
* 由 落阳 整理
* 最近更新：2015-10-16
*
* 依赖项：
*   1、jquery.jdirk.js
*
* Copyright (c) 2015 ChenJianwei personal All rights reserved.
*/
(function ($) {

    $.util.namespace("$.easyui");


    $.easyui.getTopEasyuiTooltip = function () {
        if ($.util.isUtilTop) { return $.fn.tooltip; }
        return $.util.$ && $.util.$.fn && $.util.$.fn.tooltip ? $.util.$.fn.tooltip : $.fn.tooltip;
    };
    $.easyui.tooltip = $.fn.tooltip;

    //  对某个元素设置 easyui-tooltip 属性；该函数定义如下参数：
    //      target:     表示要设置 easyui-tooltip 的元素，可以是一个 jQuery 选择器字符串，也可以是一个 DOM 对象或者 jQuery 对象。
    //      options:    表示初始化 easyui-tooltip 的参数信息，为一个 JSON-Object；
    //  备注：通过该方法设置的 easyui-tooltip 属性，在触发 mouseover 事件时，加载 easyui-tooltip，在 tooltip-tip 隐藏时，easyui-tooltip 自动调用 destroy 销毁；
    $.easyui.tooltip.init = function (target, options) {
        var t = $(target);
        t.mouseover(function () {
            t.tooltip($.extend({ trackMouse: true }, options, {
                onHide: function () {
                    if ($.isFunction(options.onHide)) { options.onHide.apply(this, arguments); }
                    t.tooltip("destroy");
                }
            })).tooltip("show");
        });
    };

})(jQuery);