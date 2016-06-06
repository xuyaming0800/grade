<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="favicon.ico">

<title>Dashboard Template for Bootstrap</title>

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
.pagination .active a{
  background-color: black;
  border-color: black;
}
.pagination>.active>a:hover{
   background-color: black;
  border-color: black;
}
</style>
<script src="<%=path%>/resources/js/jquery-1.11.1.min.js"></script>
<script src="<%=path%>/resources/js/bootstrap-paginator.min.js"></script>
<script type="text/javascript">
function init(){
	getBatchInfo();
}
function getBatchInfo(page){
  if(page==null||page== undefined){
    page=1;
  }
  $("#batch").find("tbody").children().remove();
  $.ajax({
		   type: "POST",
		   url: "../common/getOpenBatch",
		   dataType:"json",
		   data:{"pageNum":page,"pageSize":options.limit} ,
		   success: function(msg){
		     if(msg.status.code==0){
		    	 initJsonFileTable(msg)
		     }else{
		    	alert("出现错误,请联系管理员")
		     }
		   },
		   error:function(){
			   location.href="../login";
		   }
	});
}
$(document).ready(function(){
	init();
});

function initJsonFileTable(msg){
    var count=0;
    if(msg.result.length==0){
       var html="<tr><td colspan='2'>查询结果为空</td></tr>";
	   $("#batch").find("tbody").append(html);
	   count++;
    }
    else{
      for(var i=0;i<msg.result.length;i++){
		var html="<tr><td>"+msg.result[i].batchName+"</td><td><a href='javascript:editRunBase(\""+msg.result[i].id+"\")'>开始评分</a></td></tr>";
		$("#batch").find("tbody").append(html);
		count++
	  }
    }
    for(var i=count;i<options.limit;i++){
       var html="<tr style='height:37px'><td colspan='2'></td></tr>";
	   $("#batch").find("tbody").append(html);
    }
	//初始化
	if(!flag){
	  initPage(options, "akListPage",msg.totalCount,options.limit);
	}
}

var flag=false;
var options = {
			bootstrapMajorVersion : 3,
			limit : 1,
			onPageClicked : function(event, originalEvent, type, page) {
			  getBatchInfo(page);
			}
}
function initPage(options, id, totalCount, limit) {
			//分页显示
			var pageElement = $("#" + id + "");
			options.totalPages = Math
					.floor(totalCount % limit == 0 ? (totalCount / limit)
							: (totalCount / limit + 1));
			pageElement.bootstrapPaginator(options);
			flag=true;
		}
</script>
</head>

<body>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12 col-md-12 main">
				
				<table class="table" id="batch">
					<caption>绩效考勤评分活动列表
					</caption>
					<thead>
						<tr>
							<th width="60%">活动名称</th>
							<th width="40%">操作</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
				
			</div>
		</div>
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
