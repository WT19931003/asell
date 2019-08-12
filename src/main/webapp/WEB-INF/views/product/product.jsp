<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="/WEB-INF/views/head.jsp" %>
    <!-- 引入自己的js -->
    <script src="/js/model/product.js"></script>
    <!--  引入tooltip这个东西-->
    <script type="text/javascript" src="/easyui/plugin/tooltip/jeasyui.extensions.base.tooltip.js"></script>
</head>
<body>

<!-- 数据展示 -->
<table id="dataGrid" class="easyui-datagrid" style="width:400px;height:250px"
       data-options="
       url:'/product/page',
       fitColumns:true,
       singleSelect:true,
       fit:true,
       toolbar:'#tb',
       pagination:true,onLoadSuccess:loadSuccess">
    <thead>
    <tr>
        <th width="20" field="name">名称</th>
        <th width="20" field="color" data-options="formatter:formatColor">颜色</th>
        <th width="20" field="smallpic" data-options="formatter:formatImg">图片</th>
        <th width="20" field="costprice">成本价</th>
        <th width="20" field="saleprice">销售价</th>
        <th width="20" field="types" data-options="formatter:formatObj">类型</th>
        <th width="20" field="unit" data-options="formatter:formatObj">单位</th>
        <th width="20" field="brand" data-options="formatter:formatObj">品牌</th>
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
<div id="productDialog" class="easyui-dialog" title="功能操作" style="width:320px;padding: 10px;"
     data-options="iconCls:'icon-save',modal:true,closed:true">
    <!-- 表单准备 -->
    <form id="editForm" action="" method="post" enctype="multipart/form-data">
        <input id="productId" type="hidden" name="id"/>
        <table>
            <tr>
                <td>名称:</td>
                <td><input class="easyui-validatebox" type="text" name="name" data-options="required:true"></input></td>
            </tr>
            <tr>
                <td>颜色:</td>
                <td><input class="easyui-validatebox" type="color" name="color"></input></td>
            </tr>
            <tr>
                <td>成本价:</td>
                <td><input class="easyui-validatebox" type="text" name="costprice"></input></td>
            </tr>
            <tr>
                <td>销售价:</td>
                <td><input class="easyui-validatebox" type="text" name="saleprice"></input></td>
            </tr>
            <tr>
                <td>产品图片:</td>
                <td>
                    <input name="fileImage" class="easyui-filebox" style="width:100%">
                </td>
            </tr>
            <tr>
                <td>单位:</td>
                <td>
                    <input  class="easyui-combobox" name="unit.id"
                            data-options="valueField:'id',textField:'name',panelHeight:'auto',url:'/util/findAllUnit'">
                </td>
            </tr>
            <tr>
                <td>品牌:</td>
                <td>
                    <input  class="easyui-combobox" name="brand.id"
                            data-options="valueField:'id',textField:'name',panelHeight:'auto',url:'/util/findAllBrand'">
                </td>
            </tr>
            <tr>
                <td>类型:</td>
                <td>

                    <input  class="easyui-combobox" name="types.id"
                            data-options="groupField:'group',valueField:'id',textField:'name',panelHeight:'auto',url:'/util/findChildren'">
                </td>
            </tr>
        </table>
        <div style="text-align:center;padding:5px">
            <a href="#" class="easyui-linkbutton" data-method="save" iconCls="icon-save">确定</a>
            <a href="#" class="easyui-linkbutton" onclick="$('#productDialog').dialog('close')"
               iconCls="icon-cancel">取消</a>
        </div>
    </form>
</div>

</body>
</html>