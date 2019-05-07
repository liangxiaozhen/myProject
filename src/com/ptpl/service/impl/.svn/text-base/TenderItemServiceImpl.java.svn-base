package com.ptpl.service.impl;

import com.ptpl.constant.Loanapp_Constant;
import com.ptpl.constant.TenderItem_Constant;
import com.ptpl.mapper.*;
import com.ptpl.model.*;
import com.ptpl.service.TenderItemServiceI;
import com.ptpl.web.util.PublicUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author:liuqh
 * @date:2016年07月14日 15:50:16
 * @description:标的设置
 */
public class TenderItemServiceImpl extends BaseServiceImpl<TenderItem> implements TenderItemServiceI {
    @Autowired
    private UserGradeMapper userGrade;
    @Autowired
    private RemoveNameMapper removeName;
    @Autowired
    private TenderItemMapper tenderItemMapper;

    @Autowired
    private LoanTypeObjectQuoteMapper loanTypeObjectQuoteMapper;
    @Autowired
    private TenderFrontEndMapper tenderFrontEndMapper;
    @Autowired
    private TenderFrontEndSingleMapper tenderFrontEndSingleMapper;
    @Autowired
    private UserCommonMaterialMapper usermapper;
    @Autowired
    private loanappMapper loanappmapper;
    @Autowired
    private AheadRepayModeMapper aheadRepayModeMapper;
    @Autowired
    private UserTenderMapper userTenderMapper;

    @SuppressWarnings("unchecked")
    @Override
    public List<TenderItem> selectByConditionAndDecorateUgrade(TenderItem condition) {
        return PublicUtil.decorateList(selectByCondition(condition), userGrade, removeName, null);
    }

    @Override
    public TenderItem findTenderItemById(BigDecimal TenderItemId) {
        return tenderItemMapper.findTenderItemById(TenderItemId);
    }

    @Override
    public int insertSelective(TenderItem item) {
        return tenderItemMapper.insertSelective(item);
    }

    @Override
    public List<TenderItem> findTenderItemByCondition(Map<String, Object> maps) {
        return tenderItemMapper.findTenderItemByCondition(maps);
    }

    @Override
    public int updatebytno(TenderItem itTenderItem) {
        return tenderItemMapper.updatebytno(itTenderItem);
    }

    @Override
    public TenderItem seltendbytno(String tno) {
        return tenderItemMapper.seltendbytno(tno);
    }

    @Override
    public List<TenderItem> getTenderItemBytpro(TenderItem tenderItem) {
        return tenderItemMapper.getTenderItemBytpro(tenderItem);
    }


    @Override
    public List<TenderItem> getTenderItemBytpro2(TenderItem tenderItem) {
        return tenderItemMapper.getTenderItemBytpro2(tenderItem);
    }

    @Override
    public Double selectByloanid(BigDecimal loanid) {
        return tenderItemMapper.selectByloanid(loanid);
    }

    @Override
    public List<TenderItem> callbackdesk() {
        return tenderItemMapper.callbackdesk();
    }

    @Override
    public List<TenderItem> callbackdeskAll() {
        return tenderItemMapper.callbackdeskAll();
    }

    @Override
    public List<TenderItem> getMapConditionsList(Map<String, Object> conditions) {
        return tenderItemMapper.getMapConditionsList(conditions);
    }

    @Override
    public TenderItem callbackByid(BigDecimal id) {
        return tenderItemMapper.callbackByid(id);
    }

    @Override
    public List<TenderItem> selectByConditionnotin(TenderItem tenderItem) {
        return tenderItemMapper.selectByConditionnotin(tenderItem);
    }

    @Override
    public List<TenderItem> selectByAlltend(TenderItem tenderItem) {
        return tenderItemMapper.selectByAlltend(tenderItem);
    }

