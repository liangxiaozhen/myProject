package com.ptpl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigDecimal;
import com.ptpl.mapper.AheadRepayPlatformRecordMapper;
 import com.ptpl.model.AheadRepayPlatformRecord;
import com.ptpl.service.AheadRepayPlatformRecordServiceI;
/**
 * 
 * 标的提前还款奖励平台记录业务层
 * AheadRepayPlatformRecordServiceI
 * 创建人:cjm
 * 时间：2016年10月21日 12:19:20
 * @version 1.0.0
 *
 */
public class AheadRepayPlatformRecordServiceImpl implements AheadRepayPlatformRecordServiceI{
	
	@Autowired
	private AheadRepayPlatformRecordMapper aheadRepayPlatformRecordMapper;
	
	/**
	 * 
	* @Title: insert 
	* @Description: TODO(标的提前还款奖励平台记录数据增加方法) 
	* @param @param aheadRepayPlatformRecord
	* @param @return  参数说明 
	* @return int    返回类型 
	* @author cjm
	* @throws
	 */
	@Override
	public int insert(AheadRepayPlatformRecord aheadRepayPlatformRecord) {
 		return aheadRepayPlatformRecordMapper.insert(aheadRepayPlatformRecord);
	}
	/**
     * 
    * @Title: insertSelective 
    * @Description: TODO(标的提前还款奖励平台记录数据增加方法，非空值) 
    * @param @param aheadRepayPlatformRecord
    * @param @return  参数说明 
    * @return int    返回类型 
    * @author cjm
    * @throws
     */
	@Override
	public int insertSelective(AheadRepayPlatformRecord aheadRepayPlatformRecord) {
 		return aheadRepayPlatformRecordMapper.insertSelective(aheadRepayPlatformRecord);
	}
  	/**
     * 
     * @Title: deleteById 
     * @Description: TODO(标的提前还款奖励平台记录根据Id 删除方法) 
     * @param @param aheadRepayPlatformRecord
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author cjm
     * @throws
     */
	@Override
	public int deleteById(BigDecimal id) {
 		return aheadRepayPlatformRecordMapper.deleteById(id);
	}
	/**
     * 
     * @Title: updateById 
     * @Description: TODO(标的提前还款奖励平台记录更新方法) 
     * @param @param aheadRepayPlatformRecord
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author cjm
     * @throws
     */
	@Override
	public int updateById(AheadRepayPlatformRecord aheadRepayPlatformRecord) {
 		return aheadRepayPlatformRecordMapper.updateById(aheadRepayPlatformRecord);
	}
	 /**
     * 
     * @Title: findAheadRepayPlatformRecords
     * @Description: TODO(标的提前还款奖励平台记录查询全部) 
     * @param @param aheadRepayPlatformRecord
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author cjm
     * @throws
     */
	@Override
	public List<AheadRepayPlatformRecord> findAheadRepayPlatformRecords(AheadRepayPlatformRecord aheadRepayPlatformRecord) {
 		return aheadRepayPlatformRecordMapper.findAheadRepayPlatformRecords(aheadRepayPlatformRecord);
	}
  	/**
     * 
    * @Title: findAheadRepayPlatformRecordById
    * @Description: TODO(根据Id查询对应的标的提前还款奖励平台记录信息) 
    * @param @param id
    * @param @return  参数说明 
    * @return AheadRepayPlatformRecord    返回类型 
    * @author cjm
    * @throws
     */
	@Override
	public AheadRepayPlatformRecord findAheadRepayPlatformRecordById(BigDecimal id) {
 		return aheadRepayPlatformRecordMapper.findAheadRepayPlatformRecordById(id);
	}
	
	/**
     * 
     * @Title: deleteById 
     * @Description: TODO(标的提前还款奖励平台记录根据Id 删除方法) 
     * @param @param aheadRepayPlatformRecord
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author cjm
     * @throws
     */
	@Override
	public int deleteByRorderno(String Rorderno) {
 		return aheadRepayPlatformRecordMapper.deleteByRorderno(Rorderno);
	}
}
