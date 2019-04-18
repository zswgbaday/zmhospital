<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>

<!doctype html>
<html class="no-js" lang="">

<head>
    <title>用户编辑</title>
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
            <c:import url="userheader.jsp"/>

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
                                        <input type="hidden" name="id" value="${user.id}"/>
                                    </c:if>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">编号</label>
                                        <%--for="input_userid"--%>
                                        <div class="col-sm-10">
                                            <span>${user.id}</span>
                                            <%--<input type="email" class="form-control"--%>
                                            <%--id="input_${user.id}"--%>
                                            <%--name="id"--%>
                                            <%--value="${user.id}"--%>
                                            <%--placeholder="编号">--%>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="input_loginname" class="col-sm-2 control-label">登录名</label>
                                        <div class="col-sm-10">
                                            <c:choose>
                                                <c:when test="${action=='add'}">
                                                    <%--新增模式下才开放编辑用户名--%>
                                                    <input type="text" class="form-control"
                                                           id="input_loginname"
                                                           name="loginname"
                                                           value="${user.loginname}"
                                                           placeholder="登录名">
                                                </c:when>
                                                <c:otherwise>
                                                    <span>${user.loginname}</span>
                                                </c:otherwise>
                                            </c:choose>
                                            <%--<p class="help-block no-margin">Example block-level help text here.</p>--%>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="input_username" class="col-sm-2 control-label">昵称</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control"
                                                   id="input_username"
                                                   name="name"
                                                   value="${user.name}"
                                                   placeholder="昵称">
                                            <%--<p class="help-block no-margin">Example block-level help text here.</p>--%>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="input_password" class="col-sm-2 control-label">密码</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control"
                                                   id="input_password"
                                                   name="password"
                                                   value=""
                                                   placeholder="密码">
                                            <%--<p class="help-block no-margin">Example block-level help text here.</p>--%>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="input_password2" class="col-sm-2 control-label">重复密码</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control"
                                                   id="input_password2"
                                                   name="password2"
                                                   value=""
                                                   placeholder="重复密码">
                                            <%--<p class="help-block no-margin">Example block-level help text here.</p>--%>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="input_useremail" class="col-sm-2 control-label">邮箱</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control"
                                                   id="input_useremail"
                                                   name="email"
                                                   value="${user.email}"
                                                   placeholder="邮箱">
                                            <%--<p class="help-block no-margin">Example block-level help text here.</p>--%>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="input_userphone" class="col-sm-2 control-label">手机</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control"
                                                   id="input_userphone"
                                                   name="phone"
                                                   value="${user.phone}"
                                                   placeholder="手机">
                                            <%--<p class="help-block no-margin">Example block-level help text here.</p>--%>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">角色</label>
                                        <div class="col-sm-10">
                                            <%--<c:forEach var="role" items="${roles}">--%>
                                                <%--<div class="checkbox">--%>
                                                    <%--<label>--%>
                                                        <%--<input type="checkbox" name="roles"--%>
                                                        <%--<c:forEach var="uRoleId" items="${userRoleIds}">--%>
                                                            <%--<c:if test="${role.id==uRoleId}">--%>

                                                        <%--</c:if>--%>
                                                        <%--</c:forEach>--%>
                                                               <%--value="${role.id}">${role.name}--%>
                                                    <%--</label>--%>
                                                <%--</div>--%>
                                            <%--</c:forEach>--%>
                                            <c:forEach items="${roleOptions}" var="option">
                                                <div class="checkbox">
                                                    <label>
                                                        <input type="checkbox" name="roles" ${option.selected}
                                                               value="${option.value}">${option.name}
                                                    </label>
                                                </div>
                                            </c:forEach>
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
        success_return_url = "${pageContext.request.contextPath}/user/list";
        </c:when>
        <c:otherwise>
        success_return_url = "${pageContext.request.contextPath}/user/edit?id=${user.id}";
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
//                    var dataobj = $.parseJSON(data);
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