/**
 * jQuery EasyUI 1.4.3
 * Copyright (c) 2009-2015 www.jeasyui.com. All rights reserved.
 *
 * Licensed under the GPL license: http://www.gnu.org/licenses/gpl.txt
 * To use it on other terms please contact us at info@jeasyui.com
 * http://www.jeasyui.com/license_commercial.php
 *
 * jQuery EasyUI datagrid 组件扩展
 * jeasyui.extensions.datagrid.editors.js
 * 开发 流云
 * 由 落阳 整理和二次扩展
 * 最近更新：2015-12-03
 *
 * 依赖项：
 *   1、jquery.jdirk.js
 *
 * Copyright (c) 2015 ChenJianwei personal All rights reserved.
 */
(function ($) {

    var editors = $.fn.datagrid.defaults.editors,
        text_init = editors.text.init,
        textarea_init = editors.textarea.init,
        checkbox_init = editors.checkbox.init,
        textbox_init = editors.textbox.init,
        validatebox_init = editors.validatebox.init,
        numberbox_init = editors.numberbox.init,
        datebox_init = editors.datebox.init,
        combobox_init = editors.combobox.init,
        combotree_init = editors.combotree.init;


    //  扩展 easyui-datagrid editors-text 的自定义方法；
    //  setFocus，该方法用于聚焦当前编辑控件；
    //  focusable，该方法用于检查当前编辑控件是否可聚焦；
    //  input，该方法用于返回输入框对象；
    $.extend(editors.text, {
        setFocus: function (target) {
            $(target).focus();
        },
        focusable: function (target) {
            return true;
        },
        input: function (target) {
            return $(target);
        }
    });

    //  扩展 easyui-datagrid editors-textarea 的自定义方法；
    //  setFocus，该方法用于聚焦当前编辑控件；
    //  focusable，该方法用于检查当前编辑控件是否可聚焦；
    //  input，该方法用于返回输入框对象；
    $.extend(editors.textarea, {
        setFocus: function (target) {
            $(target).focus();
        },
        focusable: function (target) {
            return true;
        },
        input: function (target) {
            return $(target);
        }
    });

    //  扩展 easyui-datagrid editors-checkbox 的自定义方法；
    //  setFocus，该方法用于聚焦当前编辑控件；
    //  focusable，该方法用于检查当前编辑控件是否可聚焦；
    //  input，该方法用于返回输入框对象；
    $.extend(editors.checkbox, {
        init: function (container, options) {
            return checkbox_init.apply(this, arguments).addClass("datagrid-editable-input datagrid-editable-checkbox");
        },
        setFocus: function (target) {
            $(target).focus();
        },
        focusable: function (target) {
            return true;
        },
        input: function (target) {
            return $(target);
        }
    });

    //  扩展 easyui-datagrid editors-validatebox 的自定义方法；
    //  setFocus，该方法用于聚焦当前编辑控件；
    //  focusable，该方法用于检查当前编辑控件是否可聚焦；
    //  input，该方法用于返回输入框对象；
    $.extend(editors.validatebox, {
        setFocus: function (target) {
            $(target).focus();
        },
        focusable: function (target) {
            var t = $(target), opts = t.validatebox("options");
            return !(opts.disabled || opts.readonly);
        },
        input: function (target) {
            return $(target);
        }
    });

    //  扩展 easyui-datagrid editors-textbox 的自定义方法；
    //  setFocus，该方法用于聚焦当前编辑控件；
    //  focusable，该方法用于检查当前编辑控件是否可聚焦；
    //  input，该方法用于返回输入框对象；
    $.extend(editors.textbox, {
        init: function (container, options) {
            var box = textbox_init.apply(this, arguments);
            box.textbox("textbox").addClass("datagrid-editable-input");
            return box;
        },
        setFocus: function (target) {
            $(target).textbox("textbox").focus();
        },
        focusable: function (target) {
            var t = $(target), opts = t.textbox("options");
            return !(opts.disabled || !opts.editable || opts.readonly);
        },
        input: function (target) {
            return $(target).textbox("textbox");
        }
    });

    //  扩展 easyui-datagrid editors-numberbox 的自定义方法；
    //  setFocus，该方法用于聚焦当前编辑控件；
    //  focusable，该方法用于检查当前编辑控件是否可聚焦；
    //  input，该方法用于返回输入框对象；
    $.extend(editors.numberbox, {
        init: function (container, options) {
            var box = numberbox_init.apply(this, arguments);
            box.numberbox("textbox").addClass("datagrid-editable-input");
            return box;
        },
        setFocus: function (target) {
            $(target).numberbox("textbox").focus();
        },
        focusable: function (target) {
            var t = $(target), opts = t.numberbox("options");
            return !(opts.disabled || !opts.editable || opts.readonly);
        },
        input: function (target) {
            return $(target).numberbox("textbox");
        }
    });

    //  扩展 easyui-datagrid editors-datebox 的自定义方法；
    //  setFocus，该方法用于聚焦当前编辑控件；
    //  focusable，该方法用于检查当前编辑控件是否可聚焦；
    //  input，该方法用于返回输入框对象；
    $.extend(editors.datebox, {
        init: function (container, options) {
            var box = datebox_init.apply(this, arguments);
            box.datebox("textbox").addClass("datagrid-editable-input");
            return box;
        },
        setFocus: function (target) {
            $(target).datebox("textbox").focus();
        },
        focusable: function (target) {
            var t = $(target), opts = t.datebox("options");
            return !(opts.disabled || !opts.editable || opts.readonly);
        },
        input: function (target) {
            return $(target).datebox("textbox");
        }
    });

    //  扩展 easyui-datagrid editors-combobox 的自定义方法；
    //  setFocus，该方法用于聚焦当前编辑控件；
    //  focusable，该方法用于检查当前编辑控件是否可聚焦；
    //  input，该方法用于返回输入框对象；
    $.extend(editors.combobox, {
        init: function (container, options) {
            var box = combobox_init.apply(this, arguments);
            box.combobox("textbox").addClass("datagrid-editable-input");
            return box;
        },
        getValue: function(target){
            //return $(target).val();
            // console.debug($(target).combobox('getText')) //拿到结束的数据值)
            // //return $(target).combobox('getText');
            // return $(target).val();
            var id = $(target).val();
            var value ={};
            var data=a =$(target).combobox("getData");
            for (var i = 0; i < data.length; i++) {
                if(data[i].id == id){
                    value = data[i];
                    break;
                }
            }
            return value;
        },
        setFocus: function (target) {
            $(target).combobox("textbox").focus();
        },
        focusable: function (target) {
            var t = $(target), opts = t.combobox("options");
            return !(opts.disabled || !opts.editable || opts.readonly);
        },
        input: function (target) {
            return $(target).combobox("textbox");
        }
    });

    //  扩展 easyui-datagrid editors-combotree 的自定义方法；
    //  setFocus，该方法用于聚焦当前编辑控件；
    //  focusable，该方法用于检查当前编辑控件是否可聚焦；
    //  input，该方法用于返回输入框对象；
    $.extend(editors.combotree, {
        init: function (container, options) {
            var box = combotree_init.apply(this, arguments);
            box.combotree("textbox").addClass("datagrid-editable-input");
            return box;
        },
        setFocus: function (target) {
            $(target).combotree("textbox").focus();
        },
        focusable: function (target) {
            var t = $(target), opts = t.combotree("options");
            return !(opts.disabled || !opts.editable || opts.readonly);
        },
        input: function (target) {
            return $(target).combotree("textbox");
        }
    });

})(jQuery);