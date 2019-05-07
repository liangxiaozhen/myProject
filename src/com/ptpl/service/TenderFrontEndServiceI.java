package com.ptpl.service;

import com.ptpl.model.TenderFrontEnd;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2017/5/18.
 */
public interface TenderFrontEndServiceI {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(TenderFrontEnd record);

    int insertSelective(TenderFrontEnd record);

    TenderFrontEnd selectByPrimaryKey(BigDecimal id);

    TenderFrontEnd selectByInfono(String infono);

    int updateByPrimaryKeySelective(TenderFrontEnd record);

    int updateByPrimaryKey(TenderFrontEnd record);

    List<TenderFrontEnd> selectByCondition(TenderFrontEnd condition);

    //获取最后一个编号
    String selectLastInfoNo();

    String insertFrontAndSingle(TenderFrontEnd tenderFrontEnd, CommonsMultipartFile[] files, Short serialno) throws IOException;

    String updateFrontAndSingle(TenderFrontEnd tenderFrontEnd, @RequestParam("files") CommonsMultipartFile[] files) throws IOException;

     String updateInfoname(TenderFrontEnd tenderFrontEnd);

    String deleteFrontAndSingle(TenderFrontEnd tenderFrontEnd);

    String updateForImport(String liqno,TenderFrontEnd tenderFrontEnd);

    }


