package com.ptpl.job;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang3.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.huishang.util.HSignUtil;
import com.huishang.util.RSAUtils;
import com.ptpl.constant.ActAward_Constant;
import com.ptpl.constant.Session_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.controller.huishang.model.Trqtres;
import com.ptpl.model.AccInExRecord;
import com.ptpl.model.ActivityAwardList;
import com.ptpl.model.AdminUser;
import com.ptpl.model.Award;
import com.ptpl.model.BacthFileRecord;
import com.ptpl.model.RedEnveLopeItem;
import com.ptpl.model.UserAccount;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.model.UserRedEnvelope;
import com.ptpl.service.AccInExRecordServiceI;
import com.ptpl.service.ActivityAwardListServiceI;
import com.ptpl.service.AwardServiceI;
import com.ptpl.service.BacthFileRecordServiceI;
import com.ptpl.service.QueryBlaneServiceI;
import com.ptpl.service.RedEnveLopeItemServiceI;
import com.ptpl.service.UserAccountServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.service.UserRedEnvelopeServiceI;
import com.ptpl.web.util.StringUtil;

import net.sf.json.JSONObject;

public class UserRedHttpDownJob extends QuartzJobBean{

	@Autowired
	@Qualifier("activityAwardListService")
	private ActivityAwardListServiceI activityAwardListService;//获奖名单表
	@Autowired
	@Qualifier("userFsAccountInfoService")
	private UserFsAccountInfoServiceI userFsAccountInfoService;//用户托管账户信息Service
	@Autowired
	@Qualifier("redEnveLopeItemService")
	private RedEnveLopeItemServiceI redEnveLopeItemService;//红包发放对账记录表
	@Autowired
	@Qualifier("userAccountService")
	private UserAccountServiceI userAccountService;//用户账户表
	@Autowired
	@Qualifier("accInExRecordService")
	private AccInExRecordServiceI accInExRecordService;//账户收支记录表
	@Autowired
	@Qualifier("userRedEnvelopeService")
	private UserRedEnvelopeServiceI userRedEnvelopeService;//用户红包Service
	@Autowired
	@Qualifier("awardService")
	private AwardServiceI awardService;//奖品
	@Autowired
	@Qualifier("bacthFileRecordService")
    private BacthFileRecordServiceI bacthFileRecordService;//批量文件记录表
	@Autowired
	@Qualifier("queryBlaneService")
	private QueryBlaneServiceI queryBlaneService;//余额查询
	
	//String uri = "http://localhost:8080/ptpAPI/ptproute/file/downloadEncryptWithSign.action";//红包批量文件转账的路由地址
	
