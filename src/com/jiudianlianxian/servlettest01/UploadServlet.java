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
 * @Description: �ϴ��ļ�
 * @Company: �����ŵ�������Ϣ�������޹�˾
 * @ProjectName: servlet
 * @author fupengpeng
 * @date 2017��9��21�� ����4:48:46
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
		
		
		DiskFileItemFactory sf = new DiskFileItemFactory();    // ʵ�������̱��ļ��б���
		String path = request.getRealPath("/upload");    // �õ��ϴ��ļ��Ĵ��Ŀ¼
		System.out.println("img = " + request.getParameter("img"));
		System.out.println("name = " + request.getParameter("name"));
		System.out.println("url = " + request.getRequestURI());
		System.out.println("path = " + path);
		sf.setRepository(new File(path));    // �����ļ����Ŀ¼
		sf.setSizeThreshold(1024 * 1024);    // �����ļ��ϴ�С��1M�����ڴ���
		String rename = "";    // �ļ������ɵ��ļ���
		String fileName = "";    // �ļ�ԭ����
		String name = "";    // ��ͨfield�ֶ�
		// �ӹ����õ�servletupload�ļ��ϴ���
		ServletFileUpload sfu = new ServletFileUpload(sf);
		try {
			List<FileItem> lst = sfu.parseRequest(request);
			// �õ�request�����е�Ԫ��
			for (FileItem fileItem : lst) {
				if (fileItem.isFormField()) {
					if ("name".equals(fileItem.getFieldName())) {
						name = fileItem.getString("UTF-8");
					}
				} else {
					// ����ļ�����
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
		System.out.println("��ͨ�ֶ� == " + name);
		System.out.println("�ļ����� == " + fileName);
		System.out.println("�޸ĺ����ɵ��ļ����� == " + rename);
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
