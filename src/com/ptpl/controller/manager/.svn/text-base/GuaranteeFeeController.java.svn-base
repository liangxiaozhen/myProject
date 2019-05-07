package com.ptpl.controller.manager;

import com.alibaba.fastjson.JSON;
import com.ptpl.controller.BaseController;
import com.ptpl.model.GuaranteeFee;
import com.ptpl.model.LoanTypeObjectQuote;
import com.ptpl.model.UserGrade;
import com.ptpl.service.GuaranteeFeeServiceI;
import com.ptpl.service.LoanTypeObjectQuoteServiceI;
import com.ptpl.service.UserGradeServiceI;
import com.ptpl.web.util.StringUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:liuqh
 * @date:2016年07月12日 22:44:14
 * @description:标的担保费率设置
 */
@Controller
@RequestMapping("/admin/guaranteeFee")
public class GuaranteeFeeController extends BaseController{
    @Autowired
    private GuaranteeFeeServiceI guaranteeFeeService;

    @Autowired
    private UserGradeServiceI userGradeService;

    @Autowired
    private LoanTypeObjectQuoteServiceI loanTypeObjectQuoteServiceI;

    /**
     * 修改保存
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/save")
    public String save(HttpServletRequest request , HttpServletResponse response){
        String params = request.getParameter("params");
        String isaudit = request.getParameter("isaudit");
        String remark = request.getParameter("remark");

        Map<String,String> hashMap = new HashMap<>();
        if(StringUtil.isEmpty(params)){
            hashMap.put("result", "fail");
            hashMap.put("Msg", "参数params 找不到");
            String str = JSON.toJSONString(hashMap);
            try {
                StringUtil.sendJsonData(response, str);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        if(StringUtil.isEmpty(remark)){
            if(remark.length() > 80){
                hashMap.put("result", "fail");
                hashMap.put("Msg", "备注信息字数超出限制！不能超出80字符！");
                String str = JSON.toJSONString(hashMap);
                try {
                    StringUtil.sendJsonData(response, str);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }

        if(StringUtil.isEmpty(isaudit)){
            hashMap.put("result", "fail");
            hashMap.put("Msg", "参数isaudit找不到");
            String str = JSON.toJSONString(hashMap);
            try {
                StringUtil.sendJsonData(response, str);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        if(!isaudit.equals("1")){//是否审核
            isaudit = "0";
        }

        JSONObject json = JSONObject.fromObject(params);
        if(!(json.size() > 0)){
            hashMap.put("result", "fail");
            hashMap.put("Msg", "因网络响应不及时！保存失败！请重新操作！");
            String str = JSON.toJSONString(hashMap);
            try {
                StringUtil.sendJsonData(response, str);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        List<GuaranteeFee> guaranteeFeees = guaranteeFeeService.selectByCondition(null);
        if(guaranteeFeees.size() > 0){//修改
            for(GuaranteeFee guaranteeFee : guaranteeFeees){
                guaranteeFeeService.delete(guaranteeFee.getId());//删除全部
            }
        }

        for(int i = 0; i < json.size() ;i++){
            String str = json.getString(String.valueOf(i));
            JSONObject json2 = JSONObject.fromObject(str);
            String gfitype   = json2.getString("gfitype");
            String ttype     = json2.getString("ttype");
            String gfpercent = json2.getString("gfpercent");
            String ugrade    = json2.getString("ugrade");
            String maxgffee  = json2.getString("maxgffee");
            String ttypeStr = StringUtil.getPlaceholder(30);
            if(ttype.equals("10000")){//全部标类型
                for(int i2 = 0 ; i2 < 30 ; i2++){
                    ttypeStr = StringUtil.setPlaceholder(ttypeStr, i2);
                }
            }else{
                String[] ttypeStrs = ttype.split(",");
                for(String string : ttypeStrs){
                    ttypeStr = StringUtil.setPlaceholder(ttypeStr, Integer.parseInt(string) - 1);
                }
            }

            String ugradeStr = StringUtil.getPlaceholder(30);
            if(ugrade.equals("10000")){//全部会员等级
                for(int i2 = 0 ; i2 < 51 ; i2++){
                    ugradeStr = StringUtil.setPlaceholder(ugradeStr, i2);
                }
            }else{
                String[] ugradeStrs = ugrade.split(",");
                for(String string : ugradeStrs){
                    ugradeStr = StringUtil.setPlaceholder(ugradeStr, Integer.parseInt(string));//因为初始会员是 0 所以不进行减1操作
                }
            }
            GuaranteeFee guaranteeFee=new GuaranteeFee();
            guaranteeFee.setGFIType(new BigDecimal(gfitype));  //计算方式（1根据用户等级，2根据标的风险等级
            guaranteeFee.setTType(ttypeStr);  //标的类型（1000000…） 30位
            guaranteeFee.setUgrade(ugradeStr);  //会员等级
            guaranteeFee.setGfpercent(Double.valueOf(gfpercent));  //利息管理费百份比
            guaranteeFee.setGfrate(Double.valueOf(gfpercent) / 100 ); //利息管理费费率
            guaranteeFee.setMaxgfamount(Double.valueOf(maxgffee));  //最高利息管理收费金额
            guaranteeFee.setIsaudit(new Short(isaudit));  //资金清算是否需要审
            guaranteeFee.setAddtime(new Date()); //添加时间
            if(StringUtil.isNotEmpty(remark)){
                guaranteeFee.setRemark(remark);  //备注
            }
            guaranteeFeeService.insert(guaranteeFee);
        }

        hashMap.put("result", "success");
        hashMap.put("Msg", "保存成功");
        String str = JSON.toJSONString(hashMap);
        try {
            StringUtil.sendJsonData(response, str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 设置/修改
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/edit")
    public ModelAndView edit(HttpServletRequest request , HttpServletResponse response){
        ModelAndView andView = new ModelAndView();
        /**查出全部的会员等级和标类型**/
        List<UserGrade> allUserGrades = userGradeService.selectiveForNormal(null);
        List<LoanTypeObjectQuote> allLoanTypeObjectQuotes = loanTypeObjectQuoteServiceI.gettypeObjectQuotes(null);

