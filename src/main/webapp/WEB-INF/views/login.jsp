<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/11/28
  Time: 9:34
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: zhaoyi
  Date: 2018/10/14
  Time: 下午4:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>源码智销系统</title>
    <%@ include file="/WEB-INF/views/head.jsp"%>
    <script>
        //代表我不是一个单独的页面
        if(window!=top){
            //alert("我是一个iframe")
            top.location.href = "/login";
        }

        function submitForm() {
            //直接登录 easyui的from提求:默认就是Ajax的方式
            $("#loginForm").form("submit",{
                url:"/login",
                // onSubmit: function(){
                //     //提交前做相应的验证
                // },
                success:function(data){
                    var result = JSON.parse(data);
                    if(result.success){
                        //成功就跳到主页面 (以前BOM【浏览器对象模型】部分)
                        window.location.href ="/main";
                    }else{
                        //失败给出提示信息
                        $.messager.alert('错误',result.msg,"error");
                    }
                }
            })
        }

        //想办法监听一下咱们的键盘事件
        $(document.documentElement).on("keyup", function(event) {
            //console.debug(event);
            var keyCode = event.keyCode;
            if(keyCode==13){ //13 = Enter
                submitForm();
            }
        })
    </script>
</head>
<body>

<div align="center" style="margin-top: 100px;">
    <div class="easyui-panel" title="智销系统用户登陆" style="width: 350px; height: 240px;">
        <form id="loginForm" action="/login"  class="easyui-form" method="post">
            <table align="center" style="margin-top: 15px;">
                <tr height="20">
                    <td>用户名:</td>
                </tr>
                <tr height="10">
                    <td><input name="username" class="easyui-validatebox" required="true" value="admin" /></td>
                </tr>
                <tr height="20">
                    <td>密&emsp;码:</td>
                </tr>
                <tr height="10">
                    <td><input name="password" type="password" class="easyui-validatebox" required="true" value="admin" /></td>
                </tr>
                <tr height="40">
                    <td align="center">
                        <a href="javascript:;" class="easyui-linkbutton" onclick="submitForm();">登陆</a> <a
                            href="javascript:;" class="easyui-linkbutton" onclick="resetForm();">重置</a></td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>