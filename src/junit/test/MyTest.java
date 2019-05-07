package junit.test;

import com.ptpl.controller.BaseController;
import com.ptpl.web.util.Arith;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/4/2.
 */
public class MyTest extends BaseController {
    public static void main(String[] args) throws UnsupportedEncodingException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(new Date()));
        System.out.println(new Double(7)*100);
        double a=7.77;
        double interest = Arith.mul(a, 100);
        System.out.println(Arith.div(a,100));

       /* ArrayList<TenderFrontEnd> tenderFrontEnds = new ArrayList<>();
        TenderFrontEnd tenderFrontEnd = new TenderFrontEnd();
        tenderFrontEnd.setContent("content1");
        tenderFrontEnd.setInfoname("name1");
        TenderFrontEnd tenderFrontEnd2 = new TenderFrontEnd();
        tenderFrontEnd2.setContent("content2");
        tenderFrontEnd2.setInfoname("name2");
        tenderFrontEnds.add(tenderFrontEnd);
        tenderFrontEnds.add(tenderFrontEnd2);
        String s = JSON.toJSONString(tenderFrontEnds);
        System.out.println(s);*/
       /* BigDecimal bigDecimal = new BigDecimal(10000000000.0225).setScale(2, BigDecimal.ROUND_HALF_UP);
        double v = bigDecimal.doubleValue();
        System.out.println(bigDecimal+"ddd");//10000000000.02ddd
        System.out.println(new DecimalFormat("#,###.00").format(100000000000.0));//10,000,000,000.02

        System.out.println(v);//1.000000000002E10



        System.out.println(Arith.round(10000000.23,2));//过千万级就也是用科学计数法表示了
        System.out.println(10000000.235);*/
//        System.out.println(Arith.mul(0.14001,100));
//        System.out.println(new BigDecimal(0.14*100).setScale(2,BigDecimal.ROUND_HALF_UP)+"%");
//        System.out.println(AES.getDecrypt("6bf8be7232c6ccc830519d81b4d52ead"));
//        int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9,};
//        String[] array = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
//        Random rand = new Random();
//        for (int i = 36; i > 1; i--) {
//            int index = rand.nextInt(i);
//            String tmp = array[index];
//            array[index] = array[i - 1];
//            array[i - 1] = tmp;
//            array.toString();
//        }
//        String result = "";
//        for (int i = 0; i < 6; i++) {
//            result += array[i];
//        }
//        System.out.println(result);
//        public_XuanZe();
//        quLing(String.valueOf(100.010));
//        System.out.println(dd((short)10));
//        seriesno();
//        Apple apple1 = new Apple("小A", (short) 1);
//        Apple apple2 = new Apple("小B", (short) 2);
//        Apple apple3 = new Apple("小C", (short) 3);
//        Apple apple4 = new Apple("小D", (short) 4);
//        Apple apple5 = new Apple("小E", (short) 5);
//        Apple apple6 = new Apple("小F", (short) 6);
//        Apple apple7 = new Apple("小G", (short) 7);
//
//        ArrayList<Apple> apples = new ArrayList<>();
//
//        apples.add(apple1);
//        apples.add(apple2);
//        apples.add(apple3);
//        apples.add(apple4);
//        apples.add(apple5);
//        apples.add(apple6);
//        apples.add(apple7);
//        int getindexof = getindexof(apples, apple6);
//        System.out.println(getindexof);
//        for (Apple a : apples) {
//            System.out.print(a + "   ");
//        }
//        System.out.println();
//
//
//        Apple xxx = new Apple("小XXX", (short) 3);
//        short nowNo = xxx.getC();
//        for (int i = nowNo; i < apples.size(); i++) {
//            Apple aa = apples.get(nowNo);
//            aa.setC((short) (aa.getC() + 1));
//        }

//        //向后改
//        if (c1_befor < c1_after) {
//            for (int i = c1_befor; i < (c1_after < apples.size() ? c1_after : apples.size()); i++) {
//                Apple aa = apples.get(i);
//                aa.setC((short) (aa.getC() - 1));
//            }
//        }
//        System.out.println(apples);

    }

//    private static void seriesno() {
//        Apple apple1 = new Apple("小A", (short) 1);
//        Apple apple2 = new Apple("小B", (short) 2);
//        Apple apple3 = new Apple("小C", (short) 3);
//        Apple apple4 = new Apple("小D", (short) 4);
//        Apple apple5 = new Apple("小E", (short) 5);
//        Apple apple6 = new Apple("小F", (short) 6);
//        Apple apple7 = new Apple("小G", (short) 7);
//
//        ArrayList<Apple> apples = new ArrayList<>();
//        apples.add(apple1);
//        apples.add(apple2);
//        apples.add(apple3);
//        apples.add(apple4);
//        apples.add(apple5);
//        apples.add(apple6);
//        apples.add(apple7);
//        for (Apple a : apples) {
//            System.out.print(a + "   ");
//        }
//        System.out.println();
//
//
//        //找到修改前的
//         Apple b;
//         for(Apple a:apples){
//            if(a.getC()==4){
//                b=a;
//                break;
//            }
//        }
//        short c1_befor = b.getC();
//        applebefor.setC((short) 100);
//        //改后
//
//        short c1_after = applebefor.getC();
//        if(){
//
//        }
//        //向前改
//        if (c1_befor > c1_after) {
//            for (int i = c1_after; i < c1_befor; i++) {
//                Apple aa = apples.get(i - 1);
//                aa.setC((short) (aa.getC() + 1));
//            }
//        }
//        //向后改
//        if (c1_befor < c1_after) {
//            for (int i = c1_befor; i < (c1_after < apples.size() ? c1_after : apples.size()); i++) {
//                Apple aa = apples.get(i);
//                aa.setC((short) (aa.getC() - 1));
//            }
//        }
//        System.out.println(apples);
//    }

    public static void aa() {
        HashMap<String, String> map = new HashMap<>();
        map.put("public_XuanZe", "AA");
        map.put("public_XuanZe", "BB");
        System.out.println(map);
    }

    public static void bb() {
        Integer integer = new Integer(3);
        integer *= integer * 3;
        System.out.println(integer.toString());
    }

    public static String quLing(String s) {
        if (s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }

    public static String dd(short tstatus) {
        String tstatusStr = "";
        switch (tstatus) {
            case 3:
                tstatusStr = "此标在投标中fff";
                break;
            case 4:
                tstatusStr = "此标在还款中";
                break;
            case 5:
                tstatusStr = "已满标";
                break;
            case 7:
                tstatusStr = "此标已流标";
                break;
            case 9:
                tstatusStr = "此标已过期";
                break;
            case 10:
                tstatusStr = "此标还款已完成";
                break;
        }
        return tstatusStr;
    }

    public static int getindexof(List list, Object obj) {
        int indexof = list.indexOf(obj);
        return indexof;
    }

}

