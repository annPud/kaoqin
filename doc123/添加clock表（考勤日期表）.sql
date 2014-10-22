
INSERT INTO clock(clock) SELECT date(clock) from kaoqin GROUP BY date(clock) HAVING count(id)>30 

select * from clock ORDER BY clock

select count(date) from (
	select date(clock) date,count(id) from kaoqin GROUP BY date(clock) HAVING count(id)>30
) k



