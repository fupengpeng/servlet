package com.jiudianlianxian.uploadprogressbarservlet;

import org.apache.commons.fileupload.ProgressListener;


/**
 * 
 * @Title: UploadListener
 * @Description: �ļ��ϴ����ȼ�����
 * @Company: �����ŵ�������Ϣ�������޹�˾
 * @ProjectName: servlet
 * @author fupengpeng
 * @date 2017��9��21�� ����5:10:43
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
