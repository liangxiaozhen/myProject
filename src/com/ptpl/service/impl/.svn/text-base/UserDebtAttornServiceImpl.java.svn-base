package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.AccInExRecordMapper;
import com.ptpl.mapper.ActiveObjectListMapper;
import com.ptpl.mapper.DattornRNameLinkMapper;
import com.ptpl.mapper.DebtAttornFeeMapper;
import com.ptpl.mapper.DebtAttornMapper;
import com.ptpl.mapper.RemoveNameMapper;
import com.ptpl.mapper.RepayMentMapper;
import com.ptpl.mapper.TenderItemMapper;
import com.ptpl.mapper.UserAccountMapper;
import com.ptpl.mapper.UserAccountSafeInfoMapper;
import com.ptpl.mapper.UserBaseAccountInfoMapper;
import com.ptpl.mapper.UserDebtAttornMapper;
import com.ptpl.mapper.UserTenderMapper;
import com.ptpl.model.AccInExRecord;
import com.ptpl.model.ActiveObjectList;
import com.ptpl.model.DattornRNameLink;
import com.ptpl.model.DebtAttorn;
import com.ptpl.model.DebtAttornFee;
import com.ptpl.model.RemoveName;
import com.ptpl.model.RepayMent;
import com.ptpl.model.TenderItem;
import com.ptpl.model.UserAccount;
import com.ptpl.model.UserAccountSafeInfo;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserDebtAttorn;
import com.ptpl.model.UserTender;
import com.ptpl.service.UserDebtAttornServiceI;
import com.ptpl.web.util.Arith;
import com.ptpl.web.util.HuifuParams;
import com.ptpl.web.util.StringUtil;

public class UserDebtAttornServiceImpl extends UserDebtAttornServiceQuote implements UserDebtAttornServiceI {
	
	
	@Override
	public UserDebtAttorn selectByPrimaryKey(BigDecimal id) {
		return userDebtAttornmapper.selectByPrimaryKey(id);
	}
	@Override
	public void deleteByPrimaryKey(BigDecimal id) {
		userDebtAttornmapper.deleteByPrimaryKey(id);
	}
	@Override
	public void insertSelective(UserDebtAttorn userDebtAttorn) {
		userDebtAttornmapper.insertSelective(userDebtAttorn);
	}
	@Override
	public List<UserDebtAttorn> getAll() {
		System.out.println(userDebtAttornmapper.getAll().size());
		return userDebtAttornmapper.getAll();
	}
	@Override
	public int updateByPrimaryKeySelective(UserDebtAttorn userDebtAttorn) {
		return userDebtAttornmapper.updateByPrimaryKeySelective(userDebtAttorn);
	}
	@Override
	public List<UserDebtAttorn> getAllList(UserDebtAttorn ub) {
		return userDebtAttornmapper.getAllList(ub);
	}
	@Override
	public UserDebtAttorn getdaorderno(String daorderno) {
		return userDebtAttornmapper.getdaorderno(daorderno);
	}
	@Override
	public List<UserDebtAttorn> getCjZz(BigDecimal baseid) {
		return userDebtAttornmapper.getCjZz(baseid);
	}
	/**
	 * 查询是否是固定
	* @Title: isfixed 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param uda
	* @param @return  参数说明 
	* @return int    返回类型 
	* @author jiangxueyou
	* @throws
	 */
	public int isfixedFun(UserDebtAttorn uda){
		int isfixed = 0;
		System.out.println(uda.getTenderid());
		DebtAttorn deb = debtAttornMapper.selectByTid(uda.getTenderid());
		if(deb.getIsfixed()==1){//固定
			isfixed=1;
		}
		return isfixed;
	}
	/**
	 * 获取手续费,手续费收取模式,债转手续费收取类型
	* @Title: amountvalidation 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param userdebtattorn
	* @param @return  参数说明 
	* @return String    返回类型 
	* @author jiangxueyou
	* @throws
	 */
	public String  amountvalidation(UserDebtAttorn userdebtattorn,Double amount){
		//当前时间
		Date date = new Date();
		//手续费
		Double amountfee = 0.00;
		//判断是按照会员等级收费还是持有时间收费的标识:1:标识根据会员等级 2:表示根据持有时间
		String ugradeandbfb = "0";
		//判断手续费收费类型:1:定额 2:百分比 3:最低 4;最高
		String amountStr = "0";
		Double daamount2 = userdebtattorn.getDaamount();
		DebtAttornFee debtafee = null;
		//此标的持有时间
		int days = 0;
		if(userdebtattorn.getUsertender().getUtproperty()==1){//原始投标
			days = (int)((date.getTime() -  userdebtattorn.getUsertender().getValuedate().getTime())/86400000);//持有时间
		}else{//债转投标
			days = (int)((date.getTime() -  userdebtattorn.getUsertender().getTendtime().getTime())/86400000);//持有时间
		} 
		//获取承接人的会员等级
		UserAccountSafeInfo userAccountSafeInfo =  userAccountSafeInfoMapper.selectByBaseId(userdebtattorn.getBaseid());
		short ugrade = userAccountSafeInfo.getUgrade();
		List<DebtAttornFee>   debtAttornFee = debtAttornFeeMapper.selectByTid(userdebtattorn.getTenderid());
		for (DebtAttornFee dbf : debtAttornFee) {
			if(dbf.getIsadafeeon()==0){//手续费开关关闭状态
				amountfee = 0.00;
			}else{
				if(dbf.getFeemode()==1){//按照会员等级收费
					if(ugradeyanz(dbf.getUgrade(),ugrade)){
						 if(daamount2>dbf.getMinattornmoney() && daamount2<=dbf.getMaxattornmoney()){
							 debtafee = dbf;
							 ugradeandbfb = "1";
							 break;
						 }
					}
				}
				if(dbf.getFeemode()==2){//按照持有时间收费
					if(dayyanz(days,dbf.getHadday())){
						if(daamount2>dbf.getMinattornmoney() && daamount2<=dbf.getMaxattornmoney()){
							 debtafee = dbf;
							 ugradeandbfb = "2";
							 break;
						}
					}
				}
			}
		}	
		if(debtafee!=null){
			/*if(debta.getQuota()!=null && debta.getQuota()!=0){//假如是按定额收取的
			if(Arith.mul(daamount2,userdebtattorn.getCoefficient())>debta.getQuota()){//假如债转金额*债转系数>收费定额
				amountfee = debta.getQuota();
				amountStr="1";
			}
		}else{*/ //按百分比收取
		if (Arith.mul(amount, debtafee.getAttornrate()) <= debtafee.getMinfee()) {// 假如债转金额*百分比<minfee,那么按照minfee收费
				amountfee = debtafee.getMinfee();
				amountStr = "3";
		}
		if (Arith.mul(amount, debtafee.getAttornrate()) >= debtafee.getMaxfee()) {// 假如债转金额*百分比>maxfee,那么按照maxfee收费
				amountfee = debtafee.getMaxfee();
				amountStr = "4";
			
		}
		if (Arith.mul(amount, debtafee.getAttornrate()) > debtafee.getMinfee() && Arith.mul(amount, debtafee.getAttornrate()) < debtafee.getMaxfee()) {// minfee<假如债转金额*百分比<=maxfee,那么按照债转金额*百分比收费
				amountfee = Arith.mul(amount, debtafee.getAttornrate());
				amountStr = "2";
		}
		/*}*/
	}
		return ugradeandbfb+","+amountStr+","+amountfee;
}
	/**
	 * 保存用户债转记录+修改投标记录表中的IsDA	Number(2)	是否债转（0未债转，1全额债转，2部分债转）
	* @Title: saveUserDebtarron 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param userdebtattorn
	* @param @param baseid
	* @param @param orderno
	* @param @param amount
	* @param @param daamount
	* @param @param debtAttorn
	* @param @param coefficient
	* @param @param userBaseAccountInfo
	* @param @param d1
	* @param @param tenderid
	* @param @param udapass  参数说明 
	* @return void    返回类型 
	* @author jiangxueyou
	* @throws
	 */
	public String saveUserDebtarron(UserDebtAttorn userdebtattorn,UserTender userTender,UserBaseAccountInfo user,Date d1,String udapass,String data){
		if(userTender.getUtproperty()==1){//原始投标
			if(userTender.getDebtattorn().getIsdebtaudit()==0){//不需要审核
				userdebtattorn.setDastatus((short)2);
				userdebtattorn.setIsaudit((short)0);
				data="success";
			}else{//需要审核
				userdebtattorn.setDastatus((short)1);
				userdebtattorn.setIsaudit((short)1);
				data="audit";
			}
		}else{//债转投标:如果是债转标,那么债转标的投标订单号就是用户债转设置表的债转订单号
			//通过tenderid获取用户债转设置表中的最大次数!然后+1,就是当前的债转次数
			DebtAttorn uba = debtAttornMapper.selectByTid(userTender.getTenderid());
			if(uba!=null){
				if(uba.getIsdebtaudit()==0){//不需要审核
					userdebtattorn.setDastatus((short)2);//已上架
					userdebtattorn.setIsaudit((short)0);//是否需要审核:不需要
					data="success";
				}else{//需要审核
					userdebtattorn.setDastatus((short)1);//待审核
					userdebtattorn.setIsaudit((short)1);//需要审核
					data="audit";
				}
			}
		}
		if(userTender.getIdentifying()==2){//逾期债转
			if(userTender.getLatecoefficient()!=null){
				userdebtattorn.setLatefeecoefficient(userTender.getLatecoefficient());//滞纳金债转系数
			}else{
				userdebtattorn.setLatefeecoefficient(0.00);//滞纳金债转系数
			}
			if(userTender.getLatefee()!=null){
				userdebtattorn.setOcamount(userTender.getLatefee());//滞纳金
				userdebtattorn.setFactocamount(userTender.getLatefee());//实际转出滞纳金
			}else{
				userdebtattorn.setOcamount(0.00);//滞纳金
				userdebtattorn.setFactocamount(0.00);//实际转出滞纳金
			}
		}
		userdebtattorn.setOdperiods(new BigDecimal(userTender.getPeriods()));//逾期期数
		userdebtattorn.setIntcoefficient(userTender.getInterestcoefficient());//利息转出系数
		userdebtattorn.setLcid(StringUtil.getNolcId());//挂牌ID
		userdebtattorn.setFactintamount(userTender.getNomalfee());//实际转出利息
		userdebtattorn.setIntamount(userTender.getNomalfee());//转出利息
		userdebtattorn.setDeadline((short)userTender.getDeadline());//上架天数
		userdebtattorn.setDaproperty((short)userTender.getIdentifying());//债转属性（1正常债转，2逾期债转）
		userdebtattorn.setDaid(userTender.getDebtattorn().getId());//债权设置表Id
		userdebtattorn.setBaseid(user.getId());//转让人Id
		userdebtattorn.setTorderno(userTender.getOrderno());//投标订单号（债转流水号）
		userdebtattorn.setAmount(userTender.getAmount());//投标金额
		userdebtattorn.setDaamount(userTender.getDaamount());//债转金额
		userdebtattorn.setCandaamount(userTender.getDaamount());//可债转金额
		userdebtattorn.setIspartda(userTender.getDebtattorn().getIsasplit());//全额/部分债转(1全额，2部分)
		userdebtattorn.setCoefficient(userTender.getCoefficient());//债转系数
		userdebtattorn.setSetman(user.getLoginname());//设置人:就是出让人的名字
		userdebtattorn.setSettime(d1);//债转时间
		userdebtattorn.setTenderid(userTender.getTenderid());//标号ID
		userdebtattorn.setUdapass(udapass);//定向债转码
		System.out.println(StringUtil.getNo() + "债转标号");
		userdebtattorn.setDaorderno(StringUtil.getNo());//债转编号
		userDebtAttornmapper.insertSelective(userdebtattorn);
		return data;
	}

