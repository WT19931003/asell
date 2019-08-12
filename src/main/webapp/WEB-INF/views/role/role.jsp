<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="/WEB-INF/views/head.jsp"%>
    <!-- 引入自己的js -->
    <script src="/js/model/role.js"></script>
</head>
<body>

<!-- 数据展示 -->
<table id="dataGrid" class="easyui-datagrid" style="width:400px;height:250px"
       data-options="
       url:'/role/page',
       fitColumns:true,
       singleSelect:true,
       fit:true,
       toolbar:'#tb',
       pagination:true">
    <thead>
            <tr>
                    <th data-options="field:'name',width:100">名称</th>
                    <th data-options="field:'sn',width:100">编号</th>
                    <th data-options="field:'permissions',width:200,formatter:permissionsFormat">权限</th>
            </tr>
    </thead>
</table>
<!-- 高级查询与按钮 -->
<div id="tb" style="padding:5px;height:auto">
    <div style="margin-bottom:5px">
        <a href="#" data-method="add" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        <a href="#" data-method="edit" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
        <a href="#" data-method="delete" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    </div>
    <div>
        <form id="searchForm" action="">
            名称: <input name="name" class="easyui-textbox" style="width:80px">
            <a href="#" class="easyui-linkbutton" data-method="search" iconCls="icon-search">查询</a>
        </form>
    </div>
</div>
<!-- 添加与修改的弹出框 -->
<div id="roleDialog" class="easyui-dialog" title="功能操作" style="width:840px;padding: 10px;"
     data-options="iconCls:'icon-save',modal:true,closed:true">
    <!-- 表单准备 -->
    <form id="editForm" action="" method="post">
        <input id="roleId" type="hidden" name="id" />
        <table>
            <tr>
                <td>
                    名称:<input name="name" class="f1 easyui-validatebox"></input>
                    编号:<input name="sn" class="f1 easyui-validatebox"></input>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="easyui-layout" style="width: 800px;height: 300px;">
                        <div data-options="region:'west'" style="width:390px">
                            <table id="myPermissionGrid" title="当前角色权限">
                                <thead>
                                    <tr>
                                        <th data-options="field:'name',width:100">权限名称</th>
                                        <th data-options="field:'sn',width:100">权限编号</th>
                                        <th data-options="field:'url',width:100">资源路径</th>
                                    </tr>
                                </thead>
                            </table>
                        </div>
                        <div data-options="region:'center'">
                            <table id="allPermissionGrid" title="所有权限">
                                <thead>
                                <tr>
                                    <th data-options="field:'name',width:100">权限名称</th>
                                    <th data-options="field:'sn',width:100">权限编号</th>
                                    <th data-options="field:'url',width:100">资源路径</th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                    </div>
                </td>
            </tr>
        </table>
        <div style="text-align:center;padding:5px">
            <a href="#"  class="easyui-linkbutton" data-method="save" iconCls="icon-save">确定</a>
            <a href="#"  class="easyui-linkbutton" onclick="$('#roleDialog').dialog('close')" iconCls="icon-cancel">取消</a>
        </div>
    </form>
</div>

</body>
</html>