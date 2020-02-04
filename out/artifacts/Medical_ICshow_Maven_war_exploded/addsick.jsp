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
        <td>病历编号：</td>
        <td><input type="text" readonly="readonly" id="sickid"/></td>
    </tr>
    <tr>
        <td>病历姓名：</td>
        <td><input type="text" id="pname"/></td>
    </tr>
    <tr>
        <td>身份证号：</td>
        <td><input type="text" onblur="yancard();" id="sickcard"></td>
    </tr>
    <tr>
        <td>性别：</td>
        <td id="sex"><input type="radio" name="identity" value="1" checked="checked" id="boy"/>男
            <input type="radio" name="identity" value="0" id="girl"/>女
        </td>
    </tr>
    <tr>
        <td>年龄：</td>
        <td><input type="text" id="age" readonly="readonly"/></td>
    </tr>
    <tr>
        <td>主治医师：</td>
        <td>
            <select id="docname" onchange="docnamecha();">
            </select>
        </td>
    </tr>
    <tr style="display: none;">
        <td><input type="hidden" id="department"/></td>
    </tr>
    <tr>
        <td>就诊科室：</td>
        <td><input type="text" readonly="readonly" id="departdes"/></td>
    </tr>
    <tr>
        <td>就诊费用：</td>
        <td><input type="text" id="totalcost" onblur="yanman();"/></td>
    </tr>
    <tr>
        <td>就诊时间：</td>
        <td><input type="date" id="treatdate"/></td>
    </tr>
    <tr>
        <td>入院时间：</td>
        <td><input type="date" id="arrivedate"/></td>
    </tr>
    <tr>
        <td>备注：</td>
        <td><input type="text" id="notes"/></td>
    </tr>
</table>
<div><input type="button" onclick="submits();" value="新增"></div>
</body>
<script type="text/javascript">
    function yanman() {
        var reg = /\d+(\.\d+)?/;
        var totalcost = $("#totalcost").val();
        if (reg.test(totalcost) == false) {
            alert("就诊费用格式不正确！");
            return false;
            $("#totalcost").val("");
        }
    }

    function submits() {
        var pno = $("#sickid").val();
        var pname = $("#pname").val();
        if (pname == "" || pname == null) {
            alert("病历姓名不能为空");
            return false;
        }
        yancard();
        var identity = $("#sickcard").val();
        var age = $("#age").val();
        var psex = $('#sex input[name="identity"]:checked').val();
        var psexdes = "";
        if (psex == 1) {
            psexdes = "男";
        } else {
            psexdes = "女";
        }
        var options = $("#docname option:selected");
        var dname = options.text();
        if (dname == "没有医生坐诊") {
            alert("没有医生坐诊");
            return false;
        }
        var iftonhpan = true;
        $.ajax({

            cache: true,
            type: "POST",
            url: "InfoSelectServlet",
            data: {"userId": dname, "signal": "chadocbing"},
            async: false,
            error: function () {
                alert("error");
            },
            success: function (data) {

                var br = JSON.parse(data);
                var x;
                for (x in br) {
                    if (br[x].identity == identity) {
                        iftonhpan = false;
                    }
                }
            }
        });
        if (iftonhpan == false) {
            alert("此病人已就诊此医生！");
            return false;
        }
        /*	 var cou=0;
             $.ajax({

                    cache: true,
                    type: "POST",
                    url: "Chadoccount",
                    data: {"dname":dname},
                    async: false,
                    error: function() {
                        alert("error");
                    },
                    success: function(data) {
                         cou=data;

                            }


                });
                if(cou=="2"){
                  alert("同一天,同一医师最多接诊2名病人");
                  return false;
                    }
        */
        var department = $("#department").val();
        var departdes = $("#departdes").val();
        var totalcost = $("#totalcost").val();
        if (totalcost == "" || totalcost == null) {
            alert("就诊费用不能为空");
            return false;
        }
        var treatdate = $("#treatdate").val();
        var arrivedate = $("#arrivedate").val();
        var notes = $("#notes").val();
        $.ajax({

            cache: true,
            type: "POST",
            url: "PatientInsertServlet",
            data: {
                "pno": pno,
                "pname": pname,
                "identity": identity,
                "age": age,
                "psex": psex,
                "psexdes": psexdes,
                "dname": dname,
                "department": department,
                "departdes": departdes,
                "totalcost": totalcost,
                "treatdate": treatdate,
                "arrivedate": arrivedate,
                "notes": notes
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

    function init() {
        $("#sickid").val(uuid().split("-")[0]);
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
                    var x = document.getElementById("docname");
                    x.options.length = 0;
                    x.options.add(new Option("没有医生坐诊", "9999"));
                } else {
                    var r = document.getElementById("docname");
                    r.options.length = 0;
                    var br = JSON.parse(data);
                    var x;
                    for (x in br) {
                        var departdes = br[x].department + "&" + br[x].departdes;
                        var dname = br[x].dname;
                        r.options.add(new Option(dname, departdes));
                    }
                }
                var options = $("#docname option:selected");
                var department = options.val();
                $("#department").val(department.split("&")[0]);
                $("#departdes").val(department.split("&")[1]);

            }
        });
        var myDate = new Date();
        var month = (myDate.getMonth() + 1).toString();
        if (month.length == 1) {
            month = "0" + month;
        }
        var day = myDate.getDate().toString();
        var year = myDate.getFullYear().toString();
        var yad = year + "-" + month + "-" + day;
        $('input[type="date"]').val(yad);
    }

    function docnamecha() {
        var options = $("#docname option:selected");
        var department = options.val();
        $("#department").val(department.split("&")[0]);
        $("#departdes").val(department.split("&")[1]);
    }

    function yancard() {
        var sickcard = $("#sickcard").val();
        // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X
        var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
        if (reg.test(sickcard) === false) {
            alert("身份证输入不合法");
            return false;
        } else {
            var agecard = discriCard(sickcard);
            $("#age").val(agecard.split("&")[1]);
            var sex = agecard.split("&")[0];
            if (sex == 1) {
                $("#girl").prop("checked", false);
                $("#boy").prop("checked", "checked");
            } else if (sex == 0) {
                $("#boy").prop("checked", false);
                $("#girl").prop("checked", "checked");

            }
        }
    }

    function discriCard(UUserCard) {
//获取性别 
        var sex = 1;
        if (parseInt(UUserCard.substr(16, 1)) % 2 == 1) {

            sex = 1;
//是男则执行代码 ... 
        } else {

            sex = 0;
//是女则执行代码 ... 
        }
//获取年龄 
        var myDate = new Date();
        var month = myDate.getMonth() + 1;
        var day = myDate.getDate();
        var age = myDate.getFullYear() - UUserCard.substring(6, 10) - 1;
        if (UUserCard.substring(10, 12) < month || UUserCard.substring(10, 12) == month && UUserCard.substring(12, 14) <= day) {
            age++;
        }
        return sex + "&" + age;
//年龄 age 
    }

    function uuid() {
        var s = [];
        var hexDigits = "0123456789abcdef";
        for (var i = 0; i < 36; i++) {
            s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
        }
        s[14] = "4";  // bits 12-15 of the time_hi_and_version field to 0010
        s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);  // bits 6-7 of the clock_seq_hi_and_reserved to 01
        s[8] = s[13] = s[18] = s[23] = "-";

        var uuid = s.join("");
        return uuid;
    }
</script>
</html>