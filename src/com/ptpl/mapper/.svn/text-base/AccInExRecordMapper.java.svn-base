package com.ptpl.mapper;

import java.util.List;
import java.util.Map;
import com.ptpl.model.AccInExRecord;

public interface AccInExRecordMapper {
    int insert(AccInExRecord record);

    int insertSelective(AccInExRecord record);
    
    List<AccInExRecord>queryAllUserAccInExRecord(Map map);
    /**根据充值总金额查询数据库有无重复数据*/
    AccInExRecord queryAccInExRecord(AccInExRecord record);
    /**
     * 根据说明查询收支记录
    * @Title: getAccInExRecordBydescription 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param record
    * @param @return  参数说明 
    * @return AccInExRecord    返回类型 
    * @author jiangxueyou
    * @throws
     */
    AccInExRecord getAccInExRecordBydescription(String record);
    /*用户后台查看资金明细*/
    List<AccInExRecord>queryAllUser(AccInExRecord record);
    /**
     * 
    * @Title: findAccInExRecordByBorderno 
    * @Description: TODO(根据业务流水号 查找信息) 
    * @param @param borderno
    * @param @return  参数说明 
    * @return AccInExRecord    返回类型 
    * @author cjm
    * @throws
     */
    List<AccInExRecord> findAccInExRecordByBorderno(String borderno);

}