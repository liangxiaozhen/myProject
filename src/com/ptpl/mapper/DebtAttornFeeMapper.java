package com.ptpl.mapper;

import com.ptpl.model.DebtAttornFee;
import java.math.BigDecimal;
import java.util.List;
/**
 * @author liuj
 * 标的债权转让手续费设置
 */
public interface DebtAttornFeeMapper extends BaseMapper<DebtAttornFee>{
	/**
      * @param 根据id查找信息
	  * @return DebtAttornFee
	  */
    DebtAttornFee selectByPrimaryKey(BigDecimal id);
    /**
     * 根据tid查询手续费收取情况
    * @Title: selectByTid 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param tid
    * @param @return  参数说明 
    * @return List<DebtAttornFee>    返回类型 
    * @author jiangxueyou
    * @throws
     */
    List<DebtAttornFee> selectByTid(BigDecimal tid);

    List<String> selectGradeByTid(BigDecimal tid);
}