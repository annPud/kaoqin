var onTime = '08:30:00';
var offTime = '17:30:00';

/**
 * 得到上个月
 */
function lastMonth() {
	var now = new Date();
	now.setMonth(now.getMonth() - 1);
	var year = now.getYear() + 1900;
	var month = now.getMonth() + 1;
	if (month < 10) {
		month = '0' + month;
	}
	var lastMonth = year + '-' + month + '-01';
	return lastMonth;
}
/**
 * 主表，按名字划分
 * 
 * @type
 */
var groupByName_dg = {
	url : uri + '/kaoqin/groupByNameJson',
	singleSelect : true,
	fitColumns : true,
	rownumbers : true,
	queryParams : {
		monthDate : lastMonth()
	},
	columns : [[{
				field : 'ename',
				title : '姓名',
				width : 100
			}, {
				field : 'ckno',
				title : '考勤号码',
				width : 100
			}, {
				field : 'dep',
				title : '部门',
				width : 100
			}]],
	view : detailview,
	detailFormatter : function(index, row) {
		return "<div id='subdgPanel_" + index + "'><table id='subgrid_" + index
				+ "'></table></div>";
	},
	onExpandRow : function(index, row) {
		$('#subdgPanel_' + index).panel(subdgPanel(index, row));
		$('#subgrid_' + index).datagrid(subgrid(row.ename));
		$('#groupByName_dg').datagrid('fixDetailRowHeight', index);
	}

};
/**
 * 子表panel
 * 
 * @param {}
 *            index
 * @param {}
 *            row
 * @return {}
 */
function subdgPanel(index, row) {
	var subdg_panel = {
		width : 700,
		border : false,
		cache : false,
		onLoad : function() {
			$('#dg').datagrid('fixDetailRowHeight', index);
		}
	};
	return subdg_panel;
}

/**
 * 子表
 * 
 * @param {}
 *            name
 * @return {}
 */
function subgrid(name) {
	var subgrid = {
		url : uri + '/kaoqin/detailByNameJson',
		singleSelect : true,
		fitColumns : true,
		rownumbers : true,
		queryParams : {
			monthDate : lastMonth(),
			ename : name,
			offTime : offTime
		},
		columns : [[{
			field : 'clock',
			title : '打卡时间',
			width : 200,
			align : 'center',
			formatter : function(value, row, index) {
				// 上班时间打卡，格式处理
				// var val = toDate(value).toLocaleString();
				var val = dateFormat(value);
				dateFormat(value);
				var result = comelate(value);
				row.overMin = result[0];
				return '<span style="color:' + result[1] + ';">' + val
						+ '</span>';
			}
		}, {
			field : 'way',
			title : '打卡方式',
			width : 100,
			align : 'center'
		}, {
			field : 'overMin',
			title : '迟到时间',
			width : 100,
			align : 'center'
		}, {
			field : 'warn',
			title : '备注',
			width : 100,
			align : 'center'
		}]],
		rowStyler : function(index, row) {
			var result = comelate(row.clock);
			if ('' != result[0]) {
				return 'background-color:yellow;';
			} else if (row.warn) {
				return 'background-color:yellow;';
			}
		}

	};
	return subgrid;
}
/**
 * 判断上班时间打卡的情况
 * 
 * @param {}
 *            timeStr
 * @return {Number}
 */
function comelate(timeStr) {
	var clock = toDate(timeStr);
	var onClock = toDateOnClock(timeStr, onTime);
	var offClock = toDateOnClock(timeStr, offTime);
	var overMin = '';
	var color = 'black';
	if (onClock < clock && clock < offClock) {
		var over = clock - onClock;
		over = over / 1000 / 60;
		over = parseInt(over.toString().split('.')[0]);
		overMin = over.toString() + ' 分钟';
		if (0 < over && over <= 10) {
			color = 'red';
		} else if (10 < over && over <= 30) {
			color = 'blue';
		} else if (30 < over && over <= 60) {
			color = 'purple';
		} else if (60 < over) {
			color = 'green';
		}
	}
	return [overMin, color];
}
/**
 * 转换成Date类型
 * 
 * @param {}
 *            timeStr
 * @return {}
 */
function toDate(millisecond) {
	var datetime = new Date();
	datetime.setTime(millisecond);
	return datetime;
}
/**
 * 转化成当天上下班时间的Date类型
 * 
 * @param {}
 *            timeStr
 * @param {}
 *            onoffStr
 * @return {}
 */
function toDateOnClock(millisecond, onoffStr) {
	var onClock = toDate(millisecond);
	var onTimeArr = onoffStr.split(':');
	onClock.setHours(onTimeArr[0]);
	onClock.setMinutes(onTimeArr[1]);
	onClock.setSeconds(onTimeArr[2]);
	return onClock;
}
/**
 * 格式化时间
 * 
 * @param {}
 *            millisecond
 * @return {}
 */
function dateFormat(millisecond) {
	var date = toDate(millisecond);
	var result = date.toLocaleDateString() + ' ' + date.getHours() + ':'
			+ date.getMinutes() + ':' + date.getSeconds() + ' 星期'
			+ date.getDay();
	return result;
}
/**
 * 设置标题
 * 
 * @return {}
 */
function title() {
	var tempStr = lastMonth().split('-');
	return tempStr[0] + '年' + tempStr[1] + '月 考勤记录';
}
$(document).ready(function() {
			$('#title_panel').panel({
						title : title()
					});
			$('#groupByName_dg').datagrid(groupByName_dg);
		});
