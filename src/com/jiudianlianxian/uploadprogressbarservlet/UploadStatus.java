package com.jiudianlianxian.uploadprogressbarservlet;


/**
 * 
 * @Title: UploadStatus
 * @Description: 文件上传进度统计信息
 * @Company: 济宁九点连线信息技术有限公司
 * @ProjectName: servlet
 * @author fupengpeng
 * @date 2017年9月21日 下午5:10:48
 */
public class UploadStatus {
	//已上传文件字节数
	private long bytesRead;
	//文件总长度
	private long contentLength;
    //正在上传的是第几个文件
	private int items;
    //文件开始上传的时间
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