    @Override
    public List<TenderItem> getTenderItemsLeftJoinLoanAppByCondition(Map<String, Object> hashMap) {
        return tenderItemMapper.getTenderItemsLeftJoinLoanAppByCondition(hashMap);
    }

    /**
     * 保存新标，更改借款申请状态，保存提前还款方式设置，往单标前端信息表插入数据
     *
     * @author :liuqh
     * @date 2017/7/3 16:04
     */
    @Override
    public int insertItemAndOther(TenderItem tenderItem, AheadRepayMode aheadRepayMode) {
        int t = tenderItemMapper.insert(tenderItem);
        if (t > 0) {
            if (aheadRepayMode != null) {
                aheadRepayMode.setTid(tenderItem.getId());
                aheadRepayModeMapper.insertSelective(aheadRepayMode);//保存提前还款方式设置
            }
            int l = updateLoanapp(tenderItem);// 更改申请借款的状态为建标完成
            if (l > 0) {
                int i = insertSingleByNewTenderitem(tenderItem);//同时往单标前端信息表插入
                return i;
            }
            return l;
        }
        return t;
    }

    @Override
    public int updateItemAndOther(TenderItem tenderItem, AheadRepayMode aheadRepayMode) {
        int t = tenderItemMapper.update(tenderItem);
        if (t > 0) {
            //设置提前还款
            if (tenderItem.getIsaaheadrepay() == 1) {
                List<AheadRepayMode> aheadRepayModes = aheadRepayModeMapper.selectModeBytid(tenderItem.getId());
                int i = 0;
                //增加
                if (aheadRepayModes == null || aheadRepayModes.size() == 0) {
                    i = aheadRepayModeMapper.insertSelective(aheadRepayMode);//保存提前还款方式设置
                    //修改
                } else if (aheadRepayModes != null && aheadRepayModes.size() > 0) {
                    i = aheadRepayModeMapper.updateByPrimaryKeySelective(aheadRepayMode);//保存提前还款方式设置
                }
                System.out.println(i);
                return i;
                //不设置提前还款
            } else if (tenderItem.getIsaaheadrepay() == 0) {
                List<AheadRepayMode> aheadRepayModes = aheadRepayModeMapper.selectModeBytid(tenderItem.getId());
                if (aheadRepayModes != null && aheadRepayModes.size() > 0) {
                    //删除
                    int i = aheadRepayModeMapper.deleteByTid(tenderItem.getId());
                    System.out.println(i);
                    return i;
                }
            }
        }
        return t;
    }

    /**
     * 自动转变为投标中
     *
     * @author :liuqh
     * @date 2017/6/30 18:50
     */
    @Override
    public void updateStatusAndOnAndDownTo() {
        List<TenderItem> tenderItems = tenderItemMapper.selectByDateAndTstatus(new Date());//查询状态为待投标且在投标期的标（为了做任务调度：自动转变为投标中）
        if (tenderItems != null && tenderItems.size() > 0) {
            for (TenderItem t : tenderItems) {
                t.setTstatus(TenderItem_Constant.T3);//投标中
                if (t.getOnanddown() == 0) {
                    t.setOnanddown((short) 1);//已上架
                    t.setPutontime(new Date());
                }
                tenderItemMapper.update(t);
                loanapp loanapp = new loanapp();
                loanapp.setId(t.getLoanappid());
                loanapp.setAppstatus(Loanapp_Constant.T6);//投标中
                loanappmapper.updateByPrimaryKeySelective(loanapp);
            }
        }
    }

    /**
     * 自动过期
     *
     * @author :liuqh
     * @date 2017/7/3 9:19
     */
    @Override
    public void updateStatusAndOnAndDownTo2() {
        List<TenderItem> tenderItems = tenderItemMapper.selectByDateAndTstatus2(new Date());//查询状态为待录入且过期的标（为了做任务调度：自动过期）
        if (tenderItems != null && tenderItems.size() > 0) {
            for (TenderItem t : tenderItems) {
                t.setTstatus(TenderItem_Constant.T10);//录入过期
                tenderItemMapper.update(t);
                loanapp loanapp = new loanapp();
                loanapp.setId(t.getLoanappid());
                loanapp.setAppstatus(Loanapp_Constant.T13);//投标中
                loanappmapper.updateByPrimaryKeySelective(loanapp);
            }
        }
    }

