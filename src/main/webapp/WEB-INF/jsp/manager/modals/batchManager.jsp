<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="row managerGrade" style="display:none" id="batchManager">
			<div class="col-sm-12 col-md-12 main">
				
				<table class="table table-striped" id="batchList">
					<caption class="form-inline">
						&nbsp;&nbsp;<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
					</caption>
					<thead>
						<tr>
							<th width="60%">测评活动名称</th>
							<th width="40%">操作</th>
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
					<h4 class="modal-title">添加测评活动</h4>
				</div>
				<div class="modal-body">
					<table class="table table-striped">
						<tr>
							<td width="20%" style="line-height:35px">测评活动名称</td>
							<td width="80%"><input type="text" name="name" class="form-control batchName"></td>
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
					<h4 class="modal-title">修改测评活动</h4>
				</div>
				<div class="modal-body">
					<input type="hidden" name="name" class="form-control orgId">
					<table class="table table-striped">
						<tr>
							<td width="20%" style="line-height:35px">测评活动名称</td>
							<input type="hidden" class="batchId">
							<td width="80%"><input type="text" name="name" class="form-control batchName"></td>
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
function batchManagerInit(){
    $("div.managerGrade").hide();
    $("#batchManager").show();
	$("#batchManager button").unbind("click");
	pageFlag=false;
	options.limit=5;
	options.onPageClicked=function(event, originalEvent, type, page) {
		getBatchInfoList(page);
	};
	getBatchInfoList();
	$("#batchManager span.glyphicon-plus").on("click",function(){
		$("#batchManager div.add").modal("show");
	});

	$("#batchManager div.add button.btn-primary").on("click",function(){
		var batchName=$("#batchManager div.add input.batchName ").val();
		$.ajax({
			type: "POST",
			url: "../manager/addBatchInfo",
			dataType:"json",
			data:{"batchName":batchName} ,
			success: function(msg){
				if(msg.status.code==0){
					$("#batchManager div.add").modal("hide");
					batchManagerInit();
				}else{
					alert(msg.status.msg)
				}
			},
			error:function(){
				toLogin('<%=request.getContextPath()%>');
			}
		});
	});
	$("#batchManager div.modify button.btn-primary").on("click",function(){
		var batchName=$("#batchManager div.modify input.batchName ").val();
		var batchId=$("#batchManager div.modify input.batchId").val();
		$.ajax({
			type: "POST",
			url: "../manager/modifyBatchInfo",
			dataType:"json",
			data:{"batchName":batchName,"id":batchId} ,
			success: function(msg){
				if(msg.status.code==0){
					$("#batchManager div.modify").modal("hide");
					batchManagerInit();
				}else{
					alert(msg.status.msg)
				}
			},
			error:function(){
				toLogin('<%=request.getContextPath()%>');
			}
		});
	});
	$("#batchManager div.modify").on("hide.bs.modal",function(){
		$("#batchManager div.modify input.batchName").val("");
		$("#batchManager div.modify button.btn-primary").attr("disabled",true);
	});
}
function getBatchInfoList(page,query){
	if(page==null||page== undefined){
		page=1;
	}
	if(query==null||query==undefined){
		query="";
	}

	$.ajax({
		type: "POST",
		url: "../manager/getBatchInfoList",
		dataType:"json",
		data:{"pageNum":page,"pageSize":options.limit} ,
		success: function(msg){
			if(msg.status.code==0){
				initBatchInfoListTable(msg)

			}else{
				alert("出现错误,请联系管理员")
			}
		},
		error:function(){
			toLogin('<%=request.getContextPath()%>');
		}
	});
}
function initBatchInfoListTable(msg){
	$("#batchManager #batchList tbody tr").remove();
	var count=0;
	if(msg.result.length==0){
		var html="<tr><td colspan='2'>查询结果为空</td></tr>";
		$("#batchManager #batchList").find("tbody").append(html);
		count++;
	}
	else{
		for(var i=0;i<msg.result.length;i++){
			var html="";
			html="<tr><td>"+msg.result[i].batchName+"</td><td><input type='hidden' value='"+msg.result[i].id+"'><span class='glyphicon glyphicon-pencil' aria-hidden='true' title='修改'></span>&nbsp;&nbsp;<span class='glyphicon glyphicon-list-alt' aria-hidden='true' title='查看结果'></span></tr>";
			$("#batchManager #batchList").find("tbody").append(html);
			count++
		}
	}
	for(var i=count;i<options.limit;i++){
		var html="<tr style='height:37px'><td colspan='2'></td></tr>";
		$("#batchManager #batchList").find("tbody").append(html);
	}
	//初始化
	if(!pageFlag){
		initPage(options, "akListPage",msg.totalCount,options.limit);
	}


	$("#batchManager #batchList span.glyphicon-pencil").on("click",function(){
		var batchId=$(this).siblings("input:hidden").val();
		$("#batchManager div.modify").unbind("show.bs.modal");
		$("#batchManager div.modify").on("show.bs.modal",function(){
			$.ajax({
				type: "POST",
				url: "../manager/getBatchInfo",
				dataType:"json",
				data:{"batchId":batchId} ,
				success: function(msg){
					if(msg.status.code==0){
						$("#batchManager div.modify input.batchName").val(msg.result.batchName);
						$("#batchManager div.modify input.batchId").val(batchId);
						$("#batchManager div.modify button.btn-primary").attr("disabled",false);

					}else{
						alert(msg.status.msg);
						$("#batchManager div.modify").modal("hide");
					}
				},
				error:function(){
					toLogin('<%=request.getContextPath()%>');
				}
			});
		});
		$("#batchManager div.modify").modal("show");
	});

	$("#batchManager #batchList span.glyphicon-list-alt").on("click",function(){
		var batchId=$(this).siblings("input:hidden").val();
		scoreManagerInit(batchId);
	});
}
</script>