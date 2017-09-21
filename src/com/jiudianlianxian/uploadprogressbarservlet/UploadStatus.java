package com.jiudianlianxian.uploadprogressbarservlet;


/**
 * 
 * @Title: UploadStatus
 * @Description: �ļ��ϴ�����ͳ����Ϣ
 * @Company: �����ŵ�������Ϣ�������޹�˾
 * @ProjectName: servlet
 * @author fupengpeng
 * @date 2017��9��21�� ����5:10:48
 */
public class UploadStatus {
	//���ϴ��ļ��ֽ���
	private long bytesRead;
	//�ļ��ܳ���
	private long contentLength;
    //�����ϴ����ǵڼ����ļ�
	private int items;
    //�ļ���ʼ�ϴ���ʱ��
	private long startTime = System.currentTimeMillis();

	public long getBytesRead() {
		return bytesRead;
	}

	public void setBytesRead(long bytesRead) {
		this.bytesRead = bytesRead;
	}

	public long getContentLength() {
		return contentLength;
	}

	public void setContentLength(long contentLength) {
		this.contentLength = contentLength;
	}

	public int getItems() {
		return items;
	}

	public void setItems(int items) {
		this.items = items;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

}
