package com.ptpl.service.impl;

import com.ptpl.mapper.*;
import com.ptpl.model.*;
import com.ptpl.service.TenderFrontEndServiceI;
import com.ptpl.web.util.PublicUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/18.
 */
public class TenderFrontEndServiceImpl implements TenderFrontEndServiceI {
    @Autowired
    private TenderFrontEndMapper tenderFrontEndMapper;
    @Autowired
    private TenderItemMapper tenderItemMapper;
    @Autowired
    private TenderFrontEndSingleMapper tenderFrontEndSingleMapper;
    @Autowired
    private LoanTypeObjectQuoteMapper loanTypeObjectQuoteMapper;
    @Autowired
    private UserCommonMaterialMapper userCommonMaterialMapper;

    @Override
    public int deleteByPrimaryKey(BigDecimal id) {
        return tenderFrontEndMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TenderFrontEnd record) {
        return tenderFrontEndMapper.insert(record);
    }

    @Override
    public int insertSelective(TenderFrontEnd record) {
        return tenderFrontEndMapper.insertSelective(record);
    }

    @Override
    public TenderFrontEnd selectByPrimaryKey(BigDecimal id) {
        return tenderFrontEndMapper.selectByPrimaryKey(id);
    }

    @Override
    public TenderFrontEnd selectByInfono(String infono) {
        return tenderFrontEndMapper.selectByInfono(infono);
    }

