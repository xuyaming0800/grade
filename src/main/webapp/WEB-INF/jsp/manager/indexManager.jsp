<!DOCTYPE html>
<%@page import="cn.com.hzbank.grade.constant.GradeConstant"%>
<%@page import="cn.com.hzbank.grade.bean.UserInfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	UserInfo user = (UserInfo) request.getSession().getAttribute(
			GradeConstant.USER_SESSION);
%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="favicon.ico">

<title>管理员系统维护页面</title>

<!-- Bootstrap core CSS -->
<link href="<%=path%>/resources/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="<%=path%>/resources/css/dashboard.css" rel="stylesheet">
<link href="<%=path%>/resources/css/aof.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/<%=path%>/resources/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="<%=path%>/resources/js/ie-emulation-modes-warning.js"></script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<style>
.pagination .active a {
	background-color: black;
	border-color: black;
}

.pagination>.active>a:hover {
	background-color: black;
	border-color: black;
}
</style>
<script src="<%=path%>/resources/js/common.js"></script>
<script src="<%=path%>/resources/js/jquery-1.11.1.min.js"></script>
<script src="<%=path%>/resources/js/bootstrap-paginator.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	initBind();
});
function initBind(){
  $("li.managerMenu").on("click",function(){
    $("li.managerMenu").removeClass("active");
    $(this).addClass("active");
  });
}

var pageFlag=false;
var options = {
			bootstrapMajorVersion : 3,
			limit : 5
}

function initPage(options, id, totalCount, limit) {
			//分页显示
			var pageElement = $("#" + id + "");
			options.currentPage=1;
			options.totalPages = Math
					.floor(totalCount % limit == 0 ? (totalCount / limit)
							: (totalCount / limit + 1));
			pageElement.bootstrapPaginator(options);
			pageFlag=true;
}
</script>
</head>

<body>
	<div class="container-fluid">
		<div class="row">
		  <div class="col-sm-12 col-md-12 main">
			<ul class="nav nav-tabs">
				<li role="presentation" class="active managerMenu"><a href="#">管理员首页</a></li>
				<li role="presentation" class="managerMenu"><a href="javascript:orgManagerInit()">部门管理</a></li>
				<li role="presentation" class="managerMenu"><a href="javascript:userManagerInit()">用户管理</a></li>
				<li role="presentation" class="managerMenu"><a href="javascript:itemManagerInit()">测评项目管理</a></li>
				<li role="presentation" class="managerMenu"><a href="javascript:batchManagerInit()">测评活动管理</a></li>
			</ul>
		  </div>
		</div>
		<jsp:include page="modals/scoreManager.jsp"></jsp:include>
		<jsp:include page="modals/orgManager.jsp"></jsp:include>
		<jsp:include page="modals/userManager.jsp"></jsp:include>
		<jsp:include page="modals/itemManager.jsp"></jsp:include>
		<jsp:include page="modals/batchManager.jsp"></jsp:include>
		<div class="row">
			<div class="col-sm-12 col-md-12">
				<div style="width:100%;text-align:center;padding-right:2px;">
					<ul id="akListPage" class="pagination pagination-sx">
					</ul>
				</div>
			</div>
		</div>
	</div>
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="<%=path%>/resources/js/bootstrap.min.js"></script>
	<script src="<%=path%>/resources/js/docs.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="<%=path%>/resources/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>

