package com.ptpl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.ManualAwardMapper;
import com.ptpl.model.ManualAward;
import com.ptpl.service.ManualAwardServiceI;

public class ManualAwardServiceImpl implements ManualAwardServiceI{
	
	@Autowired
	private ManualAwardMapper manualawardmapper;
	@Override
	public int insert(ManualAward record) {
		return manualawardmapper.insert(record);
	}
	@Override
	public ManualAward selectone(String actno) {
		return manualawardmapper.selectone(actno);
	}
	@Override
	public List<ManualAward> selectAll(String actno) {
		return manualawardmapper.selectAll(actno);
	}
	@Override
	public List<ManualAward> selectlist() {
		return manualawardmapper.selectlist();
	}
	
	/**
     * pxl  查找ManualAward表中的备注（领奖时的详细收货地址）
     */
	public List<ManualAward> getRemarkById(String actno) {
		return manualawardmapper.getRemarkById(actno);
	}
	 /**
     * @author pxl
     * @date 2016-11-12
     * 根据活动编号查找到手动颁奖设置活动
     */
	public ManualAward getmanualaward(String actno) {
		return manualawardmapper.getmanualaward(actno);
	}

	 /**
     * @author pxl
     * @date 2016-11-24
     * 将手动颁奖部分数据保存到子活动表 ManualAward中
     */
	public int insertSelective(ManualAward manualAward) {
		return manualawardmapper.insertSelective(manualAward);
	}
	
	 /**
     * @author pxl
     * @date 2016-11-28
     * 根据主活动编号删除所有的子活动
     */
	public int deleteManualAwardByMactNo(String mactNo) {
		return manualawardmapper.deleteManualAwardByMactNo(mactNo);
	}
	
	 /**
     * @author pxl
     * @date 2016-11-28
     * 根据主活动编号查询到所有的子活动
     */
	public List<ManualAward> getManualAwardList(ManualAward ma) {
		return manualawardmapper.getManualAwardList(ma);
	}
	
	/**
     * @author pxl
     * @date 2016-12-2
     * 根据子活动编号更新相对应的子活动
     */
	public int updateByPrimaryKeySelective(ManualAward ma) {
		return manualawardmapper.updateByPrimaryKeySelective(ma);
	}

	 /**
     * 根据主活动编号查询所有的子活动  并按时间排序
     * @param ma
     * @return
     */
	public ManualAward getManualAwardOrderBySettime(ManualAward ma) {
		return manualawardmapper.getManualAwardOrderBySettime(ma);
	}

	/**
	 * 根据子活动编号删除对应的子活动
	 */
	public int deleteByActno(String actno) {
		return manualawardmapper.deleteByActno(actno);
	}
}
