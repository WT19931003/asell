<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/11/22
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="/WEB-INF/views/head.jsp"%>
    <!-- 引入自己的js -->
    <script src="/js/model/employee.js"></script>
</head>
<body>

<!-- 数据展示 -->
<table id="dataGrid" class="easyui-datagrid" style="width:400px;height:250px"
       data-options="
       url:'/employee/page',
       fitColumns:true,
       singleSelect:true,
       fit:true,
       toolbar:'#tb',
       pagination:true">
    <thead>
    <tr>
        <th data-options="field:'headImage',width:100,formatter:imgFormat">头像</th>
        <th data-options="field:'username',width:100">用户名</th>
        <th data-options="field:'password',width:100">密码</th>
        <th data-options="field:'email',width:100,align:'right'">邮箱</th>
        <th data-options="field:'age',width:100,align:'right'">年龄</th>
        <th data-options="field:'department',width:100,align:'right',formatter:deptFormat">部门</th>
    </tr>
    </thead>
</table>
<!-- 高级查询与按钮 -->
<div id="tb" style="padding:5px;height:auto">
    <div style="margin-bottom:5px">
        <a href="#" data-method="add" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        <a href="#" data-method="edit" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
        <shiro:hasPermission name="employee:delete">
            <a href="#" data-method="delete" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
        </shiro:hasPermission>
    </div>
    <div>
        <form id="searchForm" action="/employee/download">
            用户名: <input name="username" class="easyui-textbox" style="width:80px">
            邮件: <input name="email" class="easyui-textbox" style="width:80px">
            部门:<input name="departmentId" class="easyui-combobox" name="dept"
                      panelHeight="auto"
                      data-options="valueField:'id',textField:'name',url:'/util/dept'" />

            <a href="#" class="easyui-linkbutton" data-method="search" iconCls="icon-search">查询</a>
            <!--  button不加type属性就是提交 -->
            <button class="easyui-linkbutton" iconCls="icon-search">导出</button>
        </form>
    </div>
</div>
<!-- 添加与修改的弹出框 -->
<div id="employeeDialog" class="easyui-dialog" title="功能操作" style="width:280px;padding: 10px;"
     data-options="iconCls:'icon-save',modal:true,closed:true">
    <!-- 表单准备 -->
    <form id="editForm" action="" method="post">
        <input id="employeeId" type="hidden" name="id" />
        <table>
            <tr>
                <td>名称:</td>
                <td><input name="username" class="f1 easyui-validatebox" validType="checkName" data-options="required:true"></input></td>
            </tr>
            <tr data-save="true">
                <td>密码:</td>
                <td><input id="password" type="password" name="password"  class="f1 easyui-validatebox" data-options="required:true"></input></td>
            </tr>
            <tr data-save="true">
                <td>确认密码:</td>
                <td><input name="rePassword" type="password" class="f1 easyui-validatebox" validType="equals['password','id']" data-options="required:true"></input></td>
            </tr>
            <tr>
                <td>邮件:</td>
                <td><input name="email" class="f1 easyui-validatebox" data-options="validType:'email'"></input></td>
            </tr>
            <tr>
                <td>年龄:</td>
                <td><input name="age" class="f1 easyui-validatebox" data-options="validType:'integerRange[8,80]'"></input></td>
            </tr>
            <tr>
                <td>部门:</td>
                <td>
                    <input name="department.id" class="easyui-combobox" name="dept"
                           panelHeight="auto"
                           data-options="valueField:'id',textField:'name',url:'/util/dept',required:true" />
                </td>
            </tr>
        </table>
        <div style="text-align:center;padding:5px">
            <a href="#"  class="easyui-linkbutton" data-method="save" iconCls="icon-save">确定</a>
            <a href="#"  class="easyui-linkbutton" onclick="$('#employeeDialog').dialog('close')" iconCls="icon-cancel">取消</a>
        </div>
    </form>
</div>

</body>
</html>
