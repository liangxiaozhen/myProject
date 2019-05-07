package com.ptpl.service;

import com.ptpl.model.AheadRepayMode;
import com.ptpl.model.TenderItem;
import com.ptpl.model.UserTender;
import com.ptpl.model.loanapp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author:liuqh
 * @date:2016年07月14日 15:50:16
 * @description:标的设置
 */
public interface TenderItemServiceI extends BaseService<TenderItem> {
    public List<TenderItem> selectByConditionAndDecorateUgrade(TenderItem condition);

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
     * 通过标号和类型获得对应的标
     *
     * @param ti
     * @return
     */
    public List<TenderItem> getTenderItemBytpro(TenderItem tenderItem);

    public List<TenderItem> getTenderItemBytpro2(TenderItem tenderItem);

    //根据loanid查询信息已投金额信息
    Double selectByloanid(BigDecimal loanid);

    /**
     * @param null
     * @return List<TenderItem>
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
     * 保存新标，更改借款申请状态，保存提前还款方式设置，往单标前端信息表插入数据
     *
     * @author :liuqh
     * @date 2017/6/22 9:52
     */
    int insertItemAndOther(TenderItem tenderItem, AheadRepayMode aheadRepayMode);

    /**
     * 修改标，修改提前还款方式设置
     *
     * @author :liuqh
     * @date 2017/6/22 9:52
     */
    int updateItemAndOther(TenderItem tenderItem, AheadRepayMode aheadRepayMode);


    /**
     * 自动转变为投标中
     *
     * @author :liuqh
     * @date 2017/6/30 18:50
     */
    void updateStatusAndOnAndDownTo();

    /**
     * 自动过期
     *
     * @author :liuqh
     * @date 2017/7/3 9:19
     */
    void updateStatusAndOnAndDownTo2();

    /**
     * 自动流标
     *
     * @author :liuqh
     * @date 2017/6/26 9:48
     */
    void updateStatusAndOnAndDownTo3();


    /**
     * 更新标状态，且更新借款状态
     *
     * @author :liuqh
     * @date 2017/7/1 18:24
     */
    void updateStatusForItemAndLoan(TenderItem tenderItem, loanapp loanapp);


}
