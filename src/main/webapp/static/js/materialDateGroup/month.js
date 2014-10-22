var defaultMonthDate = initDefaultMonthDate();
function initDefaultMonthDate() {
	var now = new Date();
	now.setMonth(now.getMonth() - 1);
	var y = now.getFullYear();
	var m = now.getMonth() + 1;
	return y + '-' + n0(m) + '-01';
}
function n0(num) {
	if (10 > num) {
		return '0' + num;
	} else {
		return num.toString();
	}
}