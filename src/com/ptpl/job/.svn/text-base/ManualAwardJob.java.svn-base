package com.ptpl.job;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.QuartzJobBean;

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

/**
 * 手动颁奖活动系统执行   定时器
 * @author admin
 *
 */
public class ManualAwardJob extends QuartzJobBean{
	
	@Autowired
	@Qualifier("manualAwardMainService")
	private ManualAwardMainServiceI manualAwardMainService;
	@Autowired
	@Qualifier("manualawardService")
	private ManualAwardServiceI manualawardService;
	@Autowired
	@Qualifier("specialNameListService")
	private SpecialNameListServiceI specialNameListService;//定向名单
	@Autowired
	@Qualifier("awardService")
	private AwardServiceI awardService;
	@Autowired
	@Qualifier("activityAwardListService")
	private ActivityAwardListServiceI activityAwardListService;
	@Autowired
	@Qualifier("userBaseAccountInfoService")
	private UserBaseAccountInfoServiceI userBaseAccountInfoService;
	@Autowired
	@Qualifier("awardPackageService")
	private AwardPackageServiceI awardPackageService;//礼品包
	@Autowired
	@Qualifier("awardPackageDetailService")
	private AwardPackageDetailServiceI awardPackageDetailService;
	@Autowired
	@Qualifier("activityListService")
	private ActivityListServiceI activityListService;
	@Autowired
	@Qualifier("activeObjectListService")
	private ActiveObjectListServiceI  activeObjectListService;
	@Autowired
	@Qualifier("removeNameService")
	private RemoveNameServiceI removeNameService;
	@Autowired
	@Qualifier("userAccountSafeInfoService")
	private UserAccountSafeInfoServiceI userAccountSafeInfoService;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		
		/*System.out.println("定时器终于进来了..."+new Date());*/
		
