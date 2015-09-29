var DayZone = function(date) {
	var dayZones = new Array();
	var timeZones = new Array();
	dayZones[0] = new Array('1970/01/01', '2013/05/08');
	timeZones[0] = new Array('08:31:00', '12:00:00', '13:30:00', '17:30:00');

	dayZones[1] = new Array('2013/05/08', '2013/11/01');
	timeZones[1] = new Array('08:31:00', '12:00:00', '14:00:00', '18:00:00');

	dayZones[2] = new Array('2013/11/01', '2014/02/10');
	timeZones[2] = timeZones[0];

	dayZones[3] = new Array('2014/02/10', '2014/05/19');
	timeZones[3] = new Array('09:00:00', '12:00:00', '13:00:00', '18:00:00');

	dayZones[4] = new Array('2014/05/19', '2014/10/01');
	timeZones[4] = timeZones[1];
	
	dayZones[5] = new Array('2014/10/01', '2015/05/01');
	timeZones[5] = timeZones[0];
	
	dayZones[6] = new Array('2015/05/01', '2015/10/01');
	timeZones[6] = timeZones[1];
	
	dayZones[7] = new Array('2015/10/01', '2016/05/01');
	timeZones[7] = timeZones[0];

	var dayDate = new Date(date.toDateString());

	this.getTime = function() {
		var no = zoneNo(dayDate);
		var timeZone = timeZones[no];
		var times = new Array();
		for (var i = 0; i < timeZone.length; i++) {
			times[i] = timeToDate(dayDate, timeZone[i]);
		}
		return times;
	};

	var dayToDate = function(dayString) {
		return new Date(dayString + " 00:00:00");
	};
	var zoneNo = function(date) {
		for (var i = 0; i < dayZones.length; i++) {
			var start = dayToDate(dayZones[i][0]).getTime();
			var end = dayToDate(dayZones[i][1]).getTime();
			if (start <= date.getTime() && date.getTime() < end) {
				return i;
			};
		}
	};
	var timeToDate = function(dayDate, timeString) {
		var timeDate = new Date(dayDate.toDateString());
		var arr = timeString.split(':');
		timeDate.setHours(arr[0]);
		timeDate.setMinutes(arr[1]);
		timeDate.setSeconds(arr[2]);
		return timeDate;
	};

};

/**
 * 分组对象：月
 * 
 * @param {}
 *            monthDate
 */
var MonthKaoqin = function(monthDate) {
	this.date = new Date(monthDate);
	this.days = new Array();// 当月打卡天数
	this.putClock = function(ms) {
		var clockKaoqin = new ClockKaoqin(ms);
		if (0 == this.days.length) {
			this.days.push(new DayKaoqin(clockKaoqin));
		} else if (this.days[this.days.length - 1].dateString == clockKaoqin.date
				.toLocaleDateString()) {
			this.days[this.days.length - 1].putClock(clockKaoqin);
		} else {
			this.days.push(new DayKaoqin(clockKaoqin));
		}
	};
	/**
	 * 得到全部打卡记录
	 */
	var clocks = new Array();
	this.getAllClocks = function() {
		if (0 == clocks.length) {
			for (var i = 0; i < this.days.length; i++) {
				var day = this.days[i];
				day.handleClock();
				for (var j = 0; j < day.clocks.length; j++) {
					clocks.push(day.clocks[j]);
				}
			}
		}
		return clocks;
	};

};
/**
 * 分组对象：天
 * 
 * @param {}
 *            clockKaoqin
 */