	/**
	 * 判断出让人手续费够不够
	* @Title: quotaAndAttornrate 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param coefficient
	* @param @param debta
	* @param @param daamount
	* @param @param data
	* @param @return  参数说明 
	* @return String    返回类型 
	* @author jiangxueyou
	* @throws
	 */
	public String quotaAndAttornrate(DebtAttornFee debta,UserTender userTender,String data){
		if (debta != null) {
			if (Arith.mul(userTender.getDaamount(),debta.getAttornrate()) < debta.getMinfee()) {// 假如债转金额*百分比<minfee,那么按照minfee收费
				if (Arith.mul(userTender.getDaamount(), userTender.getCoefficient()) < debta.getMinfee()) {
					data = "minfeefalse";
				} else {
					data = "success";
				}
			} 
				if (Arith.mul(userTender.getDaamount(), debta.getAttornrate()) > debta.getMaxfee()) {// 假如债转金额*百分比>maxfee,那么按照maxfee收费
					if (Arith.mul(userTender.getDaamount(),  userTender.getCoefficient()) < debta.getMaxfee()) {
						data = "maxfeefalse";
					} else {
						data = "success";
					}
				}
				if (Arith.mul(userTender.getDaamount(),debta.getAttornrate()) > debta.getMinfee()
						&& Arith.mul(userTender.getDaamount(), debta.getAttornrate()) <= debta.getMaxfee()) {// minfee<假如债转金额*百分比<=maxfee,那么按照债转金额*百分比收费
					if (Arith.mul(userTender.getDaamount(), userTender.getCoefficient()) < Arith.mul(userTender.getDaamount(),debta.getAttornrate())) {
						data = "attornratefalse";
					} else {
						data = "success";
					}
				}
			
		}
		return data;
	}
	
