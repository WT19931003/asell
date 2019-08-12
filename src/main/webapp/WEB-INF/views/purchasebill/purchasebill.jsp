<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="/WEB-INF/views/head.jsp" %>
    <!-- 引入自己的js -->
    <script src="/js/model/purchasebill.js"></script>
    <!-- easyui支持表单编辑的扩展 -->
    <script src="/easyui/plugin/cellEdit/jeasyui.extensions.datagrid.getColumnInfo.js"></script>
    <script src="/easyui/plugin/cellEdit/jeasyui.extensions.datagrid.editors.js"></script>
    <script src="/easyui/plugin/cellEdit/jeasyui.extensions.datagrid.edit.cellEdit.js"></script>
</head>
<body>

<!-- 数据展示 -->
<table id="dataGrid" class="easyui-datagrid" style="width:400px;height:250px"
       data-options="
       url:'/purchasebill/page',
       fitColumns:true,
       singleSelect:true,
       fit:true,
       toolbar:'#tb',
       pagination:true">
    <thead>
    <tr>
        <th data-options="field:'vdate',width:100">交易时间</th>
        <th data-options="field:'supplier',width:100,formatter:formatObj">供应商</th>
        <th data-options="field:'totalamount',width:100">总金额</th>
        <th data-options="field:'totalnum',width:100">总数量</th>
        <th data-options="field:'buyer',width:100,formatter:formatEmp">采购员</th>
        <th data-options="field:'inputUser',width:100,formatter:formatEmp">录入人</th>
        <th data-options="field:'auditor',width:100,formatter:formatEmp">审核人</th>
        <th data-options="field:'status',width:100,formatter:formatStatu">状态</th>
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
            采购时间: <input name="beginDate" class="easyui-datebox" style="width:120px">
                -
                <input name="endDate" class="easyui-datebox" style="width:120px">
            状态:<select  class="easyui-combobox" name="status" panelWidth="auto" panelHeight="auto" style="width:100px;">
                    <option value="">--请选择--</option>
                    <option value="0">待审</option>
                    <option value="1">已审</option>
                    <option value="-1">作废</option>
                </select>
            <a href="#" class="easyui-linkbutton" data-method="search" iconCls="icon-search">查询</a>
        </form>
    </div>
</div>
<!-- 添加与修改的弹出框 -->
<div id="purchasebillDialog" class="easyui-dialog" title="功能操作" style="width:800px;padding: 10px;"
     data-options="iconCls:'icon-save',modal:true,closed:true">
    <!-- 表单准备 -->
    <form id="editForm" action="" method="post">
        <input id="purchasebillId" type="hidden" name="id"/>
        <table>
            <tr>
                <td>交易时间:</td>
                <td><input name="vdate" class="f1 easyui-datebox"></input></td>
            </tr>
            <tr>
                <td>供应商:</td>
                <td>
                    <input name="supplier.id" class="easyui-combobox"
                           panelHeight="auto"
                           data-options="valueField:'id',textField:'name',url:'/util/allSupplier',required:true" />
                </td>
            </tr>
            <tr>
                <td>采购员:</td>
                <td>
                    <input name="buyer.id" class="easyui-combobox"
                           panelHeight="auto"
                           data-options="valueField:'id',textField:'username',url:'/util/findBuyers',required:true" />
                </td>
            </tr>
        </table>
        <div id="itemsBtn">
            <a href="#" id="btnInsert" class="easyui-linkbutton"  iconCls="icon-add" plain="true">添加</a>
            <a href="#" id="btnRemove" class="easyui-linkbutton"  iconCls="icon-remove" plain="true">删除</a>
        </div>
        <table id="billItems" title="明细管理"></table>
        <div style="text-align:center;padding:5px">
            <a href="#" class="easyui-linkbutton" data-method="save" iconCls="icon-save">确定</a>
            <a href="#" class="easyui-linkbutton" onclick="$('#purchasebillDialog').dialog('close')"
               iconCls="icon-cancel">取消</a>
        </div>
    </form>
</div>

</body>
</html>