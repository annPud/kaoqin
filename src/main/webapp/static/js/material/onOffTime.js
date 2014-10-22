//已废弃


var defaultMonthDate = initDefaultMonthDate();
var ename = "";
var onTime = '08:30:00';
var offTime = '17:30:00';
var winterOffTime = '17:30:00';
var summerOffTime = '18:00:00';
// var summerLineDate = new Date('2013/05/08 00:00:00');
var summerLineDateStart = '2013/05/08 00:00:00';
var summerLineDate = '/05/01 00:00:00';
var winnerLineDate = '/11/01 00:00:00';
function initDefaultMonthDate() {
	var now = new Date();
	now.setMonth(now.getMonth() - 1);
	var y = now.getFullYear();
	var m = now.getMonth() + 1;
	if (m < 10) {
		m = '0' + m;
	}
	return y + '-' + m + '-01';
}
/**
 * 是否忘记打卡
 * 
 * @param {}
 *            val
 * @param {}
 *            pre
 * @return {String}
 */
function forget(val, pre) {
	var valDate = new Date($(val).attr('clock'));
	var preDate = new Date();
	// 比较下班时间，是否是在当天下班后的打卡记录
	if (isInTimeZone(valDate, getLineTime(valDate, 'off'))) {
		// 下班时间以后
		if (typeof(pre) == 'undefined') {
			return 'am';
		} else {
			preDate = new Date($(pre).attr('clock'));
			if (0 != preDate.getDate() - valDate.getDate()) {
				return 'am';
			}
		}
	} else {
		// 下班时间以前
		if (typeof(pre) == 'undefined') {

		} else {
			preDate = new Date($(pre).attr('clock'));
			if (isInTimeZone(preDate, getLineTime(preDate, 'off'))) {

			} else if (0 == preDate.getDate() - valDate.getDate()) {

			} else {
				return 'pm';
			}
		}
	}
	return '';
}
/**
 * 根据日期判断上下班时间
 * 
 * @param {}
 *            date
 * @param {}
 *            onoff
 * @return {}
 */
function getLineTime(date, onoff) {
	if ('on' == onoff) {
		// return timeInDate(date, onTime);
		return onTime;
	} else if ('off' == onoff) {
		// console.log(date);
		// if (isInDateZone(date, summerLineDate)) {
		// return summerOffTime;
		// } else {
		// return winterOffTime;
		// }
		if (!isInDateZone(date, new Date(summerLineDateStart))) {
			return winterOffTime;
		} else if (isInDateZone(date, new Date(date.getFullYear()
						+ summerLineDate), new Date(date.getFullYear()
						+ winnerLineDate))) {
			return summerOffTime;
		} else {
			return winterOffTime;
		}

	}
}

function isClockWork(all,ind) {
	
	return true;
}
