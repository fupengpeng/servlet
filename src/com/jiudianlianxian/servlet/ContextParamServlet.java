package com.jiudianlianxian.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @Title: ContextParamServlet
 * @Description: �����Ĳ�����ȡ
 * @Company: �����ŵ�������Ϣ�������޹�˾
 * @ProjectName: servlet
 * @author fupengpeng
 * @date 2017��9��21�� ����1:47:58
 */
@WebServlet("/ContextParamServlet")
public class ContextParamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContextParamServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 
		
		response.setCharacterEncoding("UTF-8");    //���ñ����ʽ
		response.setContentType("text/html");     //�����������
		PrintWriter printWriter = response.getWriter();  //��ȡ���������
		ServletContext servletContext = getServletConfig().getServletContext();   //��ȡServletContext����
		String uploadFolder = servletContext.getInitParameter("upload_folder");    //ʹ��servletContext�����ȡ�����Ĳ���ֵ
	    String url = servletContext.getRealPath(uploadFolder);    //��ȡ����·��
	    System.out.println("�ϴ��ļ���  == " + uploadFolder);
	    System.out.println("ʵ�ʴ���·�� == " + url);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
