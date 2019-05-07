package com.ptpl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigDecimal;
import com.ptpl.mapper.PlusPayoutRecordMapper;
 import com.ptpl.model.PlusPayoutRecord;
import com.ptpl.service.PlusPayoutRecordServiceI;
/**
 * 
 * 标的增益清算记录业务层
 * PlusPayoutRecordServiceI
 * 创建人:cjm
 * 时间：2016年11月23日 14:30:07
 * @version 1.0.0
 *
 */
public class PlusPayoutRecordServiceImpl implements PlusPayoutRecordServiceI{
	
	@Autowired
	private PlusPayoutRecordMapper plusPayoutRecordMapper;
	
	/**
	 * 
	* @Title: insert 
	* @Description: TODO(标的增益清算记录数据增加方法) 
	* @param @param plusPayoutRecord
	* @param @return  参数说明 
	* @return int    返回类型 
	* @author cjm
	* @throws
	 */
	@Override
	public int insert(PlusPayoutRecord plusPayoutRecord) {
 		return plusPayoutRecordMapper.insert(plusPayoutRecord);
	}
	/**
     * 
    * @Title: insertSelective 
    * @Description: TODO(标的增益清算记录数据增加方法，非空值) 
    * @param @param plusPayoutRecord
    * @param @return  参数说明 
    * @return int    返回类型 
    * @author cjm
    * @throws
     */
	@Override
	public int insertSelective(PlusPayoutRecord plusPayoutRecord) {
 		return plusPayoutRecordMapper.insertSelective(plusPayoutRecord);
	}
  	/**
     * 
     * @Title: deleteById 
     * @Description: TODO(标的增益清算记录根据Id 删除方法) 
     * @param @param plusPayoutRecord
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author cjm
     * @throws
     */
	@Override
	public int deleteById(BigDecimal id) {
 		return plusPayoutRecordMapper.deleteById(id);
	}
	/**
     * 
     * @Title: updateById 
     * @Description: TODO(标的增益清算记录更新方法) 
     * @param @param plusPayoutRecord
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author cjm
     * @throws
     */
	@Override
	public int updateById(PlusPayoutRecord plusPayoutRecord) {
 		return plusPayoutRecordMapper.updateById(plusPayoutRecord);
	}
	 /**
     * 
     * @Title: findPlusPayoutRecords
     * @Description: TODO(标的增益清算记录查询全部) 
     * @param @param plusPayoutRecord
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author cjm
     * @throws
     */
	@Override
	public List<PlusPayoutRecord> findPlusPayoutRecords(PlusPayoutRecord plusPayoutRecord) {
 		return plusPayoutRecordMapper.findPlusPayoutRecords(plusPayoutRecord);
	}
  	/**
     * 
    * @Title: findPlusPayoutRecordById
    * @Description: TODO(根据Id查询对应的标的增益清算记录信息) 
    * @param @param id
    * @param @return  参数说明 
    * @return PlusPayoutRecord    返回类型 
    * @author cjm
    * @throws
     */
	@Override
	public PlusPayoutRecord findPlusPayoutRecordById(BigDecimal id) {
 		return plusPayoutRecordMapper.findPlusPayoutRecordById(id);
	}
	/**
     * 
    * @Title: findPlusPayoutRecordByPporderno 
    * @Description: TODO(根据增益清算流水号查询信息) 
    * @param @param pporderno
    * @param @return  参数说明 
    * @return PlusPayoutRecord    返回类型 
    * @author cjm
    * @throws
     */
	@Override
	public PlusPayoutRecord findPlusPayoutRecordByPporderno(String pporderno) {
 		return plusPayoutRecordMapper.findPlusPayoutRecordByPporderno(pporderno);
	}
	/**
     * 
    * @Title: findPlusPayoutRecordByRorderno 
    * @Description: TODO(根据还款流水号查询信息) 
    * @param @param rorderno
    * @param @return  参数说明 
    * @return PlusPayoutRecord    返回类型 
    * @author cjm
    * @throws
     */
 	@Override
	public PlusPayoutRecord findPlusPayoutRecordByRorderno(String rorderno) {
 		return plusPayoutRecordMapper.findPlusPayoutRecordByRorderno(rorderno);
	}
}