	public String ublistmanytimes(BigDecimal baseid, UserTender userTender,UserDebtAttorn userdebtattorn) {
		String data = "";
		int sellFrequency = 0;
		//获取次数/层数
		int Datimes = userTender.getDebtattorn().getDatimes();
		if(moneytrueandfalse(userTender)){
			
			UserDebtAttorn ubda4 = new UserDebtAttorn();
			ubda4.setTorderno(userTender.getOrderno());
			ubda4.setBaseid(baseid);
			List<UserDebtAttorn> ubList4 = userDebtAttornmapper.getAllList(ubda4);
			System.out.println(ubList4);
			
			List<UserDebtAttorn> ubList = selectByTorderNo(userTender.getOrderno());
			if (userTender.getDebtattorn().getDattrestrict() == 1) {// 表示以层数做限制
				if(ubList!=null && ubList.size()>0){
					System.out.println(ubList.get(0).getDatimes());
					sellFrequency = ubList.get(0).getDatimes();
				}else{
					if(userTender.getUtproperty()==1){//如果这个标是原始投标,那么当ubList为null的时候就直接存1
						sellFrequency = 1;
					}else{
						System.out.println(userTender.getOlddaorderno());
						//根据原流水号查询用户债转用户表
						List<UserDebtAttorn> ubList2 = selectByTorderNo(userTender.getOlddaorderno());
						if(ubList2!=null && ubList2.size()>0){
							System.out.println(ubList2.get(0).getDatimes());
							sellFrequency = ubList2.get(0).getDatimes()+1;
						}else{
							sellFrequency = 1;
						}
					}
				}
				if(sellFrequency<=Datimes){
					userdebtattorn.setDatimes((short)(sellFrequency));
					data = "success";
				}else{
					data="Cdatimesfalse";
				}
			}
			// 表示以次数做限制
			if (userTender.getDebtattorn().getDattrestrict() == 2) {
				UserDebtAttorn ubda = new UserDebtAttorn();
				ubda.setTorderno(userTender.getOrderno());
				ubda.setBaseid(baseid);
				List<UserDebtAttorn> ubList3 = userDebtAttornmapper.getAllList(ubda);
				if(ubList3!=null && ubList3.size()>0){
					 int max = ArrayListMax(ubList3);
					 if(max+1<=Datimes){
						 userdebtattorn.setDatimes((short)(max+1));
						 data = "success";
					 }else{
						 data = "datimesfalse";
					 }
				}else{
					 userdebtattorn.setDatimes((short)1);
					 data = "success";
				}
			}
			
			
		}else{
			data = "moneyfalse";
		}
		return data;
	}
	/**
	 * 获取债转次数的最大值
	 * @param @param ubList3
	 * @param @return
	 * @return double
	 * @author jiangxueyou
	 */
	public int ArrayListMax(List<UserDebtAttorn> ubList3) {
		int maxDevation = 0;
		int totalCount = ubList3.size();
		if (totalCount >= 1) {
			int max = ubList3.get(0).getDatimes();
			for (int i = 0; i < totalCount; i++) {
				int temp = ubList3.get(i).getDatimes();
				if (temp > max) {
					max = temp;
				}
			}
			maxDevation = max;
		}
		return maxDevation;
	}
	/**
	 * 判断债转金额有没有符合要求,有没有超过限制
	 * @param @param userTender
	 * @param @return
	 * @return boolean
	 * @author jiangxueyou
	 */
	public boolean moneytrueandfalse(UserTender userTender){
		if(addMoney(userTender)<= userTender.getSurplusamount()){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 算出当前债转金额+
	 * @param @param userTender
	 * @param @return
	 * @return Double
	 * @author jiangxueyou
	 */
	public Double addMoney(UserTender userTender){
		List<UserDebtAttorn> uList = userDebtAttornmapper.selectByTorderNo(userTender.getOrderno());
		double totalMoney = TraversalAmount(uList); // 查询用户债转表中的该笔标的债转金额总数
		double money = Arith.add(totalMoney, userTender.getDaamount());// 债转金额总数+本次债转金额
		return money;
	}
	/**
	 * 验证持有天数是否大于等于手续费持有天数
	* @Title: dayyanz 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param days
	* @param @param haday
	* @param @return  参数说明 
	* @return boolean    返回类型 
	* @author jiangxueyou
	* @throws
	 */
	public boolean dayyanz(int days,int haday){
		boolean falg = false;
		if(days>=haday){//持有天数大于手续费持有天数任意一个
			falg = true;
		}
		return falg;
		
	}
	/**
	 * 获取总债转金额
	* @Title: TraversalAmount 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param ubList
	* @param @return  参数说明 
	* @return Double    返回类型  
	* @author jiangxueyou
	* @throws
	 */
	public Double TraversalAmount(List<UserDebtAttorn>  ubList){
		Double totalMoney  = 0.0;
		if(ubList!=null && ubList.size()>0){
			for (int i = 0; i < ubList.size(); i++) {
				totalMoney+=ubList.get(i).getDaamount();
			}
		}
		return totalMoney;
	}
	/**
	 * 排出人员名单验证
	* @Title: removenoyanz 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param removeno
	* @param @param baseid
	* @param @return  参数说明 
	* @return boolean    返回类型 
	* @author jiangxueyou
	* @throws
	 */
	public boolean removenoyanz(BigDecimal tid,BigDecimal baseid,int validata){
		boolean falg = false;
		boolean falg1 = false;
		boolean falg2 = false;
		String removeno = "";
		DattornRNameLink  dattornrnamelink = dattornRNameLinkMapper.selectByTid(tid);
		if(dattornrnamelink!=null){
			if(validata==1){//表示要获取承接人是否在排除人名单中
				removeno = dattornrnamelink.getNoapnno();
			}else{//为2,表示获取债转人是否在排除人名单中
				removeno = dattornrnamelink.getOwnerrnno();
			}
			if(removeno!=null && removeno!=""){
				String[]  nameno = removeno.split(",");
				for (int i = 0; i < nameno.length; i++) {
					List<RemoveName>  rnList = removeNameMapper.getRemove(nameno[i]);
					for (RemoveName removeName : rnList) {
						if(removeName.getBaseid().equals(baseid)){
							falg= true;
							falg1= true;
							falg2= true;
						}
						if(falg1){
							break; 
						}
					}
					if(falg2){
						break; 
					}
				}
			}
		}
		return falg;
	}
	
	/**
	 * 会员等级验证
	* @Title: ugradeyanz 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param ugradeStr
	* @param @param ugrade
	* @param @return  参数说明 
	* @return boolean    返回类型 
	* @author jiangxueyou
	* @throws
	 */
	public boolean ugradeyanz(String ugradeStr,short ugrade){
		List<Integer> ugradeList = StringUtil.getIntegerLenth(ugradeStr);
		boolean falg = false;
		boolean falg1 = false;
		for (int i = 0; i < ugradeList.size(); i++) {
			 if(ugradeList.get(i)==ugrade){
				 falg= true;
				 falg1= true;
			 }
			 if(falg1){
				break; 
			 }
		}
		return falg;
	}
	/**
	 * 获取包含的所有元素
	 * @param @param snlid
	 * @param @return
	 * @return Set<UserBaseAccountInfo>
	 * @author jiangxueyou
	 */
	public Set<UserBaseAccountInfo> getSpecialNameList(BigDecimal snlid){
		Set<UserBaseAccountInfo> ubaiList = new HashSet<UserBaseAccountInfo>();
		List<ActiveObjectList> aoList = activeObjectListMapper.selectBySNLId(snlid);
		for (ActiveObjectList aol : aoList){
			short or = aol.getIsRightOrExcept();//1包含，2排除
			short type = aol.getNameType();//名单类型（1大名单，2小名单，3用户名，4会员等级）
			String nameContent = aol.getNameContent();//大，小名单时 填名单编号  ，会员等级 填写会员等级，51个字符串 单条记录,单个用户时 填写具体用户名
			// 1 包含
			if (or == 1){
				// 大名单
				if (type == 1){
					List<RemoveName> list = removeNameMapper.getUserNameMax(nameContent);
					for (RemoveName rname : list){
						UserBaseAccountInfo ubai = userBaseAccountInfoMapper.getUserBaseAccountInfoById(rname.getBaseid());
						if(ubai!=null){
							ubaiList.add(ubai);
						}
					}
				}
				// 小名单
				if (type == 2){
					List<RemoveName> list = removeNameMapper.getRemoveNameByNameNo(nameContent);
					for (RemoveName rname : list){
						UserBaseAccountInfo ubai = userBaseAccountInfoMapper.getUserBaseAccountInfoById(rname.getBaseid());
						if(ubai!=null){
							ubaiList.add(ubai);
						}
					}
				}
				// 用户名
				if (type == 3){
					UserBaseAccountInfo ubai = userBaseAccountInfoMapper.getuserloginname(nameContent);
					if(ubai!=null){
						ubaiList.add(ubai);
					}
				}
				// 会员等级
				if (type == 4){
					String arr[] = StringUtil.getPlaceholderArr(nameContent);
					for (String ugrade : arr){
						List<UserAccountSafeInfo> uasiList = userAccountSafeInfoMapper.getuseradmin(Short.valueOf(ugrade));
						for (UserAccountSafeInfo uasi : uasiList){
							UserBaseAccountInfo ubai = userBaseAccountInfoMapper.getUserBaseAccountInfoById(uasi.getBaseid());
							if(ubai!=null){
								ubaiList.add(ubai);
							}
						}
					}
				}
			}
			// 排除
			if (or == 2){
				// 大名单
				if (type == 1){
					List<RemoveName> list = removeNameMapper.getUserNameMax(nameContent);
					for (RemoveName rname : list){
						UserBaseAccountInfo ubai = userBaseAccountInfoMapper.getUserBaseAccountInfoById(rname.getBaseid());
						ubaiList.remove(ubai);
					}
				}
				// 小名单
				if (type == 2){
					List<RemoveName> list = removeNameMapper.getRemoveNameByNameNo(nameContent);
					for (RemoveName rname : list){
						UserBaseAccountInfo ubai = userBaseAccountInfoMapper.getUserBaseAccountInfoById(rname.getBaseid());
						ubaiList.remove(ubai);
					}
				}
				// 用户名
				if (type == 3){
					UserBaseAccountInfo ubai = userBaseAccountInfoMapper.getuserloginname(nameContent);
					ubaiList.remove(ubai);
				}
				// 会员等级
				if (type == 4){
					String arr[] = StringUtil.getPlaceholderArr(nameContent);
					for (String ugrade : arr){
						List<UserAccountSafeInfo> uasiList = userAccountSafeInfoMapper.getuseradmin(Short.valueOf(ugrade));
						for (UserAccountSafeInfo uasi : uasiList){
							UserBaseAccountInfo ubai = userBaseAccountInfoMapper.getUserBaseAccountInfoById(uasi.getBaseid());
							ubaiList.remove(ubai);
						}
					}
				}
			}
		}
		return ubaiList;
	}
	
	/**
	 * 判断债转的会员等级符合要求不
	 * @param @param userTender
	 * @param @return
	 * @return boolean
	 * @author jiangxueyou
	 */
   private boolean ugradeFalse(UserTender userTender){
	   boolean ugradeflag = false;
	   System.out.println(userTender.getDebtattorn().getSnlid());
	   Set<UserBaseAccountInfo> UserBaseAccountInfoList = getSpecialNameList(userTender.getDebtattorn().getSnlid());
	   for (Iterator<UserBaseAccountInfo> iterator = UserBaseAccountInfoList.iterator(); iterator.hasNext();) {
		   UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) iterator.next();
		   if (userBaseAccountInfo.getId().intValue()==userTender.getOutaccountid().intValue()) {
			   ugradeflag = true;//说明在包含的会员等级中
			   break;
		   }
	   }
	   return ugradeflag;
   }
   /**
    * 判断在不在定向名单中
    * @param @param snlid
    * @param @param baseid
    * @param @return
    * @return boolean
    * @author jiangxueyou
    */
   public boolean ugradeFalsePublic(BigDecimal snlid,BigDecimal baseid){
	   boolean ugradeflag = false;
	   Set<UserBaseAccountInfo> UserBaseAccountInfoList = getSpecialNameList(snlid);
	   for (Iterator<UserBaseAccountInfo> iterator = UserBaseAccountInfoList.iterator(); iterator.hasNext();) {
		   UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) iterator.next();
		   if (userBaseAccountInfo.getId().intValue()==baseid.intValue()) {
			   ugradeflag = true;//说明在包含的定向名单中
			   break;
		   }
	   }
	   return ugradeflag;
   }
	/**
	 * 先决条件的判断
	 * @param @param userTender
	 * @param @param d
	 * @param @param repayMent 
	 * @param @param SpecialMark
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @author jiangxueyou
	 */
	public String algorithm(UserTender userTender,Date d,RepayMent repayMent) throws Exception {
		//获取标的设置记录
		TenderItem tenderItem = tenderItemmapper.findTenderItemById(userTender.getTenderid());
		int days2  = 0;//距离下个还款日还有多少天
		int days3 = 0; //逾期前多少天可债转
		String data = ""; 
		//根据标的债转记录表id四表联查相关信息
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("outaccountid",userTender.getOutaccountid());
		condition.put("orderno", userTender.getOrderno());
		DebtAttorn  dba = debtAttornMapper.getCondition(condition);
		
		/**债转容许会员等级:
		 * 如果当前会员等级在允许的会员的等级里面,就返回true
		 * */
		boolean ugradeflag =true;//ugradeFalse(userTender);
		//持有时间:不管此标是否逾期还是正常:持有时间都都只分两种,距离还款时间都是一种
		int days = CYdays(d,userTender);
		//正常标:
		if(userTender.getIdentifying()==1){
			//当前时间距离当期还款日还有多少天
			days2= (int)((repayMent.getRtime().getTime()-d.getTime())/86400000);
			//正常标:在逾期宽限期内的正常标
			if(userTender.getLatamark().equals("Y")){
				//债转宽限期
				int graceperiod = tenderItem.getGraceperiod().intValue();
				//做运算
				Calendar cal=Calendar.getInstance();
				cal.setTime(repayMent.getRtime());
				cal.add(Calendar.DAY_OF_MONTH, graceperiod+1);
				days3= (int)((cal.getTime().getTime()-d.getTime())/86400000);
			}
		}
	
		if(ugradeflag){
				if(days+1>=dba.getHoldday()){//持有天数
					if(userTender.getIdentifying()==1){//正常标的时候
						if(days2>=dba.getIntervalday()){//距离下个还款日
							if(userTender.getLatamark().equals("Y")){
								if(days3>dba.getAheadocday()){//距离逾期多少天
									data="success";
								}else{
									data = "aheadocday";// 距离逾期时间太近
								}
							}else{
								data="success";
							}
						}else {
							data = "intervalday";// 距离还款时间太近
						} 
						
					}
					if(userTender.getIdentifying()==2){
						data="success";
					}
				} else {
					data = "dayfalse";// 持有时间不足
				}  
		} else {
			data = "ugradefasle";// 不在允许的债转会员等级中
		} 
		return data;
	}
	/**
	 * 算逾期滞纳金比例和利息比例,得到最后值再来乘以债转金额
	 */
	public String normal(UserTender userTender,String periods) throws Exception{
		//获取标的设置对象
		TenderItem tItem = tenderItemmapper.findTenderItemById(userTender.getTenderid());
		//当前时间:也就是债转时间
		Date d = new Date(); 
		//债转时间到之前出让人应该得到的利息
	    Double fee = 0.00;
	    //滞纳金费率
	    Double feeLate = 0.00;
	    //待收本金
		Double ds = 0.00;
		//当期时间 
		int day = 0;
		//出让人持有时间
		int day2 = 0;
		//K:一元一天的利息
		Double k = 0.00;
	    //获取投资人还款计划:如果是逾期标就获取逾期当期的还款计划,如果是正常标就获取当前标的还款计划,当是正常标的时候periods是null
	    RepayMent rMent = getRepayMent(userTender,periods);
		String data = algorithm(userTender,d,rMent);
		if(data.equals("success")){//就是说满足先决条件之后才能执行下面的代码
			if(userTender.getIdentifying()==1){//正常标
				if(tItem.getRepaymentpro()==1){//一次性还本付息
					//获取当期时间:还款时间-起息时间相差的天数
					day =  (int)((rMent.getRtime().getTime() - userTender.getValuedate().getTime())/86400000);
					//出让然人持有时间:债转时间和起息日之间相差的时间
					day2 =  (int)((d.getTime() - userTender.getValuedate().getTime())/86400000);
					//当期待收本金
					ds = Arith.add(rMent.getRamount(), rMent.getRestprincipal());
					k = Arith.div(Arith.div(rMent.getRinterest(),ds,5),day,8);
					fee= Arith.mul(day2, k);
				}else{//等额本金,等额本息
				    /**如果当前时间在rMent对象的当期之内,那么说明是当期债转,那么持有时间=当前时间-上一期的还款时间
				     * 如果上一期的还款时间没有,那么说明是在第一期债转的,那么,持有时间=当前时间-起息日
				     * */
					RepayMent remy = everyRepayment(userTender,rMent.getPeriods()-1);//查询上一期的还款计划,得到上一期的还款时间
					if(remy!=null){//当查询到上一期的还款计划的时候
						//获取当期天数
						day =  (int)((rMent.getRtime().getTime() - remy.getRtime().getTime())/86400000);//当期时间有多少天
						if(remy.getPeriods()!=1){
							//获取投资人此标持有时间
							day2 =  (int)((d.getTime() -  remy.getRtime().getTime())/86400000);//出让人持有时间
						}
					}else{//假如没有上一期还款计划
						//获取当期天数
						day =  (int)((rMent.getRtime().getTime() - userTender.getValuedate().getTime())/86400000);//当期时间有多少天
						//获取投资人此标持有时间
						day2 =  (int)((d.getTime() -  userTender.getValuedate().getTime())/86400000);//出让人持有时间
					}
					//当期待收本金
					ds = Arith.add(rMent.getRamount(), rMent.getRestprincipal());
					k = Arith.div(Arith.div(rMent.getRinterest(),ds,5),day,8);
					fee= Arith.mul(day2+1, k);
				}
			}
			if(userTender.getIdentifying()==2){//逾期标
				ds = Arith.add(rMent.getRamount(), rMent.getRestprincipal());
				fee = Arith.div(rMent.getRinterest(), ds, 5);
				//滞纳天数
				Double totay = (double) ((d.getTime() - rMent.getRtime().getTime())/86400000);
				feeLate = Arith.mul(tItem.getDaylatefeerate(), totay);//日滞纳率*滞纳天数
			}
		}
		return fee+","+data+","+feeLate;
	}
	/**
	 * 获取投资人还款计划
	 * @param @param userTender
	 * @param @param d
	 * @param @param identifying
	 * @param @param tItem
	 * @param @param periods
	 * @param @return
	 * @return RepayMent
	 * @author jiangxueyou
	 */
	public RepayMent getRepayMent(UserTender userTender,String periods){
	    RepayMent rMent = null;
	    if(userTender.getIdentifying()==1){//正常标
	    	rMent= selectRepayMent(userTender);//获取未还款的当期还款计划
	    }else{//逾期标:查询逾期当期的还款计划
	    	rMent = everyRepayment(userTender,Integer.valueOf(periods));
	    }
	    return rMent;
	}
	/**待确认
	 * 方法的重载
	 */
	public RepayMent getRepayMent(UserTender userTender,Date d,String Daproperty,TenderItem tItem,String periods){
	    RepayMent rMent = null;
	    if(Daproperty.equals("1")){//正常标
	    	RepayMent repayMent = frontObject(userTender,d);
	    	if(tItem.getRepaymentpro()==1){//一次性还本付息
	    		//查询此标小于当前时间的上一期有无正常还款,如果有正常还款,那么说明不是在逾期宽限期内发生的债转
				if(repayMent!=null){
					if(repayMent.getRepaystatus()==1){//未还款,说明是在逾期宽限期内债转的
						rMent = repayMent;
					}
				}else{//如果上一期还款对象为null,那么说明是该一次性还本付息标是在期限内发生的债转
					rMent = returnObject(userTender,d);//获取当期的还款计划
				}
	    	}else{
				if(repayMent==null){//说明是在第一期就进行的债转,并且是在规定期限内债转的 
					rMent = returnObject(userTender,d);//获取当期的还款计划
				}else{//如果不为null,那么就看上一期还款是否正常,如果没有还款,那么就说明逾期宽限起内债转的 
					if(repayMent.getRepaystatus()==1){//未还款,说明是在逾期宽限期内债转的
						rMent = repayMent;
					}
					if(repayMent.getRepaystatus()==2){//上一期还款了的,说明是在规定期限内发生的债转
						rMent = returnObject(userTender,d);
					}
				}
	    	}
	    }else{//逾期标
	    	rMent = everyRepayment(userTender,Integer.valueOf(periods));
	    }
	    return rMent;
	}
	/**
	 * 获取是不是逾期宽限期内发生的债转
	 * @param @param userTender
	 * @param @param d
	 * @param @param identifying
	 * @param @param tItem
	 * @param @return
	 * @return String
	 * @author jiangxueyou
	 */
	public String getString(UserTender userTender,Date d){
		
		String SpecialMark = "0"; //逾期宽限期的标志
		 if(userTender.getIdentifying()==1){//正常标
    		//查询此标小于当前时间的上一期有无正常还款,如果有正常还款,那么说明不是在逾期宽限期内发生的债转
			RepayMent repayMent = frontObject(userTender,d);
			if(repayMent!=null){
				if(repayMent.getRepaystatus()==1){//未还款,说明是在逾期宽限期内债转的
					SpecialMark = "1";//表示在逾期宽限期内发生的债转的
				}
			}
		    	
		   }
	    return SpecialMark;
	}
	/**
	 * 持有时间的算法
	* @Title: CYdays 
	* @Description: TODO(持有时间的算法) 
	* @param @param d
	* @param @param userTender
	* @param @return  参数说明 
	* @return int    返回类型 
	* @author jiangxueyou
	* @throws
	 */
	public int CYdays(Date d,UserTender userTender){
		int days = 0; 
		if(userTender.getUtproperty()==1){//原始投标
			days = (int)((d.getTime() - userTender.getValuedate().getTime())/86400000);//持有时间
		} 
		if(userTender.getUtproperty()==2){//债转投标
			System.out.println(userTender.getTendtime());
			days = (int)((d.getTime() - userTender.getTendtime().getTime())/86400000);//持有时间 
		}
		return days;
	}
	/**
	 * 返回年利率:逾期就没有年利率
	 * 起息日和利息有关系也和年利率有关系
	* @Title: yearrate 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param uda
	* @param @return  参数说明 
	* @return Double    返回类型 
	* @author jiangxueyou
	* @throws
	 */
	public Double yearrate(UserDebtAttorn uda,Double proceeds,Double CJRAmount){ 
		//年利率
		Double yearlilv = 0.00;
		Date date = new Date();
		UserTender userTender = userTendermapper.findUserTenderByOrderno(uda.getTorderno());
		if(uda.getDaproperty()==1){//正常标
			DebtAttorn dAttorn = debtAttornMapper.selectByTid(uda.getTenderid());
			Date lastdate = lastDate(userTender);
			int days = (int)((lastdate.getTime() - date.getTime())/86400000);
			Calendar cal=Calendar.getInstance();  
			cal.setTime(date); 
			int flag =valuedate(dAttorn, cal);
			if(flag==1){//当日起息
				days=days+1;
			}
			yearlilv = Arith.mul(Arith.div(Arith.div(proceeds,CJRAmount,5),days,5),365);
		}
		return yearlilv;
	}
	/**
	 * 固定的时候算出让人出让时距离原始标上个还款日(起息日)的时间差
	 */
	public int fixeddays(UserDebtAttorn uda){
		UserTender userTender = userTendermapper.findUserTenderByOrderno(uda.getTorderno());
		//最后一期的还款时间
		Date lastdate = lastDate(userTender);
		/**查询这笔债转标需要审核不,如果需要审核的话,那么上架时间就是审核时间,如果不需要审核,那么上架时间就是发布时间*/
		DebtAttorn dAttorn = debtAttornMapper.selectByTid(uda.getTenderid());
		int day2 = 0;
		if(dAttorn.getIsdebtaudit()==1){//需要审核
			day2=  (int)((lastdate.getTime() - uda.getAudittime().getTime())/86400000);
		}else{
			day2=  (int)((lastdate.getTime() - uda.getSettime().getTime())/86400000);
		}
		return day2;
	}
	/**
	 * 算出承接人承接后所收到的总利息
	 * @param @param userTender
	 * @param @param repayMent
	 * @param @return
	 * @return Double
	 * @author jiangxueyou
	 */
	public Double totalFee(UserTender userTender,RepayMent repayMent){
		RepayMent rm = new RepayMent();
		rm.setInaccountid(userTender.getOutaccountid());//投资人id
		rm.setUtorderno(userTender.getOrderno());//投标订单号
		rm.setTenderid(userTender.getTenderid());
		System.out.println(repayMent.getPeriods());
		rm.setPeriods(repayMent.getPeriods());
		Double totalfee = repayMentmapper.getTotalPeriods(rm);
		return totalfee;
	}
	/**
	 * 返回当期还款计划
	 * @param @param userTender
	 * @param @param d
	 * @param @return
	 * @return RepayMent
	 * @author jiangxueyou
	 */
	public RepayMent returnObject(UserTender userTender,Date date){
		RepayMent rm = new RepayMent();
		rm.setRtime(date);
		rm.setInaccountid(userTender.getOutaccountid());//投资人id
		rm.setUtorderno(userTender.getOrderno());//投标订单号
		rm.setTenderid(userTender.getTenderid());
		System.out.println(userTender.getOrderno());
		System.out.println(sf.format(date));
		System.out.println(userTender.getTenderid());
		RepayMent repayMent = repayMentmapper.getRtimeAndPeriodsforRepayMent2(rm);
		return repayMent;
	}
	/**
	 * 返回上一期还款计划
	 * @param @param userTender
	 * @param @param d
	 * @param @return
	 * @return RepayMent
	 * @author jiangxueyou
	 */
	public RepayMent frontObject(UserTender userTender,Date date){
		RepayMent rm = new RepayMent();
		rm.setRtime(date);
		rm.setInaccountid(userTender.getOutaccountid());//投资人id
		rm.setUtorderno(userTender.getOrderno());//投标订单号
		rm.setTenderid(userTender.getTenderid());
		RepayMent repayMent = repayMentmapper.getRtimeAndPeriodsforRepayMent(rm);
		return repayMent;
	}
	/**
	 * 查询逾期还款未还款的最小期数
	 * @param @param userTender
	 * @param @param d
	 * @param @return
	 * @return RepayMent
	 * @author jiangxueyou
	 */
	public RepayMent overdueFrontNalRepayment(UserTender userTender,Date date){
		RepayMent rm = new RepayMent();
		rm.setRtime(date);
		rm.setInaccountid(userTender.getOutaccountid());//投资人id
		rm.setUtorderno(userTender.getOrderno());//投标订单号
		rm.setTenderid(userTender.getTenderid());
		RepayMent repayMent = repayMentmapper.getOverduePrincipal(rm);
		return repayMent;
	}
	/**
	 * 查询此标最后一期的还款日期
	 * @param @param userTender
	 * @param @return
	 * @return Date
	 * @author jiangxueyou 
	 */
	public Date lastDate(UserTender userTender){
		RepayMent rm = new RepayMent();
		rm.setInaccountid(userTender.getOutaccountid());//投资人id
		rm.setUtorderno(userTender.getOrderno());//投标订单号
		rm.setTenderid(userTender.getTenderid());
		RepayMent repayMent = repayMentmapper.getEndRtimeforRepayMent(rm);
		return repayMent.getRtime();
	}
	/**
	 * 查询此标每期的还款计划
	 * @param @param userTender
	 * @param @return
	 * @return Date
	 * @author jiangxueyou 
	 */
	public RepayMent everyRepayment(UserTender userTender,int periods){
		RepayMent rm = new RepayMent();
		System.out.println(userTender.getOutaccountid());
		System.out.println(userTender.getOrderno());
		System.out.println(userTender.getTenderid());
		rm.setInaccountid(userTender.getOutaccountid());//投资人id
		rm.setUtorderno(userTender.getOrderno());//投标订单号
		rm.setTenderid(userTender.getTenderid());
		rm.setPeriods(periods);
		RepayMent repayMent = repayMentmapper.getAndPeriods(rm);
		return repayMent;
	}
	/**
	 * 判断是承接日当期起息还是次日起息
	 * @param @param ub
	 * @param @param cal
	 * @param @return
	 * @return int
	 * @author jiangxueyou
	 */
	public int valuedate(DebtAttorn ub,Calendar cal){
		int days = 0;
		//算起息日
		//起息规则（1承接日当天，2承接日次日，3承接日固定时间之前，4承接日固定时间之后）
		short  valuerule = ub.getValuerule();
		//short  valuerule = 1;
		//起息时间点
		String valuepoint = ub.getValuepoint();
		//获取承接时间
		SimpleDateFormat sdf3 = new SimpleDateFormat("HH:mm:ss");
		if(valuerule==1){
			days=1;
		}
		if(valuerule==2){
			days=2;
		}
		if(valuerule==3){
			//承接时间
			String caltime = sdf3.format(cal.getTime());
			String [] calTime = caltime.split(":");
			//标的设置表中设置的时间点
			String [] valuepointArray = valuepoint.split(":");
			if(Double.valueOf(calTime[0])<=Double.valueOf(valuepointArray[0])){//时
				if(Double.valueOf(calTime[1])<=Double.valueOf(valuepointArray[1])){//分
					if(Double.valueOf(calTime[2])<=Double.valueOf(valuepointArray[2])){//秒
						days=1;
					}else{
						days=2;
					}
				}else{
					days=2;
				}
			}else{
				days=2;
			}
		}
		return days;
	}
	/**
	 * 查询此标的具体投资人还款计划
	 * @param @param user
	 * @param @param userTender
	 * @param @return
	 * @return List<RepayMent>
	 * @author jiangxueyou
	 */
	public List<RepayMent> rmlist(UserBaseAccountInfo user,UserTender  userTender){
		RepayMent rm = new RepayMent();
		rm.setInaccountid(user.getId());
		rm.setUtorderno(userTender.getOrderno());
		rm.setTenderid(userTender.getTenderid());
		List<RepayMent> rmList =repayMentmapper.selectRepayMentList(rm);
		return rmList;
	}
	/**
	 * 下架以TenderId为XX的所有债转标
	 */
	@Override
	public boolean undercarriageUserTender(BigDecimal TenderId) {
		boolean flag = true;
		int falg = 0;
		List<Integer> flagList = new ArrayList<Integer>();
		UserDebtAttorn ub = new UserDebtAttorn();
		ub.setTenderid(TenderId);
		ub.setDastatus((short)2);//已经上架的标
		List<UserDebtAttorn> ubList = getAllList(ub);
		if(ubList!=null && ubList.size()!=0){
			for (UserDebtAttorn userDebtAttorn : ubList) {
				userDebtAttorn.setDastatus((short)3);//下架
				falg = userDebtAttornmapper.updateByPrimaryKeySelective(userDebtAttorn);
				flagList.add(falg);
			}
			for (int i = 0; i < flagList.size(); i++) {
				if(flagList.get(i)==0){
					flag = false;
					break;
				}
			}
		}
		return flag;
	}
	/**
	 *  下架部分债转标
	 */
	@Override
	public boolean partcarriageUserTender(String rocd) {
		boolean face = false;
		String [] str =  rocd.split(",");
		for (int i = 0; i < str.length; i++) {
			UserTender utender = userTendermapper.findUserTenderByOrderno(str[i]);
			if(utender!=null){
				face = partUserTender(utender);
				if(face==false){
					break;
				}
			}
		} 
		return face;
	}
	/**
	 * 下架提前还款某一个人的债转标
	 * @param @param userTender
	 * @param @return
	 * @return boolean
	 * @author jiangxueyou
	 */
	public boolean partUserTender(UserTender userTender){
		List<Integer> flagList = new ArrayList<Integer>();
		boolean flag = true;
		int falg = 0;
		UserDebtAttorn ub = new UserDebtAttorn();
		ub.setTenderid(userTender.getTenderid());
		ub.setDastatus((short)2);//已经上架的标
		ub.setTorderno(userTender.getOrderno());//投标订单号
		ub.setBaseid(userTender.getOutaccountid());//投资人id
		List<UserDebtAttorn> ubList = getAllList(ub);
		if(ubList!=null && ubList.size()!=0){
			for (UserDebtAttorn userDebtAttorn : ubList) {
				userDebtAttorn.setDastatus((short)3);//下架
				falg = userDebtAttornmapper.updateByPrimaryKeySelective(userDebtAttorn);
				flagList.add(falg);
			}
			for (int i = 0; i < flagList.size(); i++) {
				if(flagList.get(i)==0){
					flag = false;
					break;
				}
			}
		}
		return flag;
	}
	/**
	 * 更新出让人的账户记录和收支记录表
	 */
	@Override
	public void updateUserAccount(HuifuParams huifuParams, UserDebtAttorn userdebtattorn,String orderno) {
		/**============================================保存出让人用户账户记录====================================================*/
		 //查询转让人用户的可用余额
		 UserAccount userAccount = userAccountMapper.getUserAccountByBaseId(userdebtattorn.getBaseid());
		 //承接人付给出让人的钱,也就是出让人的收入
		 Double creditdealamt = Double.valueOf(huifuParams.getCreditDealAmt());
		 /**可用余额*/
		 Double kyamount = Arith.add(userAccount.getAvlbalance(), creditdealamt);
		 /**总金额*/
		 Double TotalAmount = Arith.add(userAccount.getFreezebalance(),kyamount);
		 saveBargainorAccInExRecord(userdebtattorn,huifuParams,orderno,kyamount,TotalAmount,userAccount.getFreezebalance());
		 
		 /**总金额-手续费之后的可用余额*/
		 Double   keyongAmount = Arith.sub(kyamount, Double.valueOf(huifuParams.getFee()));
		 /**总金额2*/
		 Double   TalAmount=  Arith.add(userAccount.getFreezebalance(),keyongAmount);
		 saveBargainorFeeAccInExRecord(userdebtattorn,huifuParams,orderno,keyongAmount,TalAmount,userAccount.getFreezebalance());
		 
		 //保存可用余额和总额
		 userAccount.setAvlbalance(keyongAmount);
		 userAccount.setBalance(TalAmount);
		 userAccountMapper.updateUseraccount(userAccount); //修改转让人用户账户表记录
	}
	/**
	 * 更新承接人的用户账户信息和收支记录信息
	 * @param @param userid
	 * @param @param huifuParams 
	 * @return void
	 * @author jiangxueyou
	 */
	public void updateCJUserAccount(BigDecimal userid,HuifuParams huifuParams,UserDebtAttorn userdebtattorn,String orderno){
		/**============================================保存承接人用户账户记录====================================================*/
		 //查看承接人用户的可用余额
		 UserAccount CJuserAccount = userAccountMapper.getUserAccountByBaseId(userid);
		 //承接人承接完债转后,剩余的可用余额
		 Double acbalance = Arith.sub(CJuserAccount.getAvlbalance(), Double.valueOf(huifuParams.getCreditDealAmt()));
		 Double zongamount = Arith.add(acbalance, CJuserAccount.getFreezebalance());
		 saveUndertakePersonAccInExRecord(huifuParams,userid,orderno,acbalance,zongamount,CJuserAccount.getFreezebalance());
		 //债转完成后的,承接人的总额
		 CJuserAccount.setAvlbalance(acbalance);
		 CJuserAccount.setBalance(zongamount);
		 userAccountMapper.updateUseraccount(CJuserAccount);//修改承接用户账户表记录
	}
	/**保存出让的收支记录:主要指收入*/
	public int saveBargainorAccInExRecord(UserDebtAttorn userdebtattorn,HuifuParams huifuParams,String orderno,Double kyamount,Double TotalAmount,Double Freezebalance){
		AccInExRecord aExRecord = new  AccInExRecord();
		aExRecord.setBaseid(userdebtattorn.getBaseid());//用户id
		aExRecord.setAieorderno(StringUtil.getNo());//收支记录流水号
		aExRecord.setBorderno(orderno);//业务流水号
		aExRecord.setType((short)31);//表示业务类型为:债转
		aExRecord.setInamount(Double.valueOf(huifuParams.getCreditDealAmt()));//收入
		aExRecord.setOutamount(0.00);//支出
		aExRecord.setPaccount("");//平台账户
		aExRecord.setPinamount(0.00);//平台收入
		aExRecord.setPoutamount(0.00);//平台支出
		aExRecord.setStatus((short)1);//状态:1成功 2,失败
		aExRecord.setDescription("债转出让人收入");//说明
		aExRecord.setBalance(kyamount);//可用余额
		aExRecord.setFreebalance(Freezebalance);//冻结余额
		aExRecord.setTotalbalance(TotalAmount);//总金额
		aExRecord.setRecordtime(new Date());//发生时间
		aExRecord.setRemark("出让人收入记录");//备注
		return accInExRecordMapper.insertSelective(aExRecord);
	}
	/**保存出让人的手续费收支记录*/
	public int saveBargainorFeeAccInExRecord(UserDebtAttorn userdebtattorn,HuifuParams huifuParams,String orderno,Double keyongAmount,Double TalAmount,
			Double Freezebalance){
		AccInExRecord aExRecord = new  AccInExRecord();
		aExRecord.setBaseid(userdebtattorn.getBaseid());//用户id
		aExRecord.setAieorderno(StringUtil.getNo());//收支记录流水号
		aExRecord.setBorderno(orderno);//业务流水号
		aExRecord.setType((short)14);//表示业务类型为:债转手续费
		aExRecord.setInamount(0.00);//收入
		aExRecord.setOutamount(Double.valueOf(huifuParams.getFee()));//支出
		aExRecord.setPaccount("MDT000001");//平台账户
		aExRecord.setPinamount(Double.valueOf(huifuParams.getFee()));//平台收入
		aExRecord.setPoutamount(0.00);//平台支出
		aExRecord.setStatus((short)1);//状态:1成功 2,失败
		aExRecord.setDescription("债转出让人手续费");//说明
		aExRecord.setBalance(keyongAmount);//可用余额
		aExRecord.setFreebalance(Freezebalance);//冻结余额
		aExRecord.setTotalbalance(TalAmount);//总金额
		aExRecord.setRecordtime(new Date());//发生时间
		aExRecord.setRemark("手续费支出记录");//备注
		return accInExRecordMapper.insertSelective(aExRecord);
	}
	/**保存承接人的收支记录:主要指出*/
	public int saveUndertakePersonAccInExRecord(HuifuParams huifuParams,BigDecimal userid,String orderno,Double acbalance,Double zongamount,Double Freezebalance){
		AccInExRecord aExRecord = new  AccInExRecord();
		aExRecord.setBaseid(userid);//用户id
		aExRecord.setAieorderno(StringUtil.getNo());//收支记录流水号
		aExRecord.setBorderno(orderno);//业务流水号
		aExRecord.setType((short)31);//表示业务类型为:债转
		aExRecord.setInamount(0.00);//收入
		aExRecord.setOutamount(Double.valueOf(huifuParams.getCreditDealAmt()));//支出
		aExRecord.setPaccount("");//平台账户
		aExRecord.setPinamount(0.00);//平台收入
		aExRecord.setPoutamount(0.00);//平台支出
		aExRecord.setStatus((short)1);//状态:1成功 2,失败
		aExRecord.setDescription("债转承接人支出");//说明
		aExRecord.setBalance(acbalance);//可用余额
		aExRecord.setFreebalance(Freezebalance);//冻结余额
		aExRecord.setTotalbalance(zongamount);//总金额
		aExRecord.setRecordtime(new Date());//发生时间
		aExRecord.setRemark("承接人支出记录");//备注
		return accInExRecordMapper.insertSelective(aExRecord);
	}
	/**
	 * 更新投标记录
	 * @param @param huifuParams
	 * @param @param daorderno
	 * @return void
	 * @author jiangxueyou
	 */
	public int updateUserTender(HuifuParams huifuParams,String OrdId){
		/**修改债转标的状态和添加时间*/
		System.out.println(huifuParams);
		//查询当前操作的债转标的信息,修改其投标状态和承接时间(转让时间)
		UserTender userTender = userTendermapper.findUserTenderByOrderno(OrdId);
		userTender.setTstatus((short)4);//投标的状态(0.初始，1.待审核，2.失败，3.撤销，4.已完成)
		userTender.setDadate(new Date());//转让时间
		userTender.setTendtime(new Date());//投标结束时间
		Double BidCreditAmt = Double.valueOf(huifuParams.getCreditAmt());//本次债转出的本金
		Double restamountsj = Arith.sub(userTender.getRestamount(), BidCreditAmt);//此标的剩余金额
		userTender.setRestamount(restamountsj);//修改剩余金额
		return userTendermapper.updateByPrimaryKeySelective(userTender);
	}
	@Override
	public List<UserDebtAttorn> selectByTorderNo(String torderno) {
		return userDebtAttornmapper.selectByTorderNo(torderno);
	}
	/**
	 * 获取提前还款的最大的那一期
	 * @param @param tender
	 * @param @return
	 * @return RepayMent
	 * @author jiangxueyou
	 */
	public RepayMent prepayment(UserTender tender){
		List<RepayMent> repayMentList = new ArrayList<RepayMent>();
		RepayMent repayMent = new RepayMent();
		repayMent.setUtorderno(tender.getOrderno());
		repayMent.setTenderid(tender.getTenderid());
		repayMent.setInaccountid(tender.getOutaccountid());
		List<RepayMent> rMentList = repayMentmapper.selectRepayMentList(repayMent);
		for (int i = 0; i < rMentList.size(); i++) {
			if (rMentList.get(i).getRepaystatus()==3) {
				repayMentList.add(rMentList.get(i));
			}
		}
		RepayMent rMent = null;
		if(repayMentList!=null && repayMentList.size()!=0){
			int maxpriods = maxpriods(repayMentList);
			rMent = everyRepayment(tender,maxpriods);
		}
		return rMent;
	}
	/**
	 * 获取提前还款那几期中的最大期数
	 * @param @param ubList3
	 * @param @return
	 * @return double
	 * @author jiangxueyou
	 */
	public int maxpriods(List<RepayMent> rMentList) {
		int maxpriods = 0;
		int totalCount = rMentList.size();
		if (totalCount >= 1) {
			int max = rMentList.get(0).getPeriods();
			for (int i = 0; i < totalCount; i++) {
				int temp = rMentList.get(i).getPeriods();
				if (temp > max) {
					max = temp;
				}
			}
			maxpriods = max;
		}
		return maxpriods;
	}
	/**
	 * 获取利息债转系数的限制
	 * @param @param userTender
	 * @param @return
	 * @return Double
	 * @author jiangxueyou
	 */
	public Double debtInterest(UserTender userTender){
		Double dlixi = coeffcient(userTender);
		/**第四步:算利息债转系数:公式:k=((债转金额+dlixi)-金额实际成交价)/此标本期债转出去的利息/*/
 		Double k = Arith.div(Arith.sub(Arith.add(userTender.getDaamount(),dlixi),userTender.getTotaldaamount()),userTender.getNomalfee(),2);
		return k;
	}
/**查询没有还款的最小期数对象*/
	/*public RepayMent getPeriodsforRepayMent(UserTender tender){
		RepayMent repayMent = new RepayMent();
		repayMent.setUtorderno(tender.getOrderno());
		repayMent.setTenderid(tender.getTenderid());
		repayMent.setInaccountid(tender.getOutaccountid());
		repayMentmapper.selectRepayMentList(repayMent);
		return repayMentmapper.getPeriodsforRepayMent(repayMent);
		
	}*/
	/**
	 * 查询已经还款的金额
	 * @param @param tender
	 * @param @return
	 * @return Double
	 * @author jiangxueyou
	 */
	public Double selectAlreadyRepaymentAmount(UserDebtAttorn userdebtattorn){
		UserTender tender = userTendermapper.findUserTenderByOrderno(userdebtattorn.getTorderno());
		RepayMent repayMent = new RepayMent();
		repayMent.setUtorderno(tender.getOrderno());
		repayMent.setTenderid(tender.getTenderid());
		repayMent.setInaccountid(tender.getOutaccountid());
		repayMentmapper.selectRepayMentList(repayMent);
		Double alreadyRepaymentAmount = repayMentmapper.selectAlreadyRepaymentAmount(repayMent);
		return alreadyRepaymentAmount;
		
	}
	/**
	 * 获取此笔债转金额预计可以获取多少利息
	 * @param @param userTender
	 * @param @param coefficient
	 * @param @return
	 * @return boolean
	 * @author jiangxueyou
	 */
	public Double coeffcient(UserTender userTender){
		RepayMent repayMent = selectRepayMent(userTender);
		Double lixi = Arith.add(repayMent.getRinterest(), repayMent.getRestamountintprofit());
		System.out.println(lixi);
		/**第三步:等比例获取此笔债转金额承接人能获取到的利息*/
		Double dlixi = Arith.div(Arith.mul(userTender.getDaamount(), lixi), repayMent.getRestprincipal(),2);
		return dlixi;
	}
	/**
	 * 算出金额债转系数的范围
	 * @param @param userTender
	 * @param @return
	 * @return Double
	 * @author jiangxueyou
	 */
	public Double Amountcoeffcient(UserTender userTender){
		//获取此笔标的收益
		Double dlixi = coeffcient(userTender);
		//算出金额债转系数范围
		Double k = Arith.div(Arith.add(userTender.getDaamount(), dlixi),userTender.getDaamount(),2);
		return k;
	}
	/**主要用于做判断是在哪一期可以发生债转
	 * 正常标的时候查询当期还款对象
	 * @param @param userTender
	 * @param @return
	 * @return RepayMent
	 * @author jiangxueyou
	 */
	/*public RepayMent selectRmt(UserTender userTender){
		RepayMent repayMent = null;
		Date date = new Date();
		if(userTender.getLatamark().equals("Y")){//此标在逾期宽限期内的标志
			*//**第一步:获取当前标的前一期的还款计划(也就是小于等于当前时间的最大的一期还款计划)*//*
			repayMent =frontObject(userTender, date);
		}else{//此标正常情况下
			*//**第一步:获取当前标的还款当期还款计划(也就是大于等于当前时间的最小一期的还款计划)*//*
			repayMent =returnObject(userTender, date);
			if(repayMent.getRepaystatus()==2 || repayMent.getRepaystatus()==3){//假如当期的还款状态为提前还款,那么就查询
				repayMent =  getPeriodsforRepayMent(userTender);
			}
		}
		return repayMent;
	}*/
	/**
	 * 查询没有还款的当期计划
	 */
	@Override
	public RepayMent selectRepayMent(UserTender userTender) {
		RepayMent repay = new RepayMent();
		System.out.println(userTender.getTenderid());
		System.out.println(userTender.getOrderno());
		System.out.println(userTender.getOutaccountid());
		repay.setTenderid(userTender.getTenderid());
		repay.setUtorderno(userTender.getOrderno());
		repay.setInaccountid(userTender.getOutaccountid());
		return repayMentmapper.selectRepayMent(repay);
	}
	/**
	 * 返回债转还款时间+宽限期后的时间
	 * @param @param repayment
	 * @param @param tenderItem
	 * @param @return
	 * @return Date
	 * @author jiangxueyou
	 */
	public Date returnDate(RepayMent repayment,TenderItem tenderItem){
		Calendar cal = Calendar.getInstance();
		cal.setTime(repayment.getRtime());
		// 债转宽限期
		int graceperiod = 1;//tenderItem.getGraceperiod().intValue();
		// 获取债转宽限日期后的时间
		cal.add(Calendar.DAY_OF_MONTH, graceperiod + 1);
		return cal.getTime();
	}
	@Override
	public List<UserDebtAttorn> getAllListCode(UserDebtAttorn ub) {
		return userDebtAttornmapper.getAllListCode(ub);
	}

}
