package com.ptpl.controller.manager;

import com.alibaba.fastjson.JSON;
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ptpl.controller.BaseController;
import com.ptpl.mapper.RechargeRateMapper;
import com.ptpl.model.*;
import com.ptpl.service.*;
import com.ptpl.web.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author shenggege
 * @version V1.0
 * @ClassName: UserRiskController
 * @Package com.ptpl.controller
 * @Description: TODO(用户风控 控制层)
 * @date
 */
@Controller
@RequestMapping("/admin/userRisk")
public class UserRiskController extends BaseController {

    /**
     * 用户风控 service
     */
    @Autowired
    private UserRiskServiceI userRiskService;

    /**
     * 屏蔽安全验证设置service
     */
    @Autowired
    private CancelValidateServiceI cancelValidateServiceI;

    /**
     * 用户 service
     */
    @Autowired
    private UserBaseAccountInfoServiceI userBaseAccountInfoServiceI;

    /**
     * 用户账户信息安全 service
     */
    @Autowired
    private UserAccountSafeInfoServiceI userAccountSafeInfoServiceI;

    /**
     * 定向名单列表service
     */
    @Autowired
    private SpecialNameListServiceI specialNameListService;

    /**
     * 业务对象名单设置service
     */
    @Autowired
    private ActiveObjectListServiceI activeObjectListService;

    /**
     *
     */
    @Autowired
    private RemoveNameServiceI removeNameService;
    @Autowired
    UserGradeServiceI userGradeService;

    /**
     * @param request
     * @param userRisk
     * @return
     * @Description :用户风险控制管理
     */

    @RequestMapping("/listUI")
    public ModelAndView list(HttpServletRequest request, UserRisk userRisk) {
        //处理分页请求
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");
        int num = 1;
        int size = 20;
        if (pageNum != null && !"".equals(pageNum)) {
            num = Integer.parseInt(pageNum);
        }
        if (pageSize != null && !"".equals(pageSize)) {
            size = Integer.parseInt(pageSize);
        }
        //排序
        String sortString = "id.desc";
        Order.formString(sortString);

        PageHelper.startPage(num, size);

        //调用service层的方法得到对象列表
        List<UserRisk> userRisks = userRiskService.selectUserRiskByCondition(userRisk);
        PageInfo<UserRisk> pagehelper = new PageInfo<UserRisk>(userRisks);
        pagehelper.setFirstPage(1);

        int lastPageNum = 0;
        if (pagehelper.getTotal() % size == 0) {
            lastPageNum = (int) pagehelper.getTotal() / size;
        } else {
            lastPageNum = (int) pagehelper.getTotal() / size + 1;
        }
        pagehelper.setLastPage(lastPageNum);

        //返回页面信息
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pagehelper", pagehelper);
        modelAndView.setViewName("admin/userRisk/userRisk_list");
        return modelAndView;
    }


    /**
     * @param @return 参数说明
     * @return void   无返回类型
     * @throws IOException
     * @throws
     * @Title: isAddUser
     * @Description: TODO(检测添加用户是否存在)
     * @author shenggege
     */
    @RequestMapping("/isAddUser")
    public void isAddUser(HttpServletResponse response, String username, UserRisk userRisk)
            throws Exception {
        //建立一个集合并调根据条件选择方法 判断用户是否为重复添加
        //select * from USERRISK where ......
        List<UserRisk> list = userRiskService.selectUserRiskByCondition(userRisk);
        //如果数据库有数据则返回List的size（）大于0，反之
        if (list.size() > 0) {
            String json = JSON.toJSONString("用户已添加!");
            sendJsonData(response, json);
        } else {
            if (username != null && !"".equals(username)) {
                //用户基本信息表
                UserBaseAccountInfo ubai = new UserBaseAccountInfo();
                //把登录名设为用户名
                ubai.setLoginname(username);
                //单个条件单条数据返回
                //select xxx from USERBASEACCOUNTINFO where...loginname==
                ubai = userBaseAccountInfoServiceI.getUserBaseAccountInfoByOneCondition(ubai);
                if (ubai != null) {
                    String jsonStr = JSON.toJSONString("添加成功!");
                    sendJsonData(response, jsonStr);
                } else {
                    String jsonStr = JSON.toJSONString(username + "用户不存在!");
                    sendJsonData(response, jsonStr);
                }
            }
        }
    }


