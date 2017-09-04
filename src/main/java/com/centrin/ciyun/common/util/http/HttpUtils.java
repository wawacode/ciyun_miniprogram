package com.centrin.ciyun.common.util.http;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;


/**
 * @名称: HttpUtils.java
 * @包名： com.centrin.news
 * @描述: TODO
 * @作者： lwh
 * @日期：2013-6-18 下午02:04:50
 */
public class HttpUtils {
	
	
	private static Log LOG = LogFactory.getLog(HttpUtils.class);
	
	public static final String  DEFAULT_ENCODE="UTF-8";
	
	
	/*@SuppressWarnings("unchecked")
	public static <T,L> HttpResponse<T, L> sendHttpRequest(String httpUrl,String jsonData,Class<T> returnObjectClass,Class<L> returnListClass){
		try{
			jsonData = jsonData==null?"":jsonData;
			String result = sendHttpPostForJson(httpUrl, jsonData, DEFAULT_ENCODE);
			if(StringUtils.isEmpty(result)){
				return new HttpResponse<T,L>(EReturnCode.SYSTEM_BUSY.key,EReturnCode.SYSTEM_BUSY.value,null,null);
			}
			HttpResponse<T,L> response = new HttpResponse<>();
			
			HttpResponse<JSON,JSON> responseObj=  JSONObject.parseObject(result,HttpResponse.class );
			if(responseObj.getData()!=null){
				T data = JSONObject.parseObject(responseObj.getData().toString(),returnObjectClass );
				response.setData(data);
			}
			if(responseObj.getList()!=null){
				List<L> list = JSONObject.parseArray(responseObj.getList().toString(), returnListClass);
				response.setList(list);
			}
			response.setResult(responseObj.getResult());
			response.setMessage(responseObj.getMessage());
			
			return response;
		}catch(Exception e){
			e.printStackTrace();
			return new HttpResponse<T,L>(EReturnCode.SYSTEM_BUSY.key,EReturnCode.SYSTEM_BUSY.value,null,null);
		}
	}*/

	
	
