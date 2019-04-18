<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/global.jsp" %>

<html>
<head>
    <title></title>
    <%@include file="/commons/basejs.jsp" %>
</head>
<body>
<div>
    <div class="panel-body no-padding">
        <%--搜索--%>
        <div>
            <form id="myform" class="form-inline" method="get" action="${cur_url}" role="form">
                <div class="form-group">
                    <label class="sr-only" for="search_name">Email</label>
                    <input type="text" class="form-control" name="search_name" id="search_name"
                           value="${pageinfo.condition['search_name']}"
                           placeholder="病人名字/身份证号码/诊疗卡号码">
                </div>
                <button type="submit" class="btn btn-default">查询</button>
            </form>
        </div>
        <%--列表--%>
        <div class="table-responsive">
            <table class="table table-striped responsive" data-sortable>
                <thead>
                <tr>
                    <th>id</th>
                    <th>姓名</th>
                    <th>年龄</th>
                    <th>电话</th>
                    <th>身份证号</th>
                    <th>诊疗卡号</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${pageinfo.list}">
                    <tr>
                        <td>
                            <span><c:out value="${item.id}"/></span>
                        </td>
                        <td>
                            <span><c:out value="${item.name}"/></span>
                        </td>
                        <td>
                            <c:out value="${item.age}"/>
                        </td>
                        <td>
                            <c:out value="${item.phone}"/>
                        </td>
                        <td>
                            <c:out value="${item.identityCard}"/>
                        </td>
                        <td>
                            <c:out value="${item.medicalCard}"/>
                        </td>
                        <td>
                            <div class="btn-group">
                                <input class="patient_select" type="button" value="选择"
                                       data-patient-id="<c:out value="${item.id}"/>"
                                       data-patient-name="<c:out value="${item.name}"/>"/>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $('.patient_select').click(function () {
            var $this = $(this);
            // var $tr = $this.parents("tr");
            //调用父页面的回调
            parent.setPatient($this.attr("data-patient-id"), $this.attr("data-patient-name"));
        });
    });
</script>
</body>
</html>
