<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--高亮当前标签--%>
<script type="text/javascript">
    $(document).ready(function () {
        var url = window.location.href;
        $("#header_ul").find("li").each(function () {
            var $this = $(this);
            if (url.indexOf($this.attr("data-li")) > -1) {
                $this.addClass("active");
            }
        });
    })
</script>

<header class="header navbar bg-default">
    <ul id="header_ul" class="nav navbar-nav">
        <li data-li="user/list">
            <a href="${pageContext.request.contextPath}/resource/list">列表</a>
        </li>
    </ul>
</header>
