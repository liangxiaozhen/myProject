package com.ptpl.job;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ptpl.model.AdminUser;
import com.ptpl.service.AdminUserServiceI;

 
public class AdminUserJob extends QuartzJobBean{

	@Autowired
	@Qualifier("adminUserService")
	private AdminUserServiceI adminUserService;
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
  		AdminUser adminUser = new AdminUser();
		adminUser.setErrorcount(5);
		adminUser.setForbid("1");
		List<AdminUser> adminUsers = adminUserService.findAllAdminUserByForBid(adminUser);
		if(adminUsers != null){
 			long nowTimer = System.currentTimeMillis();
			long timer = (1000*60*30);
			for(AdminUser adminUserss: adminUsers){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				adminUserss.setLogintimestr(sdf.format(adminUserss.getLogintime()));
				try {
					long srTimer = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(adminUserss.getLogintimestr()).getTime();
					long newTimer = nowTimer-srTimer;
					if(newTimer >=timer){
						adminUserss.setForbid("0");
						adminUserss.setErrorcount(0);
						adminUserService.updateAdminUser(adminUserss);
					} 
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
 	}

	public AdminUserServiceI getAdminUserService() {
		return adminUserService;
	}

	public void setAdminUserService(AdminUserServiceI adminUserService) {
		this.adminUserService = adminUserService;
	}
	

}
