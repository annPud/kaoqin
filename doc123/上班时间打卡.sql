/*上班时间打卡（迟到早退）*/
select * from kaoqin where 
	date(clock) in ( date('2013-03-04'),date('2013-03-01') )
	and time(clock)>time('8:31') and TIME('17:30')>TIME(clock) ORDER BY clock asc;
/**************/