	/**
	 * 匹配对象
	 * @param resultClass
	 * @param httpUrl
	 * @param str
	 * @param encode
	 * @return
	 */
	public static <T> T httpObject(Class<T> resultClass, String httpUrl, JSONObject param, String encode){
		try{
			if(StringUtils.isEmpty(encode)){
				encode = DEFAULT_ENCODE ;
			}
			String result = sendHttpPostForJson(httpUrl, param, encode);
			if(StringUtils.isEmpty(result)){
				return null;
			}
			return  JSONObject.parseObject(result, resultClass);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	/**
	 * 匹配列表
	 * @param resultClass
	 * @param httpUrl
	 * @param str
	 * @param encode
	 * @return
	 */
	public static <T> List<T> httpArray(Class<T> resultClass, String httpUrl, JSONObject param, String encode){
		try{
			if(StringUtils.isEmpty(encode)){
				encode = DEFAULT_ENCODE ;
			}
			String result = sendHttpPostForJson(httpUrl, param, encode);
			if(StringUtils.isEmpty(result)){
				return null;
			}
			return  JSONObject.parseArray(result, resultClass);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * post提交http数据流,固定使用utf-8的编码方式提交
	 * @param httpUrl
	 * @param str
	 * @return
	 */
	public static String sendHttpPost(String httpUrl,String str){
	
		return HttpUtils.sendHttpPost(httpUrl, str, DEFAULT_ENCODE);
		
	}
	
	/**
	 * post提交http数据流
	 * @param httpUrl
	 * @param str
	 * @return
	 */
	public static String sendHttpPost(String httpUrl,String str,String encode){
	
		
			HttpURLConnection conn = null;
			InputStream is = null;
			OutputStream out = null;
			BufferedReader bread = null;
			try {
				URL url = new URL(httpUrl);
				
				//当代理配置参数不为空时，自动设置代理（需要代理的才去配置代理参数）
				Proxy proxy = setProxy();//设置代理
				if(proxy != null){
					LOG.info("设置代理成功："+proxy.address());
					conn = (HttpURLConnection) url.openConnection(proxy);
				}else{
					conn = (HttpURLConnection) url.openConnection();
				}
				
				conn.setRequestMethod("POST");
				conn.setDoInput(true);//允许输入
				conn.setDoOutput(true);//允许输出
				conn.setUseCaches(false);//不许缓存
				int outTime = 5*60*1000;
				//LOG.info("设置超时时间："+outTime);
				//conn.setConnectTimeout(outTime);//链接超时设置
				conn.setReadTimeout(outTime);//一分钟超时
				
				
				
				//需要传递流时，一定要添加的参数，而且ACTION中通过request.getInputStream获取流的情况下，也必须添加该参数
				conn.setRequestProperty("content-type", "text/html");//访问struts2Action时需要设置，否则request得不到inputStream
				conn.setRequestProperty("Charsert", encode);  //访问struts2Action时需要设置，否则request得不到inputStream中文乱码
				//conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + java.util.UUID.randomUUID().toString());    
				
				out = conn.getOutputStream();//得到输出流
				
				//在Action中获流数据乱码时处理方法本地发送方式
				//String aa = URLDecoder.decode(str, "utf-8");
				//out.write(aa.getBytes());//发送数据
				out.write((str==null?"":str).getBytes(encode));//linux发送方式
				out.flush();
				
				is = conn.getInputStream();//得到输入流
				
				bread = new BufferedReader(new InputStreamReader(is, encode));
				
				StringBuilder buff = new StringBuilder();
				String line = null;
				while((line = bread.readLine())!=null){
					buff.append(line);
				}
				
				LOG.info("接收返回数据："+buff.toString());
				return buff.toString();
				
				
			} catch (IOException e) {
				LOG.error("IO异常："+e);
				e.printStackTrace();
			}finally{
				try {
					if(is != null){
						is.close();
					}
					if(out != null){
						out.close();
					}
					if(bread != null){
						bread.close();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		
		return null;
		
	}
	
	public static String sendHttpPostForJson(String httpUrl,JSONObject param,String encode){
		return sendHttpPostForJson(httpUrl,param==null?"": JSON.toJSONString(param,SerializerFeature.WriteMapNullValue),encode);
	}
	/**
	 * post提交http数据流
	 * @param httpUrl
	 * @param param:发送参数
	 * @return
	 */
	public static String sendHttpPostForJson(String httpUrl,String param,String encode){
		HttpURLConnection conn = null;
		InputStream is = null;
		OutputStream out = null;
		BufferedReader bread = null;

        try {  
             //创建连接  
             URL url = new URL(httpUrl); 
           //当代理配置参数不为空时，自动设置代理（需要代理的才去配置代理参数）
			Proxy proxy = setProxy();//设置代理
			if(proxy != null){
				LOG.info("设置代理成功："+proxy.address());
				conn = (HttpURLConnection) url.openConnection(proxy);
			}else{
				conn = (HttpURLConnection) url.openConnection();
			}
               
  
             //设置http连接属性  
               
             conn.setRequestMethod("POST"); // 可以根据需要 提交 GET、POST、DELETE、INPUT等http提供的功能  
             conn.setDoOutput(true);  
             conn.setDoInput(true);  
             conn.setUseCaches(false);  
             conn.setInstanceFollowRedirects(true);  
             
             int outTime = 5*60*1000;
			//LOG.info("设置超时时间："+outTime);
			//conn.setConnectTimeout(outTime);//链接超时设置
			conn.setReadTimeout(outTime);//一分钟超时
               
             //设置http头 消息  
             conn.setRequestProperty("Content-Type","application/json");  //设定 请求格式 json，也可以设定xml格式的  
             conn.setRequestProperty("Accept","application/json");//设定响应的信息的格式为 json，也可以设定xml格式的
             conn.setRequestProperty("charset", encode);
             conn.connect();    
              
             out = conn.getOutputStream();                   
             out.write((param == null ? "" : param).getBytes(DEFAULT_ENCODE));  
             out.flush();  
             out.close();  
   
			is = conn.getInputStream();// 得到输入流
			bread = new BufferedReader(new InputStreamReader(is, encode));
			StringBuilder buff = new StringBuilder();
			String line = null;
			while ((line = bread.readLine()) != null) {
				buff.append(line);
			}
			// 断开连接
			conn.disconnect();
     		return buff.toString();
         } catch (MalformedURLException e) {  
             // TODO Auto-generated catch block  
             e.printStackTrace();  
         } catch (UnsupportedEncodingException e) {  
             // TODO Auto-generated catch block  
             e.printStackTrace();  
         } catch (IOException e) {  
             // TODO Auto-generated catch block  
             e.printStackTrace();  
         }
		return null;  
		
	}
	public static String sendSocketPost(String httpUrl,String str){
		
		Socket socket = null;
		InputStream is = null;
		OutputStreamWriter out = null;
		BufferedReader bread = null;
		BufferedWriter bw = null;
		try {
			
			URL url = new URL(httpUrl);
			
			int port = url.getPort()==-1?80:url.getPort();
			
			//socket = (SSLSocket)((SSLSocketFactory)SSLSocketFactory.getDefault()).createSocket(url.getHost(), port);
			
			//当代理配置参数不为空时，自动设置代理（需要代理的才去配置代理参数）
			//InetAddress.getByName(url.getHost())根据主机名得到IP
			Proxy proxy = setSocketProxy();//设置代理
			if(proxy != null){
				socket = new Socket(proxy);
				SocketAddress socketAddress = new InetSocketAddress(InetAddress.getByName(url.getHost()), port);
				socket.connect(socketAddress);
				LOG.info("设置代理成功："+proxy.address());
				
			}else{
				//不设代理
				socket = new Socket(InetAddress.getByName(url.getHost()), port);
			}
			
			
			out = new OutputStreamWriter(socket.getOutputStream());
			
			bw = new BufferedWriter(out);
			
			bw.write("POST " + url.getPath() + " HTTP/1.1\r\n");  
			bw.write("Host: " + url.getHost() + "\r\n");  
			bw.write("Content-Length: " + str.length() + "\r\n");  
			bw.write("Content-Type: application/x-www-form-urlencoded\r\n");  
			bw.write("\r\n"); 
			bw.write(str);
			bw.write("\r\n"); 
			bw.flush();
			
			is = socket.getInputStream();//得到输入流
			bread = new BufferedReader(new InputStreamReader(is, DEFAULT_ENCODE));
			
			StringBuilder buff = new StringBuilder();
			String line = null;
			while((line = bread.readLine())!=null){
				buff.append(line);
			}
			LOG.info("接收返回数据："+buff.toString());
			
			
			return buff.toString();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(bw != null){
					bw.close();
				}
				if(is != null){
					is.close();
				}
				if(out != null){
					out.close();
				}
				if(bread != null){
					bread.close();
				}
				
				if(socket != null){
					socket.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		
		return null;
		
	} 
	
	/**
	 *  post/get提交http请求
	 * @param httpUrl
	 * @param method(post、get)
	 * @return
	 */
public static InputStream getHttpUrl(String httpUrl,String method){
		HttpURLConnection conn = null;
		InputStream is = null;
		try {
			URL url = new URL(httpUrl);
			
			//当代理配置参数不为空时，自动设置代理（需要代理的才去配置代理参数）
			Proxy proxy = setProxy();//设置代理
			if(proxy != null){
				LOG.info("设置代理成功："+proxy.address());
				conn = (HttpURLConnection) url.openConnection(proxy);
			}else{
				conn = (HttpURLConnection) url.openConnection();
			}
			
			conn.setRequestMethod(method);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setReadTimeout(5*60*1000);
			
			conn.setRequestProperty("content-type", "text/html");
			InputStream nis = conn.getInputStream();//得到输入流
			
			return nis;
		} catch (IOException e) {
			e.printStackTrace();
			LOG.error("IO异常："+e);
		}finally{
			try {
				if(is != null){
					is.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return null;
		
	}
	/**
	 *  post/get提交http请求
	 * @param httpUrl
	 * @param method(post、get)
	 * @return
	 */
	public static String sendHttpUrl(String httpUrl,String method){
		
		
		HttpURLConnection conn = null;
		InputStream is = null;
		BufferedReader bread = null;
		try {
			URL url = new URL(httpUrl);
			
			//当代理配置参数不为空时，自动设置代理（需要代理的才去配置代理参数）
			Proxy proxy = setProxy();//设置代理
			if(proxy != null){
				
				LOG.info("设置代理成功："+proxy.address());
				conn = (HttpURLConnection) url.openConnection(proxy);
			}else{
				conn = (HttpURLConnection) url.openConnection();
			}
			
			
			conn.setRequestMethod(method);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setReadTimeout(5*60*1000);
			
			
			conn.setRequestProperty("content-type", "text/html");
			
			is = conn.getInputStream();//得到输入流
			
			bread = new BufferedReader(new InputStreamReader(is, DEFAULT_ENCODE));
			
			StringBuilder buff = new StringBuilder();
			String line = null;
			while((line = bread.readLine())!=null){
				buff.append(line);
			}
			LOG.info("接收返回数据："+buff.toString());
			return buff.toString();

		} catch (IOException e) {
			e.printStackTrace();
			LOG.error("IO异常："+e);
		}finally{
			try {
				if(is != null){
					is.close();
				}
				
				if(bread != null){
					bread.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	
		return null;
	
	}

	
	/**
	 * 通过http上传文件
	 * @param httpUrl
	 * @param file
	 * @param fileName
	 * @return
	 */
	public static String postFile(String httpUrl,File file,String fileName){
		
		HttpURLConnection conn = null;
		InputStream is = null;
		OutputStream out = null;
		BufferedReader bread = null;
		try {
			
			URL url = new URL(httpUrl);
			
			//当代理配置参数不为空时，自动设置代理（需要代理的才去配置代理参数）
			Proxy proxy = setProxy();//设置代理
			if(proxy != null){
				
				LOG.info("设置代理成功："+proxy.address());
				conn = (HttpURLConnection) url.openConnection(proxy);
			}else{
				conn = (HttpURLConnection) url.openConnection();
			}
			
			
			//发送post请求必须设置
			conn.setDoInput(true);
			conn.setDoOutput(true);
			
			conn.setUseCaches(false);//设置是否使用缓存
			conn.setRequestMethod("POST");
			conn.setReadTimeout(5*60*1000);//请求五分钟超时
			
			//设置请求参数配置
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			conn.setRequestProperty("Charsert", DEFAULT_ENCODE);
			
			String BOUNDARY = "---------7d4a6d158c9"; //定义数据分割线
			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);//上传文件的设置
			
			out = new DataOutputStream(conn.getOutputStream());//数据输出流
			
			byte[] end_data = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();// 定义最后数据分隔线
			
			StringBuilder sb = new StringBuilder();
			sb.append("--");
			sb.append(BOUNDARY);
			sb.append("\r\n");
			sb.append("Content-Disposition: form-data;name=\""+fileName+"\";filename=\""+ file.getName() + "\"\r\n");//要提交form表中的文件
			sb.append("Content-Type:application/octet-stream\r\n\r\n");
			byte[] data = sb.toString().getBytes();
			
			/*************************输出开始线和文件相关参数*************************/
			out.write(data);//输出请求的数据
			
			/*************************输出文件流*************************/
			DataInputStream in = new DataInputStream(new FileInputStream(file));
			
			int bytes = 0;
			byte[] bufferOut = new byte[1024];
			
			while((bytes = in.read(bufferOut)) != -1){//将文件流读入bufferOut中
				out.write(bufferOut,0,bytes);//输出文件流
			}
			
			in.close();
			
			/*************************输出结束线*************************/
			out.write(end_data);
			out.flush();
			//文件输出完成
			
			is = conn.getInputStream();//得到输入流
			bread = new BufferedReader(new InputStreamReader(is, DEFAULT_ENCODE));
			
			StringBuilder buff = new StringBuilder();
			String line = null;
			while((line = bread.readLine())!=null){
				buff.append(line);
			}
			LOG.info("接收返回数据："+buff.toString());
			return buff.toString();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(is != null){
					is.close();
				}
				if(out != null){
					out.close();
				}
				if(bread != null){
					bread.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		
		return null;
		
	} 
	
	public static String socketPostFile(String httpUrl,File file,String fileName){
		
		Socket socket = null;
		InputStream is = null;
		OutputStream out = null;
		BufferedReader bread = null;
		try {
			
			URL url = new URL(httpUrl);
			
			int port = url.getPort()==-1?80:url.getPort();
			
			
			
			
			//当代理配置参数不为空时，自动设置代理（需要代理的才去配置代理参数）
			//InetAddress.getByName(url.getHost())根据主机名得到IP
			Proxy proxy = setSocketProxy();//设置代理
			if(proxy != null){
				socket = new Socket(proxy);
				SocketAddress socketAddress = new InetSocketAddress(InetAddress.getByName(url.getHost()), port);
				socket.connect(socketAddress);
				LOG.info("设置代理成功："+proxy.address());
				
			}else{
				//不设代理
				socket = new Socket(InetAddress.getByName(url.getHost()), port);
			}
			
			String BOUNDARY = "---------7d4a6d158c9"; //定义数据分割线
			
			//得到输出流
			out = socket.getOutputStream();
			
			String reqMethod = "POST "+url.getPath()+" HTTP/1.1/r/n";
			out.write(reqMethod.getBytes());
			
			String accept = "Accept:image/gif, image/jpeg, image/pjpeg, image/jpg, application/x-shockwave-flash\r\n";
			out.write(accept.getBytes());
			
			String language = "Accept-Language: zh-CN\r\n";
			out.write(language.getBytes());
			
			String contentType = "Content-Type: multipart/form-data; boundary=" + BOUNDARY+"\r\n";
			out.write(contentType.getBytes());
			
			String contentLength = "Content-Length:"+file.length()+"\r\n";
			out.write(contentLength.getBytes());
			
			String keepAlive = "connection: Keep-Alive\r\n";
			out.write(keepAlive.getBytes());
			
			String host = "Host:"+url.getHost()+":"+port+"\r\n";
			out.write(host.getBytes());
			
			out.write("\r\n".getBytes());
			
			
			byte[] end_data = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();// 定义最后数据分隔线
			
			StringBuilder sb = new StringBuilder();
			sb.append("--");
			sb.append(BOUNDARY);
			sb.append("\r\n");
			sb.append("Content-Disposition: form-data;name=\""+fileName+"\";filename=\""+ file.getName() + "\"\r\n");//要提交form表中的文件
			sb.append("Content-Type:application/octet-stream\r\n\r\n");
			byte[] data = sb.toString().getBytes();
			
			/*************************输出开始线和文件相关参数*************************/
			out.write(data);//输出请求的数据
			
			/*************************输出文件流*************************/
			DataInputStream in = new DataInputStream(new FileInputStream(file));
			
			int bytes = 0;
			byte[] bufferOut = new byte[1024];
			
			while((bytes = in.read(bufferOut)) != -1){//将文件流读入bufferOut中
				out.write(bufferOut,0,bytes);//输出文件流
			}
			
			in.close();
			
			/*************************输出结束线*************************/
			out.write(end_data);
			out.flush();
			//文件输出完成
			
			is = socket.getInputStream();//得到输入流
			bread = new BufferedReader(new InputStreamReader(is, DEFAULT_ENCODE));
			
			StringBuilder buff = new StringBuilder();
			String line = null;
			while((line = bread.readLine())!=null){
				buff.append(line);
			}
			LOG.info("接收返回数据："+buff.toString());
			
			
			return buff.toString();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(is != null){
					is.close();
				}
				if(out != null){
					out.close();
				}
				if(bread != null){
					bread.close();
				}
				
				if(socket != null){
					socket.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		
		return null;
		
	} 
	
	
	/**
	 * post提交https数据流
	 * @param httpsUrl
	 * @param str
	 * @return
	 */
	public static String sendHttpsPost(String httpsUrl,String str){
		
		HttpsURLConnection conn = null;
		InputStream is = null;
		OutputStream out = null;
		BufferedReader bread = null;
		try {
			URL url = new URL(httpsUrl);
			
			SSLContext sc = SSLContext.getInstance("SSL");
			TrustManager[] tm = {new TrustSSL.TrustAnyTrustManager()};
			SecureRandom random = new SecureRandom();
			sc.init(null, tm,random);

			//当代理配置参数不为空时，自动设置代理（需要代理的才去配置代理参数）
			Proxy proxy = setProxy();//设置代理
			if(proxy != null){
				LOG.info("设置代理成功："+proxy.toString());
				conn = (HttpsURLConnection) url.openConnection(proxy);
			}else{
				conn = (HttpsURLConnection) url.openConnection();
			}
			
			conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setHostnameVerifier(new TrustSSL.TrustAnyHostnameVerifier());
			
			conn.setRequestMethod("POST");
			conn.setDoInput(true);//允许输入
			conn.setDoOutput(true);//允许输出
			conn.setUseCaches(false);//不许缓存
			conn.setReadTimeout(5*60*1000);//一分钟超时
			
			
			//需要传递流时，一定要添加的参数，而且ACTION中通过request.getInputStream获取流的情况下，也必须添加该参数
			conn.setRequestProperty("content-type", "text/html");//访问struts2Action时需要设置，否则request得不到inputStream
			conn.setRequestProperty("Charsert",DEFAULT_ENCODE);  //访问struts2Action时需要设置，否则request得不到inputStream中文乱码  
			
			out = conn.getOutputStream();//得到输出流
			
			//在Action中获流数据乱码时处理方法本地发送方式
			//String aa = URLDecoder.decode(str, "utf-8");
			//out.write(aa.getBytes());//发送数据
			out.write(str.getBytes("utf-8"));//linux发送方式
			out.flush();
			
			is = conn.getInputStream();//得到输入流
			
			bread = new BufferedReader(new InputStreamReader(is,DEFAULT_ENCODE));
			
			StringBuilder buff = new StringBuilder();
			String line = null;
			while((line = bread.readLine())!=null){
				buff.append(line);
			}
			LOG.info("接收返回数据："+buff.toString());
			return buff.toString();
				
		} catch (IOException e) {
			LOG.error("IO异常："+e);
		} catch (NoSuchAlgorithmException e) {
			LOG.error("SSL异常："+e);
		} catch (KeyManagementException e) {
			LOG.error("SSL异常："+e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(is != null){
					is.close();
				}
				if(out != null){
					out.close();
				}
				if(bread != null){
					bread.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	
		return null;
	
	}
	
	/**
	 * post/get提交https请求
	 * @param httsUrl
	 * @param method
	 * @return
	 */
	public static String sendHttpsUrl(String httpsUrl,String method){
		
		HttpsURLConnection conn = null;
		InputStream is = null;
		BufferedReader bread = null;
		try {

			URL url = new URL(httpsUrl);
			
			SSLContext sc = SSLContext.getInstance("SSL");
			TrustManager[] tm = {new TrustSSL.TrustAnyTrustManager()};
			SecureRandom random = new SecureRandom();
			sc.init(null, tm,random);
			
			//当代理配置参数不为空时，自动设置代理（需要代理的才去配置代理参数）
			Proxy proxy = setProxy();//设置代理
			if(proxy != null){
				LOG.info("设置代理成功："+proxy.address());
				conn = (HttpsURLConnection) url.openConnection(proxy);
			}else{
				conn = (HttpsURLConnection) url.openConnection();
			}
			
			conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setHostnameVerifier(new TrustSSL.TrustAnyHostnameVerifier());
			
			conn.setRequestMethod(method);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setReadTimeout(5*60*1000);
			
			conn.setRequestProperty("content-type", "text/html");
			
			
			is = conn.getInputStream();//得到输入流
			
			bread = new BufferedReader(new InputStreamReader(is, DEFAULT_ENCODE));
			
			StringBuilder buff = new StringBuilder();
			String line = null;
			while((line = bread.readLine())!=null){
				buff.append(line);
			}
			
			
			LOG.info("接收返回数据："+buff.toString());
			return buff.toString();
			
		} catch (IOException e) {
			e.printStackTrace();
			LOG.error("IO异常："+e);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			LOG.error("SSL异常："+e);
		} catch (KeyManagementException e) {
			e.printStackTrace();
			LOG.error("SSL异常："+e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				if(is != null){
					is.close();
				}
				if(bread != null){
					bread.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	
		return null;
	
	}
	
	/**
	 * 设置http代理
	 * @param conn
	 */
	private static Proxy setProxy(){
		
		Properties p = new Properties();
		
		InputStream is = HttpUtils.class.getResourceAsStream("/proxy.properties");
		
		if(is != null){
			String host = "";
			String port = "";
			try {
				p.load(is);
				host = p.getProperty("proxyHost");
				port = p.getProperty("proxyPort");
			} catch (IOException e) {
				LOG.error("读取配置参数异常："+e);
			}
			
			if(!StringUtils.isEmpty(host) && !StringUtils.isEmpty(port)){
				Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, Integer.parseInt(port)));  
				
				LOG.info("设置IP代理:"+host);
				
				return proxy;
			}
		}
		
		return null;
		
	}
	
	private static Proxy setSocketProxy(){
		
		Properties p = new Properties();
		
		InputStream is = HttpUtils.class.getResourceAsStream("/proxy.properties");
		
		if(is != null){
			String host = "";
			String port = "";
			try {
				p.load(is);
				host = p.getProperty("proxyHost");
				port = p.getProperty("proxyPort");
			} catch (IOException e) {
				LOG.error("读取配置参数异常："+e);
			}
			
			if(!StringUtils.isEmpty(host) && !StringUtils.isEmpty(port)){
				Proxy proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(host, Integer.parseInt(port)));  
				
				LOG.info("设置IP代理:"+host);
				
				return proxy;
			}
		}
		
		return null;
		
	}
	
	
	/**
	 *  post/get提交http请求下载文件
	 * @param httpUrl
	 * @param method(post、get)
	 * @param newFilePath 文件保存路径和名称 home/aa/bb/cc
	 * @return 返回一个文件下载的后缀
	 */
	public static String downHttpFile(String httpUrl,String newFilePath,String method){
		
		
		HttpURLConnection conn = null;
		InputStream is = null;
		BufferedReader bread = null;
		FileOutputStream os = null;
		try {
			URL url = new URL(httpUrl);
			
			//当代理配置参数不为空时，自动设置代理（需要代理的才去配置代理参数）
			Proxy proxy = setProxy();//设置代理
			if(proxy != null){
				
				LOG.info("设置代理成功："+proxy.address());
				conn = (HttpURLConnection) url.openConnection(proxy);
			}else{
				conn = (HttpURLConnection) url.openConnection();
			}
			
			
			conn.setRequestMethod(method);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setReadTimeout(5*60*1000);
			
			conn.setRequestProperty("content-type", "text/html");
			
			is = conn.getInputStream();//得到输入流
			
			String contentDis = conn.getHeaderField("Content-disposition");
			
			LOG.info("下载文件的表头信息："+conn.getHeaderFields());
			
			if(!StringUtils.isEmpty(contentDis)){
				
				String fileName = contentDis.substring(contentDis.indexOf("=")+2, contentDis.lastIndexOf("\""));//得到文件名称
				int length = conn.getContentLength();//得到文件大小
				
				String ext = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());//得到文件后缀
				
				newFilePath = newFilePath+"."+ext;
				
				File newFile = new File(newFilePath);
				
				if(newFile.exists()){//存放图片文件夹（news_pic）是否存在
					if(!newFile.isDirectory()){//判断文件存在不
						try {
							newFile.createNewFile();//创建文件
						} catch (IOException e) {
							LOG.error("创建文件失败："+e);
						}
					}
				}else{
					File file2 = new File(newFile.getParent());//得到图片文件夹的上一级目录路径
					file2.mkdirs();//创建文件夹
					if(!newFile.isDirectory()){//判断文件存在不
						try {
							newFile.createNewFile();//创建文件
						} catch (IOException e) {
							LOG.error("创建文件失败："+e);
						}
						
					}
					
				}
				
				os = new FileOutputStream(newFile);//图片输出流
				byte[] buff = new byte[length];
				
				int len = 0;
				
				while((len = is.read(buff))>0){
					os.write(buff, 0, len);//将文件流写入到新的文件中
				}
				
				os.flush();
				
				
				LOG.info("接收返回数据："+newFile.getPath());
				return ext;
				
			}else{
				LOG.info("http请求返回非文件流，不能下载文件。");
				
				return null;
			}
		

		} catch (IOException e) {
			e.printStackTrace();
			LOG.error("IO异常："+e);
		}finally{
			try {
				if(is != null){
					is.close();
				}
				
				if(bread != null){
					bread.close();
				}
				
				if(os != null){
					os.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	
		return null;
	
	}
	
	/**
	 *  post/get提交http请求下载文件
	 * @param httpUrl
	 * @param method(post、get)
	 * @param newFilePath 文件保存路径和名称 home/aa/bb/cc
	 * @return 返回一个文件下载的后缀
	 */
	public static String downHttpsFile(String httpsUrl,String newFilePath,String method){
		
		
		HttpsURLConnection conn = null;
		InputStream is = null;
		BufferedReader bread = null;
		FileOutputStream os = null;
		try {
			
			URL url = new URL(httpsUrl);
			
			SSLContext sc = SSLContext.getInstance("SSL");
			TrustManager[] tm = {new TrustSSL.TrustAnyTrustManager()};
			SecureRandom random = new SecureRandom();
			sc.init(null, tm,random);
			
			//当代理配置参数不为空时，自动设置代理（需要代理的才去配置代理参数）
			Proxy proxy = setProxy();//设置代理
			if(proxy != null){
				
				LOG.info("设置代理成功："+proxy.address());
				conn = (HttpsURLConnection) url.openConnection(proxy);
			}else{
				conn = (HttpsURLConnection) url.openConnection();
			}
			
			conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setHostnameVerifier(new TrustSSL.TrustAnyHostnameVerifier());
			
			//LOG.info("系统设置："+System.getProperty("jsse.enableSNIExtension"));
			
			conn.setRequestMethod(method);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setReadTimeout(5*60*1000);
			
			conn.setRequestProperty("content-type", "text/html");
			
			is = conn.getInputStream();//得到输入流
			
			String contentLength = conn.getHeaderField("Content-Length");
			
			String contentDis = conn.getHeaderField("Content-disposition");
			
			LOG.info("下载文件的表头信息："+conn.getHeaderFields());
			
			LOG.info("下载文件大小："+contentLength+"，文件描述："+contentDis);
			
			if(!StringUtils.isEmpty(contentLength) && Integer.parseInt(contentLength)>0){
				
				String ext = "jpg";
				if(!StringUtils.isEmpty(contentDis)){
					String fileName = contentDis.substring(contentDis.indexOf("=")+2, contentDis.lastIndexOf("\""));//得到文件名称
					ext = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());//得到文件后缀
				}
				
				int length = conn.getContentLength();//得到文件大小
				
				
				
				newFilePath = newFilePath+"."+ext;
				
				File newFile = new File(newFilePath);
				
				if(newFile.exists()){//存放图片文件夹（news_pic）是否存在
					if(!newFile.isDirectory()){//判断文件存在不
						try {
							newFile.createNewFile();//创建文件
						} catch (IOException e) {
							LOG.error("创建文件失败："+e);
						}
					}
				}else{
					File file2 = new File(newFile.getParent());//得到图片文件夹的上一级目录路径
					file2.mkdirs();//创建文件夹
					if(!newFile.isDirectory()){//判断文件存在不
						try {
							newFile.createNewFile();//创建文件
						} catch (IOException e) {
							LOG.error("创建文件失败："+e);
						}
						
					}
					
				}
				
				os = new FileOutputStream(newFile);//图片输出流
				byte[] buff = new byte[length];
				
				int len = 0;
				
				while((len = is.read(buff))>0){
					os.write(buff, 0, len);//将文件流写入到新的文件中
				}
				
				os.flush();
				
				
				LOG.info("接收返回数据："+newFile.getPath());
				return ext;
				
			}else{
				LOG.info("http请求返回非文件流，不能下载文件。");
				
				return null;
			}
		

		} catch (IOException e) {
			e.printStackTrace();
			LOG.error("IO异常："+e);
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(is != null){
					is.close();
				}
				
				if(bread != null){
					bread.close();
				}
				
				if(os != null){
					os.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	
		return null;
	
	}
	
	/**
	 * 
	 * @作者： lwh
	 * @日期：2014-7-9 下午02:09:03
	 * @param sendMsgs
	 * @return
	 */
	public static String sendRobotMessage(String message){
		
		StringBuffer bufferRes = new StringBuffer();
		HttpURLConnection conn = null;
		OutputStreamWriter out = null;
		InputStream in = null;
        try {
            URL url = new URL("http://www.niurenqushi.com/app/simsimi/ajax.aspx");
            
          //当代理配置参数不为空时，自动设置代理（需要代理的才去配置代理参数）
			Proxy proxy = setProxy();//设置代理
			if(proxy != null){
				
				LOG.info("设置代理成功："+proxy.address());
				conn = (HttpURLConnection) url.openConnection(proxy);
			}else{
				conn = (HttpURLConnection) url.openConnection();
			}
            
            // 连接超时
            conn.setConnectTimeout(25000);
            // 读取超时 --服务器响应比较慢，增大时间
            conn.setReadTimeout(5*60*1000);

            HttpURLConnection.setFollowRedirects(true);
            // 请求方式
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:21.0) Gecko/20100101 Firefox/21.0");
            conn.setRequestProperty("Accept", "*/*");
            conn.setRequestProperty("Referer","http://www.niurenqushi.com/app/simsimi/");
            conn.connect();
            
            // 获取URLConnection对象对应的输出流
            out = new OutputStreamWriter(conn.getOutputStream());
            // 发送请求参数
            out.write("txt=" + URLEncoder.encode(message, "UTF-8"));
            out.flush();
      
            
            in = conn.getInputStream();
            BufferedReader read = new BufferedReader(new InputStreamReader(in,"UTF-8"));
            String valueString = null;
            while ((valueString = read.readLine()) != null) {
                bufferRes.append(valueString);
            }
           
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
        	try {
	        	if(conn != null){
	        		conn.disconnect();
	        	}
	        	if(out != null){
					out.close();
	        	}
	        	if(in != null){
	        		in.close();
	        	}
        	} catch (IOException e) {
				e.printStackTrace();
			}
        }
        
        
       return HttpUtils.removeNews(bufferRes.toString()) ;
		
	}
	
	/**
	 * 
	 * @作者： lwh
	 * @日期：2014-7-9 下午02:09:03
	 * @param sendMsgs
	 * @return
	 */
	private static String removeNews(String sendMsgs) {
        // 去除广告
        if (sendMsgs.indexOf("simsimi2") != -1) {
            sendMsgs = "偶是毛小驴，女，还木有男友，欢迎帅哥美女调戏   O(∩_∩)O";
        } else if (sendMsgs.indexOf("Database") != -1 || sendMsgs.indexOf("Failed") != -1) {
            int random = (int) (Math.random() * 5);
            switch (random) {
            case 1:
                sendMsgs = "嗯";
                break;
            case 2:
                sendMsgs = "聊天其它的吧";
                break;
            case 3:
                sendMsgs = "嗯哼";
                break;
            case 4:
                sendMsgs = "哎呀";
                break;
            case 5:
                sendMsgs = "额";
                break;
            default:
                sendMsgs = "嗯";
                break;
            }
        } else if (sendMsgs.indexOf("Unauthorized access") != -1) {
            sendMsgs = "我怎么听不懂你说的什么意思呀[大哭]。咱们能换个话题吗！";
        } else if (sendMsgs.indexOf("你可以教我回答") != -1) {
            sendMsgs = "好吧";
        }else if(sendMsgs.contains("糗百")){
        	sendMsgs = "哈喽，帅哥美女。";
        }
        // 替换部分内容
        sendMsgs = sendMsgs.replaceAll("傻逼", "sb");
        sendMsgs = sendMsgs.replaceAll("小九", "毛小驴");
        // sendMsgs = sendMsgs.replaceAll("小豆", "小贱贱");
        sendMsgs = sendMsgs .replaceAll(
                        "小豆机器人网页版地址：http://xiao.douqq.com QQ个性网http://www.xiugexing.com",
                        "伦家不懂官人的话了啦~");
        sendMsgs = sendMsgs.replaceAll("小豆", "毛小驴");
        sendMsgs = sendMsgs.replaceAll("人家", "伦家");
        sendMsgs = sendMsgs.replaceAll("林晨爱你QQ个性网http://www.xiugexing.com","伦家不懂官人的话了啦~");
        return sendMsgs;
    }
	
	
	/**
	 * 异步发送http请求
	 * @作者： lwh
	 * @日期：2015-9-17 上午09:34:15
	 * @param httpUrl
	 * @param str 发送数据流需要
	 * @param flag true:发送数据流，false:请求httpUrl不输出数据流
	 * @param method 发送httpurl请求的时候需要，GET/POST
	 */
	public static void sendAsynhttp(String httpUrl,String str,boolean flag,String method){
		
		SendThread st = new SendThread(httpUrl, str, flag, method);
		st.start();
	}
	
	
	/**
	 * 异步发送请求
	 */
	public static class SendThread extends Thread{
		
		private String httpUrl;
		private String str;
		private boolean flag;
		private String method;
		
		public SendThread(String httpUrl,String str,boolean flag,String method){
			this.httpUrl = httpUrl;
			this.str = str;
			this.flag = flag;
			this.method = method;
		}
		
		/**
		 * 详细描述该方法的作用
		 * @作者： lwh
		 * @日期：2015-9-17 上午09:20:52
		 */
		@Override
		public void run() {
			
			String result = "";
			if(flag){
				if(httpUrl.contains("https://")){
					result = HttpUtils.sendHttpsPost(httpUrl, str.toString());
				}else{
					result = HttpUtils.sendHttpPost(httpUrl, str.toString());
				}
			}else{
				if(httpUrl.contains("https://")){
					result = HttpUtils.sendHttpsUrl(httpUrl, method);
				}else{
					result = HttpUtils.sendHttpUrl(httpUrl, method);
				}
			}
			
			LOG.info("异步发送请求返回结果："+result);
		}
		
	}
	
	public static void main(String[] args) {
		
		//HttpUtils.downHttpFile("http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=uG_6I2x8wJNwtCREbHjH-BMU0btl-SIIspeiFzV0dJtdwdTKak7-tHJ5G5xxxSJLhyAziJqR_sj4Hfl189pPK5QIH378Cd48-05yG-avpJqtSyF_yOWr44LUX6G_THlilgAwbT4FjslINtrR4LsVCQ&media_id=lo9URhhch9Usmirm0B4Oe2wh8zKAY425ZpHMjH85XrTV60aIcSX5ufp6i0lxbcyn");
//		HttpUtils.downHttpFile("http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=PthDpKoQg0q2LQdf4kBFFYGANemCUqg-iUbCPJA49cQPPGMKK80NB9J_rNAiiQcpJdfuxLtQ-fY6uLuxL7op9CdsBydkeKj6g74MAwxjVEY&media_id=QD5It3BCqfSGyNjQ0jpJFNeAHgVtAlLwfQ-ckAdr8bJZhZ5WcgdfzHvHYuAYC7d4", "D:/temp/aa", "GET");
		//InputStream is = HttpUtils.getHttpUrl("http://10.1.203.14:8087/group1/M00/22/98/CgHLDljlubSAEWtaAACAACSztR8184.xls", "GET");
		//System.out.println(null == is);
		Map<String,String> hashMap = new HashMap<>();
		hashMap.put("1", "");
		hashMap.put("2", null);
		hashMap.put("3", "xxxx");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("avc","xxx");
		jsonObject.put("map", hashMap);
		String string =jsonObject.toString();
		System.out.println(string);
		
		String string1 =JSON.toJSONString(jsonObject,SerializerFeature.WriteMapNullValue);
		System.out.println(string1);
	}
	
	
}
