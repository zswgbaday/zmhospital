<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>

<!doctype html>
<html class="no-js" lang="">

<head>
    <title></title>
    <%@include file="/commons/basejs.jsp" %>

    <style>
        /*科室,医生的ul*/
        .ul_list {
            padding: 6px 12px;
        }

        .ul_list li {
            float: left;
            margin: 1px;
            width: 100px;
        }

        .ul_list label {
            float: none;
            width: inherit;
        }

        .ul_list a {
            cursor: pointer;
            color: #695fff;
            position: absolute;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: pre;
            width: inherit;
        }

        .form-horizontal .control-label {

        }
    </style>
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
                                        <input type="hidden" name="id" value="${registerBo.id}"/>
                                    </c:if>

                                    <%--选择区域--%>
                                    <section class="panel panel-color">
                                        <div class="panel-heading">选择
                                        </div>
                                        <div class="panel-body">
                                            <%--选择科室--%>
                                            <div class="form-group">
                                                <span class="col-sm-2 control-label">科室:</span>
                                                <div class="col-sm-10">
                                                    <ul id="departs" class="ul_list">
                                                        <c:forEach var="depart" items="${departments}">
                                                            <li><a data-id="${depart.id}">${depart.name}</a></li>
                                                        </c:forEach>
                                                    </ul>
                                                </div>
                                            </div>
                                            <%--选择医生--%>
                                            <div class="form-group">
                                                <span class="col-sm-2 control-label">医生:</span>
                                                <div class="col-sm-10">
                                                    <ul id="doctors" class="ul_list">
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                    </section>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">编号</label>
                                        <%--for="input_userid"--%>
                                        <div class="col-sm-10">
                                            <c:if test="${action=='add'}">
                                                <span>自动生成</span>
                                            </c:if>
                                            <c:if test="${action=='edit'}">
                                                <span>${registerBo.id}</span>
                                            </c:if>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="patient_name" class="col-sm-2 control-label">病人</label>
                                        <div class="col-sm-10">
                                            <input type="hidden" class="form-control" id="patient_id"
                                                   name="patientId"
                                                   value="${registerBo.patientId}"
                                                   placeholder="">
                                            <span id="patient_name">${registerBo.patient.name}</span>
                                            <input id="select_patient" type="button" class="btn btn-default"
                                                   value="选择病人"/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="department_id"
                                               class="col-sm-2 control-label">科室</label>
                                        <div class="col-sm-10">
                                            <input type="hidden" class="form-control" id="department_id"
                                                   name="departmentId"
                                                   value="${registerBo.departmentId}"
                                                   placeholder="">
                                            <span id="department_name">${registerBo.department.name}</span>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="doctor_id"
                                               class="col-sm-2 control-label">医生</label>
                                        <div class="col-sm-10">
                                            <input type="hidden" class="form-control" id="doctor_id"
                                                   name="doctorId"
                                                   value="${registerBo.doctorId}"
                                                   placeholder="">
                                            <span id="doctor_name">${registerBo.doctor.realName}</span>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="input_time"
                                               class="col-sm-2 control-label">录入时间</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="input_time"
                                                   name="time"
                                                   value="<fmt:formatDate value="${registerBo.time}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                                                   onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss',choose: function(){}})"
                                            >
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="select_status"
                                               class="col-sm-2 control-label">状态</label>
                                        <div class="col-sm-10">
                                            <select name="status" id="select_status" class="form-control" disabled>
                                                <option value="0">未完成</option>
                                                <option value="1" <c:if test="${registerBo.status==1}">selected</c:if>>
                                                    完成
                                                </option>
                                            </select>
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
        success_return_url = "${pageContext.request.contextPath}/register/list";
        </c:when>
        <c:otherwise>
        success_return_url = "${pageContext.request.contextPath}/register/edit?id=${registerBo.id}";
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

<%--动态显示医生--%>
<script type="text/javascript">
    $(document).ready(function () {
        var $ul_departs = $('#departs');
        var $ul_doctors = $('#doctors');

        function updateDoctorValue(id, name) {
            //更新表单的数据
            $("#doctor_id").val(id);
            $("#doctor_name").text(name);
        }

        /**
         * 根据科室id获得科室的所有医生
         * @param department_id
         */
        function getDoctors(department_id) {
            $.ajax({
                url: "${pageContext.request.contextPath}/doctor/getdepartall",
                type: "POST",
                datatype: "json",
                data: {
                    id: department_id
                },
                success: function (data, textStatus) {
                    $ul_doctors.empty();

                    if (data.success) {
                        //获取成功
                        $.each(data.obj, function (n, value) {
                            var $li = $("<li></li>").appendTo($ul_doctors);
                            var $a = $('<a></a>').appendTo($li).attr("data-id", value.id).text(value.realName);
                            //医生点击事件
                            $a.click(function () {
                                var $this = $(this);

                                //高亮当前
                                $ul_doctors.find('a').css("font-weight", "normal");
                                $this.css("font-weight", "bold");

                                updateDoctorValue(value.id, value.realName);
                                //
                            });
                        });

                        //切换科室的时候顺便切换医生
                        var $first_a = $ul_doctors.find("a").eq(0).click();
//                        updateDoctorValue($first_a.attr("data-id"), $first_a.text());

                    } else {
                        //获取失败
                        var index = layer.alert(data.msg, {icon: 3, title: '提示'}, function (index) {
                            layer.close(index)
                        });
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("ajax error");
                    console.log(XMLHttpRequest);
                    console.log(textStatus);
                    console.log(errorThrown);
                }
            });
        }

        //页面刚载入是获取默认医生列表
        //getDoctors($ul_departs.find('a').eq(0).attr("data-id"));

        //科室点击事件
        $ul_departs.find('a').click(function () {
            var $this = $(this);
            var depart_id = $this.attr("data-id");

            //高亮当前
            $ul_departs.find('a').css("font-weight", "normal")
            $this.css("font-weight", "bold");

            //更新表单的值
            $('#department_id').val(depart_id);
            $("#department_name").text($this.text());

            //查询科室的医生
            getDoctors(depart_id);
        });
    });
</script>

<%--选择病人--%>
<script type="text/javascript">
    var window_patient;

    function setPatient(id, name) {
        $('#patient_id').val(id);
        $('#patient_name').text(name);
        layer.close(window_patient);
//        window_patient>0 && window_patient.close();
    }

    $(document).ready(function () {
        $('#select_patient').click(function () {
            window_patient = layer.open({
                type: 2,
                title: '选择一个病人',
                shadeClose: true,
                shade: false,
//                maxmin: true, //开启最大化最小化按钮
                area: ['80%', '60%'],
                content: '${pageContext.request.contextPath}/patient/select'
            });
        });
    });
</script>


</body>

</html>