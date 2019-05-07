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
import com.ptpl.mapper.TradeFileDetailMapper;
import com.ptpl.model.BacthFileRecord;
import com.ptpl.model.TradeFileDetail;
import com.ptpl.service.TradeFileDetailServiceI;

import net.sf.json.JSONObject;

public class TradeFileDetailServiceImpl implements TradeFileDetailServiceI {
	@Autowired
	private TradeFileDetailMapper tradeFileDetailMapper;

	@Override
	public int deleteByPrimaryKey(BigDecimal id) {
		return tradeFileDetailMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(TradeFileDetail record) {
		return tradeFileDetailMapper.insert(record);
	}

	@Override
	public int insertSelective(TradeFileDetail record) {
		return tradeFileDetailMapper.insertSelective(record);
	}

	@Override
	public TradeFileDetail selectByPrimaryKey(BigDecimal id) {
		return tradeFileDetailMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TradeFileDetail> listTradeFileDetail(TradeFileDetail record) {
		return tradeFileDetailMapper.listTradeFileDetail(record);
	}

	@Override
	public int updateByPrimaryKeySelective(TradeFileDetail record) {
		return tradeFileDetailMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TradeFileDetail record) {
		return tradeFileDetailMapper.updateByPrimaryKey(record);
	}

	@SuppressWarnings("finally")
	@Override
	public boolean tradeFileDetailDeal(BacthFileRecord bacthFileRecord) {
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
		if (fileType != 5 || fileType == null) {
			return flag;
		}
		try {
			flag = HSFileUtil.downFile(filePath, getFileName,
					getFileName.substring(getFileName.length() - 8, getFileName.length()));
			if (flag)
				flag = insertTradeFileDetailFromFile(filePath, getFileName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return flag;
		}
	}

	/**
	 * 插入数据库
	 * 
	 * @author 作者 xiaoy:
	 * @version 创建时间：2017年5月13日 下午6:45:23
	 * @param filePath
	 * @param fileName
	 * @throws IOException
	 */
	private  boolean insertTradeFileDetailFromFile(String filePath, String fileName) throws IOException {
		File file = new File(filePath, fileName);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"GBK"));
		String line = null;
		while ((line = br.readLine()) != null) {
			String acqcode = line.substring(0, 11).trim();// 受理方标识
			String seqno = line.substring(11, 17).trim();//系统跟踪号
			String cendt=line.substring(17,27).trim();//交易传输时间
			String cardnbr=line.substring(27,46).trim();//交易主账号
			String amount = line.substring(46,58).trim();//交易金额
			String crflag=line.substring(58,59).trim();//交易金额符号
			String msgtype=line.substring(59,63).trim();//消息类型
			String proccode=line.substring(63,69).trim();//交易类型码
			String mertype=line.substring(69,73).trim();//商户类型
			String term=line.substring(73,81).trim();//受卡机终端标识码
			String retseqno=line.substring(81,93).trim();//检索参考号
			String conmode=line.substring(93,95).trim();//服务点条件码
			String autresp =line.substring(95,101).trim();//授权应答码
			String forcode=line.substring(101,112).trim();//发送方标识码
			String clrdate =line.substring(112,116).trim();//清算日期
			String oldseqno=line.substring(116,122).trim();//原始交易的系统跟踪号
			String openbrno=line.substring(122,128).trim();//发卡网点号
			String tranbrno=line.substring(129,134).trim();//交易网点
			String ervind=line.substring(134,135).trim();//冲正，撤销标志
			String transtype=line.substring(135,139).trim();//主机交易类型
			
			System.out.println("受理方标识="+acqcode);
			System.out.println("系统跟踪号="+seqno);
			System.out.println("交易传输时间="+cendt);
			System.out.println("交易主账号="+cardnbr);
			System.out.println("交易金额="+amount);
			System.out.println("交易金额符号="+crflag);
			System.out.println("消息类型="+msgtype);
			System.out.println("交易类型码="+proccode);
			System.out.println("商户类型="+mertype);
			System.out.println("受卡机终端标识码="+term);
			System.out.println("检索参考号="+retseqno);
			System.out.println("服务点条件码="+conmode);
			System.out.println("授权应答码="+autresp);
			System.out.println("发送方标识码="+forcode);
			System.out.println("清算日期="+clrdate);
			System.out.println("原始交易的系统跟踪号="+oldseqno);
			System.out.println("发卡网点号="+openbrno);
			System.out.println("交易网点="+tranbrno);
			System.out.println("冲正，撤销标志="+ervind);
			System.out.println("主机交易类型="+transtype);
			System.out.println("===============================================");
			
			TradeFileDetail tfd=new TradeFileDetail();
			tfd.setSeqno(seqno);//系统跟踪号
			tfd.setCendt(cendt);//交易传输时间
			int size=listTradeFileDetail(tfd).size();
			if(size==0){
				tfd.setAcqcode(acqcode);
				tfd.setCardnbr(cardnbr);
				tfd.setAmount(new BigDecimal(amount));
				tfd.setCrflag(crflag);
				tfd.setMsgtype(msgtype);
				tfd.setProccode(proccode);
				tfd.setMertype(mertype);
				tfd.setTerm(term);
				tfd.setRetseqno(retseqno);
				tfd.setConmode(conmode);
				tfd.setAutresp(autresp);
				tfd.setForcode(forcode);
				tfd.setClrdate(clrdate);
				tfd.setOldseqno(oldseqno);
				tfd.setOpenbrno(openbrno);
				tfd.setTranbrno(tranbrno);
				tfd.setErvind(ervind.equals("1")?(short)1:(short)0);
				tfd.setTranstype(transtype);
				tfd.setFilename(fileName);
				tfd.setEntertime(new Date());
				insertSelective(tfd);
			}
		}
		br.close();
		return true;
	}

}