var DayKaoqin = function(clockKaoqin) {
	this.dateString = clockKaoqin.date.toLocaleDateString();
	this.date = new Date(clockKaoqin.date.toDateString());
	this.amOnTime = new Date(this.date);// AM上班时间
	this.amOffTime = new Date(this.date);// AM下班时间
	this.pmOnTime = new Date(this.date);// PM上班时间
	this.pmOffTime = new Date(this.date);// PM下班时间
	this.clocks = new Array();// 当天打卡记录
	/**
	 * 设置打卡时间
	 */
	this.putClock = function(clockKaoqin) {
		this.clocks.push(clockKaoqin);
	};
	this.putClock(clockKaoqin);
	/**
	 * 打卡记录设置属性
	 */
	this.handleClock = function() {
		var clockArr = this.clocks.slice(0);
		for (var i = 0; i < this.clocks.length; i++) {
			var clock = this.clocks[i];
			var ctime = clock.date.getTime();
			// 忘记打卡上午上班
			if (0 == i) {
				if (ctime > this.pmOffTime) {
					var forgot = new ClockKaoqin(this.amOnTime);
					forgot.setForgot();
					clockArr.unshift(forgot);
				}
			}
			// 忘记打卡下午上班
			if (this.clocks.length - 1 == i) {
				if (ctime < this.pmOffTime) {
					var forgot = new ClockKaoqin(this.pmOffTime);
					forgot.setForgot();
					clockArr.push(forgot);
				}
			}
			// 迟到
			if ((this.amOnTime.getTime() < ctime && ctime < this.amOffTime
					.getTime())
					|| (this.pmOnTime.getTime() < ctime && ctime < this.pmOffTime
							.getTime())) {
				if (0 != i) {
					continue;
				}
				var over = ctime - this.amOnTime.getTime();
				over = over / 1000 / 60;
				if (0 < over && over <= 10) {
					clock.lateLevel = clock.LATE_0_10;
				} else if (10 < over && over <= 30) {
					clock.lateLevel = clock.LATE_10_30;
				} else if (30 < over && over <= 60) {
					clock.lateLevel = clock.LATE_30_60;
				} else if (60 < over) {
					clock.lateLevel = clock.LATE_60_;
				} else {

				}
			}
		}
		this.clocks = clockArr;
	};
	/**
	 * 设置上下班时间
	 */
	var dz = new DayZone(this.date);
	var timeArr = dz.getTime();
	this.amOnTime = timeArr[0];
	this.amOffTime = timeArr[1];
	this.pmOnTime = timeArr[2];
	this.pmOffTime = timeArr[3];

	// /**
	// * 判断上下班时间
	// */
	// // 新规则new rule 1
	// if (this.date.getTime() - new Date(nr1LineDateStart).getTime() >= 0) {
	// setTime(this.amOnTime, nr1amOnTimeStr);
	// setTime(this.amOffTime, nr1amOffTimeStr);
	// setTime(this.pmOnTime, nr1pmOnTimeStr);
	// setTime(this.pmOffTime, nr1pmOffTimeStr);
	// } else {
	// setTime(this.amOnTime, amOnTimeStr);
	// setTime(this.amOffTime, amOffTimeStr);
	// if (this.date.getTime() - new Date(summerLineDateStart).getTime() < 0) {
	// setTime(this.pmOnTime, pmOnTimeWinterStr);
	// setTime(this.pmOffTime, pmOffTimeWinterStr);
	// } else if (this.date.getTime()
	// - new Date(this.date.getFullYear() + summerLine).getTime() > 0
	// && this.date.getTime()
	// - new Date(this.date.getFullYear() + winnerLine)
	// .getTime() < 0) {
	// setTime(this.pmOnTime, pmOnTimeSummerStr);
	// setTime(this.pmOffTime, pmOffTimeSummerStr);
	// } else {
	// setTime(this.pmOnTime, pmOnTimeWinterStr);
	// setTime(this.pmOffTime, pmOffTimeWinterStr);
	// }
	// }

};
/**
 * 分组对象：打卡次
 * 
 * @param {}
 *            ms
 */
var ClockKaoqin = function(ms) {
	this.ms = ms;
	this.date = new Date(this.ms);
	this.isValid = true;// 是否有效打卡记录
	this.isForgot = false;// 是否是忘记打卡记录
	this.lateLevel = 0;// 迟到等级
	/**
	 * 设置忘记打卡记录
	 */
	this.setForgot = function() {
		this.isForgot = true;
		this.isValid = false;
	};

	this.LATE_0 = 0;// 正常打卡
	this.LATE_0_10 = 1;// 迟到0-10分钟
	this.LATE_10_30 = 2;// 迟到10-30分钟
	this.LATE_30_60 = 3;// 迟到30-60分钟
	this.LATE_60_ = 4;// 迟到60分钟以上
};

// function setTime(date, timeStr) {
// var arr = timeStr.split(':');
// date.setHours(arr[0]);
// date.setMinutes(arr[1]);
// date.setSeconds(arr[2]);
// }
