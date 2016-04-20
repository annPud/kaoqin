package annpud.kaoqin.datamodel;

import java.sql.Timestamp;

/**
 * 考勤数据对象
 * Created by annpud on 16/4/14.
 */
public class KaoqinDm {
    String ename;
    Timestamp clock;
    Boolean isFogot;
    Integer lateLevel;

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Timestamp getClock() {
        return clock;
    }

    public void setClock(Timestamp clock) {
        this.clock = clock;
    }

    public Integer getLateLevel() {
        return lateLevel;
    }

    public Boolean getFogot() {
        return isFogot;
    }

    public void setFogot(Boolean fogot) {
        isFogot = fogot;
    }

    public void setLateLevel(Integer lateLevel) {
        this.lateLevel = lateLevel;
    }
}
