package annpud.kaoqin;

import annpud.kaoqin.datamodel.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.time.*;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

/**
 * 接受动态请求
 * Created by annpud on 16/4/14.
 */
@RestController public class KaoqinController {
    private static final Logger log = Logger.getLogger(KaoqinController.class);

    @Autowired KaoqinTbMapper km;

    @Autowired TimeTbMapper tm;

    //    List<TimeDm> times = new ArrayList<>();
    List<TimeTb> defindTimes;

    final LocalTime[] AM_TIMES = new LocalTime[] {LocalTime.of(8, 31), LocalTime.of(12, 0)};
    final LocalTime[] PM_TIMES_SUMMER = new LocalTime[] {LocalTime.of(14, 0), LocalTime.of(18, 0)};
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

    @RequestMapping(path = "/detail/{ename}", method = RequestMethod.GET)
    public DetailDm detail(@PathVariable() String ename, DetailDm dm) {
        Date[] startEnd = startEnd(dm.getYearMonth());
        List<KaoqinTb> tblist = km.selectByEname(ename, startEnd[0], startEnd[1]);
        List<KaoqinDm> dmlist = new ArrayList<>();

        return dm;
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

    private Integer late(Timestamp clock) {

        return 0;
    }

    /**
     * 上下班时间
     *
     * @param clock 考勤时间
     * @return 上下班时间
     */
    private LocalTime[] timesOnAndOff(Timestamp clock) {
        LocalDateTime dt = clock.toLocalDateTime();
        for (TimeTb tb : defindTimes) {
            if (dt.toLocalDate().isAfter(tb.getStart().toLocalDate()) && dt.toLocalDate()
                .isBefore(tb.getEnd().toLocalDate())) {
                return new LocalTime[] {tb.getOnAm().toLocalTime(), tb.getOffAm().toLocalTime(),
                    tb.getOnPm().toLocalTime(), tb.getOffPm().toLocalTime()};
            }
        }
        LocalDate summerStart = dt.toLocalDate().withMonth(5).withDayOfMonth(1);
        LocalDate summerEnd = dt.toLocalDate().withMonth(11).withDayOfMonth(1);
        if (dt.toLocalDate().isAfter(summerStart) && dt.toLocalDate().isBefore(summerEnd)) {
            return new LocalTime[] {AM_TIMES[0], AM_TIMES[1], PM_TIMES_SUMMER[0],
                PM_TIMES_SUMMER[1]};
        } else {
            return new LocalTime[] {AM_TIMES[0], AM_TIMES[1], PM_TIMES_WINTER[0],
                PM_TIMES_WINTER[1]};
        }
    }

    private KaoqinDm forgot() {

        return new KaoqinDm();
    }
}
