$(document).ready(function() {
	/**
	 * 数据显示表格
	 */
	$('#kaoqin_detail').datagrid(kaoqin_detail_dg);
});
// dg表格
var kaoqin_detail_dg = {
	url : uri + '/kaoqin/kaoqinJson',
	singleSelect : true,
	fitColumns : true,
	pagination : true,
	rownumber : true,
	columns : [ [ {
		field : 'dep',
		title : '部门',
		width : 100
	}, {
		field : 'ename',
		title : '姓名',
		width : 100
	}, {
		field : 'clock',
		title : '打卡时间',
		width : 100
	}, {
		field : 'ckno',
		title : '考勤号码',
		width : 100
	}, {
		field : 'way',
		title : '打卡方式',
		width : 100
	} ] ],
	pageSize : 20,
	toolbar : '#kaoqin_toolbar'
};

function loadComelate() {
	kaoqin_detail_dg.url = uri + '/kaoqin/comlateJson';
	kaoqin_detail_dg.queryParams = {
		monthDate : '2013-03-01',
		onClock : '8:31',
		offClock : '17:30',
	};
	$('#kaoqin_detail').datagrid(kaoqin_detail_dg);
}
