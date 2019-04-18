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

            <%--标签头--%>
            <c:import url="resourceheader.jsp"/>

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
                                        <input type="hidden" name="id" value="${resource.id}"/>
                                    </c:if>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">编号</label>
                                        <%--for="input_userid"--%>
                                        <div class="col-sm-10">
                                            <span>${resource.id}</span>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="input_resourcename" class="col-sm-2 control-label">资源名称</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control"
                                                   id="input_resourcename"
                                                   name="name"
                                                   value="${resource.name}"
                                                   placeholder="">
                                            <%--<p class="help-block no-margin">Example block-level help text here.</p>--%>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="input_pid" class="col-sm-2 control-label">父资源</label>
                                        <div class="col-sm-10">
                                            <select name="pid" id="input_pid" class="form-control">
                                                <option value="">无</option>
                                                <c:forEach items="${allTree}" var="node">
                                                    <c:if test="${node.item.id!=resource.id}">
                                                        <%--不打印自己--%>
                                                        <option value="${node.item.id}"
                                                                <c:if test="${node.item.id==resource.pid}">selected</c:if>
                                                        >
                                                                <%--打印空格--%>
                                                            <c:forEach var="i" begin="1" end="${node.depth}" step="1">
                                                                -&nbsp;
                                                            </c:forEach>
                                                                ${node.item.name}
                                                        </option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="input_url" class="col-sm-2 control-label">资源地址</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control"
                                                   id="input_url"
                                                   name="url"
                                                   value="${resource.url}"
                                                   placeholder="">
                                            <%--<p class="help-block no-margin">Example block-level help text here.</p>--%>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="input_description" class="col-sm-2 control-label">描述</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control"
                                                   id="input_description"
                                                   name="description"
                                                   value="${resource.description}"
                                                   placeholder="">
                                            <%--<p class="help-block no-margin">Example block-level help text here.</p>--%>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">状态</label>
                                        <div class="col-sm-10">
                                            <div class="radio">
                                                <label><input name="status" type="radio"
                                                              <c:if test="${resource.status==1}">checked</c:if>
                                                              value="1">开</label>
                                            </div>
                                            <div class="radio">
                                                <label><input name="status" type="radio"
                                                              <c:if test="${resource.status==0}">checked</c:if>
                                                              value="0">关</label>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="input_type_id" class="col-sm-2 control-label">资源类型</label>
                                        <div class="col-sm-10">
                                            <select name="typeId" id="input_type_id" class="form-control">
                                                <c:forEach items="${restypes}" var="item">
                                                    <option value="${item.id}"
                                                            <c:if test="${item.id==resource.typeId}">selected</c:if>
                                                    >${item.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="input_seq" class="col-sm-2 control-label">排序</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control"
                                                   id="input_seq"
                                                   name="seq"
                                                   value="${resource.seq}"
                                                   placeholder="">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="input_icon" class="col-sm-2 control-label">图标(可选)</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control"
                                                   id="input_icon"
                                                   name="icon"
                                                   value="${resource.icon}"
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
        success_return_url = "${pageContext.request.contextPath}/resource/list";
        </c:when>
        <c:otherwise>
        success_return_url = "${pageContext.request.contextPath}/resource/edit?id=${resource.id}";
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