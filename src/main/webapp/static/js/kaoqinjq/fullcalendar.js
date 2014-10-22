//已废弃

var chidao = new Array(0, 0, 0, 0, 0, 0);
var chuqin = 0;
function resetCount() {
	chidao = [0, 0, 0, 0, 0, 0];
	chuqin = 0;
}
var monthDateArr = defaultMonthDate.split('-');
var year = monthDateArr[0];
var month = monthDateArr[1] - 1;
var date = monthDateArr[2];

var fcOptions = {
	/** 布局 */
	header : {
		left : 'title',
		center : '',
		right : 'prev,next today'
	},
	contentHeight : 500,
	weekMode : 'variable',
	/** 变量 */
	year : year,
	month : month,
	date : date,
	/** 本地化 */
	monthNames : ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月',
			'十一月', '十二月'],
	dayNames : ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
	dayNamesShort : ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
	buttonText : {
		today : '今天',
		month : '月',
		week : '星期',
		day : '日'
	},
	/** 数据 */
	events : function(start, end, callback) {
		// console.log(start);
		// console.log(end);
		var monthFirst = getMonthFirst(start);
		resetCount();
		$.getJSON(uri + '/kaoqinjq/detailByNameJson', {
			ename : ename,
			monthDate : monthFirst
				// offTime : offTime
			}, function(jsonObj, status) {
			// console.log(data);
			// console.log(status);
			var mil = 0;
			var tit = '';
			var sta = new Date();
			var eve = [];
			var tc = '';
			var bac = '';
			var boc = '';
			if ('success' == status) {
				$.each(jsonObj, function(ind, val) {
							mil = $(val).attr('clock');
							sta = new Date(mil);
							tit = timeFormat(sta);
							// forget(val);
							// isClockWork(jsonObj,ind);
							if (0 != ind) {
								isForget(eve, val, jsonObj[ind - 1]);
								// hasWarn(eve, val, jsonObj[ind - 1]);
								chuqinCount(val, jsonObj[ind - 1]);
							} else {
								isForget(eve, val);
								// hasWarn(eve, val);
								chuqinCount(val);
							}
							var lateLevel = 0;
							if (isClockWork(jsonObj, ind)) {
								lateLevel = isLate(sta);
							}
							var cArr = setColor(lateLevel);
							tc = cArr[0];
							bac = cArr[1];
							boc = cArr[2];
							eve.push({
										title : tit,
										start : sta,
										textColor : tc,
										backgroundColor : bac,
										borderColor : boc
									});
						});
			}
			// console.log(chidao);
			$.each(chidao, function(ind, val) {
						$('#chidao' + ind).html(val);
					});
			$('#chuqin').html(chuqin);
			callback(eve);
		});
	},
	viewDisplay : function(view) {
		$('td.fc-sat,td.fc-sun').addClass('weekend_bkc');
	}
};

$(document).ready(function() {
			$('#detail_fc').fullCalendar(fcOptions);
		});
// ********* 此方法已过期
/**
 * 根据时间转换成date类型
 * 
 * @param {}
 *            millisecond
 * @param {}
 *            onoffStr
 * @return {}
 */
// function getDateFromTime(clock, onoffStr) {
// var onClock = clock;
// var onTimeArr = onoffStr.split(':');
// onClock.setHours(onTimeArr[0]);
// onClock.setMinutes(onTimeArr[1]);
// onClock.setSeconds(onTimeArr[2]);
// return onClock;
// }
/**
 * 迟到时长
 * 
 * @param {}
 *            clock
 * @return {Number}
 */
function isLate(clock) {
	// var clockms = clock.getTime();
	// var onms = getDateFromTime(clock, onTime).getTime();
	// var offms = getDateFromTime(clock, getOffTimeByDate(clock, false))
	// .getTime();
	// if (onms < clockms && clockms < offms) {
	if (isInTimeZone(clock, getLineTime(clock, 'on'), getLineTime(clock, 'off'))) {
		var onms = timeInDate(clock, getLineTime(clock, 'on'));
		var clockms = clock.getTime();
		var over = clockms - onms;
		over = over / 1000 / 60;
		over = parseInt(over.toString().split('.')[0]);
		if (0 < over && over <= 10) {
			return 1;
		} else if (10 < over && over <= 30) {
			return 2;
		} else if (30 < over && over <= 60) {
			return 3;
		} else if (60 < over) {
			return 4;
		}
	}
	return 0;
}
/**
 * 根据情况设置颜色
 * 
 * @param {}
 *            num
 * @return {}
 */
