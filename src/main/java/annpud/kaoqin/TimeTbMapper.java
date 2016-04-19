package annpud.kaoqin;

import annpud.kaoqin.datamodel.TimeTb;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 上下班时间
 * Created by annpud on 16/4/14.
 */
public interface TimeTbMapper {

    /**
     * 查询所有设置
     *
     * @return 设置列表
     */
    @Select("select * from time_tb")
    List<TimeTb> selectAll();
}
