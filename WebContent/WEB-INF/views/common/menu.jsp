<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript">
    var contextPath = "${pageContext.request.contextPath}";


    function layera() {

    }

    $(document).ready(function () {
        $.ajax({
            url: "${pageContext.request.contextPath}/resource/getmenu",
            type: "POST",
            datatype: "json",
            //timeout: 3000,
            data: {},
            success: function (data, textStatus) {
                //console.log(data);
                if (data.success) {
                    //成功,显示菜单
                    var menus = data.obj;

                    //ul
                    var $ul_main = $("#ul_menu").empty();

                    //遍历创建菜单
                    $.each(menus, function (n, value) {
                        var $li = $('<li class="dropdown show-on-hover"></li>').appendTo($ul_main);

                        $('<a href="' + contextPath + value.url + '" data-toggle="dropdown"><i class="fa fa-ellipsis-h"></i><span>' + value.name + '</span></a>').appendTo($li);

                        var $ul_drop = $('<ul class="dropdown-menu"></ul>').appendTo($li);

                        if (value.subMenus != null) {
                            //遍历创建该项的子菜单
                            $.each(value.subMenus, function (index, submenu) {
                                var $li_sub = $('<li><a href="' + contextPath + submenu.url + '"><span>' + submenu.name + '</span></a></li>').appendTo($ul_drop);

                                //高亮当前菜单
                                if (window.location.href.indexOf(submenu.url) > -1) {
                                    $li_sub.addClass('active');
                                    $li.addClass('active');
                                }
                            });
                        }
                    });

                } else {
                    //layer弹出提示
                    var index = layer.alert(data.msg, {icon: 3, title: '提示'}, function (index) {
                        layer.close(index);
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
    });
</script>
<%--侧边栏菜单--%>
<aside class="sidebar canvas-left">

    <nav class="main-navigation">
        <ul id="ul_menu">
            <li>
                <a href="">
                    <i class="fa fa-coffee"></i>
                    <span>首页</span>
                </a>
            </li>
            <%--用户管理--%>
<%--            <li class="dropdown active show-on-hover">
                <a href="javascript:void(0);" data-toggle="dropdown">
                    <i class="fa fa-ellipsis-h"></i>
                    <span>用户管理</span>
                </a>
                <ul class="dropdown-menu">
                    <li>
                        <a href="${pageContext.request.contextPath}/user/list">
                            <span>列表</span>
                        </a>
                    </li>
                    <li class="active">
                        <a href="">
                            <span>新增</span>
                        </a>
                    </li>
                </ul>
            </li>--%>

            <%--角色管理--%>
<%--            <li class="dropdown show-on-hover">
                <a href="javascript:void(0);" data-toggle="dropdown">
                    <i class="fa fa-tasks"></i>
                    <span>角色管理</span>
                </a>
                <ul class="dropdown-menu">
                    <li>
                        <a href="${pageContext.request.contextPath}/role/list">
                            <span>列表</span>
                        </a>
                    </li>
                </ul>
            </li>--%>
            <%--权限管理--%>
<%--            <li class="dropdown show-on-hover">
                <a href="javascript:void(0);" data-toggle="dropdown">
                    <i class="fa fa-tasks"></i>
                    <span>权限管理</span>
                </a>
                <ul class="dropdown-menu">
                    <li>
                        <a href="${pageContext.request.contextPath}/operation/list">
                            <span>列表</span>
                        </a>
                    </li>
                </ul>
            </li>--%>
            <%--资源管理--%>
<%--            <li class="dropdown show-on-hover">
                <a href="javascript:void(0);" data-toggle="dropdown">
                    <i class="fa fa-tasks"></i>
                    <span>资源管理</span>
                </a>
                <ul class="dropdown-menu">
                    <li>
                        <a href="${pageContext.request.contextPath}/resource/list">
                            <span>列表</span>
                        </a>
                    </li>
                </ul>
            </li>--%>
            <%--资源类型管理--%>
<%--            <li class="dropdown show-on-hover">
                <a href="javascript:void(0);" data-toggle="dropdown">
                    <i class="fa fa-tasks"></i>
                    <span>资源类型管理</span>
                </a>
                <ul class="dropdown-menu">
                    <li>
                        <a href="${pageContext.request.contextPath}/resourcetype/list">
                            <span>列表</span>
                        </a>
                    </li>
                </ul>
            </li>--%>
        </ul>
    </nav>

    <%--脚部提示--%>
    <footer>
        <div class="about-app pd-md animated pulse">
            <a href="javascript:;">
                <img src="" alt="">
            </a>
            <span>
<b>Cameo</b>&#32;is a responsive admin template powered by bootstrap 3.
<a href="javascript:;">
<b>Find out more</b>
</a>
</span>
        </div>
        <div class="footer-toolbar pull-left">
            <a href="javascript:" class="pull-left help">
                <i class="fa fa-question-circle"></i>
            </a>
            <a href="javascript:" class="toggle-sidebar pull-right hidden-xs">
                <i class="fa fa-angle-left"></i>
            </a>
        </div>
    </footer>


</aside>
