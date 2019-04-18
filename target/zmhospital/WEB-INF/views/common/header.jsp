<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--登出js--%>
<script type="text/javascript">
    $(document).ready(function () {
        $('#logout').click(function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/logout",
                type: "POST",
                datatype: "json",
                data: {},
                success: function (data, textStatus) {

                    var index = layer.alert(data.msg, {icon: 3, title: '提示'}, function (index) {
                        //do something
                        if (data.success) {
                            window.location.href = data.obj;
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

<%--头部--%>
<header class="header header-fixed navbar">

    <%--后台标题--%>
    <div class="brand">
        <a href="javascript:;" class="fa fa-bars off-left visible-xs" data-toggle="off-canvas" data-move="ltr"></a>
        <a href="" class="navbar-brand text-white">
            <i class="fa fa-stop mg-r-sm"></i>
            <span class="heading-font">Cameo<b>后台</b></span>
        </a>
    </div>

    <%--搜索--%>
    <form class="navbar-form navbar-left hidden-xs" role="search">
        <div class="form-group">
            <button class="btn no-border no-margin bg-none no-pd-l" type="submit">
                <i class="fa fa-search"></i>
            </button>
            <input type="text" class="form-control no-border no-padding search" placeholder="Search Workspace">
        </div>
    </form>

    <ul class="nav navbar-nav navbar-right off-right">

        <%--登录名--%>
        <li class="hidden-xs">
            <a href="javascript:void(0);">
                <shiro:principal property="loginname"/>
            </a>
        </li>
        <%--消息--%>
        <%--
    <li class="notifications dropdown hidden-xs">
        <a href="javascript:;" data-toggle="dropdown">
            <i class="fa fa-bell"></i>
            <div class="badge badge-top bg-danger animated flash">3</div>
        </a>
        <div class="dropdown-menu dropdown-menu-right animated slideInRight">
            <div class="panel bg-white no-border no-margin">
                <div class="panel-heading no-radius">
                    <small>
                        <b>Notifications</b>
                    </small>
                    <small class="pull-right">
                        <a href="javascript:;" class="mg-r-xs">mark as read</a>&#8226;
                        <a href="javascript:;" class="fa fa-cog mg-l-xs"></a>
                    </small>
                </div>
                <ul class="list-group">
                    <li class="list-group-item">
                        <a href="javascript:;">
                                    <span class="pull-left mg-t-xs mg-r-md">
<img src="" class="avatar avatar-sm img-circle" alt="">
</span>
                            <div class="m-body show pd-t-xs">
                                <span>Dean Winchester</span>
                                <span>Posted on to your wall</span>
                                <small>2 mins ago</small>
                            </div>
                        </a>
                    </li>
                    <li class="list-group-item">
                        <a href="javascript:;">
                                    <span class="pull-left mg-t-xs mg-r-md">
<span class="fa-stack fa-lg">
<i class="fa fa-circle fa-stack-2x text-warning"></i>
<i class="fa fa-download fa-stack-1x fa-inverse"></i>
</span>
                                    </span>
                            <div class="m-body show pd-t-xs">
                                <span>145 MB download in progress.</span>
                                <div class="progress progress-xs mg-t-xs mg-b-xs">
                                    <div class="progress-bar progress-bar-primary" role="progressbar"
                                         aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
                                         style="width: 40%">
                                    </div>
                                </div>
                                <small>Started 23 mins ago</small>
                            </div>
                        </a>
                    </li>
                    <li class="list-group-item">
                        <a href="javascript:;">
                                    <span class="pull-left mg-t-xs mg-r-md">
<img src="" class="avatar avatar-sm img-circle" alt="">
</span>
                            <div class="m-body show pd-t-xs">
                                <span>Application</span>
                                <span>Where is my workspace widget</span>
                                <small>5 days ago</small>
                            </div>
                        </a>
                    </li>
                </ul>
                <div class="panel-footer no-border">
                    <a href="javascript:;">See all notifications</a>
                </div>
            </div>
        </div>
    </li>
--%>

        <li class="quickmenu">
            <a href="javascript:void(0);" data-toggle="dropdown">
                <span>操作</span>
                <%--<img src="" class="avatar pull-left img-circle" alt="user" title="user">--%>
                <i class="caret mg-l-xs hidden-xs no-margin"></i>
            </a>
            <ul class="dropdown-menu dropdown-menu-right mg-r-xs">
                <li>
                    <a href="javascript:void(0);">
                        <div class="pd-t-sm">
                            <shiro:principal property="email"/>
                            <br>
                            <%--<small class="text-muted">4.2 MB of 51.25 GB used</small>--%>
                        </div>
                        <%--<div class="progress progress-xs no-radius no-margin mg-b-sm">--%>
                        <%--<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40"--%>
                        <%--aria-valuemin="0" aria-valuemax="100" style="width: 40%">--%>
                        <%--</div>--%>
                        <%--</div>--%>
                    </a>
                </li>
                <li>
                    <a href="">设置</a>
                </li>

                <%--通知--%>
                <%--<li>--%>
                <%--<a href="javascript:;">Notifications--%>
                <%--<div class="badge bg-danger pull-right">3</div>--%>
                <%--</a>--%>
                <%--</li>--%>

                <li>
                    <a href="javascript:void(0);">帮助 ?</a>
                </li>
                <li class="divider"></li>
                <li>
                    <a id="logout" href="javascript:void(0);">登出</a>
                    <%--${pageContext.request.contextPath}/logout--%>
                </li>

            </ul>
        </li>
    </ul>
</header>



