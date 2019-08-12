<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="/WEB-INF/views/head.jsp"%>
    <!-- 引入自己的js -->
    <script src="/js/model/productstock.js"></script>
</head>
<body>

<!-- 数据展示 -->
<table id="dataGrid" class="easyui-datagrid" style="width:400px;height:250px"
       data-options="
       url:'/productstock/page',
       fitColumns:true,
       singleSelect:true,
       fit:true,
       toolbar:'#tb',
       pagination:true">
    <thead>
    <tr>
                    <th data-options="field:'num',width:100">num</th>
                    <th data-options="field:'amount',width:100">amount</th>
                    <th data-options="field:'price',width:100">price</th>
                    <th data-options="field:'incomedate',width:100">incomedate</th>
                    <th data-options="field:'warning',width:100">warning</th>
                    <th data-options="field:'topnum',width:100">topnum</th>
                    <th data-options="field:'bottomnum',width:100">bottomnum</th>
                    <th data-options="field:'productId',width:100">productId</th>
                    <th data-options="field:'depotId',width:100">depotId</th>
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
<div id="productstockDialog" class="easyui-dialog" title="功能操作" style="width:280px;padding: 10px;"
     data-options="iconCls:'icon-save',modal:true,closed:true">
    <!-- 表单准备 -->
    <form id="editForm" action="" method="post">
        <input id="productstockId" type="hidden" name="id" />
        <table>
                      <tr>
                <td>num:</td>
                <td><input name="num" class="f1 easyui-validatebox"></input></td>
            </tr>
                      <tr>
                <td>amount:</td>
                <td><input name="amount" class="f1 easyui-validatebox"></input></td>
            </tr>
                      <tr>
                <td>price:</td>
                <td><input name="price" class="f1 easyui-validatebox"></input></td>
            </tr>
                      <tr>
                <td>incomedate:</td>
                <td><input name="incomedate" class="f1 easyui-validatebox"></input></td>
            </tr>
                      <tr>
                <td>warning:</td>
                <td><input name="warning" class="f1 easyui-validatebox"></input></td>
            </tr>
                      <tr>
                <td>topnum:</td>
                <td><input name="topnum" class="f1 easyui-validatebox"></input></td>
            </tr>
                      <tr>
                <td>bottomnum:</td>
                <td><input name="bottomnum" class="f1 easyui-validatebox"></input></td>
            </tr>
                      <tr>
                <td>productId:</td>
                <td><input name="productId" class="f1 easyui-validatebox"></input></td>
            </tr>
                      <tr>
                <td>depotId:</td>
                <td><input name="depotId" class="f1 easyui-validatebox"></input></td>
            </tr>
                  </table>
        <div style="text-align:center;padding:5px">
            <a href="#"  class="easyui-linkbutton" data-method="save" iconCls="icon-save">确定</a>
            <a href="#"  class="easyui-linkbutton" onclick="$('#productstockDialog').dialog('close')" iconCls="icon-cancel">取消</a>
        </div>
    </form>
</div>

</body>
</html>