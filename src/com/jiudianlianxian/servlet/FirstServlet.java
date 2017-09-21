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
 * @Description: ��һ��Servlet��ͨ��HttpServletRequest��HttpServletResponse�����ȡ����ʱ����Ӧʱ�Ĳ�����ȡ������
 * @Company: �����ŵ�������Ϣ�������޹�˾
 * @ProjectName: servlet
 * @author fupengpeng
 * @date 2017��9��20�� ����9:33:58
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
		
		
		request.setCharacterEncoding("UTF-8");  //�����������ݶ�ȡ�ı����ʽ
		response.setCharacterEncoding("UTF-8");   //������Ӧ���ݵı����ʽ
		String requestURI = request.getRequestURI();  //��ȡ�����Servlet��uri
		String requestMethod = request.getMethod();   //��ȡ�����Servlet�ķ�ʽ
		String parameter = request.getParameter("parameter");   //��ȡ����Ĳ�����Ϣ
		response.setContentType("text/html");   //������Ӧ�󷵻ص���������
		
		String paramter = this.getInitParameter("chushihuacanshuming");  //���ݳ�ʼ����������ȡ����ֵ
		
		
		
		
		
		AsyncContext asyncContext = request.getAsyncContext();
		Object object = request.getAttribute("paramter");
		Enumeration<String> enumeration = request.getAttributeNames();
		String authType = request.getAuthType();
		int contentLength = request.getContentLength();
		String contextPath = request.getContextPath();  //��������URI��һ����,��ʾ�����������
		Cookie[] cookie = request.getCookies();    //����һ������,���а������е�Cookie����Ŀͻ��˷�������
		long dateHeader = request.getDateHeader("parameter");
		String header = request.getHeader("qingqiutou");  //����ָ��������ͷ��ֵΪ�ַ�����
		DispatcherType dispatcherType = request.getDispatcherType();
		try {
			InputStream inputStream = request.getInputStream();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpSession session = request.getSession();  //���������صĵ�ǰ�Ự����,�������û��һ���Ự������,����һ����
		Object obj = session.getAttribute("jian");    //���ذ�������Ự��ָ�����ƵĶ���,���û�а󶨶���������򷵻�null��
		String string = session.getId();    //��ȡ��������session��Ψһidֵ
		session.removeAttribute("jian");  //ɾ���󶨴�����Ựdeָ�����ƵĶ���
		session.setAttribute("jian", "value");    //��session����ָ���ļ�ֵ��
		
		ServletContext servletContext = session.getServletContext();  //���ش˻Ự����ServletContext��
		FilterRegistration filterRegistration = servletContext.addFilter("filtername", "�̳�Filter����������� ");
		servletContext.addListener("�̳�EventListener�����������������");
		ServletRegistration servletRegistration = servletContext.addServlet("servletname", "servlet���������");
		Object object2 = servletContext.getAttribute("jian");    //����jian��ȡjian��Ӧ��ֵ
		servletContext.setAttribute("jian", "value");    //��servletContext���ò���ֵ
		/*
		 * servletContext.createFilter(��������);    //����һ����������
		 * servletContext.createListener(��������);    //����һ����������
		 * servletContext.createServlet(Servlet��);    //����һ��Servlet��
		 */
		
		
		response.addCookie(cookie[0]);    //��ָ��cookie��ӵ���Ӧ��
		response.addDateHeader("haha", 20150115);    //�ø��������ƺ�����ֵ���һ����Ӧͷ��
		response.addHeader("as", "value");    //�ø��������ƺ�ֵ���һ����Ӧͷ��
		response.getStatus();    //��ȡ��ǰ״̬����ķ�Ӧ��
		response.setStatus(102);     //����Ӧ����״̬��
		try {
			response.sendError(102);    //����һ��������Ӧ���ͻ���  ʹ��ָ����״̬����,����ջ�����
			response.sendRedirect("zhuanxiangde   servlet lu  jing");    //����һ����ʱ�ض�����Ӧ���ͻ���ʹ��ָ����λ���ض���URL����ջ�������
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

		// ���ʸ� Servlet �� URI
		// ���ʸ� Servlet �ķ�ʽ���� GET ���� POST
		String method = request.getMethod();
		// �ͻ����ύ�Ĳ��� param ֵ
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
		out.println("	�� " + method + " ��ʽ���ʸ�ҳ�档ȡ���� param ����Ϊ��" + param
				+ "<br/>");

		out.println("	<form action='"
				+ requestURI
				+ "' method='get'><input type='text' name='param' value=''><input type='submit' value='�� GET ��ʽ���� RequestServlet'></form>");
		out.println("	<form action='"
				+ requestURI
				+ "' method='post'><input type='text' name='param' value=''><input type='submit' value='�� POST ��ʽ���� RequestServlet'></form>");

		// �ɿͻ����������ȡ���ĵ��ĸ���ʱ��
		out.println("	<script>document.write('��ҳ��������ʱ�䣺' + document.lastModified + '<br />'); </script>");
		out.println("	<script>document.write('��ҳ��URL��' + location + '<br/>' ); </script>");

		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();

	
		
	}
	
	

}
