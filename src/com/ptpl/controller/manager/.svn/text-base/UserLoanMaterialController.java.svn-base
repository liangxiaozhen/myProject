package com.ptpl.controller.manager;

import com.alibaba.fastjson.JSON;
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ptpl.constant.Session_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.AdminUser;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserCommonMaterial;
import com.ptpl.model.UserLoanMaterial;
import com.ptpl.service.UserCommonMaterialServiceI;
import com.ptpl.service.UserLoanMaterialServiceI;
import com.ptpl.web.util.AES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
 
/**
 * @author LIUJ
 * 用户借款资料记录
 */
@RequestMapping(value="/admin/loanmaterial")
@Controller
public class UserLoanMaterialController extends BaseController{
   
	@Autowired
	private UserLoanMaterialServiceI userLoanMaterialServicei;
	@Autowired
	private UserCommonMaterialServiceI userCommonMaterialService;//用户公共资料
	
	@RequestMapping(value = "/selectAllUserLoanmat", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView selectuserLoanMaterial(UserLoanMaterial loanMaterial){
		System.out.println("传过来的对象："+loanMaterial);
		System.out.println("借款编号："+loanMaterial.getLoanno());
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
		List<UserLoanMaterial> loanMaterials = userLoanMaterialServicei.selectAllUserLoanmat(loanMaterial);
		for (UserLoanMaterial u:loanMaterials){
			UserBaseAccountInfo userBaseAccountInfouser=u.getAccountInfo();
			userBaseAccountInfouser.setRealname(AES.getDecrypt(userBaseAccountInfouser.getRealname()));
			userBaseAccountInfouser.setLoginname(AES.getDecrypt(userBaseAccountInfouser.getLoginname()));
		}
		PageInfo<UserLoanMaterial> pagehelper = new PageInfo<UserLoanMaterial>(loanMaterials);
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
		modelAndView.addObject("loanMaterial", loanMaterial);
		// 指定视图
		modelAndView.setViewName("admin/loans/userLoanMaterial");
		return modelAndView;
	}
	
	//审核通过或不通过
	@RequestMapping(value = "/passorfail", method = { RequestMethod.POST, RequestMethod.GET })
	public void passorfail(BigDecimal id,String tstatus) throws Exception {
		if(id!=null&&tstatus!=null){
			UserLoanMaterial loanMaterial=new UserLoanMaterial();
			AdminUser adminUser=(AdminUser) session.getAttribute(Session_Constant.ADMINUSER);
			if(adminUser!=null){
			   loanMaterial.setAuditman(adminUser.getUsername());
			}
			loanMaterial.setAudittime(new Date());//审核时间
			loanMaterial.setId(id);
			String AuditStatus="";
			if(tstatus.equals("p")){//通过
				loanMaterial.setAuditstatus((short) 2);
				AuditStatus="合格";
			}
			if(tstatus.equals("f")){//拒绝
				loanMaterial.setAuditstatus((short) 3);
				AuditStatus="不合格";
			}
			HashMap<Object, Object> hashMap = new HashMap<>();
			hashMap.put("auditStatus",AuditStatus);
			String str = JSON.toJSONString(hashMap);
			userLoanMaterialServicei.updateByPrimaryKeySelective(loanMaterial);
			System.out.println(str);
			sendJsonData(response,str);
		}
	}
	
	//详情
	@RequestMapping(value="toDetail",method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView toDetail(BigDecimal id){
		ModelAndView mv=new ModelAndView();
		if(id!=null){
			UserLoanMaterial loanMaterial=userLoanMaterialServicei.selectByPrimaryKey(id);
			mv.addObject("loanMaterial", loanMaterial);
		}
		mv.setViewName("admin/loans/Detail");
		return mv;
	}
	
	//同步公共资料
	@RequestMapping(value="/together",method={RequestMethod.POST, RequestMethod.GET})
	public void together(BigDecimal baseid,String loanno) throws Exception{
		if(baseid!=null&&loanno!=null){
			    UserLoanMaterial loanMaterial=new UserLoanMaterial();//new一个用户借款资料对象
			    loanMaterial.setBaseid(baseid);//baseid
			    loanMaterial.setLoanno(loanno);//借款编号
				loanMaterial.setMaterialtype((short) 1);//设置资料类型为公共
				List<UserLoanMaterial> list=userLoanMaterialServicei.selectTogether(loanMaterial);//通过用户id和借款编号查询公共资料
				if(list.size()>0){//如果有值表示已同步
					int b=userLoanMaterialServicei.deleteBylondid(loanMaterial); //删除查出来的公共资料
					if(b>0){//删除成功
						List<UserCommonMaterial> commonMaterials=userCommonMaterialService.selectAllByBaseid(loanMaterial.getBaseid());//通过baseid查询对应的公共资料
						if(commonMaterials.size()>0){
							UserLoanMaterial loanMaterial1=new UserLoanMaterial();//new一个借款资料对象
							loanMaterial1.setMaterialtype((short)1);//公共
							loanMaterial1.setBaseid(loanMaterial.getBaseid());//用户id
							loanMaterial1.setAddtime(new Date());//提交时间
							loanMaterial1.setAuditstatus((short)1);//改为1.审核中
							loanMaterial1.setLoanno(loanMaterial.getLoanno());//借款编号
							int a=0;
							for (int i = 0; i < commonMaterials.size(); i++) {//循环同步到用户借款资料记录中
								loanMaterial1.setMaterialname(commonMaterials.get(i).getMaterialname());//资料名称
								loanMaterial1.setMaterialcontent(commonMaterials.get(i).getMaterialcontent());//资料内容
								loanMaterial1.setMaterialpic(commonMaterials.get(i).getMaterialpic());//资料图片
								loanMaterial1.setRemark(commonMaterials.get(i).getRemark());//备注
								a=userLoanMaterialServicei.insertSelective(loanMaterial1);
							}
							if(a>0){
								sendJsonData(response, JSON.toJSONString("同步成功"));	
							}
						}
					}
 				}else{//没同步，现在同步
					List<UserCommonMaterial> commonMaterials=userCommonMaterialService.selectAllByBaseid(loanMaterial.getBaseid());//通过baseid查询对应的公共资料
					if(commonMaterials.size()>0){
						UserLoanMaterial loanMaterial1=new UserLoanMaterial();//new一个借款资料对象
						loanMaterial1.setMaterialtype((short)1);//公共
						loanMaterial1.setBaseid(loanMaterial.getBaseid());//用户id
						loanMaterial1.setAddtime(new Date());//提交时间
						loanMaterial1.setAuditstatus((short)1);//改为1.审核中
						loanMaterial1.setLoanno(loanMaterial.getLoanno());//借款编号
						int a=0;
						for (int i = 0; i < commonMaterials.size(); i++) {//循环同步到用户借款资料记录中
							loanMaterial1.setMaterialname(commonMaterials.get(i).getMaterialname());//资料名称
							loanMaterial1.setMaterialcontent(commonMaterials.get(i).getMaterialcontent());//资料内容
							loanMaterial1.setMaterialpic(commonMaterials.get(i).getMaterialpic());//资料图片
							loanMaterial1.setRemark(commonMaterials.get(i).getRemark());//备注
							a=userLoanMaterialServicei.insertSelective(loanMaterial1);
						}
						if(a>0){
							sendJsonData(response, JSON.toJSONString("插入成功"));	
						}
					}
				}
		}
	}
	
	/*//修改补充资料
	@RequestMapping(value="/tochange",method={RequestMethod.POST, RequestMethod.GET})
	public void tochange(BigDecimal id){
		if(id!=null){
			UserLoanMaterial loanMaterial=new UserLoanMaterial();
			loanMaterial.setId(id);//id
			loanMaterial.setAuditstatus((short)1);//1.审核中
			
		}
	}*/
	
	//管理员查看用户借款资料
	@RequestMapping(value="/lookuserloanMaterial",method={RequestMethod.POST, RequestMethod.GET})
	public ModelAndView lookuserloanMaterial(String loanno){
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
				List<UserLoanMaterial> loanMaterials = userLoanMaterialServicei.lookuserloanMaterial(loanno);
				PageInfo<UserLoanMaterial> pagehelper = new PageInfo<UserLoanMaterial>(loanMaterials);
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
				// 指定视图
				modelAndView.setViewName("admin/loans/loanMaterial");
				return modelAndView;
	}
}
