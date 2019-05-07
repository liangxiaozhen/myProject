package com.ptpl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigDecimal;
import com.ptpl.mapper.OverdueRecordMapper;
 import com.ptpl.model.OverdueRecord;
import com.ptpl.service.OverdueRecordServiceI;
/**
 * 
 * 标的提前还款奖励单个投资人记录业务层
 * OverdueRecordServiceI
 * 创建人:cjm
 * 时间：2017年02月10日 16:05:47
 * @version 1.0.0
 *
 */
public class OverdueRecordServiceImpl implements OverdueRecordServiceI{
	
	@Autowired
	private OverdueRecordMapper overdueRecordMapper;
	
	/**
	 * 
	* @Title: insert 
	* @Description: TODO(标的提前还款奖励单个投资人记录数据增加方法) 
	* @param @param overdueRecord
	* @param @return  参数说明 
	* @return int    返回类型 
	* @author cjm
	* @throws
	 */
	@Override
	public int insert(OverdueRecord overdueRecord) {
 		return overdueRecordMapper.insert(overdueRecord);
	}
	/**
     * 
    * @Title: insertSelective 
    * @Description: TODO(标的提前还款奖励单个投资人记录数据增加方法，非空值) 
    * @param @param overdueRecord
    * @param @return  参数说明 
    * @return int    返回类型 
    * @author cjm
    * @throws
     */
	@Override
	public int insertSelective(OverdueRecord overdueRecord) {
 		return overdueRecordMapper.insertSelective(overdueRecord);
	}
  	/**
     * 
     * @Title: deleteById 
     * @Description: TODO(标的提前还款奖励单个投资人记录根据Id 删除方法) 
     * @param @param overdueRecord
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author cjm
     * @throws
     */
	@Override
	public int deleteById(BigDecimal id) {
 		return overdueRecordMapper.deleteById(id);
	}
	/**
     * 
     * @Title: updateById 
     * @Description: TODO(标的提前还款奖励单个投资人记录更新方法) 
     * @param @param overdueRecord
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author cjm
     * @throws
     */
	@Override
	public int updateById(OverdueRecord overdueRecord) {
 		return overdueRecordMapper.updateById(overdueRecord);
	}
	 /**
     * 
     * @Title: findOverdueRecords
     * @Description: TODO(标的提前还款奖励单个投资人记录查询全部) 
     * @param @param overdueRecord
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author cjm
     * @throws
     */
	@Override
	public List<OverdueRecord> findOverdueRecords(OverdueRecord overdueRecord) {
 		return overdueRecordMapper.findOverdueRecords(overdueRecord);
	}
  	/**
     * 
    * @Title: findOverdueRecordById
    * @Description: TODO(根据Id查询对应的标的提前还款奖励单个投资人记录信息) 
    * @param @param id
    * @param @return  参数说明 
    * @return OverdueRecord    返回类型 
    * @author cjm
    * @throws
     */
	@Override
	public OverdueRecord findOverdueRecordById(BigDecimal id) {
 		return overdueRecordMapper.findOverdueRecordById(id);
	}
}
