package annpud.kaoqin;

import annpud.kaoqin.datamodel.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.time.*;
import java.util.*;
import java.sql.Date;

/**
 * 接受动态请求
 * Created by annpud on 16/4/14.
 */
@RestController public class KaoqinController {

    private Logger log = Logger.getLogger(getClass());

    @Autowired KaoqinTbMapper km;

    @Autowired TimeTbMapper tm;

    List<TimeTb> defindTimes;

    final Integer SUMMER_MONTH = 5;
    final Integer WINTER_MONTH = 11;
    //上午上班8:31,上午下班12:00
    final LocalTime[] AM_TIMES = new LocalTime[] {LocalTime.of(8, 31), LocalTime.of(12, 0)};
    //夏天5月-11月,下午上班14:00,下午下班18:00
    final LocalTime[] PM_TIMES_SUMMER = new LocalTime[] {LocalTime.of(14, 0), LocalTime.of(18, 0)};
    //冬天11月-5月,下午上班13:30,下午下班17:30
    final LocalTime[] PM_TIMES_WINTER =
        new LocalTime[] {LocalTime.of(13, 30), LocalTime.of(17, 30)};

    @PostConstruct public void init() {
        defindTimes = tm.selectAll();
    }

    /**
     * 查询表格数据
     *
     * @param dm 请求参数
     * @return 表格数据
     */
    @RequestMapping(path = "/group", method = RequestMethod.GET) public GridDm group(GridDm dm) {
        Date[] startEnd = startEnd(dm.getYearMonth());
        dm.setList(km.selectByEnameGroup(startEnd[0], startEnd[1], dm.getSidx(), dm.getSord()));
        Long count = km.selectCountByENameGroup(startEnd[0], startEnd[1]);
        dm.setRecords(count);
        dm.setTotal(count / dm.getRows() + 1);
        return dm;
    }

    /**
     * 按名字查询详细打卡信息
     *
     * @param ename 姓名
     * @param dm    分页参数
     * @return 详细打卡信息
     */
    @RequestMapping(path = "/detail/{ename}", method = RequestMethod.GET) public DetailDm detail(
        @PathVariable String ename, DetailDm dm) {
        Date[] startEnd = startEnd(dm.getYearMonth());
        List<KaoqinTb> tblist = km.selectByEname(ename, startEnd[0], startEnd[1]);
        return handle(tblist, dm);
    }

    /**
     * 根据年月计算开始和结束时间
     *
     * @param yearMonth 年月格式yyyy-MM
     * @return 开始和结束时间
     */
    private Date[] startEnd(String yearMonth) {
        LocalDate start = LocalDate.parse(yearMonth + "-01");
        LocalDate end = start.plusMonths(1);
        return new Date[] {Date.valueOf(start), Date.valueOf(end)};
    }

    private DetailDm handle(List<KaoqinTb> tblist, DetailDm dm) {
        Map<LocalDate, List<KaoqinDm>> dates = new HashMap<>();
        KaoqinDm kdm;
        LocalTime[] times;
        Integer[] late = new Integer[] {0, 0, 0, 0};
        Integer lateLevel;
        //按日期分组
        for (KaoqinTb tb : tblist) {
            kdm = new KaoqinDm();
            //            kdm.setEname(tb.getEname());
            kdm.setClock(tb.getClock());
            byDay(kdm, dates);
        }
        for (Map.Entry<LocalDate, List<KaoqinDm>> en : dates.entrySet()) {
            //处理迟到
            times = timesOnAndOff(en.getKey());
            lateLevel = lateLevel(en.getValue().get(0).getClock().toLocalDateTime(), times);
            if (lateLevel > -1) {
                en.getValue().get(0).setLateLevel(lateLevel);
                late[lateLevel]++;
            }
            //处理忘记打卡
            forgot(en.getValue(), times);
        }
        List<KaoqinDm> dmlist = new ArrayList<>();
        for (Map.Entry<LocalDate, List<KaoqinDm>> en : dates.entrySet()) {
            dmlist.addAll(en.getValue());
        }
        dm.setLists(dmlist);
        dm.setLate(late);
        dm.setDay(dates.size());
        //扣款
        if (3 < dm.getLate()[1] && dm.getLate()[1] <= 8) {
            dm.setCut(15 - ((8 - dm.getLate()[1]) * 3));
        } else if (dm.getLate()[1] > 8) {
            dm.setCut(15 + ((dm.getLate()[1] - 8) * 5));
        } else {
            dm.setCut(0);
        }
        return dm;
    }

