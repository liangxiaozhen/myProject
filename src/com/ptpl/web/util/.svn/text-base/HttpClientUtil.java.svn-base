package com.ptpl.web.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.huifu.util.httpClient.HttpClientHandler;


public class HttpClientUtil
{
	private static final CloseableHttpClient httpClient;
	public static final String CHARSET = "utf-8";
	private static PoolingHttpClientConnectionManager cm = null;
	public static Log logger = LogFactory.getLog(HttpClientUtil.class);
	
	static
	{
		RequestConfig config = RequestConfig.custom().setConnectTimeout(20000).setSocketTimeout(10000).build();
		
		cm = new PoolingHttpClientConnectionManager();
		// 将最大连接数增加到200
		cm.setMaxTotal(50);
		// 将每个路由基础的连接增加到20
		cm.setDefaultMaxPerRoute(5);
		
		// X509TrustManager x509mgr = new X509TrustManager() {
		// public void checkClientTrusted(X509Certificate[] xcs, String string) {
		// }
		// public void checkServerTrusted(X509Certificate[] xcs, String string) {
		// }
		// public X509Certificate[] getAcceptedIssuers() {
		// return null;
		// }
		// };
		//        
		// // 这个好像是HOST验证
		// X509HostnameVerifier hostnameVerifier = new X509HostnameVerifier()
		// {
		// public boolean verify(String host, SSLSession ssl)
		// {
		// return true;
		// }
		// public void verify(String host, SSLSocket ssl) throws IOException {}
		// public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {}
		// public void verify(String host, X509Certificate cert) throws SSLException {}
		// };
		//	    
		// SSLContext sslContext = null;
		// try
		// {
		// sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy()
		// {
		// // 信任所有
		// public boolean isTrusted(X509Certificate[] chain,
		// String authType) throws CertificateException
		// {
		// return true;
		// }
		// }).build();
		// }
		// catch (KeyManagementException e)
		// {
		// e.printStackTrace();
		// }
		// catch (NoSuchAlgorithmException e)
		// {
		// e.printStackTrace();
		// }
		// catch (KeyStoreException e)
		// {
		// e.printStackTrace();
		// }
		//		
		// SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		
		// SSLContext sslContext = null;
		// try
		// {
		// sslContext = SSLContext.getInstance("SSL");
		// }
		// catch (NoSuchAlgorithmException e)
		// {
		// e.printStackTrace();
		// }
		// try
		// {
		// sslContext.init(null, new TrustManager[]
		// { x509mgr }, null);
		// }
		// catch (KeyManagementException e)
		// {
		// e.printStackTrace();
		// }
		// SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		
		httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).setConnectionManager(cm).build();
	}
	
	public static String[] doGetQueryCmd(String url, Map<String, String> params)
	{
		return doGet(url, params, CHARSET);
	}
	
	public static String[] doPostQueryCmd(String url, Map<String, String> params)
	{
		String[] resultarr = doPost(url, params, CHARSET, 0);
		
		if (resultarr == null || resultarr.length < 2 || StringUtils.isBlank(resultarr[0]) || !resultarr[0].equals("200"))
		{
			resultarr = doPost(url, params, CHARSET, 1);
		}
		if (resultarr == null || resultarr.length < 2)
		{
			resultarr = new String[2];
		}
		return resultarr;
	}
	
	@SuppressWarnings("unchecked")
	public static String[] doPostQueryCmd(String url, Map<String, String> params, Map<String, Object> loanparams)
	{
		String[] resultarr = new String[2];
		
		if(url.contains("www.126.com"))
		{
			resultarr[0] = "200";
			resultarr[1] = "SUCCESS";
			return resultarr;
		}
		
		
		int returntype = 0;
		if (loanparams.containsKey("loanService") && loanparams.get("loanService") != null && loanparams.containsKey("mlai") && loanparams.get("mlai") != null)
		{
			
		}
		
		resultarr = doPost(url, params, CHARSET, 0);
		
		if (resultarr == null || resultarr.length < 2 || StringUtils.isBlank(resultarr[0]) || !resultarr[0].equals("200"))
		{
			resultarr = doPost(url, params, CHARSET, 1);
		}
		if (resultarr == null || resultarr.length < 2)
		{
			resultarr = new String[2];
		}
		
		String resultstr = resultarr[1];
		if (StringUtils.isNotBlank(resultstr))
		{
			resultstr = resultstr.replaceAll("\\n", "");
			
			if (resultstr.startsWith("<html><head></head><script type=\"text/javascript\">var sa = \"http://202.102.110.207:8080/\";"))
			{
				resultarr[0] = "404";
			}
		}
		
		if (loanparams.containsKey("loanService") && loanparams.get("loanService") != null && loanparams.containsKey("mlai") && loanparams.get("mlai") != null)
		{
			// if (resultarr == null || resultarr.length < 2 || StringUtils.isBlank(resultarr[0]) || !resultarr[0].equals("200"))
			// {
			// 错误次数+1
			
			// }
		}
		
		return resultarr;
	}
	@SuppressWarnings("unchecked")
	public static String doPostQueryCmd(String url, String xmlData) throws Exception{
		
//		HttpClient httpclient = new HttpClient();
//		byte[] xmlDataB = null;
//		if(xmlData != null && !xmlData.equals("")){
//			xmlDataB = xmlData.getBytes("utf-8");
//		}
//		PostMethod post = new PostMethod(url);
//		post.setRequestEntity(new ByteArrayRequestEntity(xmlDataB));
//		post.setRequestHeader("Content-type", "application/x-NS-BDES");
//		post.setRequestHeader("Accept", "text/xml; *.*");
//		int result = httpclient.executeMethod(post);
//		try
//        {
//            int result1 = httpclient.executeMethod(post);
//            BufferedReader reader=new BufferedReader(new InputStreamReader( post.getResponseBodyAsStream(),"utf-8"));
//            //InputStream body =;
//            //String bod = post.getResponseCharSet();
//            String tmp=null;
//            String htmlRet="";
//           while((tmp=reader.readLine())!=null){
//        	   htmlRet+=tmp+"\r\n";
//          }
//           //System.out.println(new String(htmlRet.getBytes("gbk"),"utf-8"));
//           System.out.println(htmlRet);
//
//            
//            //byte[] body1 = body.getBytes("GB2312");
//            //String s1 = new String(body1,"utf-8");
//            post.releaseConnection();
//            System.out.println(result1);
//            //System.out.println(s1);
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//		return null;
		HttpPost httpPost = null;
		BufferedReader reader = null;
		try {
	    httpPost = new HttpPost(url);
		StringEntity myEntity = new StringEntity(xmlData,"UTF-8");
		httpPost.addHeader("Content-Type","text/xml; charset=UTF-8");
		httpPost.addHeader("Content-type", "application/x-NS-BDES");
		httpPost.addHeader("Accept", "text/xml; *.*");
		httpPost.setEntity(myEntity);
		HttpResponse response = httpClient.execute(httpPost);
		
		HttpEntity resEntity = response.getEntity();
		if (resEntity != null) {
		reader = new BufferedReader(new InputStreamReader(resEntity.getContent(),"GB2312"));
		StringBuffer sb = new StringBuffer();
		String line = null;
		
		while ((line = reader.readLine()) != null) {
		sb.append(line);
		sb.append("\r\n");
		}
		return sb.toString();
		}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		if (httpPost != null) {
			httpPost.abort();
		}
		if (reader != null) {
		try {
		reader.close();
		} catch (IOException e) {
		e.printStackTrace();
		}
		}
		}
		return null;
	}
	
	/**
	 * HTTP Get 获取内容
	 * 
	 * @param url
	 *            请求的url地址 ?之前的地址
	 * @param params
	 *            请求的参数
	 * @param charset
	 *            编码格式
	 * @return 页面内容
	 */
	public static String[] doGet(String url, Map<String, String> params, String charset)
	{
		// if (StringUtils.isBlank(url))
		// {
		// return null;
		// }
		// try
		// {
		// if (params != null && !params.isEmpty())
		// {
		// List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
		// for (Map.Entry<String, String> entry : params.entrySet())
		// {
		// String value = entry.getValue();
		// if (value != null)
		// {
		// pairs.add(new BasicNameValuePair(entry.getKey(), value));
		// }
		// }
		// url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, charset));
		// }
		// HttpGet httpGet = new HttpGet(url);
		// CloseableHttpResponse response = httpClient.execute(httpGet);
		// int statusCode = response.getStatusLine().getStatusCode();
		// if (statusCode != 200)
		// {
		// httpGet.abort();
		// throw new RuntimeException("HttpClient,error status code :" + statusCode);
		// }
		// HttpEntity entity = response.getEntity();
		// String result = null;
		// if (entity != null)
		// {
		// result = EntityUtils.toString(entity, "utf-8");
		// }
		// EntityUtils.consume(entity);
		// response.close();
		// return result;
		// }
		// catch (Exception e)
		// {
		// e.printStackTrace();
		// }
		return null;
	}
	
	/**
	 * HTTP Post 获取内容
	 * 
	 * @param url
	 *            请求的url地址 ?之前的地址
	 * @param params
	 *            请求的参数
	 * @param charset
	 *            编码格式
	 * @return 页面内容
	 */
	public static String[] doPost(String strURL, Map<String, String> params, String charset, int proxy)
	{
		String returncode = null;
		String result = null;
		String[] resultarr = new String[2];
		
		if (StringUtils.isBlank(strURL))
		{
			return resultarr;
		}
		
		try
		{
			int check = 1;
			// if (strURL.contains("www.moye.com"))
			// {
			// strURL = strURL.replaceAll("www.moye.com", "192.168.2.81");
			// check = 0;
			// }
			
			if (check == 1)
			{
				if (StringUtils.isBlank(strURL) || (!strURL.startsWith("http://") && !strURL.startsWith("https://")))
				{
					return resultarr;
				}
				String str = strURL;
				String[] strarr = str.split("/");
				if (strarr == null || strarr.length < 3)
				{
					return resultarr;
				}
				
				if (strarr[2] == null)
				{
					return resultarr;
				}
				
				Pattern pattern = Pattern.compile("^((10(\\.\\d{1,3}){3})|(127(\\.\\d{1,3}){3})|(172\\.16(\\.\\d{1,3}){2})|(172\\.32(\\.\\d{1,3}){2})|(192\\.168\\.(([0-1]|[3-9])|\\d{2,3})(\\.\\d{1,3}))|localhost).*$");
				Matcher matcher = pattern.matcher(strarr[2]);
				if (matcher.find())
				{
					return resultarr;
				}
			}
			
			List<NameValuePair> pairs = null;
			if (params != null && !params.isEmpty())
			{
				pairs = new ArrayList<NameValuePair>(params.size());
				for (Map.Entry<String, String> entry : params.entrySet())
				{
					String value = entry.getValue();
					if (value != null)
					{
						pairs.add(new BasicNameValuePair(entry.getKey(), value));
					}
				}
			}
			
//			//创建TrustManager() 
//	        //用于解决javax.net.ssl.SSLPeerUnverifiedException: peer not authenticated 
//	        X509TrustManager trustManager = new X509TrustManager(){
//
//				@Override
//				public void checkClientTrusted(X509Certificate[] chain,
//						String authType) throws CertificateException {
//					// TODO Auto-generated method stub
//					
//				}
//
//				@Override
//				public void checkServerTrusted(X509Certificate[] chain,
//						String authType) throws CertificateException {
//					// TODO Auto-generated method stub
//					
//				}
//
//				@Override
//				public X509Certificate[] getAcceptedIssuers() {
//					// TODO Auto-generated method stub
//					return null;
//				} 
//	          
//	        }; 
//	        
//	      //创建HostnameVerifier 
//	        //用于解决javax.net.ssl.SSLException: hostname in certificate didn't match: <123.125.97.66> != <123.125.97.241> 
//	        X509HostnameVerifier hostnameVerifier = new X509HostnameVerifier(){
//
//				@Override
//				public boolean verify(String arg0, SSLSession arg1) {
//					// TODO Auto-generated method stub
//					return false;
//				}
//
//				@Override
//				public void verify(String arg0, SSLSocket arg1)
//						throws IOException {
//					// TODO Auto-generated method stub
//					
//				}
//
//				@Override
//				public void verify(String arg0, X509Certificate arg1)
//						throws SSLException {
//					// TODO Auto-generated method stub
//					
//				}
//
//				@Override
//				public void verify(String arg0, String[] arg1, String[] arg2)
//						throws SSLException {
//					// TODO Auto-generated method stub
//					
//				} 
// 
//	        }; 
//	        
//	       //TLS1.0与SSL3.0基本上没有太大的差别,可粗略理解为TLS是SSL的继承者，但它们使用的是相同的SSLContext 
//            SSLContext sslContext = SSLContext.getInstance(SSLSocketFactory.TLS); 
//            //使用TrustManager来初始化该上下文,TrustManager只是被SSL的Socket所使用 
//            sslContext.init(null, new TrustManager[]{trustManager}, null); 
//            //创建SSLSocketFactory 
//            SSLSocketFactory socketFactory = new SSLSocketFactory(sslContext, hostnameVerifier); 
//            //通过SchemeRegistry将SSLSocketFactory注册到HttpClient上 
//            httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 443, socketFactory)); 
	        
			
			HttpPost httpPost = new HttpPost(strURL);
			httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");//x-www-form-urlencoded
			
			httpPost.addHeader("Accept", "text/html");
			httpPost.addHeader("Accept-Charset", "utf-8");
			httpPost.addHeader("Accept-Encoding", "gzip");
			httpPost.addHeader("Accept-Language", "en-US,en");
			httpPost.addHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/25.0.1364.160 Safari/537.22");
			
			if (pairs != null && pairs.size() > 0)
			{
				httpPost.setEntity(new UrlEncodedFormEntity(pairs, CHARSET));
			}
			
			if (proxy == 1)
			{
				// 依次是代理地址，代理端口号，协议类型
				HttpHost httpproxy = new HttpHost("112.126.80.125", 9513, "http");
				RequestConfig config = RequestConfig.custom().setProxy(httpproxy).build();
				httpPost.setConfig(config);
			}
			
			// 将目标主机的最大连接数增加到50
			// HttpRoute hr = new HttpRoute();
			// cm.setMaxPerRoute();
			
			CloseableHttpResponse response = httpClient.execute(httpPost);
			try
			{
				int ireturncode = response.getStatusLine().getStatusCode();
				returncode = Integer.toString(ireturncode);
				// if (statusCode != 200)
				// {
				// httpPost.abort();
				// throw new RuntimeException("HttpClient,error status code :" + statusCode);
				// }
				HttpEntity entity = response.getEntity();
				if (entity != null)
				{
					result = EntityUtils.toString(entity, "utf-8");
				}
				EntityUtils.consume(entity);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				response.close();
				// httpClient.close();
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		if (result == null)
		{
			result = "";
		}
		
		resultarr[0] = returncode;
		resultarr[1] = result;
		
		return resultarr;
	}
	
    //如果关注性能问题可以考虑使用HttpClientConnectionManager
    public String doPost(Map<String, String> params) throws ClientProtocolException, IOException {
        String result = null;
        List<NameValuePair> nvps = HttpClientHandler.buildNameValuePair(params);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        EntityBuilder builder = EntityBuilder.create();
        try {
            HttpPost httpPost = new HttpPost(HttpClientHandler.HTTP_HOST);
            builder.setParameters(nvps);
            httpPost.setEntity(builder.build());
            CloseableHttpResponse response = httpclient.execute(httpPost);

            try {
                HttpEntity entity = response.getEntity();
                if (response.getStatusLine().getReasonPhrase().equals("OK")
                    && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    result = EntityUtils.toString(entity, "UTF-8");
                }
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
        return result;
    }

}
