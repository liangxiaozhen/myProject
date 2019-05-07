package com.ptpl.controller.huifu;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huifu.util.SignUtils;
import com.ptpl.controller.BaseController;
import com.ptpl.model.RedEnveLopeItem;
import com.ptpl.model.TrfReconciliation;
import com.ptpl.model.TrfReconciliationDto;
import com.ptpl.service.RedEnveLopeItemServiceI;
import com.ptpl.web.util.HttpClientUtil;

/**
 * 现金红包对账（汇付）
 * @author admin
 *
 */

@Controller
@RequestMapping("/admin/huifuBlend")
public class HuifuUserRedBlendController extends BaseController{

	@Autowired
	private RedEnveLopeItemServiceI redEnveLopeItemService;
	
	//现金红包转账  系统勾兑
	@RequestMapping("/redEnvelopeSysBlend")
	public void userRedSysBlend() throws Exception{
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String edate = format.format(new Date());
			
		//根据传过来的id来获取数据
		RedEnveLopeItem ri = new RedEnveLopeItem();
		ri.setIsblending((short)0);//是否系统勾兑  0.未勾兑  1.已勾兑
		//ri.setIsmanblending((short)0);//是否人工勾兑   0.未勾兑  1.已勾兑
		List<RedEnveLopeItem> rList = redEnveLopeItemService.selectByCondition(ri);
		if(rList.size() > 0){
			
			String version = "10";//版本号
			String cmdId = "TrfReconciliation";//消息类型
			String merCustId = "6000060004166478";//商户客户号
			String beginDate = "20161215";//开始时间
			String endDate = edate;//结束时间
			String pageNum = "1";
			String pageSize = "200";
			String dataStr = version+cmdId+merCustId+beginDate+endDate+pageNum+pageSize;
			String chkValue = SignUtils.encryptByRSA(dataStr);
			
			Map<String,String> map = new HashMap<String,String>();
			map.put("Version", version);//版本号
			map.put("CmdId", cmdId);//消息类型
			map.put("MerCustId", merCustId);//商户客户号
			map.put("BeginDate", beginDate);//开始时间
			map.put("EndDate", endDate);//结束时间
			map.put("PageNum", pageNum);//页数
			map.put("PageSize",pageSize);//每页记录数
			map.put("ChkValue", chkValue);//签名
			
			//提交数据
			HttpClientUtil http = new HttpClientUtil();
			String resultStr = http.doPost(map);
			System.out.println("系统对账数据包：   "+resultStr);
			JSONObject json = JSON.parseObject(resultStr);//应该是将json字符串转换成json对象
			System.out.println("json:  "+json);
			
			TrfReconciliation tr =JSON.toJavaObject(json, TrfReconciliation.class);
			System.out.println("返回的参数为：   "+tr);
			
			boolean flag = false;
			flag = SignUtils.verifyByRSA(tr.getPlainText(), tr.getChkValue());
			System.out.println("flag:  "+flag);
			
			if(flag && "000".equals(tr.getRespCode())){
				
				List<TrfReconciliationDto> trdList = tr.getTrfReconciliationDtoList();
				
				for(TrfReconciliationDto trd :trdList){
					System.out.println("返回的二级参数：   "+trd);
					//根据订单号查询本地记录
					String orderNo = trd.getOrdId();//订单号
					String transAmt = trd.getTransAmt();//交易金额
					System.out.println("订单号：   "+ orderNo);
					RedEnveLopeItem reli=redEnveLopeItemService.getByOrderNo(orderNo);
					if(reli!=null){
						
						System.out.println(df1.format(reli.getReamount()));
						
						boolean flag1 = transAmt.equals(df1.format(reli.getReamount()));
						String remark = "";
						if(!flag1){
							remark+="转账金额异常";
						}
						
						RedEnveLopeItem r = new RedEnveLopeItem();
						r.setSysbtime(new Date());//系统勾兑时间
						r.setId(reli.getId());//红包对账记录表id
						System.out.println("系统是否勾兑：  "+reli.getIsblending());
						if(reli.getIsblending()==0){//表明系统未勾兑
							r.setIsblending((short)1);//系统勾兑 0.未勾兑  1.已勾兑
							r.setSysrectime(new Date());//系统勾兑接收数据时间 第一次
						}
						r.setReqquerydata(map.toString());//请求数据包
						//System.out.println("接受数据包：  "+loanobjectlist.get(i).toString());
						r.setRecresultdata(trd.toString());//接收数据包
						r.setRemark(remark);
						redEnveLopeItemService.updateByPrimaryKeySelective(r);
						
					}
					
				}
			}
			
		}
		
	}
	
}
