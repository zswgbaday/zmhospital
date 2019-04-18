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

            <%--标签头--%>
            <c:import url="resourceheader.jsp"/>

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
                                            <label class="sr-only" for="search_name">Email</label>
                                            <input type="text" class="form-control" name="search_name" id="search_name"
                                                   value="${pageinfo.condition['search_name']}"
                                                   placeholder="资源名字">
                                        </div>

                                        <%--资源类型--%>
                                        <select name="search_res_type">
                                            <option value="">资源类型</option>
                                            <c:forEach var="item" items="${resTypes}">
                                                <option value="${item.id}"
                                                        <c:if test="${item.id==pageinfo.condition['search_res_type']}">selected</c:if>
                                                >${item.name}</option>
                                            </c:forEach>
                                        </select>

                                        <button type="submit" class="btn btn-default">查询</button>
                                        <a href="${pageContext.request.contextPath}/resource/add"
                                           class="btn btn-default">新增</a>
                                    </form>
                                </div>

                                <%--列表--%>
                                <div class="table-responsive">
                                    <table class="table table-striped responsive" data-sortable>
                                        <thead>
                                        <tr>
                                            <th>id</th>
                                            <th>资源名称</th>
                                            <th>类型</th>
                                            <th>url</th>
                                            <th>排序</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="node" items="${resourceTree}">
                                            <tr>
                                                <td>
                                                    <c:out value="${node.item.id}"/>
                                                </td>
                                                <td>
                                                        <%--打印空格--%>
                                                    <c:forEach var="i" begin="1" end="${node.depth}" step="1">
                                                        -&nbsp;
                                                    </c:forEach>
                                                    <c:out value="${node.item.name}"/>
                                                </td>
                                                <td>
                                                    <c:forEach var="type" items="${resTypes}">
                                                        <c:if test="${type.id==node.item.typeId}">
                                                            ${type.name}
                                                        </c:if>
                                                    </c:forEach>
                                                </td>
                                                <td>
                                                    <c:out value="${node.item.url}"/>
                                                </td>
                                                <td>
                                                    <c:out value="${node.item.seq}"/>
                                                </td>
                                                <td>
                                                    <div class="btn-group">
                                                        <a class="btn btn-success" title="查看">
                                                            <i class="fa fa-book"></i>
                                                        </a>
                                                        <a href="${pageContext.request.contextPath}/resource/edit?id=${node.item.id}"
                                                           class="btn btn-info" title="修改">
                                                            <i class="fa fa-edit"></i>
                                                        </a>
                                                        <a href="${pageContext.request.contextPath}/resource/add?pid=${node.item.id}"
                                                           class="btn btn-warning" title="新增子资源">
                                                            <i class="fa fa-angle-double-up"></i>
                                                        </a>
                                                        <a class="btn btn-danger button_delete"
                                                           data-deleteid="${node.item.id}"
                                                           title="删除">
                                                            <i class="fa fa-remove"></i>
                                                        </a>
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

                <%--批量操作--%>
                <%--<div class="row">--%>
                <%--<button class="delete-all">删除全部</button>--%>
                <%--</div>--%>

                <%--分页--%>
                <%--资源树,不分页--%>
                <%--<div class="row">--%>
                <%--&lt;%&ndash;导入分页&ndash;%&gt;--%>
                <%--<c:import url="/WEB-INF/views/common/pagination.jsp">--%>
                <%--<c:param name="total" value="${pageinfo.total}"/>--%>
                <%--<c:param name="pageSize" value="${pageinfo.pagesize}"/>--%>
                <%--<c:param name="pageUrl" value="${cur_url}"/>--%>
                <%--<c:param name="pageIndex" value="${pageinfo.nowpage}"/>--%>
                <%--<c:param name="pagesCount" value="${pageinfo.totalPage}"/>--%>
                <%--</c:import>--%>
                <%--</div>--%>

                <%--一键创建--%>
                <div class="row">
                    <form class="form-inline" method="get"
                          action="${pageContext.request.contextPath}/resource/autocreate" role="form">
                        <div>一键创建:</div>
                        <div class="form-group">
                            <label class="sr-only" for="search_name">控制器</label>
                            <input type="text" class="form-control" name="controller_name" value=""
                                   placeholder="controller">
                        </div>
                        <div class="form-group">
                            <label class="sr-only" for="search_name">名称</label>
                            <input type="text" class="form-control" name="descript" value=""
                                   placeholder="中文名称">
                        </div>
                        <input type="submit" class="btn btn-default" value="创建"/>
                    </form>
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
                url: "${pageContext.request.contextPath}/resource/delete",
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