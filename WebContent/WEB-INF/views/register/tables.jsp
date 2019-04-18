<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>

<!doctype html>
<html class="no-js" lang="">
<head>
    <title>列表</title>
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
                                <div>
                                    <form id="myform" class="form-inline" method="get" action="${cur_url}" role="form">
                                        <div class="form-group">
                                            <label class="sr-only" for="search_name"></label>
                                            <input type="text" class="form-control" name="search_name" id="search_name"
                                                   value="${pageinfo.condition['search_name']}"
                                                   placeholder="病人名称">
                                            <input type="text" class="form-control" name="search_doctor"
                                                   id="search_doctor"
                                                   value="${pageinfo.condition['search_doctor']}"
                                                   placeholder="医生名称">
                                            <input type="text" class="form-control" name="search_user" id="search_user"
                                                   value="${pageinfo.condition['search_user']}"
                                                   placeholder="操作人员名称">
                                        </div>

                                        <%--科室--%>
                                        <select name="search_depart">
                                            <option value="">选择科室</option>
                                            <c:forEach var="item" items="${departments}">
                                                <option value="${item.id}"
                                                        <c:if test="${item.id==pageinfo.condition['search_depart']}">selected</c:if>
                                                >${item.name}</option>
                                            </c:forEach>
                                        </select>

                                        <button type="submit" class="btn btn-default">查询</button>
                                        <shiro:hasPermission name="/register/add">
                                            <a href="${add_url}" class="btn btn-default">新增挂号</a>
                                        </shiro:hasPermission>

                                        <shiro:hasPermission name="/patient/add">
                                            <a href="${pageContext.request.contextPath}/patient/add" class="btn btn-default">新增病人</a>
                                        </shiro:hasPermission>
                                    </form>
                                </div>
                                <%--列表--%>
                                <div class="table-responsive">
                                    <table class="table table-striped responsive" data-sortable>
                                        <thead>
                                        <tr>
                                            <th>id</th>
                                            <th>病人</th>
                                            <th>医生</th>
                                            <th>科室</th>
                                            <th>操作人员</th>
                                            <th>状态</th>
                                            <th>录入时间</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="item" items="${pageinfo.list}">
                                            <tr>
                                                <td>
                                                    <c:out value="${item.id}"/>
                                                </td>
                                                <td>
                                                    <c:out value="${item.patient.name}"/>
                                                </td>
                                                <td>
                                                    <c:out value="${item.doctor.realName}"/>
                                                </td>
                                                <td>
                                                    <c:out value="${item.department.name}"/>
                                                </td>
                                                <td>
                                                    <c:out value="${item.user.name}"/>
                                                </td>
                                                <td>
                                                    <c:if test="${item.status==0}">未完成</c:if>
                                                    <c:if test="${item.status==1}">完成</c:if>
                                                </td>
                                                <td>
                                                        <%--<c:out value="${item.time}"/>--%>
                                                    <fmt:formatDate value="${item.time}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                                </td>
                                                <td>
                                                    <div class="btn-group">

                                                        <a class="btn btn-success" title="查看">
                                                            <i class="fa fa-book"></i>
                                                        </a>

                                                        <shiro:hasPermission name="/register/edit">
                                                            <a href="${edit_url}?id=${item.id}" class="btn btn-info"
                                                               title="修改">
                                                                <i class="fa fa-edit"></i>
                                                            </a>
                                                        </shiro:hasPermission>

                                                        <shiro:hasPermission name="/register/delete">
                                                            <a class="btn btn-danger button_delete"
                                                               data-deleteid="${item.id}"
                                                               title="删除">
                                                                <i class="fa fa-remove"></i>
                                                            </a>
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
                <div class="row">
                    <%--导入分页--%>
                    <c:import url="/WEB-INF/views/common/pagination.jsp">
                        <c:param name="total" value="${pageinfo.total}"/>
                        <c:param name="pageSize" value="${pageinfo.pagesize}"/>
                        <c:param name="pageUrl" value="${cur_url}"/>
                        <c:param name="pageIndex" value="${pageinfo.nowpage}"/>
                        <c:param name="pagesCount" value="${pageinfo.totalPage}"/>
                    </c:import>
                </div>
            </div>

        </section>

    </section>
</div>

<%@include file="/commons/basejs2.jsp" %>
<script type="text/javascript">
    $(document).ready(function () {
        var $btn_deletes = $('a.button_delete');

        //删除按钮
        $btn_deletes.click(function () {
            //本按钮
            var $this = $(this);

            $.ajax({
                url: "${delete_url}",
                type: "POST",
                datatype: "json",
                data: {
                    id: $this.attr("data-deleteid")
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