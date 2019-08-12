<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/11/22
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>智销系统</title>
    <%@ include file="/WEB-INF/views/head.jsp"%>
    <script>
        $(function(){
            $("#menuTree").tree({
                url:"/util/treeMenu",
                onClick: function(node){
                    if(node.url){
                        var nodeName = node.text;
                        //判断这个选项卡是否已经存在，存在就选中，不存在重新打开
                        var tab = $("#mainTabs").tabs("getTab",nodeName);
                        if(tab){
                            $("#mainTabs").tabs("select",nodeName);
                        }else{
                            //iframe:在一个页面中嵌入另一个页面(单独的)
                            var content = '<iframe scrolling="auto" frameborder="0"  src="'+node.url+'" style="width:100%;height:100%;"></iframe>';
                            $("#mainTabs").tabs('add',{
                                title:nodeName,
                                content:content,
                                closable:true
                            });
                        }


                    }
                }

            });
        })
    </script>
</head>
<body class="easyui-layout">
    <div data-options="region:'north'" style="height:100px;">
        <h1>源码时代智销系统</h1>
        <div style="text-align: right;padding: 0px 10px 5px 0px">
           欢迎你，亲爱的用户【 ${loginUser.username}】 <a href="/logout">注销</a>
        </div>
    </div>
    <div data-options="region:'west',title:'菜单',split:true" style="width:200px;">
        <ul id="menuTree"></ul>
    </div>
    <div id="mainTabs" class="easyui-tabs" data-options="region:'center'">
        <div title="主页面">
            我是主页面，你管我干什么？
        </div>
    </div>
</body>
</html>