        List<GuaranteeFee> guaranteeFees = guaranteeFeeService.selectByCondition(null);
        andView.setViewName("admin/guaranteeFee/edit");
        if(guaranteeFees.size() > 0){//修改
            for(GuaranteeFee guaranteeFee : guaranteeFees){
                //会员等级
                List<UserGrade> userGrades = new ArrayList<>();
                if(guaranteeFee.getUgrade() != null){
                    List<Integer> pa1 = StringUtil.pars(guaranteeFee.getUgrade());
                    if(pa1.size() > 0){
                        for(Integer integer : pa1){
                            UserGrade userGrade = new UserGrade();
                            userGrade.setUgrade(new BigDecimal(integer));
                            List<UserGrade> userGrades2 = userGradeService.selectiveForNormal(userGrade);
                            if(userGrades2.size() > 0){
                                userGrades.add(userGrades2.get(0));
                            }
                        }
                    }
                }

                if(userGrades.size() > 0){
                    guaranteeFee.setUsergrades(userGrades);
                    if(userGrades.size() == allUserGrades.size() ){//全部会员等级
                        guaranteeFee.setIsusergrades((short)1);
                    }
                }

                //标类型
                List<LoanTypeObjectQuote> loanTypeObjectQuotes = new ArrayList<>();
                if(guaranteeFee.getTType() != null){
                    List<Integer> pa1 = StringUtil.parsStringToList(guaranteeFee.getTType());
                    if(pa1.size() > 0){
                        for(Integer integer : pa1){
                            LoanTypeObjectQuote loanTypeObjectQuote = loanTypeObjectQuoteServiceI.selectBySerialNo(new BigDecimal(integer));
                            if(loanTypeObjectQuote != null){
                                loanTypeObjectQuotes.add(loanTypeObjectQuote);
                            }
                        }
                    }
                }

                if(loanTypeObjectQuotes.size() > 0){
                    guaranteeFee.setLoantypeobjectquotes(loanTypeObjectQuotes);
                    if(loanTypeObjectQuotes.size() == allLoanTypeObjectQuotes.size()){//全部标类型
                        guaranteeFee.setIsloantypeobjectquotes((short)1);
                    }
                }
            }
            andView.addObject("allUserGrades", allUserGrades);//全部会员等级
            andView.addObject("allLoanTypeObjectQuotes", allLoanTypeObjectQuotes);//全部标类型
            andView.addObject("guaranteeFees", guaranteeFees);//全部担保费费设置
            andView.addObject("guaranteeFee", guaranteeFees.get(0));//单个利息管理费设置
            andView.setViewName("admin/guaranteeFee/update");
            return andView;
        }

