/**
 * 日期工具类
 */
var DateTimeUtil = function() {
	var self = this;
	/**
	 * 获取上个月的第一天
	 */
	this.lastMonthFirstDate = function(date) {
		return self.firstMonthDate(date, -1);
	};

	/**
	 * 获取上个月的第一天，string
	 */
	this.lastMonthFirstDateStr = function(date, separator, isFill0) {
		var lastMonthDate = self.lastMonthFirstDate(date);
		return self.toDateString(lastMonthDate, separator, isFill0);
	};

	/**
	 * 获取上个月的year,numbser类型
	 */
	this.lastMonthsYearNum = function() {
		return self.lastMonthFirstDate().getFullYear();
	}
	/**
	 * 获取上个月的month,numbser类型
	 */
	this.lastMonthsMonthNum = function() {
		return self.lastMonthFirstDate().getMonth();
	}
	/**
	 * 获取上个月的date,numbser类型
	 */
	this.lastMonthsDateNum = function() {
		return self.lastMonthFirstDate().getDate();
	}
	/**
	 * 获取上个月的year,string类型
	 */
	this.lastMonthsYearStr = function() {
		return self.lastMonthsYearNum();
	}
	/**
	 * 获取上个月的month,string类型
	 */
	this.lastMonthsMonthStr = function(isFill0) {
		if (typeof (isFill0) == 'undefined') {
			isFill0 = true;
		}
		var result = self.lastMonthsMonthNum() + 1;
		if (isFill0) {
			return self.fill0(result);
		} else {
			return result;
		}
	};

	/**
	 * 获取下一个月的第一天
	 */
	this.nextMonthFirstDate = function(date) {
		return self.firstMonthDate(date, 1);
	};

	/**
	 * 获取下一个月的第一天，string类型
	 */
	this.nextMonthFirstDateStr = function(date, separator, isFill0) {
		var nextMonthDate = self.nextMonthFirstDate(date);
		return self.toDateString(nextMonthDate, separator, isFill0);
	};

	/**
	 * 获取下一个1号
	 */
	this.monthFirstDayPerWeek = function(date) {
		if (1 == date.getDate()) {
			return self.toDateString(date);
		} else {
			return self.nextMonthFirstDateStr(date);
		}
	};

	/**
	 * 每月的第一天
	 */
	this.firstMonthDate = function(date, incremental) {
		var before = new Date();
		if (typeof (date) == 'undefined') {

		} else {
			before = new Date(date);
		}
		if (typeof (incremental) == 'undefined') {
			incremental = 0;
		}
		var after = before;
		after.setDate(1);
		after.setHours(0, 0, 0, 0);
		after.setMonth(before.getMonth() + incremental);
		return after;
	};

	/**
	 * 格式化输出日期
	 */
	this.toDateString = function(date, separator, isFill0) {
		if (typeof (separator) == 'undefined') {
			separator = '-';
		}
		if (typeof (isFill0) == 'undefined') {
			isFill0 = true;
		}
		var year = date.getFullYear();
		var month = date.getMonth() + 1;
		var date = date.getDate();
		if (isFill0) {
			month = self.fill0(month);
			date = self.fill0(date);
		}
		return year + separator + month + separator + date;
	};

	/**
	 * 格式化输出时间
	 */
	this.toTimeString = function(date, separator, isFill0) {
		if (typeof (separator) == 'undefined') {
			separator = ':';
		}
		if (typeof (isFill0) == 'undefined') {
			isFill0 = true;
		}
		var hour = date.getHours();
		var min = date.getMinutes();
		var sec = date.getSeconds();
		if (isFill0) {
			hour = self.fill0(hour);
			min = self.fill0(min);
			sec = self.fill0(sec);
		}
		return hour + separator + min + separator + sec;
	};

	/**
	 * 不满10的数字前添加0
	 */
	this.fill0 = function(num) {
		if (num < 10) {
			return '0' + num;
		} else {
			return num;
		}
	};

	/**
	 * 是否在日期段内
	 */
	this.isInDayZone = function(date, dayZone) {
		// 装载开始结束区间
		var startends = new Array();
		for (var i = 0; i < dayZone.length; i++) {
			var spl = dayZone[i].split('/');
			var startend = new Date(date);
			startend.setHours(0, 0, 0, 0);
			if (spl.length == 2) {
				startend.setMonth(spl[0] - 1, spl[1]);
			} else if (spl.length == 3) {
				startend.setFullYear(spl[0], spl[1] - 1, spl[2]);
			}
			startends[i] = startend.getTime();
		}
		// 比较
		if (startends[0] < startends[1]) {// 开始时间 < 结束时间
			if (startends[0] <= date.getTime() && date.getTime() < startends[1]) {
				return true;
			} else {
				return false;
			}
		} else {// 开始时间 > 结束时间
			var first = new Date(date);
			first.setMonth(0, 1);
			first.setHours(0, 0, 0, 0);
			first = first.getTime();
			var last = new Date(first);
			last.setFullYear(last.getFullYear() + 1);
			last = last.getTime();
			if (first <= date.getTime() && date.getTime() < startends[1]) {
				return true;
			} else if (startends[0] <= date.getTime() && date.getTime() < last) {
				return true;
			} else {
				return false;
			}
		}
	};

	/**
	 * 是否在时间段内
	 */
	this.isInTimeZone = function(date, startStr, endStr) {

	};

	/**
	 * 时间字符串（或字符串数组），转换为Date
	 */
	this.toDate = function(date, times) {
		if (times instanceof Array) {
			var dates = new Array();
			for (var i = 0; i < times.length; i++) {
				dates[i] = self.toDate(date, times[i]);
			}
			return dates;
		} else {
			var datetime = new Date(date);
			var spl = times.split(':');
			datetime.setHours(spl[0], spl[1], spl[2], 0);
			return datetime;
		}
	};
}

var datetimeUtil = new DateTimeUtil();
// console.log(datetimeUtil.lastMonthFirstDateString());

