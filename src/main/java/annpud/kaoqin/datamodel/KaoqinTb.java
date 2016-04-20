package annpud.kaoqin.datamodel;

import java.sql.Timestamp;

/**
 * 考勤对象
 * Created by annpud on 16/4/14.
 */
public class KaoqinTb {

    Long id;
    String dep;
    String ename;
    Integer ckno;
    Timestamp clock;
    Integer mac;
    Integer no;
    String way;
    Integer cdno;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Integer getCkno() {
        return ckno;
    }

    public void setCkno(Integer ckno) {
        this.ckno = ckno;
    }

    public Integer getMac() {
        return mac;
    }

    public void setMac(Integer mac) {
        this.mac = mac;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public Integer getCdno() {
        return cdno;
    }

    public void setCdno(Integer cdno) {
        this.cdno = cdno;
    }

    public Timestamp getClock() {
        return clock;
    }

    public void setClock(Timestamp clock) {
        this.clock = clock;
    }
}
