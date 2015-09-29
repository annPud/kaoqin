var monthDateArr = defaultMonthDate.split('-');
var year = monthDateArr[0];
var month = monthDateArr[1] - 1;
var date = monthDateArr[2];

var CountCalendar = function(monthKaoqin) {
	// 迟到次数统计
	var lates = new Array(0, 0, 0, 0, 0);
	var forgot = 0;// 忘记打卡次数
	this.values = new Array();// 打卡数据

	// 表格数据
	var dataList = monthKaoqin.getAllClocks();
	for (var i = 0; i < dataList.length; i++) {
		var clock = dataList[i];
		if (clock.isForgot) {
			forgot++;
		} else if (clock.isValid) {
			lates[clock.lateLevel]++;
		}
		var value = new ValueCalendar(clock);
		this.values.push(value);
	}
	// 说明、统计显示
	// 设置迟到数据
	for (var i = 0; i < lates.length; i++) {
		var $late = $('#late' + i);
		$late.find('div').html(lates[i]);
		var color = new ColorLate(i).init();
		$late.find('label').css({
					'color' : color.textColor,
					'border' : '1px solid ' + color.borderColor,
					'background-color' : color.backgroundColor
				});
	}
	// 设置迟到1扣款
	var late1Count = lates[1];
	var koukuan = 0;
	if (3 < late1Count && late1Count <= 8) {
		koukuan = 15 - ((8 - late1Count) * 3);
	} else if (late1Count > 8) {
		koukuan = 15 + ((late1Count - 8) * 5);
	} else {

	}
	$('#late1Koukuan').find('label').html('迟到10分钟：￥' + koukuan);

	// 设置忘记打卡
	var color = new ColorForgot().init();
	var $forgot = $('#forgot');
	$forgot.find('label').css({
				'color' : color.textColor,
				'border' : '1px solid ' + color.borderColor,
				'background-color' : color.backgroundColor
			});
	$forgot.find('div').html(forgot);
	// 设置出勤天数
	$('#chuqin').find('div').last().html(monthKaoqin.days.length);

};

var ValueCalendar = function(clock) {
	this.start = clock.date;// 开始时间
	// 显示标题
	this.title = n0(clock.date.getHours()) + ':' + n0(clock.date.getMinutes())
			+ ':' + n0(clock.date.getSeconds());
	var color = new Object();
	if (clock.isForgot) {
		color = new ColorForgot();
	} else {
		color = new ColorLate(clock.lateLevel);
	}
	this.textColor = color.textColor;// 字体颜色
	this.backgroundColor = color.backgroundColor;// 背景颜色
	this.borderColor = color.borderColor;// 边框颜色
};
/**
 * 忘记打卡颜色
 */
var ColorForgot = function() {
	this.backgroundColor = 'red';
	this.textColor = 'white';
	this.borderColor = '';
	this.init = function() {
		if ('' == this.borderColor) {
			this.borderColor = 'rgb(58, 135, 173)';
		}
		return this;
	};
};
/**
 * 迟到颜色
 * 
 * @param {}
 *            lateLevel
 */
var ColorLate = function(lateLevel) {
	this.backgroundColor = '';
	this.textColor = '';
	this.borderColor = '';
	if (lateLevel > 0) {
		this.backgroundColor = 'yellow';
	}
	switch (lateLevel) {
		case 1 :
			this.textColor = 'red';
			break;
		case 2 :
			this.textColor = 'blue';
			break;
		case 3 :
			this.textColor = 'purple';
			break;
		case 4 :
			this.textColor = 'green';
			break;
	}
	this.init = function() {
		if ('' == this.textColor) {
			this.textColor = 'white';
		}
		if ('' == this.backgroundColor) {
			this.backgroundColor = 'rgb(58, 135, 173)';
		}
		if ('' == this.borderColor) {
			this.borderColor = 'rgb(58, 135, 173)';
		}
		return this;
	};
};

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
		var monthFirst = getMonthFirst(start);
		$.getJSON(uri + '/detailByNameJson', {
					ename : ename,
					monthDate : monthFirst
				}, function(jsonObj, status) {
					var monthKaoqin = new MonthKaoqin(monthFirst);
					if ('success' == status) {
						$.each(jsonObj, function(ind, val) {
									monthKaoqin.putClock(val.clock);
								});
					}
					var cc = new CountCalendar(monthKaoqin);
					callback(cc.values);
				});
	},
	viewDisplay : function(view) {
		$('td.fc-sat,td.fc-sun').addClass('weekend_bkc');
	}
};

$(document).ready(function() {
			$('#detail_fc').fullCalendar(fcOptions);
		});

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
