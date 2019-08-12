<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="/WEB-INF/views/head.jsp"%>
    <!-- 引入自己的js -->
    <script src="/js/model/purchasebillitem.js"></script>
    <!-- 引入相应的grid的js -->
    <script type="text/javascript" src="/easyui/plugin/datagrid-groupview.js"></script>
    <!-- 引入相应的highchart的js -->
    <script src="/js/highcharts/code/highcharts.js"></script>
    <script src="/js/highcharts/code/highcharts-3d.js"></script>
    <script src="/js/highcharts/code/modules/exporting.js"></script>
    <script src="/js/highcharts/code/modules/export-data.js"></script>
</head>
<body>

<table id="itemsGrid"></table>
<form id="searchForm" action="" style="padding: 0px;margin: 0px;">
    采购时间: <input name="beginDate" class="easyui-datebox" style="width:120px">
    -
    <input name="endDate" class="easyui-datebox" style="width:120px">
    状态:<select  class="easyui-combobox" name="status" panelWidth="auto" panelHeight="auto" style="width:100px;">
    <option value="">--请选择--</option>
    <option value="0">待审</option>
    <option value="1">已审</option>
    <option value="-1">作废</option>
</select>
    <select  class="easyui-combobox" name="groupType" panelWidth="auto" panelHeight="auto" style="width:100px;">
        <option value="1">供应商</option>
        <option value="2">采购员</option>
        <option value="3">月份</option>
    </select>
    <a href="#" class="easyui-linkbutton" data-method="search" iconCls="icon-search">查询</a>
    <a href="#" class="easyui-linkbutton" data-method="chart3D" iconCls="icon-search">3D</a>
    <a href="#" class="easyui-linkbutton" data-method="chart2D" iconCls="icon-search">2D</a>
</form>


<div id="chartDialog" class="easyui-dialog" title="3弟"  style="width:600px;height:400px;"
     data-options="modal:true,closed:true">
</div>


</body>
</html>