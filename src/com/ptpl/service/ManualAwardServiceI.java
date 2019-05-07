package com.ptpl.service;

import java.util.List;

import com.ptpl.model.ManualAward;

public interface ManualAwardServiceI {
	
	//添加手动颁奖数据
	int insert(ManualAward record);
	
	//根据编号查询出一条数据（只要一条就够了）
    ManualAward selectone(String actno);
    
    //根据编号查询出所有
    List<ManualAward> selectAll (String actno);
    
    //查出手动颁奖中所有是模板的数据每个活动编号相同的取一条
    List<ManualAward> selectlist();
    
    /**
     * @author pxl 
     * 查找ManualAward表中的备注（领奖时的详细收货地址）
     */
    List<ManualAward> getRemarkById(String actno);
    
    /**
     * @author pxl
     * @date 2016-11-12
     * 根据活动编号查找到手动颁奖设置活动
     */
    ManualAward getmanualaward(String actno);
    
    /**
     * @author pxl
     * @date 2016-11-24
     * 将手动颁奖部分数据保存到子活动表 ManualAward中
     */
    int insertSelective(ManualAward manualAward);
    
    /**
     * @author pxl
     * @date 2016-11-28
     * 根据主活动编号删除所有的子活动
     */
    int deleteManualAwardByMactNo(String mactNo);
    
    /**
     * @author pxl
     * @date 2016-11-28
     * 根据主活动编号查询到所有的子活动
     */
    List<ManualAward> getManualAwardList(ManualAward ma);
    
    /**
     * @author pxl
     * @date 2016-12-2
     * 根据子活动编号更新相对应的子活动
     */
    int updateByPrimaryKeySelective(ManualAward ma);
    
    /**
     * 根据主活动编号查询所有的子活动  并按时间排序
     * @param ma
     * @return
     */
	ManualAward getManualAwardOrderBySettime(ManualAward ma);

	/**
	 * 根据子活动编号删除对应的子活动
	 * @param actno
	 * @return
	 */
	int deleteByActno(String actno);
}
