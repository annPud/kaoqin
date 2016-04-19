package annpud.kaoqin.datamodel;

import java.sql.Date;
import java.sql.Time;

/**
 * 上下班时间配置
 * Created by annpud on 16/4/14.
 */
public class TimeTb {
    Integer id;
    Date start;
    Date end;
    Time onAm;
    Time offAm;
    Time onPm;
    Time offPm;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Time getOnAm() {
        return onAm;
    }

    public void setOnAm(Time onAm) {
        this.onAm = onAm;
    }

    public Time getOffAm() {
        return offAm;
    }

    public void setOffAm(Time offAm) {
        this.offAm = offAm;
    }

    public Time getOnPm() {
        return onPm;
    }

    public void setOnPm(Time onPm) {
        this.onPm = onPm;
    }

    public Time getOffPm() {
        return offPm;
    }

    public void setOffPm(Time offPm) {
        this.offPm = offPm;
    }
}
