/**
 * jQuery EasyUI 1.4.3
 * Copyright (c) 2009-2015 www.jeasyui.com. All rights reserved.
 *
 * Licensed under the GPL license: http://www.gnu.org/licenses/gpl.txt
 * To use it on other terms please contact us at info@jeasyui.com
 * http://www.jeasyui.com/license_commercial.php
 *
 * jQuery EasyUI datagrid 编辑器扩展-回车聚焦
 * jeasyui.extensions.datagrid.edit.cellEdit.js
 * 开发 落阳
 * 最近更新：2017-03-21
 *
 * 依赖项：
 *   1、jquery.jdirk.js
 *   2、jeasyui.extensions.datagrid.editors.js
 *   3、jeasyui.extensions.datagrid.getColumnInfo.js
 *
 * Copyright (c) 2015 ChenJianwei personal All rights reserved.
 */
(function () {

    $.util.namespace("$.fn.datagrid.extensions");

    var editCell = function (target, param) {
        if (!param || !param.field || param.index == undefined || param.index == null) { return; }
        var t = $(target), opts = t.datagrid('options'), fields = t.datagrid("getColumnFields", "all");
        for (var i = 0; i < fields.length; i++) {
            var col = t.datagrid('getColumnOption', fields[i]);
            col.editor1 = col.editor;
            if (fields[i] != param.field) {
                col.editor = null;
            }
        }
        opts.editIndex = param.index; opts.editField = param.field;
        t.datagrid('beginEdit', param.index);
        var ed = t.datagrid('getEditor', param);
        if (ed) {
            //尝试聚焦
            var editorTarget = ed.target[0];
            if ($.isFunction(ed.actions.focusable)) {
                if (ed.actions.focusable(editorTarget) == true) {
                    if ($.isFunction(ed.actions.setFocus)) {
                        ed.actions.setFocus(editorTarget);
                    } else {
                        var theInput = $.isFunction(ed.actions.input) ? ed.actions.input(editorTarget) : ed.target;
                        theInput.focus();
                    }
                } else {
                    //不可聚焦
                }
            }
            else {
                var theInput = $.isFunction(ed.actions.input) ? ed.actions.input(editorTarget) : ed.target;
                theInput.focus();
            }
        }
        for (var i = 0; i < fields.length; i++) {
            var col = t.datagrid('getColumnOption', fields[i]);
            col.editor = col.editor1;
        }
    };

    // 初始化事件绑定
    function initExtendEventBind(t, opts) {

        if (opts.enableCellEdit) {
            //单元格点击事件
            var _onClickCell = opts.onClickCell;
            opts.onClickCell = function (index, field) {
                if ($.isFunction(_onClickCell)) { _onClickCell.call(this, index, field); }
                //console.log("内置事单元格点击件" + field);
                if (opts.editIndex != undefined && opts.editIndex != null) {
                    if (t.datagrid('validateRow', opts.editIndex)) {
                        t.datagrid('endEdit', opts.editIndex);
                    } else {
                        return;
                    }
                }
                opts.editIndex = index;
                opts.editField = field;
                editCell(t[0], { index: index, field: field });
            };

            //结束编辑事件
            var _onEndEdit = opts.onEndEdit;
            opts.onEndEdit = function (index, row, changes) {
                if ($.isFunction(_onEndEdit)) { _onEndEdit.call(this, index, row, changes); }
                opts.editIndex = undefined; opts.editField = undefined;
            };
        }
    }

    function initializeExtensions(target) {
        var t = $(target),
            state = $.data(target, "datagrid"),
            opts = state.options;

        initExtendEventBind(t, opts);
    }

    var _datagrid = $.fn.datagrid;
    $.fn.datagrid = function (options, param) {
        if (typeof options == "string") {
            return _datagrid.apply(this, arguments);
        }
        options = options || {};
        return this.each(function () {
            var jq = $(this),
                isInited = $.data(this, "datagrid") ? true : false,
                opts = isInited ? options : $.extend({},
                    $.fn.datagrid.parseOptions(this),
                    $.parser.parseOptions(this, [
                        {
                            enableCellEdit: "boolean"
                        }
                    ]), options);
            _datagrid.call(jq, opts, param);
            if (!isInited) {
                initializeExtensions(this);
            }
        });
    };
    $.union($.fn.datagrid, _datagrid);


    var methods = {

        //  扩展 easyui-datagrid 的自定义方法；开启指定单元格的编辑状态；该方法定义如下参数：
        //      param:  这是一个 JSON-Object 对象，该对象定义如下属性：
        //          index: 要开启编辑的数据行的索引号，从 0 开始计数；
        //          field: 要开启编辑的数据的列的 field 名；
        //  返回值：返回表示当前 easyui-datagrid 控件的 jQuery 链式对象；
        editCell: function (jq, param) { return jq.each(function () { editCell(this, param); }); }
    };

    var defaults = {


        //  扩展 easyui-datagrid 的自定义属性；表示是否开启单元格编辑功能；
        //  Boolean 类型值，默认 false。
        enableCellEdit: false
    };

    if ($.fn.datagrid.extensions.defaults) {
        $.extend($.fn.datagrid.extensions.defaults, defaults);
    } else {
        $.fn.datagrid.extensions.defaults = defaults;
    }

    if ($.fn.datagrid.extensions.methods) {
        $.extend($.fn.datagrid.extensions.methods, methods);
    } else {
        $.fn.datagrid.extensions.methods = methods;
    }

    $.extend($.fn.datagrid.defaults, defaults);
    $.extend($.fn.datagrid.methods, methods);

})(jQuery);