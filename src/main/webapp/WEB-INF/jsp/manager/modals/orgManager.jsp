<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="row managerGrade" style="display:none" id="orgManager">
			<div class="col-sm-12 col-md-12 main">
				
				<table class="table table-striped" id="orgList">
				    <caption class="form-inline">
				    <input type="text"  class="form-control query" placeholder="部门名称">&nbsp;&nbsp;
				    <button type="button" class="btn btn-primary btn-xs search">检索</button>  &nbsp;&nbsp;<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
					</caption>
					<thead>
						<tr>
							<th width="70%">部门名称</th>
							<th width="30%">操作&nbsp;&nbsp;</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
				
			</div>
			<div class="modal fade add">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">添加一个部门</h4>
      </div>
      <div class="modal-body">
       <table class="table table-striped">
        <tr>
		<td width="20%" style="line-height:35px">部门名称</td>
		<td width="80%"><input type="text" name="name" class="form-control orgName"></td>
		</tr>
		<tr>
		<td width="20%" style="line-height:35px">部门描述</td>
		<td width="80%"><input type="text" name="name" class="form-control orgDesc"></td>
		</tr>
       </table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary">保存</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
 </div>
 <div class="modal fade modify">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">修改一个部门</h4>
      </div>
      <div class="modal-body">
       <input type="hidden" name="name" class="form-control orgId">
       <table class="table table-striped">
        <tr>
		<td width="20%" style="line-height:35px">部门名称</td>
		<td width="80%"><input type="text" name="name" class="form-control orgName"></td>
		</tr>
		<tr>
		<td width="20%" style="line-height:35px">部门描述</td>
		<td width="80%"><input type="text" name="name" class="form-control orgDesc"></td>
		</tr>
       </table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" disabled="disabled">保存</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
 </div>
</div>
		
