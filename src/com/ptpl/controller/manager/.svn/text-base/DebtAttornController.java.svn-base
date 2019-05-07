package com.ptpl.controller.manager;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ptpl.constant.Marknumber;
import com.ptpl.constant.Session_Constant;
import com.ptpl.model.AdminUser;
import com.ptpl.model.DebtAttorn;
import com.ptpl.model.DebtAttornBuyer;
import com.ptpl.model.DebtAttornFee;
import com.ptpl.model.RemoveName;
import com.ptpl.model.SpecialNameList;
import com.ptpl.model.TenderItem;
import com.ptpl.model.UserGrade;
import com.ptpl.service.DattornRNameLinkServiceI;
import com.ptpl.service.DebtAttornBuyerServiceI;
import com.ptpl.service.DebtAttornFeeServiceI;
import com.ptpl.service.DebtAttornServiceI;
import com.ptpl.service.RemoveNameServiceI;
import com.ptpl.service.SpecialNameListServiceI;
import com.ptpl.service.TenderItemServiceI;
import com.ptpl.service.UserGradeServiceI;
import com.ptpl.web.util.PublicUtil;
import com.ptpl.web.util.StringUtil;
import com.ptpl.web.util.UserGradeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
/**
 * @author:liuqh
 * @date:2016年07月12日 19:17:23
 * @description:标的债权转让设置 
 */
