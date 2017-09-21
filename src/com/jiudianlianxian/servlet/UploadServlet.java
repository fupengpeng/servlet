package com.jiudianlianxian.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;


/**
 * 
 * @Title: UploadServlet
 * @Description: �ϴ��ļ�
 * @Company: �����ŵ�������Ϣ�������޹�˾
 * @ProjectName: servlet
 * @author fupengpeng
 * @date 2017��9��21�� ����3:07:13
 */
public class UploadServlet extends HttpServlet {

	private static final long serialVersionUID = 7523024737218332088L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.getWriter().println("���� POST ��ʽ�ϴ��ļ�");
	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		File file1 = null, file2 = null;
		String str1 = null, str2 = null;
		String osString = "G:/os";

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <link rel='stylesheet' type='text/css' href='../css/style.css'>");
		out.println("  <BODY>");
		
		out.println("<div align=center><br/>");
		out.println("<fieldset style='width:90%'><legend>�ϴ��ļ�</legend><br/>");
		
		out.println("		<div class='line'>");
		out.println("			<div align='left' class='leftDiv'>�ϴ���־��</div>");
		out.println("			<div align='left' class='rightDiv'>");
		
		// ʹ�� DiskFileUpload ������� request
		DiskFileUpload diskFileUpload = new DiskFileUpload();
		try {
			// �� �����Ľ�� ������ List ��
			List<FileItem> list = diskFileUpload.parseRequest(request);
			out.println("�������е� FileItem ... <br/>");
			// ���� list �����е� FileItem
			for(FileItem fileItem : list){
				if(fileItem.isFormField()){
					// ����� �ı���
					if("str1".equals(fileItem.getFieldName())){
						// ����� FileItem ����Ϊ str1
						out.println("������ str1 ... <br/>");
						str1 = new String(fileItem.getString().getBytes(), "UTF-8");
					}
					if("str2".equals(fileItem.getFieldName())){
						// ����� FileItem ����Ϊ str2
						out.println("������ str2 ... <br/>");
						str2 = new String(fileItem.getString().getBytes(), "UTF-8");
					}
				}
				else{
					// ����Ϊ�ļ���
					if("file1".equals(fileItem.getFieldName())){
						// �ͻ����ļ�·�������� File ����
						File remoteFile = new File(new String(fileItem.getName().getBytes(), "UTF-8"));
						out.println("������ file1 ... <br/>");
						out.println("�ͻ����ļ�λ��: " + remoteFile.getAbsolutePath() + "<br/>");   //���������ļ���ǰ���ھ���·��
						// ���������ļ������� upload �ļ�����
						
//						file1 = new File(this.getServletContext().getRealPath("attachment"), remoteFile.getName());
						file1 = new File(osString,remoteFile.getName());
						file1.getParentFile().mkdirs();
						file1.createNewFile();
						
						// д�ļ����� FileItem ���ļ�����д���ļ���
						InputStream ins = fileItem.getInputStream();
						OutputStream ous = new FileOutputStream(file1);
						
						try{
							byte[] buffer = new byte[1024]; 
							int len = 0;
							while((len=ins.read(buffer)) > -1)
								ous.write(buffer, 0, len);
							out.println("�ѱ����ļ�" + file1.getAbsolutePath() + "<br/>");  //�ϴ����ļ�������ľ���·��
						}finally{
							ous.close();
							ins.close();
						}
					}
					if("file2".equals(fileItem.getFieldName())){
						// �ͻ����ļ�·�������� File ����
						File remoteFile = new File(new String(fileItem.getName().getBytes(), "UTF-8"));
						out.println("������ file2 ... <br/>");
						out.println("�ͻ����ļ�λ��: " + remoteFile.getAbsolutePath() + "<br/>");
						// ���������ļ������� upload �ļ�����
						file2 = new File(this.getServletContext().getRealPath("attachment"), remoteFile.getName());
						file2.getParentFile().mkdirs();
						file2.createNewFile();
						
						// д�ļ����� FileItem ���ļ�����д���ļ���
						InputStream ins = fileItem.getInputStream();
						OutputStream ous = new FileOutputStream(file2);
						
						try{
							byte[] buffer = new byte[1024]; 
							int len = 0;
							while((len=ins.read(buffer)) > -1)
								ous.write(buffer, 0, len);
							out.println("�ѱ����ļ�" + file2.getAbsolutePath() + "<br/>");
						}finally{
							ous.close();
							ins.close();
						}
					}
				}
			}
			out.println("Request �������");
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		out.println("			</div>");
		out.println("		</div>");
		
		if(file1 != null){
		out.println("		<div class='line'>");
		out.println("			<div align='left' class='leftDiv'>file1��</div>");
		out.println("			<div align='left' class='rightDiv'>");
		out.println("				<a href='" + request.getContextPath() + "/attachment/" + file1.getName() + "' target=_blank>" + file1.getName() +  "</a>"	);
		out.println("			</div>");
		out.println("		</div>");
		}

		if(file2 != null){
		out.println("		<div class='line'>");
		out.println("			<div align='left' class='leftDiv'>file2��</div>");
		out.println("			<div align='left' class='rightDiv'>");
		out.println("				<a href='" + request.getContextPath() + "/attachment/" + URLEncoder.encode(file2.getName(), "UTF-8") + "' target=_blank>" + file2.getName() +  "</a>"	);
		out.println("			</div>");
		out.println("		</div>");
		}
		

		out.println("		<div class='line'>");
		out.println("			<div align='left' class='leftDiv'>str1��</div>");
		out.println("			<div align='left' class='rightDiv'>");
		out.println(str1);
		out.println("			</div>");
		out.println("		</div>");

		out.println("		<div class='line'>");
		out.println("			<div align='left' class='leftDiv'>str2��</div>");
		out.println("			<div align='left' class='rightDiv'>");
		out.println(str2);
		out.println("			</div>");
		out.println("		</div>");
		
		out.println("</fieldset></div>");
		
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

}
