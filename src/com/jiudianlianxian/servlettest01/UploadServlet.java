package com.jiudianlianxian.servlettest01;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


/**
 * 
 * @Title: UploadServlet
 * @Description: 上传文件
 * @Company: 济宁九点连线信息技术有限公司
 * @ProjectName: servlet
 * @author fupengpeng
 * @date 2017年9月21日 下午4:48:46
 */
public class UploadServlet extends HttpServlet {
	/** * Constructor of the object. */
	public UploadServlet() {
		super();
	}

	/**
	 * * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy();
	}

	// Just puts "destroy" string in log
	// Put your code here }
	/**
	 * * The doGet method of the servlet. <br>
	 * * * This method is called when a form has its tag value method equals to
	 * get. * * @param request the request send by the client to the server
	 * * @param response the response send by the server to the client * @throws
	 * ServletException if an error occurred * @throws IOException if an error
	 * occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * * The doPost method of the servlet. <br>
	 * * * This method is called when a form has its tag value method equals to
	 * post. * * @param request the request send by the client to the server
	 * * @param response the response send by the server to the client * @throws
	 * ServletException if an error occurred * @throws IOException if an error
	 * occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		
		DiskFileItemFactory sf = new DiskFileItemFactory();    // 实例化磁盘被文件列表工厂
		String path = request.getRealPath("/upload");    // 得到上传文件的存放目录
		System.out.println("img = " + request.getParameter("img"));
		System.out.println("name = " + request.getParameter("name"));
		System.out.println("url = " + request.getRequestURI());
		System.out.println("path = " + path);
		sf.setRepository(new File(path));    // 设置文件存放目录
		sf.setSizeThreshold(1024 * 1024);    // 设置文件上传小于1M放在内存中
		String rename = "";    // 文件新生成的文件名
		String fileName = "";    // 文件原名称
		String name = "";    // 普通field字段
		// 从工厂得到servletupload文件上传类
		ServletFileUpload sfu = new ServletFileUpload(sf);
		try {
			List<FileItem> lst = sfu.parseRequest(request);
			// 得到request中所有的元素
			for (FileItem fileItem : lst) {
				if (fileItem.isFormField()) {
					if ("name".equals(fileItem.getFieldName())) {
						name = fileItem.getString("UTF-8");
					}
				} else {
					// 获得文件名称
					fileName = fileItem.getName();
					fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
					String houzhui = fileName.substring(fileName.lastIndexOf("."));
					rename = UUID.randomUUID() + houzhui;
					fileItem.write(new File(path, rename));
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("普通字段 == " + name);
		System.out.println("文件名称 == " + fileName);
		System.out.println("修改后生成的文件名称 == " + rename);
		response.sendRedirect("ok.jsp");
		out.flush();
		out.close();
	}

	/**
	 * * Initialization of the servlet. <br>
	 * * * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
