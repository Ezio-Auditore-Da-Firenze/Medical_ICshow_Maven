<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312;"/>
    <script type='text/javascript' src='./js/jquery-1.11.0.min.js'></script>
    <title>菜单选择</title>
    <style type="">
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
</head>

<body >
    <script>
        function setHref() {
            document.getElementById('zzz').href="nodeopen";
        }
        function clearHref() {
            document.getElementById('yyy').href="nodeclose";
        }
    </script>
    <div id="container">
        <div id="left">
            <ul>
                <li class="page_item page-item-5"><a href="nodeopen" target="mainFrame" title="打开节点" >打开节点</a></li>
                <li class="page_item page-item-5"><a href="nodeclose" target="mainFrame" title="关闭节点" >关闭节点</a></li>
                <li class="page_item page-item-2 "><a href="doc.jsp" target="mainFrame" title="医生信息管理">医生信息管理</a></li>
                <li class="page_item page-item-5"><a href="sick.jsp" target="mainFrame" title="就诊管理系统">就诊管理系统</a></li>
            </ul>
        </div>
        <div id="main">
            <iframe name="mainFrame"  frameborder="0" style="width:99%;height:100%;"></iframe>
        </div>
    </div>
    <div id="particles" style="z-index: -2; height: 950px;"></div>
    <script type='text/javascript' src='./js/jquery.particleground.js'></script>
    <script type='text/javascript' src='./js/demo.js'></script>
</body>
</html>
