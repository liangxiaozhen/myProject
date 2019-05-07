package com.ptpl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigDecimal;
import com.ptpl.mapper.InterestExpenseRecordMapper;
 import com.ptpl.model.InterestExpenseRecord;
import com.ptpl.service.InterestExpenseRecordServiceI;
/**
 * 
 * 标的利息管理费记录业务层
 * InterestExpenseRecordServiceI
 * 创建人:cjm
 * 时间：2016年10月13日 16:56:29
 * @version 1.0.0
 *
 */
public class InterestExpenseRecordServiceImpl implements InterestExpenseRecordServiceI{
	
	@Autowired
	private InterestExpenseRecordMapper interestExpenseRecordMapper;
	
	/**
	 * 
	* @Title: insert 
	* @Description: TODO(标的利息管理费记录数据增加方法) 
	* @param @param interestExpenseRecord
	* @param @return  参数说明 
	* @return int    返回类型 
	* @author cjm
	* @throws
	 */
	@Override
	public int insert(InterestExpenseRecord interestExpenseRecord) {
 		return interestExpenseRecordMapper.insert(interestExpenseRecord);
	}
	/**
     * 
    * @Title: insertSelective 
    * @Description: TODO(标的利息管理费记录数据增加方法，非空值) 
    * @param @param interestExpenseRecord
    * @param @return  参数说明 
    * @return int    返回类型 
    * @author cjm
    * @throws
     */
	@Override
	public int insertSelective(InterestExpenseRecord interestExpenseRecord) {
 		return interestExpenseRecordMapper.insertSelective(interestExpenseRecord);
	}
  	/**
     * 
     * @Title: deleteById 
     * @Description: TODO(标的利息管理费记录根据Id 删除方法) 
     * @param @param interestExpenseRecord
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author cjm
     * @throws
     */
	@Override
	public int deleteById(BigDecimal id) {
 		return interestExpenseRecordMapper.deleteById(id);
	}
	/**
     * 
     * @Title: updateById 
     * @Description: TODO(标的利息管理费记录更新方法) 
     * @param @param interestExpenseRecord
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author cjm
     * @throws
     */
	@Override
	public int updateById(InterestExpenseRecord interestExpenseRecord) {
 		return interestExpenseRecordMapper.updateById(interestExpenseRecord);
	}
	 /**
     * 
     * @Title: findInterestExpenseRecords
     * @Description: TODO(标的利息管理费记录查询全部) 
     * @param @param interestExpenseRecord
     * @param @return  参数说明 
     * @return int    返回类型 
     * @author cjm
     * @throws
     */
	@Override
	public List<InterestExpenseRecord> findInterestExpenseRecords(InterestExpenseRecord interestExpenseRecord) {
 		return interestExpenseRecordMapper.findInterestExpenseRecords(interestExpenseRecord);
	}
	
    /**
    * 
    * @Title: findInterestExpenseRecord
    * @Description: TODO(标的利息管理费记录 查询) 
    * @param @param interestExpenseRecord
    * @param @return  参数说明 
    * @return int    返回类型 
    * @author chenjiaming
    * @throws
    */
	@Override
	public InterestExpenseRecord findInterestExpenseRecord(InterestExpenseRecord interestExpenseRecord){
		return interestExpenseRecordMapper.findInterestExpenseRecord(interestExpenseRecord);
	}
	
  	/**
     * 
    * @Title: findInterestExpenseRecordById
    * @Description: TODO(根据Id查询对应的标的利息管理费记录信息) 
    * @param @param id
    * @param @return  参数说明 
    * @return InterestExpenseRecord    返回类型 
    * @author cjm
    * @throws
     */
	@Override
	public InterestExpenseRecord findInterestExpenseRecordById(BigDecimal id) {
 		return interestExpenseRecordMapper.findInterestExpenseRecordById(id);
	}
	 /**
     * 
    * @Title: findInterestExpenseRecordByRorderno 
    * @Description: TODO(根据还款流水号rorderno查询对应的标的利息管理费记录 信息) 
    * @param @param rorderno
    * @param @return  参数说明 
    * @return InterestExpenseRecord    返回类型 
    * @author cjm
    * @throws
     */
 	@Override
	public InterestExpenseRecord findInterestExpenseRecordByRorderno(String rorderno) {
 		return interestExpenseRecordMapper.findInterestExpenseRecordByRorderno(rorderno);
	}
}