		//查询出所有的手动颁奖主活动（待执行的）
		ManualAwardMain mam = new ManualAwardMain();
		mam.setIsDealMain((short)1);//活动状态   0.待完善  1.待执行  2.执行中（弃用）  3.已完成   4.已停用   5.已作废   6.已过期
		List<ManualAwardMain> mamList = manualAwardMainService.getManualAwardMainList(mam);//查询所有待执行的主活动
		if(mamList.size() ==0){
			//说明所有的手动颁奖活动执行完了
			System.out.println("没有系统可执行的任务啦！！！亲");
			return;
		}
		long nowTime = System.currentTimeMillis();
		for(ManualAwardMain maMain:mamList){
			//获取每个主活动的执行时间
			long activityTime = maMain.getActivityTime().getTime();
			if(nowTime>=activityTime){
				//系统自动执行手动颁奖主活动
				//根据主活动编号获得所有的子活动
				ManualAward ma = new ManualAward();
				ma.setMactNo(maMain.getActivityNo());
				List<ManualAward> maList =manualawardService.getManualAwardList(ma);
				Set<String> ultimately = new HashSet<String>();
				
				SpecialNameList snl = new SpecialNameList();
				
				for(ManualAward m:maList){
					ActivityAwardList activ=new ActivityAwardList();
					activ.setActid(m.getMactNo());//这里插入的是主活动编号 ，而非子活动编号
					
					//通过子活动中的定向名单id(SNLId)获得对应的定向名单
					snl.setId(m.getSNLId());
					snl = specialNameListService.getSpecialNameList(snl);
					System.out.println("snl=========="+snl);
					/*ultimately =calculationCount(snl);//最终的获奖用户
					 * 
					 * 
					 * 
					*/	
					
					//根据定向编号获取定向名单列表的id
					snl= specialNameListService.getSnlsByNoOrName(snl);
					List<ActiveObjectList> aolList = activeObjectListService.selectBySNLId(snl.getId());
					Set<String> palmSet=new HashSet<String>();//存放所有获奖的用户名（利用Set的特性：重复数据覆盖！正好保证奖品发放不会发重复了）
					Set<String> removeSet=new HashSet<String>();//存放所有排除的用户名（利用Set的特性：重复数据覆盖！正好保证奖品发放不会发重复了）
					ultimately=new HashSet<String>();//最终获奖人名单（注意不能省！因Set在迭代时不能删除元素）
					
					for(ActiveObjectList aol:aolList){
						System.out.println("aol>>>>"+aol);
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
						      if(palm.equals(remove))
						    	  ultimately.remove(palm);				      				     
					      }	     
					}  
					for(String loginname:ultimately){
						System.out.println("最终的获奖用户名单》》》"+loginname);
					}
					
					
					/**
					 * 
					 */
				
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
						if(maMain.getIsAudit()==1){//需要审核
							activ.setStatus((short) 1);//发放状态（1.待审核 2待处理 3已领取 4待确认 5已经确认 6发货中 7领取失败 ）
						}else if(maMain.getIsAudit()==2){//无需审核
							
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
							if(maMain.getIsAudit()==1){//需要审核
								activ.setStatus((short) 1);//发放状态（1.待审核 2待处理 3已领取 4待确认 5已经确认 6发货中 7领取失败 ）
							}else if(maMain.getIsAudit()==2){//无需审核
								
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
				
			}
			
			//执行后需要更新手动颁奖主活动的状态
			maMain.setIsDealMain((short)3);//活动状态   0.待完善  1.待执行  2.执行中（弃用）  3.已完成   4.已停用   5.已作废   6.已过期
			maMain.setExecuteStatus((short)2);//执行状态   1.手工执行   2.系统执行
			manualAwardMainService.updateByPrimaryKeySelective(maMain);
			
			//执行后需要更新全类活动的主活动的状态
			ActivityList al = new ActivityList();
			al.setActno(maMain.getActivityNo());
			al.setStatus((short)3);// 活动状态（0.待完善，1待执行，2.执行中，3.已完成，4.已停用，5.已作废，6.已过期）
			al.setExecutestatus((short)2);//执行状态（1手动，2系统，3自主，4联动）
			int records =activityListService.updateActivityList(al);
			
			System.out.println("任务执行完了");
		}
	}

	public ManualAwardMainServiceI getManualAwardMainService() {
		return manualAwardMainService;
	}

	public void setManualAwardMainService(ManualAwardMainServiceI manualAwardMainService) {
		this.manualAwardMainService = manualAwardMainService;
	}

	public ManualAwardServiceI getManualawardService() {
		return manualawardService;
	}

	public void setManualawardService(ManualAwardServiceI manualawardService) {
		this.manualawardService = manualawardService;
	}

	public SpecialNameListServiceI getSpecialNameListService() {
		return specialNameListService;
	}

	public void setSpecialNameListService(SpecialNameListServiceI specialNameListService) {
		this.specialNameListService = specialNameListService;
	}

	public AwardServiceI getAwardService() {
		return awardService;
	}

	public void setAwardService(AwardServiceI awardService) {
		this.awardService = awardService;
	}

	public ActivityAwardListServiceI getActivityAwardListService() {
		return activityAwardListService;
	}

	public void setActivityAwardListService(ActivityAwardListServiceI activityAwardListService) {
		this.activityAwardListService = activityAwardListService;
	}

	public UserBaseAccountInfoServiceI getUserBaseAccountInfoService() {
		return userBaseAccountInfoService;
	}

	public void setUserBaseAccountInfoService(UserBaseAccountInfoServiceI userBaseAccountInfoService) {
		this.userBaseAccountInfoService = userBaseAccountInfoService;
	}

	public AwardPackageServiceI getAwardPackageService() {
		return awardPackageService;
	}

	public void setAwardPackageService(AwardPackageServiceI awardPackageService) {
		this.awardPackageService = awardPackageService;
	}

	public AwardPackageDetailServiceI getAwardPackageDetailService() {
		return awardPackageDetailService;
	}

	public void setAwardPackageDetailService(AwardPackageDetailServiceI awardPackageDetailService) {
		this.awardPackageDetailService = awardPackageDetailService;
	}

	public ActivityListServiceI getActivityListService() {
		return activityListService;
	}

	public void setActivityListService(ActivityListServiceI activityListService) {
		this.activityListService = activityListService;
	}

	public ActiveObjectListServiceI getActiveObjectListService() {
		return activeObjectListService;
	}

	public void setActiveObjectListService(ActiveObjectListServiceI activeObjectListService) {
		this.activeObjectListService = activeObjectListService;
	}

	public RemoveNameServiceI getRemoveNameService() {
		return removeNameService;
	}

	public void setRemoveNameService(RemoveNameServiceI removeNameService) {
		this.removeNameService = removeNameService;
	}

	public UserAccountSafeInfoServiceI getUserAccountSafeInfoService() {
		return userAccountSafeInfoService;
	}

	public void setUserAccountSafeInfoService(UserAccountSafeInfoServiceI userAccountSafeInfoService) {
		this.userAccountSafeInfoService = userAccountSafeInfoService;
	}
	
}
