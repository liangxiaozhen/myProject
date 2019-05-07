package com.ptpl.controller.manager;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ptpl.constant.ManualAward_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.ActiveObjectList;
import com.ptpl.model.ActivityAwardList;
import com.ptpl.model.ActivityList;
import com.ptpl.model.Award;
import com.ptpl.model.AwardPackage;
import com.ptpl.model.AwardPackageDetail;
import com.ptpl.model.ManualAward;
import com.ptpl.model.ManualAwardMain;
import com.ptpl.model.RemoveName;
import com.ptpl.model.SpecialNameList;
import com.ptpl.model.UserAccountSafeInfo;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserGrade;
import com.ptpl.service.ActiveObjectListServiceI;
import com.ptpl.service.ActivityAwardListServiceI;
import com.ptpl.service.ActivityListServiceI;
import com.ptpl.service.AwardPackageDetailServiceI;
import com.ptpl.service.AwardPackageServiceI;
import com.ptpl.service.AwardServiceI;
import com.ptpl.service.ManualAwardMainServiceI;
import com.ptpl.service.ManualAwardServiceI;
import com.ptpl.service.RemoveNameServiceI;
import com.ptpl.service.SpecialNameListServiceI;
import com.ptpl.service.UserAccountSafeInfoServiceI;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.service.UserGradeServiceI;
import com.ptpl.web.util.StringUtil;

/**
 * @author:lihs
 * @date:2016年09月02日 16:00:06
 * @description:手动颁奖设置
 */
@Controller
@RequestMapping("/admin/manual")
public class ManualAwardController extends BaseController {
	@Autowired
	private RemoveNameServiceI removeNameService;
	@Autowired
	private ActivityAwardListServiceI activityAwardListService;
	@Autowired
	private ManualAwardServiceI manualawardService;
	@Autowired
	private AwardServiceI awardService;
	@Autowired
	private AwardPackageServiceI awardPackageService;//礼品包
	@Autowired
	private UserAccountSafeInfoServiceI userAccountSafeInfoService;
	@Autowired
	private UserGradeServiceI userGradeService;
	@Autowired
	private UserBaseAccountInfoServiceI userBaseAccountInfoService;
	@Autowired
	private ActiveObjectListServiceI  activeObjectListService;
	@Autowired
	private ActivityListServiceI activityListService;
	@Autowired
	private ManualAwardMainServiceI manualAwardMainService;
	@Autowired
	private SpecialNameListServiceI specialNameListService;//定向名单
	@Autowired
	private AwardPackageDetailServiceI awardPackageDetailService;//奖品包内容表
	
