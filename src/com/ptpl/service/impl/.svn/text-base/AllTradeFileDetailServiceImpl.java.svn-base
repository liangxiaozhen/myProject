package com.ptpl.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.StringUtil;
import com.huishang.util.HSFileUtil;
import com.huishang.util.HSignUtil;
import com.huishang.util.RSAUtils;
import com.ptpl.mapper.AllTradeFileDetailMapper;
import com.ptpl.model.AllTradeFileDetail;
import com.ptpl.model.BacthFileRecord;
import com.ptpl.service.AllTradeFileDetailServiceI;

import net.sf.json.JSONObject;

public class AllTradeFileDetailServiceImpl implements AllTradeFileDetailServiceI {

	@Autowired
	private AllTradeFileDetailMapper allTradeFileDetailMapper;

	@Override
	public int deleteByPrimaryKey(BigDecimal id) {
		return allTradeFileDetailMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(AllTradeFileDetail record) {
		return allTradeFileDetailMapper.insert(record);
	}

	@Override
	public int insertSelective(AllTradeFileDetail record) {
		return allTradeFileDetailMapper.insertSelective(record);
	}

	@Override
	public AllTradeFileDetail selectByPrimaryKey(BigDecimal id) {
		return allTradeFileDetailMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<AllTradeFileDetail> listAllTradeFileDetail(AllTradeFileDetail record) {
		return allTradeFileDetailMapper.listAllTradeFileDetail(record);
	}

	@Override
	public int updateByPrimaryKeySelective(AllTradeFileDetail record) {
		return allTradeFileDetailMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(AllTradeFileDetail record) {
		return allTradeFileDetailMapper.updateByPrimaryKey(record);
	}

	@SuppressWarnings("finally")
	@Override
	public boolean allTradeFileDetailDeal(BacthFileRecord bacthFileRecord) {
		boolean flag = false;
		String filePath = bacthFileRecord.getFilePath();// 上传路径
		String getFileName = bacthFileRecord.getGetFileName();// 下载文件名称;
		Short isDealResult = bacthFileRecord.getIsDealResult();// 是否已处理结果文件
		Short fileType = bacthFileRecord.getFileType();// 文件类型
		if (StringUtil.isEmpty(filePath) || StringUtil.isEmpty(getFileName)) {
			return flag;
		}
		if (isDealResult == null || isDealResult != 0) {
			return flag;
		}
		if (fileType != 6 || fileType == null) {
			return flag;
		}
		try {
			flag = HSFileUtil.downFile(filePath, getFileName,
					getFileName.substring(getFileName.length() - 8, getFileName.length()));
			if (flag)
				flag = insertAllTradeFileDetailFromFile(filePath, getFileName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return flag;
		}
	}

	/**
	 * 处理全流水文件 插入数据库
	 * 
	 * @author 作者 xiaoy:
	 * @version 创建时间：2017年5月15日 上午11:38:17
	 * @param filePath
	 * @param getFileName
	 * @return
	 * @throws IOException
	 */
	private boolean insertAllTradeFileDetailFromFile(String filePath, String getFileName) throws IOException {
		File file = new File(filePath, getFileName);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));
		String line = null;
		while ((line = br.readLine()) != null) {
			String bank = line.substring(0, 4).trim();// 银行号
			String cardnbr = line.substring(4, 23).trim();// 电子账号;
			String amount = line.substring(23, 40).trim();// 交易金额
			String cur_num = line.substring(40, 43).trim();// 货币代码
			String crflag = line.substring(43, 44).trim();// 交易金额符号
			String valdate = line.substring(44, 52).trim();// 入账日期
			String inpdate = line.substring(52, 60).trim();// 交易日期
			String reldate = line.substring(60, 68).trim();// 自然日期
			String inptime = line.substring(68, 76).trim();// 交易时间
			String tranno = line.substring(76, 82).trim();// 交易流水号
			String oriTranno = line.substring(82, 88).trim();// 关联交易流水号
			String transtype = line.substring(88, 92).trim();// 交易类型
			String desline = line.substring(92, 134).trim();// 交易描述
			String currBal = null;
			String forcardnbr = null;
			String revind = null;
			String resv = null;
			int index = desline.indexOf("0");
			if (index == -1) {
				currBal = line.substring(134, 151).trim();// 交易后余额
				forcardnbr = line.substring(151, 170).trim();// 对手交易账号
				revind = line.substring(170, 171).trim();// 冲正，撤销标志
				resv = line.substring(171, line.length()).trim();// 保留域
			} else {
				desline = desline.substring(0, index);
				index += 92;
				currBal = line.substring(index, index + 17).trim();// 交易后余额
				index += 17;
				forcardnbr = line.substring(index, index + 19).trim();// 对手交易账号
				index += 19;
				revind = line.substring(index, index + 1).trim();// 冲正，撤销标志
				index += 1;
				resv = line.substring(index, line.length()).trim();// 保留域
			}

			System.out.println("银行号=" + bank);
			System.out.println("电子账号=" + cardnbr);
			System.out.println("交易金额=" + amount);
			System.out.println("货币代码=" + cur_num);
			System.out.println("交易金额符号=" + crflag);
			System.out.println("入账日期=" + valdate);
			System.out.println("交易日期=" + inpdate);
			System.out.println("自然日期=" + reldate);
			System.out.println("交易时间=" + inptime);
			System.out.println("交易流水号=" + tranno);
			System.out.println("关联交易流水号=" + oriTranno);
			System.out.println("交易类型=" + transtype);
			System.out.println("交易描述=" + desline);
			System.out.println("交易后余额=" + currBal);
			System.out.println("对手交易账号=" + forcardnbr);
			System.out.println("冲正，撤销标志=" + revind);
			System.out.println("保留域=" + resv);
			System.out.println("===============================================");

			AllTradeFileDetail atfd = new AllTradeFileDetail();
			atfd.setTranno(tranno);// 系统跟踪号
			atfd.setReldate(reldate);
			;// 交易传输时间
			int size = listAllTradeFileDetail(atfd).size();
			if (size == 0) {
				atfd.setBank(bank);
				atfd.setCardnbr(forcardnbr);
				atfd.setAmount(new BigDecimal(amount));
				atfd.setCurNum(Integer.valueOf(cur_num));
				atfd.setCrflag(crflag);
				atfd.setValdate(valdate);
				atfd.setInpdate(inpdate);
				atfd.setReldate(reldate);
				atfd.setInptime(inptime);
				atfd.setTranno(oriTranno);
				atfd.setOriTranno(oriTranno);
				atfd.setTranstype(transtype);
				atfd.setDesline(desline);
				atfd.setCurrBal(new BigDecimal(currBal));
				atfd.setForcardnbr(forcardnbr);
				atfd.setRevind(revind.equals("1") ? (short) 1 : (short) 0);
				atfd.setResv(resv);
				atfd.setFilename(getFileName);
				atfd.setEntertime(new Date());
				insertSelective(atfd);
			}
		}
		br.close();
		return true;
	}

}
