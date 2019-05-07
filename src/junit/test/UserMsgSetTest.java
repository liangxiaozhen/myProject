package junit.test;

import com.ptpl.constant.UserMsgSet_Constant;
import com.ptpl.mapper.UserMsgSetMapper;
import com.ptpl.model.UserMsgSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * Date: 2017/3/13
 * author: Ywp
 * Description: 测试用户消息通知设置
 * Parameter:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext-dao.xml"})
public class UserMsgSetTest {
        @Autowired
        private UserMsgSetMapper userMsgSetMapper;

        @Test
        public  void testInsert(){

           UserMsgSet user=new UserMsgSet();
            user.setMsgtype((short) 10);
            user.setItem("流标");
          int count=  userMsgSetMapper.insertSelective(user);
            if(count>0){
                System.out.println("成功插入！");
            }
        }
        @Test
        public  void testDelete(){
            int count=  userMsgSetMapper.deleteByPrimaryKey(new BigDecimal(2));
            if(count>0){
                System.out.println("成功删除！");
            }
        }

        @Test
        public  void testSelectBySelective(){
            UserMsgSet userMsgSet=new UserMsgSet();
            userMsgSet.setBaseid(new BigDecimal(217));
            userMsgSet.setId(new BigDecimal(131));
            userMsgSet.setMsgtype((short) 1);
            List<UserMsgSet> list=  userMsgSetMapper.selectBySelective(userMsgSet);
           for(UserMsgSet user:list){
               System.out.println(user.toString());
            }
        }
        @Test
        public  void testMsgConstant(){
            System.out.println(UserMsgSet_Constant.ITEM_MAP);
        }

        @Test
        public  void testDeleteByMsgType(){
            int count=  userMsgSetMapper.deleteByMsgType((short) 3);
            if(count>0){
                System.out.println("成功删除！");
            }
        }

}
