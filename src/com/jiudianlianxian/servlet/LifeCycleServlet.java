package com.jiudianlianxian.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @Title: LifeCycleServlet
 * @Description: Servlet��������Ӧ��
 * @Company: �����ŵ�������Ϣ�������޹�˾
 * @ProjectName: servlet
 * @author fupengpeng
 * @date 2017��9��22�� ����8:27:57
 */
//@WebServlet("/LifeCycleServlet")
public class LifeCycleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static double startPoint = 0;  //��˰�����㣬���ڽ��ճ�ʼ��Servletʱ�Ĳ���startPoint
	
	/*
	 * 1.��һ�η��ʴ�Servletʱ������init��������ȡ��Servlet�ĳ�ʼ������
	 * 2.����service������ִ������߼�����
	 * 3.����doget��������������˰��̬ҳ��
	 * 4.�ھ�̬ҳ�����빤�ʶ�����˰�������service�������ٵ���dopost����չʾ������
	 * 
	 */
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LifeCycleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    public void init() throws ServletException {
    	//
    	this.log("ִ�� init���� ����������");
    	ServletConfig config = this.getServletConfig();
    	startPoint = Double.parseDouble(config.getInitParameter("startPoint"));
    	
    }
    @Override
    protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
    	// 
    	this.log("ִ�� service���� ����������");
    	
    	super.service(arg0, arg1);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 
		this.log("ִ�� doGet() ���� ... ");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<link rel='stylesheet' type='text/css' href='../css/style.css'>");
		out.println("<HTML><HEAD><TITLE>��������˰����</TITLE></HEAD>");

		out.println("<div align='center'><br/><fieldset style=width:90%><legend>��˰������</legend><br/>");
		out.println("<form method='post' action='LifeCycleServlet'>");
		
		out.println("<div style='line'>");
		out.println("	<div class='leftDiv'>���Ĺ���Ϊ</div><div align='left' class='rightDiv'><input type='text' name='income'> ��λ��Ԫ</div>");
		out.println("</div><br/>");
		
		out.println("<div style='line'>");
		out.println("	<div class='leftDiv'></div><div align='left' class='rightDiv'><input type='submit' value='  �����˰  ' class=button></div>");
		out.println("</div>");
		
		out.println("</form>");
		
		out.println("<BODY>");
		out.println("</BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 
		this.log("ִ�� doPost() ���� ... ");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML><HEAD><TITLE>��������˰����</TITLE></HEAD>");
		out.println("<link rel='stylesheet' type='text/css' href='../css/style.css'>");
		out.println("<BODY>");
		
		out.println("<div align='center'><br/><fieldset style=width:90%><legend>��˰������</legend><br/>");
		
		try{
			// �Ӳ����л�ȡ�Ĺ�����Ŀ
			double income = new Double(request.getParameter("income"));
			// Ӧ��˰����
			double charge = income - startPoint;
			// ��˰
			double tax = 0;

			if (charge<=0) {tax=0;}
			if (charge>0&&charge<=500) {tax=charge*0.05;}
			if (charge>500&&charge<=2000) {tax=charge*0.1-25;}
			if (charge>2000&&charge<=5000) {tax=charge*0.15-125;}
			if (charge>5000&&charge<=20000) {tax=charge*0.2-375;}
			if (charge>20000&&charge<=40000) {tax=charge*0.25-1375;}
			if (charge>40000&&charge<=60000) {tax=charge*0.30-3375;}
			if (charge>60000&&charge<=80000) {tax=charge*0.35-6375;}
			if (charge>80000&&charge<=100000) {tax=charge*0.4-10375;}
			if (charge>100000) {tax=charge*0.45-15375;}

			out.println("<div style='line'>");
			out.println("	<div class='leftDiv'>���Ĺ���Ϊ</div><div class='rightDiv'>" + income + " Ԫ</div>");
			out.println("</div>");
			
			out.println("<div style='line'>");
			out.println("	<div class='leftDiv'>��Ӧ��˰</div><div class='rightDiv'>" + tax + " Ԫ</div>");
			out.println("</div><br/>");

			out.println("<input type='button' onclick='history.go(-1);' value='��˰���� ��˰�ɳ� ����'  class=button>");
			
		}catch(Exception e){
			out.println("��������ֵ�������ݡ�<input type='button' onclick='history.go(-1);' value='����'  class=button>");
		}
		out.println("</BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}
	@Override
	public void destroy() {
		// 
		this.log("ִ�� destroy() ���� ... ");
		startPoint = 0;
	}
	

}