    /**
     * 自动流标
     *
     * @author :liuqh
     * @date 2017/7/3 12:37
     */
    @Override
    public void updateStatusAndOnAndDownTo3() {
        List<TenderItem> tenderItems = tenderItemMapper.selectByDateAndTstatus3(new Date());//查询状态为投标中且过期的标（为了做任务调度：自动流标）
        if (tenderItems != null && tenderItems.size() > 0) {
            for (TenderItem t : tenderItems) {
                List<UserTender> tenderList = userTenderMapper.findTenderByTstatusAndTenderid(t.getId());
                if (tenderList != null && tenderList.size() > 0) {

                } else {
                    loanapp loanapp = loanappmapper.selectByPrimaryKey(t.getLoanappid());
                    t.setTstatus(TenderItem_Constant.T4);//已流标
                    t.setOnanddown((short) 2);//已下架
                    t.setPullofftime(new Date());
                    loanapp.setAppstatus(Loanapp_Constant.T7);//已流标
                    updateStatusForItemAndLoan(t, loanapp);
                }
            }
        }
    }


    @Override
    public void updateStatusForItemAndLoan(TenderItem tenderItem, loanapp loanapp) {
        tenderItemMapper.update(tenderItem);
        loanappmapper.updateByPrimaryKeySelective(loanapp);
    }


    /**
     * 更改申请借款的状态为建标完成
     *
     * @author :liuqh
     * @date 2017/6/22 12:03
     */

    private int updateLoanapp(TenderItem tenderItem) {
        loanapp loanapp = loanappmapper.selectByPrimaryKey(tenderItem.getLoanappid());
        loanapp.setAppstatus(Loanapp_Constant.T4);//1.待审核  2.审核失败  3.待建标  4.待录入  5.待投标  6.投标中  7.已流标  8.待放款  9.待生成还款计划  10.还款中  11.已完成
        loanapp.setTbegintime(tenderItem.getTbegintime());//标的开始时间
        int i = loanappmapper.updateByPrimaryKey(loanapp);
        return i;
    }

    /**
     * 同时往单标前端信息表插入
     *
     * @author :liuqh
     * @date 2017/6/22 9:57
     */
    private int insertSingleByNewTenderitem(TenderItem tenderItem) {
        //标的类型：标的类型对象设置（loanTypeObjectQuote）中的SerialNo（对象序号）
        Short tpro = tenderItem.getTpro();
        //根据序号找标类型对象
        LoanTypeObjectQuote loanTypeObjectQuote = loanTypeObjectQuoteMapper.selectBySerialNo(new BigDecimal(tpro));
        //根据标类型的id找已启用的标前端信息对象
        BigDecimal loanTypeObjectQuoteId = loanTypeObjectQuote.getId();
        TenderFrontEnd tenderFrontEnd = new TenderFrontEnd();
        tenderFrontEnd.setTtypeid(loanTypeObjectQuoteId);
        //启用的标前端信息对象才对新增的标有同步作用
        tenderFrontEnd.setStatus((short) 1);
        List<TenderFrontEnd> tenderFrontEnds = tenderFrontEndMapper.selectByCondition(tenderFrontEnd);
        int size = tenderFrontEnds.size();
        if (size > 0) {
            //同步这个新标的单标前端信息
            for (int i = 0; i < size; i++) {
                //向单标前端信息表插入数据
                TenderFrontEndSingle single = new TenderFrontEndSingle();
                int j = insertSingle(tenderItem, tenderFrontEnds, i, single);
                if (j > 0) {
                    //如果来源是（2引用），把用户公共资料的内容同步到单标前端信息中
                    if (single.getSource() == 2) {
                        int h = fillCommonMaterial(tenderFrontEnds, i, single);
                        if (h < 0) {
                            return h;
                        }
                    }
                } else {
                    return j;
                }
            }
        }
        return 1;
    }

