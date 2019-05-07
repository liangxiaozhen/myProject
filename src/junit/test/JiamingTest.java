package junit.test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 
* @ClassName: JiamingTest 
* @Package junit.test 
* @Description: TODO(测试类) 
* @author cjm
* @date 2017年4月19日 上午9:44:06 
* @version V1.0
 */
public class JiamingTest {

	private static final int TED = 2;
	public static  void main(String[] args){
		System.out.println(UUID.randomUUID().toString());
//		List<Double> list = new CopyOnWriteArrayList<>();
//		ExecutorService executorService = Executors.newFixedThreadPool(TED);
//		executorService.execute(new AddThread(list));
//		executorService.execute(new AddThread(list));
//		
//		executorService.shutdown();
 	}
}

class AddThread implements Runnable{

	private List<Double> list;
	
	public AddThread(List<Double> list){
		  this.list = list;
	}
	
	@Override
	public void run() {
 		for(int i = 0;i < 10000;i++){
 			list.add(Math.random());
 		}
	}
	
}
