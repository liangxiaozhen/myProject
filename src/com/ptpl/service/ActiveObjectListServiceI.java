package com.ptpl.service;

import java.math.BigDecimal;
import java.util.List;

import com.ptpl.model.ActiveObjectList;

public interface ActiveObjectListServiceI {
	/**根据Id删除操作
	 * @author lihs
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(BigDecimal id);

    /**
     * 添加操作
     * @author lihs
     * @param record
     * @return
     */
    int insert(ActiveObjectList record);

    /**
     * 选择性添加操作
     * @param record
     * @return
     */
    int insertSelective(ActiveObjectList record);

    /**
     * 根据id查对象操作
     * @author lihs
     * @param id
     * @return
     */
    ActiveObjectList selectByPrimaryKey(BigDecimal id);
    /**
     * 选择性的修改操作
     * @author lihs
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(ActiveObjectList record);
	/**
	 * 修改操作
	 * @author lihs
	 * @param record
	 * @return
	 */
    int updateByPrimaryKey(ActiveObjectList record);
    /**
     * 根据奖品编号拿取信息
     * @author lihs
     * @param record
     * @return
     */
    List<ActiveObjectList> getlist(String actno);
    /**
     * 模板中查看获奖信息所用
     * @author lihs
     * @param record
     * @return
     */
    ActiveObjectList getuseradmin(ActiveObjectList record );
    
    /**
     * @author pxl
     * @date 2016-11-28
     * 根据子活动编号删除相对应的活动对象
     */
    int deleteActiveObject(String actno);

    /**
     * 根据定向名单列表的id获得对应的业务对象名单
     * @param id
     * @return
     */
	List<ActiveObjectList> selectBySNLId(BigDecimal id);
    
	/**
	 * 根据定向名单的id删除对应的业务对象名单
	 */
	int deleteBySNLId(BigDecimal id);

	//根据名单类型来获取多条记录
	List<ActiveObjectList> selectActiveObjectLists(ActiveObjectList aos);
	
}
