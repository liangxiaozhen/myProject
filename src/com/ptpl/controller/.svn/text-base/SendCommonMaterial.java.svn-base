package com.ptpl.controller;

import com.alibaba.fastjson.JSON;
import com.ptpl.constant.Session_Constant;
import com.ptpl.model.LoanInfoPreset;
import com.ptpl.model.LoanItemQuote;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserCommonMaterial;
import com.ptpl.service.LoanItemQuoteServiceI;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.service.UserCommonMaterialServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RequestMapping(value = "/sendCommonMaterial")
@Controller
public class SendCommonMaterial extends BaseController {

    @Autowired
    UserBaseAccountInfoServiceI userBaseAccountInfoService;
    @Autowired
    private UserCommonMaterialServiceI userCommonMaterialService;//用户公共资料
    @Autowired
    private LoanItemQuoteServiceI loanItemQuoteService;//借款资料项目应用

    //填写公共资料(文本类和选择类)
    @RequestMapping(value = "/usercommon2", method = {RequestMethod.POST, RequestMethod.GET})
    public void usercommon2() throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("text/html; charset=utf-8");
        ModelAndView mv = new ModelAndView();
        //查出公共自填类的项目
        List<LoanItemQuote> public_ZiTians = loanItemQuoteService.public_ZiTian();
        //查出公共选择类的项目(及其对应的选择项也查出来)
        List<LoanItemQuote> public_XuanZes = loanItemQuoteService.public_XuanZe();
        //把公共自填类的项目和公共选择类的项目放在一起
        List<LoanItemQuote> loanItemQuotes = new ArrayList<LoanItemQuote>();
        loanItemQuotes.addAll(public_ZiTians);
        loanItemQuotes.addAll(public_XuanZes);
        //对list进行排序
        int size = loanItemQuotes.size();
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                LoanItemQuote loanItemQuote1 = loanItemQuotes.get(i);
                LoanItemQuote loanItemQuote2 = loanItemQuotes.get(j);
                if (loanItemQuote1.getSeriesno() > loanItemQuote2.getSeriesno()) {
                    List<LoanItemQuote> temp = new ArrayList<LoanItemQuote>();
                    temp.add(loanItemQuote1);
                    loanItemQuotes.set(i, loanItemQuote2);
                    loanItemQuotes.set(j, temp.get(0));
                }
            }
        }
        for (LoanItemQuote liq : loanItemQuotes) {
            liq.setSeriesno(null);
            LoanInfoPreset loanInfoPreset = liq.getLoanInfoPreset();
            if(loanInfoPreset!=null){
                loanInfoPreset.setMultino(null);
            }
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("content", loanItemQuotes);
        System.out.println(map);
        sendJsonData(response, JSON.toJSONString(map));
    }


    //公共资料跳转的时候需判断用户是否已经填写资料(没填写则跳转填写页面,填写完则跳转显示页面)
    @RequestMapping(value = "/jumpusercommon", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView jumpusercommon() {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("text/html; charset=utf-8");
        ModelAndView mv = new ModelAndView();
        UserBaseAccountInfo accountInfo = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);//获取用户
        if (accountInfo != null) {
            if (accountInfo.getId() != null) {
                List<UserCommonMaterial> list = userCommonMaterialService.selectAllByBaseid(accountInfo.getId());
                List<String> strings = new ArrayList<>();
                if (list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getMaterialpic() != null) {
                            strings.add(list.get(i).getMaterialpic());//添加所有的图片路径
                        }
                    }
                    mv.addObject("list", list);
                    mv.setViewName("/user/manager/borrowing/pubdata");//跳转至公共资料显示页面
                } else {
                    mv.setViewName("redirect:/sendCommonMaterial/usercommon2.action");//跳转至公共资料填写页面
                }
            }
        } else {
            mv.setViewName("redirect:user/tologin.action");
        }
        return mv;
    }

}
