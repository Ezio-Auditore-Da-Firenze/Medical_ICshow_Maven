<%@ page import="static ic_ethereum.IC_Administration.ICA" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312;"/>
    <script type='text/javascript' src='./js/jquery-1.11.0.min.js'></script>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
    <title>菜单选择</title>
    <style type="text/css">
        body{
            background: url("/image/bg.jpg") no-repeat;
        }
        #container{
            position: absolute;
            width:1860px;
            height:900px;
            margin-top: 20px;
            /*display:flex;         !*flex布局*!*/
        }
        #left {
            border: 1px solid black;
            margin-left: 150px;
            width: 200px;
            height: 100%;
            float: left;
            /*flex:none;*/
        }
        #main {
            width: 68%;
            height: 100%;
            border: 1px solid black;
            margin-left: 350px;
            /*flex:1;*/
        }
    </style>
    <%
        Boolean s=ICA.isNodeStatus();
    %>
    <script type="text/javaScript">

            function ajaxNodeOpen(){
                $.ajax({
                    type: "POST",                            //传数据的方式
                    url: "nodeopen",                             //servlet地址
                    //data: $('#form').serialize(),            //传的数据  form表单 里面的数据
                    success: function(result){               //传数据成功之后的操作   result是servlet传过来的数据  这个函数对result进行处理，让它显示在 输入框中
                        //$("#results").val(result);           //找到输入框 并且将result的值 传进去
                    }
                });
            }
            function ajaxNodeClose(){
                $.ajax({
                    type: "POST",                            //传数据的方式
                    url: "nodeclose",                             //servlet地址
                    //data: $('#form').serialize(),            //传的数据  form表单 里面的数据
                    success: function(result){               //传数据成功之后的操作   result是servlet传过来的数据  这个函数对result进行处理，让它显示在 输入框中
                        //$("#results").val(result);           //找到输入框 并且将result的值 传进去
                    }
                });
            }
        function check() {
            var box = document.getElementById('checke');
            if(box.checked){
                ajaxNodeOpen();
                document.getElementById("choose1").style.display="inline";
                document.getElementById("choose2").style.display="inline";
                document.getElementById("label_check").innerText="节点已打开";
                document.getElementById("label_check").style.color="#2eff00";
            }else{
                ajaxNodeClose();
                document.getElementById("choose1").style.display="none";
                document.getElementById("choose2").style.display="none";
                document.getElementById("label_check").innerText="节点未打开";
                document.getElementById("label_check").style.color="#ff0200";
                document.getElementById("iframe").contentWindow.document.body.innerText = "";
            }
        }

    </script>
</head>

<body>
<script type="application/javascript">
    $(document).ready(function(){
        if(<%=s%>){
            //alert("已有节点！");
            $('#checke').attr('checked', true);
            document.getElementById("choose1").style.display="inline";
            document.getElementById("choose2").style.display="inline";
            document.getElementById("label_check").innerText="节点已打开";
            document.getElementById("label_check").style.color="#2eff00";
        }
    });
</script>
    <div id="container">
        <div id="left">
            <ul>
                <li><input onclick="check()" type="checkbox" id="checke"><label id="label_check" for="checke" title="打开节点" style="color: #ff0200">节点未打开</label></li>
                <li class="page_item page-item-5 "><a href="doc.jsp" target="mainFrame" title="医生信息管理"  id="choose1" style="display: none">医生信息管理</a></li>
                <li class="page_item page-item-5"><a href="sick.jsp" target="mainFrame" title="就诊管理系统"  id="choose2" style="display: none">就诊管理系统</a></li>
            </ul>
        </div>
        <div id="main">
            <iframe name="mainFrame"  frameborder="0" style="width:99%;height:100%;" id="iframe"></iframe>
        </div>
    </div>
    <div id="particles" style="z-index: -2; height: 950px;"></div>
    <script type='text/javascript' src='./js/jquery.particleground.js'></script>
    <script type='text/javascript' src='./js/demo.js'></script>
</body>
</html>
