$(document).ready(function() {
	$("#gridByName").jqGrid({
		// 数据?defaultMonthDate=2013-03-01
		url : uri + '/kaoqinjq/groupByNameJson',
		datatype : 'json',
		mtype : 'GET',
		colModel : [{
			label : '姓名',
			name : 'ename',
			index : 'ename',
			width : 100,
			key : true
		// formatter : function(value, options, row) {
		// var btn = '<button class="detail_btn" detid=' + row.ename;
		// btn += '>+</button>';
		// return btn + value;
		// }
		}, {
			label : '考勤号码',
			name : 'ckno',
			index : 'ckno',
			width : 100
		}, {
			label : '部门',
			name : 'dep',
			index : 'dep',
			width : 100
		}],
		jsonReader : {
			root : 'list'
		},
		rownumbers : true,
		// 分页
		pager : '#gridByName_pager',
		rowNum : 100,
		rowList : [30, 60, 100],
		postData : {
			monthDate : defaultMonthDate
		},
		// 格式
		autowidth : true,
		height : '100%',
		viewrecords : true,
		caption : getCaption(),
		sortname : 'ckno',
		sortorder : 'asc',

		subGrid : true,
		// subGridUrl : '/123',
		// subGridModel : [ {
		// name : [ '' ],
		// width : [ 200 ]
		// } ],
		subGridRowExpanded : function(subgrid_id, row_id) {
			// console.log(subgrid_id);
			// console.log(row_id);
			// ename = row_id;
			// var src = uri + '/kaoqinjq';
			var src = uri + '/kaoqinjq/detailByName?ename=' + row_id;
			var ifr = $('<iframe/>').attr({
				'id' : 'ifr_' + row_id,
				'src' : src
			}).css({
				'width' : '1150px',
				'height' : '600px'
			});
			ifr.appendTo($('#' + subgrid_id));
			// var html = '<iframe class="sg_if" id="if_' + divid;
			// html += '"></iframe>';
			// $('#' + divid).html(html);
			// $('#if_' + divid).load(src);
		}

	});
	// /**
	// * 按钮
	// */
	// $('#gridByName').on('click', '.detail_btn', function(e) {
	// // console.log(e.target);
	// // console.log($(e.target));
	// // var id = $(e.target).attr('detid');
	// // console.log(id);
	// // console.log($('#ifr_' + id));
	// // var src = uri + '/index.html';
	// // $('#ifr' + id).load(src);
	// $('#ifr_testd').attr('src', '/kaoqin/index.html');
	// });
	$('body').on('pageshow', '.sg_if', function(e) {
		console.log(e);
	});
});

function getCaption() {
	var arr = defaultMonthDate.split('-');
	var y = arr[0];
	var m = arr[1];
	return y + '年' + m + '月 ' + '考勤记录';
}