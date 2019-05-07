package com.ptpl.controller.manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
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
import javax.sound.midi.Synthesizer;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huishang.util.HSignUtil;
import com.huishang.util.RSAUtils;
import com.ptpl.constant.ActAward_Constant;
import com.ptpl.constant.BacthFileRecord_Constant;
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
import com.ptpl.service.ThirdRepayMentDealI;
import com.ptpl.service.UserAccountServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.service.UserRedEnvelopeServiceI;
import com.ptpl.web.util.StringUtil;

import net.sf.json.JSONObject;

/**
 * 批量文件（徽商）
 */
@Controller
@RequestMapping("/admin/batchFile")
public class BacthFileRecordController {
	
	@Autowired
	private BacthFileRecordServiceI bacthFileRecordService;//批量文件记录表
	@Autowired
	private ActivityAwardListServiceI activityAwardListService;//获奖人
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
	private ThirdRepayMentDealI thirdRepayMentDeal;//第三方支付/银行  还款处理
	@Autowired
	private QueryBlaneServiceI queryBlaneService;//余额查询
	@Autowired
	private UserFsAccountInfoServiceI userFsAccountInfoService;//用户托管账户信息Service
	
	
	@RequestMapping("/queryList")
	public ModelAndView selectBacthFileRecordByCondition(HttpServletRequest request,BacthFileRecord bfr){
		
		// 处理分页请求
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		int num = 1;
		int size = 9;
		if (pageNum != null && !"".equals(pageNum)) {
			num = Integer.parseInt(pageNum);
		}
		if (pageSize != null && !"".equals(pageSize)) {
			size = Integer.parseInt(pageSize);
		}
		String sortString = "id.desc";
		Order.formString(sortString);
		PageHelper.startPage(num, size);
		
		List<BacthFileRecord> bfrList = bacthFileRecordService.getAllBacthFileRecord(bfr);
		PageInfo<BacthFileRecord> pagehelper = new PageInfo<BacthFileRecord>(bfrList);
		int lastPageNum = 0;
		if (pagehelper.getTotal() % size == 0) {
			lastPageNum = (int) pagehelper.getTotal() / size;
		} else {
			lastPageNum = (int) pagehelper.getTotal() / size + 1;
		}
		pagehelper.setLastPage(lastPageNum);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("pagehelper",pagehelper);
		mv.addObject("bfr",bfr);
		mv.addObject("businesstype",BacthFileRecord_Constant.BUSINESSTYPE);//业务文件类型
		mv.addObject("sendbank",BacthFileRecord_Constant.SENDBANK);//是否已发送到银行
		mv.addObject("sendresult",BacthFileRecord_Constant.SENDRESULT);//发送结果（是否成功）
		mv.addObject("isdealfile",BacthFileRecord_Constant.ISDEALFILE);//是否已处理结果文件 
		//mv.addObject("dealresult",BacthFileRecord_Constant.DEALRESULT);//处理结果（是否成功）
		mv.setViewName("admin/bacthFileRecord/bacthFileRecord_list");
		return mv;
	}
	
	@RequestMapping("/viewDetails")
	public ModelAndView viewDetails(BigDecimal id){
		
		BacthFileRecord bfr = bacthFileRecordService.getBacthFileRecordById(id);
		System.out.println("bfr: "+bfr);
		ModelAndView mv = new ModelAndView();
		mv.addObject("bfr",bfr);
		mv.addObject("businesstype",BacthFileRecord_Constant.BUSINESSTYPE);//业务文件类型
		mv.addObject("sendbank",BacthFileRecord_Constant.SENDBANK);//是否已发送到银行
		mv.addObject("sendresult",BacthFileRecord_Constant.SENDRESULT);//发送结果（是否成功）
		mv.addObject("isdealfile",BacthFileRecord_Constant.ISDEALFILE);//是否已处理结果文件 
		mv.addObject("dealresult",BacthFileRecord_Constant.DEALRESULT);//处理结果（是否成功）
		mv.setViewName("admin/bacthFileRecord/bacthFileRecord_detail");
		return mv;
	}
	
