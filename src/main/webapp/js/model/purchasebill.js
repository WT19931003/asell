function  formatEmp(v) {
    if(v)return v.username;
}
function  formatObj(v) {
    if(v)return v.name;
}
function  formatStatu(v) {
    if(v==0){
        return "<span style='color: #800509;'>待审</span>"
    }else if(v==1){
        return "<span style='color: green;'>已审</span>"
    }else if(v==-1){
        return "<span style='color: rgba(115,102,106,0.29);'><s>作废</s></span>";
    }
}

$(function () {
    //把所有会多次用到的组件放到最上面
    var datagrid = $('#dataGrid'); //数据展示
    var purchasebillDialog = $('#purchasebillDialog'); //弹框
    var searchForm = $('#searchForm'); //查询表单
    var editForm = $('#editForm'); //添加与修改表单

    //为所有有data-method的组件添加事件
    $("*[data-method]").on("click",function(){
        // var method = $(this).data("method");
        // itsource[method]();
        itsource[$(this).data("method")]()
    })

    //防止污染
    window.itsource = {
        add:function(){
            //1.清空form中的数据
            editForm.form("clear");
            //清空itemGrid(明细)中的数据
            dg.datagrid("loadData",[]);
            //2.添加的时候显示密码
            $("*[data-save]").show();
            $("*[data-save] input").validatebox("enable");
            //3.打开面板(绝对居中)
            purchasebillDialog.dialog("center").dialog('open').dialog("setTitle","添加数据");

        },
        edit:function () {
            //记住 ：选择一条数据才能进行修改
            var row =  datagrid.datagrid("getSelected");
            if(row){
                //1.清空form中的数据
                editForm.form("clear");
                //2.添加的时候显示密码
                $("*[data-save]").hide();
                $("*[data-save] input").validatebox("disable");
                //3.打开面板(绝对居中)
                purchasebillDialog.dialog("center").dialog('open').dialog("setTitle","修改数据");
                //4.数据进行回显
                if(row.supplier){
                   row["supplier.id"] = row.supplier.id;
                }
                if(row.buyer){
                   row["buyer.id"] = row.buyer.id;
                }
                editForm.form("load",row);

                //复制一个明细
                var items = $.extend([],row.items);
                //把明细交给grid
                dg.datagrid("loadData",items);
            }else{
                $.messager.alert('提示','请选择一条数据再进行修改！',"warning");
            }

        },
        //代表完成保存功能
        save:function () {
            //添加和修改的路径要分开
            var url = "/purchasebill/save";
            //拿到表单中的id
            var id = $("#purchasebillId").val();
            if(id){
                url = "/purchasebill/update?cmd=update"
            }
            //提交表单的功能
            editForm.form('submit', {
                url: url,
                //提交前的额外参数(把值设置好，就会传到后端)
                onSubmit: function(params){
                    //1.获到到所有的行
                    var rows = dg.datagrid("getRows");
                    //2.遍历rows,获取里面的数据
                    for(var i=0;i<rows.length;i++){
                        //3.拿到每一行数据
                        var row = rows[i];
                        //4.拼接额外参数：items[0].xxx
                        params["items["+i+"].product.id"] = row.product.id;
                        params["items["+i+"].price"] = row.price;
                        params["items["+i+"].num"]  = row.num;
                        params["items["+i+"].descs"]  = row.descs;
                    }
                    //提交之前执行的功能，如果返回false，它就不会再执行了
                    return $(this).form('validate');
                },
                //easyui提交后表单后 返回的值只是一个字符串
                success: function(result){
                    //把它变成一个Json数据 eval("("+jsonStr+")") /JSON.parse(jsonStr)
                    //console.debug(result);
                    result = JSON.parse(result);
                   if(result.success){
                       //成功之后的处理
                        datagrid.datagrid('reload');
                        //关闭弹出框
                        purchasebillDialog.dialog('close');
                   }else{
                       $.messager.alert('提示','添加失败了！<br /> 原因是'+result.msg,"info");
                   }
                }
            });
        },
        //删除功能
        delete:function(){
          //1.判断是否已经选中了一条数据
            var row =  datagrid.datagrid("getSelected");
            if(row){
                //2.提示是否真的要删除这条数据呢？
                $.messager.confirm('确认', '你确定要<span style="color:red;font-size: 20px;">狠心</span>删除我嘛？', function(r){
                    if (r){
                        //3.直接进行相应的删除
                        $.get("/purchasebill/delete",{id:row.id},function (result) {
                            if(result.success){
                                //删除成功，刷新页面
                                datagrid.datagrid("reload");
                            }else {
                                //删除失败，给出提示信息
                                $.messager.alert('提示','这哥们是干不掉的！<br /> 原因是'+result.msg,"info");
                            }
                        })
                    }
                });
            }else{
                $.messager.alert('提示','请选择一条数据再进行删除！',"warning");
            }
        },
        search:function(){
            //获取过滤的参数值
            var params = searchForm.serializeObject();
            //刷新grid
            datagrid.datagrid('load',params);
        }
    };


    /**
     * dg = $("#billItems") : 获取到明细的表
     *      defaultRow:默认的每一行的属性(等会需要修改咱们自己明细对应的属性)
     *          分别是产品,产品颜色，产品的图片,明细的数量，明细的价格,明细的小计,明细的描述
     *      insertPosition:增添加一行要插入的位置  bottom:下方  top:上方
     */
    var dg = $("#billItems"),
        defaultRow = { product: "", productColor: "", productImage: "", num: 0, price: 0, amount: "", descs: "" },
        insertPosition = "bottom";

    //datagrid做初始化:它本身属性与列属性
    var dgInit = function () {
        //拿到表格的列属性
        var getColumns = function () {
            var result = [];
            var normal = [
                {
                    field: 'product', title: '产品名称', width: 180,
                    editor: {
                        type: "combobox",
                        options: {
                            valueField:'id',textField:'name',panelHeight:'auto',url:'/util/findProducts',
                            required: true
                        }
                    },
                    //只展示产品的名称即可
                    formatter:function (v) {
                        if(v)return v.name;
                    }
                },
                {
                    field: 'productColor', title: '产品颜色', width: 180,
                    //只展示产品的名称即可
                    // v:代表是的这一个格子的数据
                    // r:代表这一行的数据
                    formatter:function (v,r) {
                       if(r && r.product){
                           return "<div style='width: 20px;height: 20px; background-color: "+r.product.color+"'></div>"
                       }
                    }
                },
                {
                    field: 'productImage', title: '图片', width: 100,
                    formatter:function (v,r) {
                        if(r && r.product){
                            return "<img src='"+r.product.smallpic+"' />"
                        }
                    }
                },
                {
                    field: 'num', title: '数量', width: 100,
                    editor: {
                        type: "numberbox",
                        options: {
                            required: true,
                            precision:2
                        }
                    }
                },
                {
                    field: 'price', title: '价格', width: 100,
                    editor: {
                        type: "numberbox",
                        options: {
                            required: true,
                            precision:2
                        }
                    }
                },
                {
                    field: 'amount', title: '小计', width: 100,
                    formatter:function (v,r) {
                        if(r.num && r.price){
                            return r.num*r.price ;
                        }
                        return 0;
                    }
                },
                {
                    field: 'descs', title: '备注', width: 100,
                    editor: {
                        type: "textarea",
                        options: {
                            readonly: false
                        }
                    }
                }
            ];
            result.push(normal);
            return result;
        };
        //item的grid的基本参数配置
        var options = {
            idField: "ID",
            rownumbers: true,
            fitColumns: true,
           // fit: true,
            border: true,
            singleSelect: true,
            columns: getColumns(),//注意，上面的方法，拿到列属性
            toolbar:"#itemsBtn",
            height:300,
            //表示开启单元格编辑功能
            enableCellEdit: true
        };
        //创建相应的datagrid(明细datagrid)
        dg.datagrid(options);

    };

    //确定咱们要加入的行是哪一行
    var getInsertRowIndex = function () {
        return insertPosition == "top" ? 0 : dg.datagrid("getRows").length;
    }

    //为按钮绑定事件
    var buttonBindEvent = function () {
        //添加一个明细
        $("#btnInsert").click(function () {
            var targetIndex = getInsertRowIndex(), targetRow = $.extend({}, defaultRow, { ID: $.util.guid() });
            dg.datagrid("insertRow", { index: targetIndex, row: targetRow });
            //field:代表我要编辑的是哪个元素  index: 第几行的元素
            dg.datagrid("editCell", { index: targetIndex, field: "product" });
        });
        //删除一个明细
        $("#btnRemove").click(function () {
            //获取到选中的那一行 (拿到这一行的数据)
            var row = dg.datagrid("getSelected");
            //根据相应的行，获取到对应的索引号
            var rowNum = dg.datagrid("getRowIndex",row);
            //怎么从grid中删除一行(删除需要下标)
            dg.datagrid("deleteRow",rowNum);
        })

        // $("#btnSave").click(function () {
        //     var rows = dg.datagrid("getRows"), len = rows.length;
        //     for (var i = 0; i < len; i++) {
        //         dg.datagrid("endEdit", i);
        //     }
        // });
    };


    //初始化的时候执行下面两个方法
    dgInit(); buttonBindEvent();

})