@Controller
@RequestMapping("/admin/debtAttorn")
public class DebtAttornController {
	@Autowired
	private DebtAttornServiceI debtAttornService;
	@Autowired
	private UserGradeServiceI userGradeService;
	@Autowired
	private TenderItemServiceI tenderItemService;
	@Autowired
	private RemoveNameServiceI removeNameService;
	@Autowired
	private DattornRNameLinkServiceI dattornRNameLinkService;
	@Autowired
	private DebtAttornBuyerServiceI debtAttornBuyerService;
	@Autowired
	private DebtAttornFeeServiceI debtAttornFeeService;
	@Autowired
	private SpecialNameListServiceI specialNameListService;//定向名单
	// 转发到增加标的债权转让设置 页面
	@RequestMapping(value = "/insert_DebtAttorn_Ui/{tid}", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView insert_DebtAttorn_Ui(@PathVariable String tid) {
		ModelAndView modelAndView = new ModelAndView();
		SpecialNameList nameList=new SpecialNameList();
		nameList.setBusinessType((short) 1);
		List<SpecialNameList> snl2=specialNameListService.getSpecialNameLists(nameList);
		List<SpecialNameList> nameLists=new ArrayList<>();
		String systemBusType = "";
	    for (int i = 0; i < snl2.size(); i++) {
	    	systemBusType=snl2.get(i).getSystemBusType();
	    	for (int j = 0; j < systemBusType.length(); j++) {
	    		char sbt = systemBusType.charAt(j);
	    		if(sbt=='1'){
	    			if(j==1){
	    				nameLists.add(snl2.get(i));
	    			}else{
	    				continue;
	    			}
	    		}
			}
		}
	    if(nameLists.size()>0){
	    	modelAndView.addObject("snl2", nameLists);
	    }
		modelAndView.setViewName("admin/debtAttorn/DebtAttorn_Inst");
		modelAndView.addObject("tid",tid);
		DebtAttorn debtAttorn = debtAttornService.selectByTid(new BigDecimal(tid));
		if(debtAttorn!=null ){
			modelAndView.setViewName("redirect:/admin/debtAttorn/insert_DebtAttornbuyer_Ui/"+tid+".action");
		}
		return modelAndView;
	}
	// 转发到增加标的债权购买人设置 页面
	@RequestMapping(value = "/insert_DebtAttornbuyer_Ui/{tid}", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView insert_DebtAttornbuyer_Ui(@PathVariable String tid) {
		ModelAndView modelAndView = new ModelAndView();
		SpecialNameList nameList=new SpecialNameList();
		nameList.setBusinessType((short) 1);
		List<SpecialNameList> snl2=specialNameListService.getSpecialNameLists(nameList);
		List<SpecialNameList> nameLists=new ArrayList<>();
		String systemBusType = "";
	    for (int i = 0; i < snl2.size(); i++) {
	    	systemBusType=snl2.get(i).getSystemBusType();
	    	for (int j = 0; j < systemBusType.length(); j++) {
	    		char sbt = systemBusType.charAt(j);
	    		if(sbt=='1'){
	    			if(j==2){
	    				nameLists.add(snl2.get(i));
	    			}else{
	    				continue;
	    			}
	    		}
			}
		}
	    if(nameLists.size()>0){
	    	modelAndView.addObject("snl2", nameLists);
	    }
    	modelAndView.addObject("snl2", snl2);
		modelAndView.setViewName("admin/debtAttorn/DebtAttornBuyer_Inst");
		modelAndView.addObject("tid",tid);
		DebtAttornBuyer debtAttornBuyer = debtAttornBuyerService.selectByTid(new BigDecimal(tid));
		if(debtAttornBuyer!=null){
			TenderItem tenderItem=tenderItemService.findTenderItemById(new BigDecimal(tid));
			tenderItem.setIsadebtattorn((short)1);
			tenderItemService.update(tenderItem);
			modelAndView.setViewName("redirect:/admin/tenderItem/tenderItem_tag_UI/"+tid+".action");
		}
		return modelAndView;
		}
	// 转发到增加标的手续费设置页面
	@RequestMapping(value = "/insert_DebtAttornfee_Ui/{tid}", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView insert_DebtAttornfee_Ui(@PathVariable String tid, HttpServletRequest request) throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		List<UserGrade> uGrades = userGradeService.getAll(null);
		modelAndView.setViewName("admin/debtAttorn/debtAttornFee_Inst");
		List<String> gradeList=null;
		if(tid!=null){
			gradeList=debtAttornFeeService.selectGradeByTid(new BigDecimal(tid)); //查找站岗利息表中已设置的会员等级
		}
		modelAndView=  UserGradeUtil.mv(uGrades,gradeList,modelAndView,tid);
		if(gradeList!=null&&gradeList.size()>0) {
			modelAndView.addObject("feeMode", 1);
		}
		modelAndView.addObject("tid",tid);
		if(uGrades.size()<=0){
			TenderItem tenderItem=tenderItemService.findTenderItemById(new BigDecimal(tid));
			tenderItem.setIsadebtattornfee((short)1);
			tenderItemService.update(tenderItem);
			modelAndView.setViewName("redirect:/admin/tenderItem/tenderItem_tag_UI/"+tid+".action");
		}
		return modelAndView;
	 }
	//通过会员等级过滤会员
	@SuppressWarnings("unchecked")
	@RequestMapping(value="seldebBytid",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView seldebattorbytid(BigDecimal tid) throws Exception{
		ModelAndView mv=new ModelAndView();
		DebtAttorn debtAttorn=new DebtAttorn();//创建一个deb实体类
		debtAttorn.setTid(tid);//将tidset进入
		List<DebtAttorn> debtAttorns=debtAttornService.selectByConditionAndDecorateUgrade(debtAttorn);//通过tid查找该tid对应的deb信息
		List<UserGrade> grades=userGradeService.getAll(null);//获取所有的会员等级
		String debtator="";//定义一个str储存会员等级 
		for(DebtAttorn attorn:debtAttorns){
			debtator+=attorn.getUgrade();
		}
		List<String> strings=PublicUtil.singleElement(new ArrayList<>(Arrays.asList(debtator.split(" "))));//将str去空转换成list集合
		List<BigDecimal> bigDecimals=new ArrayList<>();//定义一个big的集合存储会员等级编号
		for (int i = 0; i < grades.size(); i++) {//双重for循环取选中的会员等级编号
			for (int j = 0; j < strings.size(); j++) {
				 if(grades.get(i).getUgradename().equals(strings.get(j))){
					 bigDecimals.add(grades.get(i).getUgrade());
				 }
			}
		}
		List<UserGrade> grades2=userGradeService.getGradeList(bigDecimals);//将取出的会员编号在sql里去重后取出
		mv.addObject("gradestwo", grades2);//响应在jsp上
		mv.addObject("debtattorn", debtAttorns.get(0));
		mv.setViewName("admin/debtAttorn/ ");
		return mv;
	}
	
	//添加债转设置(顺序>>>转让人设置>>>购买人设置>>>手续费设置)
	@RequestMapping(value = "/insertDebtAttorntwo", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView insertDebtAttorntwo(DebtAttorn debtAttorn,String[] aownergrades,HttpServletRequest request,HttpServletResponse response) throws Exception{
        if(debtAttorn.getAownergrade()!=null&&!debtAttorn.getAownergrade().equals("")){//判断非空
        	String aownergrade=debtAttorn.getAownergrade();//获取会员等级
        	List<UserGrade> grades=userGradeService.getAll(null);//获取会员等级
        	if(aownergrade.equals("1")){//全部等级
        		aownergrade=StringUtil.getPlaceholder(grades.size());
        		for (UserGrade userGrade : grades) {
        			aownergrade=StringUtil.setPlaceholder(aownergrade, userGrade.getUgrade().intValue());//将相应的转换成对应得字符串
				}
        	}else{//部分等级
        		if(aownergrades!=null){
        			aownergrade=StringUtil.setPlaceholderForArr1(aownergrades, grades.size());
        		}
        	}
        	debtAttorn.setAownergrade(aownergrade);//设置会员等级
        }
		debtAttorn.setDebtattornno(StringUtil.getNameNoForName(Marknumber.DEBTATTORN_NO));//生成编号
		AdminUser adminUser=(AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER);//获取后台管理员
		if(adminUser!=null){
			debtAttorn.setAddman(adminUser.getUsername());
		}
		debtAttorn.setAddtime(new Date());
		debtAttornService.insert(debtAttorn);
		ModelAndView modelAndView = new ModelAndView();
		List<UserGrade> uGrades = userGradeService.getAll(null);
		modelAndView.addObject("uGrades",uGrades);
		//加载排除人员名单
		List<RemoveName> removeNames = removeNameService.selectNameAndNameNo();
		modelAndView.addObject("removeNames",removeNames );
//		modelAndView.setViewName("admin/debtAttorn/DebtAttornBuyer_Inst");
		modelAndView.setViewName("redirect:/admin/debtAttorn/insert_DebtAttornbuyer_Ui/"+debtAttorn.getTid()+".action");
		return modelAndView;//转发到购买人页面
	}
	// 增加债权转让购买人(表二)
	@RequestMapping(value = "/insertDebtAttornBuyer", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView insertDebtAttornBuyer(DebtAttornBuyer debtAttornBuyer,String[] apurchasergrades,HttpServletRequest request,HttpServletResponse response) throws IOException {
		String apugrade=debtAttornBuyer.getApurchasergrade();//获取会员等级
		  if(apugrade!=null&&!apugrade.equals("")){//非空
			  List<UserGrade> grades=userGradeService.getAll(null);//获取全部等级
			   if(apugrade.equals("1")){
				   apugrade=StringUtil.getPlaceholder(grades.size());
				   for(UserGrade grade:grades){
					   apugrade=StringUtil.setPlaceholder(apugrade, grade.getUgrade().intValue());//转换成对应的字符串
				   }
			   }else{
				   if(apurchasergrades!=null){
					   apugrade=StringUtil.setPlaceholderForArr1(apurchasergrades, grades.size());//将会员等级转换成对应的字符串
				   }
			   }
			  debtAttornBuyer.setApurchasergrade(apugrade);
		  }
	    debtAttornBuyer.setDebtattornbno(StringUtil.getNameNoForName(Marknumber.DEBTATTORNBUYER_NO));//生成编号
		AdminUser adminUser=(AdminUser)request.getSession().getAttribute(Session_Constant.ADMINUSER);//从session中获取管理员
		if(adminUser!=null){
			debtAttornBuyer.setAddman(adminUser.getUsername());
		} 
		debtAttornBuyer.setAddtime(new Date());
	    debtAttornBuyerService.insert(debtAttornBuyer);
	    //得到债权转让设置的编号并set进标的债权转让编号字段
		TenderItem tenderItem=tenderItemService.findTenderItemById(debtAttornBuyer.getTid());
		tenderItem.setIsadebtattorn((short)1);
		tenderItemService.update(tenderItem);
		ModelAndView mv=new ModelAndView();
		mv.setViewName("redirect:/admin/tenderItem/tenderItem_tag_UI/"+debtAttornBuyer.getTid()+".action");
		return mv;
	}
	//增加债权手续费(表三)
	@RequestMapping(value="insertDebtAttornFee",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView insertDebtAttornFee(DebtAttornFee debtAttornFee,String[] ugrades,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String debno=StringUtil.getNameNoForName(Marknumber.DEBTATTORNFEE_NO);//生成编号
		List<UserGrade> userGrades=userGradeService.getAll(null);//全部等级
		AdminUser adminUser=(AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER);//获取管理员
		 if(adminUser!=null){
			  debtAttornFee.setAddman(adminUser.getUsername());
		   }
		debtAttornFee.setAddtime(new Date());//添加时间
               if(debtAttornFee.getFeemode().toString().equals("1")){//根据用户等级
            	  String isAll=debtAttornFee.getUgrade();//全部等级或部分等级，1全部，2部分
				String gradeStr= UserGradeUtil.changeString(isAll,ugrades,userGrades);//将等级转化为字符串
           			debtAttornFee.setUgrade(gradeStr);
           		  List<DebtAttornFee> attornFees=debtAttornFee.getDebtAttornFees();//获取前台的数据
           		  for (int i = 0; i < attornFees.size(); i++) {
					  if(attornFees.get(i).getMinattornmoney()==null){//去掉无用的数据
						  attornFees.remove(i);
					  }
				}
              	   for (int i = 0; i < attornFees.size(); i++) {
              		    debtAttornFee.setMinattornmoney(attornFees.get(i).getMinattornmoney());//债转金额低值
              		    debtAttornFee.setMaxattornmoney(attornFees.get(i).getMaxattornmoney());//债转金额高值
              		    if(attornFees.get(i).getAttornrate()!=null&&!attornFees.get(i).getAttornrate().equals("")){
              		    	 debtAttornFee.setAttornrate(attornFees.get(i).getAttornrate()/100);//收取债转金额百分比
              		    }
              		    debtAttornFee.setMinfee(attornFees.get(i).getMinfee());//收取最低值
              		    debtAttornFee.setMaxfee(attornFees.get(i).getMaxfee());//收取最高值
              		    debtAttornFee.setDebtattornfno(debno);//编号
             			debtAttornFeeService.insert(debtAttornFee);
               	}
				   List<String> gradeList=debtAttornFeeService.selectGradeByTid(debtAttornFee.getTid());//该标已经设置的等级记录
				   int count=UserGradeUtil.count(gradeList);//统计所有记录已设置的等级个数

				   if(count<userGrades.size()){
					   ModelAndView mv=new ModelAndView("redirect:/admin/debtAttorn/insert_DebtAttornfee_Ui/"+debtAttornFee.getTid()+".action");
					   return  mv;
				   }//如果count小于所有等级个数，说明没有设置完，则重定向到转发页面的请求Url
               }else{//持有时间
            	  List<DebtAttornFee> attornFees=debtAttornFee.getDebtAttornFees();//持有时间
            	  for (int i = 0; i < attornFees.size(); i++) {
					  if(attornFees.get(i).getMinattornmoney()==null){//去掉无用的数据
						 attornFees.remove(i);
					  }
				   }
            	  for (int i = 0; i < attornFees.size(); i++) {
            		    debtAttornFee.setHadday(attornFees.get(i).getHadday());//持有时间
            		    debtAttornFee.setMinattornmoney(attornFees.get(i).getMinattornmoney());//债转金额低值
            		    debtAttornFee.setMaxattornmoney(attornFees.get(i).getMaxattornmoney());//债转金额高值
            		    if(attornFees.get(i).getAttornrate()!=null&&!attornFees.get(i).getAttornrate().equals("")){
            		    	debtAttornFee.setAttornrate(attornFees.get(i).getAttornrate()/100);//收取债转金额百分比
            		    }
            		    debtAttornFee.setMinfee(attornFees.get(i).getMinfee());//收取最低值
            		    debtAttornFee.setMaxfee(attornFees.get(i).getMaxfee());//收取最高值
            		    debtAttornFee.setDebtattornfno(debno);//编号
           			    debtAttornFeeService.insert(debtAttornFee);
				 }
               }
		    //得到债权转让设置的编号并set进标的债权转让编号字段
			TenderItem tenderItem=tenderItemService.findTenderItemById(debtAttornFee.getTid());
			tenderItem.setIsadebtattornfee((short)1);
			tenderItemService.update(tenderItem);
			ModelAndView mv=new ModelAndView();
			mv.setViewName("redirect:/admin/tenderItem/tenderItem_tag_UI/"+debtAttornFee.getTid()+".action");
			return mv;
	}
	
	// 根据条件查找标的债权转让设置 并转发到列表页面
	@RequestMapping(value = "/selectDebtAttornByCondition", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView selectDebtAttornByCondition(HttpServletRequest request,DebtAttorn debtAttorn) {

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

		
		
		// 调用service层的方法得到对象列表
		List<DebtAttorn> DebtAttornList = debtAttornService.selectByConditionAndDecorateUgrade(debtAttorn);
		PageInfo<DebtAttorn> pagehelper = new PageInfo<DebtAttorn>(DebtAttornList);
		pagehelper.setFirstPage(1);

		int lastPageNum = 0;
		if (pagehelper.getTotal() % size == 0) {
			lastPageNum = (int) pagehelper.getTotal() / size;
		} else {
			lastPageNum = (int) pagehelper.getTotal() / size + 1;
		}
		pagehelper.setLastPage(lastPageNum);
		// 把对象放进modelAndView中
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("pagehelper", pagehelper);
		modelAndView.addObject("debtAttorn", debtAttorn);
		// 指定视图
		modelAndView.setViewName("admin/debtAttorn/DebtAttorn_List");
		PublicUtil.removeFormSession(request);
		return modelAndView;
	}

	// 根据id查找并返回详细页面(查看详情)
	@RequestMapping(value = "/selectDebtAttornByPrimaryKey", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView selectDebtAttornByPrimaryKey(BigDecimal tid) {
		ModelAndView modelAndView = new ModelAndView();
		if(tid!=null){
	    	DebtAttorn DebtAttorn=debtAttornService.selectByTid(tid);
	    	if(DebtAttorn!=null){
	    		modelAndView.addObject("debtAttorn", DebtAttorn);
	    	}
	    }
		modelAndView.setViewName("admin/debtAttorn/DebtAttorn_Detail");
		return modelAndView;
	}

	// 转发到修改页面
	@RequestMapping(value = "/toUpdateUi", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView toUpdateUi(BigDecimal tid) {
		ModelAndView modelAndView = new ModelAndView();
		if(tid!=null){
	    	DebtAttorn DebtAttorn=debtAttornService.selectByTid(tid);
	    	if(DebtAttorn!=null){
	    		modelAndView.addObject("debtAttorn", DebtAttorn);
	    	}
	    }
		modelAndView.setViewName("admin/debtAttorn/DebtAttorn_Update");
		return modelAndView;
		
		/*DebtAttorn debtAttorn = new DebtAttorn();
		debtAttorn.setId(new BigDecimal(id));
		List<DebtAttorn> debtAttorns = debtAttornService.selectByCondition(debtAttorn);
		DebtAttorn debtAttorn1 =null;
		if(debtAttorns.size()>0){
			debtAttorn1 = debtAttorns.get(0);
		}
		String aownergrade = debtAttorn1.getAownergrade();
		ModelAndView modelAndView = new ModelAndView();
		PublicUtil.decorateGrade(modelAndView,userGradeService,aownergrade,"aownergrade","aownergrade1");
		
		String apurchasergrade = debtAttorn1.getApurchasergrade();
		PublicUtil.decorateGrade(modelAndView,userGradeService,apurchasergrade,"apurchasergrade","apurchasergrade1");
		
		String ugrade = debtAttorn1.getUgrade();
		PublicUtil.decorateGrade(modelAndView,userGradeService,ugrade,"ugrade","ugrades1");
		
		List<UserGrade> uGrades = userGradeService.getAll(null);
		modelAndView.addObject("uGrades",uGrades);
		//加载排除人员名单
		List<RemoveName> removeNames = removeNameService.selectNameAndNameNo();
		modelAndView.addObject("removeNames",removeNames );
		modelAndView.addObject("debtAttorn",debtAttorn1);
		modelAndView.setViewName("admin/debtAttorn/DebtAttorn_Update");
		return modelAndView;*/
	}

	// 修改
	@RequestMapping(value = "/updateDebtAttorn", method = { RequestMethod.POST, RequestMethod.GET })
	public String updateDebtAttorn(DebtAttorn debtAttorn,String aownergrade,String[] aownergrades,String apurchasergrade,String[] apurchasergrades,String ugrade,String[] ugrades) {
		/*//判断ugrade是否为1，如果为1，则为全部等级
		if("1".equals(aownergrade)){
			List<UserGrade> all = userGradeService.getAll(null);
			int size = all.size();
			String aownergrade1 = StringUtil.intToString(size);
			debtAttorn.setAownergrade(aownergrade1);
		}else if("2".equals(aownergrade)){
				String aownergrade2 = StringUtil.stringArrayToString(aownergrades);
				debtAttorn.setAownergrade(aownergrade2);
		}
		
		//判断ugrade是否为1，如果为1，则为全部等级
		if("1".equals(apurchasergrade)){
			List<UserGrade> all = userGradeService.getAll(null);
			int size = all.size();
			String apurchasergrade1 = StringUtil.intToString(size);
			debtAttorn.setApurchasergrade(apurchasergrade1);
		}else if("2".equals(apurchasergrade)){
				String apurchasergrade2 = StringUtil.stringArrayToString(apurchasergrades);
				debtAttorn.setApurchasergrade(apurchasergrade2);
		}

		//判断ugrade是否为1，如果为1，则为全部等级
		if("1".equals(ugrade)){
			List<UserGrade> all = userGradeService.getAll(null);
			int size = all.size();
			String ugrade1 = StringUtil.intToString(size);
			debtAttorn.setUgrade(ugrade1);
		}else if("2".equals(ugrade)){
				String ugrade2 = StringUtil.stringArrayToString(ugrades);
				debtAttorn.setUgrade(ugrade2);
		}
		System.out.println(debtAttorn);*/
		debtAttornService.update(debtAttorn);
		return "redirect:/admin/debtAttorn/selectDebtAttornByCondition.action";
	}
	//删除
	@RequestMapping(value = "/deleteDebtAttorn", method = { RequestMethod.POST, RequestMethod.GET })
	public void deletePlus(BigDecimal id,BigDecimal tid,HttpServletResponse response) throws IOException{
		PublicUtil.decideBeforeDelete(id, tid, response, tenderItemService,debtAttornService);
	}

}
