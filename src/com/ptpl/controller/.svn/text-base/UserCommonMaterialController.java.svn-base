package com.ptpl.controller;

import com.ptpl.constant.Session_Constant;
import com.ptpl.model.*;
import com.ptpl.service.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author liuj
 *         用户资料记录(公共和补充)
 */
@Controller
@RequestMapping(value = "/user/common")
public class UserCommonMaterialController extends BaseController {

    @Autowired
    private UserCommonMaterialServiceI userCommonMaterialService;
    @Autowired
    private UserLoanMaterialServiceI userLoanMaterialServicei;
    @Autowired
    private LoanCertPicpathServiceI loanCertPicpathService;
    @Autowired
    private UserLoanMaterialServiceI userLoanMaterialService;
    @Autowired
    private loanappServiceI loanappService;

    //借款资料项目引用设置
    @Autowired
    private LoanItemQuoteServiceI loanItemQuoteService;

    //文本选择类上传(公共资料)
    @RequestMapping(value = "/adduserCommon", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView adduserCommon(UserCommonMaterial commonMaterial, HttpServletRequest request, HttpServletResponse response, @RequestParam("files") CommonsMultipartFile[] files) throws Exception {
        BigDecimal baseid = commonMaterial.getBaseid();
        List<UserCommonMaterial> userCommonMaterials = commonMaterial.getUserCommonMaterials();
        System.out.println("userCommonMaterials:*********************" + userCommonMaterials);
        if (userCommonMaterials.size() > 0) {
            for (UserCommonMaterial ucm : userCommonMaterials) {
                String myFileName = ucm.getMaterialpic();
                if (myFileName != null && !(myFileName.equals(""))) {
                    System.out.println("files:**********" + files);
                    for (CommonsMultipartFile file : files) {// 取得当前上传文件的文件名称
                        String fname = file.getOriginalFilename();
                        if (myFileName.trim() != "" && fname.equals(myFileName)) {
                            System.out.println(myFileName);
                            // 重命名上传后的文件名
                            String suffix = myFileName.substring(myFileName.lastIndexOf('.'));
                            System.out.println("截取文件后缀--->" + suffix);
                            String prefix = System.currentTimeMillis() + "";
                            String newfileName = prefix + suffix;
                            System.out.println("newfileName:------" + newfileName);
                            // 创建文件夹
                            File f = null;
                            f = new File("/upload/UserCommonMaterial");
                            if (!f.exists()) {
                                f.mkdirs();
                            }
                            // 定义上传路径
                            String path = f.getPath() + "/" + newfileName;
                            //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
                            File localFile = new File(path);
                            file.transferTo(localFile);
                            //保存用户公共资料
                            ucm.setAddtime(new Date());
                            ucm.setBaseid(baseid);
                            ucm.setMaterialpic(newfileName);
                            userCommonMaterialService.insertSelective(ucm);
                            //项目设置为补引用
                            LoanItemQuote loanItemQuote = new LoanItemQuote();
                            String liqno = ucm.getLiqno();
                            loanItemQuote.setLiqno(liqno);
                            loanItemQuote.setIscite((short) 1);
                            loanItemQuoteService.updateByLiqno(loanItemQuote);
                            break;
                        }
                    }
                } else {
                    //保存非图片类的
                    ucm.setAddtime(new Date());
                    ucm.setBaseid(baseid);
                    userCommonMaterialService.insertSelective(ucm);
                    LoanItemQuote loanItemQuote = new LoanItemQuote();
                    String liqno = ucm.getLiqno();
                    loanItemQuote.setLiqno(liqno);
                    loanItemQuote.setIscite((short) 1);
                    loanItemQuoteService.updateByLiqno(loanItemQuote);
                }
            }
        }
        ModelAndView mv = new ModelAndView();
        UserBaseAccountInfo accountInfo = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);//获取用户
        if (accountInfo != null) {
            if (accountInfo.getId() != null) {
                List<UserCommonMaterial> list = userCommonMaterialService.selectAllByBaseid(accountInfo.getId());
                mv.addObject("list", list);
                mv.setViewName("redirect:/user/common/pubdata.action");//跳转至公共资料显示页面
            }
        } else {
            mv.setViewName("user/manager/login");
        }
        return mv;
    }

    @RequestMapping(value = "/pubdata", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView pubdata() {
        ModelAndView mv = new ModelAndView();
        UserBaseAccountInfo accountInfo = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);//获取用户
        List<UserCommonMaterial> list = userCommonMaterialService.selectAllByBaseid(accountInfo.getId());
        mv.addObject("list", list);
        mv.setViewName("/user/manager/borrowing/pubdata");//跳转至公共资料显示页面
        return mv;
    }

