<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Insert title here</title>
    <script type="text/javascript" src="./js/jquery-1.11.0.min.js"></script>
</head>
<body onload="init();">
<table>
    <tr>
        <td>医生编号：</td>
        <td><input type="text" readonly="readonly" id="docid"/></td>
    </tr>
    <tr>
        <td>医生姓名：</td>
        <td><input type="text" onblur="updadocn();" id="docname"/></td>
    </tr>
    <tr>
        <td>性别：</td>
        <td id="sex"><input type="radio" name="identity" value="1" checked="checked"/>男
            <input type="radio" name="identity" value="0"/>女
        </td>
    </tr>
    <tr>
        <td>所属科室：</td>
        <td><select id="first" onChange="change()">
            <option selected="selected" value="0">五官科</option>
            <option value="1">外科</option>
            <option value="2"> 内科</option>
        </select></td>
    </tr>
    <tr>
        <td>级别：</td>
        <td><select id="second">
            <option selected="selected">主治医师</option>
            <option>手术师</option>
        </select></td>
    </tr>
    <tr>
        <td>联系方式：</td>
        <td><input type="text" onblur="yaphone();" id="phone"/></td>
    </tr>
    <tr>
        <td>看诊人数：</td>
        <td><input type="text" readonly="readonly" id="kanz" value="0"/></td>
    </tr>
</table>
<div><input type="button" onclick="submits();" value="新增"></div>
</body>
<script type="text/javascript">
    function submits() {
        var phone = $("#phone").val();
        var dname = $("#docname").val();
        if (dname == "") {
            alert("医生姓名不能为空");
            return false;
        }
        if (phone == "") {
            alert("手机号不能为空");
            return false;
        }
        var dno = $("#docid").val();
        var dsex = $('#sex input[name="identity"]:checked').val();
        var dsexdes = "";
        if (dsex == 1) {
            dsexdes = "男";
        } else {
            dsexdes = "女";
        }
        var options = $("#first option:selected");
        var department = options.val();
        var departdes = options.text();
        var optionss = $("#second option:selected");
        var position = optionss.val();
        var positiondes = optionss.text();
        var count = $("#kanz").val();
        $.ajax({

            cache: true,
            type: "POST",
            url: "DoctorInsertServlet",
            data: {
                "dno": dno,
                "dname": dname,
                "dsex": dsex,
                "dsexdes": dsexdes,
                "department": department,
                "departdes": departdes,
                "position": position,
                "positiondes": positiondes,
                "phone": phone,
                "count": count
            },
            async: false,
            error: function () {
                alert("error");
            },
            success: function (data) {
                if (data == "true") {
                    alert("新增成功");
                    history.go(-1);
                } else {
                    alert("新增失败");
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
            y.options.add(new Option("专家", "4"));  // 默认选中省会城市
            y.options.add(new Option("助理", "5"));
        }
        var docname = $("#docname").val().indexOf("(");
        if (docname != -1) {
            var options = $("#first option:selected");
            var ke = options.text();
            docname = $("#docname").val().substr(0, docname);
            $("#docname").val(docname + "(" + ke + ")");
        }
    }

    function yaphone() {
        var isPhone = /^([0-9]{3,4}-)?[0-9]{7,8}$/;
        var isMob = /^((\+?86)|(\(\+86\)))?(13[012356789][0-9]{8}|15[012356789][0-9]{8}|18[02356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$/;
        var value = document.getElementById("phone").value.trim();
        if (isMob.test(value) || isPhone.test(value)) {
            return true;
        } else {
            alert("格式不符");
            $("#phone").val("");
        }
    }

    function updadocn() {
        var docname = $("#docname").val();
        $.ajax({

            cache: true,
            type: "POST",
            url: "InfoSelectServlet",
            data: {"userId": docname, "signal": "updadocn"},
            async: false,
            error: function () {
                alert("error");
            },
            success: function (data) {
                if (data != "[]") {
                    var options = $("#first option:selected");
                    var ke = options.text();
                    $("#docname").val(docname + "(" + ke + ")");
                }

            }


        });
    }

    function init() {
        $.ajax({
            cache: true,
            type: "POST",
            url: "InfoSelectServlet",
            data: {"userId": "", "signal": "init"},
            async: false,
            error: function () {
                alert("error");
            },
            success: function (data) {
                if (data == "[]") {
                    var date = new Date();
                    var year = date.getFullYear().toString();
                    var mou = (date.getMonth() + 1).toString();
                    if (mou.length == 1) {
                        mou = "0" + mou;
                    }
                    $("#docid").val("DC" + year + mou + "0000");
                } else {
                    var br = JSON.parse(data);
                    var x;
                    var dno;
                    for (x in br) {
                        var dnoup = br[x].dno;
                        dno = dnoup;
                    }
                    var date = new Date();
                    var year = date.getFullYear().toString();
                    var mou = (date.getMonth() + 1).toString();
                    if (mou.length == 1) {
                        mou = "0" + mou;
                    }
                    var dn = (parseInt(dno.substring(dno.length - 4, dno.length)) + 1).toString();
                    if (dn.length == 1) {
                        dn = "000" + dn;
                    } else if (dn.length == 2) {
                        dn = "00" + dn;
                    } else if (dn.length == 3) {
                        dn = "0" + dn;
                    }
                    $("#docid").val("DC" + year + mou + dn);
                }


            }
        });
    }

    function adddoc() {
        $.ajax({

            cache: true,
            type: "POST",
            url: "InfoSelectServlet",
            data: {"userId": customerName},
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