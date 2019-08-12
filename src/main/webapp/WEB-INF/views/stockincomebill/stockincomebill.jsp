<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="/WEB-INF/views/head.jsp"%>
    <!-- 引入自己的js -->
    <script src="/js/model/stockincomebill.js"></script>
</head>
<body>

<!-- 数据展示 -->
<table id="dataGrid" class="easyui-datagrid" style="width:400px;height:250px"
       data-options="
       url:'/stockincomebill/page',
       fitColumns:true,
       singleSelect:true,
       fit:true,
       toolbar:'#tb',
       pagination:true">
    <thead>
    <tr>
                    <th data-options="field:'vdate',width:100">vdate</th>
                    <th data-options="field:'totalamount',width:100">totalamount</th>
                    <th data-options="field:'totalnum',width:100">totalnum</th>
                    <th data-options="field:'inputtime',width:100">inputtime</th>
                    <th data-options="field:'auditortime',width:100">auditortime</th>
                    <th data-options="field:'status',width:100">status</th>
                    <th data-options="field:'supplierId',width:100">supplierId</th>
                    <th data-options="field:'auditorId',width:100">auditorId</th>
                    <th data-options="field:'inputuserId',width:100">inputuserId</th>
                    <th data-options="field:'keeperId',width:100">keeperId</th>
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
<div id="stockincomebillDialog" class="easyui-dialog" title="功能操作" style="width:280px;padding: 10px;"
     data-options="iconCls:'icon-save',modal:true,closed:true">
    <!-- 表单准备 -->
    <form id="editForm" action="" method="post">
        <input id="stockincomebillId" type="hidden" name="id" />
        <table>
                      <tr>
                <td>vdate:</td>
                <td><input name="vdate" class="f1 easyui-validatebox"></input></td>
            </tr>
                      <tr>
                <td>totalamount:</td>
                <td><input name="totalamount" class="f1 easyui-validatebox"></input></td>
            </tr>
                      <tr>
                <td>totalnum:</td>
                <td><input name="totalnum" class="f1 easyui-validatebox"></input></td>
            </tr>
                      <tr>
                <td>inputtime:</td>
                <td><input name="inputtime" class="f1 easyui-validatebox"></input></td>
            </tr>
                      <tr>
                <td>auditortime:</td>
                <td><input name="auditortime" class="f1 easyui-validatebox"></input></td>
            </tr>
                      <tr>
                <td>status:</td>
                <td><input name="status" class="f1 easyui-validatebox"></input></td>
            </tr>
                      <tr>
                <td>supplierId:</td>
                <td><input name="supplierId" class="f1 easyui-validatebox"></input></td>
            </tr>
                      <tr>
                <td>auditorId:</td>
                <td><input name="auditorId" class="f1 easyui-validatebox"></input></td>
            </tr>
                      <tr>
                <td>inputuserId:</td>
                <td><input name="inputuserId" class="f1 easyui-validatebox"></input></td>
            </tr>
                      <tr>
                <td>keeperId:</td>
                <td><input name="keeperId" class="f1 easyui-validatebox"></input></td>
            </tr>
                      <tr>
                <td>depotId:</td>
                <td><input name="depotId" class="f1 easyui-validatebox"></input></td>
            </tr>
                  </table>
        <div style="text-align:center;padding:5px">
            <a href="#"  class="easyui-linkbutton" data-method="save" iconCls="icon-save">确定</a>
            <a href="#"  class="easyui-linkbutton" onclick="$('#stockincomebillDialog').dialog('close')" iconCls="icon-cancel">取消</a>
        </div>
    </form>
</div>

</body>
</html>