    /**
     * @param @return 参数说明
     * @return String 返回类型
     * @throws Exception
     * @throws IOException
     * @throws
     * @Title: save
     * @Description: TODO(添加到用户风控信息)
     * @author shenggege
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(HttpServletRequest request, HttpServletResponse response, String type, UserRisk userRisk, String[] username, UserBaseAccountInfo userBaseAccountInfo)
            throws Exception {
        //创建集合接收前端传来的一个或多个username
        List<UserRisk> list = new ArrayList();
        //添加到userRisk
        list.add(userRisk);
        //遍历集合
        for (UserRisk e : list) {
            //集合的长度
            for (int i = 0; i < username.length; i++) {
                //前端是以下标传过来的
                userRisk.setUsername(username[i]);
                //把用户基本信息表中的登录名设置为传过来的username 再进行添加
                userBaseAccountInfo = userBaseAccountInfoServiceI.getuserloginname(username[i]);
                userRisk.setBaseid(userBaseAccountInfo.getId());
                userRisk.setIp(userBaseAccountInfo.getRegip());
                userRisk.setCookie(userBaseAccountInfo.getRegcookie());
                userRisk.setMobile(userBaseAccountInfo.getMobilephone());
                userRisk.setEmail(userBaseAccountInfo.getEmail());
                //调管理员
                AdminUser adminUser = getAdminUser();
                //判断各条件不为空
                if (userRisk != null && userRisk.getBaseid() != null && userRisk.getType() != null) {
                    //添加人不为空
                    if (adminUser != null && adminUser.getUsername() != null) {
                        //// 添加人
                        userRisk.setAddman(adminUser.getUsername());
                    }
                    //添加时间
                    userRisk.setAddtime(new Date());
                    //根据id主键关联
                    UserAccountSafeInfo accountSafeInfo = userAccountSafeInfoServiceI.selectByBaseId(userRisk.getBaseid());
                    //判断逻辑
                    //黑名单
                    if (type.equalsIgnoreCase("1")) {
                        //UserAccountSafeInfo Status(0.停用 1.正常)
                        accountSafeInfo.setStatus((short) 0);
                        //UserAccountSafeInfo Risklevel(1.正常 2.有风险 3.高风险 4.严重 5.锁定 6.冻结)
                        accountSafeInfo.setRisklevel((short) 6);
                        //设置完成后更新UserAccountSafeInfo
                        userAccountSafeInfoServiceI.update(accountSafeInfo);
                        //把数据插入UserRisk
                        userRiskService.insertSelective(userRisk);
                        String json = JSON.toJSONString("添加成功!!!");
                        sendJsonData(response, json);
                        //风险名单
                    } else if (type.equalsIgnoreCase("2")) {
                        //UserAccountSafeInfo Status(0.停用 1.正常)
                        accountSafeInfo.setStatus((short) 1);
                        //UserAccountSafeInfo Risklevel(1.正常 2.有风险 3.高风险 4.严重 5.锁定 6.冻结)
                        accountSafeInfo.setRisklevel((short) 4);
                        //设置完成后更新UserAccountSafeInfo
                        userAccountSafeInfoServiceI.update(accountSafeInfo);
                        //把数据插入UserRisk
                        userRiskService.insertSelective(userRisk);
                        String json = JSON.toJSONString("添加成功!!!");
                        sendJsonData(response, json);
                        //应急改密名单
                    } else if (type.equalsIgnoreCase("3")) {
                        //UserAccountSafeInfo Status(0.停用 1.正常)
                        accountSafeInfo.setStatus((short) 1);
                        //UserAccountSafeInfo Risklevel(1.正常 2.有风险 3.高风险 4.严重 5.锁定 6.冻结)
                        accountSafeInfo.setRisklevel((short) 2);
                        //设置完成后更新UserAccountSafeInfo
                        userAccountSafeInfoServiceI.update(accountSafeInfo);
                        //把数据插入UserRisk
                        userRiskService.insertSelective(userRisk);
                        String json = JSON.toJSONString("添加成功!!!");
                        sendJsonData(response, json);
                        //白名单
                    } else if (type.equalsIgnoreCase("4")) {
                        //UserAccountSafeInfo Status(0.停用 1.正常)
                        accountSafeInfo.setStatus((short) 1);
                        //UserAccountSafeInfo Risklevel(1.正常 2.有风险 3.高风险 4.严重 5.锁定 6.冻结)
                        accountSafeInfo.setRisklevel((short) 1);
                        //设置完成后更新UserAccountSafeInfo
                        userAccountSafeInfoServiceI.update(accountSafeInfo);
                        //把数据插入UserRisk
                        userRiskService.insertSelective(userRisk);
                        String json = JSON.toJSONString("添加成功!!!");
                        sendJsonData(response, json);
                    }
                } else {
                    String json = JSON.toJSONString("添加失败!!!");
                    sendJsonData(response, json);
                }
            }
        }

        return null;
    }






    /**
     * @param @param  request
     * @param @param  response
     * @param @return 参数说明
     * @return modelAndView   返回类型
     * @throws IOException
     * @throws
     * @Title: findDeleteUser
     * @Description: TODO(查找要移除的用户)
     * @author shenggege
     */
    @RequestMapping(value = "/deleteUI", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView findDeleteUser(HttpServletRequest request, UserRisk userRisk) {
        ModelAndView modelAndView = new ModelAndView();
        String id = request.getParameter("id");
        System.out.println(id);
        UserRisk risk = userRiskService.findUserRiskById(new BigDecimal(id));
        if (risk != null) {
            modelAndView.addObject("userRisk", risk);
        }
        modelAndView.setViewName("/admin/userRisk/userRisk_delete");
        return modelAndView;
    }


    /**
     * @param @param  request
     * @param @param  response
     * @param @return 参数说明
     * @return void   返回类型
     * @throws
     * @Title: deleteById
     * @Description: TODO(通过id删除用户)
     * @author shenggege
     */
    @RequestMapping(value = "/deleteById", method = {RequestMethod.POST, RequestMethod.GET})
    public void deleteById(HttpServletRequest request)
            throws Exception {
        //opid为主键id
        //uid为baseid
        String uid = request.getParameter("uid");
        String opid = request.getParameter("opid");

        //根据主键id查找userrisk中的数据
        UserRisk userRisk = userRiskService.findUserRiskById(new BigDecimal(opid));

        //如果UserBaseAccountInfo表中有前端页面传过来的数据且一致
        //创建这个对象来接收数据并设置
        UserBaseAccountInfo userBaseAccountInfo = new UserBaseAccountInfo();
        userBaseAccountInfo.setLoginname(userRisk.getUsername());
        userBaseAccountInfo.setRegip(userRisk.getIp());
        userBaseAccountInfo.setMobilephone(userRisk.getMobile());
        userBaseAccountInfo.setEmail(userRisk.getEmail());
        userBaseAccountInfo.setRegcookie(userRisk.getCookie());
        //创建集合来通过UserBaseAccountInfo查询数据
        List<UserBaseAccountInfo> userBaseAccountInfos = userBaseAccountInfoServiceI.getByUserBaseAccountInfo(userBaseAccountInfo);
        //用户账户安全设置表创建集合
        List<UserAccountSafeInfo> accountSafeInfos = new ArrayList<>();
        //如果里面有完全相等的数据 返回数据size（）>0
        if (userBaseAccountInfos.size() > 0) {
            //遍历
            for (int i = 0; i < userBaseAccountInfos.size(); i++) {
                //如果获得的下标i 为ID且不等于null
                if (userBaseAccountInfos.get(i).getId() != null) {
                    //用户基本表中的id与账户安全设置表中的主键进行关联
                    accountSafeInfos.add(userAccountSafeInfoServiceI.selectBaseId(userBaseAccountInfos.get(i).getId()));
                }
            }
        }

        //通过主键关联之后判断账户安全设置表不为空且返回的size（）>0
        if (accountSafeInfos != null && accountSafeInfos.size() > 0) {
            //对账户安全信息表中的字段进行设置
            for (int i = 0; i < accountSafeInfos.size(); i++) {
                //UserAccountSafeInfo Status(0.停用 1.正常)
                accountSafeInfos.get(i).setStatus((short) 1);
                //UserAccountSafeInfo Risklevel(1.正常 2.有风险 3.高风险 4.严重 5.锁定 6.冻结)
                accountSafeInfos.get(i).setRisklevel((short) 1);
                //更新表中设置完成的字段状态
                userAccountSafeInfoServiceI.update(accountSafeInfos.get(i));
                userRiskService.deleteById(new BigDecimal(opid));
                String jsonStr = JSON.toJSONString("操作:删除成功!");
                sendJsonData(response, jsonStr);
            }
        } else {
            userRiskService.deleteById(new BigDecimal(opid));
            String jsonStr = JSON.toJSONString("操作:删除成功!");
            sendJsonData(response, jsonStr);
        }


        //正常程序删除
        if (StringUtil.isNotEmpty(uid) && StringUtil.isNotEmpty(opid)) {
            UserAccountSafeInfo userAccountSafeInfo = userAccountSafeInfoServiceI.selectByBaseId(new BigDecimal(uid));
            if (userAccountSafeInfo != null) {
                 /*用户账户信息安全 账户状态(1正常\0停用) */
                userAccountSafeInfo.setStatus((short) 1);
                 /*用户账户信息安全 1正常，2有风险，3高风险，4严重，5 锁定（已加入黑库）,6 冻结） */
                userAccountSafeInfo.setRisklevel((short) 1);
                if (opid != null && uid != null) {
                    userAccountSafeInfoServiceI.update(userAccountSafeInfo);
                    userRiskService.deleteById(new BigDecimal(opid));
                    String jsonStr = JSON.toJSONString("操作:删除成功!");
                    sendJsonData(response, jsonStr);
                } else {
                    String jsonStr = JSON.toJSONString("操作:删除失败!");
                    sendJsonData(response, jsonStr);
                }
            }
        }
    }

    /**
     * @param @param  request
     * @param @param  userrisk
     * @param @return 参数说明
     * @return ModelAndView 返回类型
     * @throws
     * @Title: edit
     * @Description: TODO(通过id查找需编辑的用户)
     * @author shenggege
     */
    @RequestMapping(value = "/updateUI", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView edit(HttpServletRequest request, UserRisk userRisk) {
        ModelAndView modelAndView = new ModelAndView();
        String id = request.getParameter("id");
        System.out.println(id);
        UserRisk risk = userRiskService.findUserRiskById(new BigDecimal(id));
        if (risk != null) {
            modelAndView.addObject("userRisk", risk);
        }
        modelAndView.setViewName("/admin/userRisk/userRisk_update");
        return modelAndView;
    }

    /**
     * @param @param  request
     * @param @param  userrisk
     * @param @return 参数说明
     * @return ModelAndView 返回类型
     * @throws
     * @Title: updateUserRisk
     * @Description: TODO(更新风控表信息)
     * @author shenggege
     */
    @RequestMapping(value = "/updateUserRisk", method = {RequestMethod.POST, RequestMethod.GET})
    public void updateUserRisk(HttpServletRequest request, UserRisk userRisk)
            throws Exception {
        //opid为主键id
        //uid为baseid
        //获取前端隐藏input中设定的id
        String uid = request.getParameter("uid");
        String opid = request.getParameter("opid");
        //获得type
        String type = request.getParameter("type");
//		 String username = request.getParameter("username");
//		 String ip = request.getParameter("ip");
//		 String cookie = request.getParameter("cookie");
//		 String mac = request.getParameter("mac");
//		 String mobile = request.getParameter("mobile");
//		 String email = request.getParameter("email");
//		 String remark = request.getParameter("remark");
//		 System.out.println(uid+"uid>>"+opid+"opid>>>"+type+"type>>"+username+"userna>>"+ip+">>ip"+cookie+">cok>"+mac+">>mac");
        //UserBaseAccountInfo baseAccountInfo = userBaseAccountInfoServiceI.selectByBaseId(userRisk.getBaseid());

		 /*风险因子添加之后的编辑*/
        userRiskService.findUserRiskById(new BigDecimal(opid));
        //如果UserBaseAccountInfo表中有前端页面传过来的数据且一致
        //创建这个对象来接收数据并设置
        UserBaseAccountInfo userBaseAccountInfo = new UserBaseAccountInfo();
        userBaseAccountInfo.setLoginname(userRisk.getUsername());
        userBaseAccountInfo.setRegip(userRisk.getIp());
        userBaseAccountInfo.setMobilephone(userRisk.getMobile());
        userBaseAccountInfo.setEmail(userRisk.getEmail());
        userBaseAccountInfo.setRegcookie(userRisk.getCookie());
        //创建集合来通过UserBaseAccountInfo查询数据
        List<UserBaseAccountInfo> userBaseAccountInfos = userBaseAccountInfoServiceI.getByUserBaseAccountInfo(userBaseAccountInfo);
        //用户账户安全设置表创建集合
        List<UserAccountSafeInfo> accountSafeInfos = new ArrayList<>();
        //如果里面有完全相等的数据 返回数据size（）>0
        if (userBaseAccountInfos.size() > 0) {
            //遍历
            for (int i = 0; i < userBaseAccountInfos.size(); i++) {
                //如果获得的下标i 为ID且不等于null
                if (userBaseAccountInfos.get(i).getId() != null) {
                    //用户基本表中的id与账户安全设置表中的主键进行关联
                    accountSafeInfos.add(userAccountSafeInfoServiceI.selectBaseId(userBaseAccountInfos.get(i).getId()));
                }
            }
        } else {
            //把风控表的id设置为opid（主键id）
            userRisk.setId(new BigDecimal(opid));
            //通过主键id更新风控表
            userRiskService.updateById(userRisk);
            String json = JSON.toJSONString("操作:编辑成功!");
            sendJsonData(response, json);
        }

        //通过主键关联之后判断账户安全设置表不为空且返回的size（）>0
        if (accountSafeInfos != null && accountSafeInfos.size() > 0) {
            //对账户安全信息表中的字段进行设置
            for (int i = 0; i < accountSafeInfos.size(); i++) {
                //userRisk名单类型(1.黑名单 2.风险名单 3.应急改密名单 4.白名单)
                if (userRisk.getType().toString().equals("1")) {
                    //UserAccountSafeInfo Status(0.停用 1.正常)
                    accountSafeInfos.get(i).setStatus((short) 0);
                    //UserAccountSafeInfo Risklevel(1.正常 2.有风险 3.高风险 4.严重 5.锁定 6.冻结)
                    accountSafeInfos.get(i).setRisklevel((short) 6);
                } else if (userRisk.getType().toString().equals("2")) {
                    //UserAccountSafeInfo Status(0.停用 1.正常)
                    accountSafeInfos.get(i).setStatus((short) 1);
                    //UserAccountSafeInfo Risklevel(1.正常 2.有风险 3.高风险 4.严重 5.锁定 6.冻结)
                    accountSafeInfos.get(i).setRisklevel((short) 4);
                } else if (userRisk.getType().toString().equals("3")) {
                    //UserAccountSafeInfo Status(0.停用 1.正常)
                    accountSafeInfos.get(i).setStatus((short) 1);
                    //UserAccountSafeInfo Risklevel(1.正常 2.有风险 3.高风险 4.严重 5.锁定 6.冻结)
                    accountSafeInfos.get(i).setRisklevel((short) 2);
                } else if (userRisk.getType().toString().equals("4")) {
                    //UserAccountSafeInfo Status(0.停用 1.正常)
                    accountSafeInfos.get(i).setStatus((short) 1);
                    //UserAccountSafeInfo Risklevel(1.正常 2.有风险 3.高风险 4.严重 5.锁定 6.冻结)
                    accountSafeInfos.get(i).setRisklevel((short) 1);
                }
                //更新表中设置完成的字段状态
                userAccountSafeInfoServiceI.update(accountSafeInfos.get(i));
                if (userRisk != null) {
                    //把风控表的id设置为opid（主键id）
                    userRisk.setId(new BigDecimal(opid));
                    //通过主键id更新风控表
                    userRiskService.updateById(userRisk);
                    //以json的形式返回结果
                    String json = JSON.toJSONString("操作:编辑成功!");
                    sendJsonData(response, json);
                } else {
                    String json = JSON.toJSONString("操作:编辑失败!");
                    sendJsonData(response, json);
                }

            }
		 
		 
		 
		 /*正常添加的编辑*/
            //通过主键id关联表之间的关系
            UserAccountSafeInfo accountSafeInfo = userAccountSafeInfoServiceI.selectByBaseId(new BigDecimal(uid));
            //UserRisk risk = userRiskService.findUserRiskById(new BigDecimal(opid));
            //判断表不为空 且type为1时
            if (accountSafeInfo != null && type.equalsIgnoreCase("1")) {//黑名单
                //修改表的字段
                accountSafeInfo.setStatus((short) 0);
                accountSafeInfo.setRisklevel((short) 6);
                //更新表
                userAccountSafeInfoServiceI.update(accountSafeInfo);
                //判断风控表不为null
                if (userRisk != null) {
                    //把风控表的id设置为opid（主键id）
                    userRisk.setId(new BigDecimal(opid));
                    //通过主键id更新风控表
                    userRiskService.updateById(userRisk);
                    //以json的形式返回结果
                    String json = JSON.toJSONString("操作:编辑成功!");
                    sendJsonData(response, json);
                } else {
                    String json = JSON.toJSONString("操作:编辑失败!");
                    sendJsonData(response, json);
                }


            } else if (accountSafeInfo != null && type.equalsIgnoreCase("2")) {//风险名单
                accountSafeInfo.setStatus((short) 1);
                accountSafeInfo.setRisklevel((short) 4);
                userAccountSafeInfoServiceI.update(accountSafeInfo);
                if (userRisk != null) {
                    userRisk.setId(new BigDecimal(opid));
                    userRiskService.updateById(userRisk);
                    String json = JSON.toJSONString("操作:编辑成功!");
                    sendJsonData(response, json);
                } else {
                    String json = JSON.toJSONString("操作:编辑失败!");
                    sendJsonData(response, json);
                }
            } else if (accountSafeInfo != null && type.equalsIgnoreCase("3")) {//应急改密名单
                accountSafeInfo.setStatus((short) 1);
                accountSafeInfo.setRisklevel((short) 2);
                userAccountSafeInfoServiceI.update(accountSafeInfo);
                if (userRisk != null) {
                    userRisk.setId(new BigDecimal(opid));
                    userRiskService.updateById(userRisk);

                    String json = JSON.toJSONString("操作:编辑成功!");
                    sendJsonData(response, json);
                } else {
                    String json = JSON.toJSONString("操作:编辑失败!");
                    sendJsonData(response, json);
                }
            } else if (accountSafeInfo != null && type.equalsIgnoreCase("4")) {//白名单
                accountSafeInfo.setStatus((short) 1);
                accountSafeInfo.setRisklevel((short) 1);
                userAccountSafeInfoServiceI.update(accountSafeInfo);
                if (userRisk != null) {
                    userRisk.setId(new BigDecimal(opid));
                    userRiskService.updateById(userRisk);
                    String json = JSON.toJSONString("操作:编辑成功!");
                    sendJsonData(response, json);
                } else {
                    String json = JSON.toJSONString("操作:编辑失败!");
                    sendJsonData(response, json);
                }
            } else {
                String json = JSON.toJSONString("操作:编辑失败!");
                sendJsonData(response, json);
            }
        }
    }

//	 /**
//	  * 添加时不允许重复添加
//	  * @throws Exception 
//	  */
//	 @RequestMapping(value="/addUser",method={RequestMethod.POST,RequestMethod.GET})
//	 public void addUser(UserRisk userRisk) throws Exception{
//		 String username = request.getParameter("username");
//		 UserRisk risk = userRiskService.getByUsername(username);
//		 if(username != userRisk.getUsername()){
//			 String json = JSON.toJSONString("请继续添加!");
//			 sendJsonData(response, json);
//		 }else{
//			 String json = JSON.toJSONString("用户已存在!");
//			 sendJsonData(response, json);
//		 }
//	 }

    /**
     * @return
     * @Description :转至风险因子增加页面
     */
    @RequestMapping(value = "/fengXianYinZhiAddUI", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView insert_FXYZ() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/userRisk/userRisk_fengXianYinZhiAdd");
        return modelAndView;
    }

    /**
     * @return
     * @Description :根据用户名去用户表找用户,ajax的请求
     */
    @RequestMapping(value = "/findUserByUsername", method = {RequestMethod.POST, RequestMethod.GET})
    public void findUserByUsername(HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("??????????????????????????????????????");
//        sendJsonData(response, JSON.toJSONString("dd"));
        modelAndView.setViewName("/admin/userRisk/userRisk_addUser");

    }

    /**
     * @param userRisk
     * @throws Exception
     * @Description : 保存新增风险因子
     */
    @RequestMapping(value = "/batchInsert", method = {RequestMethod.POST, RequestMethod.GET})
    public void batchInsert(UserRisk userRisk)
            throws Exception {
        System.out.println(userRisk);
        //判断是否为重复添加
        List<UserRisk> list = userRiskService.selectUserRiskByCondition(userRisk);
        if (list.size() > 0) {
            String json = JSON.toJSONString("用户已添加!");
            sendJsonData(response, json);
        } else {
            AdminUser adminUser = getAdminUser();
            //判断type字段与表不能为空
            if (userRisk != null && userRisk.getType() != null) {
                //判断并添加添加人
                if (adminUser != null && adminUser.getUsername() != null) {
                    //设置添加人
                    userRisk.setAddman(adminUser.getUsername());
                }
                //设置添加时间
                userRisk.setAddtime(new Date());
                //插入数据
                userRiskService.insertSelective(userRisk);
                String json = JSON.toJSONString("添加成功!");
                sendJsonData(response, json);
            } else {
                String json = JSON.toJSONString("添加失败!");
                sendJsonData(response, json);
            }

            //如果UserBaseAccountInfo表中有前端页面传过来的数据且一致
            //创建这个对象来接收数据并设置
            UserBaseAccountInfo userBaseAccountInfo = new UserBaseAccountInfo();
            userBaseAccountInfo.setLoginname(userRisk.getUsername());
            userBaseAccountInfo.setRegip(userRisk.getIp());
            userBaseAccountInfo.setMobilephone(userRisk.getMobile());
            userBaseAccountInfo.setEmail(userRisk.getEmail());
            userBaseAccountInfo.setRegcookie(userRisk.getCookie());
            //创建集合来通过UserBaseAccountInfo查询数据
            List<UserBaseAccountInfo> userBaseAccountInfos = userBaseAccountInfoServiceI.getByUserBaseAccountInfo(userBaseAccountInfo);
            //用户账户安全设置表创建集合
            List<UserAccountSafeInfo> accountSafeInfos = new ArrayList<>();
            //如果里面有完全相等的数据 返回数据size（）>0
            if (userBaseAccountInfos.size() > 0) {
                //遍历
                for (int i = 0; i < userBaseAccountInfos.size(); i++) {
                    //如果获得的下标i 为ID且不等于null
                    if (userBaseAccountInfos.get(i).getId() != null) {
                        //用户基本表中的id与账户安全设置表中的主键进行关联
                        accountSafeInfos.add(userAccountSafeInfoServiceI.selectBaseId(userBaseAccountInfos.get(i).getId()));
                    }
                }
            }

            //通过主键关联之后判断账户安全设置表不为空且返回的size（）>0
            if (accountSafeInfos != null && accountSafeInfos.size() > 0) {
                //对账户安全信息表中的字段进行设置
                for (int i = 0; i < accountSafeInfos.size(); i++) {
                    //userRisk名单类型(1.黑名单 2.风险名单 3.应急改密名单 4.白名单)
                    if (userRisk.getType().toString().equals("1")) {
                        //UserAccountSafeInfo Status(0.停用 1.正常)
                        accountSafeInfos.get(i).setStatus((short) 0);
                        //UserAccountSafeInfo Risklevel(1.正常 2.有风险 3.高风险 4.严重 5.锁定 6.冻结)
                        accountSafeInfos.get(i).setRisklevel((short) 6);
                    } else if (userRisk.getType().toString().equals("2")) {
                        //UserAccountSafeInfo Status(0.停用 1.正常)
                        accountSafeInfos.get(i).setStatus((short) 1);
                        //UserAccountSafeInfo Risklevel(1.正常 2.有风险 3.高风险 4.严重 5.锁定 6.冻结)
                        accountSafeInfos.get(i).setRisklevel((short) 4);
                    } else if (userRisk.getType().toString().equals("3")) {
                        //UserAccountSafeInfo Status(0.停用 1.正常)
                        accountSafeInfos.get(i).setStatus((short) 1);
                        //UserAccountSafeInfo Risklevel(1.正常 2.有风险 3.高风险 4.严重 5.锁定 6.冻结)
                        accountSafeInfos.get(i).setRisklevel((short) 2);
                    } else if (userRisk.getType().toString().equals("4")) {
                        //UserAccountSafeInfo Status(0.停用 1.正常)
                        accountSafeInfos.get(i).setStatus((short) 1);
                        //UserAccountSafeInfo Risklevel(1.正常 2.有风险 3.高风险 4.严重 5.锁定 6.冻结)
                        accountSafeInfos.get(i).setRisklevel((short) 1);
                    }
                    //更新表中设置完成的字段状态
                    userAccountSafeInfoServiceI.update(accountSafeInfos.get(i));
                }
            }
        }
    }


    /**
     * @return
     * @Description :去用户添加页面
     */
    @RequestMapping(value = "/addUserUI", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView insert_AddUser() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/userRisk/userRisk_addUser");
        return modelAndView;
    }


    /**
     * @param @return 参数说明
     * @return String    返回类型
     * @throws
     * @Title: detail
     * @Description: TODO(查看用户风控详情信息)
     * @author shenggege
     */
    @RequestMapping(value = "/detailUI")
    public ModelAndView detail(HttpServletRequest request) {
        String id = request.getParameter("id");
        if (StringUtil.isNotEmpty(id)) {
            ModelAndView modelAndView = new ModelAndView();
            UserRisk userRisk = userRiskService.findUserRiskById(new BigDecimal(id));
            modelAndView.addObject("userRisk", userRisk);
            modelAndView.setViewName("/admin/userRisk/userRisk_detail");
            return modelAndView;
        } else {
            return null;
        }
    }


    /**
     * @param @return 参数说明
     * @return ModelAndView 返回类型
     * @throws
     * @Title:dingXiang
     * @Description: TODO(跳转定向添加页面)
     * @author shenggege
     */
    @RequestMapping(value = "/dingXiangAddUI", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView dingXiang() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/userRisk/userRisk_dingXiangAdd");
        return modelAndView;
    }

    /**
     * @return
     * @Description :跳转到按等级添加页面
     */
    @RequestMapping(value = "/dengjiAddUI", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView dengjiAddUI() {
        ModelAndView modelAndView = new ModelAndView();
        List<UserGrade> uGrades = userGradeService.getAll(null);
        modelAndView.addObject("uGrades", uGrades);
        modelAndView.setViewName("/admin/userRisk/userRisk_dengJiAdd");
        return modelAndView;
    }


    /**
     * @param @return 参数说明
     * @return 返回类型
     * @throws Exception
     * @throws
     * @Title:addDingXiang
     * @Description: TODO(定向添加方法)
     * @author shenggege
     */
    @RequestMapping(value = "/addDingXiang", method = {RequestMethod.POST, RequestMethod.GET})
    public void addDingXiang(UserRisk userRisk, SpecialNameList specialNameList, UserBaseAccountInfo userBaseAccountInfo) throws Exception {
        //从前端页面获取userRisk(用户风险控制表)中的type(类型:1.黑名单  2.风险名单  3.应急改密名单  4.白名单)字段
        String type = request.getParameter("type");
        //从前端页面获取SpecialNameList(定向名单表)中的businessNo(定向编号)字段
        String businessNo = request.getParameter("businessNo");

        //判断定向编号与风控类型数据数据是否取到
        if (specialNameList.getBusinessNo() != null && userRisk.getType() != null) {
			 /*如果存在定向表中,则通过定向编号查询出整条数据,且取出SystemBusType(系统业务具体类型),通过这个字段
			  * 来解析占位符(50位) 如果此占位符第五十位为1的话证明此名单为风险可添加名单,如果占位符最后一位为0,证明
			  * 此定向名单不是风险类型并不能添加*/

            //通过前端传过来的businessNo(定向名单编号)并根据定向编号获取定向名单列表
            SpecialNameList snl = specialNameListService.getSnlsByNoOrName(specialNameList);
            //得到定向名单名称(标题)
            snl.getBusinessName();
            //得到业务类型（1系统业务，2短信通知）
            snl.getBusinessType();

            //判断此编号
            if (snl.equals(null)) {
                //如果编号不存在定向名单表内,返回一个提示,并return出来
                String jsonStr = JSON.toJSONString("此定向编号不存在!");
                StringUtil.sendJsonData(response, jsonStr);
                //终止此程序
                return;
                //否则就获取此列表中业务类型为系统业务   (BusinessType 1.系统业务   2.短信通知 )
                //短信业务为排除的名单 只有系统业务方可进行下面的操作
            } else if (snl != null && snl.getBusinessType() == 1) {
				 /*SystemBusType字段为定向名单表的系统业务具体类型，用50位占位符标识，必须判断他的最后一位为1时才可添加并进行列的操作*/
                //获取systemBusType字段
                String systemBusType = snl.getSystemBusType();
                //用c接收systemBusType字段的占位符最后一位
                char c = systemBusType.charAt(systemBusType.length() - 1);
                //最后一位必须为1时才能进行下列操作
                if (c == '1') {
					 /*calculationCount方法为筛选出来的最终添加名单*/
                    //用一个集合来接收删选出来的定向名单
                    Set<String> set = calculationCount(specialNameList);
                    //得到这个名单的总人数
                    int num = set.size();
                    //遍历集合 查到所有名单内用户的登录名
                    for (String loginname : set) {
                        //把set集合里面的所有loginname取出来并在userRisk中设置
                        userRisk.setUsername(loginname);
                        //通过loginname得到所有对应的信息并设置
                        userBaseAccountInfo = userBaseAccountInfoServiceI.getuserloginname(loginname);
                        userRisk.setBaseid(userBaseAccountInfo.getId());
                        userRisk.setIp(userBaseAccountInfo.getRegip());
                        userRisk.setCookie(userBaseAccountInfo.getRegcookie());
                        userRisk.setMac(userBaseAccountInfo.getEmail());
                        userRisk.setMobile(userBaseAccountInfo.getMobilephone());
                        userRisk.setEmail(userBaseAccountInfo.getEmail());
                        //调管理员
                        AdminUser adminUser = getAdminUser();
                        //判断各条件不为空
                        if (userRisk != null && userRisk.getBaseid() != null && userRisk.getType() != null) {

                            if (adminUser != null && adminUser.getUsername() != null) {
                                //设置添加人
                                userRisk.setAddman(adminUser.getUsername());
                            }
                            //设置添加时间
                            userRisk.setAddtime(new Date());
                            //根据id主键关联
                            UserAccountSafeInfo accountSafeInfo = userAccountSafeInfoServiceI.selectByBaseId(userRisk.getBaseid());
                            //判断用户是否为重复添加
                            List<UserRisk> list = userRiskService.selectDixngXiangCondition(userRisk);

                            //如果返回的size()>0时,就说明该用户已经存在于userRisk之内,不添加
                            if (list.size() > 0) {
                                continue;
                                //否则就执行下面的程序
                            } else {

                                //如果为黑名单
                                if (type.equalsIgnoreCase("1")) {
                                    //UserAccountSafeInfo Status(0.停用 1.正常)
                                    accountSafeInfo.setStatus((short) 0);
                                    //UserAccountSafeInfo Risklevel(1.正常 2.有风险 3.高风险 4.严重 5.锁定 6.冻结)
                                    accountSafeInfo.setRisklevel((short) 6);
                                    //设置完成后更新UserAccountSafeInfo
                                    userAccountSafeInfoServiceI.update(accountSafeInfo);
                                    //把数据插入UserRisk
                                    userRiskService.insertSelective(userRisk);
                                    String json = JSON.toJSONString("添加成功!!!");
                                    sendJsonData(response, json);
                                    //为白名单时
                                } else if (type.equalsIgnoreCase("2")) {
                                    //UserAccountSafeInfo Status(0.停用 1.正常)
                                    accountSafeInfo.setStatus((short) 1);
                                    //UserAccountSafeInfo Risklevel(1.正常 2.有风险 3.高风险 4.严重 5.锁定 6.冻结)
                                    accountSafeInfo.setRisklevel((short) 4);
                                    //设置完成后更新UserAccountSafeInfo
                                    userAccountSafeInfoServiceI.update(accountSafeInfo);
                                    //把数据插入UserRisk
                                    userRiskService.insertSelective(userRisk);
                                    String json = JSON.toJSONString("添加成功!!!");
                                    sendJsonData(response, json);
                                    //为应急改密名单时
                                } else if (type.equalsIgnoreCase("3")) {
                                    //UserAccountSafeInfo Status(0.停用 1.正常)
                                    accountSafeInfo.setStatus((short) 1);
                                    //UserAccountSafeInfo Risklevel(1.正常 2.有风险 3.高风险 4.严重 5.锁定 6.冻结)
                                    accountSafeInfo.setRisklevel((short) 2);
                                    //设置完成后更新UserAccountSafeInfo
                                    userAccountSafeInfoServiceI.update(accountSafeInfo);
                                    //把数据插入UserRisk
                                    userRiskService.insertSelective(userRisk);
                                    String json = JSON.toJSONString("添加成功!!!");
                                    sendJsonData(response, json);
                                } else if (type.equalsIgnoreCase("4")) {
                                    //UserAccountSafeInfo Status(0.停用 1.正常)
                                    accountSafeInfo.setStatus((short) 1);
                                    //UserAccountSafeInfo Risklevel(1.正常 2.有风险 3.高风险 4.严重 5.锁定 6.冻结)
                                    accountSafeInfo.setRisklevel((short) 1);
                                    //设置完成后更新UserAccountSafeInfo
                                    userAccountSafeInfoServiceI.update(accountSafeInfo);
                                    //把数据插入UserRisk
                                    userRiskService.insertSelective(userRisk);
                                    String json = JSON.toJSONString("添加成功!!!");
                                    sendJsonData(response, json);
                                }
                            }
                        } else {
                            String json = JSON.toJSONString("添加失败!!!");
                            sendJsonData(response, json);
                        }
                    }

                } else if (c == '0') {
                    String jsonStr = JSON.toJSONString("此定向编号不能添加!");
                    StringUtil.sendJsonData(response, jsonStr);
                    //终止此程序
                    return;
                }
            }
        }
    }


    /**
     * 计算最终的获奖用户名单
     *
     * @param snl
     * @return
     * @throws Exception
     */
    public Set<String> calculationCount(SpecialNameList snl) throws Exception {

        //根据定向编号获取定向名单列表的id
        snl = specialNameListService.getSnlsByNoOrName(snl);
        //根据定向名单列表的id获取业务对象名单
        List<ActiveObjectList> aolList = activeObjectListService.selectBySNLId(snl.getId());

        Set<String> palmSet = new HashSet<String>();//存放所有获奖的用户名（利用Set的特性：重复数据覆盖！正好保证奖品发放不会发重复了）
        Set<String> removeSet = new HashSet<String>();//存放所有排除的用户名（利用Set的特性：重复数据覆盖！正好保证奖品发放不会发重复了）
        Set<String> ultimately = new HashSet<String>();//最终获奖人名单（注意不能省！因Set在迭代时不能删除元素）

        for (ActiveObjectList aol : aolList) {
            if (aol.getIsRightOrExcept() == 1) {
                if (aol.getNameType() == 1) {
                    //获奖大名单
                    List<RemoveName> rnList = removeNameService.getUserNameMax(aol.getNameContent());
                    for (RemoveName rn : rnList) {
                        System.out.println("获奖大名单下的所有用户》》》" + rn.getLoginname());
                        palmSet.add(rn.getLoginname());
                        ultimately.add(rn.getLoginname());

                    }
                    System.out.println("获奖大名单用户总数为：" + palmSet.size());
                }
                if (aol.getNameType() == 2) {
                    //获奖小名单
                    List<RemoveName> rnList = removeNameService.getRemoveNameByNameNo(aol.getNameContent());
                    for (RemoveName rn : rnList) {
                        System.out.println("获奖小名单下的所有用户》》》》》" + rn.getLoginname());
                        palmSet.add(rn.getLoginname());
                        ultimately.add(rn.getLoginname());

                    }
                    System.out.println("小名单之后的用户总数：" + palmSet.size());
                }
                if (aol.getNameType() == 3) {
                    System.out.println("获奖用户名》》》》" + aol.getNameContent());
                    //获奖用户名
                    palmSet.add(aol.getNameContent());
                    ultimately.add(aol.getNameContent());

                }
                if (aol.getNameType() == 4) {
                    //获取每一个会员等级下的所有用户
                    //需要对会员等级占位符进行解析
                    String nc = aol.getNameContent();
                    for (int i = 0; i < nc.length(); i++) {
                        char c = nc.charAt(i);
                        if (c == '1') {
                            List<UserAccountSafeInfo> name = userAccountSafeInfoServiceI.getuseradmin((short) i);
                            for (UserAccountSafeInfo re : name) {
                                System.out.println("会员ID》》" + re.getBaseid());
                                //获取用户名
                                UserBaseAccountInfo us = userBaseAccountInfoServiceI.getUserBaseAccountInfoById(re.getBaseid());
                                //保存进Set集合当中
                                if (us != null) {
                                    System.out.println("会员等级用户名》》》》" + us.getLoginname());
                                    palmSet.add(us.getLoginname());
                                    ultimately.add(us.getLoginname());

                                }
                            }
                        }
                    }

                    System.out.println("会员等级的用户总数：" + palmSet.size());
                }

                System.out.println("获奖的人数为》》》" + palmSet.size());

            } else if (aol.getIsRightOrExcept() == 2) {
                if (aol.getNameType() == 1) {
                    //排除大名单
                    List<RemoveName> rnList = removeNameService.getUserNameMax(aol.getNameContent());
                    for (RemoveName rn : rnList) {
                        System.out.println("排除大名单用户名》》》" + rn.getLoginname());
                        removeSet.add(rn.getLoginname());
                    }
                    System.out.println("获奖大名单用户总数为：" + removeSet.size());
                }

                if (aol.getNameType() == 2) {
                    //排除小名单
                    List<RemoveName> rnList = removeNameService.getRemoveNameByNameNo(aol.getNameContent());
                    for (RemoveName rn : rnList) {
                        System.out.println("排除小名单用户名》》》" + rn.getLoginname());
                        removeSet.add(rn.getLoginname());
                    }
                    System.out.println("排除小名单后用户总数：" + removeSet.size());
                }
                if (aol.getNameType() == 3) {
                    //排除用户名
                    System.out.println("排除用户名》》》" + aol.getNameContent());
                    removeSet.add(aol.getNameContent());
                }
                System.out.println("排除的人数为》》》》" + removeSet.size());
            }
        }
        System.out.println("排除的总人数为》》》》" + removeSet.size());

        //将获奖用户名单与排除名单对比，一致时，移除出获奖名单
        for (String palm : palmSet) {
            for (String remove : removeSet) {
                if (palm.equals(remove))
                    ultimately.remove(palm);
            }
        }
        for (String loginname : ultimately) {
            System.out.println("最终的获奖用户名单》》》" + loginname);
        }

        return ultimately;
    }

    /**
     * @param @return 参数说明
     * @return void   无返回类型
     * @throws IOException
     * @throws
     * @Title: isDingXiang
     * @Description: TODO(检测添加定向编号是否存在)
     * @author shenggege
     */
    @RequestMapping("/isDingXiang")
    public void isDingXiang(HttpServletResponse response, String businessNo, SpecialNameList specialNameList)
            throws Exception {
        System.out.println("bianhao ====" + businessNo);
        //通过前端传过来的businessNo(定向名单编号)并根据定向编号获取定向名单列表
        SpecialNameList snl = specialNameListService.getSnlsByNoOrName(specialNameList);
        Map<String, Object> map = new HashMap<>();
        if (snl != null) {
            //得到定向名单名称(标题)
            snl.getBusinessName();
            //得到业务类型（1系统业务，2短信通知）
            snl.getBusinessType();
        } else {
            String jsonStr = JSON.toJSONString("此编号不存在!");
            map.put("result", jsonStr);
            sendJsonData(response, JSON.toJSONString(map));
            return;
        }
        //用一个集合来接收删选出来的定向名单
        Set<String> set = calculationCount(specialNameList);
        //得到这个名单的总人数
        int num = set.size();
        if (businessNo != null && !"".equals(businessNo)) {
            //用户定向名单表
            //SpecialNameList snl = new SpecialNameList();
            snl.setBusinessNo(businessNo);
            specialNameListService.getSnlsByNoOrName(snl);
            snl = specialNameListService.selectSpecialNameListByCondition(snl);
            if (snl != null) {
                //String jsonStr = JSON.toJSONString("请确定添加!");
                //map.put("result", jsonStr);
                map.put("businessName", snl.getBusinessName());
                map.put("businessType", snl.getBusinessType());
                map.put("num", num);
                sendJsonData(response, JSON.toJSONString(map));
            } else {
                String jsonStr = JSON.toJSONString("此编号不存在!");
                map.put("result", jsonStr);
                sendJsonData(response, JSON.toJSONString(map));
            }
        }
    }


    /**
     * @param request
     * @param userRisk
     * @return
     * @Description :用户风险控制查看
     */
    @RequestMapping(value = "/FenghongListUI", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView ShowList(HttpServletRequest request, UserRisk userRisk) {
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");

        int num = 1;
        int size = 20;

        if (pageNum != null && !"".equals(pageNum)) {
            num = Integer.parseInt(pageNum);
        }
        if (pageSize != null && !"".equals(pageSize)) {
            size = Integer.parseInt(pageSize);
        }

        String sortString = "id.desc";
        Order.formString(sortString);

        PageHelper.startPage(num, size);

        List<UserRisk> userRisks = userRiskService.selectUserRiskByCondition(userRisk);
        PageInfo<UserRisk> pagehelper = new PageInfo<UserRisk>(userRisks);
        pagehelper.setFirstPage(1);

        int lastPageNum = 0;
        if (pagehelper.getTotal() % size == 0) {
            lastPageNum = (int) pagehelper.getTotal() / size;
        } else {
            lastPageNum = (int) pagehelper.getTotal() / size + 1;
        }
        pagehelper.setLastPage(lastPageNum);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pagehelper", pagehelper);
        modelAndView.setViewName("admin/showList/fenghongList");
        modelAndView.addObject("userRisk", userRisk);
        return modelAndView;
    }


    /**
     * @param @return 参数说明
     * @return modelAndView
     * @throws
     * @Title: show
     * @Description: TODO(黑名单)
     * @author shenggege
     */
    @RequestMapping(value = "/BlackListUI", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView BlackListShow(HttpServletRequest request, HttpServletResponse response, UserRisk userRisk) {
        //获得分页信息
        String pageS = request.getParameter("pageSize");
        String pageNo = request.getParameter("pageNo");
        int num = 1;
        int pageSize = 20;
        //根据id排序
        String sort = "id.desc";
        Order.formString(sort);
        PageHelper.startPage(num, pageSize);
        //查询全部方法
        List<UserRisk> userRisks = userRiskService.findAllBlackList();

        PageInfo<UserRisk> pagehelper = new PageInfo<UserRisk>(userRisks);
        pagehelper.setFirstPage(1);
        int lasePageNum = 0;
        if (pagehelper.getTotal() % pageSize == 0) {
            lasePageNum = (int) pagehelper.getTotal() / pageSize;
        } else {
            lasePageNum = (int) pagehelper.getTotal() / pageSize + 1;
        }
        pagehelper.setLastPage(lasePageNum);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/showList/blackList");
        modelAndView.addObject("pagehelper", pagehelper);
        return modelAndView;
    }


    /**
     * @param @return 参数说明
     * @return modelAndView
     * @throws
     * @Title: show
     * @Description: TODO(白名单)
     * @author shenggege
     */
    @RequestMapping(value = "/WhiteListUI", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView WhiteListShow(HttpServletRequest request, HttpServletResponse response) {

        String pageS = request.getParameter("pageSize");
        String pageNo = request.getParameter("pageNo");
        int num = 1;
        int pageSize = 20;
        //根据id排序
        String sort = "id.desc";
        Order.formString(sort);
        PageHelper.startPage(num, pageSize);
        //查询全部方法
        List<UserRisk> userRisks = userRiskService.findAllWhiteList();

        PageInfo<UserRisk> pagehelper = new PageInfo<UserRisk>(userRisks);
        pagehelper.setFirstPage(1);
        int lasePageNum = 0;
        if (pagehelper.getTotal() % pageSize == 0) {
            lasePageNum = (int) pagehelper.getTotal() / pageSize;
        } else {
            lasePageNum = (int) pagehelper.getTotal() / pageSize + 1;
        }
        pagehelper.setLastPage(lasePageNum);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/showList/whiteList");
        modelAndView.addObject("pagehelper", pagehelper);
        return modelAndView;

    }

    /**
     * @param @return 参数说明
     * @return modelAndView
     * @throws
     * @Title: show
     * @Description: TODO(风险名单)
     * @author shenggege
     */
    @RequestMapping(value = "/SuspiciousListUI", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView SuspiciousListShow(HttpServletRequest request, HttpServletResponse response) {
        String pageS = request.getParameter("pageSize");
        String pageNo = request.getParameter("pageNo");
        int num = 1;
        int pageSize = 20;
        //根据id排序
        String sort = "id.desc";
        Order.formString(sort);
        PageHelper.startPage(num, pageSize);
        //查询全部方法
        List<UserRisk> userRisks = userRiskService.findAllSuspiciousList();

        PageInfo<UserRisk> pagehelper = new PageInfo<UserRisk>(userRisks);
        pagehelper.setFirstPage(1);
        int lasePageNum = 0;
        if (pagehelper.getTotal() % pageSize == 0) {
            lasePageNum = (int) pagehelper.getTotal() / pageSize;
        } else {
            lasePageNum = (int) pagehelper.getTotal() / pageSize + 1;
        }
        pagehelper.setLastPage(lasePageNum);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/showList/suspiciousList");
        modelAndView.addObject("pagehelper", pagehelper);
        return modelAndView;

    }


    /**
     * @param @return 参数说明
     * @return modelAndView
     * @throws
     * @Title: show
     * @Description: TODO(应急改密名单)
     * @author shenggege
     */
    @RequestMapping(value = "/YingjigaimiListUI", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView ZymdListShow(HttpServletRequest request, HttpServletResponse response) {
        String pageS = request.getParameter("pageSize");
        String pageNo = request.getParameter("pageNo");
        int num = 1;
        int pageSize = 20;
        //根据id排序
        String sort = "id.desc";
        Order.formString(sort);
        PageHelper.startPage(num, pageSize);
        //查询全部方法
        List<UserRisk> userRisks = userRiskService.findAllZymdList();

        PageInfo<UserRisk> pagehelper = new PageInfo<UserRisk>(userRisks);
        pagehelper.setFirstPage(1);
        int lasePageNum = 0;
        if (pagehelper.getTotal() % pageSize == 0) {
            lasePageNum = (int) pagehelper.getTotal() / pageSize;
        } else {
            lasePageNum = (int) pagehelper.getTotal() / pageSize + 1;
        }
        pagehelper.setLastPage(lasePageNum);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/showList/yingjigaimiList");
        modelAndView.addObject("pagehelper", pagehelper);
        return modelAndView;
    }

//	 /**
//	  * @Title:login
//	  * @Description: TODO 登录验证
//	  * @author shenggege
//	  * @param username
//	  * @param req
//	  * @param session
//	  * @return
//	  */
//	 @RequestMapping(value = "/login")
//	 public ModelAndView login(String username,HttpServletRequest req,HttpSession session) 
//	 {
//	 //1：验证用户名及密码
//	 UserRisk userRisk = userRiskService.getByUsername(username);
//	 if(null == userRisk)
//	 {
//	 return mistake();
//	 }
//
//	 //是否需要验证当前登录用户
//	 boolean cancelLoginVerify = isCancelLoginVerify(userRisk);
//	 //如果用户为白名单时 验证码屏蔽掉,不需要验证,直接放行
//	 if(cancelLoginVerify)
//	 {
//	 return success();
//	 }
//	 //设置登录验证码
//	 CancelValidate cancelValidate = cancelValidateServiceI.getByUsername(username);
//	 cancelValidate.setCanceltype("1");
//
//	 //2如果为黑名单用户， 直接跳转修改密码页面(类型（1黑名单，2风险名单，3应急改密名单，4白名单）)
//	 if(1 == userRisk.getType()){
//	 return blacklist();
//	 }
//
//	 //3：得到登录真实IP/根据IP获取MAC地址/根据IP获取所在城市/用户信息
//	 String currLoginIp = HttpUtils.getIntranetIp(req);
//	 String macAddress = HttpUtils.getMACAddress(currLoginIp);
//	 String currLoginAddress = HttpUtils.getIpCity(currLoginIp).trim();
//
//	 //4:是否为黑名单环境
//	 if(isBlacklist("ip",currLoginIp,userRisk) || isBlacklist("mac",macAddress,userRisk) || isBlacklist("address",currLoginAddress,userRisk))
//	 {
//	 return blacklist();
//	 }
//
//	 //5.1：第一次登录,则记录地址和登录时间
//	 UserAccountSafeInfo userAccountSafeInfo = userAccountSafeInfoServiceI.getByUsername(username);
//	 Date currDate = new Date();
//	 if(null == userAccountSafeInfo.getLastlogintime() || null == userAccountSafeInfo.getLastloginip() )
//	 {
//	 userAccountSafeInfo.setLastloginip(currLoginIp);
//	 userAccountSafeInfo.setLastlogintime(currDate);
//	 session.setAttribute("userAccountSafeInfo",userAccountSafeInfo);
//	 return success();
//	 }
//	 else
//	 {
//	 //5.2：在1小时范围内，登录城市不一样，则为风险名单
//	 if(currDate.getTime() - userAccountSafeInfo.getLastlogintime().getTime() < 1000 * 60 * 60)
//	 {
//	 if(!currLoginAddress.equals(userAccountSafeInfo.getLastloginip()))
//	 {
//	 userRisk.setType((short)2);
//	 req.setAttribute("message", String.format("一小时内出现不同登录城市，上一次地点:%s,本次地点:%s ,将%s标识为风险名单",userAccountSafeInfo.getLastloginip(),currLoginAddress,username));
//	 req.setAttribute("username",username);
//	 return suspicious();
//	 }
//	 }
//	 else
//	 {
//	 //5.3：超过1小时，则重新记录登录城市和登录时间
//	 userAccountSafeInfo.setLastloginip(currLoginAddress);
//	 userAccountSafeInfo.setLastlogintime(currDate);
//	 }
//	 }
//
//	 	 //6.1：判断用户登录IP所在城市 手机城市 身份证城市 常用城市是否一致
//	     if(userRisk.getIp().contains(currLoginAddress) || currLoginAddress.equals(userRisk.getIp()))
//	     {
//	 return success();
//	     }
//	     else
//	     {
//	      //将用户标记为 风险名单
//	      userRisk.setType((short) 2);
//	     
//	 //生成验证码
//	 int threadLocalRandom = RandomUtils.threadLocalRandom(1000, false, 9999,false);
//	 session.setAttribute("verifyCode",String.valueOf(threadLocalRandom));
//	 session.setAttribute("verifyCity",currLoginAddress);
//	 session.setAttribute("currUser",userRisk);
//
//	 //6.2:调用接口发布 验证码（邮箱或手机号）
//	 System.out.println(String.format("%s 您好，系统检测到您 账户出现异常，登录城市:%s与常用城市不一致验证，执行系统验证，本次验证码：%s", username,currLoginAddress,threadLocalRandom));
//	 return suspicious();
//	     }
//	 }
//
//	 /**
//	  * @Title:verify 
//	  * @Description: TODO 是否将城市添加为常驻城市
//	  * @author shenggege
//	  * @param req
//	  * @return
//	  */
//	 @RequestMapping(value = "/verify")
//	 public ModelAndView verify(HttpServletRequest req) 
//	 {
//	 //从表单中获取用户输入的验证码
//	 String code = req.getParameter("code");
//	 HttpSession session = req.getSession();
//	 Object currUser = session.getAttribute("currUser");
//	 Object verifyCode = session.getAttribute("verifyCode");
//	 Object verifyCity = session.getAttribute("verifyCity");
//	 
//	 if(StringUtils.isNotEmpty(code) && null != currUser && null != verifyCode &&null != verifyCity)
//	 {
//		 
//	 //从session移除，释放内存
//	 session.removeAttribute("verifyCode");
//	 session.removeAttribute("verifyCity");
//	 
//	 //验收通过后，添加为常驻城市
//	 UserRisk userRisk = (UserRisk) currUser;
//	 if(code.equals(verifyCode))
//	 {
//	 //新增用户的常驻城市
//	 userRiskService.addResident(verifyCity,userRisk);
//	 //清除风险
//	 userRisk.setType((short) 4);
//	 }
//	 else
//	 {
//	 //验证失败，标识为风险名单
//	 userRisk.setType((short) 2);
//	 }
//	 }
//	 return null;
//	 }
//
//	 /**
//	  * @Title:loginVerify
//	  * @Description: TODO 是否取消  登录验证码 验证
//	  * @author shenggege
//	  * @param req
//	  * @return
//	  */
//	 public boolean isCancelLoginVerify(UserRisk userRisk) 
//	 {
//	 boolean tag = false;
//	 switch (userRisk.getType()) 
//	 {
//	 //如果用户为白名单时 验证码屏蔽掉,不需要验证
//	 case 1:
//	 tag = true;
//	 break;
//	 //如果用户为黑名单或风险名单时 登录需要验证码验证 输入账号密码 还得有正确的验证码才能登录
//	 case 2:
//	 tag = false;
//	 break;
//	 //应急改密名单不考虑
//	 case 3:
//	 tag = true;
//	 break;
//	 //如果用户为黑名单或风险名单时 登录需要验证码验证 输入账号密码 还得有正确的验证码才能登录
//	 case 4:
//	 tag = false;
//	 break;
//	 }
//	 return tag;
//	 }
//
//	 /**
//	  * @Title:isCancelRegisterVerify
//	  * @Description: TODO 是否取消注册　验证码屏蔽
//	  * @author shenggege
//	  * @param userRisk
//	  * @return
//	  */
//	 public boolean isCancelRegisterVerify(String ip,String cookie) 
//	 {
//	 boolean tag = false;
//	 //用户注册时先判断用户以前有没有用这个ip或cookie注册过
//	 if(userRiskService.ipIsRegister(ip) && userRiskService.cookieIsRegister(cookie))
//	 {
//	 tag = true;
//	 }
//	 return tag;
//	 }
//
//	 /**
//	  * @param type 渠道类型(ip,mac,IP所在城市)
//	  * @param value 对应的 ip,mac,IP所在城市
//	  * @param userRisk 用户信息
//	  * @author shenggege
//	  * @return
//	  */
//	 public boolean isBlacklist(String type, String data, UserRisk userRisk) 
//	 {
//	 boolean tag = false;
//	 switch (type) 
//	 {
//	 case "ip":
//	 tag = userRiskService.ipIsBlacklist(data,userRisk);
//	 break;
//	 case "nac":
//	 tag = userRiskService.macIsBlacklist(data,userRisk);
//	 break;
//	 case "ipAddress":
//	 tag = userRiskService.ipAddressIsBlacklist(data,userRisk);
//	 break;
//	 }
//	 return tag;
//	 }
//
//	 // 用户输入有误(密码有误不能登录的)
//	 public ModelAndView mistake() 
//	 {
//		 //如果用户输入有误导致不能正常登录，再跳转一次登录页面供用户再次登录
//		 ModelAndView modelAndView = new ModelAndView();
//		 modelAndView.setViewName("/admin/login");
//		 return modelAndView;
//	 }
//
//	 // 白名单用户登录
//	 public ModelAndView success() 
//	 {
//		 
//	 return new ModelAndView("正常登录不需要屏蔽页面");
//	 }
//
//	 // 风险名单:短信验证 邮箱验证码 安保密码验证(三取其一验证即可)
//	 public ModelAndView suspicious() 
//	 {
//	 return new ModelAndView("可疑页面");
//	 }
//
//	 // 黑名单风险响应:密码重置 通知管理员 短信通知用户
//	 public ModelAndView blacklist() 
//	 {
//	 return new ModelAndView("黑名单页面");
//	 }


    /**
     * 登录验证
     */
    @RequestMapping(value = "/loginVerify")
    public ModelAndView loginVerify(HttpServletRequest request, String loginname, HttpSession session) {
        //当用户在前端点击登录按钮时 必须验证用户名及密码
        UserRisk userRisk = userRiskService.getByUsername(loginname);
        //如果userRisk(用户风控表)为null
        if (userRisk == null) {
            //返回mistake方法,此方法为重新返回到登录页面
            return mistake();
        }

        //判断是否需要验证当前的登录用户
        boolean cancelLoginVerify = isCancelLoginVerify(userRisk);
        //如果用户为白名单,验证码屏蔽掉
        if (cancelLoginVerify) {
            return success();
        }
        //设置取消登录验证码
        CancelValidate cancelValidate = cancelValidateServiceI.getByUsername(loginname);
        cancelValidate.setCanceltype("1");

        return null;
    }


    private boolean isCancelLoginVerify(UserRisk userRisk) {
        // TODO Auto-generated method stub
        return false;
    }


    private ModelAndView success() {
        // TODO Auto-generated method stub
        return null;
    }


    private ModelAndView mistake() {
        // TODO Auto-generated method stub
        return null;
    }
}

	
