package annpud.kaoqin.datamodel;


import java.util.List;

/**
 * 详细数据
 * Created by annpud on 16/4/14.
 */
public class DetailDm extends YearMonthDm {
    Integer[] late = new Integer[] {0, 0, 0, 0, 0};
    Integer forgot = 0;
    Integer day = 0;
    Integer cut = 0;
    List<KaoqinDm> lists;

    public Integer getCut() {
        return cut;
    }

    public void setCut(Integer cut) {
        this.cut = cut;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer[] getLate() {
        return late;
    }

    public void setLate(Integer[] late) {
        this.late = late;
    }

    public Integer getForgot() {
        return forgot;
    }

    public void setForgot(Integer forgot) {
        this.forgot = forgot;
    }

    public List<KaoqinDm> getLists() {
        return lists;
    }

    public void setLists(List<KaoqinDm> lists) {
        this.lists = lists;
    }
}
