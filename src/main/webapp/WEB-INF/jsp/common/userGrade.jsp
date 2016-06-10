<!DOCTYPE html>
<%@page import="cn.com.hzbank.grade.constant.GradeConstant"%>
<%@page import="cn.com.hzbank.grade.bean.UserInfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String batchId=request.getParameter("batchId");
UserInfo user=(UserInfo)request.getSession().getAttribute(GradeConstant.USER_SESSION);
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
<script src="<%=path%>/resources/js/common.js"></script>
<script src="<%=path%>/resources/js/jquery-1.11.1.min.js"></script>
<script src="<%=path%>/resources/js/bootstrap-paginator.min.js"></script>
<script type="text/javascript">
function init(){
	getBatchItem();
}
function getBatchItem(){
  $("#user_batch").find("thead").children().remove();
  $("#user_batch").find("tbody").children().remove();
  $.ajax({
		   type: "POST",
		   url: "../common/getGradeItem",
		   dataType:"json",
		   data:{"batchId":<%=batchId%>} ,
		   success: function(msg){
		     if(msg.status.code==0){
		    	 toField(msg);
		    	 getUserInfo();
		     }else{
		    	alert(msg.status.msg)
		     }
		   },
		   error:function(){
			   toLogin('<%=path%>');
		   }
	});
}

function getUserInfo(){
   $("#user_batch").find("tbody").children().remove();
   $.ajax({
		   type: "POST",
		   url: "../common/getUserInfo",
		   dataType:"json",
		   data:{"orgId":<%= user.getOrgId() %>} ,
		   success: function(msg){
		     if(msg.status.code==0){
		    	 toGrade(msg)
		     }else{
		    	alert(msg.status.msg)
		     }
		   },
		   error:function(){
			   toLogin('<%=path%>');
		   }
	});
}

$(document).ready(function(){
	init();
	initBind();
});
var fieldArray=new Array();
function toField(msg){
    var html="<tr><td style='border:1px solid;' width='20%'></td>";
    for(var i=0;i<msg.result.length;i++){
		var _html="<td style='border:1px solid;text-align:center'>"+msg.result[i].itemName+"-排名</td>";
		html=html+_html;
		fieldArray.push(msg.result[i].id);
	}
	html=html+"</tr>"
	$("#user_batch").find("thead").append(html);
}
var userSize=0;
function toGrade(msg){
    userSize=msg.result.length;
    for(var i=0;i<msg.result.length;i++){
		 var html="<tr><td style='border:1px solid;' width='20%'>"+msg.result[i].userName+"</td>";
         for(var j=0;j<fieldArray.length;j++){
		   var _html="<td style='border:1px solid;text-align:center'><select name='"+msg.result[i].id+"_"+fieldArray[j]+"' class='gradeSelect'>";
		   for(var k=1;k<=userSize;k++){
		     var __html="<option value='"+k+"'>"+k+"</option>";
		     _html=_html+__html;
		   }
		   _html=_html+"</select></td>";
		   html=html+_html;
	     }
	     html=html+"</tr>"
	     $("#user_batch").find("tbody").append(html);
     }
}
function initBind(){
  $("button.submitGrade").on("click",function(){
      var gradeObj={};
      var flag=false;
      $("select.gradeSelect").each(function(i){
        $(this).parent().css("background-color","white");
      })
      $.each(fieldArray,function(i,n){
        gradeObj[""+n+""]=new Array(userSize);
      });
	  $("select.gradeSelect").each(function(i){
	    var selectName=$(this).attr("name").split("_");
	    var _userId=selectName[0];
	    var _itemId=selectName[1];
	    var grade=$(this).val();
	    var indexValue=gradeObj[""+_itemId+""][grade-1];
	    if(indexValue==null||indexValue==undefined){
	      gradeObj[""+_itemId+""][grade-1]=_userId;
	    }else{
	      flag=true;
	      $(this).parent().css("background-color","red");
	      $("select[name='"+indexValue+"_"+_itemId+"']").parent().css("background-color","red");
	    }
	  });
	  if(flag){
	    alert("同一项目的排名不能重复");
	    return;
	  }
	  submitGradeUser(gradeObj);
	});
}
function submitGradeUser(datas){
  $.ajax({
		   type: "POST",
		   url: "../common/submitGradeUserInfo",
		   dataType:"json",
		   data:{"orgId":<%= user.getOrgId() %>,"batchId":<%=batchId%>,"content":JSON.stringify(datas)} ,
		   success: function(msg){
		     if(msg.status.code==0){
		    	alert("提交成功")
		     }else{
		    	alert(msg.status.msg)
		     }
		   },
		   error:function(){
			   toLogin('<%=path%>');
		   }
	});
}	


</script>
</head>

<body>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12 col-md-12 main">
				
				<table class="table" id="user_batch">
					<caption>部门内员工打分表格
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
		<div class="row text-center">
		<div class="col-sm-12 col-md-12 main">
		  <button type="button" class="btn btn-primary submitGrade">提交</button>
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