	//手动上传文件
	@RequestMapping("/uploadFile")
	public void uploadFile(HttpServletRequest request,HttpServletResponse response,BigDecimal id) throws Exception{
		
		System.out.println("id: "+id);
		AdminUser au = (AdminUser)request.getSession().getAttribute(Session_Constant.ADMINUSER);
		Map<String,String> map = new HashMap<String,String>();
		//根据id查询出记录
		BacthFileRecord bfr = bacthFileRecordService.getBacthFileRecordById(id);
		//根据文件的路径来获取对应的日期
		String dateStr = bfr.getSendFileName().substring(bfr.getSendFileName().length()-8);
		HttpClient httpClient = new HttpClient();
		int lines = 0;
		if(bfr!=null){
			if(bfr.getIsSend()==0){
				//文件没有发送到银行，需要手动上传文件
				Short fileType = bfr.getFileType();//业务文件类型
				System.out.println("业务文件类型fileType："+fileType);
				if(fileType==2){
					//红包转账
					Map<String,Object> params = new HashMap<String,Object>();
					params.put("coinstCode", /*"800114"*/HSignUtil.COINSTCODE);
					params.put("bankCode", /*"30040000"*/HSignUtil.BANKCODE);
					params.put("transDate", dateStr);//交易日期
					params.put("fileName",bfr.getSendFileName());//文件名称
					params.put("fileType", BacthFileRecord_Constant.RENTRANSFER);//文件类型
					
					File file1 = new File(bfr.getFilePath()+bfr.getSendFileName());
					//String md5encryptionPath = "D:"+File.separator+"wskey"+File.separator+800114+File.separator+"encryptions_.cer";
					String md5encryptionPath = HSignUtil.ENCRYPTPATH;
					//根据公钥文件读取验签公钥
					String encryptionKey4Server = RSAUtils.getVerifyKey4Client(md5encryptionPath);
					System.out.println("验签公钥："+encryptionKey4Server);
					System.out.println("file1的path： "+file1.getPath()); //D:\\upload\20170408\3004-TRQT-800114-993281-20170408.TXT
					File bak = new File(file1.getPath()+".bak");
					bak.createNewFile();//创建文件名称
					System.out.println("bak的路径： "+bak.getPath());
					transFormToTmpFile(file1, bak);//file1文件转换成  .bak
					//进行摘要并对摘要进行加密
					String encryptData = RSAUtils.encryptRSAByte(RSAUtils.getFileMD5String(bak),encryptionKey4Server);
					params.put("sign", encryptData);
					
					JSONObject paramsJson = JSONObject.fromObject(params);
					String pam = paramsJson.toString();
					StringPart sp = new StringPart("parameters",pam);
					sp.setCharSet("GBK");//这里要设置字符编码，不然会乱码
					
					File  file2 = new File(bfr.getFilePath()+bfr.getSendFileName()+".txt");
					//String keypath = "D:"+File.separator+"wskey"+File.separator+800114+File.separator+"encryption_file_800114.key";
					String keypath = HSignUtil.ENCRYPTION_FILE;
					BufferedReader bre = bre = new BufferedReader(new FileReader(new File(keypath)));
					String keyPath = bre.readLine();
					
					encryptHand(file1,file2,keyPath);//加密处理  file1转换成 .tmp
					FilePart fp = null;
					if(file2.exists()){
						fp = new FilePart("file",file2);
					}
					
					String md5 = new String(Hex.encodeHex(RSAUtils.getFileMD5String(file1)));
					//渠道上传交易文件接口的URL
					//String uri = "http://192.168.0.108:8080/ptpAPI/userRedBatch/transferAccounts.action";
					PostMethod method = new PostMethod(/*uri*/HSignUtil.BATCREQHURL);
					Part[] parts  = new Part[]{fp,sp};
					method.getParams().setContentCharset("UTF-8");
					method.setRequestEntity(new MultipartRequestEntity(parts, method.getParams()));
					httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
					httpClient.executeMethod(method);
					
					String responseMap = method.getResponseBodyAsString();
					Map m = JSONObject.fromObject(responseMap);
					String return_code = (String)m.get("return_code");
					System.out.println("返回码为： "+return_code);
					bfr.setUpResultCode(return_code);//上传文件的银行返回码
					if(return_code.equals("0000")){
						System.out.println("文件上传成功");
						bfr.setIsSend((short)1);//是否已发送到银行
						bfr.setSendResult((short)1);//发送结果
						bfr.setSubmitTime(new Date());//文件上传时间
						int row = bacthFileRecordService.update(bfr);
						System.out.println("row: "+row);
						//根据上传文件名读取出其中的获奖名单的id
						FileInputStream fis = new FileInputStream(bfr.getFilePath()+bfr.getSendFileName());
						BufferedReader br = new BufferedReader(new InputStreamReader(fis, "GBK"));
						String line = null;
						while((line = br.readLine())!=null){
							byte[] data = line.getBytes("GBK");
							byte[] RESE = new byte[100];
							System.arraycopy(data, 217, RESE, 0, 100);
							String reseId = new String(RESE).trim();
							System.out.println("reseId: "+reseId);
							ActivityAwardList aal = new ActivityAwardList();
							aal.setId(new BigDecimal(reseId));
							aal.setStatus((short)9);// 9.(现金红包)待发放
							aal.setDealman(au.getUsername());//处理人
							aal.setDealtime(new Date());//处理时间
							lines = activityAwardListService.update(aal);
						}
					}
					if(lines > 0){
						map.put("result", "success");
					}else{
						map.put("result", "fail");
					}
				}else if(fileType == 4){
					//到期还款
					boolean flag = thirdRepayMentDeal.repayMentFileUpload(bfr);
					if(flag){
						map.put("result", "success");
					}else{
						map.put("result", "fail");
					}
				}
			}else{
				return;
			}
		}
		String jsonStr = JSON.toJSONString(map);
		PrintWriter pw = response.getWriter();
		pw.println(jsonStr);
		pw.flush();
		pw.close();
	}
	//手动下载文件
	@RequestMapping("/processFile")
	public void processFile(HttpServletResponse response,BigDecimal id) throws Exception{
		
		System.out.println("id: "+id);
		//根据id获取相对应的记录
		HttpClient httpClient = new HttpClient();
		BacthFileRecord bfr = bacthFileRecordService.getBacthFileRecordById(id);
		//根据文件的路径来获取对应的日期
		String dateStr = bfr.getSendFileName().substring(bfr.getSendFileName().length()-8);
		Map<String,String> map = new HashMap<String,String>();
		if(bfr.getSendResult()==1 && bfr.getIsDealResult()==0){
			//文件已发送到银行，但是还没有处理结果
			if(bfr.getFileType()==2){
				//红包转账
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("coinstCode", HSignUtil.COINSTCODE); // 合作单位编号
				params.put("bankCode", HSignUtil.BANKCODE); // 银行代码
				params.put("transDate", dateStr); // 交易日期
				params.put("fileName", bfr.getGetFileName());//下载文件的名称
				JSONObject paramsJson = JSONObject.fromObject(params);
				String pam = paramsJson.toString();
				PostMethod method = new PostMethod(HSignUtil.BATCHRESURL);//通过路由进行文件下载
				method.getParams().setContentCharset("GBK");
				method.setParameter("parameters", pam);
				httpClient.executeMethod(method);
				
				String responseMap = method.getResponseBodyAsString();
				System.out.println("responseMap: "+responseMap);
				Map m = JSONObject.fromObject(responseMap);
				String return_code = (String) m.get("return_code");
				String sign = (String) m.get("sign");
				String decryptKeyPath = HSignUtil.DECRYPTPATH; // 拼接解密私钥路径
				String decryptKey4Server = RSAUtils.getSignPrivateKey4Client(decryptKeyPath);
				String signdecrypt = new String(Hex.encodeHex(RSAUtils.decryptRSAByte(sign, decryptKey4Server)));
				if(return_code.equals("0000")){
					//下载文件成功
					
					String file = (String) m.get("file");
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
	                
					//处理现金红包批量转账业务
					int row = processBatchFile(newFile+File.separator+bfr.getGetFileName());
					System.out.println("row: "+row);
					
					//更新批量文件记录表
					bfr.setDownResultCode(return_code);//下载文件返回码
					bfr.setDealTime(new Date());//处理文件时间
					bfr.setIsDealResult((short)1);//是否已处理结果文件
					bfr.setDealResult((short)1);//处理结果
					bfr.setRemark("红包转账");
					bacthFileRecordService.update(bfr);
					map.put("result", "success");
				}else{
					//更新批量文件记录表
					BacthFileRecord bac = new BacthFileRecord();
					bac.setId(bfr.getId());
					bac.setDownResultCode(return_code);//下载文件返回码
					bac.setDealTime(new Date());//处理文件时间
					bac.setIsDealResult((short)1);//是否已处理结果文件
					bac.setDealResult((short)0);//处理结果  0.失败  1.成功   
					bacthFileRecordService.update(bac);
					map.put("result", "fail");
				}
			}else if(bfr.getFileType()==4){
				//到期还款
				boolean flag = thirdRepayMentDeal.repayMentFileDeal(bfr);
				if(flag){
					map.put("result", "success");
				}else{
					map.put("result", "fail");
				}
			}
		}else{
			map.put("result", "fail");
		}
		
		String jsonStr = JSON.toJSONString(map);
		PrintWriter pw = response.getWriter();
		pw.println(jsonStr);
		pw.flush();
		pw.close();
	}
	
