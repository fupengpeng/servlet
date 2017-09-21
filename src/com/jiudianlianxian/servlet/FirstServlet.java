package com.jiudianlianxian.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.javafx.collections.SetAdapterChange;
import com.sun.jmx.snmp.Enumerated;
import com.sun.xml.internal.bind.v2.model.core.Adapter;

import sun.util.logging.resources.logging;


/**
 * 
 * @Title: FirstServlet
 * @Description: 第一个Servlet，通过HttpServletRequest、HttpServletResponse对象获取请求时和响应时的参数获取和设置
 * @Company: 济宁九点连线信息技术有限公司
 * @ProjectName: servlet
 * @author fupengpeng
 * @date 2017年9月20日 上午9:33:58
 */
public class FirstServlet extends HttpServlet {
	
	PrintWriter out;
	
	private static final long serialVersionUID = 2386052823761867369L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.execute(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.execute(request, response);
	}

	@Override
	protected long getLastModified(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return super.getLastModified(request);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}
	
	private void execute(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		
		
		request.setCharacterEncoding("UTF-8");  //设置请求数据读取的编码格式
		response.setCharacterEncoding("UTF-8");   //设置响应数据的编码格式
		String requestURI = request.getRequestURI();  //获取请求此Servlet的uri
		String requestMethod = request.getMethod();   //获取请求次Servlet的方式
		String parameter = request.getParameter("parameter");   //获取请求的参数信息
		response.setContentType("text/html");   //设置响应后返回的数据类型
		
		String paramter = this.getInitParameter("chushihuacanshuming");  //根据初始化参数名获取参数值
		
		
		
		
		
		AsyncContext asyncContext = request.getAsyncContext();
		Object object = request.getAttribute("paramter");
		Enumeration<String> enumeration = request.getAttributeNames();
		String authType = request.getAuthType();
		int contentLength = request.getContentLength();
		String contextPath = request.getContextPath();  //返回请求URI的一部分,表示请求的上下文
		Cookie[] cookie = request.getCookies();    //返回一个数组,其中包含所有的Cookie对象的客户端发送请求。
		long dateHeader = request.getDateHeader("parameter");
		String header = request.getHeader("qingqiutou");  //返回指定的请求头的值为字符串。
		DispatcherType dispatcherType = request.getDispatcherType();
		try {
			InputStream inputStream = request.getInputStream();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpSession session = request.getSession();  //返回与此相关的当前会话请求,或者如果没有一个会话的请求,创建一个。
		Object obj = session.getAttribute("jian");    //返回绑定在这个会话上指定名称的对象,如果没有绑定对象的名字则返回null。
		String string = session.getId();    //获取分配给这个session的唯一id值
		session.removeAttribute("jian");  //删除绑定从这个会话de指定名称的对象
		session.setAttribute("jian", "value");    //给session设置指定的键值对
		
		ServletContext servletContext = session.getServletContext();  //返回此会话所属ServletContext。
		FilterRegistration filterRegistration = servletContext.addFilter("filtername", "继承Filter的类或者类名 ");
		servletContext.addListener("继承EventListener监听器的类或者类名");
		ServletRegistration servletRegistration = servletContext.addServlet("servletname", "servlet类或者类名");
		Object object2 = servletContext.getAttribute("jian");    //根据jian获取jian对应的值
		servletContext.setAttribute("jian", "value");    //给servletContext设置参数值
		/*
		 * servletContext.createFilter(过滤器类);    //创建一个过滤器类
		 * servletContext.createListener(监听器类);    //创建一个监听器类
		 * servletContext.createServlet(Servlet类);    //创建一个Servlet类
		 */
		
		
		response.addCookie(cookie[0]);    //将指定cookie添加到响应。
		response.addDateHeader("haha", 20150115);    //用给定的名称和日期值添加一个响应头。
		response.addHeader("as", "value");    //用给定的名称和值添加一个响应头。
		response.getStatus();    //获取当前状态代码的反应。
		response.setStatus(102);     //给响应设置状态码
		try {
			response.sendError(102);    //发送一个错误响应给客户端  使用指定的状态代码,并清空缓冲区
			response.sendRedirect("zhuanxiangde   servlet lu  jing");    //发送一个临时重定向响应给客户端使用指定的位置重定向URL和清空缓冲区。
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			OutputStream outputStream = response.getOutputStream();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		// 访问该 Servlet 的 URI
		// 访问该 Servlet 的方式，是 GET 还是 POST
		String method = request.getMethod();
		// 客户端提交的参数 param 值
		String param = request.getParameter("param");

		response.setContentType("text/html");
		
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.println("	以 " + method + " 方式访问该页面。取到的 param 参数为：" + param
				+ "<br/>");

		out.println("	<form action='"
				+ requestURI
				+ "' method='get'><input type='text' name='param' value=''><input type='submit' value='以 GET 方式访问 RequestServlet'></form>");
		out.println("	<form action='"
				+ requestURI
				+ "' method='post'><input type='text' name='param' value=''><input type='submit' value='以 POST 方式访问 RequestServlet'></form>");

		// 由客户端浏览器读取该文档的更新时间
		out.println("	<script>document.write('本页面最后更新时间：' + document.lastModified + '<br />'); </script>");
		out.println("	<script>document.write('本页面URL：' + location + '<br/>' ); </script>");

		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();

	
		
	}
	
	

}