    /**
     * 处理日期分组
     *
     * @param kdm   考勤dm对象
     * @param dates 日期分组
     */
    private void byDay(KaoqinDm kdm, Map<LocalDate, List<KaoqinDm>> dates) {
        LocalDate day = kdm.getClock().toLocalDateTime().toLocalDate();
        List<KaoqinDm> list = dates.get(day);
        if (null == list) {
            list = new LinkedList<>();
        }
        list.add(kdm);
        dates.put(day, list);
    }

    /**
     * 计算迟到时间
     *
     * @param clock 打卡时间
     * @return 迟到等级
     */
    private Integer lateLevel(LocalDateTime clock, LocalTime[] times) {
        LocalTime t = clock.toLocalTime();
        if (t.isBefore(times[0]) || (t.isAfter(times[1]) && t.isBefore(times[2])) || t
            .isAfter(times[3])) {
            return -1;
        }
        long minutes = Duration.between(times[0], t).toMinutes();
        if (minutes >= 10) {
            return 1;
        } else if (minutes >= 30) {
            return 2;
        } else if (minutes >= 60) {
            return 3;
        } else {
            return 0;
        }
    }

    /**
     * 计算上下班时间
     *
     * @param date 日期
     * @return 上下班时间
     */
    private LocalTime[] timesOnAndOff(LocalDate date) {
        for (TimeTb tb : defindTimes) {
            if (date.isAfter(tb.getStart().toLocalDate()) && date
                .isBefore(tb.getEnd().toLocalDate())) {
                return new LocalTime[] {tb.getOnAm().toLocalTime(), tb.getOffAm().toLocalTime(),
                    tb.getOnPm().toLocalTime(), tb.getOffPm().toLocalTime()};
            }
        }
        LocalDate summer = date.withMonth(SUMMER_MONTH).withDayOfMonth(1);
        LocalDate winter = date.withMonth(WINTER_MONTH).withDayOfMonth(1);
        if (date.isAfter(summer) && date.isBefore(winter)) {
            return new LocalTime[] {AM_TIMES[0], AM_TIMES[1], PM_TIMES_SUMMER[0],
                PM_TIMES_SUMMER[1]};
        } else {
            return new LocalTime[] {AM_TIMES[0], AM_TIMES[1], PM_TIMES_WINTER[0],
                PM_TIMES_WINTER[1]};
        }
    }

    /**
     * 忘记打卡
     *
     * @param daylist 按天分组打卡纪录列表
     * @param times   上下班时间
     */
    private void forgot(List<KaoqinDm> daylist, LocalTime[] times) {
        KaoqinDm dm = new KaoqinDm();
        dm.setFogot(Boolean.TRUE);
        LocalDateTime first = daylist.get(0).getClock().toLocalDateTime();
        LocalDateTime last = daylist.get(daylist.size() - 1).getClock().toLocalDateTime();
        if (first.toLocalTime().isAfter(times[3])) {
            //上班忘记打卡
            dm.setClock(Timestamp.valueOf(first.with(times[0])));
            daylist.add(dm);
        }
        if (last.toLocalTime().isBefore(times[3])) {
            dm = new KaoqinDm();
            dm.setFogot(Boolean.TRUE);
            dm.setClock(Timestamp.valueOf(last.with(times[3])));
            daylist.add(dm);
        }
    }

}
