<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/global.jsp" %>
<html>
<head>
    <title></title>
    <%@include file="/commons/basejs.jsp" %>
</head>

<body>
<section class="main-content">
    <header class="header navbar bg-default">
        <p class="navbar-text">挂号项目</p>
<%--        <div class="btn-group pull-right mg-r-sm">
            <button type="button" class="btn btn-sm btn-success btn-rounded navbar-btn">
                <i class="fa fa-clock-o mg-r-xs"></i>Schedule
            </button>
            <button type="button" class="btn btn-sm btn-primary btn-rounded navbar-btn">
                Save Draft
            </button>
            <button type="button" class="btn btn-sm btn-info btn-rounded navbar-btn">
                Publish
            </button>
        </div>--%>
    </header>

    <div class="content-wrap no-padding">
<%--        <div class="col-md-3">
            <div class="pd-md text-center">
                <img src="img/avatar.jpg" class="avatar avatar-lg img-circle" alt="">
            </div>
            <p class="text-center">超级
                <br>
                <small>
                    <i>第x组</i>
                </small>
            </p>
            <hr>
            <p class="text-center">
                <small>成员</small>
                <br>
                <small class="characters">1</small>
                <br>
                <small class="words">2</small>
                <br>
                <small class="paragraphs">3</small>
                <br>
                <small class="paragraphs">4</small>
            </p>
        </div>--%>
        <div class="col-md-12 bg-white">
            <div class="editable pd-b-lg" contenteditable="true" data-placeholder="Type your text" data-medium-element="true">
                <h3>开发环境java1.7,tomcat7</h3>
                <p></p>
                <p>开发技术:</p>
                <p>1、后端</p>
                <p></p>
                <p>核心框架：Spring Framework</p>
                <p>安全框架：Apache Shiro</p>
                <p>视图框架：Spring MVC</p>
                <p>持久层框架：mybatis</p>
                <p>数据库连接池：Alibaba Druid</p>
                <p>缓存框架：Ehcache</p>
                <p>日志管理：SLF4J、LOGBACKUP</p>
                <p>工具类：Apache Commons、FastJson、EASYPOI、BladeToolBox</p>
                <p></p>
                <p>2、前端</p>
                <p></p>
                <p>JS框架：jQuery</p>
                <p>CSS框架：Twitter Bootstrap</p>
                <a  class="btn btn-success" onclick="window.location.href='${pageContext.request.contextPath}/login'">开始使用</a>
            </div>
        </div>
    </div>

    <div class="site-overlay"></div></section>
</body>
</html>
