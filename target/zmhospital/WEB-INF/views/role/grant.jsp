<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>

<!doctype html>
<head>
    <title>Title</title>
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
            <c:import url="roleheader.jsp"/>

            <div class="content-wrap">
                <div class="row">
                    <div class="col-md-12">
                        <section class="panel">
                            <%--<header class="panel-heading">Sortable table</header>--%>
                            <div class="panel-body no-padding">
                                <div>
                                    <h4>正在为角色:(${role.id}) ${role.name} 分配资源</h4>
                                </div>
                                <%--action="${form_url}"--%>
                                <form id="myform" class="form-horizontal" role="form">
                                    <input type="hidden" name="roleId" value="${role.id}"/>
                                    <%--列表--%>
                                    <div class="table-responsive">
                                        <table class="table table-striped no-margin">
                                            <thead>
                                            <tr>
                                                <th width="40px">
                                                    <label><input type="checkbox" id="btn_check_all"/></label>
                                                </th>
                                                <th width="40px">id</th>
                                                <th>资源名称</th>
                                                <th>url</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach var="node" items="${resTree}">
                                                <tr>
                                                    <td>
                                                        <label>
                                                            <input type="checkbox" name="resIds"
                                                                   <c:if test="${node.checked}">checked</c:if>
                                                                   value="${node.item.id}"/>
                                                        </label>
                                                    </td>
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
                                                        <c:out value="${node.item.url}"/>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
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
        //其它设置
        //全选按钮
        $('#btn_check_all').click(function () {
            var $this = $(this);
            var $checkboxs = $('input[name=resids]');
            if ($this.prop("checked")) {
                $checkboxs.prop("checked", true);
            } else {
                $checkboxs.prop("checked", false);
            }
        });
    });
    $(document).ready(function () {
        var $btn_save = $('#button_save');

        //成功后返回的url
        var success_return_url = "${cur_url}";//"${pageContext.request.contextPath}/role/grant";

        //编辑按钮
        $btn_save.click(function () {
            //使用HTML表单来初始化一个FormData对象
            var data = new FormData($('#myform').get(0));

            $.ajax({
                url: "/role/dogrant",
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