	/**
	 * @author pxl
	 * @date 2016-11-24
	 * 跳转到手动颁奖活动页面
	 */
	@RequestMapping(value = "/manualAwardAdd", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView medalproject(ManualAwardMain mam,ManualAward manualAward,HttpServletRequest request) {
		
		if("".equals(mam.getActivityNo()) || mam.getActivityNo()==null){
		
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddhhmmss");
			Date currentTime = new Date();
			String dateString = formatter.format(currentTime);
			int x=(int)(Math.random()*99999);
			//主活动编号
			String activityNo="GJSDBJ"+dateString+x;
			mam.setActivityNo(activityNo);
			mam.setActMType((short)1);//手动颁奖
			//子活动编号
			String actno = mam.getActivityNo()+"-1";
			manualAward.setActno(actno);
		}
		
		//需要获取定向名单的编号
		SpecialNameList snl = new SpecialNameList();
		snl.setIsUse((short)1);
		snl.setBusinessType((short)1);//系统业务才可引用
		List<SpecialNameList> snlLists = specialNameListService.getSpecialNameListByNo(snl);
		List<SpecialNameList> snlList = new ArrayList<SpecialNameList>();
		for(SpecialNameList s:snlLists){
			String systemBusType = s.getSystemBusType();
			System.out.println("systemBusType: "+systemBusType);
			if(systemBusType.charAt(6)=='1'){
				//系统业务为手动颁奖
				snlList.add(s);
			}
		}
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("mam",mam);
		mv.addObject("snl",snl);
		mv.addObject("snlList",snlList);
		mv.addObject("manualAward",manualAward);
		mv.setViewName("admin/MANUALAWARD/manualAddPage");
		return mv;
	}
	
	/**
	 * 根据定向名单编号获取对应的定向名单
	 * @throws IOException 
	 */
	@RequestMapping("/activeObjectSelect")
	public void activeObjectSelect(HttpServletResponse response,SpecialNameList snl) throws IOException{
		
		/*
		 * 用户最终的获奖名单
		 */
		Set<String> ultimately =calculationCount(snl);	
		
		System.out.println("人数： "+ultimately.size());
				
		snl = specialNameListService.getSnlsByNoOrName(snl);
		if(snl!=null && !"".equals(snl)){
			String jsonStr = JSON.toJSONString(snl.getBusinessName()+","+ultimately.size());
			StringUtil.sendJsonData(response, jsonStr);
		}
	}
	
	/**
	 * @author pxl
	 * @date 2016-11-24
	 * 跳转到模板颁奖活动页面
	 */
	@RequestMapping("/templetAwardAdd")
	public ModelAndView templetAwardAdd(ManualAwardMain mam){
		ModelAndView mv = new ModelAndView();
		//找出作为模板的主活动
		mam.setIsTempletMain((short)1);
		List<ManualAwardMain> mamList = manualAwardMainService.getManualAwardMainList(mam);
		mv.addObject("mamList",mamList);
		mv.setViewName("admin/MANUALAWARD/TemplateProductActivity");
		return mv;
	}

		/**
		 * @author pxl
		 * @throws IOException 
		 * @date   2016-11-23
		 * 手动颁奖或者模板颁奖，继续添加子活动
		 */
		@RequestMapping(value = "/cinsertmedalproject", method = { RequestMethod.POST, RequestMethod.GET })
		public ModelAndView cinsertmedalproject(ManualAwardMain mam,ManualAward manualAward,SpecialNameList sn, 
				String maMainNo,HttpServletRequest request,HttpServletResponse response) throws Exception {
			
			/*
			 * 用户最终的获奖名单
			 */
			Set<String> ultimately =calculationCount(sn);	
			
			/*
			 *将定向名单的状态 （isQuote）改为引用
			 */
			sn.setIsQuote((short)1);
			specialNameListService.updateSpecialNameList(sn);
			
			/*
			 * 继续添加子活动
			 */
			String continueToAdd = request.getParameter("continueToAdd");
			ModelAndView mv = new ModelAndView();
			ActivityList activity = new ActivityList();
			if(continueToAdd!=null && continueToAdd.equals("1")){
				
				String activityTimeStr = mam.getActivityTimeStr();//活动执行时间字符串
				System.out.println("activityTimeStr>>>"+activityTimeStr);
				//将字符串时间转换成Date时间
				SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date1 = sdf.parse(activityTimeStr);
				mam.setActivityTime(date1);
				mam.setAddTimeMain(new Date());
				//如果数据库中已有该活动，就不需要再保存了
				ManualAwardMain manualAwardMain = manualAwardMainService.selectone(mam.getActivityNo());
				System.out.println("manualAwardMain>>>"+manualAwardMain);
				if(manualAwardMain==null){
					mam.setIsDealMain((short)0);//0.待完善  1.待执行  2.执行中（弃用）  3.已完成   4.已停用   5.已作废   6.已过期
					mam.setSubActivityNum((short)1);//主活动数量为1
					int mainRows = manualAwardMainService.insertSelective(mam);
					
					/*
					 * 将数据保存到全类活动列表
					 */
					activity.setActno(mam.getActivityNo());//活动编号
					activity.setActname(mam.getActivityName());//活动名称
					activity.setStatus((short)0);//活动的状态   与手动颁奖主活动对应
					activity.setActtype((short)5);//1.注册  2.累投  3.单标  4.首投 5.手动颁奖 6.自主颁奖
					activity.setIslistaudit(mam.getIsAudit());//获奖人审核(1需要审核，2无需审核)
					activity.setGeneratetype(mam.getActMType());//生成方式（1手动，2模板）
					activity.setAllowmanual(mam.getIsManual());//手动执行(1容许，2不容许)
					activity.setExecutetime(mam.getActivityTime());//活动执行时间
					activity.setMademan(mam.getAddManMain());//活动制作人
					activity.setMadetime(mam.getAddTimeMain());//制作时间
					
					activityListService.insert(activity);
				}else{
					//主活动已经存在  需要更新其子活动的数量
					if(manualAwardMain.getSubActivityNum()!=null){
						mam.setSubActivityNum((short)(manualAwardMain.getSubActivityNum()+1));
						manualAwardMainService.updateByPrimaryKeySelective(mam);
					}
				}
				
				/*
				 * 将数据保存到子活动表 ManualAward中
				 */
				manualAward.setMactNo(mam.getActivityNo());//获取主活动的主活动编号
				manualAward.setSettime(new Date());
				//先要获得定向名单列表  然后获得 名单的设置方式(1.根据大小名单  2.根据会员等级)
				sn = specialNameListService.getSnlsByNoOrName(sn);
				manualAward.setObjectSetting(sn.getNameMode());//活动对象设置方式      1.根据大小名单  2.根据会员等级
				manualAward.setAwardNum(ultimately.size());//每一个子活动的获奖人数
				manualAward.setSNLId(sn.getId());
				ManualAward ma = manualawardService.getmanualaward(manualAward.getActno());//旧的
				if(ma==null){
					//子活动没插入过
					int subRows=manualawardService.insertSelective(manualAward);
					updateAwardNum(ma,manualAward, ultimately.size());//根据奖品或礼品包的编号来更新奖品和礼品包的数量
				}
				
				//获取子活动编号
				String actNo=manualAward.getActno();
				//将子活动编号切成两个部分
				String[] actNoArr=actNo.split("-");
				
				int actNum = Integer.parseInt(actNoArr[1]);
				System.out.println("actNum>>>"+actNum);
				actNo=actNoArr[0]+"-"+String.valueOf(actNum+1);
				System.out.println("新子活动编号》》》"+actNo);
				
				if(mam.getActMType()==2){
					//模板新增   继续添加子活动
					//将模板主活动编号（String maMainNo）和需要新增的子活动编号末端的数字组合成一个新的子活动编号
					String newActNo=maMainNo+"-"+String.valueOf(actNum+1);
					//根据新的子活动编号查询出原来某个模板的子活动
					manualAward= manualawardService.getmanualaward(newActNo);
					System.out.println("原来子活动》》》"+manualAward);
					if(manualAward!=null){
						//需要获得原来子活动对应的定向名单编号
						SpecialNameList snl = new SpecialNameList();
						snl.setId(manualAward.getSNLId());
						snl = specialNameListService.getSpecialNameList(snl);
						mv.addObject("snl",snl);
					}else{
						manualAward = new ManualAward();
					}
				}
				
				manualAward.setActno(actNo);
				
				//处理全类活动的人次和人数
				if(ma==null){
					ma = new ManualAward();
					ma.setActno(manualAward.getActno());
				}
				ActivityList al = dealPeopleNumbers(ma,ultimately.size());
				activityListService.updateActivityList(al);
				
				//需要获取定向名单的编号
				sn.setIsUse((short)1);
				sn.setBusinessType((short)1);//系统业务才可引用
				List<SpecialNameList> snlLists = specialNameListService.getSpecialNameListByNo(sn);
				List<SpecialNameList> snlList = new ArrayList<SpecialNameList>();
				for(SpecialNameList s:snlLists){
					String systemBusType = s.getSystemBusType();
					System.out.println("systemBusType: "+systemBusType);
					if(systemBusType.charAt(6)=='1'){
						//系统业务为手动颁奖
						snlList.add(s);
					}
				}
				
				System.out.println("子活动》》》》"+manualAward);
				System.out.println("活动执行时间》》》"+mam.getActivityTimeStr());
				System.out.println("活动设置时间》》》"+mam.getAddTimeMainStr());
				mv.addObject("maMainNo",maMainNo);//模板的主活动编号
				mv.addObject("isContinue",continueToAdd);
				mv.addObject("mam",mam);
				mv.addObject("snlList",snlList);
				mv.addObject("size",snlList.size());
				mv.addObject("manualAward",manualAward);
				mv.setViewName("admin/MANUALAWARD/manualAddPage");
				
			}
			
			return mv;
		}
		
		
		/**
		 * @author pxl
		 * @throws IOException 
		 * @date   2016-11-23
		 * 手动颁奖或者模板颁奖，保存本活动
		 */
		@RequestMapping(value = "/insertmedalproject", method = { RequestMethod.POST, RequestMethod.GET })
		public ModelAndView insertmedalproject(ManualAwardMain mam,ManualAward manualAward,SpecialNameList sn, 
				String maMainNo,HttpServletRequest request,HttpServletResponse response) throws Exception {
			
			ModelAndView mv = new ModelAndView();
			/*
			 * 用户最终的获奖名单
			 */
			Set<String> ultimately =calculationCount(sn);
			
			/*
			 *将定向名单的状态 （isQuote）改为引用
			 */
			sn.setIsQuote((short)1);
			specialNameListService.updateSpecialNameList(sn);
			
			String saveActivity = request.getParameter("saveActivity");
			
			if(saveActivity!=null && saveActivity.equals("2")){
				String activityTimeStr = mam.getActivityTimeStr();//活动执行时间字符串
				System.out.println("activityTimeStr>>>"+activityTimeStr);
				//将字符串时间转换成Date时间
				SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date1 = sdf.parse(activityTimeStr);
				mam.setActivityTime(date1);
				mam.setAddTimeMain(new Date());
				//如果数据库中已有该活动，就不需要再保存了
				ManualAwardMain manualAwardMain = manualAwardMainService.selectone(mam.getActivityNo());
				System.out.println("manualAwardMain>>>"+manualAwardMain);
				if(manualAwardMain==null){
					mam.setIsDealMain((short)0);//0.待完善  1.待执行  2.执行中（弃用）  3.已完成   4.已停用   5.已作废   6.已过期
					mam.setSubActivityNum((short)1);//主活动数量为1
					int mainRows = manualAwardMainService.insertSelective(mam);
					/*
					 * 将数据保存到全类活动列表
					 */
					ActivityList activity = new ActivityList();
					activity.setActno(mam.getActivityNo());//活动编号
					activity.setActname(mam.getActivityName());//活动名称
					activity.setStatus((short)0);//活动的状态   与手动颁奖主活动对应
					activity.setActtype((short)5);//1.注册  2.累投  3.单标  4.首投 5.手动颁奖 6.自主颁奖
					activity.setIslistaudit(mam.getIsAudit());//获奖人审核(1需要审核，2无需审核)
					activity.setGeneratetype(mam.getActMType());//生成方式（1手动，2模板）
					activity.setAllowmanual(mam.getIsManual());//手动执行(1容许，2不容许)
					activity.setExecutetime(mam.getActivityTime());//活动执行时间
					activity.setMademan(mam.getAddManMain());//活动制作人
					activity.setMadetime(mam.getAddTimeMain());//制作时间
					
					activityListService.insert(activity);
				}else{
					//需要更新主活动的子活动数量
					mam.setSubActivityNum((short)(manualAwardMain.getSubActivityNum()+1));
				}
				
				/*
				 * 将数据保存到子活动表 ManualAward中
				 */
				manualAward.setMactNo(mam.getActivityNo());//获取主活动的主活动编号
				manualAward.setSettime(new Date());
				//先要获得定向名单列表  然后获得 名单的设置方式(1.根据大小名单  2.根据会员等级)
				sn = specialNameListService.getSnlsByNoOrName(sn);
				manualAward.setObjectSetting(sn.getNameMode());//活动对象设置方式      1.根据大小名单  2.根据会员等级
				manualAward.setAwardNum(ultimately.size());//每一个子活动的获奖人数
				manualAward.setSNLId(sn.getId());
				ManualAward ma = manualawardService.getmanualaward(manualAward.getActno());//旧的
				if(ma==null){
					//子活动没插入过
					int subRows=manualawardService.insertSelective(manualAward);
					updateAwardNum(ma,manualAward, ultimately.size());//根据奖品或礼品包的编号来更新奖品和礼品包的数量
				}
				
				//点击保存本活动时，主活动状态变为待执行
				mam.setIsDealMain((short)1);//0.待完善  1.待执行  2.执行中（弃用）  3.已完成   4.已停用   5.已作废   6.已过期
				manualAwardMainService.updateByPrimaryKeySelective(mam);
				
				//总活动列表的对应活动状态也需要更新
				ActivityList al = new ActivityList();
				al.setActno(mam.getActivityNo());//活动编号
				al.setStatus((short)1);
				activityListService.updateActivityList(al);
				
				//处理全类活动的人次和人数
				if(ma==null){
					ma = new ManualAward();
					ma.setActno(manualAward.getActno());
				}
				ActivityList atl = dealPeopleNumbers(ma,ultimately.size());
				activityListService.updateActivityList(atl);
				
				mv.addObject("mam",mam);
				mv.setViewName("admin/MANUALAWARD/saveSuccess");
				
			}
		
			return mv;
		}
		
		/**
		 * 添加手动颁奖活动时处理全类活动的人次和人数
		 * @param mam 手动颁奖主活动
		 * @return
		 */
		public ActivityList dealPeopleNumbers(ManualAward ma,int count){
			
			String[] actnoArr = ma.getActno().split("-");
			
			ActivityList al = new ActivityList();
			al.setActno(actnoArr[0]);
			al = activityListService.getActListByAl(al);
			
			BigDecimal awardtimes = al.getAwardtimes();//获奖人次
			if(awardtimes==null){
				al.setAwardtimes(new BigDecimal(count));//获奖人次
			}else{
				al.setAwardtimes(awardtimes.add(new BigDecimal(count)));//获奖人次
			}
			
			//根据主活动编号获得所有的子活动
			ManualAward man = new ManualAward();
			man.setMactNo(actnoArr[0]);
			List<ManualAward> maList = manualawardService.getManualAwardList(man);
			
			//在更新人数之前，先将全类活动清空
			al.setAwardnumber(new BigDecimal(0));
			activityListService.updateActivityList(al);
			
			Set<String> set = new HashSet<String>();
			for(ManualAward m :maList){
				//通过子活动中的定向名单id(SNLId)获得对应的定向名单
				SpecialNameList snl = new SpecialNameList();
				snl.setId(m.getSNLId());
				snl = specialNameListService.getSpecialNameList(snl);
				Set<String> ultimately = calculationCount(snl);//最终的获奖用户
				
				//获取全类活动中原来的获奖人次和人数
				Iterator<String> it = ultimately.iterator();
				while(it.hasNext()){
					String name=it.next();
					System.out.println(name);
					set.add(name);
				}
			}
			
			BigDecimal awardnumber = al.getAwardnumber();//获奖人数
			if(awardnumber==null){
				al.setAwardnumber(new BigDecimal(set.size()));
			}else{
				al.setAwardnumber(awardnumber.add(new BigDecimal(set.size())));
			}
			al.setActno(actnoArr[0]);
			return al;
		}
		

		/**
		 * 
		 * @param manualAward   旧的
		 * @param ma            新的
		 * @param count
		 * 更新奖品和礼品包的数量
		 */
		public void updateAwardNum(ManualAward manualAward,ManualAward ma,int count) {
			//根据奖品的编号获得奖品
			Award award = new Award();//奖品
			AwardPackage awardPackage = new AwardPackage();//礼品包
			if(manualAward!=null){
				int awardCount = manualAward.getQuantity()*manualAward.getAwardNum();
				//奖品类型 1.礼品包  2.奖品
				if(manualAward.getAwardType()==2){
					//奖品
					award.setAno(manualAward.getAwardno());//奖品的编号
					//根据奖品的编号获取某个奖品
					Award aw = awardService.getAward(award);
					aw.setQuantityrest(aw.getQuantityrest()+awardCount);//奖品的剩余数量
					awardService.update(aw);
					
				}else if(manualAward.getAwardType()==1){
					//礼品包
					awardPackage.setApno(manualAward.getAwardno());//礼品包编号
					awardPackage =awardPackageService.getAwardPackage(awardPackage);
					awardPackage.setQuantityrest(awardPackage.getQuantityrest()+awardCount);
					awardPackageService.update(awardPackage);
										
				}
			}
			if(ma !=null){
				int awardCount = ma.getQuantity()*count;
				//奖品类型 1.礼品包  2.奖品
				if(ma.getAwardType()==2){
					//奖品
					award.setAno(ma.getAwardno());//奖品的编号
					//根据奖品的编号获取某个奖品
					Award aw = awardService.getAward(award);
					aw.setQuantityrest(aw.getQuantityrest()-awardCount);//奖品的剩余数量
					awardService.update(aw);
					
				}else if(ma.getAwardType()==1){
					//礼品包
					awardPackage.setApno(ma.getAwardno());//礼品包编号
					awardPackage =awardPackageService.getAwardPackage(awardPackage);
					awardPackage.setQuantityrest(awardPackage.getQuantityrest()-awardCount);
					awardPackageService.update(awardPackage);
										
				}
			}
		}

	/**
	 * @author pxl
	 * 活动列表（全类）查看
	 */
	@RequestMapping("/activityListQuery")
	public ModelAndView medalprojectExecute(HttpServletRequest request,ActivityList al){
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
		String sort = "id.desc";
		Order.formString(sort);
		PageHelper.startPage(num, size);
		 
		List<ActivityList> activityLists = activityListService.getActivityList(al);
		PageInfo<ActivityList> pagehelper = new PageInfo<ActivityList>(activityLists);
		pagehelper.setFirstPage(1);
		int lasePageNum = 0;
		if(pagehelper.getTotal() % size ==0){
			lasePageNum = (int)pagehelper.getTotal() / size;
		}else{
			lasePageNum = (int)pagehelper.getTotal() / size+1 ;
		}
		pagehelper.setLastPage(lasePageNum);
		ModelAndView mv= new ModelAndView();
		mv.addObject("pagehelper",pagehelper);
		mv.addObject("pageNum",pageNum);
		mv.addObject("al",al);
		mv.addObject("typemaps",ManualAward_Constant.TYPE_STATUS);//活动类型 1.注册  2.累投  3.单标  4.首投 5.手动颁奖 6.自主颁奖
		mv.addObject("statusmaps",ManualAward_Constant.STATUS_MAP);//活动状态 0.待完善，1待执行，2.执行中，3.已完成，4.已停用，5.已作废，6.已过期
		mv.addObject("auditmaps",ManualAward_Constant.STATUS_AUDIT);//获奖人审核  1.需要  2.无需
		mv.addObject("rectypemaps",ManualAward_Constant.RECTYPE_MAP);//生成方式  1.手动   2.模板
		mv.addObject("executemaps",ManualAward_Constant.EXECUTE_MAP);//手动执行  1.容许  2.不容许
		mv.addObject("executestatus",ManualAward_Constant.EXECUTE_STATUS);//执行状态  1手动，2系统，3自主，4联动
		mv.setViewName("admin/MANUALAWARD/activityAwardList");
		return mv;
	}
	
	/**
	 * @author pxl
	 * 手动颁奖活动执行,将获奖用户添加至获奖名单
	 * @throws IOException 
	 */
	@RequestMapping("/executeManualActivity")
	public void executeManualActivity(HttpServletRequest request,HttpServletResponse response,ManualAwardMain mam) throws IOException{	
				
		//根据主活动编号获得所有的子活动
		ManualAward ma = new ManualAward();
		ma.setMactNo(mam.getActivityNo());
		List<ManualAward> maList =manualawardService.getManualAwardList(ma);
		Set<String> ultimately = new HashSet<String>();
		
		SpecialNameList snl = new SpecialNameList();
		
		for(ManualAward m:maList){
			ActivityAwardList activ=new ActivityAwardList();
			activ.setActid(m.getMactNo());//这里插入的是主活动编号 ，而非子活动编号
			
			//通过子活动中的定向名单id(SNLId)获得对应的定向名单
			snl.setId(m.getSNLId());
			snl = specialNameListService.getSpecialNameList(snl);
			ultimately = calculationCount(snl);//最终的获奖用户
			
			if(m.getAwardType()==2){//奖品
				Award award = new Award();
				activ.setAwardno(m.getAwardno());//奖品编号
				activ.setAwardname(m.getAwardname());//奖品名称
				//根据奖品的名称查出奖品的属性
				award = awardService.getAwardByName(m.getAwardname());
				activ.setAwardattribute(award.getAttribute());//奖品属性
				if(award.getAtype()==1){//站内虚拟
					if(award.getCashorvoucher()!=null && !"".equals(award.getCashorvoucher())){//站内虚拟
						activ.setAwardmoney(award.getCashorvoucher());//奖品金额 , 金额可能会为空
						//activ.setAwardUnit(award.getAwardUnit());//金额单位
					}else{
						activ.setAwardmoney(m.getAwardamount());//奖品金额
						//activ.setAwardUnit(m.getAwardUnit());//金额单位
					}
				}
				
				activ.setAwardquantity(m.getQuantity());//奖品数量/个人
				
				activ.setMademan(m.getAddman());//制作人
				activ.setMadetime(m.getSettime());//制作时间
					
				//是否需要审核
				if(mam.getIsAudit()==1){//需要审核
					activ.setStatus((short) 1);//发放状态（1.待审核 2待处理 3已领取 4待确认 5已经确认 6发货中 7领取失败 ）
				}else if(mam.getIsAudit()==2){//无需审核
					
					if(award.getAtype()==1){//奖品  站内
						activ.setStatus((short) 2);//发放状态（1.待审核 2待处理 3已领取 4待确认 5已经确认 6发货中 7领取失败 ）
					}else{//站外奖品
						activ.setStatus((short) 4);//发放状态（1.待审核 2待处理 3已领取 4待确认 5已经确认 6发货中 7领取失败 ）
					}
				}
				
				for(String loginname:ultimately){
					activ.setUsername(loginname);
					if(loginname!=null&&!"".equals(loginname)){					
						UserBaseAccountInfo username= userBaseAccountInfoService.getuserloginname(loginname);
						activ.setBaseid(username.getId());//获奖用户baseId
					}
					System.out.println("最终用户名》》》"+loginname);
					int rows = activityAwardListService.insert(activ);
				}
				
			}else if(m.getAwardType()==1){//礼品包
				AwardPackage awardPackage = new AwardPackage();
				//根据奖品包的名称查出奖品的属性
				awardPackage.setApname(m.getAwardname());
				awardPackage = awardPackageService.getAwardPackage(awardPackage);
				//需要将礼品包拆开成一个一个的奖品
				List<AwardPackageDetail> apdList = awardPackageDetailService.getAPDsByApId(awardPackage.getId());
				for(AwardPackageDetail a :apdList){
					System.out.println("奖品》》》"+a.getAward());
					activ.setAwardno(a.getAward().getAno());//奖品编号
					activ.setAwardname(a.getAward().getAname());//奖品名称
					activ.setAwardattribute(a.getAward().getAttribute());//奖品属性
					activ.setAwardmoney(a.getAward().getCashorvoucher());//奖品金额 , 金额可能会为空
					
					if(a.getAward().getAtype()==1){
						activ.setStatus((short) 2);//发放状态（1.待审核 2待处理 3已领取 4待确认 5已经确认 6发货中 7领取失败 ）
					}else{
						activ.setStatus((short) 4);//发放状态（1.待审核 2待处理 3已领取 4待确认 5已经确认 6发货中 7领取失败 ）
					}
					
					activ.setAwardquantity(a.getAwardQTY()*m.getQuantity());//奖品数量/个人
					
					activ.setMademan(m.getAddman());//制作人
					activ.setMadetime(m.getSettime());//制作时间
						
					//是否需要审核
					if(mam.getIsAudit()==1){//需要审核
						activ.setStatus((short) 1);//发放状态（1.待审核 2待处理 3已领取 4待确认 5已经确认 6发货中 7领取失败 ）
					}else if(mam.getIsAudit()==2){//无需审核
						
						if(a.getAward().getAtype()==1){//奖品  站内
							activ.setStatus((short) 2);//发放状态（1.待审核 2待处理 3已领取 4待确认 5已经确认 6发货中 7领取失败 ）
						}else{//站外奖品
							activ.setStatus((short) 4);//发放状态（1.待审核 2待处理 3已领取 4待确认 5已经确认 6发货中 7领取失败 ）
						}
					}
					
					for(String loginname:ultimately){
						activ.setUsername(loginname);
						if(loginname!=null&&!"".equals(loginname)){					
							UserBaseAccountInfo username= userBaseAccountInfoService.getuserloginname(loginname);
							activ.setBaseid(username.getId());//获奖用户baseId
						}
						System.out.println("最终用户名》》》"+loginname);
						int rows = activityAwardListService.insert(activ);
					}
					
				}
			}
			
		}
		
		//执行后需要更新手动颁奖主活动的状态
		mam.setIsDealMain((short)3);//活动状态   0.待完善  1.待执行  2.执行中（弃用）  3.已完成   4.已停用   5.已作废   6.已过期
		mam.setExecuteStatus((short)1);//执行状态   1.手工执行   2.系统执行
		manualAwardMainService.updateByPrimaryKeySelective(mam);
		
		//执行后需要更新全类活动的主活动的状态
		ActivityList al = new ActivityList();
		al.setActno(mam.getActivityNo());
		al.setStatus((short)3);// 活动状态（0.待完善，1待执行，2.执行中，3.已完成，4.已停用，5.已作废，6.已过期）
		al.setExecutestatus((short)1);//执行状态（1手动，2系统，3自主，4联动）
		int records =activityListService.updateActivityList(al);
		String jsonStr = null;
		if(records>0){
			jsonStr = JSON.toJSONString("执行成功");
		}else{
			jsonStr = JSON.toJSONString("执行失败");
		}
		StringUtil.sendJsonData(response, jsonStr);
	}
	
	/**
	 * @author pxl
	 * @date 2016-11-19
	 * 活动详情
	 */
	@RequestMapping("/actListDetail")
	public ModelAndView actListDetail(ActivityList al){
		System.out.println("活动编号》》》"+al.getActno());
		ModelAndView mv = new ModelAndView();
		//根据活动的编号查出活动的信息
		al=activityListService.getActListByAl(al);
		System.out.println("活动名称》》》"+al.getActname());
		mv.addObject("al",al);
		mv.addObject("typemaps",ManualAward_Constant.TYPE_STATUS);//活动类型 1.注册  2.累投  3.单标  4.首投 5.手动颁奖 6.自主颁奖
		mv.addObject("statusmaps",ManualAward_Constant.STATUS_MAP);//活动状态 0.待完善，1待执行，2.执行中，3.已完成，4.已停用，5.已作废，6.已过期
		mv.addObject("auditmaps",ManualAward_Constant.STATUS_AUDIT);//获奖人审核  1.需要  2.无需
		mv.addObject("rectypemaps",ManualAward_Constant.RECTYPE_MAP);//生成方式  1.手动   2.模板
		mv.addObject("executemaps",ManualAward_Constant.EXECUTE_MAP);//手动执行  1.容许  2.不容许
		mv.addObject("executestatus",ManualAward_Constant.EXECUTE_STATUS);//执行状态  1手动，2系统，3自主，4联动
		mv.setViewName("admin/MANUALAWARD/ActivityList_Detail");
		return mv;
	}
	
	/**
	 * @author pxl
	 * @date 2016-11-24
	 * 手动颁奖活动查看
	 */
	@RequestMapping("/manualActivityQuery")
	public ModelAndView manualActivityQuery(HttpServletRequest request,ManualAwardMain mam){
		
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
		String sort = "id.desc";
		Order.formString(sort);
		PageHelper.startPage(num, size);
		 
		List<ManualAwardMain> manualAwardMains = manualAwardMainService.getManualAwardMainList(mam);
		PageInfo<ManualAwardMain> pagehelper = new PageInfo<ManualAwardMain>(manualAwardMains);
		pagehelper.setFirstPage(1);
		int lasePageNum = 0;
		if(pagehelper.getTotal() % size ==0){
			lasePageNum = (int)pagehelper.getTotal() / size;
		}else{
			lasePageNum = (int)pagehelper.getTotal() / size+1 ;
		}
		pagehelper.setLastPage(lasePageNum);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("mam",mam);
		mv.addObject("pagehelper",pagehelper);
	
		mv.addObject("statusmaps",ManualAward_Constant.STATUS_MAP);//活动状态   0.待完善  1.待执行  2.执行中（弃用）  3.已完成   4.已停用   5.已作废   6.已过期
		mv.addObject("rectypemaps",ManualAward_Constant.RECTYPE_MAP);//生成方式   1.手动   2.模板
		mv.addObject("executemaps",ManualAward_Constant.EXECUTE_MAP);//手动执行  1.容许  2.不容许
		mv.addObject("executestatus",ManualAward_Constant.EXECUTE_STATUS);//执行状态  1.手工执行  2.系统执行
		mv.setViewName("admin/MANUALAWARD/manualAwardList");
		return mv;
	}
	
	/**
	 * @author pxl
	 * @date 2016-11-25
	 * 在手动颁奖活动页面 输入奖品或者礼品包的奖品编码后
	 * 1.将奖品或者礼品包的名称自动显示文本框的后面
	 * 2.需要验证奖品或者礼品包的数量是否为0或者是否下架
	 */
	@RequestMapping("/getAwardName")
	public void getAwardName(HttpServletResponse response,String awardno,String awardType){
		
		if(awardno!=null&&!"".equals(awardno)){
			Map<String,Object> map = new HashMap<String,Object>();
			if(awardType.equals("2")){
				//根据奖品编码获得对应的奖品或者礼品包
				Award award = new Award();//奖品
				award.setAno(awardno);
				award = awardService.getAward(award);
				System.out.println("金额==="+award.getCashorvoucher());
				if(award!=null&&!"".equals(award)){
					map.put("cashorvoucher", award.getCashorvoucher());
					map.put("result", award.getAname());
					map.put("atype", award.getAtype());//奖品的类型
					map.put("attribute", award.getAttribute());//奖品的属性
					map.put("iscancel", award.getIscancel());//1.上架   2.下架
				}else{
					map.put("result", "奖品不存在");
				}
				
			}else if(awardType.equals("1")){
				AwardPackage awardPackage = new AwardPackage();
				awardPackage.setApno(awardno);
				awardPackage =awardPackageService.getAwardPackage(awardPackage);
				if(awardPackage!=null&&!"".equals(awardPackage)){
					map.put("result", awardPackage.getApname());
					map.put("iscancel", awardPackage.getIscancel());//1.上架   2.下架
				}else{
					map.put("result", "礼品包不存在");
				}
			}
			
			String jsonStr = JSON.toJSONString(map);
			try {
				StringUtil.sendJsonData(response, jsonStr);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	/**
	 * @author pxl
	 * @throws IOException 
	 * @date 2016-11-26
	 * 插入活动时，检测数据库中活动名称是否唯一	 
	 */
	@RequestMapping("/actNameChecked")
	public void actNameChecked(HttpServletResponse response,ManualAwardMain mam) throws IOException{
		mam=manualAwardMainService.getManualAwardMain(mam);
		Map<String,String> map = new HashMap<String,String>();
		if(mam!=null){
			map.put("result", "该活动名称已存在！");
		}else{
			map.put("result","");
		}
		String jsonStr= JSON.toJSONString(map);
		StringUtil.sendJsonData(response, jsonStr);
		
	}
	
	/**
	 * @author pxl
	 * @throws IOException 
	 * @date 2016-11-26
	 * 编辑活动时，检测数据库中活动名称是否唯一	 
	 */
	@RequestMapping("/updNameChecked")
	public void updNameChecked(HttpServletResponse response,ManualAwardMain mam) throws IOException{
		
		String activityName = URLDecoder.decode(mam.getActivityName(),"UTF-8");
		System.out.println("activityName==="+activityName);
		mam.setActivityName(activityName);
		mam=manualAwardMainService.getManualAwardMainOnly(mam);
		Map<String,String> map = new HashMap<String,String>();
		if(mam!=null){
			map.put("result", "名称已存在");
		}else{
			map.put("result","");
		}
		String jsonStr= JSON.toJSONString(map);
		StringUtil.sendJsonData(response, jsonStr);
	}
	
	/**
	 * @author pxl
	 * @throws IOException 
	 * @date 2016-11-28
	 * 根据主活动编号删除本活动
	 */
	@RequestMapping("/invalidActivity")
	public void invalidActivity(HttpServletResponse response,ManualAwardMain mam) throws IOException{
		
		//1.删除本活动时需要将奖品数量返回
		ManualAward ma = new ManualAward();
		ma.setMactNo(mam.getActivityNo());
		List<ManualAward> maList = manualawardService.getManualAwardList(ma);
		int rowsAward=0;
		for(ManualAward m:maList){
			Short awardType = m.getAwardType();//奖品类型
			String awardno = m.getAwardno();//奖品编号
			Integer quantity = m.getQuantity();//一个获奖人的奖品份数
			Integer awardNum = m.getAwardNum();//子活动中的获奖人数
			Integer awardCount = quantity*awardNum;
			if(awardType==2){
				//奖品
				Award award = new Award();//奖品
				award.setAno(awardno);//奖品的编号
				//根据奖品的编号获取某个奖品
				Award aw = awardService.getAward(award);
				aw.setQuantityrest(aw.getQuantityrest()+awardCount);//奖品的剩余数量
				rowsAward = awardService.update(aw);
			}else if(awardType==1){
				AwardPackage awardPackage = new AwardPackage();//礼品包
				//礼品包
				awardPackage.setApno(awardno);//礼品包编号
				awardPackage =awardPackageService.getAwardPackage(awardPackage);
				awardPackage.setQuantityrest(awardPackage.getQuantityrest()+awardCount);
				rowsAward = awardPackageService.update(awardPackage);
			}
		}
		//2.删除手动颁奖主活动
		int rowsMain = manualAwardMainService.deleteManualAwardMain(mam);
		//3.根据手动颁奖主活动编号，删除对应的总活动列表的数据
		int rowsTotal=activityListService.deleteActivityListByActNo(mam.getActivityNo());
		//4.根据手动颁奖主活动编号，删除其所有的子活动
		System.out.println("主活动编号》》》"+mam.getActivityNo());
		int rowsSub = manualawardService.deleteManualAwardByMactNo(mam.getActivityNo());
		Map<String,String> map =new HashMap<String,String>();
		if(rowsMain>0 && rowsSub>0 && rowsTotal>0 && rowsAward>0){
			map.put("result", "删除成功");
		}else{
			map.put("result", "删除失败");
		}
		String jsonStr= JSON.toJSONString(map);
		StringUtil.sendJsonData(response, jsonStr);
	}
	
	/**
	 * @author pxl
	 * @throws UnsupportedEncodingException 
	 * @throws IOException 
	 * @date 2016-11-28
	 * 模板生成活动
	 */
	@RequestMapping("/templetCreate")
	public ModelAndView templetCreate(HttpServletRequest request,ManualAwardMain maMain) throws UnsupportedEncodingException{
		
		String activityName=URLDecoder.decode(maMain.getActivityName(), "UTF-8");
		System.out.println("活动名称》》》"+activityName);
		maMain.setActivityName(activityName);
		//根据活动的名称查询出手动颁奖主活动
		maMain = manualAwardMainService.getManualAwardMain(maMain);
		
		//查询出该主活动所有的子活动
		ManualAward manu = new ManualAward();
		manu.setMactNo(maMain.getActivityNo());
		List<ManualAward> maList = manualawardService.getManualAwardList(manu);
		
		List<Integer> list = new ArrayList<Integer>();
		for(ManualAward m:maList){
			String actno = m.getActno();//子活动的编号
			String[] actnoArr = actno.split("-");
			list.add(Integer.valueOf(actnoArr[1]));
		}
		
		Integer min =list.get(0);
		for(int i= 0;i<list.size();i++){
			if(min>list.get(i)){
				min=list.get(i);
			}
		}
		
		//第一个子活动的编号
		String actNo = maMain.getActivityNo()+"-"+min;
		//查询出第一个子活动
		ManualAward manualAward = manualawardService.getmanualaward(actNo);
		//通过子活动的奖品编号获取对应的奖品
		Award award = new Award();
		award.setAno(manualAward.getAwardno());
		award = awardService.getAward(award);

	
		//根据子活动定向名单的id获取对应的定向名单列表
		SpecialNameList snl = new SpecialNameList();
		System.out.println("SNLId>>>"+manualAward.getSNLId());
		snl.setId(manualAward.getSNLId());
		snl = specialNameListService.getSpecialNameList(snl);
		
		//需要获取定向名单的编号
		snl.setIsUse((short)1);
		snl.setBusinessType((short)1);//系统业务才可引用
		List<SpecialNameList> snlLists = specialNameListService.getSpecialNameListByNo(snl);
		List<SpecialNameList> snlList = new ArrayList<SpecialNameList>();
		for(SpecialNameList s:snlLists){
			String systemBusType = s.getSystemBusType();
			if(systemBusType.charAt(6)=='1'){
				//系统业务为手动颁奖
				snlList.add(s);
			}
		}

		//生成活动编号
		ManualAwardMain mam = new ManualAwardMain();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddhhmmss");
		Date currentTime = new Date();
		String dateString = formatter.format(currentTime);
		int x=(int)(Math.random()*99999);
		//主活动编号
		String activityNo="GJSDBJ"+dateString+x;
		mam.setActivityNo(activityNo);
		mam.setActMType((short)2);//活动生成方式  1.手动 2.模板
		//子活动编号
		String actno = mam.getActivityNo()+"-1";
		manualAward.setActno(actno);
			
		ModelAndView mv = new ModelAndView();
		mv.addObject("manualAward",manualAward);//手动颁奖第一个子活动   作用：活动对象设置 子活动编号  奖品的编号和数量
		mv.addObject("mam",mam);  //作用：主活动编号 
		mv.addObject("maMainNo",maMain.getActivityNo());//模板的主活动编号
		mv.addObject("snl",snl);
		mv.addObject("award",award);
		mv.addObject("snlList",snlList);
		mv.addObject("size",snlList.size());
		mv.setViewName("admin/MANUALAWARD/manualAddPage");
		return mv;
	}
	
	/**
	 * 
	 * @param manualAward
	 * @param snl
	 * @param response
	 * @throws IOException
	 * 判断奖品是否足够
	 */
	@RequestMapping("/isAwardEnough")
	public void isAwardEnough(ManualAward manualAward,SpecialNameList snl,HttpServletResponse response) throws IOException{
		
		Set<String> ultimately = calculationCount(snl);
		
		//子活动所有获奖用户
		int count=ultimately.size();
		System.out.println("添加-排除的获奖人数》》》"+count);
		
		//根据奖品的编号获取某个奖品
		Award award = new Award();
		award.setAno(manualAward.getAwardno());
		award = awardService.getAward(award);
		//剩余奖品的数量
		Long quantityrest = award.getQuantityrest();
		Map<String,String> map = new HashMap<String,String>();
		if(quantityrest<count){
			map.put("result", "编号: "+manualAward.getAwardno()+"的奖品数量不足");
		}else if(award.getIscancel()==0){
			map.put("result", "该奖品已下架");
		}else{
			map.put("result", "奖品足够");
		}
		String jsonStr = JSON.toJSONString(map);
		StringUtil.sendJsonData(response, jsonStr);
	}
	
	/**
	 * 计算最终的获奖用户名单
	 * @param snl
	 * @return
	 */
	public Set<String> calculationCount(SpecialNameList snl){
		
		//根据定向编号获取定向名单列表的id
		snl= specialNameListService.getSnlsByNoOrName(snl);
		//根据定向名单列表的id获取业务对象名单
		List<ActiveObjectList> aolList = activeObjectListService.selectBySNLId(snl.getId());
		
		Set<String> palmSet=new HashSet<String>();//存放所有获奖的用户名（利用Set的特性：重复数据覆盖！正好保证奖品发放不会发重复了）
		Set<String> removeSet=new HashSet<String>();//存放所有排除的用户名（利用Set的特性：重复数据覆盖！正好保证奖品发放不会发重复了）
		Set<String>  ultimately=new HashSet<String>();//最终获奖人名单（注意不能省！因Set在迭代时不能删除元素）
		
		for(ActiveObjectList aol:aolList){
			if(aol.getIsRightOrExcept()==1){
				if(aol.getNameType()==1){
					//获奖大名单
					List<RemoveName> rnList = removeNameService.getUserNameMax(aol.getNameContent());
					for(RemoveName rn : rnList){
						System.out.println("获奖大名单下的所有用户》》》"+rn.getLoginname());
						palmSet.add(rn.getLoginname());
						ultimately.add(rn.getLoginname());
					}
					System.out.println("获奖大名单用户总数为："+palmSet.size());
				}
				if(aol.getNameType()==2){
					//获奖小名单
					List<RemoveName> rnList = removeNameService.getRemoveNameByNameNo(aol.getNameContent());
					for(RemoveName rn:rnList){
						System.out.println("获奖小名单下的所有用户》》》》》"+rn.getLoginname());
						palmSet.add(rn.getLoginname());
						ultimately.add(rn.getLoginname());
					}
					System.out.println("小名单之后的用户总数："+palmSet.size());	
				}
				if(aol.getNameType()==3){
					System.out.println("获奖用户名》》》》"+aol.getNameContent());
					//获奖用户名
					palmSet.add(aol.getNameContent());
					ultimately.add(aol.getNameContent());
				}
				if(aol.getNameType()==4){
					//获取每一个会员等级下的所有用户
					//需要对会员等级占位符进行解析
					String nc = aol.getNameContent();
					for(int i=0;i<nc.length();i++){
						char c = nc.charAt(i);
						if(c=='1'){
							List<UserAccountSafeInfo> name=userAccountSafeInfoService.getuseradmin((short)i);
							for(UserAccountSafeInfo re : name){
								System.out.println("会员ID》》"+re.getBaseid());
								//获取用户名
								UserBaseAccountInfo us= userBaseAccountInfoService.getUserBaseAccountInfoById(re.getBaseid());
								//保存进Set集合当中
								if(us!=null){
									System.out.println("会员等级用户名》》》》"+us.getLoginname());
									palmSet.add(us.getLoginname());
									ultimately.add(us.getLoginname());
								}
							}
						}
					}
					
					System.out.println("会员等级的用户总数："+palmSet.size());
				}
				
				System.out.println("获奖的人数为》》》"+palmSet.size());
				
			}else if(aol.getIsRightOrExcept()==2){
				if(aol.getNameType()==1){
					//排除大名单
					List<RemoveName> rnList = removeNameService.getUserNameMax(aol.getNameContent());
					for(RemoveName rn : rnList){
						System.out.println("排除大名单用户名》》》"+rn.getLoginname());
						removeSet.add(rn.getLoginname());
					}
					System.out.println("获奖大名单用户总数为："+removeSet.size());
				}
				
				if(aol.getNameType()==2){
					//排除小名单
					List<RemoveName> rnList = removeNameService.getRemoveNameByNameNo(aol.getNameContent());
					for(RemoveName rn : rnList){
						System.out.println("排除小名单用户名》》》"+rn.getLoginname());
						removeSet.add(rn.getLoginname());
					}
					System.out.println("排除小名单后用户总数："+removeSet.size());
				}
				if(aol.getNameType()==3){
					//排除用户名
					System.out.println("排除用户名》》》"+aol.getNameContent());
					removeSet.add(aol.getNameContent());
				}
				System.out.println("排除的人数为》》》》"+removeSet.size());
			}
		}
		System.out.println("排除的总人数为》》》》"+removeSet.size());
		
		//将获奖用户名单与排除名单对比，一致时，移除出获奖名单
		for (String palm : palmSet){ 
		      for (String remove : removeSet){
		    	  if(palm!=null){
		    		  if(palm.equals(remove))
		    			  ultimately.remove(palm);				      				     
		    	  }
		      }	     
		}  
		for(String loginname:ultimately){
			System.out.println("最终的获奖用户名单》》》"+loginname);
		}
		
		return ultimately;
	}
	
	
	/**
	 * @author pxl
	 * @param maMain
	 * @return
	 * 查看手动颁奖主活动详情
	 */
	@RequestMapping("/manualActivityDetail")
	public ModelAndView manualActivityDetail(ManualAwardMain maMain){
		
		//根据主活动编号获得对应的主活动
		maMain=manualAwardMainService.selectone(maMain.getActivityNo()); 
		//根据主活动编号获得其所有的子活动
		ManualAward ma = new ManualAward();
		ma.setMactNo(maMain.getActivityNo());
		List<ManualAward> maList = manualawardService.getManualAwardList(ma);
		ModelAndView mv = new ModelAndView();
		mv.addObject("maMain",maMain);
		mv.addObject("maList",maList);
		mv.addObject("statusmaps",ManualAward_Constant.STATUS_MAP);//活动状态   0.待完善  1.待执行  2.执行中（弃用）  3.已完成   4.已停用   5.已作废   6.已过期
		mv.addObject("rectypemaps",ManualAward_Constant.RECTYPE_MAP);//生成方式   1.手动   2.模板
		mv.addObject("executemaps",ManualAward_Constant.EXECUTE_MAP);//手动执行  1.容许  2.不容许
		mv.addObject("executestatus",ManualAward_Constant.EXECUTE_STATUS);//执行状态  1.手工执行  2.系统执行
		mv.addObject("templatemaps",ManualAward_Constant.TEMPLATE_JUDGE);//是否为模板（1是，2否）
		mv.addObject("directiontype",ManualAward_Constant.DIRECTION_TYPE);//活动对象设置方式      1.根据大小名单  2.根据会员等级
		mv.addObject("awardtype",ManualAward_Constant.AWARD_TYPE);//奖品类型  1.礼品包   2.奖品
		mv.setViewName("admin/MANUALAWARD/manualActivityDetail");
		return mv;
	}
	
	/**
	 * @author pxl
	 * @param maMain
	 * 将主活动作废掉
	 * @throws IOException 
	 */
	@RequestMapping("/manualActivityInvalid")
	public void manualActivityInvalid(HttpServletResponse response,ManualAwardMain maMain) throws IOException{
		if(maMain.getIsDealMain()!=3 && maMain.getIsDealMain()!=6){
			maMain.setIsDealMain((short)5);//将主活动状态改为作废状态
			int rows=manualAwardMainService.updateByPrimaryKeySelective(maMain);
			Map<String,String> map =new HashMap<String,String>();
			if(rows>0){
				map.put("result", "操作成功");
			}else{
				map.put("result", "操作失败");
			}
			String jsonStr = JSON.toJSONString(map);
			StringUtil.sendJsonData(response, jsonStr);
		}
	}
	
	/**
	 * @author pxl
	 * @param response
	 * @param maMain
	 * 删除主活动
	 * @throws IOException 
	 */
	@RequestMapping("/manualActivityDelete")
	public void manualActivityDelete(HttpServletResponse response,ManualAwardMain maMain) throws IOException{
		if(maMain.getIsDealMain()!=3){
			invalidActivity(response,maMain);
		}
	}
	
	/**
	 * @author pxl
	 * @param mam
	 * @return
	 * 编辑手动颁奖活动
	 */
	@RequestMapping("/manualActivityEdit")
	public ModelAndView manualActivityEdit(ManualAwardMain mam){
		
		System.out.println("活动编号》》》"+mam.getActivityNo());
		System.out.println("活动状态》》》》"+mam.getIsDealMain());
		
		ModelAndView mv = new ModelAndView();
		if(mam.getIsDealMain()==0||mam.getIsDealMain()==1||mam.getIsDealMain()==5){
			//只能编辑 待完善 待执行 已作废
			//根据活动的编号查询出手动颁奖主活动
			mam = manualAwardMainService.getManualAwardMain(mam);
			//获取主活动的执行时间，并在前端显示
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = mam.getActivityTime();
			String activityTimeStr = sdf.format(date);//活动执行时间字符串（用在前台显示）
			mam.setActivityTimeStr(activityTimeStr);
			
			mv.addObject("mam",mam);  //作用：主活动
		}
		mv.setViewName("admin/MANUALAWARD/manualPublicPage");
		return mv;
	}
	
	/**
	 * 编辑手动颁奖活动的公共部分，然后保存
	 * @throws ParseException 
	 * @throws IOException 
	 */
	@RequestMapping("/editSaveManual")
	public void editSaveManual(HttpServletResponse response,ManualAwardMain mam) throws ParseException, IOException{
		//获取主活动的执行时间，并在前端显示
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String activityTimeStr = mam.getActivityTimeStr();//活动执行时间字符串（用在前台显示）
		Date date = sdf.parse(activityTimeStr);
		mam.setActivityTime(date);
		mam.setIsDealMain((short)1);
		mam.setAddTimeMain(new Date());
		int rows = manualAwardMainService.updateByPrimaryKeySelective(mam);
		String jsonStr=null;
		if(rows>0){
			jsonStr= JSON.toJSONString("保存成功");
		}else{
			jsonStr= JSON.toJSONString("保存失败");
		}
		StringUtil.sendJsonData(response, jsonStr);
	}
	
	/**
	 * 公共部分，继续编辑手动颁奖活动
	 * @throws ParseException 
	 */
	@RequestMapping("/continueToEdit")
	public ModelAndView continueToEdit(ManualAwardMain mam) throws ParseException{
		
		ManualAwardMain maMain = manualAwardMainService.selectone(mam.getActivityNo());
		int number = 1;
		
		//获取主活动的执行时间，并在前端显示
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String activityTimeStr = mam.getActivityTimeStr();//活动执行时间字符串（用在前台显示）
		Date date = sdf.parse(activityTimeStr);
		mam.setActivityTime(date);
		mam.setAddTimeMain(new Date());
		int rows = manualAwardMainService.updateByPrimaryKeySelective(mam);
		
		//根据主活动编号查询出所有的子活动（通过时间排序）
		ManualAward ma = new ManualAward();
		ma.setMactNo(mam.getActivityNo());
		List<ManualAward> maList = manualawardService.getManualAwardList(ma);
		List<Integer> list = new ArrayList<Integer>();
		for(ManualAward m:maList){
			String actno = m.getActno();//子活动的编号
			String[] actnoArr = actno.split("-");
			list.add(Integer.valueOf(actnoArr[1]));
		}
		
		Integer min =list.get(0);
		for(int i= 0;i<list.size();i++){
			if(min>list.get(i)){
				min=list.get(i);
			}
		}
		
		//第一个子活动的编号
		String actno = mam.getActivityNo()+"-"+min;
		ma = manualawardService.selectone(actno);
		//通过子活动的奖品编号获取对应的奖品
		Award award = new Award();
		award.setAno(ma.getAwardno());
		award = awardService.getAward(award);
		
		//根据子活动定向名单的id获取对应的定向名单列表
		SpecialNameList snl = new SpecialNameList();
		snl.setId(ma.getSNLId());
		snl = specialNameListService.getSpecialNameList(snl);
		
		//需要获取定向名单的编号
		snl.setIsUse((short)1);
		snl.setBusinessType((short)1);//系统业务才可引用
		List<SpecialNameList> snlLists = specialNameListService.getSpecialNameListByNo(snl);
		List<SpecialNameList> snlList = new ArrayList<SpecialNameList>();
		for(SpecialNameList s:snlLists){
			String systemBusType = s.getSystemBusType();
			System.out.println("systemBusType: "+systemBusType);
			if(systemBusType.charAt(6)=='1'){
				//系统业务为手动颁奖
				snlList.add(s);
			}
		}
		
		/*
		 * 用户最终的获奖名单
		 */
		Set<String> ultimately =calculationCount(snl);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("manualAward",ma);
		mv.addObject("award",award);
		mv.addObject("snlList",snlList);
		mv.addObject("snl",snl);
		mv.addObject("size",snlList.size());
		mv.addObject("number",number);
		mv.addObject("count",ultimately.size());
		mv.addObject("subActivityNum",maMain.getSubActivityNum());
		mv.setViewName("admin/MANUALAWARD/manualSubPage");
		return mv;
	}
	
	/**
	 * 子活动部分，继续编辑手动颁奖活动
	 * @throws ParseException 
	 */
	@RequestMapping("/continueToEditSub")
	public ModelAndView continueToEditSub(ManualAward ma,SpecialNameList snlist,int number){
		
		Set<String> ultimately = calculationCount(snlist);//获奖人数
		
		//子活动所有获奖用户
		int count=ultimately.size();
		System.out.println("添加-排除的获奖人数》》》"+count);
		
		//获取子活动编号
		String actNo=ma.getActno();
		//将子活动编号切成两个部分
		String[] actNoArr=actNo.split("-");
		//获取子活动对应的主活动
		ManualAwardMain mam = manualAwardMainService.selectone(actNoArr[0]);
		number++;
		
		System.out.println("子活动编号》》》"+ma.getActno());
		//根据子活动编号获取对应的单个子活动
		ManualAward manualAward = manualawardService.selectone(ma.getActno());//旧的
		
		/**
		 * 处理全类活动的获奖人次和人数
		 */
		ActivityList al = new ActivityList();
		al.setActno(actNoArr[0]);
		al = activityListService.getActListByAl(al);
		
		BigDecimal awardtimes = al.getAwardtimes();//获奖人次
		al.setAwardtimes(awardtimes.subtract(new BigDecimal(manualAward.getAwardNum())).add(new BigDecimal(count)));//获奖人次
		
		//编辑后更新奖品的数量
		updateAwardNum(manualAward,ma,count);//根据奖品或礼品包的编号来更新奖品和礼品包的数量
		
		//获得下一个时间的子活动
		ManualAward man = new ManualAward();
		man.setMactNo(actNoArr[0]);
		List<ManualAward> maList = manualawardService.getManualAwardList(man);
		List<Integer> list1 = new ArrayList<Integer>();
		for(ManualAward m:maList){
			String actno = m.getActno();//子活动的编号
			String[] actnoArr = actno.split("-");
			list1.add(Integer.valueOf(actnoArr[1]));
		}
		List<Integer> list2 = new ArrayList<Integer>();
		for(int i=0;i<list1.size();i++){
			if(Integer.valueOf(actNoArr[1])<list1.get(i)){
				list2.add(list1.get(i));
			}
		}
		Integer min =list2.get(0);
		for(int i= 0;i<list2.size();i++){
			if(min>list2.get(i)){
				min=list2.get(i);
			}
		}
		
		//下一个子活动的编号
		String actno = actNoArr[0]+"-"+min;
		manualAward = manualawardService.selectone(actno);
		//通过子活动的奖品编号获取对应的奖品
		Award award = new Award();
		award.setAno(manualAward.getAwardno());
		award = awardService.getAward(award);
		
		//通过定向名单的id（SNLId）获取对应的定向名单列表
		SpecialNameList snl = new SpecialNameList();
		snl.setId(manualAward.getSNLId());
		snl = specialNameListService.getSpecialNameList(snl);
		
		//需要获取定向名单的编号
		snl.setIsUse((short)1);
		snl.setBusinessType((short)1);//系统业务才可引用
		List<SpecialNameList> snlLists = specialNameListService.getSpecialNameListByNo(snl);
		List<SpecialNameList> snlList = new ArrayList<SpecialNameList>();
		for(SpecialNameList s:snlLists){
			String systemBusType = s.getSystemBusType();
			System.out.println("systemBusType: "+systemBusType);
			if(systemBusType.charAt(6)=='1'){
				//系统业务为手动颁奖
				snlList.add(s);
			}
		}
		System.out.println("businessNo>>>"+snlist.getBusinessNo());
		//根据定向编号获取对应的定向名单列表（或者id）
		snlist = specialNameListService.getSnlsByNoOrName(snlist);
		//根据子活动编号将本子活动更新
		ma.setAwardNum(count);
		ma.setSettime(new Date());
		ma.setSNLId(snlist.getId());
		manualawardService.updateByPrimaryKeySelective(ma);
		
		//在更新人数之前，先将全类活动清空
		al.setAwardnumber(new BigDecimal(0));
		activityListService.updateActivityList(al);
		//根据主活动编号获得所有的子活动
		ManualAward manu = new ManualAward();
		manu.setMactNo(actNoArr[0]);
		List<ManualAward> madList = manualawardService.getManualAwardList(manu);
		Set<String> set = new HashSet<String>();
		for(ManualAward m :madList){
			//通过子活动中的定向名单id(SNLId)获得对应的定向名单
			SpecialNameList sn = new SpecialNameList();
			sn.setId(m.getSNLId());
			sn = specialNameListService.getSpecialNameList(sn);
			Set<String> ult = calculationCount(sn);//最终的获奖用户
			
			//获取全类活动中原来的获奖人次和人数
			Iterator<String> it = ult.iterator();
			while(it.hasNext()){
				String name=it.next();
				set.add(name);
			}
		}
		al.setAwardnumber(new BigDecimal(set.size()));
		//将人数全部清除后在重新插入
		activityListService.updateActivityList(al);
		
		/*
		 * 用户最终的获奖名单
		 */
		Set<String> ultimately1 =calculationCount(snl);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("actNo",actNo);//在返回上一页时，需要用到该子活动编号
		mv.addObject("manualAward",manualAward);
		mv.addObject("award",award);
		mv.addObject("snl",snl);
		mv.addObject("snlList",snlList);
		mv.addObject("size",snlList.size());
		mv.addObject("number",number);
		mv.addObject("count",ultimately1.size());
		mv.addObject("subActivityNum",mam.getSubActivityNum());
		mv.setViewName("admin/MANUALAWARD/manualSubPage");
		return mv;
	}
	
	/**
	 * 子活动中点击新增子活动
	 */
	@RequestMapping("/newlyAddActivity")
	public ModelAndView newlyAddActivity(ManualAward manualAward,SpecialNameList snl){
		
		Set<String> ultimately = calculationCount(snl);//获奖人数
		
		//子活动所有获奖用户
		int count=ultimately.size();
		System.out.println("添加-排除的获奖人数》》》"+count);
		
		ManualAward man = manualawardService.selectone(manualAward.getActno());//旧的
		
		/**
		 * 处理全类活动的获奖人次和人数
		 */
		ActivityList al = new ActivityList();
		al.setActno(manualAward.getActno().split("-")[0]);
		al = activityListService.getActListByAl(al);
		
		BigDecimal awardtimes = al.getAwardtimes();//获奖人次
		al.setAwardtimes(awardtimes.subtract(new BigDecimal(man.getAwardNum())).add(new BigDecimal(count)));//获奖人次
		
		
		System.out.println("定向名单编号》》"+snl.getBusinessNo());
		System.out.println("定向名单标题》》》"+snl.getBusinessName());
		//根据编号获取对应的定向名单列表
		snl = specialNameListService.getSnlsByNoOrName(snl);
		manualAward.setSettime(new Date());
		manualAward.setSNLId(snl.getId());
		manualAward.setAwardNum(count);
		manualawardService.updateByPrimaryKeySelective(manualAward);
		
		//编辑后保存时更新奖品的数量
		ManualAward mad = manualawardService.selectone(manualAward.getActno());//新的
		updateAwardNum(man,mad,count);//根据奖品或礼品包的编号来更新奖品和礼品包的数量
		
		//在更新人数之前，先将全类活动清空
		al.setAwardnumber(new BigDecimal(0));
		activityListService.updateActivityList(al);
		//根据主活动编号获得所有的子活动
		ManualAward manu = new ManualAward();
		manu.setMactNo(manualAward.getActno().split("-")[0]);
		List<ManualAward> madList = manualawardService.getManualAwardList(manu);
		Set<String> set = new HashSet<String>();
		for(ManualAward m :madList){
			//通过子活动中的定向名单id(SNLId)获得对应的定向名单
			SpecialNameList sn = new SpecialNameList();
			sn.setId(m.getSNLId());
			sn = specialNameListService.getSpecialNameList(sn);
			Set<String> ult = calculationCount(sn);//最终的获奖用户
			
			//获取全类活动中原来的获奖人次和人数
			Iterator<String> it = ult.iterator();
			while(it.hasNext()){
				String name=it.next();
				set.add(name);
			}
		}
		al.setAwardnumber(new BigDecimal(set.size()));
		//将人数全部清除后在重新插入
		activityListService.updateActivityList(al);
		
		//将子活动编号切成两个部分
		String[] actNoArr=manualAward.getActno().split("-");
				
		//根据主活动编号查询出所有的子活动
		ManualAward ma= new ManualAward();
		ma.setMactNo(actNoArr[0]);
		List<ManualAward> maList = manualawardService.getManualAwardList(ma);
		List<Integer> list = new ArrayList<Integer>();
		for(ManualAward m:maList){
			String[] actnoArray=m.getActno().split("-");
			list.add(Integer.valueOf(actnoArray[1]));
		}
		Integer max =list.get(0);
		for(int i=0;i<list.size();i++){
			if(max < list.get(i)){
				max = list.get(i);
			}
		}
		String actNo=actNoArr[0]+"-"+String.valueOf(max+1);
		System.out.println("下一个子活动编号》》》"+actNo);
		manualAward.setActno(actNo);
		manualAward.setQuantity(null);//将奖品份数清掉
		
		//查询出对应的主活动
		ManualAwardMain mam = manualAwardMainService.selectone(actNoArr[0]);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String activityTimeStr = sdf.format(mam.getActivityTime());
		mam.setActivityTimeStr(activityTimeStr);
		
		//需要获取定向名单的编号
		snl.setIsUse((short)1);
		snl.setBusinessType((short)1);//系统业务才可引用
		List<SpecialNameList> snlLists = specialNameListService.getSpecialNameListByNo(snl);
		List<SpecialNameList> snlList = new ArrayList<SpecialNameList>();
		for(SpecialNameList s:snlLists){
			String systemBusType = s.getSystemBusType();
			System.out.println("systemBusType: "+systemBusType);
			if(systemBusType.charAt(6)=='1'){
				//系统业务为手动颁奖
				snlList.add(s);
			}
		}
				
		ModelAndView mv = new ModelAndView();
		mv.addObject("manualAward",manualAward);
		mv.addObject("mam",mam);
		mv.addObject("snlList",snlList);
		mv.setViewName("admin/MANUALAWARD/editAddSubPage");
		return mv;
	}
	
	/**
	 * 子活动中点击保存子活动
	 * @throws IOException 
	 */
	@RequestMapping("/saveSubActivity")
	public void saveSubActivity(HttpServletResponse response,ManualAward manualAward,SpecialNameList snl) throws IOException{
		
		Set<String> ultimately = calculationCount(snl);//获奖人数
		
		//子活动所有获奖用户
		int count=ultimately.size();
		System.out.println("添加-排除的获奖人数》》》"+count);
		
		ManualAward man = manualawardService.selectone(manualAward.getActno());//旧的
		
		/**
		 * 处理全类活动的获奖人次和人数
		 */
		ActivityList al = new ActivityList();
		al.setActno(manualAward.getActno().split("-")[0]);
		al = activityListService.getActListByAl(al);
		
		BigDecimal awardtimes = al.getAwardtimes();//获奖人次
		al.setAwardtimes(awardtimes.subtract(new BigDecimal(man.getAwardNum())).add(new BigDecimal(count)));//获奖人次
		
		snl = specialNameListService.getSnlsByNoOrName(snl);
		manualAward.setSettime(new Date());
		manualAward.setSNLId(snl.getId());
		manualAward.setAwardNum(count);
		int rows = manualawardService.updateByPrimaryKeySelective(manualAward);
		
		//编辑后保存时更新奖品的数量
		updateAwardNum(man,manualAward,count);//根据奖品或礼品包的编号来更新奖品和礼品包的数量
		
		//在更新人数之前，先将全类活动清空
		al.setAwardnumber(new BigDecimal(0));
		activityListService.updateActivityList(al);
		//根据主活动编号获得所有的子活动
		ManualAward manu = new ManualAward();
		manu.setMactNo(manualAward.getActno().split("-")[0]);
		List<ManualAward> madList = manualawardService.getManualAwardList(manu);
		Set<String> set = new HashSet<String>();
		for(ManualAward m :madList){
			//通过子活动中的定向名单id(SNLId)获得对应的定向名单
			SpecialNameList sn = new SpecialNameList();
			sn.setId(m.getSNLId());
			sn = specialNameListService.getSpecialNameList(sn);
			Set<String> ult = calculationCount(sn);//最终的获奖用户
			
			//获取全类活动中原来的获奖人次和人数
			Iterator<String> it = ult.iterator();
			while(it.hasNext()){
				String name=it.next();
				set.add(name);
			}
		}
		al.setAwardnumber(new BigDecimal(set.size()));
		//将人数全部清除后在重新插入
		activityListService.updateActivityList(al);
		
		//需要更新主活动的状态   改为待执行
		ManualAwardMain mam = new ManualAwardMain();
		mam.setActivityNo(manualAward.getActno().split("-")[0]);
		mam.setIsDealMain((short)1);
		manualAwardMainService.updateByPrimaryKeySelective(mam);

		String jsonStr = null;
		if(rows>0){
			jsonStr = JSON.toJSONString("保存成功");
		}else{
			jsonStr = JSON.toJSONString("保存失败");
		}
		StringUtil.sendJsonData(response, jsonStr);
	}
	
	/**
	 * 删除本子活动
	 * @throws IOException 
	 */
	@RequestMapping("/invalidSubActivity")
	public void invalidSubActivity(HttpServletResponse response,ManualAward ma) throws IOException{
		
		//删除本子活动后还需将奖品（或者礼品包）数量归还
		//获取该子活动
		ma = manualawardService.getmanualaward(ma.getActno());
		Short awardType = ma.getAwardType();//奖品类型
		String awardno = ma.getAwardno();//奖品编号
		Integer quantity = ma.getQuantity();//一个获奖人的奖品份数
		Integer awardNum = ma.getAwardNum();//子活动中的获奖人数
		Integer awardCount = quantity*awardNum;
		if(awardType==2){
			//奖品
			Award award = new Award();//奖品
			award.setAno(awardno);//奖品的编号
			//根据奖品的编号获取某个奖品
			Award aw = awardService.getAward(award);
			aw.setQuantityrest(aw.getQuantityrest()+awardCount);//奖品的剩余数量
			int rowsAward = awardService.update(aw);
		}else if(awardType==1){
			AwardPackage awardPackage = new AwardPackage();//礼品包
			//礼品包
			awardPackage.setApno(awardno);//礼品包编号
			awardPackage =awardPackageService.getAwardPackage(awardPackage);
			awardPackage.setQuantityrest(awardPackage.getQuantityrest()+awardCount);
			int rowsAward = awardPackageService.update(awardPackage);
		}
		
		//将子活动编号切成两个部分
		String[] actNoArr=ma.getActno().split("-");
		
		/**
		 * 处理全类活动的获奖人次和人数
		 */
		ActivityList al = new ActivityList();
		al.setActno(actNoArr[0]);
		al = activityListService.getActListByAl(al);
		
		BigDecimal awardtimes = al.getAwardtimes();//获奖人次
		/*ma = manualawardService.getmanualaward(ma.getActno());*/
		al.setAwardtimes(awardtimes.subtract(new BigDecimal(ma.getAwardNum())));//获奖人次
		
		int rows = manualawardService.deleteByActno(ma.getActno());
		
		//在更新人数之前，先将全类活动清空
		al.setAwardnumber(new BigDecimal(0));
		activityListService.updateActivityList(al);
		//根据主活动编号获得所有的子活动
		ManualAward manu = new ManualAward();
		manu.setMactNo(actNoArr[0]);
		List<ManualAward> madList = manualawardService.getManualAwardList(manu);
		Set<String> set = new HashSet<String>();
		for(ManualAward m :madList){
			//通过子活动中的定向名单id(SNLId)获得对应的定向名单
			SpecialNameList sn = new SpecialNameList();
			sn.setId(m.getSNLId());
			sn = specialNameListService.getSpecialNameList(sn);
			Set<String> ult = calculationCount(sn);//最终的获奖用户
			
			//获取全类活动中原来的获奖人次和人数
			Iterator<String> it = ult.iterator();
			while(it.hasNext()){
				String name=it.next();
				set.add(name);
			}
		}
		al.setAwardnumber(new BigDecimal(set.size()));
		//将人数全部清除后再重新插入
		activityListService.updateActivityList(al);
		
		//先要获得主活动的子活动数量
		ManualAwardMain mam = manualAwardMainService.selectone(actNoArr[0]);
		Short subActivityNum = mam.getSubActivityNum();
		subActivityNum--;
		mam.setSubActivityNum(subActivityNum);
		int lines = manualAwardMainService.updateByPrimaryKeySelective(mam);
		
		String jsonStr = null;
		if(rows>0 && lines>0){
			jsonStr = JSON.toJSONString("删除成功");
		}else{
			jsonStr = JSON.toJSONString("删除失败");
		}
		StringUtil.sendJsonData(response, jsonStr);
	}
	
	/**
	 * 子活动页面，返回上一页
	 */
	@RequestMapping("/returnPreviousPage")
	public ModelAndView returnPreviousPage(ManualAward ma,String number,String subActivityNum){
		
		ModelAndView mv = new ModelAndView();
		String[] actnoArr = ma.getActno().split("-");
		
		if("1".equals(number)){
			
			//查询出对应的主活动
			ManualAwardMain mam = manualAwardMainService.selectone(actnoArr[0]);
			//获取主活动的执行时间，并在前端显示
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = mam.getActivityTime();
			String activityTimeStr = sdf.format(date);//活动执行时间字符串（用在前台显示）
			mam.setActivityTimeStr(activityTimeStr);
			mv.addObject("mam",mam);
			mv.setViewName("admin/MANUALAWARD/manualPublicPage");
		}else{
			
			number =Integer.valueOf(number)-1+"";
			
			//获得上一个时间的子活动
			ManualAward man = new ManualAward();
			man.setMactNo(actnoArr[0]);
			//获取所有的子活动
			List<ManualAward> maList = manualawardService.getManualAwardList(man);
			List<Integer> list1 = new ArrayList<Integer>();
			for(ManualAward m:maList){
				String actno = m.getActno();//子活动的编号
				String[] actnoArray = actno.split("-");
				list1.add(Integer.valueOf(actnoArray[1]));
			}
			List<Integer> list2 = new ArrayList<Integer>();
			for(int i=0;i<list1.size();i++){
				if(list1.get(i)<Integer.valueOf(actnoArr[1])){
					list2.add(list1.get(i));
				}
			}
			Integer max =list2.get(0);
			for(int i= 0;i<list2.size();i++){
				if(max<list2.get(i)){
					max=list2.get(i);
				}
			}
			
			//上一个子活动的编号
			String actno = actnoArr[0]+"-"+max;
			//根据子活动编号获取对应的单个子活动
			ManualAward manualAward = manualawardService.selectone(actno);
			//通过子活动的奖品编号获取对应的奖品
			Award award = new Award();
			award.setAno(manualAward.getAwardno());
			award = awardService.getAward(award); 
			
			//需要获取定向名单的编号
			SpecialNameList snl = new SpecialNameList();
			snl.setId(manualAward.getSNLId());
			snl = specialNameListService.getSpecialNameList(snl);
			
			snl.setIsUse((short)1);
			snl.setBusinessType((short)1);//系统业务才可引用
			List<SpecialNameList> snlLists = specialNameListService.getSpecialNameListByNo(snl);
			List<SpecialNameList> snlList = new ArrayList<SpecialNameList>();
			for(SpecialNameList s:snlLists){
				String systemBusType = s.getSystemBusType();
				System.out.println("systemBusType: "+systemBusType);
				if(systemBusType.charAt(6)=='1'){
					//系统业务为手动颁奖
					snlList.add(s);
				}
			}
			/*
			 * 用户最终的获奖名单
			 */
			Set<String> ultimately =calculationCount(snl);
			
			mv.addObject("manualAward",manualAward);
			mv.addObject("number",number);
			mv.addObject("snl",snl);
			mv.addObject("award",award);
			mv.addObject("snlList",snlList);
			mv.addObject("size",snlList.size());
			mv.addObject("count",ultimately.size());
			mv.addObject("subActivityNum",subActivityNum);
			mv.setViewName("admin/MANUALAWARD/manualSubPage");
		}
		return mv;
	}
	
	/**
	 * 编辑手动颁奖  新增子活动后  再返回上页
	 */
	@RequestMapping("/goPreviousPage")
	public ModelAndView goPreviousPage(ManualAward ma){
		
		String actno = ma.getActno();//子活动编号
		String[] actnoArr = actno.split("-");
		//上一个子活动编号
		String previousActno = actnoArr[0]+"-"+(Integer.parseInt(actnoArr[1])-1);
		
		//获得上一个子活动的所有信息
		ManualAward manualAward = manualawardService.selectone(previousActno);
		
		//需要获取定向名单的编号
		SpecialNameList snl = new SpecialNameList();
		snl.setId(manualAward.getSNLId());
		snl = specialNameListService.getSpecialNameList(snl);
		
		snl.setIsUse((short)1);
		snl.setBusinessType((short)1);//系统业务才可引用
		List<SpecialNameList> snlLists = specialNameListService.getSpecialNameListByNo(snl);
		List<SpecialNameList> snlList = new ArrayList<SpecialNameList>();
		for(SpecialNameList s:snlLists){
			String systemBusType = s.getSystemBusType();
			System.out.println("systemBusType: "+systemBusType);
			if(systemBusType.charAt(6)=='1'){
				//系统业务为手动颁奖
				snlList.add(s);
			}
		}
		ManualAwardMain maMain = manualAwardMainService.selectone(actnoArr[0]);
		
		/*
		 * 用户最终的获奖名单
		 */
		Set<String> ultimately =calculationCount(snl);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("manualAward",manualAward);
		mv.addObject("number",maMain.getSubActivityNum());
		mv.addObject("snl",snl);
		mv.addObject("snlList",snlList);
		mv.addObject("size",snlList.size());
		mv.addObject("count",ultimately.size());
		mv.addObject("subActivityNum",maMain.getSubActivityNum());
		mv.setViewName("admin/MANUALAWARD/manualSubPage");
		return mv;
	}
	
	/**
	 * 编辑手动颁奖活动里面 点击继续添加（子活动）
	 */
	@RequestMapping("/continueToInsert")
	public ModelAndView continueToInsert(ManualAwardMain mam,ManualAward ma,SpecialNameList snl){
		
		/*
		 * 用户最终的获奖名单
		 */
		Set<String> ultimately = calculationCount(snl);	
		
		//更新主动的子活动数量
		mam = manualAwardMainService.selectone(mam.getActivityNo());
		mam.setSubActivityNum((short)(mam.getSubActivityNum()+1));
		manualAwardMainService.updateByPrimaryKeySelective(mam);
		
		/*
		 * 将数据保存到子活动表 ManualAward中
		 */
		ma.setMactNo(mam.getActivityNo());//获取主活动的主活动编号
		ma.setSettime(new Date());
		//先要获得定向名单列表  然后获得 名单的设置方式(1.根据大小名单  2.根据会员等级)
		snl = specialNameListService.getSnlsByNoOrName(snl);
		ma.setObjectSetting(snl.getNameMode());//活动对象设置方式      1.根据大小名单  2.根据会员等级
		ma.setAwardNum(ultimately.size());//每一个子活动的获奖人数
		ma.setSNLId(snl.getId());
		ManualAward man = manualawardService.getmanualaward(ma.getActno());//旧的
		
		int subRows=manualawardService.insertSelective(ma);
		updateAwardNum(man,ma, ultimately.size());//根据奖品或礼品包的编号来更新奖品和礼品包的数量
		
		snl.setIsUse((short)1);
		snl.setBusinessType((short)1);//系统业务才可引用
		List<SpecialNameList> snlLists = specialNameListService.getSpecialNameListByNo(snl);
		List<SpecialNameList> snlList = new ArrayList<SpecialNameList>();
		for(SpecialNameList s:snlLists){
			String systemBusType = s.getSystemBusType();
			System.out.println("systemBusType: "+systemBusType);
			if(systemBusType.charAt(6)=='1'){
				//系统业务为手动颁奖
				snlList.add(s);
			}
		}
		//处理全类活动的人次和人数
		ActivityList al = dealPeopleNumbers(ma,ultimately.size());
		activityListService.updateActivityList(al);
		
		//获取子活动编号
		String actNo=ma.getActno();
		//将子活动编号切成两个部分
		String[] actNoArr=actNo.split("-");
		
		int actNum = Integer.parseInt(actNoArr[1]);
		//下一个子活动编号
		actNo=actNoArr[0]+"-"+String.valueOf(actNum+1);
		ma.setActno(actNo);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String activityTimeStr = sdf.format(mam.getActivityTime());
		mam.setActivityTimeStr(activityTimeStr);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("mam",mam);
		mv.addObject("snlList",snlList);
		mv.addObject("size",snlList.size());
		mv.addObject("manualAward",ma);
		mv.setViewName("admin/MANUALAWARD/editAddSubPage");
		
		return mv;
	}
}
