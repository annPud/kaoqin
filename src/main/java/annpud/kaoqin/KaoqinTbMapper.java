package annpud.kaoqin;

import annpud.kaoqin.datamodel.KaoqinTb;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * 考勤对象
 * Created by annpud on 16/4/14.
 */
public interface KaoqinTbMapper {

    /**
     * @param ename     名字
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 考勤对象集
     */
    @Select("SELECT * FROM kaoqin_tb WHERE ename = #{0} AND clock BETWEEN #{1} AND #{2} ORDER BY clock ASC")
    List<KaoqinTb> selectByEname(String ename, Date startTime, Date endTime);

    /**
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param sidx      排序字段
     * @param sord      排序方式
     * @return 考勤对象集
     */
    @Select("SELECT ename,ckno FROM kaoqin_tb WHERE clock BETWEEN #{0} AND #{1} GROUP BY ename, ckno ORDER BY #{2} #{3}")
    List<KaoqinTb> selectByEnameGroup(Date startTime, Date endTime, String sidx, String sord);

    /**
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 集合数
     */
    @Select("SELECT COUNT(g.ename) FROM (SELECT ename FROM kaoqin_tb WHERE clock BETWEEN #{0} AND #{1} GROUP BY ename, ckno ) g")
    Long selectCountByENameGroup(Date startTime, Date endTime);
}
