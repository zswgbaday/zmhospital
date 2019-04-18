<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<html>
<head>
    <title></title>
    <%@include file="/commons/basejs.jsp" %>
</head>

<body class="bg-color center-wrapper">
<div class="center-content">
    <div class="row">
        <div class="col-xs-10 col-xs-offset-1 col-sm-6 col-sm-offset-3 col-md-4 col-md-offset-4">
            <section class="panel panel-default">
                <header class="panel-heading">Sign in</header>
                <div class="bg-white user pd-md">
                    <h6><strong>欢迎.</strong>登陆去开始!</h6>
                    <form id="myform" role="form" action="" method="post">
                        <input type="text" name="username" class="form-control mg-b-sm" placeholder="username"
                               autofocus>
                        <input type="password" name="password" class="form-control" placeholder="password">
                        <%--<select name="login_type">--%>
                        <%--<option value="1">普通用户</option>--%>
                        <%--<option value="2">管理员</option>--%>
                        <%--</select>--%>
                        <label class="checkbox pull-left">
                            <input type="checkbox" value="remember-me">记住我
                        </label>
                        <div class="text-right mg-b-sm mg-t-sm">
                            <a href="javascript:void(0);">忘记密码?</a>
                        </div>
                        <input id="btn_submit" type="button" class="btn btn-info btn-block" value="Sign in"/>
                        <%--<button id="btn_submit" class="btn btn-info btn-block">Sign in</button>--%>
                    </form>
                    <%--<p class="center-block mg-t mg-b text-center">OR</p>--%>
                    <%--<p>--%>
                    <%--<a class="btn btn-primary btn-block mg-b-sm">--%>
                    <%--<i class="fa fa-facebook mg-r-md pull-left pd-l-md pd-r-md pd-t-xs"></i>Login with Facebook--%>
                    <%--</a>--%>
                    <%--<a class="btn btn-info btn-block">--%>
                    <%--<i class="fa fa-twitter mg-r-md pull-left pd-l-md pd-r-md pd-t-xs"></i>Login with Twitter--%>
                    <%--</a>--%>
                    <%--</p>--%>
                    <%--<p class="center-block mg-t mg-b text-right">Dont have and account?--%>
                    <%--<a href="">Signup here.</a>--%>
                    <%--</p>--%>
                </div>
            </section>
        </div>
    </div>
</div>

<%@include file="/commons/basejs2.jsp" %>
<script type="text/javascript">
    $(document).ready(function () {
        function submitForm() {
            //使用HTML表单来初始化一个FormData对象
            var data = new FormData($('#myform').get(0));

            $.ajax({
                url: "${pageContext.request.contextPath}/login",
                type: "POST",
                datatype: "json",
                data: data,
                processData: false,  // 告诉jQuery不要去处理发送的数据
                contentType: false,   // 告诉jQuery不要去设置Content-Type请求头
                success: function (data, textStatus) {

                    var index = layer.alert(data.msg, {icon: 3, title: '提示'}, function (index) {
                        //do something
                        if (data.success) {
                            window.location.href = data.obj;
//                            window.location.reload(true);
                        } else {
                            layer.close(index);
                        }
                    });
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log(XMLHttpRequest + textStatus + errorThrown);
                }
            });
        }

        $('#btn_submit').click(submitForm).keydown(function (event) {
            //回车键触发提交事件
            if (event.keyCode == 13) {
                submitForm();
            }
        });
    })
</script>

<script type="text/javascript">
    $(document).ready(function () {
        var msg = "${msg}";
        if (msg != "") {
            layer.alert(msg);
        }
    });
</script>
</body>

</html>