	public void executeInternal(JobExecutionContext context) throws JobExecutionException{
		
		System.out.println("===进来了    红包转账定时器===");
		
		
		//从批量文件记录表中查找出已成功发送，没有处理的记录
		BacthFileRecord bfr = new BacthFileRecord();
		bfr.setCoinstCode(/*"800114"*/HSignUtil.COINSTCODE);//平台编号
		bfr.setFileType((short)2);//业务文件类型  1.开户   2.红包转账  3....
		bfr.setSendResult((short)1);//发送结果（是否成功）   0.失败    1.成功   
		bfr.setIsDealResult((short)0);//是否已处理结果文件     0.否   1.是
		List<BacthFileRecord> bfrList = bacthFileRecordService.getBacthFileRecords(bfr);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		int size = bfrList.size();
		HttpClient httpClient = null;
		PostMethod method = null;
		Map<String, Object> params = new HashMap<String, Object>();
		for(int i=0;i<size;i++){
			
			params.put("coinstCode", HSignUtil.COINSTCODE); // 合作单位编号
			params.put("bankCode", HSignUtil.BANKCODE); // 银行代码
			String sendFileName = bfrList.get(i).getSendFileName();
			System.out.println("===上传的文件名==="+sendFileName);
			String dataStr = sendFileName.substring(sendFileName.lastIndexOf("-")+1);
			System.out.println("==交易日期=="+dataStr);
			params.put("transDate", dataStr); // 交易日期
			params.put("fileName", bfrList.get(i).getGetFileName());//下载文件的名称
			JSONObject paramsJson = JSONObject.fromObject(params);
			String pam = paramsJson.toString();
			method = new PostMethod(HSignUtil.BATCHRESURL);//通过路由进行文件下载
			method.getParams().setContentCharset("GBK");
			method.setParameter("parameters", pam);
			httpClient = new HttpClient();
			try{
				
				httpClient.executeMethod(method);
				String responseMap = method.getResponseBodyAsString();
				
				System.out.println("responseMap: "+responseMap);
				Map map = JSONObject.fromObject(responseMap);
				String return_code = (String) map.get("return_code");
				System.out.println("====return_code==="+return_code);
				String sign = (String) map.get("sign");
				String decryptKeyPath = HSignUtil.DECRYPTPATH; // 拼接解密私钥路径
				
				
				if(return_code.equals("0000")){
					
					String decryptKey4Server = RSAUtils.getSignPrivateKey4Client(decryptKeyPath);
					String signdecrypt = new String(Hex.encodeHex(RSAUtils.decryptRSAByte(sign, decryptKey4Server)));
					
					//更新批量文件记录表
					BacthFileRecord b = new BacthFileRecord();
					b.setId(bfrList.get(i).getId());
					b.setDownResultCode(return_code);//下载文件返回码
					b.setDealTime(new Date());//处理文件时间
					b.setIsDealResult((short)1);//是否已处理结果文件
					b.setDealResult((short)1);//处理结果
					bacthFileRecordService.update(b);
					
					String file = (String) map.get("file");
					byte[] _file = file.getBytes("GBK");
					//下载批量文件的路径
					String downPath = "D:"+"/batchfile"+File.separator+HSignUtil.COINSTCODE+HSignUtil.REDTRANSTER;
					System.out.println("==红包下载批量文件路径=="+downPath);
					File newFile = new File(downPath+File.separator+StringUtil.formatDate(new Date(), "yyyyMMdd"));
					File file1 = new File(newFile+File.separator+bfr.getGetFileName());
					File file2 = new File(newFile+File.separator+bfr.getGetFileName()+".BAK");
					String newFileName = file1.getPath();
					//判断文件夹是否存在，不存在就创建
					if(!newFile.exists() && !newFile.isDirectory()){
						newFile.mkdirs();
					}
					FileOutputStream out = new FileOutputStream(newFile+File.separator+bfr.getGetFileName());
					String str = new String(_file,"GBK");
					out.write(str.getBytes());
					String keypath = HSignUtil.ENCRYPTION_FILE;
					BufferedReader bre = new BufferedReader(new FileReader(new File(keypath)));
	                String keyPath = bre.readLine();
	                decryptHand(file1, file2, keyPath);
	                out.flush();
	                out.close();
	                File tmp = new File(file2.getPath() + ".bak");
	                tmp.createNewFile();
	                transFormToTmpFile(file2, tmp);
	                String md5 = new String(Hex.encodeHex(RSAUtils.getFileMD5String(tmp)));

	                if (md5.equals(signdecrypt)) {
	                    System.out.println("文件验签成功！");
	                    System.out.println("摘要内容：" + signdecrypt);
	                } else {
	                    System.out.println("文件验签失败！");
	                    System.out.println("sign摘要内容：" + signdecrypt);
	                    System.out.println("系统计算摘要内容：" + md5);
	                }
	                if (file1.exists()) {
	                    file1.delete();
	                }
	                if (file2.exists()) {
	                    file2.renameTo(new File(newFileName));
	                }
					
					
					FileInputStream fis = new FileInputStream(newFile+File.separator+bfr.getGetFileName());
					BufferedReader br = new BufferedReader(new InputStreamReader(fis,"GBK"));
					String line = null;
					while((line = br.readLine())!=null){
						
						Trqtres t = readRedResult(line);
						if(!"00".equals(t.getResult())){
							System.out.println("处理失败");
							//将该状态改为待处理
							ActivityAwardList a = new ActivityAwardList();
							a.setId(new BigDecimal(t.getRese().trim()));
							a.setStatus((short)2);//发放状态 1.待审核 2.待处理 3.已领取 4.待确认 5.已经确认 6.发货中 7.领取失败  9.(现金红包)待发放
							activityAwardListService.update(a);
							continue;
						}
						
						//开始处理自己的业务逻辑
						//通过获奖名单里面红包的id获取对应的红包记录
						ActivityAwardList aal=activityAwardListService.getselectById(new BigDecimal(t.getRese().trim()));
						//现金红包发放对账记录表
						RedEnveLopeItem reli=new RedEnveLopeItem();
						reli.setOrderno(t.getBatch());//记录表的流水号 对应批次号
						reli.setRedenvelopeno(aal.getAwardno());//红包编号
						reli.setRedenvelopename(aal.getAwardname());//奖品的名称
						reli.setReamount(Float.parseFloat(t.getAmount().toString()));//奖品的金额
						reli.setBaseid(aal.getBaseid());//用户id
						reli.setUsername(aal.getUsername());//用户名
						reli.setBusinesstype(ActAward_Constant.RECTYPE_MAP.get(aal.getAl().getActtype()));//业务类型  5.手动颁奖
						reli.setSendtime(new Date());//处理时间
						reli.setIsblending((short)0);//是否系统勾兑
						reli.setIsmanblending((short)0);//是否人工勾兑
						reli.setPaycompany("徽商银行");//托管通道公司（徽商）
						int rows = redEnveLopeItemService.insert(reli);
						if(rows>0){
							//用户账户表
							/*UserAccount usAc=userAccountService.getUserAccountByBaseId(aal.getBaseid());
							Double balance = usAc.getBalance() + new Double(t.getAmount());
							Double avlbalance = usAc.getAvlbalance() + new Double(t.getAmount());
							Double freezeBalance = usAc.getFreezebalance() ==null?0.00 : usAc.getFreezebalance();
							//汇付转账成功后，才给用户余额进账，更新用户账户表
							usAc.setBalance(balance);
							usAc.setAvlbalance(avlbalance);*/
								
							/*BaseController bc = new BaseController();*/
							
							Map<String,String> r5863 =queryBlaneService.queryBlane(t.getCardnnbr());
							System.out.println("===查询余额==="+r5863);
							String AVAIL_BAL = r5863.get("AVAIL_BAL");//可用余额
							String CURR_BAL = r5863.get("CURR_BAL");//账户余额
							//插入数据
							/*UserAccount usAc = new UserAccount();
							usAc.setBaseid(aal.getBaseid());
							usAc.setBalance(Double.parseDouble(CURR_BAL));//用户总资产
							usAc.setAvlbalance(Double.parseDouble(AVAIL_BAL));//可用余额
							userAccountService.updateUseraccount(usAc);*/
							int iiii = queryBlaneService.queryBlaneAndUpdateUserAccount(t.getCardnnbr(),aal.getBaseid().toString());

							
							//账户收支记录表
							AccInExRecord aier = new AccInExRecord();
							aier.setBaseid(aal.getBaseid());//用户的id
							aier.setAieorderno(StringUtil.getNo());//收支记录流水号
							aier.setBorderno(t.getBatch());//业务流水号(这里放的是批次号)
							aier.setType((short)32);//业务类型   现金
							aier.setInamount(t.getAmount());//收入
							aier.setOutamount(0.00);//支出
							//获取徽商红包账户
							UserFsAccountInfo u = new UserFsAccountInfo();
							u.setAccounttype((short)2);//账户类型  1.个人    2.企业
							u.setAccPurpose((short)2);//账户用途（1普通，2红包，3手续费）
							u.setMercustid(/*"800114"*/HSignUtil.COINSTCODE);//商户客户号
							UserFsAccountInfo userFsAccountInfo=userFsAccountInfoService.getUsrCustId(u);
							
							aier.setPaccount(new BaseController().getDecrypt(userFsAccountInfo.getUsrcustid())/*"9930040050240250015"*/);//平台账户   账户：徽商红包账户
							aier.setPinamount(0.00);//平台账户收入,平台产生的费用
							aier.setPoutamount(0.00);//平台账户支出,平台产生的费用
							aier.setStatus((short)1);//业务状态  0冻结  1成功 2失败
							aier.setDescription("现金红包转账");//说明
							aier.setBalance(/*avlbalance*/Double.parseDouble(AVAIL_BAL));//用户的可用余额
							//aier.setFreebalance(freezeBalance);//用户的冻结余额
							aier.setTotalbalance(/*balance*/Double.parseDouble(CURR_BAL));//用户的总金额
							aier.setRecordtime(new Date());//发生时间
							aier.setRemark("现金红包转账");//备注
							//插入数据
							accInExRecordService.insertSelective(aier);
							
							//插入红包表记录(需要一份一份的插入)
							UserRedEnvelope urel= new UserRedEnvelope();
							urel.setBaseid(aal.getBaseid());//用户ID
							urel.setUreno(aal.getAwardno());//奖品编号
							urel.setRectype(aal.getAl().getActtype());//红包获取方式类型(1.注册  2.累投  3.单标  4.首投 5.手动颁奖 6.自主颁奖 7.完善资料)
							urel.setRetype(aal.getAwardattribute());//红包类型（1.现金，2.类现金，3.假现金）
							urel.setRestime(new Date());//红包发放时间
							urel.setRedenvelope(aal.getAwardmoney());//红包金额
							urel.setStatus((short)3);//奖品发放状态 （1待审核 2待处理 3已领取 4待确认 5已经确认 6发货中 7领取失败 8审核失败）
							urel.setIsuse((short)4);//奖品状态  （1未到期 2可使用 3已冻结 4已使用 5已到期 6已作废）
							//根据奖品的编号获取奖品（到期时间）
							Award a = awardService.selectByAwardNo(aal.getAwardno());
							urel.setRefailtime(a.getEndtime());//到期时间
							if(aal.getAuditman()==null||"".equals(aal.getAuditman())){
								urel.setIsaudit((short)1);//是否审核（0.是，1.否）
							}else{
								urel.setIsaudit((short)0);//是否审核（0.是，1.否）
							}
							urel.setAuditman(aal.getAuditman());//审核人
							urel.setAudittime(aal.getAudittime());//审核时间
							urel.setPurpose("该红包可以直接提现");
							urel.setRemark(aal.getRemark());//备注
							urel.setRedealtime(new Date());//红包的入账时间
							for(int k = 0;k<aal.getAwardquantity();k++){
								int lines = userRedEnvelopeService.insert(urel);
							}
							
							//更新奖品名单的处理人和处理时间 
							ActivityAwardList activityAwardList = new ActivityAwardList();
							activityAwardList.setId(aal.getId());
							activityAwardList.setStatus((short)3);//发放状态 1.待审核 2.待处理 3.已领取 4.待确认 5.已经确认 6.发货中 7.领取失败 9.(现金红包)待发放
//							HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
//							AdminUser au = (AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER);
//							if(au!=null){
//								activityAwardList.setDealman(au.getUsername());//处理人
//							}
							activityAwardList.setDealtime(new Date());//处理时间
							int row = activityAwardListService.update(activityAwardList);
						}
					}	
					
				}else{
					
					System.out.println("===返回码不成功===");
					
					//更新批量文件记录表
					BacthFileRecord bac = new BacthFileRecord();
					bac.setId(bfrList.get(i).getId());
					bac.setDownResultCode(return_code);//下载文件返回码
					bac.setDealTime(new Date());//处理文件时间
					bac.setIsDealResult((short)1);//是否已处理结果文件
					bac.setDealResult((short)0);//处理结果  0.失败  1.成功   
					bacthFileRecordService.update(bac);
					
				}
				//同步平台账户余额
				UserFsAccountInfo u_ = new UserFsAccountInfo();
				u_.setAccounttype((short)2);//账户类型  1.个人    2.企业
				u_.setAccPurpose((short)2);//账户用途（1普通，2红包，3手续费）
				u_.setMercustid(/*"800114"*/HSignUtil.COINSTCODE);//商户客户号
				UserFsAccountInfo userFsAccountInfo_=userFsAccountInfoService.getUsrCustId(u_);
				if(userFsAccountInfo_!=null){
					/*Map<String,String> r58631 = queryBlaneService.queryBlane(BaseController.getDecrypt(userFsAccountInfo_.getUsrcustid()));
					String AVAIL_BAL1 = r58631.get("AVAIL_BAL");//可用余额
					String CURR_BAL1 = r58631.get("CURR_BAL");//账户余额
					//插入数据
					UserAccount usAc1 = new UserAccount();
					usAc1.setBaseid(userFsAccountInfo_.getBaseid());
					usAc1.setBalance(Double.parseDouble(CURR_BAL1));//用户总资产
					usAc1.setAvlbalance(Double.parseDouble(AVAIL_BAL1));//可用余额
					userAccountService.updateUseraccount(usAc1);*/
					int nnnn = queryBlaneService.queryBlaneAndUpdateUserAccount(BaseController.getDecrypt(userFsAccountInfo_.getUsrcustid()),userFsAccountInfo_.getBaseid().toString());

				}
			}catch(Exception e){
				e.printStackTrace();		
			}
		}
		
	}
	
