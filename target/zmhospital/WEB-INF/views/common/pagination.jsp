<%--<%@ page isELIgnored="false" %>--%>
<%@ page language="java" pageEncoding="utf-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<!--接收参数total,总记录数-->
<c:set var="total" value="${param.total}"/>
<!--接收参数total,总页数-->
<c:set var="pagesCount" value="${param.pagesCount}"/>
<!--接收参数pageSize,每页最多显示的记录数-->
<c:set var="pageSize" value="${param.pageSize}"/>
<!--接收参数url,当前页-->
<c:set var="pageIndex" value="${param.pageIndex}"/>
<!--接收参数url,要分页的页面URL-->
<c:set var="url" value="${param.pageUrl}"/>

<style >
    .pagination > li.disabled > a, .pagination > li.disabled > a:hover, .pager > li.disabled > a, .pager > li.disabled > a:hover {
        background-color: #f9f9f9;
        border-color: #d9d9d9;
    }

    .pagination > li:first-child > a, .pagination > li:first-child > span {
        border-bottom-left-radius: 4px;
        border-top-left-radius: 4px;
        margin-left: 0;
    }

    .pagination > .disabled > span, .pagination > .disabled > a, .pagination > .disabled > a:hover, .pagination > .disabled > a:focus {
        background-color: #fff;
        border-color: #ddd;
        color: #999;
        cursor: not-allowed;
    }

    .pagination > li.active > a, .pagination > li.active > a:hover {
        background-color: #6faed9;
        border-color: #6faed9;
        color: #fff;
        text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25);
        cursor: default;
    }
</style>

<script type="text/javascript">
    var url = "${url}";

    /**
     * 改变url的请求参数
     */
    function changeURLPar(url, ref, value) {
        var str = "";
        if (url.indexOf('?') != -1){
            str = url.substr(url.indexOf('?') + 1);
        }
        else{
            return url + "?" + ref + "=" + value;
        }
        var returnurl = "";
        var setparam = "";
        var arr;
        var modify = "0";
        if (str.indexOf('&') != -1) {
            arr = str.split('&');
            for (var i in arr) {
                if (arr[i].split('=')[0] == ref) {
                    setparam = value;
                    modify = "1";
                }
                else {
                    setparam = arr[i].split('=')[1];
                }
                returnurl = returnurl + arr[i].split('=')[0] + "=" + setparam + "&";
            }
            returnurl = returnurl.substr(0, returnurl.length - 1);
            if (modify == "0")
                if (returnurl == str)
                    returnurl = returnurl + "&" + ref + "=" + value;
        }
        else {
            if (str.indexOf('=') != -1) {
                arr = str.split('=');
                if (arr[0] == ref) {
                    setparam = value;
                    modify = "1";
                }
                else {
                    setparam = arr[1];
                }
                returnurl = arr[0] + "=" + setparam;
                if (modify == "0")
                    if (returnurl == str)
                        returnurl = returnurl + "&" + ref + "=" + value;
            }
            else
                returnurl = ref + "=" + value;
        }
        return url.substr(0, url.indexOf('?')) + "?" + returnurl;
    }

    /**
     * 提交跳转的时候做一下验证
     */
    function onSubmit() {
        var re = /^[1-9][0-9]*$/;
        var totalPage = "${pagesCount}";
        var $input = $("#pageNum1");
        //控制页数为整数
        if (!re.test($input.val())) {
            $input.val(1);
        }
        //控制不超过总页数
        if (Number($input.val()) > Number(totalPage)) {
            $input.val(totalPage);
        }
        //input传递真正的页数
        //$("#pageIndex_p").val($input.val());
        //$("#pagination").submit();
        window.location.href = changeURLPar(url, "page", $input.val());
        return false;
    }

    /**
     * 跳转到某页
     * @param pageIndex
     */
    function page(pageIndex) {
        //
        window.location.href = changeURLPar(url, "page", pageIndex);

        //$("#pageIndex_p").val(pageIndex);
        //$("#pagination").submit();
    }
</script>

<c:if test="${total>0}">
    <form action="${url}" method="get" id="pagination">
            <%--每页最多显示的记录数--%>
        <input type="hidden" name="pageSize" value="${pageSize}"/>
            <%--当前页数--%>
        <input type="hidden" name="page" id="pageIndex_p"/>
        <div class="pagination">

            <c:choose>
                <c:when test="${pageIndex==1}">
                    <li class="disabled"><span class="prev">首页</span></li>
                    <li class="disabled"><span class="prev">上一页</span></li>
                </c:when>
                <c:otherwise>
                    <li><a href="#" onClick="page('1')" class="pagenum">首页</a></li>
                    <li><a href="#" onClick="page('${pageIndex-1}')" class="pagenum">上一页</a></li>
                </c:otherwise>
            </c:choose>

            <c:if test="${pageIndex-3>0}">
                <li><a href="#" onClick="page('${pageIndex-3}')">${pageIndex-3}</a></li>
            </c:if>
            <c:if test="${pageIndex-2>0}">
                <li><a href="#" onClick="page('${pageIndex-2}')">${pageIndex-2}</a></li>
            </c:if>
            <c:if test="${pageIndex-1>0}">
                <li><a href="#" onClick="page('${pageIndex-1}')">${pageIndex-1}</a></li>
            </c:if>
            <li class="active"><a href="#">${pageIndex}</a></li>
            <c:if test="${pageIndex+1<=pagesCount}">
                <li><a href="#" onClick="page('${pageIndex+1}')">${pageIndex+1}</a></li>
            </c:if>
            <c:if test="${pageIndex+2<=pagesCount}">
                <li><a href="#" onClick="page('${pageIndex+2}')">${pageIndex+2}</a></li>
            </c:if>
            <c:if test="${pageIndex+3<=pagesCount}">
                <li><a href="#" onClick="page('${pageIndex+3}')">${pageIndex+3}</a></li>
            </c:if>

            <c:choose>
                <c:when test="${pageIndex==pagesCount}">
                    <li class="disabled"><span class="next">下一页</span></li>
                    <li class="disabled"><span class="next">末页</span></li>
                </c:when>
                <c:otherwise>
                    <li><a href="#" onClick="page('${pageIndex+1}')" class="pagenum">下一页</a></li>
                    <li><a href="#" onClick="page('${pagesCount}')" class="pagenum">末页</a></li>
                </c:otherwise>
            </c:choose>

            <li><span>${pageIndex}/${pagesCount}页&nbsp;共${total}条&nbsp;</span></li>
            <li>
                <label for="pageNum1">跳到第
                    <input type="text" value="${pageIndex}" size="3" name="pageIndex" id="pageNum1">页
                </label>
                <button type="button" class="btn btn-primary" onClick="onSubmit();">确定</button>
            </li>
        </div>
    </form>
</c:if>
<c:if test="${total==0}">
    <c:choose>
        <c:when test="${url=='subscriptionList.do'}">
            <h1>暂无结果!</h1>
        </c:when>
        <c:otherwise>
            <h1>暂无结果!</h1>
        </c:otherwise>
    </c:choose>
</c:if>
