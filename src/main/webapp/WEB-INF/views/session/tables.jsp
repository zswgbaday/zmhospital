<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>

<!doctype html>
<html class="no-js" lang="">
<head>
    <title></title>
    <%@include file="/commons/basejs.jsp" %>
</head>
<body>

<div class="app">

    <%--头部--%>
    <c:import url="../common/header.jsp"/>

    <section class="layout">

        <%--导入菜单--%>
        <c:import url="../common/menu.jsp"/>

        <%--主要页面--%>
        <section class="main-content">

            <div class="content-wrap">
                <div class="row">
                    <div class="col-md-12">
                        <section class="panel">
                            <%--<header class="panel-heading">Sortable table</header>--%>
                            <div class="panel-body no-padding">
                                <%--搜索--%>
                                <%--                                <div>
                                                                    <form id="myform" class="form-inline" method="get" action="${cur_url}" role="form">
                                                                        <div class="form-group">
                                                                            <label class="sr-only" for="search_name">Email</label>
                                                                            <input type="text" class="form-control" name="search_name" id="search_name"
                                                                                   value="${pageinfo.condition['search_name']}"
                                                                                   placeholder="科室名称">
                                                                        </div>
                                                                        <button type="submit" class="btn btn-default">查询</button>
                                                                        <shiro:hasPermission name="/department/add">
                                                                            <a href="${add_url}" class="btn btn-default">新增</a>
                                                                        </shiro:hasPermission>
                                                                    </form>
                                                                </div>--%>
                                共有 <c:out value="${total}"/> 个在线用户
                                <%--列表--%>
                                <div class="table-responsive">
                                    <table class="table table-striped responsive" data-sortable>
                                        <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>身份</th>
                                            <th>状态</th>
                                            <th>开始时间</th>
                                            <th>最后访问时间</th>
                                            <th>超时</th>
                                            <th>主机</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="session" items="${sessions}">
                                            <tr>
                                                <td>
                                                    <c:out value="${session.id}"/>
                                                </td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${session.login==true}">
                                                            <c:out value="${session.loginName}"/>
                                                            <c:if test="${session.id==curSession.id}"><span
                                                                    style="color: red;">(我)</span></c:if>
                                                        </c:when>
                                                        <c:when test="${session.login==false}">游客</c:when>
                                                    </c:choose>
                                                </td>
                                                <td>
                                                    <c:out value="${session.status}"/>
                                                </td>
                                                <td>
                                                    <fmt:formatDate value="${session.startTimestamp}"
                                                                    pattern="yyyy-MM-dd HH:mm:ss"/>
                                                </td>
                                                <td>
                                                    <fmt:formatDate value="${session.lastAccessTime}"
                                                                    pattern="yyyy-MM-dd HH:mm:ss"/>
                                                </td>
                                                <td>
                                                    <c:out value="${session.timeout}"/>ms
                                                </td>
                                                <td>
                                                    <c:out value="${session.host}"/>
                                                </td>
                                                <td>
                                                    <div class="btn-group">

                                                        <shiro:hasPermission name="/session/kick">

                                                            <c:if test="${session.id!=curSession.id}">
                                                                <input class="btn btn-default btn_kick" type="button"
                                                                       value="踢出" data-sid="${session.id}">
                                                            </c:if>
                                                        </shiro:hasPermission>

                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </section>
                    </div>
                </div>
                <%--                <div class="row">
                                    &lt;%&ndash;导入分页&ndash;%&gt;
                                    <c:import url="/WEB-INF/views/common/pagination.jsp">
                                        <c:param name="total" value="${pageinfo.total}"/>
                                        <c:param name="pageSize" value="${pageinfo.pagesize}"/>
                                        <c:param name="pageUrl" value="${cur_url}"/>
                                        <c:param name="pageIndex" value="${pageinfo.nowpage}"/>
                                        <c:param name="pagesCount" value="${pageinfo.totalPage}"/>
                                    </c:import>
                                </div>--%>
            </div>

        </section>

    </section>
</div>

<%@include file="/commons/basejs2.jsp" %>
<script type="text/javascript">
    $(document).ready(function () {
        var $btn_deletes = $('input.btn_kick');

        //按钮
        $btn_deletes.click(function () {
            //本按钮
            var $this = $(this);

            $.ajax({
                url: "${pageContext.request.contextPath}/session/kick",
                type: "POST",
                datatype: "json",
                data: {
                    sid: $this.attr("data-sid")
                },
                success: function (data, textStatus) {
                    var index = layer.alert(data.msg, {icon: 3, title: '提示'}, function (index) {
                        //do something
                        if (data.success) {
                            window.location.reload(true);
                        } else {
                            layer.close(index);
                        }
                    });
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log(XMLHttpRequest + textStatus + errorThrown);
                }
            });
        });

    });
</script>


</body>
</html>