        //新增页面
        andView.addObject("allUserGrades", allUserGrades);//全部会员等级
        andView.addObject("allLoanTypeObjectQuotes", allLoanTypeObjectQuotes);//全部标类型
        andView.setViewName("admin/guaranteeFee/edit");
        return andView;

    }


    /**
     * 返回标类型名称
     * @param ttype
     * @return
     */
    public Map<String,String> getTtype(String ttype){
        Map<String,String> hashMap = new HashMap<>();
        List<Integer> pa1 = StringUtil.pars(ttype);
        String ttypestr = "";
        String subttypestr = "";
        if(pa1.size() > 0){
            int i = 0;
            for(Integer integer : pa1){
                LoanTypeObjectQuote loanTypeObjectQuote = loanTypeObjectQuoteServiceI.selectBySerialNo(new BigDecimal(integer));
                if(loanTypeObjectQuote != null){
                    i++;
                    if(i <= 2){
                        subttypestr += loanTypeObjectQuote.getObjectname() + ",";
                        ttypestr += loanTypeObjectQuote.getObjectname() + ",";
                    }else{
                        ttypestr += loanTypeObjectQuote.getObjectname() + ",";
                    }
                }
            }

            if(StringUtil.isNotEmpty(ttypestr)){
                ttypestr 	= ttypestr.substring(0,ttypestr.lastIndexOf(","));
            }

            if(StringUtil.isNotEmpty(subttypestr)){
                subttypestr = subttypestr.substring(0,subttypestr.lastIndexOf(","));
                if(i > 2){
                    subttypestr += ".....";
                }
            }

        }
        hashMap.put("ttypestr", ttypestr);
        hashMap.put("subttypestr", subttypestr);
        return hashMap;
    }

    /**
     * 返回 会员等级名称
     * @param ugrade
     * @return
     */
    public Map<String,String> getUserGrade(String ugrade){
        Map<String,String> hashMap = new HashMap<>();
        List<Integer> pa1 = StringUtil.pars(ugrade);
        String ugradestr = "";
        String subugradestr = "";
        if(pa1.size() > 0){
            int i = 0;
            for(Integer integer : pa1){
                UserGrade userGrade = new UserGrade();
                userGrade.setUgrade(new BigDecimal(integer));
                List<UserGrade> userGrades = userGradeService.selective(userGrade);
                if(userGrades.size() > 0){
                    UserGrade userGrade2 = userGrades.get(0);
                    i++;
                    if(i <= 2){
                        subugradestr += userGrade2.getUgradename() + ",";
                        ugradestr += userGrade2.getUgradename() + ",";
                    }else{
                        ugradestr += userGrade2.getUgradename() + ",";
                    }
                }
            }

            if(StringUtil.isNotEmpty(subugradestr)){
                subugradestr = subugradestr.substring(0,subugradestr.lastIndexOf(","));
                if(i > 2){
                    subugradestr += ".....";
                }
            }

            if(StringUtil.isNotEmpty(ugradestr)){
                ugradestr = ugradestr.substring(0,ugradestr.lastIndexOf(","));
            }
        }
        hashMap.put("ugradestr", ugradestr);
        hashMap.put("subugradestr", subugradestr);
        return hashMap;
    }
