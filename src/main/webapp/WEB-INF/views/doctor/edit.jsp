<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>

<!doctype html>
<html class="no-js" lang="">

<head>
    <title>角色</title>
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
                                <%--action="${form_url}"--%>
                                <form id="myform" class="form-horizontal" role="form">
                                    <%--表单--%>
                                    <c:if test="${action=='edit'}">
                                        <input type="hidden" name="id" value="${doctor.id}"/>
                                    </c:if>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">编号</label>
                                        <%--for="input_userid"--%>
                                        <div class="col-sm-10">
                                            <span>${doctor.id}</span>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="input_realName"
                                               class="col-sm-2 control-label">姓名</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control"
                                                   id="input_realName"
                                                   name="realName"
                                                   value="${doctor.realName}"
                                                   placeholder="">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">性别</label>
                                        <div class="col-sm-10">
                                            <label>
                                                <input type="radio" name="sex" value="1"
                                                       <c:if test="${doctor.sex==true}">checked</c:if>
                                                />
                                                男
                                            </label>
                                            <label>
                                                <input type="radio" name="sex" value="0"
                                                       <c:if test="${doctor.sex==false}">checked</c:if>
                                                />
                                                女
                                            </label>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="select_departmentId"
                                               class="col-sm-2 control-label">科室</label>
                                        <div class="col-sm-10">
                                            <select id="select_departmentId" name="departmentId">
                                                <c:forEach var="depart" items="${departments}">
                                                    <option value="${depart.id}"
                                                            <c:if test="${depart.id==doctor.departmentId}">selected</c:if>
                                                    >${depart.name}
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>


                                    <div class="form-group">
                                        <label for="input_position"
                                               class="col-sm-2 control-label">职位</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control"
                                                   id="input_position"
                                                   name="position"
                                                   value="${doctor.position}"
                                                   placeholder="专业医师/高级医师/...">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="input_desciption"
                                               class="col-sm-2 control-label">描述</label>
                                        <div class="col-sm-10">
                                            <textarea id="input_desciption" name="desciption" class="form-control">${doctor.desciption}</textarea>
                                        </div>
                                    </div>

                                    <%--按钮组--%>
                                    <div class="form-group">
                                        <div class="col-sm-offset-2 col-sm-10">
                                            <button id="button_save" type="button" class="btn btn-default">保存</button>
                                        </div>
                                    </div>

                                </form>
                            </div>
                        </section>
                    </div>
                </div>
            </div>

        </section>

    </section>
</div>

<%@include file="/commons/basejs2.jsp" %>
<script type="text/javascript">
    $(document).ready(function () {
        var $btn_save = $('#button_save');

        //成功后返回的url
        var success_return_url;
        <c:choose>
        <c:when test="${action=='add'}">
        success_return_url = "${pageContext.request.contextPath}/doctor/list";
        </c:when>
        <c:otherwise>
        success_return_url = "${pageContext.request.contextPath}/doctor/edit?id=${doctor.id}";
        </c:otherwise>
        </c:choose>

        //编辑按钮
        $btn_save.click(function () {
            //使用HTML表单来初始化一个FormData对象
            var data = new FormData($('#myform').get(0));

            $.ajax({
                url: "${form_url}",
                type: "POST",
                datatype: "json",
//                timeout: 3000,
                data: data,
                processData: false,  // 告诉jQuery不要去处理发送的数据
                contentType: false,   // 告诉jQuery不要去设置Content-Type请求头
                success: function (data, textStatus) {
                    //var dataobj = $.parseJSON(data);
                    var index = layer.alert(data.msg, {icon: 3, title: '提示'}, function (index) {
                        //do something
                        if (data.success) {
                            window.location.href = success_return_url;
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
        });


    });
</script>

</body>

</html>