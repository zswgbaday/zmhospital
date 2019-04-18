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
                                <%--action="${form_url}"--%>
                                <form id="myform" class="form-horizontal" role="form">
                                    <%--表单--%>
                                    <c:if test="${action=='edit'}">
                                        <input type="hidden" name="id" value="${patient.id}"/>
                                    </c:if>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">编号</label>
                                        <%--for="input_userid"--%>
                                        <div class="col-sm-10">
                                            <span>${patient.id}</span>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="input_patientname"
                                               class="col-sm-2 control-label">姓名</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control"
                                                   id="input_patientname"
                                                   name="name"
                                                   value="${patient.name}"
                                                   placeholder="">
                                            <%--<p class="help-block no-margin">Example block-level help text here.</p>--%>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="input_age"
                                               class="col-sm-2 control-label">年龄</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control"
                                                   id="input_age"
                                                   name="age"
                                                   value="${patient.age}"
                                                   placeholder="">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="input_medicalCard"
                                               class="col-sm-2 control-label">性别</label>
                                        <div class="col-sm-10">
                                            <label>
                                                <input type="radio" name="sex" value="1"
                                                       <c:if test="${patient.sex==true}">checked</c:if>
                                                />
                                                男
                                            </label>
                                            <label>
                                                <input type="radio" name="sex" value="0"
                                                       <c:if test="${patient.sex==false}">checked</c:if>
                                                />
                                                女
                                            </label>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="input_phone"
                                               class="col-sm-2 control-label">手机</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control"
                                                   id="input_phone"
                                                   name="phone"
                                                   value="${patient.phone}"
                                                   placeholder="">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="input_identityCard"
                                               class="col-sm-2 control-label">身份证</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control"
                                                   id="input_identityCard"
                                                   name="identityCard"
                                                   value="${patient.identityCard}"
                                                   placeholder="">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="input_medicalCard"
                                               class="col-sm-2 control-label">医疗卡号</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control"
                                                   id="input_medicalCard"
                                                   name="medicalCard"
                                                   value="${patient.medicalCard}"
                                                   placeholder="">
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
        success_return_url = "${pageContext.request.contextPath}/patient/list";
        </c:when>
        <c:otherwise>
        success_return_url = "${pageContext.request.contextPath}/patient/edit?id=${patient.id}";
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