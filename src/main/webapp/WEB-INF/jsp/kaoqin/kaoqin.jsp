<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>kaoqin.jsp</title>
<%@include file="../material/easyui.jsp"%>
<script type="text/javascript" src="${uri}/static/js/kaoqin/kaoqin.js"></script>
</head>
<body>
	<table id="kaoqin_detail"></table>
	<div id="kaoqin_toolbar">
		<a href="#" class="easyui-linkbutton" icon="icon-sum" plain="true"
			onclick="loadComelate()">工作时间打卡记录</a> <a href="#"
			class="easyui-linkbutton" icon="icon-cut" plain="true"
			onclick="javascript:alert('未打卡记录')">未打卡记录</a>
	</div>
</body>
</html>