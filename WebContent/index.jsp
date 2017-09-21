<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>文件测试界面</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>
<body>
	<div align="center">
		<form action="Upload" enctype="multipart/form-data"
			method="post">
			名称：<input name="name" /> <br> 视频：<input name="img" type="file" /><br>
			<input type="submit" value="提交" /> <input type="reset" value="重置" />
		</form>
	</div>
</body>
</html>
