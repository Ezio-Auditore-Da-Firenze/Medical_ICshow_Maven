<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
    <title>菜单选择</title>
    <style type="">
        #left {
            border: 1px solid black;
            width: 300px;
            height: 900px;
            float: left;
        }

        #main {
            width: 1800px;
            height: 900px;
            border: 1px solid black;
        }
    </style>
</head>

<body>

<div id="left">
    <ul>
        <li class="page_item page-item-2 "><a href="doc.jsp" target="mainFrame" title="医生信息管理">医生信息管理</a></li>
        <li class="page_item page-item-5"><a href="sick.jsp" target="mainFrame" title="就诊管理系统">就诊管理系统</a></li>
    </ul>
</div>
<div id="main">
    <iframe name="mainFrame" scrolling="no" frameborder="0" style="width:1400px;height: 850px;"></iframe>
</div>
</body>
</html>
