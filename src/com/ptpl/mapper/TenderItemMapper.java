package com.ptpl.mapper;

import com.ptpl.model.TenderItem;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author:liuqh
 * @date:2016年07月14日 15:50:16
 * @description:标的设置
 */
public interface TenderItemMapper extends BaseMapper<TenderItem> {
    /**
     * @param @param  TenderItemId
     * @param @return 参数说明
     * @return TenderItem    返回类型
     * @throws
     * @Title: findTenderItemById
     * @Description: TODO(根据标ID 查找标信息)
     * @author cjm
     */
    public TenderItem findTenderItemById(BigDecimal TenderItemId);

    /**
     * @param @param  maps
     * @param @return 参数说明
     * @return TenderItem    返回类型
     * @throws
     * @Title: findTenderItemByCondition
     * @Description: TODO(条件查找标的信息)
     * @author cjm
     */
    public List<TenderItem> findTenderItemByCondition(Map<String, Object> maps);

    //新增
    public int insertSelective(TenderItem item);

    //根据标号跟新信息
    public int updatebytno(TenderItem itTenderItem);

    public TenderItem seltendbytno(String tno);

    /**
     * 根据类型和标状态查询（标状态为1,2,9的标）
     *
     * @param
     * @return
     */
    public List<TenderItem> getTenderItemBytpro(TenderItem tenderItem);

    /**
     * 根据类型和标状态查询（标状态为1,2,3,9的标）
     *
     * @param
     * @return
     */
    public List<TenderItem> getTenderItemBytpro2(TenderItem tenderItem);

    //根据loanid查询信息已投金额信息
    Double selectByloanid(BigDecimal loanid);

    /**
     * @param
     * @return List<TenderItem> 返回之前段页面
     * @author liuj
     */
    List<TenderItem> callbackdesk();

    List<TenderItem> callbackdeskAll();

    /**
     * @param @param  maps tenderItem
     * @param @return 参数说明
     * @return List<TenderItem>   返回类型
     * @throws
     * @Title: getMapConditionsList
     * @Description: TODO(根据筛选条件筛选数据)
     * @author liuj
     */
    List<TenderItem> getMapConditionsList(Map<String, Object> conditions);

    //根据返回的id查询信息
    TenderItem callbackByid(BigDecimal id);

    //获取带审核的标的
    List<TenderItem> selectByConditionnotin(TenderItem tenderItem);

    List<TenderItem> selectByAlltend(TenderItem tenderItem);


    List<TenderItem> getTenderItemsLeftJoinLoanAppByCondition(Map<String, Object> hashMap);

    /**
     * 查询状态为待投标且在投标期的标（为了做任务调度：自动转变为投标中）
     *
     * @author :liuqh
     * @date 2017/6/28 10:45
     */
    List<TenderItem> selectByDateAndTstatus(Date time);

    /**
     * 查询状态为待录入且过期的标（为了做任务调度：自动过期）
     *
     * @author :liuqh
     * @date 2017/7/3 9:18
     */
    List<TenderItem> selectByDateAndTstatus2(Date time);

    /**
     *  查询状态为投标中且过期的标（为了做任务调度：自动流标）
     * @author :liuqh
     * @date 2017/7/3 15:27
     */
    List<TenderItem> selectByDateAndTstatus3(Date time);

}