<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%@ taglib uri="http://www.scse.com" prefix="c" %>--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script type="text/javascript" src="./js/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="./js/json.js"></script>
    <style type="text/css">
        #table1 {
            margin-top: 15px;
            width: 770px;
            border-collapse: collapse;
        }

        #table1 tr {
            border: 1px solid rosybrown;
        }

        #table1 tr td {
            height: 25px;
            text-align: center;
            font-size: 13px;
            border: 1px solid rosybrown;
        }
    </style>
</head>
<body>
<button onclick="select();" style="display: none;">查询</button>
<form action="" method="post">
    <a href="addsick.jsp" target="mainFrame" title="新增" style="text-decoration: none;border: 1px solid gray;">新增</a>
    <input type="button" value="删除" onclick="tpformdelete();"/>
    病历编号：<input type="text" id="dosname"/>
    <input type="button" value="查询" onclick="tpformsubmit();"/>

    <table id="table1">
        <tr>
            <td><input type="checkbox" id="allChecks" onclick="ckAll()">全选/全不选</td>
            <td>病历编号</td>
            <td>姓名</td>
            <td>身份证号</td>
            <td>性别</td>
            <td>年龄</td>
            <td>主治医生</td>
            <td>就诊科室</td>
            <td>就诊费用</td>
            <td>就诊时间</td>
            <td>住院时间</td>
            <td>备注</td>
        </tr>
        <c:forEach items="${sessionScope.listG}" var="goods">
            <tr>
                <td><input type="checkbox" name="check" value="${goods.pno}"></td>
                <td class="dno">${goods.pno}</td>
                <td>${goods.pname}</td>
                <td>${goods.identity}</td>
                <td>
                        ${goods.psexdes}
                </td>
                <td>${goods.age}</td>
                <td>${goods.dname}</td>
                <td class="department" style="display: none;">${goods.department}</td>
                <td>${goods.departdes}</td>
                <td>${goods.totalcost}</td>
                <td>${goods.treatdate}</td>
                <td>${goods.arrivedate}</td>
                <td>${goods.notes}</td>
            </tr>
        </c:forEach>
    </table>

</form>

</body>
<script type="text/javascript">
    function tpformsubmit() {
        var dosname = $('#dosname').val();
        url = 'InfoSelectServlet?userId=' + dosname + '&signal=selectpan';
        window.location.href = url;
        $('#dosname').val("");
    }

    function ckAll() {
        var flag = document.getElementById("allChecks").checked;
        var cks = document.getElementsByName("check");
        for (var i = 0; i < cks.length; i++) {
            cks[i].checked = flag;
        }
    }

    function tpformdelete() {
        if (!confirm("确定要删除这些信息吗？")) {
            return;
        }
        var cks = document.getElementsByName("check");
        var str = "";
        for (var i = 0; i < cks.length; i++) {
            if (cks[i].checked) {
                str += cks[i].value + "&";
            }
        }
        //去掉字符串末尾的‘&'
        str = str.substring(0, str.length - 1);
        $.ajax({

            cache: true,
            type: "POST",
            url: "DeleteServlet",
            data: {"deleteId": str, "signal": "pandelete"},
            async: false,
            error: function () {
                alert("error");
            },
            success: function (data) {
                if (data == "true") {
                    alert("删除成功");
                } else {
                    alert("删除失败");
                }
                tpformsubmit();
            }


        });
    }
</script>
</html>