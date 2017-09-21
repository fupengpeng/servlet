package com.jiudianlianxian.uploadprogressbarservlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;



/**
 * 
 * @Title: ProgressUploadServlet
 * @Description: 带进度条的文件上传
 * @Company: 济宁九点连线信息技术有限公司
 * @ProjectName: servlet
 * @author fupengpeng
 * @date 2017年9月21日 下午5:10:37
 */
public class ProgressUploadServlet extends HttpServlet {

	private static final long serialVersionUID = -4935921396709035718L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 上传状态
		UploadStatus status = new UploadStatus();

		// 监听器，初始化的时候就得到默认上传进度情况
		UploadListener listener = new UploadListener(status);

		// 把 UploadStatus 放到 session 里
		request.getSession(true).setAttribute("uploadStatus", status);

		// Apache 上传工具
		ServletFileUpload upload = new ServletFileUpload(
				new DiskFileItemFactory());

		// 给上传工具设置 listener，监听上传进度
		upload.setProgressListener(listener);

		try {
			//通过Request对象获取上传的文件集合
			List itemList = upload.parseRequest(request);
			
			//遍历上传文件集合
			for (Iterator it = itemList.iterator(); it.hasNext();) {
				FileItem item = (FileItem) it.next();
				//判断是文件还是文件说明
				if (item.isFormField()) {  // 文件说明
					System.out.println("FormField: " + item.getFieldName()
							+ " = " + item.getString());
				} else {    //文件
					
					//源文件路径
					System.out.println("File: " + item.getName());

					// 统一 Linux 与 windows 的路径分隔符
					String fileName = item.getName().replace("/", "\\");
					fileName = fileName.substring(fileName.lastIndexOf("\\"));
					
					//设置文件保存路径
					File saved = new File("G:\\upload_test", fileName);
					saved.getParentFile().mkdirs();
					System.out.println("");
					
					//文件保存
					InputStream ins = item.getInputStream();
					OutputStream ous = new FileOutputStream(saved);

					byte[] tmp = new byte[1024];
					int len = -1;

					while ((len = ins.read(tmp)) != -1) {
						ous.write(tmp, 0, len);
					}

					ous.close();
					ins.close();
					//文件保存成功
					response.getWriter().println("已保存文件：" + saved);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().println("上传发生错误：" + e.getMessage());
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setHeader("Cache-Control", "no-store");    //禁止浏览器缓存
		response.setHeader("Pragrma", "no-cache");    //禁止浏览器缓存
		response.setDateHeader("Expires", 0);    //禁止浏览器缓存

		//获取文件上传状态
		UploadStatus status = (UploadStatus) request.getSession(true)
				.getAttribute("uploadStatus");

		//根据上传状态判断是否有文件上传
		if (status == null) {
			response.getWriter().println("没有上传信息");
			return;
		}
		//获取文件上传的开始时间
		long startTime = status.getStartTime();
		//当前时间
		long currentTime = System.currentTimeMillis();

		// 已传输的时间 单位：s
		long time = (currentTime - startTime) / 1000 + 1;

		// 传输速度 单位：byte/s
		double velocity = ((double) status.getBytesRead()) / (double) time;

		// 估计总时间 单位：s
		double totalTime = status.getContentLength() / velocity;

		// 估计剩余时间 单位：s
		double timeLeft = totalTime - time;

		// 已完成的百分比
		int percent = (int) (100 * (double) status.getBytesRead() / (double) status
				.getContentLength());

		// 已完成数 单位：M
		double length = ((double) status.getBytesRead()) / 1024 / 1024;

		// 总长度 单位：M
		double totalLength = ((double) status.getContentLength()) / 1024 / 1024;

		// 格式：百分比||已完成数(M)||文件总长度(M)||传输速率(K)||已用时间(s)||估计总时间(s)||估计剩余时间(s)||正在上传第几个文件
		String value = percent + "||" + length + "||" + totalLength + "||"
				+ velocity + "||" + time + "||" + totalTime + "||" + timeLeft
				+ "||" + status.getItems();

		response.getWriter().println(value);
	}

}
