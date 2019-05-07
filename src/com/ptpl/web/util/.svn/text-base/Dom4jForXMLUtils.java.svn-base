package com.ptpl.web.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class Dom4jForXMLUtils {
	//DOM4j解析XML   
    public static HashMap<String,String> parse(String protocolXML) {   
        try {
        	HashMap<String,String> hashMap = new HashMap<String,String>();

            Document doc=(Document)DocumentHelper.parseText(protocolXML);   
            Element books = doc.getRootElement();   
//            System.out.println("根节点"+books.getName());   
//            Iterator users_subElements = books.elementIterator("name");//指定获取那个元素   
            
            Iterator  Elements = books.elementIterator();    
            while(Elements.hasNext()){   
               Element user = (Element)Elements.next();
//             System.out.println("节点"+user.getName()+"\ttext="+user.getText());   
               if(user.getName()!=null && !"".equals(user.getName())){
            	   hashMap.put(user.getName(), user.getText());
               }    
//               List subElements = user.elements("username");//指定获取那个元素 
               List  subElements = user.elements(); 
//	           System.out.println("size=="+subElements.size());

	           for( int i=0;i<subElements.size();i++){   
	             Element ele = (Element)subElements.get(i); 
	             if(ele.getName()!=null && !"".equals(ele.getName())){
	            	 hashMap.put(ele.getName(), ele.getText()); 
	             }
	             List subElemts1 = ele.elements();
	             for( int j=0;j<subElemts1.size();j++){
	            	 Element ele1 = (Element)subElemts1.get(j); 
	            	 if(ele1.getName()!=null && !"".equals(ele1.getName())){
	            		 hashMap.put(ele1.getName(), ele1.getText());
	            	 }
	            	 List subElemts2 = ele1.elements();
	            	 for( int k=0;k<subElemts2.size();k++){
	            		 Element ele2 = (Element)subElemts2.get(k); 
	            		 if(ele2.getName()!=null && !"".equals(ele2.getName())){
	            			 hashMap.put(ele2.getName(), ele2.getText());
	            		 }
//	            		 System.out.println(ele2.getName()+"= "+ele2.getText()+" ");
	            	 }
//	            	 System.out.println(ele1.getName()+"= "+ele1.getText()+" ");
	             }
//	             System.out.println("--"+ele.getName()+"---"+"="+"---"+ele.getText()+"--- ");
	                
	           }   
               //System.out.println();   
           } 
           return hashMap;
        } catch (Exception e) {   
            e.printStackTrace();   
        }
		return null;           
    }   

	public static void main(String[] args) {
		Dom4jForXMLUtils xml = new Dom4jForXMLUtils();
		String tranDataStr = "<?xml  version=\"1.0\" encoding=\"GBK\" standalone=\"no\" ?><B2CRes><interfaceName>ICBC_PERBANK_B2C</interfaceName><interfaceVersion>1.0.0.3</interfaceVersion><orderInfo><orderDate>20070725105014</orderDate><orderid>20070725105014-2134062548</orderid><amount>20</amount><curType>001</curType><merID>0200EC20000875</merID><merAcct>0200020409015029130</merAcct></orderInfo><custom><verifyJoinFlag>0</verifyJoinFlag><JoinFlag></JoinFlag><UserNum></UserNum></custom><bank><TranSerialNo></TranSerialNo><notifyDate>20070725110400</notifyDate><tranStat>1</tranStat><comment>交易成功！</comment></bank></B2CRes>";
		xml.parse(tranDataStr);
		HashMap<String,String> map = xml.parse(tranDataStr);
		System.out.println(map);
		System.out.println(map.get("interfaceVersion"));
	}

}
