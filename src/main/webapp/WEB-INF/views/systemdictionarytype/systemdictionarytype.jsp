<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="/WEB-INF/views/head.jsp"%>
    <!-- 引入自己的js -->
    <script src="/js/model/systemdictionarytype.js"></script>
</head>
<body>

<!-- 数据展示 -->
<table id="dataGrid" class="easyui-datagrid" style="width:400px;height:250px"
       data-options="
       url:'/systemdictionarytype/page',
       fitColumns:true,
       singleSelect:true,
       fit:true,
       toolbar:'#tb',
       pagination:true">
    <thead>
    <tr>
                    <th data-options="field:'sn',width:100">sn</th>
                    <th data-options="field:'name',width:100">name</th>
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
<div id="systemdictionarytypeDialog" class="easyui-dialog" title="功能操作" style="width:280px;padding: 10px;"
     data-options="iconCls:'icon-save',modal:true,closed:true">
    <!-- 表单准备 -->
    <form id="editForm" action="" method="post">
        <input id="systemdictionarytypeId" type="hidden" name="id" />
        <table>
                      <tr>
                <td>sn:</td>
                <td><input name="sn" class="f1 easyui-validatebox"></input></td>
            </tr>
                      <tr>
                <td>name:</td>
                <td><input name="name" class="f1 easyui-validatebox"></input></td>
            </tr>
                  </table>
        <div style="text-align:center;padding:5px">
            <a href="#"  class="easyui-linkbutton" data-method="save" iconCls="icon-save">确定</a>
            <a href="#"  class="easyui-linkbutton" onclick="$('#systemdictionarytypeDialog').dialog('close')" iconCls="icon-cancel">取消</a>
        </div>
    </form>
</div>

</body>
</html>