package com.jiudianlianxian.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @Title: ForwardServlet
 * @Description: Servlet转向
 * @Company: 济宁九点连线信息技术有限公司
 * @ProjectName: servlet
 * @author fupengpeng
 * @date 2017年9月22日 上午9:08:17
 */
@WebServlet("/ForwardServlet")
public class ForwardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ForwardServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		//
		String destination = request.getParameter("destination");
		if ("file".equals(destination)) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/web.xml");
			requestDispatcher.forward(request, response);
		} else if ("jsp".equals(destination)) {
			request.setAttribute("date", new Date());
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/forward.jsp");
			requestDispatcher.forward(request, response);
		} else if ("servlet".equals(destination)) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/servlet/LifeCycleServlet");
			requestDispatcher.forward(request, response);
		} else {
			response.setCharacterEncoding("UTF-8");
			response.getWriter().println("缺少参数，用法 ： " + request.getRequestURL() + "?destination=jsp或者file或者serlvet");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