/*    // 转发到增加页面
    @RequestMapping(value = "/insert_GuaranteeFee_Ui", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView insert_GuaranteeFee_Ui() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        List<UserGrade> allUserGrades = userGradeService.selectiveForNormal(null);//获取正常会员等级
        List<LoanTypeObjectQuote> TTypes = loanTypeObjectQuoteServiceI.selectIsuse();
        modelAndView.setViewName("admin/guaranteeFee/Insert_GuaranteeFee");
        modelAndView.addObject("allUserGrades",allUserGrades);
        modelAndView.addObject("allLoanTypeObjectQuotes",TTypes);
        return modelAndView;
    }

    //添加
    @RequestMapping(value = "/insertGuaranteeFee", method = {RequestMethod.POST, RequestMethod.GET})
    public void insertGuaranteeFee(GuaranteeFee guaranteeFee, String ugrades[],String TTypes[], HttpServletRequest request, HttpServletResponse response) throws Exception {

        String gradeStr = StringUtil.setPlaceholderForArr1(ugrades, 51);//将等级转化为字符串
        String TTypesStr = StringUtil.setPlaceholderForArr1(TTypes, 30);//将标类型转化为字符串
        guaranteeFee.setUgrade(gradeStr);//将转化后的字符串设置到ugrade字段里
        guaranteeFee.setTType(TTypesStr);
        AdminUser adminUser = (AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER);
        if (adminUser != null) {
            guaranteeFee.setAddman(adminUser.getUsername());//设置添加人
        }
        guaranteeFee.setAddtime(new Date());//设置添加时间
        if (guaranteeFee.getChargetype().toString() != null && !guaranteeFee.getChargetype().toString().equals("")) {//获取收费类型
            String chargetype = guaranteeFee.getChargetype().toString();//获取收费类型
            if (chargetype.equals("2")) {//投标时收取
                if (guaranteeFee.getGfrate() != null) {
                    guaranteeFee.setGfrate(guaranteeFee.getGfrate() / 100);
                }
            }
        }
        guaranteeFeeService.insert(guaranteeFee);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/admin/guaranteeFee/selectGuaranteeFeeByCondition.action");
        return mv;
    }


    // 根据条件查找标的担保费率设置并转发到列表页面
    @RequestMapping(value = "/selectGuaranteeFeeByCondition", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView selectGuaranteeFeeByCondition(HttpServletRequest request, GuaranteeFee guaranteeFee) {

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
        List<GuaranteeFee> GuaranteeFeeList = guaranteeFeeService.selectByConditionAndDecorateGuaranteeid(guaranteeFee);
        //封装担保服务费收款人字段和标编号字段
        if(GuaranteeFeeList!=null&&GuaranteeFeeList.size()>0){
            for(GuaranteeFee g: GuaranteeFeeList){
                BigDecimal baseId = g.getGfrecmanid();
                List<EnterpriseUsersInfo> enterpriseUsersInfos = enterpriseUsersInfoService.selectBybaseID(baseId);
                TenderItem tenderitem = tenderItemService.findTenderItemById(g.getTid());
                if(tenderitem!=null){
                    g.setTno(tenderitem.getTno());
                }
                if(enterpriseUsersInfos!=null&&enterpriseUsersInfos.size()>0){
                    EnterpriseUsersInfo enterpriseUsersInfo = enterpriseUsersInfos.get(0);
                    String usrname = enterpriseUsersInfo.getUsrname();
                    g.setUsrname(usrname);
                }

            }
        }
        PageInfo<GuaranteeFee> pagehelper = new PageInfo<GuaranteeFee>(GuaranteeFeeList);
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
        modelAndView.addObject("guaranteeFee", guaranteeFee);
        // 指定视图
        modelAndView.setViewName("admin/guaranteeFee/GuaranteeFee_List");
        PublicUtil.removeFormSession(request);
        return modelAndView;
    }

    // 根据id查找标的担保费率设置并返回详细页面(查看详情)
    @RequestMapping(value = "/selectGuaranteeFeeByPrimaryKey", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView selectGuaranteeFeeByPrimaryKey(BigDecimal id) {
        ModelAndView modelAndView = new ModelAndView();
        GuaranteeFee guaranteeFee = guaranteeFeeService.selectByPrimaryKey(id);
        modelAndView.addObject("guaranteeFee",guaranteeFee);
        modelAndView.setViewName("admin/guaranteeFee/GuaranteeFee_Detail");
        return modelAndView;
    }

    // 转发到修改标的担保费率设置页面(修改)
    @RequestMapping(value = "/toUpdateUi", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView toUpdateUi(BigDecimal id) {
        ModelAndView modelAndView = new ModelAndView();
        GuaranteeFee guaranteeFee = guaranteeFeeService.selectByPrimaryKey(id);
        List<UserGrade> uGrades = userGradeService.getAll(null);//获取全部等级
        List<LoanTypeObjectQuote> TTypes = loanTypeObjectQuoteServiceI.selectIsuse();
        modelAndView.addObject("uGrades",uGrades);
        modelAndView.addObject("TTypes",TTypes);
        modelAndView.addObject("guaranteeFee",guaranteeFee);
        modelAndView.setViewName("admin/guaranteeFee/GuaranteeFee_Update");
        return modelAndView;
    }

    // 修改
    @RequestMapping(value = "/updateGuaranteeFee", method = {RequestMethod.POST, RequestMethod.GET})
    public String updateGuaranteeFee(GuaranteeFee guaranteeFee,String ugrades[],String TTypes[],  HttpServletRequest request) {
        String gradeStr = StringUtil.setPlaceholderForArr1(ugrades, 30);//将等级转化为字符串
        String TTypesStr = StringUtil.setPlaceholderForArr1(TTypes, 30);//将标类型转化为字符串
        guaranteeFee.setUgrade(gradeStr);//将转化后的字符串设置到ugrade字段里
        guaranteeFee.setTType(TTypesStr);
        AdminUser adminUser = (AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER);
        if (adminUser != null) {
            guaranteeFee.setAddman(adminUser.getUsername());//修改人
        }
        guaranteeFee.setAddtime(new Date());//修改时间
        if (guaranteeFee.getChargetype().toString().equals("1")) {//结标收取
            List<GuaranteeFee> fees = guaranteeFee.getGuaranteefees();//获取数据
            for (int i = 0; i < fees.size(); i++) {
                guaranteeFee.setMinnmmoney(fees.get(i).getMinnmmoney());//结标分段金额低值
                guaranteeFee.setMaxnmmoney(fees.get(i).getMaxnmmoney());//结标分段金额高值
                guaranteeFee.setGfquota(fees.get(i).getGfquota());//定额
                guaranteeFee.setGfpercent(fees.get(i).getGfpercent());//百分比
                guaranteeFee.setMingffee(fees.get(i).getMingffee());//最低收费
                guaranteeFee.setMaxgffee(fees.get(i).getMaxgffee());//最高收取
                guaranteeFee.setId(fees.get(i).getId());//id
                guaranteeFeeService.update(guaranteeFee);
            }
        }
        if (guaranteeFee.getChargetype().toString().equals("2")) {//投标时收取
            guaranteeFeeService.update(guaranteeFee);
        }
        return "redirect:/admin/guaranteeFee/selectGuaranteeFeeByCondition.action";
    }

    //删除
    @RequestMapping(value = "/deleteGuaranteeFee", method = {RequestMethod.POST, RequestMethod.GET})
    public void deleteRiskGuarantyMoney(BigDecimal id,HttpServletResponse response) throws Exception {
        guaranteeFeeService.delete(id);
        sendJsonData(response,"删除成功");
    }*/


}
