package com.jiudianlianxian.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @Title: RedirectServlet
 * @Description: Servlet重定向
 * @Company: 济宁九点连线信息技术有限公司
 * @ProjectName: servlet
 * @author fupengpeng
 * @date 2017年9月22日 上午10:06:06
 */
@WebServlet("/RedirectServlet")
public class RedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Map<String, Integer> map = new HashMap<String,Integer>();
	@Override
	public void init() throws ServletException {
		// 
	map.put("/download/setup.exe", 0);
	map.put("/download/application.zip", 0);
	map.put("/download/01.mp3", 0);
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RedirectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//
		String filename = request.getParameter("filename");
		if (filename != null ) {
			int hit = map.get(filename);
			response.sendRedirect(request.getContextPath() + filename);
		}else {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("  <HEAD><TITLE>文件下载</TITLE></HEAD>");
			out.println("	<link rel='stylesheet' type='text/css' href='../css/style.css'>");
			out.println("  <BODY><br/>");

			out.println("<fieldset align=center style=width:90%><legend>文件下载</legend>");
			out.println("<table width=100%>");
			out.println("	<tr>");
			out.println("		<td><b>文件名</b></td>");
			out.println("		<td><b>下载次数</b></td>");
			out.println("		<td><b>下载</b></td>");
			out.println("	</tr>");
			
			for(Entry<String, Integer> entry : map.entrySet()){
				out.println("<tr>");
				out.println("	<td>" + entry.getKey() + "</td>");
				out.println("	<td>" + entry.getValue() + "</td>");
				out.println("	<td><a href='" + request.getRequestURI() + "?filename=" + entry.getKey() + "' target='_blank' onclick='location=location; '>下载</a></td>");
				out.println("</tr>");
			}
			
			out.println("</table>");
			out.println("	</legend>");
			out.println("  </BODY>");
			out.println("</HTML>");
			out.flush();
			out.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	@Override
	public void destroy() {
		// 
		map = null;
		
	}

}
