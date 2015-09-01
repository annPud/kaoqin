<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>fullcalender.jsp</title>
<%@include file="material/jqueryui.jsp"%>
<%@include file="material/fullcalendar.jsp"%>
<script type="text/javascript">
	ename = '${ename}';
</script>
<script type="text/javascript" src="${uri}/static/js/kaoqinDatetime.js"></script>
<script type="text/javascript" src="${uri}/static/js/kaoqinFullcalendar.js"></script>

<style>
body {
	margin: 10px 0px 0px 10px;
	font-size: 14px;
	font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
}

#detail_fc {
	float: left;
	width: 900px;
}

.weekend_bkc {
	background-color: gray;
}

#tishi {
	float: left;
	margin: 50px 0px 0px 10px;
	width: 170px;
	height: auto;
	text-align: center;
}

ul.tishi {
	height: auto;
	margin: 0px;
	padding: 0px;
}

ul.tishi li {
	line-height: auto;
	list-style-type: none;
	margin: 5px 0px 5px 0px;
	float: left;
}

ul.tishi li div {
	float: left;
	margin-left: 5px;
	margin-top: 2px;
}

label {
	height: 18px;
	width: 130px;
	display: block;
	float: left;
	border-radius: 3px;
	padding: 2px 2px 0px 2px;
	border-radius: 3px;
	margin-right: 5px;
}
</style>
</head>
<body>
	<div id="detail_fc"></div>
	<div id="tishi">
		<ul class="tishi">
			<li id="chuqin"><div>出勤天数:</div>
				<div>0</div></li>
			<li id="late0"><label>正常打卡</label>
				<div>0</div></li>
			<li id="late1"><label>迟到10分钟之内</label>
				<div>0</div></li>
			<li id="late1Koukuan"><label></label></li>
			<li id="late2"><label>迟到10-30分钟</label>
				<div>0</div></li>
			<li id="late3"><label>迟到30-60分钟</label>
				<div>0</div></li>
			<li id="late4"><label>迟到60分钟以上</label>
				<div>0</div></li>
			<li id="forgot"><label>忘记打卡</label>
				<div>0</div></li>
		</ul>
	</div>
</body>
</html>