	public Trqtres readRedResult(String line) throws IOException{
			
			Trqtres t = new Trqtres();
			byte[] data = line.getBytes("GBK");
			byte[] BANK = new byte[4];
			System.arraycopy(data, 0, BANK, 0, 4);
			String bank = new String(BANK);
			t.setBank(bank);
			System.out.println(bank);
			
			byte[] BATCH = new byte[6];
			System.arraycopy(data, 4, BATCH, 0, 6);
			String batch = new String(BATCH);
			t.setBatch(batch);
			System.out.println(batch);
			
			byte[] DATE = new byte[8];
			System.arraycopy(data, 10, DATE, 0, 8);
			String date = new String(DATE);
			t.setDate(date);
			System.out.println(date);
			
			byte[] TYPE = new byte[3];
			System.arraycopy(data, 18, TYPE, 0, 3);
			String type = new String(TYPE);
			t.setType(type);
			System.out.println(type);
			
			byte[] CARDNNBR = new byte[19];
			System.arraycopy(data, 21, CARDNNBR, 0, 19);
			String cardnnbr = new String(CARDNNBR);
			t.setCardnnbr(cardnnbr);
			System.out.println(cardnnbr);
			
			byte[] AMOUNT = new byte[12];
			System.arraycopy(data, 40, AMOUNT, 0, 12);
			String str = new String(AMOUNT);
			Double amount = new Double(str.substring(0,10)+"."+str.substring(10));
			t.setAmount(amount);
			System.out.println(amount);
			
			byte[] CURR = new byte[3];
			System.arraycopy(data, 52, CURR, 0, 3);
			String curr = new String(CURR);
			t.setCurr(curr);
			System.out.println(curr);
			
			byte[] RESULT = new byte[2];
			System.arraycopy(data, 55, RESULT, 0, 2);
			String result = new String(RESULT);
			t.setResult(result);
			System.out.println(result);
			
			byte[] NAME = new byte[60];
			System.arraycopy(data, 57, NAME, 0, 60);
			String name = new String(NAME,"GBK");
			t.setName(name);
			System.out.println(name);
			
			byte[] TRDRESE = new byte[100];
			System.arraycopy(data, 117, TRDRESE, 0, 100);
			String trdrese = new String(TRDRESE);
			t.setTrdrese(trdrese);
			System.out.println(trdrese);
			
			byte[] RESE = new byte[100];
			System.arraycopy(data, 217, RESE, 0, 100);
			String rese = new String(RESE);
			t.setRese(rese);
			System.out.println(rese);
			
	        return t;
	}
	
