<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <a href="adddoc.jsp" target="mainFrame" title="新增" style="text-decoration: none;border: 1px solid gray;">新增</a>
    <input type="button" value="删除" onclick="tpformdelete();"/>
    医生姓名：<input type="text" id="dosname"/>
    <input type="button" value="查询" onclick="tpformsubmit();"/>

    <table id="table1">
        <tr>
            <td><input type="checkbox" id="allChecks" onclick="ckAll()">全选/全不选</td>
            <td>医生编号</td>
            <td>姓名</td>
            <td>性别</td>
            <td>所属科室</td>
            <td>职务级别</td>
            <td>联系方式</td>
            <td>看诊人数</td>
            <td>操作</td>
        </tr>
        <c:forEach items="${sessionScope.listC}" var="goods">
            <tr>
                <td><input type="checkbox" name="check" value="${goods.dno}"></td>
                <td class="dno">${goods.dno}</td>
                <td>${goods.dname}</td>
                <td>
                        ${goods.dsexdes}
                </td>
                <td class="department" style="display: none;">${goods.department}</td>
                <td class="position" style="display: none;">${goods.position}</td>
                <td>${goods.departdes}</td>
                <td>${goods.positiondes}</td>
                <td class="phone">${goods.phone}</td>
                <td>${goods.count}</td>
                <td class="updatedoc"><input type="button" id="${goods.dno}" value="修改"></td>
            </tr>
        </c:forEach>
    </table>

</form>
<div style="display: none;" id="updatbiao">
    <input type="hidden" id="updno">
    <table id="table2">
        <tr>
            <td>所属科室：<select id="first" onChange="change()"></select></td>
        </tr>
        <tr>
            <td>级别：<select id="second">

            </select></td>
        </tr>
        <tr>
            <td>手机号：<input type="text" id="upphone" onblur="yaphone();"></td>
        </tr>
        <tr>
            <td><input type="button" onclick="updatedocs();" value="更新"></td>
        </tr>
    </table>
</div>
</body>
<script type="text/javascript">
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
            data: {"deleteId": str, "signal": "docdelete"},
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

    $(".updatedoc").click(function () {
        $("#updatbiao").css("display", "block");
        var x = document.getElementById("first");
        x.options.length = 0;
        x.options.add(new Option("五官科", "0"));
        x.options.add(new Option("外科", "1"));
        x.options.add(new Option("内科", "2"));
        var data1 = $(this).parent().find(".department").text();
        $("#first option[value='" + data1 + "']").attr("selected", "selected");
        change();
        var data2 = $(this).parent().find(".position").text();
        $("#second option[value='" + data2 + "']").attr("selected", "selected");
        var data3 = $(this).parent().find(".phone").text();
        $("#upphone").val(data3);
        var data4 = $(this).parent().find(".dno").text();
        $("#updno").val(data4);

    })

    function yaphone() {
        var isPhone = /^([0-9]{3,4}-)?[0-9]{7,8}$/;
        var isMob = /^((\+?86)|(\(\+86\)))?(13[012356789][0-9]{8}|15[012356789][0-9]{8}|18[02356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$/;
        var value = document.getElementById("upphone").value.trim();
        if (isMob.test(value) || isPhone.test(value)) {
            return true;
        } else {
            alert("格式不符");
            $("#upphone").val("");
        }
    }

    function updatedocs() {
        if (!confirm("确定更新？")) {
            return;
        }
        var options = $("#first option:selected");
        var department = options.val();
        var departdes = options.text();
        var optionss = $("#second option:selected");
        var position = optionss.val();
        var positiondes = optionss.text();
        var phone = $("#upphone").val();
        if (phone == "") {
            alert("手机号不能为空");
            return false;
        }
        var dno = $("#updno").val();
        $.ajax({

            cache: true,
            type: "POST",
            url: "DoctorUpdateServlet",
            data: {
                "dno": dno,
                "department": department,
                "departdes": departdes,
                "position": position,
                "positiondes": positiondes,
                "phone": phone
            },
            async: false,
            error: function () {
                alert("error");
            },
            success: function (data) {
                if (data == "true") {
                    alert("更新成功");
                    tpformsubmit();
                } else {
                    alert("更新失败");
                }

            }


        });
    }

    function change() {
        var x = document.getElementById("first");
        var y = document.getElementById("second");
        y.options.length = 0; // 清除second下拉框的所有内容
        if (x.value == 0) {
            y.options.add(new Option("主治医师", "0"));
            y.options.add(new Option("手术师", "1"));
        }

        if (x.value == 1) {
            y.options.add(new Option("主治医师", "0"));
            y.options.add(new Option("副主治医师", "2"));
        }
        if (x.value == 2) {
            y.options.add(new Option("主任", "3"));
            y.options.add(new Option("专家", "4"));
            y.options.add(new Option("助理", "5"));
        }

    }

    function tpformsubmit() {
        var dosname = $('#dosname').val();
        url = 'InfoSelectServlet?userId=' + dosname + '&signal=select';
        window.location.href = url;
        $('#dosname').val("");

    }

    function select() {
        var customerName = "111";
        $.ajax({

            cache: true,
            type: "POST",
            url: "InfoSelectServlet",
            data: {"userId": customerName, "signal": "select"},
            async: false,
            error: function () {
                alert("error");
            },
            success: function (data) {
                alert(data)
                var br = JSON.parse(data);
                var x;
                for (x in br) {
                    alert(br[x].dno)
                }

            }
        });
    }

</script>
</html>