package com.jiudianlianxian.uploadprogressbarservlet;

import org.apache.commons.fileupload.ProgressListener;


/**
 * 
 * @Title: UploadListener
 * @Description: 文件上传进度监听器
 * @Company: 济宁九点连线信息技术有限公司
 * @ProjectName: servlet
 * @author fupengpeng
 * @date 2017年9月21日 下午5:10:43
 */
public class UploadListener implements ProgressListener {

	private UploadStatus status;

	public UploadListener(UploadStatus status) {
		this.status = status;
	}

	public void update(long bytesRead, long contentLength, int items) {
		status.setBytesRead(bytesRead);
		status.setContentLength(contentLength);
		status.setItems(items);
	}
}