	//手动处理批量文件成功后，处理现金红包批量转账业务
	public int processBatchFile(String filePath) throws Exception{
		
		FileInputStream fis = new FileInputStream(filePath);
		BufferedReader br = new BufferedReader(new InputStreamReader(fis, "GBK"));
		String line = null;
		int row = 0;
		while((line = br.readLine())!=null){
			
			Trqtres t = readRedResult(line);
			if(!"00".equals(t.getResult())){
				System.out.println("该条记录处理失败");
				//将该条记录的状态改为待处理
				ActivityAwardList a = new ActivityAwardList();
				a.setId(new BigDecimal(t.getRese().trim()));
				a.setStatus((short)2);//改为待处理状态
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
					
				//BaseController bc = new BaseController();
				Map<String,String> r5863 = /*bc.queryBanlance(t.getCardnnbr())*/queryBlaneService.queryBlane(t.getCardnnbr());
				String AVAIL_BAL= r5863.get("AVAIL_BAL");//可用余额
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
				HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
				AdminUser au = (AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER);
				if(au!=null){
					activityAwardList.setDealman(au.getUsername());//处理人
				}
				activityAwardList.setDealtime(new Date());//处理时间
				int r = activityAwardListService.update(activityAwardList);
				row+=r;
			}
			
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
		
		return row;
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
	
	//加密,读取旧的文件的内容加密后写入新的文件
	public static String encryptHand(File file1,File file2, String keyPath){
        String result="";
        String line = "";
        byte[] bt;
        String encryptResultStr="";
        try {
            BufferedReader br = new BufferedReader (new InputStreamReader (new FileInputStream (file1),"GBK"));
            BufferedWriter bw = new BufferedWriter (new OutputStreamWriter (new FileOutputStream (file2),"GBK"));
            if((line=br.readLine())!=null){
        		bt=encryptByAES(result + line, keyPath);//逐行加密
                encryptResultStr =parseByte2HexStr(bt);
                bw.write(encryptResultStr);
        	}
            while((line=br.readLine())!=null){
                bt=encryptByAES(result + line, keyPath);//逐行加密
                encryptResultStr =parseByte2HexStr(bt);
                bw.newLine();
                bw.write(encryptResultStr);
            }
            bw.newLine();
            bw.flush();
            bw.close();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
	
	/**
     * 加密
     */
    public static byte[] encryptByAES(String content, String keyPath) throws NoSuchAlgorithmException {

        SecretKeySpec key = null;
        try {
            SecretKey secretKey =getKey(keyPath);
            byte[] enCodeFormat = secretKey.getEncoded();
            key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            byte[] byteContent = content.getBytes("GBK");
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            return result; // 加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
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
     * 将二进制转换成16进制
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
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
	
}
