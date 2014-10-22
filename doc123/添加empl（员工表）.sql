
/*自动填入员工表只添加新员工*/
insert into employee(ename,ckno,intime) 
	select a.ename,a.ckno,now() from kaoqin a 
	where a.ename not in (select b.ename from empl b) 
	GROUP BY a.ename,a.ckno 

select a.ename,a.ckno from kaoqin a GROUP BY a.ename,a.ckno 