function setColor(num) {
	// console.log(num);
	chidao[num] += 1;
	var tc = '';
	var bac = '';
	var boc = '';
	switch (num) {
		case 1 :
			tc = 'red';
			bac = 'yellow';
			break;
		case 2 :
			tc = 'blue';
			bac = 'yellow';
			break;
		case 3 :
			tc = 'purple';
			bac = 'yellow';
			break;
		case 4 :
			tc = 'green';
			bac = 'yellow';
			break;
		default :
	}
	return [tc, bac, boc];
}
/**
 * 判断是否忘记打卡
 * 
 * @param {}
 *            eve
 * @param {}
 *            val
 * @param {}
 *            pre
 */
function isForget(eve, val, pre) {
	var ap = forget(val, pre);
	if ('' == ap) {
		return;
	}
	chidao[5] += 1;
	var tit = '';
	var forgetDate = new Date();
	if ('pm' == ap) {
		// console.log('-------------------');
		// console.log(pre);
		// console.log(new Date($(pre).attr('clock')));
		// console.log(val);
		// console.log(new Date($(val).attr('clock')));
		// console.log('*******************');
		forgetDate = new Date($(pre).attr('clock'));
		var off = getLineTime(forgetDate, 'off');
		forgetDate = timeInDate(forgetDate, off);
	} else if ('am' == ap) {
		forgetDate = new Date($(val).attr('clock'));
		var on = getLineTime(forgetDate, 'on');
		forgetDate = timeInDate(forgetDate, on);
	}
	tit = timeFormat(forgetDate);
	eve.push({
				title : tit,
				start : forgetDate,
				textColor : 'black',
				backgroundColor : 'red'
			});
}
// ******* 此方法已过期
/**
 * 添加忘记打卡记录
 * 
 * @param {}
 *            eve
 * @param {}
 *            val
 * @param {}
 *            pre
 */
// function hasWarn(eve, val, pre) {
// var warn = $(val).attr('warn');
// if (!warn) {
// return;
// }
// chidao[5] += 1;
// var tit = '';
// var sta = new Date();
// var aop = warn.split('-')[0];
// var arr = new Array(3);
// // console.log(aop);
// // console.log(new Date(clock));
// if ('am' == aop) {
// sta = new Date($(val).attr('clock'));
// arr = onTime.split(':');
// } else if ('pm' == aop) {
// // console.log(pre);
// sta = new Date($(pre).attr('clock'));
// arr = getOffTimeByDate(sta, false).split(':');
// }
// sta.setHours(arr[0]);
// sta.setMinutes(arr[1]);
// sta.setSeconds(arr[2]);
// tit = timeFormat(sta);
// eve.push({
// title : tit,
// start : sta,
// textColor : 'black',
// backgroundColor : 'white'
// });
// }
/**
 * 统计出勤天数
 * 
 * @param {}
 *            val
 * @param {}
 *            pre
 */
function chuqinCount(val, pre) {
	if (!pre) {
		chuqin += 1;
		return;
	};
	var nowDate = new Date($(val).attr('clock')).toLocaleDateString();
	var preDate = new Date($(pre).attr('clock')).toLocaleDateString();
	if (nowDate == preDate) {

	} else {
		chuqin += 1;
	}
}
/**
 * 下一个1号
 * 
 * @param {}
 *            dateString
 * @return {}
 */
function getMonthFirst(date) {
	var nowDate = new Date(date.toDateString());
	if (1 == nowDate.getDate()) {

	} else {
		nowDate.setDate(1);
		nowDate.setMonth(nowDate.getMonth() + 1);
	}
	var y = nowDate.getFullYear();
	var m = nowDate.getMonth() + 1;
	if (m < 10) {
		m = '0' + m;
	};
	return y + '-' + m + '-01';
}