<script>
function orgManagerInit(){
  $("div.managerGrade").hide();
  $("#orgManager").show();
  $("#orgManager button").unbind("click");
  $("#orgManager input.query").val("");
  pageFlag=false;
  options.limit=5;
  options.onPageClicked=function(event, originalEvent, type, page) {
			  getOrgInfoList(page);
  };
  getOrgInfoList();
  $("#orgManager button.search").on("click",function(){
     pageFlag=false;
      options.onPageClicked=function(event, originalEvent, type, page) {
			  getOrgInfoList(page,$("#orgManager input.query").val());
     };
     getOrgInfoList(1,$("#orgManager input.query").val());
  });
  $("#orgManager span.glyphicon-plus").on("click",function(){
     $("#orgManager div.add").modal("show");
  });
  $("#orgManager div.add button.btn-primary").on("click",function(){
     var orgName=$("#orgManager div.add input.orgName ").val();
     var orgDesc=$("#orgManager div.add input.orgDesc ").val();
     $.ajax({
		   type: "POST",
		   url: "../manager/addOrgInfo",
		   dataType:"json",
		   data:{"orgName":orgName,"orgDesc":orgDesc} ,
		   success: function(msg){
		     if(msg.status.code==0){
		         $("#orgManager div.add").modal("hide");
		    	 orgManagerInit();
		     }else{
		    	alert(msg.status.msg)
		     }
		   },
		   error:function(){
			   toLogin('<%=request.getContextPath()%>');
		   }
	});
  });
   $("#orgManager div.modify button.btn-primary").on("click",function(){
     var orgName=$("#orgManager div.modify input.orgName").val();
     var orgDesc=$("#orgManager div.modify input.orgDesc").val();
     var orgId=$("#orgManager div.modify input.orgId").val();
     $.ajax({
		   type: "POST",
		   url: "../manager/modifyOrgInfo",
		   dataType:"json",
		   data:{"orgName":orgName,"orgDesc":orgDesc,"id":orgId} ,
		   success: function(msg){
		     if(msg.status.code==0){
		         $("#orgManager div.modify").modal("hide");
		    	 orgManagerInit();
		     }else{
		    	alert(msg.status.msg)
		     }
		   },
		   error:function(){
			   toLogin('<%=request.getContextPath()%>');
		   }
	});
  });
   $("#orgManager div.modify").on("hide.bs.modal",function(){
      $("#orgManager div.modify input.orgName").val("");
      $("#orgManager div.modify input.orgDesc").val("");
	  $("#orgManager div.modify button.btn-primary").attr("disabled",true);
   });
}
function getOrgInfoList(page,query){
  if(page==null||page== undefined){
    page=1;
  }
  if(query==null||query==undefined){
    query="";
  }
  
  $.ajax({
		   type: "POST",
		   url: "../manager/getOrgInfoList",
		   dataType:"json",
		   data:{"pageNum":page,"pageSize":options.limit,"query":query} ,
		   success: function(msg){
		     if(msg.status.code==0){
		    	 initOrgInfoListTable(msg)
		    	 
		     }else{
		    	alert("出现错误,请联系管理员")
		     }
		   },
		   error:function(){
			   toLogin('<%=request.getContextPath()%>');
		   }
	});
}
function initOrgInfoListTable(msg){ 
    $("#orgManager #orgList tbody tr").remove();
    var count=0;
    if(msg.result.length==0){
       var html="<tr><td colspan='2'>查询结果为空</td></tr>";
	   $("#orgList").find("tbody").append(html);
	   count++;
    }
    else{
      for(var i=0;i<msg.result.length;i++){
        var html="";
        html="<tr><td>"+msg.result[i].orgName+"</td><td><input type='hidden' value='"+msg.result[i].id+"'><span class='glyphicon glyphicon-pencil' aria-hidden='true' title='修改'></span>&nbsp;&nbsp;<span class='glyphicon glyphicon-remove' aria-hidden='true' title='删除'></span></tr>";
		$("#orgList").find("tbody").append(html);
		count++
	  }
    }
    for(var i=count;i<options.limit;i++){
       var html="<tr style='height:37px'><td colspan='2'></td></tr>";
	   $("#orgList").find("tbody").append(html);
    }
	//初始化
	if(!pageFlag){
	  initPage(options, "akListPage",msg.totalCount,options.limit);
	}
	
	 $("#orgList span.glyphicon-remove").on("click",function(){
	    var orgId=$(this).siblings("input:hidden").val();
	    var gnl=confirm("确定要删除吗？");
	    if(gnl){
	      $.ajax({
		   type: "POST",
		   url: "../manager/deleteOrgInfo",
		   dataType:"json",
		   data:{"id":orgId} ,
		   success: function(msg){
		     if(msg.status.code==0){
		    	alert("操作成功");
		    	orgManagerInit();
		    	 
		     }else{
		    	alert(msg.status.msg);
		     }
		   },
		   error:function(){
			   toLogin('<%=request.getContextPath()%>');
		   }
	    });
	    }
	 });
	
	 $("#orgList span.glyphicon-pencil").on("click",function(){
      var orgId=$(this).siblings("input:hidden").val();
      $("#orgManager div.modify").unbind("show.bs.modal");
      $("#orgManager div.modify").on("show.bs.modal",function(){
         $.ajax({
		   type: "POST",
		   url: "../manager/getOrgInfo",
		   dataType:"json",
		   data:{"orgId":orgId} ,
		   success: function(msg){
		     if(msg.status.code==0){
		    	 $("#orgManager div.modify input.orgName").val(msg.result.orgName);
		    	 $("#orgManager div.modify input.orgDesc").val(msg.result.orgDesc);
		    	 $("#orgManager div.modify input.orgId").val(orgId);
		    	 $("#orgManager div.modify button.btn-primary").attr("disabled",false);
		    	 
		     }else{
		    	alert(msg.status.msg);
		    	$("#orgManager div.modify").modal("hide");
		     }
		   },
		   error:function(){
			   toLogin('<%=request.getContextPath()%>');
		   }
	    });
      });
      $("#orgManager div.modify").modal("show");
     });
}
</script>