	private static void transFormToTmpFile(File f1, File bak)throws IOException {
		BufferedReader  br = null;	
		BufferedWriter bw = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(f1.getPath()),"GBK"));
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream (bak.getPath()),"GBK"));
			String line = "";
			while((line = br.readLine()) != null){
				if(!("").equals(line)){
					bw.write(line);
					bw.flush();
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(bw!=null){
				bw.close();
			}
		}
	}
	
	//初始化密钥
    public static SecretKey getKey(String strKey) {
        try {
            KeyGenerator _generator = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );
            secureRandom.setSeed(strKey.getBytes());
            _generator.init(128,secureRandom);
            return _generator.generateKey();
        }  catch (Exception e) {
            throw new RuntimeException( "初始化密钥出现异常 " );
        }
    }
	
	 /**
     * 解密
     */
    public static byte[] decryptByAES(byte[] content, String keyPath) throws NoSuchAlgorithmException {

        SecretKeySpec key = null;
        try {
            SecretKey secretKey =getKey(keyPath);
            byte[] enCodeFormat = secretKey.getEncoded();
            key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(content);
            return result; // 加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	 /**
     * 将16进制转换为二进制
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
	
	public static void decryptHand(File file1,File file2,String keyPath){
        // String result="";
        String line ="";
        byte[] decryptFrom=null;
        byte[] decryptResult=null;

        try{

            //FileReader reader = new FileReader(file1);
            //FileWriter writer = new FileWriter(file2);
            BufferedReader br = new BufferedReader (new InputStreamReader (new FileInputStream (file1),"GBK"));
            BufferedWriter bw = new BufferedWriter (new OutputStreamWriter (new FileOutputStream (file2),"GBK"));
            //BufferedReader br = new BufferedReader(reader);
            // BufferedWriter bw = new BufferedWriter(writer);
            if((line = br.readLine())!=null){
        		//解密
                String result="";
                decryptFrom =parseHexStr2Byte(result+line);
                decryptResult = decryptByAES(decryptFrom, keyPath);//逐行解密
                result=new String(decryptResult,"GBK");
                bw.write(result);
        	}
            while((line = br.readLine())!=null){
                //解密
                String result="";
                decryptFrom =parseHexStr2Byte(result+line);
                decryptResult = decryptByAES(decryptFrom, keyPath);//逐行解密
                result=new String(decryptResult,"GBK");
                bw.newLine();
                bw.write(result);
            }
            bw.flush();
            bw.close();
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

	public ActivityAwardListServiceI getActivityAwardListService() {
		return activityAwardListService;
	}

	public void setActivityAwardListService(ActivityAwardListServiceI activityAwardListService) {
		this.activityAwardListService = activityAwardListService;
	}

	public UserFsAccountInfoServiceI getUserFsAccountInfoService() {
		return userFsAccountInfoService;
	}

	public void setUserFsAccountInfoService(UserFsAccountInfoServiceI userFsAccountInfoService) {
		this.userFsAccountInfoService = userFsAccountInfoService;
	}

	public RedEnveLopeItemServiceI getRedEnveLopeItemService() {
		return redEnveLopeItemService;
	}

	public void setRedEnveLopeItemService(RedEnveLopeItemServiceI redEnveLopeItemService) {
		this.redEnveLopeItemService = redEnveLopeItemService;
	}

	public UserAccountServiceI getUserAccountService() {
		return userAccountService;
	}

	public void setUserAccountService(UserAccountServiceI userAccountService) {
		this.userAccountService = userAccountService;
	}

	public AccInExRecordServiceI getAccInExRecordService() {
		return accInExRecordService;
	}

	public void setAccInExRecordService(AccInExRecordServiceI accInExRecordService) {
		this.accInExRecordService = accInExRecordService;
	}

	public UserRedEnvelopeServiceI getUserRedEnvelopeService() {
		return userRedEnvelopeService;
	}

	public void setUserRedEnvelopeService(UserRedEnvelopeServiceI userRedEnvelopeService) {
		this.userRedEnvelopeService = userRedEnvelopeService;
	}

	public AwardServiceI getAwardService() {
		return awardService;
	}

	public void setAwardService(AwardServiceI awardService) {
		this.awardService = awardService;
	}

	public BacthFileRecordServiceI getBacthFileRecordService() {
		return bacthFileRecordService;
	}

	public void setBacthFileRecordService(BacthFileRecordServiceI bacthFileRecordService) {
		this.bacthFileRecordService = bacthFileRecordService;
	}

	public QueryBlaneServiceI getQueryBlaneService() {
		return queryBlaneService;
	}

	public void setQueryBlaneService(QueryBlaneServiceI queryBlaneService) {
		this.queryBlaneService = queryBlaneService;
	}

}
