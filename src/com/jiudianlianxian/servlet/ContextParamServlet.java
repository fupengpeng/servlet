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
 * @Description: 上下文参数获取
 * @Company: 济宁九点连线信息技术有限公司
 * @ProjectName: servlet
 * @author fupengpeng
 * @date 2017年9月21日 下午1:47:58
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
		
		response.setCharacterEncoding("UTF-8");    //设置编码格式
		response.setContentType("text/html");     //设置输出类型
		PrintWriter printWriter = response.getWriter();  //获取输出流对象
		ServletContext servletContext = getServletConfig().getServletContext();   //获取ServletContext对象
		String uploadFolder = servletContext.getInitParameter("upload_folder");    //使用servletContext对象获取上下文参数值
	    String url = servletContext.getRealPath(uploadFolder);    //获取磁盘路径
	    System.out.println("上传文件夹  == " + uploadFolder);
	    System.out.println("实际磁盘路径 == " + url);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
