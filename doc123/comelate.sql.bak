

select time('8:31');

select * from kaoqin a where 
	day(a.clock) in ( day('2013-03-04'),day('2013-03-01') )
	and a.clock in(select b.clock from kaoqin b where )

select date('2013-03-01')

/**忘记打卡*/
-- 计算打卡次数
select ename,ckno,date(clock) date,count(id) count from kaoqin where date(clock) in (date('2013-03-01')) GROUP BY ename,ckno,date(clock) ORDER BY ckno asc

select b.ename,b.ckno,count(id) from kaoqin b where date(b.clock) = date('2013-03-01') and b.ename ='王卡' GROUP BY b.ename

select b.ename,b.ckno,date('2013-03-01') from kaoqin b LEFT JOIN (
	select a.ename,a.ckno,date(a.clock) date,count(id) count 
	from kaoqin a where date(a.clock) in (date('2013-03-01')) 
	GROUP BY a.ename,a.ckno,date(a.clock) 
	ORDER BY a.ckno asc
) on b.ename = a.ename

select * from (
	select a.ename,a.ckno,date(clock) date,count(*) count from kaoqin a GROUP BY a.ename,a.ckno,date(a.clock) ORDER BY a.ckno asc,date asc
) b where b.count<2

/************/

select ename,ckno from kaoqin GROUP BY ename,ckno


/*未打卡记录 建表*/
select ce.*,t.count from (select c.clock,e.ename,e.ckno from clock c,empl e ) AS ce LEFT JOIN (
	select a.ename,date(clock) date,count(*) count from kaoqin a GROUP BY a.ename,date(a.clock)
) AS t
 ON ce.ename = t.ename  and ce.clock = date(t.date)
ORDER BY ce.clock asc , ce.ckno asc

/*未打卡记录 视图*/
select ce.clock AS clock,ce.ename AS ename,ce.ckno as ckno,if(ISNULL(t.count),0,t.count) as count 
from (select c.clock,e.ename,e.ckno from v_clock c,v_empl e ) AS ce LEFT JOIN (
	select a.ename,date(clock) date,count(*) count from kaoqin a GROUP BY a.ename,date(a.clock)
) AS t
 ON ce.ename = t.ename  and ce.clock = date(t.date)
ORDER BY ce.clock asc , ce.ckno asc
/*********/

select c.clock from v_clock c
select * from v_empl e
/*未打卡记录 视图 不用left join*/
select ce.clock AS clock,ce.ename AS ename,ce.ckno as ckno ,(
	select count(*) from kaoqin as a where a.ename = ce.ename and ce.clock = date(a.clock) GROUP BY a.ename,date(a.clock)
) as count
from (select c.clock,e.ename,e.ckno from v_clock c,v_empl e ) as ce
ORDER BY ce.clock asc , ce.ckno asc