    //用户修改公共自资料(文本和选择)
    @RequestMapping(value = "updateuserCommon", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView updateuserCommon(UserCommonMaterial commonMaterial) throws Exception {
        if (commonMaterial.getBaseid() != null) {//判断用户id是否为空
            int a = userCommonMaterialService.delectBybaseid(commonMaterial.getBaseid());//先删除用户原来的公共资料，再添加
            if (a > 0) {//删除成功
                List<UserCommonMaterial> commonMaterialneeds = commonMaterial.getCommonneeds();//自填类集合
                List<UserCommonMaterial> commonMaterialpresets = commonMaterial.getCommonpreset();//选择类集合
                commonMaterial.setAddtime(new Date());//添加时间
                if (commonMaterialneeds.size() > 0) {//填自类
                    for (int i = 0; i < commonMaterialneeds.size(); i++) {
                        commonMaterial.setLiqno(commonMaterialneeds.get(i).getLiqno());//引用资料编号
                        commonMaterial.setMaterialname(commonMaterialneeds.get(i).getMaterialname());//资料名称
                        commonMaterial.setMaterialcontent(commonMaterialneeds.get(i).getMaterialcontent());//资料内容
                        userCommonMaterialService.insertSelective(commonMaterial);
                    }
                }
                if (commonMaterialpresets.size() > 0) {//选择类
                    for (int i = 0; i < commonMaterialpresets.size(); i++) {
                        commonMaterial.setLiqno(commonMaterialpresets.get(i).getLiqno());//引用资料编号
                        commonMaterial.setMaterialname(commonMaterialpresets.get(i).getMaterialname());//资料名称
                        commonMaterial.setMaterialcontent(commonMaterialpresets.get(i).getMaterialcontent());//资料内容
                        userCommonMaterialService.insertSelective(commonMaterial);
                    }
                }
            }
        }
        return new ModelAndView("redirect:/user/loan/usercommonpic.action");

    }


    //图片上传(公共资料)
    @RequestMapping(value = "/adduserCommonpic", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView adduserCommonpic(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        ModelAndView modelandview = new ModelAndView();
        // 1.创建 DiskFileItemFactory
        DiskFileItemFactory factory = new DiskFileItemFactory(10 * 1024, new File("e:/temp/")); // 使用默认的.
        // 2.创建ServletFileUpload
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 总大小为10m
        upload.setSizeMax(1024 * 1024 * 20);

        if (upload.getSizeMax() > (1024 * 1024 * 20)) {
            throw new FileUploadException("文件超过了指定的容量,文件只支持10M");
        }
        //创建对象，用于参数传递给service,好使材料保存至数据库中
        UserCommonMaterial commonMaterial = new UserCommonMaterial();
        // 用于判断是否是上传操作.
        boolean flag = upload.isMultipartContent(request);
        //上传时间，并以时间生成文件夹保存数据
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String dateString = formatter.format(currentTime);
        if (flag) {
            // 解决上传文件名称中文乱码
            upload.setHeaderEncoding("utf-8");
            try {
                // 解决request,得到所有的上传项FileItem
                List<FileItem> items = upload.parseRequest(request);
                // 3.得到所有上传项
                int num = 0;//用于保存添加信息的状态
                for (FileItem item : items) {
                    if (item.isFormField()) {
                        /*// 非上传组件
                        System.out.println("组件名称:" + item.getFieldName());
						// 解决乱码问题
						System.out.println("内容:" + item.getString("utf-8")); */
                        if (item.getFieldName().equals("baseid"))
                            commonMaterial.setBaseid(new BigDecimal(item.getString("utf-8")));//baseid
                        //System.out.println("检测baseid是否拿到值==========="+commonMaterial.getBaseid());
                        if (item.getFieldName().equals("remark")) {
                            commonMaterial.setRemark(item.getString());//备注
                            //System.out.println("检测remark是否拿到值==========="+commonMaterial.getRemark());
                        }
                    } else {
                        //如果用户当前材料没有，无法上传时，跳过当前循环，进行下一次判断是否有文件上传
                        if (item.getFieldName() == null || item.getFieldName() == "" && item.getName() == null || item.getName() == "")
                            continue;
                        // 上传组件
                            /*System.out.println("组件名称:" + item.getFieldName());
                            System.out.println("上传文件名称:" + item.getName());*/
                        //当前上传文件的资料名称
                        commonMaterial.setMaterialname(item.getFieldName().split(",")[0]);
                        //上传文件编号
                        commonMaterial.setLiqno(item.getFieldName().split(",")[1]);
                        //当前上传时间
                        commonMaterial.setAddtime(new Date());
                        //当前资料图片名称
                        String name = item.getName(); // 上传文件名称
                        name = name.substring(name.lastIndexOf("\\") + 1);
                        //将新文件以UUID生成新名字，以保证不会重名
                        String uuidname = getUUIDFileName(name);
                        //System.out.println("新文件名:"+uuidname);
                        //D:/picture/userimage/"+baseid+"/"+dateString
                        File path = pictureFiles(commonMaterial.getBaseid(), dateString);
                        IOUtils.copy(item.getInputStream(), new FileOutputStream(path + "\\" + uuidname));
                        //截取部分，因数据库只需要部分
                        commonMaterial.setMaterialpic((commonMaterial.getBaseid() + "/" + dateString + "/" + uuidname));
                        System.out.println("当前文件的路径。。。。。。。。" + commonMaterial.getMaterialpic());
                        System.out.println("看看作用域baseid是否拿到值===========" + commonMaterial.getBaseid());
                        // 删除临时文件
                        item.delete();
                        if (commonMaterial.getMaterialpic() != null) {
                            num = userCommonMaterialService.insertSelective(commonMaterial);
                               /*UserLoanMaterial loanMaterial=new UserLoanMaterial();
                               loanMaterial.setBaseid(commonMaterial.getBaseid());//用户id
							   loanMaterial.setMaterialtype((short) 1);//资料类型
							   loanMaterial.setMaterialname(commonMaterial.getMaterialname());//资料名称
							   loanMaterial.setMaterialpic(commonMaterial.getMaterialpic());
							   loanMaterial.setAddtime(commonMaterial.getAddtime());
							   userLoanMaterialService.insertSelective(loanMaterial);*/
                            //每次操作后，清除已有路径，防止上述条件被执行
                            commonMaterial.setMaterialpic(null);
                        }
                    }
                }
                //循环结束后进入图片展示方法
                if (num > 0)
                    modelandview.setViewName("redirect:/user/loan/jumpusercommon.action?baseid=" + commonMaterial.getBaseid());
            } catch (FileUploadException e) {
                response.getWriter().write(e.getMessage());
                return modelandview;
            }
        } else {
            response.getWriter().write("不是上传操作");
            return modelandview;
        }
        return modelandview;
    }

    private File pictureFiles(BigDecimal baseid, String dateString) {
        File file = new File("D:/picture/userimage/" + baseid + "/" + dateString);
        //如果文件夹不存在则创建
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
        return file;
    }

    //获取随机名称 a.jpg
    private String getUUIDFileName(String filename) {
        int index = filename.lastIndexOf(".");
        if (index != -1) {
            return UUID.randomUUID() + filename.substring(index);
        } else {
            return UUID.randomUUID().toString();
        }
    }

    //图片展示详情(用户后台-公共资料)
    @RequestMapping(value = "/picturedetails", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView picturedetails(String liano) {
        System.out.println("方法进来没有  liano====" + liano);
        ModelAndView modelandview = new ModelAndView();
        List<LoanCertPicpath> piclist = loanCertPicpathService.selectbybaseid(liano);
        modelandview.addObject("piclist", piclist);
        modelandview.setViewName("user/loans/picture");
        return modelandview;
    }


    //文本选择类(补充资料)
    @RequestMapping(value = "/adduserLoanMaterial", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView adduserLoanMaterial(UserLoanMaterial loanMaterial) {
        ModelAndView mv = new ModelAndView();
        Integer id = Integer.parseInt(request.getParameter("appid"));//获取借款id
        List<UserLoanMaterial> loanMaterialneeds = loanMaterial.getInfoNeeds();//自填类资料
        List<UserLoanMaterial> loanMaterialpreset = loanMaterial.getInfoPresets();//选择类资料
        loanMaterial.setAddtime(new Date());//添加时间
        loanMaterial.setMaterialtype((short) 2);//设置为补充资料
        loanMaterial.setAuditstatus((short) 0);//深圳状态
        if (loanMaterialneeds.size() > 0) {//自填类
            for (int i = 0; i < loanMaterialneeds.size(); i++) {
                loanMaterial.setLinno(loanMaterialneeds.get(i).getLinno());//资料编号
                loanMaterial.setMaterialname(loanMaterialneeds.get(i).getMaterialname());//资料名称
                loanMaterial.setMaterialcontent(loanMaterialneeds.get(i).getMaterialcontent());//资料内容
                userLoanMaterialServicei.insertSelective(loanMaterial);
            }
        }
        if (loanMaterialpreset.size() > 0) {//选择类
            for (int i = 0; i < loanMaterialpreset.size(); i++) {
                loanMaterial.setLinno(loanMaterialpreset.get(i).getLinno());//资料编号
                loanMaterial.setMaterialname(loanMaterialpreset.get(i).getMaterialname());//资料名称
                loanMaterial.setMaterialcontent(loanMaterialpreset.get(i).getMaterialcontent());//资料内容
                userLoanMaterialServicei.insertSelective(loanMaterial);
            }
        }
        mv.setViewName("redirect:/user/loan/usercommonpicbucho.action?id=" + id);
        return mv;
    }

    //资料上传类(补充资料)
    @RequestMapping(value = "/adduserLoanMaterialprc", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView adduserLoanMaterialprc(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        ModelAndView modelandview = new ModelAndView();
        // 1.创建 DiskFileItemFactory
        DiskFileItemFactory factory = new DiskFileItemFactory(); // 使用默认的.
        // 2.创建ServletFileUpload
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 总大小为10m
        upload.setSizeMax(1024 * 1024 * 20);
        //创建对象，用于参数传递给service,好使材料保存至数据库中
        loanapp loanapp = new loanapp();
        UserLoanMaterial commonMaterial = new UserLoanMaterial();
        // 用于判断是否是上传操作.
        boolean flag = upload.isMultipartContent(request);
        //上传时间，并以时间生成文件夹保存数据
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String dateString = formatter.format(currentTime);
        if (flag) {
            // 解决上传文件名称中文乱码
            upload.setHeaderEncoding("utf-8");
            try {
                // 解决request,得到所有的上传项FileItem
                List<FileItem> items = upload.parseRequest(request);
                // 3.得到所有上传项
                int num = 0;//用于保存添加信息的状态
                for (FileItem item : items) {
                    if (item.isFormField()) {
                        // 非上传组件
                        System.out.println("组件名称:" + item.getFieldName());
                        // 解决乱码问题
                        System.out.println("内容:" + item.getString("utf-8"));
                        if (item.getFieldName().equals("baseid"))
                            commonMaterial.setBaseid(new BigDecimal(item.getString("utf-8")));//baseid
                        System.out.println("检测baseid是否拿到值===========" + commonMaterial.getBaseid());
                        if (item.getFieldName().equals("remark")) {
                            commonMaterial.setRemark(item.getString());//备注
                            System.out.println("检测remark是否拿到值===========" + commonMaterial.getRemark());
                        }
                        if (item.getFieldName().equals("loanno")) {
                            commonMaterial.setLoanno(item.getString());//借款编号
                            System.out.println("检测remark是否拿到值===========" + commonMaterial.getLoanno());
                        }
                        if (item.getFieldName().equals("loanappid")) {
                            loanapp.setId(new BigDecimal(item.getString("utf-8")));//借款id
                            System.out.println("借款id+++++" + loanapp.getId());
                        }
                    } else {
                        //如果用户当前材料没有，无法上传时，跳过当前循环，进行下一次判断是否有文件上传
                        if (item.getFieldName() == null || item.getFieldName() == "" && item.getName() == null || item.getName() == "")
                            continue;
                        // 上传组件
                        System.out.println("组件名称:" + item.getFieldName());
                        System.out.println("上传文件名称:" + item.getName());
                        //当前上传文件的资料名称
                        commonMaterial.setMaterialname(item.getFieldName());
                        //当前上传时间
                        commonMaterial.setAddtime(new Date());
                        //资料类型(2补充)
                        commonMaterial.setMaterialtype((short) 2);
                        //审核状体
                        commonMaterial.setAuditstatus((short) 0);
                        //当前资料图片名称
                        String name = item.getName(); // 上传文件名称
                        name = name.substring(name.lastIndexOf("\\") + 1);
                        //将新文件以UUID生成新名字，以保证不会重名
                        String uuidname = getUUIDFileName(name);
                        System.out.println("新文件名:" + uuidname);
                        File path = pictureFiles(commonMaterial.getBaseid(), dateString);
                        IOUtils.copy(item.getInputStream(), new FileOutputStream(path + "\\" + uuidname));
                        //截取部分，因数据库只需要部分
                        commonMaterial.setMaterialpic((commonMaterial.getBaseid() + "/" + dateString + "/" + uuidname));
                        System.out.println("当前文件的路径。。。。。。。。" + commonMaterial.getMaterialpic());
                        System.out.println("看看作用域baseid是否拿到值===========" + commonMaterial.getBaseid());
                        // 删除临时文件
                        item.delete();
                        if (commonMaterial.getMaterialpic() != null) {
                            num = userLoanMaterialServicei.insertSelective(commonMaterial);
                            //每次操作后，清楚已有路径，防止上述条件被执行
                            commonMaterial.setMaterialpic(null);
                        }
                    }
                }
                //循环结束后进入图片展示方法
                if (num > 0)
                    loanapp.setMastatus((short) 2);
                loanappService.updateByPrimaryKeySelective(loanapp);
                modelandview.setViewName("redirect:/user/loan/selectbaseid.action?id=" + commonMaterial.getBaseid());
            } catch (FileUploadException e) {
                response.getWriter().write(e.getMessage());
                return modelandview;
            }
        } else {
            response.getWriter().write("不是上传操作");
            return modelandview;
        }
        return modelandview;
    }
}
