<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>groupByName.jsp</title>
<%@include file="../material/easyui.jsp"%>
<script type="text/javascript"
	src="${uri}/static/jslib/easyui/extension/datagrid-detailview.js"></script>
<script type="text/javascript"
	src="${uri}/static/js/kaoqin/groupByName.js"></script>
</head>
<body>
	<div id="title_panel">
		<table id="groupByName_dg"></table>
	</div>
</body>
</html>