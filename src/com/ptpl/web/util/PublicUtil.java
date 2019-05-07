package com.ptpl.web.util;

import com.ptpl.mapper.GuaranteeInfoMapper;
import com.ptpl.mapper.LoanTypeObjectQuoteMapper;
import com.ptpl.mapper.RemoveNameMapper;
import com.ptpl.mapper.UserGradeMapper;
import com.ptpl.model.*;
import com.ptpl.service.BaseService;
import com.ptpl.service.TenderItemServiceI;
import com.ptpl.service.UserGradeServiceI;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PublicUtil {
	/**
	 * 包装list集合中对象的用户等级，
	 * @param list
	 * @param userGrade
	 * @param removeName
	 * @param guaranteeInfoMapper
	 * @return
	 */
	public static List decorateloanList(List<? extends Object> list,LoanTypeObjectQuoteMapper objectQuoteMapper){
		for(Object obj : list){
			if(obj instanceof LoanInfoNeed){
				LoanInfoNeed loanInfoNeed=(LoanInfoNeed)obj;
				String quoteObject=loanInfoNeed.getQuoteobject();//获取应用对象
				if(quoteObject!=null&&!quoteObject.equals("")){
					//把“11001011”变成list集合：[1,2,5,7,8]
					List<Integer> ulist = StringUtil.parsStringToList(quoteObject);
					 StringBuffer sb=new StringBuffer();
					 //迭代集合[1,2,5,7,8]，
					 if(ulist.size()>0){
						 for(Integer interger:ulist){
							 LoanTypeObjectQuote objectQuote=new LoanTypeObjectQuote();
							 objectQuote.setSerialno(interger.shortValue());
							 //以集合中的每一个元素作为条件去标的类型设置表中找出相应标并取出标的名称
							 List<LoanTypeObjectQuote> objectQuotes=objectQuoteMapper.gettypeObjectQuotes(objectQuote);
							 if(objectQuotes.size()>0){
								 sb.append(objectQuotes.get(0).getObjectname()+",");
							 }
						 }
						 loanInfoNeed.setQuoteobject(sb.toString());
					 }
				}
			}else if(obj instanceof LoanInfoPreset){
				LoanInfoPreset loanInfoPreset=(LoanInfoPreset)obj;
				String quoteObject=loanInfoPreset.getQuoteobject();//获取应用对象
				if(quoteObject!=null&&!quoteObject.equals("")){
					//把“11001011”变成list集合：[1,2,5,7,8]
					List<Integer> ulist = StringUtil.parsStringToList(quoteObject);
					 StringBuffer sb=new StringBuffer();
					 //迭代集合[1,2,5,7,8]，
					 if(ulist.size()>0){
						 for(Integer interger:ulist){
							 LoanTypeObjectQuote objectQuote=new LoanTypeObjectQuote();
							 objectQuote.setSerialno(interger.shortValue());
							 //以集合中的每一个元素作为条件去会员等级表中找出相应的会员等级并取出会员等级的名称
							 List<LoanTypeObjectQuote> objectQuotes=objectQuoteMapper.gettypeObjectQuotes(objectQuote);
							 if(objectQuotes.size()>0){
								 sb.append(objectQuotes.get(0).getObjectname()+",");
							 }
						 }
						 loanInfoPreset.setQuoteobject(sb.toString());
					 }
				}
			}else if(obj instanceof LoanItemQuote){
				LoanItemQuote loanItemQuote=(LoanItemQuote)obj;
				String quoteObject=loanItemQuote.getInfotype();//获取应用对象
				if(quoteObject!=null&&!quoteObject.equals("")){
					//把“11001011”变成list集合：[1,2,5,7,8]
					List<Integer> ulist = StringUtil.pars(quoteObject);
					 StringBuffer sb=new StringBuffer();
					 //迭代集合[1,2,5,7,8]，
					 if(ulist.size()>0){
						 for(Integer interger:ulist){
							 LoanTypeObjectQuote objectQuote=new LoanTypeObjectQuote();
							 objectQuote.setSerialno(interger.shortValue());
							 //以集合中的每一个元素作为条件去会员等级表中找出相应的会员等级并取出会员等级的名称
							 List<LoanTypeObjectQuote> objectQuotes=objectQuoteMapper.gettypeObjectQuotes(objectQuote);
							 if(objectQuotes.size()>0){
								 sb.append(objectQuotes.get(0).getObjectname()+",");
							 }
						 }
						 loanItemQuote.setInfotype(sb.toString());
					 }
				}
			}
		}
		return list;
	}
	/**
	 * 包装list集合中对象的用户等级，小名单id，担保公司id转为相应的等级名称，小名单名称，担保公司名称
	 * @param list
	 * @param userGrade
	 * @param removeName
	 * @param guaranteeInfoMapper
	 * @return
	 */
	public static List decorateList(List<? extends Object> list,UserGradeMapper userGrade,RemoveNameMapper removeName,GuaranteeInfoMapper guaranteeInfoMapper) {
		 for (Object obj : list) {
			 if(obj instanceof TenderItem){
				 TenderItem tenderItem=(TenderItem)obj;
				 //取出每一个TenderItem的允许撤回的会员等级例如：“11001011”
				 String allowcugradeStr = tenderItem.getAllowcugrade();
				 if(allowcugradeStr!=null){
					 if(!allowcugradeStr.contains("0")&&allowcugradeStr.length()==userGrade.getUserGradeAll(null).size()){
						 tenderItem.setAllowcugrade("全部等级");
					 }else{
						 //把“11001011”变成list集合：[1,2,5,7,8]
						 List<Integer> ulist = StringUtil.pars(allowcugradeStr);
						 StringBuffer sb=new StringBuffer();
						 //迭代集合[1,2,5,7,8]，
						 for(Integer interger:ulist){
							 UserGrade ug = new UserGrade();
							 ug.setUgrade(new BigDecimal(interger));
							 //以集合中的每一个元素作为条件去会员等级表中找出相应的会员等级并取出会员等级的名称
							 List<UserGrade> uglist = userGrade.getUserGradeAll(ug);
							 if(uglist.size()>0){
								 sb.append(uglist.get(0).getUgradename()+" ");
							 }
						 }
						 tenderItem.setAllowcugrade(sb.toString());
					 }
				 }
				//获取：约标中允许投标的会员等级
				String ugrestrictStr = tenderItem.getUgrestrict();
				 if(ugrestrictStr!=null){
					 if(!ugrestrictStr.contains("0")&&ugrestrictStr.length()==userGrade.getUserGradeAll(null).size()){
						 tenderItem.setUgrestrict("全部等级");
					 }else{
						 //把“11001011”变成list集合：[1,2,5,7,8]
						 List<Integer> ulist = StringUtil.pars(ugrestrictStr);
						 StringBuffer sb=new StringBuffer();
						 //迭代集合[1,2,5,7,8]，
						 for(Integer interger:ulist){
							 UserGrade ug = new UserGrade();
							 ug.setUgrade(new BigDecimal(interger));
							 //以集合中的每一个元素作为条件去会员等级表中找出相应的会员等级并取出会员等级的名称
							 List<UserGrade> uglist = userGrade.getUserGradeAll(ug);
							 if(uglist.size()>0){
								 sb.append(uglist.get(0).getUgradename()+" ");
							 }
						 }
						 tenderItem.setUgrestrict(sb.toString());
					 }
				 }
				 //1.获取债转排除名单编号nameno
				 String removenameno = tenderItem.getRemovenameno();
				 if(removenameno!=null){
					 //2.转变成数组
					 String[] removenamenoArr = removenameno.split(",");
					 StringBuffer sb = new StringBuffer();
					 //3.迭代数组
					 for (String string : removenamenoArr) {
						 //3.1根据名单编号找出名单名称
						 String name = removeName.selectNameForNameNo(string);
						 sb.append(name+" ");
					 }
					 tenderItem.setRemovenameno(sb.toString());
				 }
				 //装饰投标客户端来源限制
				 String crestrict = tenderItem.getCrestrict();
				 String[] crestrictArr = crestrict.split(",");
				 StringBuffer sb = new StringBuffer();

				 for(int i=0;i<crestrictArr.length;i++){
					 String temp=crestrictArr[i].trim();
					 if(temp.equals("d")){
						 sb.append("PC  ");
					 }else if(temp.equals("a")){
						 sb.append("安卓  ");
					 }else if(temp.equals("w")){
						 sb.append("微信  ");
					 }else if(temp.equals("p")){
						 sb.append("苹果  ");
					 }else if(temp.equals("o")){
						 sb.append("wap  ");
					 }
				 }
				 tenderItem.setCrestrict(sb.toString());
	 }else if(obj instanceof RiskGuarantyMoney){
						 RiskGuarantyMoney riskGuarantyMoney=(RiskGuarantyMoney)obj;
						 //取出每一个RiskGuarantyMoney的会员等级例如：“11001011”
						 String ugradeStr = riskGuarantyMoney.getUgrade();
						 if(ugradeStr!=null){
							 if(!ugradeStr.contains("0")&&ugradeStr.length()==userGrade.getUserGradeAll(null).size()){
								 riskGuarantyMoney.setUgrade("全部等级");
							 }else{
								 //把“11001011”变成list集合：[1,2,5,7,8]
								 List<Integer> ulist = StringUtil.pars(ugradeStr);
								 StringBuffer sb=new StringBuffer();
								 //迭代集合[1,2,5,7,8]，
								 for(Integer interger:ulist){
									 UserGrade ug = new UserGrade();
									 ug.setUgrade(new BigDecimal(interger));
									 //以集合中的每一个元素作为条件去会员等级表中找出相应的会员等级并取出会员等级的名称
									 List<UserGrade> uglist = userGrade.getUserGradeAll(ug);
									 if(uglist.size()>0){
										 sb.append(uglist.get(0).getUgradename()+" ");
									 }
								 }
								 riskGuarantyMoney.setUgrade(sb.toString());
							 }
						 }
			 }else if(obj instanceof InterestExpense){
				 InterestExpense interestExpense=(InterestExpense)obj;
				 //取出每一个RiskGuarantyMoney的会员等级例如：“11001011”
				 String ugradeStr = interestExpense.getUgrade();
				 if(ugradeStr!=null){
					 if(!ugradeStr.contains("0")&&ugradeStr.length()==userGrade.getUserGradeAll(null).size()){
						 interestExpense.setUgrade("全部等级");
					 }else{
						 //把“11001011”变成list集合：[1,2,5,7,8]
						 List<Integer> ulist = StringUtil.pars(ugradeStr);
						 StringBuffer sb=new StringBuffer();
						 //迭代集合[1,2,5,7,8]，
						 for(Integer interger:ulist){
							 UserGrade ug = new UserGrade();
							 ug.setUgrade(new BigDecimal(interger));
							 //以集合中的每一个元素作为条件去会员等级表中找出相应的会员等级并取出会员等级的名称
							 List<UserGrade> uglist = userGrade.getUserGradeAll(ug);
							 if(uglist.size()>0){
								 sb.append(uglist.get(0).getUgradename()+" ");
							 }
						 }
						 interestExpense.setUgrade(sb.toString());
					 }
				 }
			 }else if(obj instanceof DebtAttorn){
								 DebtAttorn debtAttorn=(DebtAttorn)obj;
								 //取出每一个DebtAttorn的会员等级例如：“11001011”
								 String ugradeStr = debtAttorn.getUgrade();
								 if(ugradeStr!=null){
									 if(!ugradeStr.contains("0")&&ugradeStr.length()==userGrade.getUserGradeAll(null).size()){
										 debtAttorn.setUgrade("全部等级");
									 }else{
										 //把“11001011”变成list集合：[1,2,5,7,8]
										 List<Integer> ulist = StringUtil.pars(ugradeStr);
										 StringBuffer sb=new StringBuffer();
										 //迭代集合[1,2,5,7,8]，
										 for(Integer interger:ulist){
											 UserGrade ug = new UserGrade();
											 ug.setUgrade(new BigDecimal(interger));
											 //以集合中的每一个元素作为条件去会员等级表中找出相应的会员等级并取出会员等级的名称
											 List<UserGrade> uglist = userGrade.getUserGradeAll(ug);
											 if(uglist.size()>0){
												 sb.append(uglist.get(0).getUgradename()+" ");
											 }
										 }
										 debtAttorn.setUgrade(sb.toString());
									 }
								 }
								 String aownergrade = debtAttorn.getAownergrade();
								 if(aownergrade!=null){
									 if(!aownergrade.contains("0")&&aownergrade.length()==userGrade.getUserGradeAll(null).size()){
										 debtAttorn.setAownergrade("全部等级");
									 }else{
										 //把“11001011”变成list集合：[1,2,5,7,8]
										 List<Integer> ulist = StringUtil.pars(aownergrade);
										 StringBuffer sb=new StringBuffer();
										 //迭代集合[1,2,5,7,8]，
										 for(Integer interger:ulist){
											 UserGrade ug = new UserGrade();
											 ug.setUgrade(new BigDecimal(interger));
											 //以集合中的每一个元素作为条件去会员等级表中找出相应的会员等级并取出会员等级的名称
											 List<UserGrade> uglist = userGrade.getUserGradeAll(ug);
											 if(uglist.size()>0){
												 sb.append(uglist.get(0).getUgradename()+" ");
											 }
										 }
										 debtAttorn.setAownergrade(sb.toString());
									 }
								 }
								 String apurchasergrade = debtAttorn.getApurchasergrade();
								 
								 if(apurchasergrade!=null){
									 if(!apurchasergrade.contains("0")&&apurchasergrade.length()==userGrade.getUserGradeAll(null).size()){
										 debtAttorn.setApurchasergrade("全部等级");
									 }else{
										 //把“11001011”变成list集合：[1,2,5,7,8]
										 List<Integer> ulist = StringUtil.pars(apurchasergrade);
										 StringBuffer sb=new StringBuffer();
										 //迭代集合[1,2,5,7,8]，
										 for(Integer interger:ulist){
											 UserGrade ug = new UserGrade();
											 ug.setUgrade(new BigDecimal(interger));
											 //以集合中的每一个元素作为条件去会员等级表中找出相应的会员等级并取出会员等级的名称
											 List<UserGrade> uglist = userGrade.getUserGradeAll(ug);
											 if(uglist.size()>0){
												 sb.append(uglist.get(0).getUgradename()+" ");
											 }
										 }
										 debtAttorn.setApurchasergrade(sb.toString());
									 }
								 }
								 //1.获取债转排除名单编号nameno
								 String removenameno = debtAttorn.getRemovenameno();
								 if(removenameno!=null){
									 //2.转变成数组
									 String[] removenamenoArr = removenameno.split(",");
									 StringBuffer sb = new StringBuffer();
									 //3.迭代数组
									 for (String string : removenamenoArr) {
										 //3.1根据名单编号找出名单名称
										 String name = removeName.selectNameForNameNo(string);
										 sb.append(name+" ");
									 }
									 debtAttorn.setRemovenameno(sb.toString());
								 }
								 
								 //1.不允许购买的用户名单表编号noapnameno
								 String noapnameno = debtAttorn.getNoapnameno();
								 if(noapnameno!=null){
									 //2.转变成数组
									 String[] noapnamenoArr = noapnameno.split(",");
									 StringBuffer sb = new StringBuffer();
									 //3.迭代数组
									 for (String string : noapnamenoArr) {
										 //3.1根据名单编号找出名单名称
										 String name = removeName.selectNameForNameNo(string);
										 sb.append(name+" ");
									 }
									 debtAttorn.setNoapnameno(sb.toString());
								 }
								 
			 	}else if(obj instanceof OverdueCompensate){
			 			OverdueCompensate overdueCompensate=(OverdueCompensate)obj;
						 //取出每一个RiskGuarantyMoney的会员等级例如：“11001011”
						 String ugradeStr = overdueCompensate.getUgrade();
						 if(ugradeStr!=null){
							 if(!ugradeStr.contains("0")&&ugradeStr.length()==userGrade.getUserGradeAll(null).size()){
								 overdueCompensate.setUgrade("全部等级");
							 }else{
								 //把“11001011”变成list集合：[1,2,5,7,8]
								 List<Integer> ulist = StringUtil.pars(ugradeStr);
								 StringBuffer sb=new StringBuffer();
								 //迭代集合[1,2,5,7,8]，
								 for(Integer interger:ulist){
									 UserGrade ug = new UserGrade();
									 ug.setUgrade(new BigDecimal(interger));
									 //以集合中的每一个元素作为条件去会员等级表中找出相应的会员等级并取出会员等级的名称
									 List<UserGrade> uglist = userGrade.getUserGradeAll(ug);
									 if(uglist.size()>0){
										 sb.append(uglist.get(0).getUgradename()+" ");
									 }
								 }
								 overdueCompensate.setUgrade(sb.toString());
							 }
						 }
			 	}else if(obj instanceof FailTCompensate){
			 		FailTCompensate failTCompensate=(FailTCompensate)obj;
					 //取出每一个RiskGuarantyMoney的会员等级例如：“11001011”
					 String ugradeStr = failTCompensate.getIntugrade();
					 if(ugradeStr!=null){
						 if(!ugradeStr.contains("0")&&ugradeStr.length()==userGrade.getUserGradeAll(null).size()){
							 failTCompensate.setIntugrade("全部等级");
						 }else{
							 //把“11001011”变成list集合：[1,2,5,7,8]
							 List<Integer> ulist = StringUtil.pars(ugradeStr);
							 StringBuffer sb=new StringBuffer();
							 //迭代集合[1,2,5,7,8]，
							 for(Integer interger:ulist){
								 UserGrade ug = new UserGrade();
								 ug.setUgrade(new BigDecimal(interger));
								 //以集合中的每一个元素作为条件去会员等级表中找出相应的会员等级并取出会员等级的名称
								 List<UserGrade> uglist = userGrade.getUserGradeAll(ug);
								 if(uglist.size()>0){
									 sb.append(uglist.get(0).getUgradename()+" ");
								 }
							 }
							 failTCompensate.setIntugrade(sb.toString());
						 }
					 }
				 }else if(obj instanceof GfundsInt){
					 GfundsInt gfundsInt=(GfundsInt)obj;
						 //取出每一个GfundsInt的会员等级例如：“11001011”
						 String ugradeStr = gfundsInt.getUgrade();
						 if(ugradeStr!=null){
							 if(!ugradeStr.contains("0")&&ugradeStr.length()==userGrade.getUserGradeAll(null).size()){
								 gfundsInt.setUgrade("全部等级");
							 }else{
								 //把“11001011”变成list集合：[1,2,5,7,8]
								 List<Integer> ulist = StringUtil.pars(ugradeStr);
								 StringBuffer sb=new StringBuffer();
								 //迭代集合[1,2,5,7,8]，
								 for(Integer interger:ulist){
									 UserGrade ug = new UserGrade();
									 ug.setUgrade(new BigDecimal(interger));
									 //以集合中的每一个元素作为条件去会员等级表中找出相应的会员等级并取出会员等级的名称
									 List<UserGrade> uglist = userGrade.getUserGradeAll(ug);
									 if(uglist.size()>0){
										 sb.append(uglist.get(0).getUgradename()+" ");
									 }
								 }
								 gfundsInt.setUgrade(sb.toString());
							 }
						 }
					 }else if(obj instanceof MediacyFee){
				 	//封装居间费中的会员等级
					 MediacyFee mediacyFee=(MediacyFee)obj;
						 //取出每一个GfundsInt的会员等级例如：“11001011”
						 String ugradeStr = mediacyFee.getUgrade();
						 if(ugradeStr!=null){
							 if(!ugradeStr.contains("0")&&ugradeStr.length()==userGrade.getUserGradeAll(null).size()){
								 mediacyFee.setUgrade("全部等级");
							 }else{
								 //把“11001011”变成list集合：[1,2,5,7,8]
								 List<Integer> ulist = StringUtil.pars(ugradeStr);
								 StringBuffer sb=new StringBuffer();
								 //迭代集合[1,2,5,7,8]，
								 for(Integer interger:ulist){
									 UserGrade ug = new UserGrade();
									 ug.setUgrade(new BigDecimal(interger));
									 //以集合中的每一个元素作为条件去会员等级表中找出相应的会员等级并取出会员等级的名称
									 List<UserGrade> uglist = userGrade.getUserGradeAll(ug);
									 if(uglist.size()>0){
										 sb.append(uglist.get(0).getUgradename()+" ");
									 }
								 }
								 mediacyFee.setUgrade(sb.toString());
							 }
						 }
					 }else if(obj instanceof GuaranteeFee){
						//封装担保费的会员等级
					 GuaranteeFee guaranteeFee=(GuaranteeFee)obj;
						 //取出每一个GfundsInt的会员等级例如：“11001011”
						 String ugradeStr = guaranteeFee.getUgrade();
						 if(ugradeStr!=null){
							 if(!ugradeStr.contains("0")&&ugradeStr.length()==userGrade.getUserGradeAll(null).size()){
								 guaranteeFee.setUgrade("全部等级");
							 }else{
								 //把“11001011”变成list集合：[1,2,5,7,8]
								 List<Integer> ulist = StringUtil.pars(ugradeStr);
								 StringBuffer sb=new StringBuffer();
								 //迭代集合[1,2,5,7,8]，
								 for(Integer interger:ulist){
									 UserGrade ug = new UserGrade();
									 ug.setUgrade(new BigDecimal(interger));
									 //以集合中的每一个元素作为条件去会员等级表中找出相应的会员等级并取出会员等级的名称
									 List<UserGrade> uglist = userGrade.getUserGradeAll(ug);
									 if(uglist.size()>0){
										 sb.append(uglist.get(0).getUgradename()+" ");
									 }
								 }
								 guaranteeFee.setUgrade(sb.toString());
							 }
						 }
					 }
		}
		return list;
	}

	/**
	 * 该方法是用于判断这个方法是通过设置标的时候过来的还是点新增的时候过来的
	 * @param request
	 * @param url
	 * @return
	 */
	public  static ModelAndView changeUrlForTenderItem(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		//如果是设置标的时候转跳过来的就按以下代码处理
		HttpSession session = request.getSession();
		ArrayList<String> urlList = (ArrayList<String>) session.getAttribute("urlList");
		if(urlList!=null&&urlList.size()>0){
			modelAndView.setViewName(urlList.get(0));
			urlList.remove(0);
			if(urlList.size()>0){
				session.setAttribute("urlList", urlList);
			}else{
				session.removeAttribute("nextPage");
				session.removeAttribute("urlList");
			}
			return  modelAndView;
		}else{
			//如果是点击新增标的相关设置过来的就返回Null,在jsp页面执行回调函数的转跳
			return null;
		}
	}

	/**
	 * 点击返回列表时清空session中的urlList（防止设置标，用户进入到相应设置时用点击了返回列表造成session中有还有urlList，从而影响到单独设置时点保存跳到session中urlList指定的页面）
	 * @param request
	 */
	public static void removeFormSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ArrayList<String> urlList = (ArrayList<String>) session.getAttribute("urlList");
			session.removeAttribute("urlList");
	       //tenderItemId也要删除，如果不删除：用户单独进标的相关设置进行增加操作时如果有这个id就会set标
			session.removeAttribute("tenderItemId");
			session.removeAttribute("isaudit");
			session.removeAttribute("nextPage");
	}
	//通过id获取信息
	public static TenderItem getTenderItem(BigDecimal tenderItemId, TenderItemServiceI tenderItemService) {
		TenderItem tenderItem1 = tenderItemService.findTenderItemById(tenderItemId);
		if(tenderItem1!=null){
			return tenderItem1;
		}else{
			return null;
		}
   }
	
	/**
	 * 把标要设置的相关url找出来并增加到urlList中
	 * @param tenderItem
	 * @param urlList
	 */
	@SuppressWarnings("unchecked")
	public static HashMap<String, String> addUrlToList(TenderItem tenderItem, HashMap<String,String> urlList) {
		Short issetgfundsint = tenderItem.getIssetgfundsint();//站岗利息
		if(issetgfundsint!=null&&issetgfundsint==2){
			urlList.put("站岗利息设置","admin/gfundsInt/insert_GfundsInt_Ui");
		}
		
		Short isintcompensateon=tenderItem.getIsintcompensateon();//流标利息方式补偿开关(利息方式)
		if(isintcompensateon!=null&&isintcompensateon==2){
			urlList.put("流标利息方式补偿设置","admin/failTCompensate/insert_FailTCompensate_Ui");
		}
		
		Short isawardcompensateon=tenderItem.getIsawardcompensateon();//流标奖品补偿方式开关(0关,1，开)(奖品方式)
		if(isawardcompensateon!=null&&isawardcompensateon==2){
			urlList.put("流标奖品补偿设置","admin/failTCompensate/insert_FailTAwardCompensate_Ui");
		}
		
		Short isaoverduec = tenderItem.getIsaoverduec();
		if(isaoverduec!=null&&isaoverduec==2){//是否允许逾期代偿(会员垫付(表1))
			urlList.put("逾期代偿设置","admin/overdueCompensate/insert_OverdueCompensate_Ui");
		/*	urlList.put("逾期滞纳金费率设置","admin/overdueCompensate/insert_overdueFeeRate_UI");*/
		}
		
		Short isaocfee=tenderItem.getIsaocfee();//收取平台追偿费开关(0关,1开)(表2)
		if(isaocfee!=null&&isaocfee==2){
			urlList.put("收取平台追偿费设置","admin/overdueCompensate/insert_overdueRecovery_Ui");
		}
		
		Short isaaheadrepay = tenderItem.getIsaaheadrepay();
		if(isaaheadrepay!=null&&isaaheadrepay==2){//是否允许提前还款(还款方式设置)
			urlList.put("提前还款设置","admin/aheadRepay/insert_AheadRepayMode_Ui");
		}
		
		Short ispicompensateon=tenderItem.getIspicompensateon();//提前还款本金利息补偿开关(0关,1开)
		if(ispicompensateon!=null&&ispicompensateon==2){
			urlList.put("提前还款本金利息补偿设置","admin/aheadRepay/insert_AheadRepay_Ui");
		}
		
		Short ispluscompensateon=tenderItem.getIspluscompensateon();//提前还款增益利息补偿开关(0关,1开)
		if(ispluscompensateon!=null&&ispluscompensateon==2){
			urlList.put("提前还款增益利息补偿设置","admin/aheadRepay/insert_AheadRepayAward_Ui");
		}
			
		Short isforplatformon=tenderItem.getIsforplatformon();//提前还款还款人补偿平台(0,关1开)	
		if(isforplatformon!=null&&isforplatformon==2){
		    urlList.put("提前还款还款人补偿平台设置","admin/aheadRepay/insert_AheadRepayPlatform_Ui");
		}
		
		Short isadebtattorn = tenderItem.getIsadebtattorn();
		if(isadebtattorn!=null&&isadebtattorn==2){//债权转让
			urlList.put("债权转让设置","admin/debtAttorn/insert_DebtAttorn_Ui");
		}

		Short isadebtattornfee=tenderItem.getIsadebtattornfee();//是否收取债转手续费(0关,1开)
		if(isadebtattornfee!=null&&isadebtattornfee==2){
			urlList.put("债转手续费设置","admin/debtAttorn/insert_DebtAttornfee_Ui");
		}
		
		Short isaplus = tenderItem.getIsaplus();
		if(isaplus!=null&&isaplus==2){//增益
			urlList.put("增益设置","admin/plus/insert_Plus_Ui");
		}
		Short isamediacy = tenderItem.getIsamediacy();
		if(isamediacy!=null&&isamediacy==2){//居间服务费
			urlList.put("居间服务费设置","admin/mediacyFee/insert_MediacyFee_Ui");
		}
		
		Short isaguarantee = tenderItem.getIsaguarantee();
		if(isaguarantee!=null&&isaguarantee==2){//担保
			urlList.put("担保设置","admin/guaranteeFee/insert_GuaranteeFee_Ui");
		}
		
		Short isaintexp = tenderItem.getIsaintexp();
		if(isaintexp!=null&&isaintexp==2){//利息管理费
			urlList.put("利息管理费设置","admin/interestExpense/insert_InterestExpense_Ui");
		}
		
		Short isariskgm = tenderItem.getIsariskgm();
		if(isariskgm!=null&&isariskgm==2){//风险保证金
			urlList.put("风险保证金","admin/riskGuarantyMoney/insert_RiskGuarantyMoney_Ui");
		}
		
		return urlList;
	}
	

	public static  void sendJsonData(HttpServletResponse response, String data)
			throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out;
		out = response.getWriter();
		out.println(data);
		out.flush();
		out.close();
	}

	public static  ModelAndView setLastItem(HttpServletRequest request, HttpServletResponse response, TenderItem tenderItem,
			Short isaudit,TenderItemServiceI tenderItemService) throws IOException {
		// 如果标设置为要审核，则标的状态设置为审核中;
		if (isaudit == 1) {
			tenderItem.setTstatus((short) 1);
			// 设置标的相应字段
			tenderItemService.update(tenderItem);
			PublicUtil.removeFormSession(request);
			PublicUtil.sendJsonData(response, "标设置完成");
			return null;
		} else{
			// 如果标设置为不审核，则标的状态设置为待发布;
			tenderItem.setTstatus((short) 3);
			// 设置标的相应字段
			tenderItemService.update(tenderItem);
			PublicUtil.removeFormSession(request);
			PublicUtil.sendJsonData(response, "标设置完成");
			return null;
		}
	}
	public static int getNo(){
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddhhmmss");
		String dateString = formatter.format(currentTime);
		int x=(int)(Math.random()*99999);
		return x;
	}
	/**
	 * 装饰会员等级用于回显：全部等级   选择等级
	 * @param modelAndView:
	 * @param userGradeService:
	 * @param ugrade:
	 * @param gradeNameInJsp：
	 * @param gradesNameInJsp
	 */
	public static void decorateGrade(ModelAndView modelAndView,UserGradeServiceI userGradeService, String ugrade ,String gradeNameInJsp,String gradesNameInJsp) {
		if(ugrade!=null){
			//全部等级 ：如果全是0例如："1111111111111",且长度为会员等级总数的长度，则把ugrade=1传回jsp页面回显全部等级
			if (!ugrade.contains("0")&&ugrade.length()==userGradeService.getAll(null).size()) {
				modelAndView.addObject(gradeNameInJsp,1);
			}else{
				//选择等级：如果不全是1例如："101101"，则把ugrade=2传回jsp页面回显选择等级，并把"101101"转为[1，3，4，6]用于回显checkbox框
				modelAndView.addObject(gradeNameInJsp,2);
				List<Integer> allowcugrades = StringUtil.parsStringToList(ugrade);
				modelAndView.addObject(gradesNameInJsp, allowcugrades);
			}
		}
	}
	/**
	 * 删除标的相关设置之前先判断有没有标引用这个相关设置，如果有就不能删除，如果没有就删除
	 * @param id
	 * @param response
	 * @param tenderItemService
	 * @param bs
	 * @throws IOException
	 */
	public static   void decideBeforeDelete(BigDecimal id,BigDecimal tid, HttpServletResponse response,TenderItemServiceI tenderItemService,BaseService bs) throws IOException{
		TenderItem item=tenderItemService.findTenderItemById(tid);
		if(item!=null){
			if(item.getTstatus().toString().equals("12")){
				bs.delete(id);
				PublicUtil.sendJsonData(response, "删除成功");
			}else{
				PublicUtil.sendJsonData(response, "有标引用这条数据，删除失败");
			}
		}
	}
	
	
	
	//32位小写md5加密
	public static String md5s(String plainText) {
		 StringBuffer buf = new StringBuffer("");
		  try {
		   MessageDigest md = MessageDigest.getInstance("MD5");
		   md.update(plainText.getBytes());
		   byte b[] = md.digest();

		   int i;

		  
		   for (int offset = 0; offset < b.length; offset++) {
		    i = b[offset];
		    if (i < 0)
		     i += 256;
		    if (i < 16)
		     buf.append("0");
		    buf.append(Integer.toHexString(i));
		   }
		  } catch (NoSuchAlgorithmException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		   
		  }
		  return buf.toString();
		 }
	
	/**
	 * @author liuj
	 * @param list去重
	 * */
	public static ArrayList singleElement(ArrayList al)  
    {  
        ArrayList newAl = new ArrayList();  
  
        for(Iterator it = al.iterator(); it.hasNext();)  
        {  
            Object obj = it.next();
            if(!obj.equals(" ")){
            	 if(!newAl.contains(obj))  
 	            {  
 	                newAl.add(obj);  
 	            }  
            }
        }  
        return newAl;  
    }


	public static String updateFiles(@RequestParam("files") CommonsMultipartFile[] files, String newfileName) throws IOException {
		for (CommonsMultipartFile file : files) {
			// 取得当前上传文件的文件名称
			String fname = file.getOriginalFilename();
			System.out.println(fname);
			if (fname != null && fname != "") {
				// 重命名上传后的文件名
				String suffix = fname.substring(fname.lastIndexOf('.'));
				System.out.println("截取文件后缀--->" + suffix);
				String prefix = System.currentTimeMillis() + StringUtil.buildNo("");
				String tempnewfileName = prefix + suffix;
				System.out.println("tempnewfileName:------" + tempnewfileName);
				// 创建文件夹
				File f = null;
				f = new File("/upload/UserCommonMaterial");
				if (!f.exists()) {
					f.mkdirs();
				}
				// 定义上传路径
				String path = f.getPath() + "/" + tempnewfileName;
				//通过CommonsMultipartFile的方法直接写文件（注意这个时候）
				File localFile = new File(path);
				file.transferTo(localFile);
				newfileName += tempnewfileName + ",";
			}
		}
		return newfileName;
	}

	public static void main(String[] args) {
		System.out.println(md5s("20AddBidInfo6000060004166478GJBH20160905162402500000.0012.0060000.00201609232016090500000020160909000000042016092301127213553869016UTF-8http://113.99.86.169:8885/ptpjx/tenderItem/sendTenderItemToHuifuCallback.action "));
	}
}
