package annpud.kaoqin.datamodel;

import java.sql.Date;

/**
 * 考勤数据对象
 * Created by annpud on 16/4/14.
 */
public class KaoqinDm {
    String ename;
    Date clock;
    Boolean isValid;
    Integer lateLevel;

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Date getClock() {
        return clock;
    }

    public void setClock(Date clock) {
        this.clock = clock;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }

    public Integer getLateLevel() {
        return lateLevel;
    }

    public void setLateLevel(Integer lateLevel) {
        this.lateLevel = lateLevel;
    }
}
