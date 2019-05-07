package com.ptpl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigDecimal;
import com.ptpl.mapper.AheadRepayAwardRecordMapper;
 import com.ptpl.model.AheadRepayAwardRecord;
import com.ptpl.service.AheadRepayAwardRecordServiceI;
/**
 * 
 * 标的提前还款奖品补偿记录业务层
 * AheadRepayAwardRecordServiceI
 * 创建人:cjm
 * 时间：2016年10月21日 11:34:25
 * @version 1.0.0
 *
 */
public class AheadRepayAwardRecordServiceImpl implements AheadRepayAwardRecordServiceI{
	
	@Autowired
	private AheadRepayAwardRecordMapper aheadRepayAwardRecordMapper;
	
	/**
	 * 
	* @Title: insert 
	* @Description: TODO(标的提前还款奖品补偿记录数据增加方法) 
	* @param @param aheadRepayAwardRecord
	* @param @return  参数说明 
	* @return int    返回类型 
	* @author cjm
	* @throws
	 */
	@Override
	public int insert(AheadRepayAwardRecord aheadRepayAwardRecord) {
 		return aheadRepayAwardRecordMapper.insert(aheadRepayAwardRecord);
	}
	/**
     * 
    * @Title: insertSelective 
    * @Description: TODO(标的提前还款奖品补偿记录数据增加方法，非空值) 
    * @param @param aheadRepayAwardRecord
    * @param @return  参数说明 
    * @return int    返回类型 
    * @author cjm
    * @throws
     */
	@Override
	public int insertSelective(AheadRepayAwardRecord aheadRepayAwardRecord) {
 		return aheadRepayAwardRecordMapper.insertSelective(aheadRepayAwardRecord);
	}
  	/**
     * 
     * @Title: deleteById 
     * @Description: TODO(标的提前还款奖品补偿记录根据Id 删除方法) 
     * @param @param aheadRepayAwardRecord
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author cjm
     * @throws
     */
	@Override
	public int deleteById(BigDecimal id) {
 		return aheadRepayAwardRecordMapper.deleteById(id);
	}
	/**
     * 
     * @Title: updateById 
     * @Description: TODO(标的提前还款奖品补偿记录更新方法) 
     * @param @param aheadRepayAwardRecord
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author cjm
     * @throws
     */
	@Override
	public int updateById(AheadRepayAwardRecord aheadRepayAwardRecord) {
 		return aheadRepayAwardRecordMapper.updateById(aheadRepayAwardRecord);
	}
	 /**
     * 
     * @Title: findAheadRepayAwardRecords
     * @Description: TODO(标的提前还款奖品补偿记录查询全部) 
     * @param @param aheadRepayAwardRecord
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author cjm
     * @throws
     */
	@Override
	public List<AheadRepayAwardRecord> findAheadRepayAwardRecords(AheadRepayAwardRecord aheadRepayAwardRecord) {
 		return aheadRepayAwardRecordMapper.findAheadRepayAwardRecords(aheadRepayAwardRecord);
	}
  	/**
     * 
    * @Title: findAheadRepayAwardRecordById
    * @Description: TODO(根据Id查询对应的标的提前还款奖品补偿记录信息) 
    * @param @param id
    * @param @return  参数说明 
    * @return AheadRepayAwardRecord    返回类型 
    * @author cjm
    * @throws
     */
	@Override
	public AheadRepayAwardRecord findAheadRepayAwardRecordById(BigDecimal id) {
 		return aheadRepayAwardRecordMapper.findAheadRepayAwardRecordById(id);
	}
	/**
     * 
    * @Title: deleteByArbatchno 
    * @Description: TODO(根据还款批次号删除) 
    * @param @param Arbatchno
    * @param @return    设定文件 
    * @return int    返回类型 
    * @author   cjm  
    * @throws
     */
	@Override
	public int deleteByArbatchno(String Arbatchno) {
 		return aheadRepayAwardRecordMapper.deleteByArbatchno(Arbatchno);
	}
}
