//已废弃
/**
 * 判断是否同一天
 * 
 * @param {}
 *            date1
 * @param {}
 *            date2
 * @return {Boolean}
 */
function isSameDate(date1, date2) {
	if (date1.toLocaleString() == date2.toLocaleString()) {
		return true;
	} else {
		return false;
	}
}

/**
 * 判断是否在时间段内
 * 
 * @param {}
 *            date
 * @param {}
 *            start
 * @param {}
 *            end
 * @return {Boolean}
 */
function isInTimeZone(date, start, end) {
	var nowDate = toDate(date);
	// console.log(start);
	var startDate = timeInDate(nowDate, start);
	var result = false;
	if (nowDate.getTime() - startDate.getTime() < 0) {

	} else {
		result = true;
	}
	if (typeof(end) == 'undefined') {
		return result;
	} else {
		// console.log(end);
		var endDate = timeInDate(nowDate, end);
		if (nowDate.getTime() - endDate.getTime() < 0 && result) {
			return true;
		}
	}
	return false;
}
/**
 * 判断是否在日期段内
 * 
 * @param {}
 *            date
 * @param {}
 *            start
 * @param {}
 *            end
 * @return {Boolean}
 */
function isInDateZone(date, start, end) {
	var nowDate = toDate(date);
	var staDate = toDate(start);
	var result = false;
	if (nowDate.getTime() - staDate.getTime() < 0) {

	} else {
		result = true;
	}
	if (typeof(end) == 'undefined') {
		return result;
	} else {
		var endDate = toDate(end);
		if (nowDate.getTime() - endDate.getTime() < 0 && result) {
			return true;
		}
	}
	return false;
}
/**
 * 将字符串，数字，日期转换为日期
 * 
 * @param {}
 *            date
 * @return {}
 */
function toDate(date) {
	var targetDate = new Date();
	if (typeof(date) == 'undefined') {
		return targetDate;
	} else if (typeof(date) == 'string') {
		if (-1 != date.indexOf('-')) {
			targetDate = new Date(date.replace(/-/gi, '/'));
		} else if (-1 != date.indexOf('/')) {
			targetDate = new Date(date);
		}
	} else if (typeof(date) == 'number') {
		targetDate = new Date(date);
	} else if (typeof(date) == 'object') {
		if (!date) {
			return null;
		} else if (date instanceof Date) {
			targetDate = date;
			return targetDate;
		}
	} else {
		return null;
	}
}
/**
 * 时间赋值到给定的date
 * 
 * @param {}
 *            date
 * @param {}
 *            timeStr
 * @return {}
 */
function timeInDate(date, timeStr) {
	var timeDate = new Date(date);
	// console.log(timeStr);
	var arr = timeStr.toString().split(':');
	timeDate.setHours(arr[0]);
	timeDate.setMinutes(arr[1]);
	timeDate.setSeconds(arr[2]);
	return timeDate;
}
/**
 * 数字加0
 * 
 * @param {}
 *            a
 * @return {}
 */
function a0(a) {
	if (a < 10)
		return '0' + a;
	else {
		return a;
	}
}
/**
 * 时间格式，冒号间隔
 * 
 * @param {}
 *            date
 * @return {}
 */
function timeFormat(date) {
	// console.log(date);
	var h = a0(date.getHours());
	var m = a0(date.getMinutes());
	var s = a0(date.getSeconds());
	return h + ":" + m + ":" + s;
}
function dateFormat(date) {
}
/**
 * 时间段内
 * 
 * @param {}
 *            time
 * @param {}
 *            start
 * @param {}
 *            end
 * @return {Boolean}
 */
// function isInTimeZone(time, start, end) {
// if ('undefined' == typeof(end)) {
// console.log(start);
// var dv = time.getTime() - start.getTime();
// if (dv > 0) {
// return true;
// } else {
// return false;
// }
// } else {
// var dv1 = time.getTime() - start.getTime();
// var dv2 = time.getTime() - end.getTime();
// if (dv1 > 0 && dv2 < 0) {
// return true;
// } else {
// return false;
// }
// }
// }