    /**
     * 用户公共资料的内容同步到单标前端信息中
     *
     * @author :liuqh
     * @date 2017/6/22 11:18
     */
    private int fillCommonMaterial(List<TenderFrontEnd> tenderFrontEnds, int i, TenderFrontEndSingle single) {
        String tno = single.getTno();
        TenderItem tenderItem1 = tenderItemMapper.seltendbytno(tno);
        //根据标id找出借款申请对象，再根据借款申请对象找到申请人，再根据申请人找到申请人所填写的公共资料
        List<UserCommonMaterial> userCommonMaterials = usermapper.selectByTenderitemId(tenderItem1.getId());
        //如果用户填写了公共资料
        if (userCommonMaterials != null && userCommonMaterials.size() > 0) {
            for (UserCommonMaterial userc : userCommonMaterials) {
                String liqno = userc.getLiqno();
                String content = tenderFrontEnds.get(i).getContent();
                //标的前端信息来源为引用且有引用过的
                if (content != null && !content.equals("")) {
                    //标的前端信息内容与公共资料编号一样
                    if (content.equals(liqno)) {
                        if (userc.getMaterialpic() != null && !userc.getMaterialpic().equals("")) {
                            //图片
                            single.setInfoattribute((short) 1);
                            single.setContent(userc.getMaterialpic());
                            single.setPictitle(userc.getMaterialname());
                        } else {
                            //文字
                            single.setInfoattribute((short) 2);
                            single.setContent(userc.getMaterialcontent());
                        }
                        int i1 = tenderFrontEndSingleMapper.updateByPrimaryKeySelective(single);
                        return i1;
                    }
                }
            }
        }
        return 1;
    }

    /**
     * 向单标前端信息表插入数据
     *
     * @author :liuqh
     * @date 2017/6/22 11:19
     */
    private int insertSingle(TenderItem tenderItem, List<TenderFrontEnd> tenderFrontEnds, int i, TenderFrontEndSingle single) {
        single.setSno(i + 1);//排序号
        single.setTno(tenderItem.getTno());//标号
        single.setInfono(tenderFrontEnds.get(i).getInfono());//编号
        single.setInfoname(tenderFrontEnds.get(i).getInfoname());//项目名称(资料)
        single.setSource(tenderFrontEnds.get(i).getSource());//来源（新增，引用)
        single.setTtypename(tenderFrontEnds.get(i).getTtypename());//标的类型名称
        single.setInfotype(tenderFrontEnds.get(i).getInfotype());//资料类别
        single.setInfoattribute(tenderFrontEnds.get(i).getInfoattribute());//内容属性（图片，文字，下拉，标签）
        Short Infoattribute=tenderFrontEnds.get(i).getInfoattribute();
        Short isfixed = tenderFrontEnds.get(i).getIsfixed();
        if(Infoattribute!=null&&isfixed==2&&(Infoattribute==3||Infoattribute==4)){
            single.setContent(null);//内容
        }else {
            single.setContent(tenderFrontEnds.get(i).getContent());//内容
        }
        single.setPictitle(tenderFrontEnds.get(i).getPictitle());
        single.setIsfixed(tenderFrontEnds.get(i).getIsfixed());
        //通用则显示
        if (tenderFrontEnds.get(i).getIsfixed() == 1) {
            //显示
            single.setIsdisplay((short) 1);
            //唯一则不显示
        } else if (tenderFrontEnds.get(i).getIsfixed() == 2) {
            //不显示
            single.setIsdisplay((short) 0);
        }
        int j = tenderFrontEndSingleMapper.insertSelective(single);
        return j;
    }
}
