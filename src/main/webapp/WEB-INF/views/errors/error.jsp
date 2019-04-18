<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="description" content="Flat, Clean, Responsive, admin template built with bootstrap 3">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1, maximum-scale=1">

    <title>500</title>
    <%@include file="/commons/basejs.jsp" %>

</head>
<body class="bg-white center-wrapper">

<div class="center-content text-center">
    <div class="error-number">500</div>
    <div class="mg-b-lg">发生了错误</div>
    <p> ${msg}
        <br>
        <a href="${pageContext.request.contextPath}/index">返回首页</a>
    </p>
    <ul class="mg-t-lg error-nav">
        <li>
            <a href="javascript:;">&copy;
                <span id="year" class="mg-r-xs"></span>项目</a>
        </li>
        <li>
            <a href="javascript:;">关于</a>
        </li>
        <li>
            <a href="javascript:;">帮助</a>
        </li>
        <li>
            <a href="javascript:;">状态</a>
        </li>
    </ul>
</div>

<script type="text/javascript">
    var el = document.getElementById("year"),
            year = (new Date().getFullYear());
    el.innerHTML = year;
</script>
</body>
</html>
