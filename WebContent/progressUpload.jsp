<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
body, td, div {
	font-size: 12px;
	font-familly: 宋体;
}

#progressBar {
	width: 400px;
	height: 12px;
	background: #FFFFFF;
	border: 1px solid #000000;
	padding: 1px;
}

#progressBarItem {
	width: 30%;
	height: 100%;
	background: #FF0000;
}
</style>

</head>

<body>

	<iframe name=upload_iframe width=0 height=0></iframe>

    <!-- 用于提交上传的文件 -->
	<form action="servlet/ProgressUploadServlet" method="post"
		enctype="multipart/form-data" target="upload_iframe"
		onsubmit="showStatus(); ">

		<input type="file" name="file1" style="width: 350px;"> <br />
		<input type="file" name="file2" style="width: 350px;"> <br />
		<input type="file" name="file3" style="width: 350px;"> <br />
		<input type="file" name="file4" style="width: 350px;"> <input
			type="submit" value=" 开始上传 " id="btnSubmit">
	</form>
    <!-- 上传进度条 -->
	<div id="status" style="display: none;">
		上传进度条：
		<div id="progressBar">
			<div id="progressBarItem"></div>
		</div>
		<div id="statusInfo"></div>
	</div>

	<br />
	<br />
	<br />
	<br />
	<br />

	<script type="text/javascript">
	    // 当前文件是否上传结束 
		var _finished = true;

	    // 获取指定id的html元素
		function $(obj) {
			return document.getElementById(obj);
		}

	    // 显示进度条
		function showStatus() {
			_finished = false;
			$('status').style.display = 'block';    //将吟唱的进度条显示
			$('progressBarItem').style.width = '1%';    //设置进度条的初始进度显示为1%
			$('btnSubmit').disabled = true;    //设置提交按钮为灰色，防止出现重复提交的情况

			setTimeout("requestStatus()", 1000);    //1秒后，执行requestStatus()方法，更新上传进度
		}
	    
	    //上传进度的更新
		function requestStatus() {

			if (_finished)    // 当前文件是否上传结束 
				return;

			var req = createRequest();    //获取Ajax请求

			req.open("GET", "servlet/ProgressUploadServlet");    //设置请求路径
			req.onreadystatechange = function() {    //请求完毕就刷新进度条，更新展示页面
				callback(req);
			}
			req.send(null);    //发送请求

			setTimeout("requestStatus()", 1000);    //1秒后，执行requestStatus()方法，更新上传进度
		}

	    // 返回Ajax请求
		function createRequest() {
			if (window.XMLHttpRequest)//ns
			{
				return new XMLHttpRequest();
			} else//IE
			{
				try {
					return new ActiveXObject("Msxml2.XMLHTTP");
				} catch (e) {
					return new ActiveXObject("Microsoft.XMLHTTP");
				}
			}
			return null;
		}
	    
	    //进度条的刷新
		function callback(req) {

			if (req.readyState == 4) {    //请求结束后
				if (req.status != 200) {    //如果发生错误，则显示错误信息
					_debug("发生错误。 req.status: " + req.status + "");    //显示错误信息
					return;
				}

				_debug("status.jsp 返回值：" + req.responseText);    //显示debug信息

				var ss = req.responseText.split("||");

				// 格式：百分比||已完成数(M)||文件总长度(M)||传输速率(K)||已用时间(s)||估计总时间(s)||估计剩余时间(s)||正在上传第几个文件
				$('progressBarItem').style.width = '' + ss[0] + '%';
				$('statusInfo').innerHTML = '已完成百分比: ' + ss[0]
						+ '% <br />已完成数(M): ' + ss[1] + '<br/>文件总长度(M): '
						+ ss[2] + '<br/>传输速率(K): ' + ss[3] + '<br/>已用时间(s): '
						+ ss[4] + '<br/>估计总时间(s): ' + ss[5]
						+ '<br/>估计剩余时间(s): ' + ss[6] + '<br/>正在上传第几个文件: '
						+ ss[7];

				if (ss[1] == ss[2]) {
					_finished = true;
					$('statusInfo').innerHTML += "<br/><br/><br/>上传已完成。";
					$('btnSubmit').disabled = false;
				}
			}
		}
	    //显示调试信息
		function _debug(obj) {
			var div = document.createElement("DIV");
			div.innerHTML = "[debug]: " + obj;
			document.body.appendChild(div);
		}
	</script>

</body>
</html>