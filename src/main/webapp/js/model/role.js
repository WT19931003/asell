function permissionsFormat(v,r,i) {
    //v:当前这个格子的值   r:这一行的值  i:索引
    var permissStr = "";
    for(var i=0;i<v.length;i++){
        permissStr += v[i].name;
        if(i<v.length-1){
            permissStr+= ",";
        }
    }
    return permissStr
}

$(function () {
    //把所有会多次用到的组件放到最上面
    var datagrid = $('#dataGrid'); //数据展示
    var roleDialog = $('#roleDialog'); //弹框
    var searchForm = $('#searchForm'); //查询表单
    var editForm = $('#editForm'); //添加与修改表单
    var myPermissionGrid = $('#myPermissionGrid');
    var allPermissionGrid = $('#allPermissionGrid');

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
            // loadData:加载一些数据(加一个空数组，它就清空)
            myPermissionGrid.datagrid("loadData",[]);
            //2.添加的时候显示密码
            $("*[data-save]").show();
            $("*[data-save] input").validatebox("enable");
            //3.打开面板(绝对居中)
            roleDialog.dialog("center").dialog('open').dialog("setTitle","添加数据");

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
                roleDialog.dialog("center").dialog('open').dialog("setTitle","修改数据");
                //4.数据进行回显
                //if(row.department){
                //    row["department.id"] = row.department.id;
                //}
                // loadData:加载一些数据(当前这个角色对应的所有权限)
                /**
                 * 注意:不要直接把这个角色的权限交过去(不要给原件)
                 *  -> 怎么办:搞一个一模一样的数据(复印件)
                 */
                //创建一个新的数组
                var permissions = [];
                //把后面的数据的所有东西交给前面的对象
                $.extend(permissions,row.permissions);
                myPermissionGrid.datagrid("loadData",permissions);
                editForm.form("load",row);
            }else{
                $.messager.alert('提示','请选择一条数据再进行修改！',"warning");
            }

        },
        //代表完成保存功能
        save:function () {
            //添加和修改的路径要分开
            var url = "/role/save";
            //拿到表单中的id
            var id = $("#roleId").val();
            if(id){
                url = "/role/update?cmd=update"
            }
            //提交表单的功能
            editForm.form('submit', {
                url: url,
                //在这里，藏了一个额外的参数
                onSubmit: function(param){
                    //1.拿到角色权限grid的所有行
                    var rows = myPermissionGrid.datagrid("getRows");
                    //2.遍历它，拿它的id值
                    for(var i=0;i<rows.length;i++){
                        var row = rows[i];
                        param["permissions["+i+"].id"] = row.id;
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
                        roleDialog.dialog('close');
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
                        $.get("/role/delete",{id:row.id},function (result) {
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
        },
        //添加权限  index:这一行索引  row:这一行数据
        addPermission:function (index, row) {
            //获取到这个grid的所有行
            var rows = myPermissionGrid.datagrid("getRows");
            for(var i=0;i<rows.length;i++){
                //判断，如果这一行的id值与传过来的那一行的id相等,表示这个数据已经存在
                if(rows[i].id == row.id){
                    $.messager.show({
                        title:'警告',
                        msg:'这条信息已经存在！',
                        showType:'show'
                    });
                    return ;
                }
            }
            myPermissionGrid.datagrid("appendRow",row);
        },
        //删除权限
        removePermission:function (index,row) {
            myPermissionGrid.datagrid("deleteRow",index);
        }
    };


    //创建当前角色的权限grid
    myPermissionGrid.datagrid({
        fitColumns:true,
        singleSelect:true,
        fit:true,
        //监听双击事件
        onDblClickRow:itsource.removePermission
    });
    //创建添加的时候的所有权限的grid
    allPermissionGrid.datagrid({
        url:'/permission/page',
        fitColumns:true,
        singleSelect:true,
        fit:true,
        pagination:true,
        //监听双击事件
        onDblClickRow:itsource.addPermission
    })

})