    @Override
    public int updateByPrimaryKeySelective(TenderFrontEnd record) {
        return tenderFrontEndMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TenderFrontEnd record) {
        return tenderFrontEndMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<TenderFrontEnd> selectByCondition(TenderFrontEnd condition) {
        return tenderFrontEndMapper.selectByCondition(condition);
    }

    @Override
    public String selectLastInfoNo() {
        return tenderFrontEndMapper.selectLastInfoNo();
    }

    @Override
    public String insertFrontAndSingle(TenderFrontEnd tenderFrontEnd, CommonsMultipartFile[] files, Short serialno) throws IOException {
        String data = "fail";
        //来源（1新增，2引用）
        Integer source = tenderFrontEnd.getSource();
        //是否同步存量标:0:不同步   1同步待投标的（同步标状态为1,2,9的标）  2同步待投标的 + 投标中的标的（同步标状态为1,2,3,9的标）
        Short issynchisbid = tenderFrontEnd.getIssynchisbid();
        //如果是引用的则直接保存
        if (source == 2) {
            int iden = tenderFrontEndMapper.insertSelective(tenderFrontEnd);
            if (iden > 0) {
                //1同步待投标的（同步标状态为1,2,9的标）   2同步待投标的 + 投标中的标的（同步标状态为1,2,3,9的标）
                if (issynchisbid == 1 || issynchisbid == 2) {
                    inertTenderFrontEndSingle(tenderFrontEnd, serialno, issynchisbid);
                }
                data = "success";
            }
            //如果是新增的
        } else if (source == 1) {
            //应用范围（1通用，2唯一）
            Short isfixed = tenderFrontEnd.getIsfixed();
            //内容属性（1图片，2文字，3下拉，4标签）
            Short infoattribute = tenderFrontEnd.getInfoattribute();
            //如果是唯一则直接保存
            if (isfixed == 2) {
                if (infoattribute == 1 || infoattribute == 2 || infoattribute == 3) {
                    int iden = tenderFrontEndMapper.insertSelective(tenderFrontEnd);
                    if (iden > 0) {
                        //1同步待投标的（同步标状态为1,2,9的标）   2同步待投标的 + 投标中的标的（同步标状态为1,2,3,9的标）
                        if (issynchisbid == 1 || issynchisbid == 2) {
                            inertTenderFrontEndSingle(tenderFrontEnd, serialno, issynchisbid);
                        }
                        data = "success";
                    }
                } else if (infoattribute == 4) {
                    //系统自动初始化8个标签选项:1，2，3，4，5，6，7
                    String content = "1,2,3,4,5,6,7";
                    tenderFrontEnd.setContent(content);
                    int iden = tenderFrontEndMapper.insertSelective(tenderFrontEnd);
                    if (iden > 0) {
                        //1同步待投标的（同步标状态为1,2,9的标）   2同步待投标的 + 投标中的标的（同步标状态为1,2,3,9的标）
                        if (issynchisbid == 1 || issynchisbid == 2) {
                            inertTenderFrontEndSingle(tenderFrontEnd, serialno, issynchisbid);
                        }
                        data = "success";
                    }
                }
                //如果是通用则
            } else if (isfixed == 1) {
                //如果是文字
                if (infoattribute == 2) {
                    tenderFrontEnd.setContent(tenderFrontEnd.getContent_text());
                    int iden = tenderFrontEndMapper.insertSelective(tenderFrontEnd);
                    if (iden > 0) {
                        //1同步待投标的（同步标状态为1,2,9的标）   2同步待投标的 + 投标中的标的（同步标状态为1,2,3,9的标）
                        if (issynchisbid == 1 || issynchisbid == 2) {
                            inertTenderFrontEndSingle(tenderFrontEnd, serialno, issynchisbid);
                        }
                        data = "success";
                    }
                    //1图片（1图片，2文字，3下拉，4标签）
                } else if (infoattribute == 1) {
                    String newfileName = "";
                    newfileName = PublicUtil.updateFiles(files, newfileName);
                    //去最后的逗号
                    if (newfileName.length() > 0) {
                        newfileName = newfileName.substring(0, newfileName.lastIndexOf(','));
                    }
                    tenderFrontEnd.setContent(newfileName);
                    int iden = tenderFrontEndMapper.insertSelective(tenderFrontEnd);
                    if (iden > 0) {
                        //1同步待投标的（同步标状态为1,2,9的标）   2同步待投标的 + 投标中的标的（同步标状态为1,2,3,9的标）
                        if (issynchisbid == 1 || issynchisbid == 2) {
                            inertTenderFrontEndSingle(tenderFrontEnd, serialno, issynchisbid);
                        }
                        data = "success";
                    }
                } else if (infoattribute == 4) {
                    //把tenderFrontEnd中的Content_label的内容赋值给content
                    tenderFrontEnd.setContent(tenderFrontEnd.getContent_label());
                    int iden = tenderFrontEndMapper.insertSelective(tenderFrontEnd);
                    if (iden > 0) {
                        //1同步待投标的（同步标状态为1,2,9的标）   2同步待投标的 + 投标中的标的（同步标状态为1,2,3,9的标）
                        if (issynchisbid == 1 || issynchisbid == 2) {
                            inertTenderFrontEndSingle(tenderFrontEnd, serialno, issynchisbid);
                        }
                        data = "success";
                    }
                }
            }
        }
        return data;
    }

    @Override
    public String updateFrontAndSingle(TenderFrontEnd tenderFrontEnd, @RequestParam("files") CommonsMultipartFile[] files) throws IOException {
        String data = "fail";
        Short infoattribute = tenderFrontEnd.getInfoattribute();
        if (infoattribute == 1) {
            String newfileName = "";
            newfileName = PublicUtil.updateFiles(files, newfileName);
            //有部分图或全部图被删除同时也有新增图，则走以下代码
            if (!newfileName.equals("")) {
                //去除最后一个逗号
                newfileName = newfileName.substring(0, newfileName.lastIndexOf(','));
                String content = tenderFrontEnd.getContent();
                //用户操作时把图片删除完的那一刻content就为null，然后用户又新增加，但content还是为null，所以在这么情况下就要以下判断
                if (content == null) {
                    content = newfileName;
                } else {
                    content = content + "," + newfileName;
                }
                tenderFrontEnd.setContent(content);
                int iden = tenderFrontEndMapper.updateByPrimaryKeySelective(tenderFrontEnd);
                if (iden > 0) {
                    updateSingleContent(tenderFrontEnd, tenderFrontEnd.getContent());
                    data = "success";
                }
                //删除了一部分或删除全部图片或根本没有改动内容但没新增的图片则走以下代码
            } else {
                if (tenderFrontEnd.getPictitle() == null || tenderFrontEnd.getContent() == null) {
                    //为了避免为空时不保存
                    tenderFrontEnd.setPictitle("");
                    tenderFrontEnd.setContent("");
                }
                int iden = tenderFrontEndMapper.updateByPrimaryKeySelective(tenderFrontEnd);
                if (iden > 0) {
                    updateSingleContent(tenderFrontEnd, tenderFrontEnd.getContent());
                    data = "success";
                }
            }
            //文本
        } else if (infoattribute == 2) {
            int iden = tenderFrontEndMapper.updateByPrimaryKeySelective(tenderFrontEnd);
            if (iden > 0) {
                updateSingleContent(tenderFrontEnd, tenderFrontEnd.getContent());
                data = "success";
            }
            //下拉
        } else if (infoattribute == 3) {
            int iden = tenderFrontEndMapper.updateByPrimaryKeySelective(tenderFrontEnd);
            if (iden > 0) {
                updateSingleContent(tenderFrontEnd, tenderFrontEnd.getContent());
                data = "success";
            }
        } else if (infoattribute == 4) {
            int iden = tenderFrontEndMapper.updateByPrimaryKeySelective(tenderFrontEnd);
            if (iden > 0) {
                updateSingleContent(tenderFrontEnd, tenderFrontEnd.getContent());
                data = "success";
            }
        }
        return data;
    }

    @Override
    public String updateInfoname(TenderFrontEnd tenderFrontEnd) {
        String data = "fail";
        int iden = tenderFrontEndMapper.updateByPrimaryKeySelective(tenderFrontEnd);
        if (iden > 0) {
            BigDecimal ttypeid = tenderFrontEnd.getTtypeid();
            //根据标类型id找标类型对象
            LoanTypeObjectQuote loanTypeObjectQuote = loanTypeObjectQuoteMapper.selectByPrimaryKey(ttypeid);
            //得到标类型对象序号(1-30)
            Short serialno = loanTypeObjectQuote.getSerialno();
            TenderItem tenderItem = new TenderItem();
            tenderItem.setTpro(serialno);
            //通过标类型查找还没有发标的标
            List<TenderItem> tenderItems = tenderItemMapper.getTenderItemBytpro(tenderItem);
            int size = tenderItems.size();
            if (size > 0) {
                for (int i = 0; i < size; i++) {
                    TenderFrontEndSingle tenderFrontEndSingle = new TenderFrontEndSingle();
                    tenderFrontEndSingle.setTno(tenderItems.get(i).getTno());
                    tenderFrontEndSingle.setInfono(tenderFrontEnd.getInfono());
                    //通过标编号与标前端信息的编号找出相应的单标前端对象进行更改
                    List<TenderFrontEndSingle> tenderFrontEndSingles = tenderFrontEndSingleMapper.selectByCondition(tenderFrontEndSingle);
                    TenderFrontEndSingle tenderFrontEndSingle1 = tenderFrontEndSingles.get(0);
                    if (tenderFrontEndSingle1 != null) {
                        tenderFrontEndSingle1.setInfoname(tenderFrontEnd.getInfoname());
                        tenderFrontEndSingleMapper.updateByPrimaryKeySelective(tenderFrontEndSingle1);
                    }
                }
            }
            data = "success";
        }
        return data;
    }

    @Override
    public String deleteFrontAndSingle(TenderFrontEnd tenderFrontEnd) {
        String data = "fail";
        int iden = tenderFrontEndMapper.deleteByPrimaryKey(tenderFrontEnd.getId());
        if (iden > 0) {
            BigDecimal ttypeid = tenderFrontEnd.getTtypeid();
            //根据标类型id找标类型对象
            LoanTypeObjectQuote loanTypeObjectQuote = loanTypeObjectQuoteMapper.selectByPrimaryKey(ttypeid);
            //得到标类型对象序号(1-30)
            Short serialno = loanTypeObjectQuote.getSerialno();
            TenderItem tenderItem = new TenderItem();
            tenderItem.setTpro(serialno);
            //通过标类型查找还没有发标的标
            List<TenderItem> tenderItems = tenderItemMapper.getTenderItemBytpro(tenderItem);
            int size = tenderItems.size();
            if (size > 0) {
                for (int i = 0; i < size; i++) {
                    TenderFrontEndSingle tenderFrontEndSingle = new TenderFrontEndSingle();
                    tenderFrontEndSingle.setTno(tenderItems.get(i).getTno());
                    tenderFrontEndSingle.setInfono(tenderFrontEnd.getInfono());
                    List<TenderFrontEndSingle> tenderFrontEndSingles = tenderFrontEndSingleMapper.selectByCondition(tenderFrontEndSingle);
                    if (tenderFrontEndSingles != null && tenderFrontEndSingles.size() > 0) {
                        TenderFrontEndSingle tenderFrontEndSingle1 = tenderFrontEndSingles.get(0);
                        tenderFrontEndSingleMapper.deleteByPrimaryKey(tenderFrontEndSingle1.getId());
                    }
                }
            }
            data = "success";
        }
        return data;
    }

    @Override
    public String updateForImport(String liqno,TenderFrontEnd tenderFrontEnd) {
        String data = "fail";
        //把引用的公共资料编号作为标的前端信息内容保存
        tenderFrontEnd.setContent(liqno);
        tenderFrontEndMapper.updateByPrimaryKeySelective(tenderFrontEnd);
        //根据资料编号infono查找单标前端信息
        TenderFrontEndSingle tenderFrontEndSingle = new TenderFrontEndSingle();
        String infono = tenderFrontEnd.getInfono();
        tenderFrontEndSingle.setInfono(infono);
        List<TenderFrontEndSingle> tenderFrontEndSingles = tenderFrontEndSingleMapper.selectByCondition(tenderFrontEndSingle);
        if (tenderFrontEndSingles != null && tenderFrontEndSingles.size() > 0) {
            for (TenderFrontEndSingle s : tenderFrontEndSingles) {
                //根据标编号找出对应的标对象
                String tno = s.getTno();
                TenderItem tenderItem1 = tenderItemMapper.seltendbytno(tno);
                //根据标id找出借款申请对象，再根据借款申请对象找到申请人，再根据申请人找到申请人所填写的公共资料
                List<UserCommonMaterial> userCommonMaterials = userCommonMaterialMapper.selectByTenderitemId(tenderItem1.getId());
                //如果用户填写了公共资料
                if (userCommonMaterials != null && userCommonMaterials.size() > 0) {
                    for (UserCommonMaterial userc : userCommonMaterials) {
                        String liqno1 = userc.getLiqno();
                        //筛选出与传过来的公共资料编号一样的的资料
                        if (liqno.equals(liqno1)) {
                            if (userc.getMaterialpic() != null && !userc.getMaterialpic().equals("")) {
                                //图片
                                s.setInfoattribute((short) 1);
                                s.setPictitle(userc.getMaterialname());
                                s.setContent(userc.getMaterialpic());
                            } else {
                                //文字
                                s.setInfoattribute((short) 2);
                                s.setContent(userc.getMaterialcontent());
                            }
                            int i = tenderFrontEndSingleMapper.updateByPrimaryKeySelective(s);
                            if (i > 0) {
                                data = "success";
                            }
                        }
                    }
                }
            }
        }
        return data;
    }

    /**
     * 向单标前端信息表插入数据
     *
     * @author :liuqh
     * @date :2017/7/5 11:36
     */
    private void inertTenderFrontEndSingle(TenderFrontEnd tenderFrontEnd, Short serialno, Short issynchisbid) {
        TenderItem tenderItem = new TenderItem();
        tenderItem.setTpro(serialno);
        //通过标类型查找标状态为1,2,9的标(目的是取到标的tno)
        List<TenderItem> tenderItems = new ArrayList<TenderItem>();
        if (issynchisbid == 1) {
            tenderItems = tenderItemMapper.getTenderItemBytpro(tenderItem);
        } else if (issynchisbid == 2) {
            tenderItems = tenderItemMapper.getTenderItemBytpro2(tenderItem);
        }
        int size = tenderItems.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                TenderFrontEndSingle single = new TenderFrontEndSingle();
                //获取当前标的标项目最大序号
                Integer sno = tenderFrontEndSingleMapper.selectMaxSno(tenderItems.get(i).getTno());
                if (sno == null) {
                    sno = 1;
                } else {
                    sno = (Integer) (sno + 1);
                }
                single.setSno(sno);//排序号
                single.setTno(tenderItems.get(i).getTno());//标号
                single.setInfono(tenderFrontEnd.getInfono());//编号
                single.setInfoname(tenderFrontEnd.getInfoname());//项目名称(资料)
                single.setSource(tenderFrontEnd.getSource());//来源（新增，引用)
                single.setTtypename(tenderFrontEnd.getTtypename());//标的类型名称
                single.setInfotype(tenderFrontEnd.getInfotype());//资料类别
                single.setInfoattribute(tenderFrontEnd.getInfoattribute());//内容属性（图片，文字，下拉，标签）
                Short Infoattribute=tenderFrontEnd.getInfoattribute();
                Short isfixed = tenderFrontEnd.getIsfixed();
                if(Infoattribute!=null&&isfixed==2&&(Infoattribute==3||Infoattribute==4)){
                    single.setContent(null);//内容
                }else {
                    single.setContent(tenderFrontEnd.getContent());//内容
                }
                single.setPictitle(tenderFrontEnd.getPictitle());
                single.setIsfixed(tenderFrontEnd.getIsfixed());
                //通用则显示
                if (tenderFrontEnd.getIsfixed() == 1) {
                    //显示
                    single.setIsdisplay((short) 1);
                    //唯一则不显示
                } else if (tenderFrontEnd.getIsfixed() == 2) {
                    //不显示
                    single.setIsdisplay((short) 0);
                }
                tenderFrontEndSingleMapper.insertSelective(single);
            }
        }
    }

    /**
     * 同步更新单标前端信息的content字段和pictitle字段
     *
     * @author :liuqh
     * @date :2017/7/5 11:40
     */
    private void updateSingleContent(TenderFrontEnd tenderFrontEnd, String content) {
        BigDecimal ttypeid = tenderFrontEnd.getTtypeid();
        //根据标类型id找标类型对象
        LoanTypeObjectQuote loanTypeObjectQuote = loanTypeObjectQuoteMapper.selectByPrimaryKey(ttypeid);
        //得到标类型对象序号(1-30)
        Short serialno = loanTypeObjectQuote.getSerialno();
        TenderItem tenderItem = new TenderItem();
        tenderItem.setTpro(serialno);
        //通过标类型查找还没有发标的标
        List<TenderItem> tenderItems = tenderItemMapper.getTenderItemBytpro(tenderItem);
        int size = tenderItems.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                TenderFrontEndSingle tenderFrontEndSingle = new TenderFrontEndSingle();
                tenderFrontEndSingle.setTno(tenderItems.get(i).getTno());
                tenderFrontEndSingle.setInfono(tenderFrontEnd.getInfono());
                List<TenderFrontEndSingle> tenderFrontEndSingles = tenderFrontEndSingleMapper.selectByCondition(tenderFrontEndSingle);
                if (tenderFrontEndSingles != null && tenderFrontEndSingles.size() > 0) {
                    TenderFrontEndSingle tenderFrontEndSingle1 = tenderFrontEndSingles.get(0);
                    tenderFrontEndSingle1.setContent(content);
                    tenderFrontEndSingle1.setPictitle(tenderFrontEnd.getPictitle());
                    tenderFrontEndSingleMapper.updateByPrimaryKeySelective(tenderFrontEndSingle1);
                }
            }
        }
    }
}
