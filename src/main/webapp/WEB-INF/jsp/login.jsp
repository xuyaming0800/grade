<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="favicon.ico">

<title>绩效考核测评系统</title>

<!-- Bootstrap core CSS -->
<link href="<%=path%>/resources/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="<%=path%>/resources/css/dashboard.css" rel="stylesheet">
<link href="<%=path%>/resources/css/aof.css" rel="stylesheet">
<link href="<%=path%>/resources/css/signin.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/<%=path%>/resources/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="<%=path%>/resources/js/ie-emulation-modes-warning.js"></script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<script src="<%=path%>/resources/js/jquery-1.11.1.min.js"></script>

</head>
<style>
body{
 background-color: #101010;
 color:white;
}
</style>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$("#loginDiv")
								.css(
										"top",
										window.screen.height / 4);
						$("#login")
								.click(
										function() {
											$
													.ajax({
														type : "POST",
														url : "checkLogin",
														dataType : "json",
														data : {
															uid : $(
																	"#uid")
																	.val(),
															userPass : $(
																	"#userPass")
																	.val()
														},
														success : function(msg) {
															if (msg.status.code == 0) {
																alert("登录成功")
															} else {
															    alert(msg.status.msg)
															}
														}
													});
										});
					});
</script>

<body>

	<div class="container col-sm-4 col-md-4 col-md-offset-4 col-sm-offset-4" id="loginDiv">

			<h2 class="form-signin-heading" style="color:white" >绩效考核测评系统</h2>
			<div class="input-group">
				<span class="input-group-addon">员工工号</span> <input type="text"
					class="form-control" placeholder="" name="uid" id="uid">
			</div>
			<div style="margin:auto;text-align: center;height:5px">&nbsp;</div>
			<div class="input-group">
				<span class="input-group-addon">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</span> <input type="password"
					class="form-control" placeholder="" name="userPass" id="userPass">
			</div>
			<div style="margin:auto;text-align: center;height:5px">&nbsp;</div>
			<div style="margin:auto;text-align: center;">
			<button type="button" class="btn btn-default" id="login">
				<span class="glyphicon glyphicon-user"></span> 登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录
			</button>
			</div>
			
	</div>

	</div>
	<br>
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="<%=path%>/resources/js/bootstrap.min.js"></script>
	<script src="<%=path%>/resources/js/docs.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="<%=path%>/resources/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
