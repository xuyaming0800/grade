<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="row managerGrade" style="display:none" id="scoreManager">
			<div class="col-sm-12 col-md-12 main">
				
				<table class="table table-striped" id="orgList">
				    <caption class="form-inline">
				    <input type="text"  class="form-control query" placeholder="部门名称">&nbsp;&nbsp;
				    <button type="button" class="btn btn-primary btn-xs search">检索</button>
						<button type="button" class="btn btn-primary btn-xs score">查看分数和排名</button>
						<button type="button" class="btn btn-primary btn-xs scoreAvg">查看平均分数</button>
					</caption>
					<thead>
						<tr>
							<th width="70%">部门名称</th>
							<th width="30%">选择一个部门查看分数&nbsp;&nbsp;</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
				
			</div>
	<div class="modal fade score">
	<div class="modal-dialog" style="width:80%">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">员工得分与排名详情</h4>
			</div>
			<div class="modal-body">
				<table class="table table-striped" id="scoreList">
					<tr id="listHeader">
					</tr>
				</table>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div>
	<div class="modal fade scoreAvg">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">员工平均分</h4>
				</div>
				<div class="modal-body">
					<table class="table table-striped" id="scoreList">
						<tr id="listHeader">
						</tr>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div>
</div>
		
<script>
function scoreManagerInit(batchId){
  $("div.managerGrade").hide();
  $("#scoreManager").show();
  $("#scoreManager button").unbind("click");
  $("#scoreManager input.query").val("");
  pageFlag=false;
  options.limit=5;
  options.onPageClicked=function(event, originalEvent, type, page) {
	  getScoreOrgInfoList(page);
  };
  getScoreOrgInfoList();
  $("#scoreManager button.search").on("click",function(){
     pageFlag=false;
      options.onPageClicked=function(event, originalEvent, type, page) {
		  getScoreOrgInfoList(page,$("#scoreManager input.query").val());
     };
	  getScoreOrgInfoList(1,$("#scoreManager input.query").val());
  });
  $("#scoreManager button.score").on("click",function () {
	  if($("#scoreManager #orgList input:radio:checked").length==1){
		  var orgId=$("#scoreManager #orgList input:radio:checked").val();
		  $.ajax({
			  type: "POST",
			  url: "../manager/score",
			  dataType:"json",
			  data:{"batchId":batchId,"orgId":orgId} ,
			  success: function(msg){
				  if(msg.status.code==0){
					  if(msg.result.length==0){
						  alert("目前暂无评分结果,请稍后再试");

					  }else{
						  for(var i=0;i<msg.result.length;i++){
							  var score=msg.result[i].scoreMap;
							  var grade=msg.result[i].gradeMap;
							  var name=msg.result[i].userName;
							  var totalScore=msg.result[i].totalScore;
							  if(i==0){
								  $("#scoreManager div.score tr#listHeader").empty();
								  $("#scoreManager div.score tr.listContent").remove();
								  $("#scoreManager div.score tr#listHeader").append("<td width='20%' style='line-height:35px'>员工</td>");
								  $("#scoreManager div.score tr#listHeader").append("<td width='20%' style='line-height:35px' id='totalScore'>总分</td>");
								  for(var key in grade){
									  $("#scoreManager div.score td#totalScore").before("<td style='line-height:35px'>"+key+"排名</td>");
								  }
								  for(var key in score){
									  $("#scoreManager div.score td#totalScore").before("<td style='line-height:35px'>"+key+"分数</td>");
								  }
							  }
							  var _html="<tr class='listContent'><td>"+name+"</td>";
							  for(var key in grade){
								  _html=_html+"<td>"+grade[key]+"</td>";
							  }
							  for(var key in score){
								  _html=_html+"<td>"+score[key]+"</td>";
							  }
							  _html=_html+"<td>"+totalScore+"</td>";
							  $("#scoreManager div.score tr#listHeader").parent().append(_html);

						  }
						  $("#scoreManager div.score").modal("show");
					  }

//
				  }else{
					  alert("出现错误,请联系管理员")
				  }
			  },
			  error:function(){
				  toLogin('<%=request.getContextPath()%>');
			  }
		  });
	  }else{
		  alert("请选择一个部门")
	  }

  });
  $("#scoreManager button.scoreAvg").on("click",function () {
	  if($("#scoreManager #orgList input:radio:checked").length==1){
		  var orgId=$("#scoreManager #orgList input:radio:checked").val();
		  $.ajax({
			  type: "POST",
			  url: "../manager/scoreAvg",
			  dataType:"json",
			  data:{"batchId":batchId,"orgId":orgId} ,
			  success: function(msg){
				  if(msg.status.code==0){
					  if(msg.result.length==0){
						  alert("目前暂无评分结果,请稍后再试");

					  }else{
						  for(var i=0;i<msg.result.length;i++){
							  var name=msg.result[i].userName;
							  var totalScore=msg.result[i].totalScore;
							  if(i==0){
								  $("#scoreManager div.scoreAvg tr#listHeader").empty();
								  $("#scoreManager div.scoreAvg tr.listContent").remove();
								  $("#scoreManager div.scoreAvg tr#listHeader").append("<td width='50%' style='line-height:35px'>员工</td>");
								  $("#scoreManager div.scoreAvg tr#listHeader").append("<td width='50%' style='line-height:35px' id='totalScore'>平均分</td>");
							  }
							  var _html="<tr class='listContent'><td>"+name+"</td>";
							  _html=_html+"<td>"+totalScore+"</td>";
							  $("#scoreManager div.scoreAvg tr#listHeader").parent().append(_html);

						  }
						  $("#scoreManager div.scoreAvg").modal("show");
					  }

//
				  }else{
					  alert("出现错误,请联系管理员")
				  }
			  },
			  error:function(){
				  toLogin('<%=request.getContextPath()%>');
			  }
		  });
	  }else{
		  alert("请选择一个部门")
	  }
  });

}
function getScoreOrgInfoList(page,query){
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
		    	 initScoreOrgInfoListTable(msg)
		    	 
		     }else{
		    	alert("出现错误,请联系管理员")
		     }
		   },
		   error:function(){
			   toLogin('<%=request.getContextPath()%>');
		   }
	});
}
function initScoreOrgInfoListTable(msg){
    $("#scoreManager #orgList tbody tr").remove();
    var count=0;
    if(msg.result.length==0){
       var html="<tr><td colspan='2'>查询结果为空</td></tr>";
	   $("#scoreManager #orgList").find("tbody").append(html);
	   count++;
    }
    else{
      for(var i=0;i<msg.result.length;i++){
        var html="";
        html="<tr><td>"+msg.result[i].orgName+"</td>选择<td><input type='hidden' value='"+msg.result[i].id+"'><input type='radio' name='orgId' value='"+msg.result[i].id+"'></tr>";
		$("#scoreManager #orgList").find("tbody").append(html);
		count++
	  }
    }
    for(var i=count;i<options.limit;i++){
       var html="<tr style='height:37px'><td colspan='2'></td></tr>";
	   $("#scoreManager #orgList").find("tbody").append(html);
    }
	//初始化
	if(!pageFlag){
	  initPage(options, "akListPage",msg.totalCount,options.limit);
	}
}
</script>