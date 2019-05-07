package com.ptpl.web.util;

import com.ptpl.model.UserGrade;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author ywp
 * @ClassName UserGradeUtil
 * @Package com.ptpl.web.util
 * @Description: TODO 标设置中会员等级相关内容
 * @date 2017/4/10
 */
public class UserGradeUtil {
    /**
     * Date: 2017/4/10
     * author: Ywp
     * Description: 获取已设置的会员等级个数
     * Parameter:@list 标相关设置表中的等级字段集合
     */
    public static int count(List<String> list) throws Exception {
        int count=0;
        for(String str:list){
            for(int i=0;i<=str.length()-1;i++) {
                String getstr=str.substring(i,i+1);
                if(getstr.equals("1")){
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Date: 2017/4/10
     * author: Ywp
     * Description: 标设置中有关等级内容的公共部分
     * Parameter:@uGrades 等级表的List
     * Parameter:@gradeList 标相关设置表中的等级字段集合
     */
    public static ModelAndView mv(List<UserGrade> uGrades, List<String> gradeList, ModelAndView modelAndView,String tid) throws Exception {
        Set<Integer> indexList= new HashSet<Integer>();//用来存放已经设置的等级在字符串中的位置
        modelAndView.addObject("isPart",0);//是否显示全部等级选项，0则显示，1则不显示
        if(gradeList!=null){
            for(String str:gradeList){
                for(int i=0;i<=str.length()-1;i++) {
                    String getstr=str.substring(i,i+1);
                    if(getstr.equals("1")){
                        indexList.add(i);
                    }
                }
            }
        }
        if(indexList.size()>0) {
            for (int i = uGrades.size()-1; i >= 0; i--) {
                if(indexList.contains(i)){
                    uGrades.remove(i);
                }
            }
            modelAndView.addObject("isPart",1);//是否显示全部等级选项，0则显示，1则不显示
        }
        modelAndView.addObject("tid",tid);
        modelAndView.addObject("uGrades",uGrades);   //等级没有设置完，则添加未设置的等级List到modelAndView，再返回
        return modelAndView;
    }

    /**
     * Date: 2017/4/10
     * author: Ywp
     * Description: 抽取标设置中有关等级的公共部分，判断是否为全部等级，并转化为字符串
     * Parameter:@isAll 判断是否全部等级，1代表全部，2代表部分
     * Parameter:@ugrades 存放已设置的等级的数组
     * Parameter:@userGrades 所有等级集合
     */
    public static String changeString(String isAll,String[] ugrades,List<UserGrade> userGrades) throws Exception {
        String gradeStr=null;
        List<String> list=null;
        String[] tempUgrades=null;
        if(ugrades!=null) {
            list=new ArrayList<String> ();
            for (String str : ugrades) {
                if (!str.equals("on")) {
                    list.add(str);
                }
            } //去除全选按钮的值
             tempUgrades = (String[]) list.toArray(new String[list.size()]);
        }
        if(isAll!=null&&!isAll.equals("")){
            if(isAll.equals("1")){//全部等級
                gradeStr=StringUtil.getPlaceholder(userGrades.size());//生成长度为30位的字符串0000000....
                for(UserGrade grade:userGrades){
                    gradeStr=StringUtil.setPlaceholder(gradeStr, grade.getUgrade().intValue());//转换成对应的符串
                }
            }
            if(isAll.equals("2")){//部分等級
                if(ugrades!=null&&ugrades.length>0){
                    gradeStr=StringUtil.setPlaceholderForArr1(tempUgrades, userGrades.size());//转成对应的字符串
                }
            }
        }
        return gradeStr;
    }
}