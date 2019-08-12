function formatImg(data) {
    return "<img src='"+data+"' alt='没有图片' />"
}
function formatColor(data) {
    return "<div style='width: 20px;height: 20px;background-color: "+data+"'></div>"
}
function formatObj(data) {
    if(data){
        return data.name;
    }
}
//成功后进行加载
function loadSuccess(data) {
    // $.easyui.tooltip.init($("img"), {
    //     position: "bottom",
    //     content: "<div style=\"width:600px;height:480px;\">我真的是有点瓜</div>"
    // });
    var rows = data.rows;
    for(var i=0;i<rows.length;i++){
        var result = rows[i];
        $.easyui.tooltip.init($("img[src='"+result.smallpic+"']"), {
            position: "rigth",
            content: "<div style=\"width:600px;height:480px;\"><img src='"+result.pic+"'  /></div>"
        });
    }
}
$(function () {
    // $("#t3") -> 代表咱们的图片
// position：出现的位置
// content：展示的是什么东西

    //把所有会多次用到的组件放到最上面
    var datagrid = $('#dataGrid'); //数据展示
    var productDialog = $('#productDialog'); //弹框
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
            //2.添加的时候显示密码
            $("*[data-save]").show();
            $("*[data-save] input").validatebox("enable");
            //3.打开面板(绝对居中)
            productDialog.dialog("center").dialog('open').dialog("setTitle","添加数据");

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
                productDialog.dialog("center").dialog('open').dialog("setTitle","修改数据");
                //4.数据进行回显
                //解决单位，品牌，类型的回显问题
                if(row.unit){
                    row["unit.id"] = row.unit.id;
                }
                if(row.brand){
                    row["brand.id"] = row.brand.id;
                }
                if(row.types){
                    row["types.id"] = row.types.id;
                }
                editForm.form("load",row);
            }else{
                $.messager.alert('提示','请选择一条数据再进行修改！',"warning");
            }

        },
        //代表完成保存功能
        save:function () {
            //添加和修改的路径要分开
            var url = "/product/save";
            //拿到表单中的id
            var id = $("#productId").val();
            if(id){
                url = "/product/update?cmd=update"
            }
            //提交表单的功能
            editForm.form('submit', {
                url: url,
                onSubmit: function(){
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
                        productDialog.dialog('close');
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
                        $.get("/product/delete",{id:row.id},function (result) {
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
})

