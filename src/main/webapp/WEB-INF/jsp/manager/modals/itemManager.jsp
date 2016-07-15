<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="row managerGrade" style="display:none" id="itemManager">
			<div class="col-sm-12 col-md-12 main">
				
				<table class="table table-striped" id="itemList">
					<caption class="form-inline">
						&nbsp;&nbsp;<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
					</caption>
					<thead>
						<tr>
							<th width="60%">测评项目名称</th>
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
					<h4 class="modal-title">添加测评项目</h4>
				</div>
				<div class="modal-body">
					<table class="table table-striped">
						<tr>
							<td width="20%" style="line-height:35px">测评项目名称</td>
							<td width="80%"><input type="text" name="name" class="form-control itemName"></td>
						</tr>
						<tr>
							<td width="20%" style="line-height:35px">权重值</td>
							<td width="80%"><input type="text" name="name" class="form-control percent"></td>
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
					<h4 class="modal-title">修改测评项目</h4>
				</div>
				<div class="modal-body">
					<input type="hidden" name="name" class="form-control orgId">
					<table class="table table-striped">
						<tr>
							<td width="20%" style="line-height:35px">测评项目名称</td>
							<input type="hidden" class="itemId">
							<td width="80%"><input type="text" name="name" class="form-control itemName"></td>
						</tr>
						<tr>
							<td width="20%" style="line-height:35px">权重值</td>
							<td width="80%"><input type="text" name="name" class="form-control percent"></td>
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
function itemManagerInit(){
    $("div.managerGrade").hide();
    $("#itemManager").show();
	$("#itemManager button").unbind("click");
	pageFlag=false;
	options.limit=5;
	options.onPageClicked=function(event, originalEvent, type, page) {
		getItemInfoList(page);
	};
	getItemInfoList();
	$("#itemManager span.glyphicon-plus").on("click",function(){
		$("#itemManager div.add").modal("show");
	});

	$("#itemManager div.add button.btn-primary").on("click",function(){
		var itemName=$("#itemManager div.add input.itemName ").val();
		var percent=$("#itemManager div.add input.percent ").val();
		$.ajax({
			type: "POST",
			url: "../manager/addItemInfo",
			dataType:"json",
			data:{"itemName":itemName,"percent":percent} ,
			success: function(msg){
				if(msg.status.code==0){
					$("#itemManager div.add").modal("hide");
					itemManagerInit();
				}else{
					alert(msg.status.msg)
				}
			},
			error:function(){
				toLogin('<%=request.getContextPath()%>');
			}
		});
	});
	$("#itemManager div.modify button.btn-primary").on("click",function(){
		var itemName=$("#itemManager div.modify input.itemName ").val();
		var percent=$("#itemManager div.modify input.percent ").val();
		var itemId=$("#itemManager div.modify input.itemId").val();
		$.ajax({
			type: "POST",
			url: "../manager/modifyItemInfo",
			dataType:"json",
			data:{"itemName":itemName,"id":itemId,"percent":percent} ,
			success: function(msg){
				if(msg.status.code==0){
					$("#itemManager div.modify").modal("hide");
					itemManagerInit();
				}else{
					alert(msg.status.msg)
				}
			},
			error:function(){
				toLogin('<%=request.getContextPath()%>');
			}
		});
	});
	$("#itemManager div.modify").on("hide.bs.modal",function(){
		$("#itemManager div.modify input.itemName").val("");
		$("#itemManager div.modify button.btn-primary").attr("disabled",true);
	});
}
function getItemInfoList(page,query){
	if(page==null||page== undefined){
		page=1;
	}
	if(query==null||query==undefined){
		query="";
	}

	$.ajax({
		type: "POST",
		url: "../manager/getItemInfoList",
		dataType:"json",
		data:{"pageNum":page,"pageSize":options.limit} ,
		success: function(msg){
			if(msg.status.code==0){
				initItemInfoListTable(msg)

			}else{
				alert("出现错误,请联系管理员")
			}
		},
		error:function(){
			toLogin('<%=request.getContextPath()%>');
		}
	});
}
function initItemInfoListTable(msg){
	$("#itemManager #itemList tbody tr").remove();
	var count=0;
	if(msg.result.length==0){
		var html="<tr><td colspan='2'>查询结果为空</td></tr>";
		$("#itemManager #itemList").find("tbody").append(html);
		count++;
	}
	else{
		for(var i=0;i<msg.result.length;i++){
			var html="";
			html="<tr><td>"+msg.result[i].itemName+"</td><td><input type='hidden' value='"+msg.result[i].id+"'><span class='glyphicon glyphicon-pencil' aria-hidden='true' title='修改'></span>&nbsp;&nbsp;<span class='glyphicon glyphicon-remove' aria-hidden='true' title='删除'></span></tr>";
			$("#itemManager #itemList").find("tbody").append(html);
			count++
		}
	}
	for(var i=count;i<options.limit;i++){
		var html="<tr style='height:37px'><td colspan='2'></td></tr>";
		$("#itemManager #itemList").find("tbody").append(html);
	}
	//初始化
	if(!pageFlag){
		initPage(options, "akListPage",msg.totalCount,options.limit);
	}

	$("#itemManager #itemList span.glyphicon-remove").on("click",function(){
		var itemId=$(this).siblings("input:hidden").val();
		var gnl=confirm("确定要删除吗？");
		if(gnl){
			$.ajax({
				type: "POST",
				url: "../manager/deleteItemInfo",
				dataType:"json",
				data:{"id":itemId} ,
				success: function(msg){
					if(msg.status.code==0){
						alert("操作成功");
						itemManagerInit();

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

	$("#itemManager #itemList span.glyphicon-pencil").on("click",function(){
		var itemId=$(this).siblings("input:hidden").val();
		$("#itemManager div.modify").unbind("show.bs.modal");
		$("#itemManager div.modify").on("show.bs.modal",function(){
			$.ajax({
				type: "POST",
				url: "../manager/getItemInfo",
				dataType:"json",
				data:{"itemId":itemId} ,
				success: function(msg){
					if(msg.status.code==0){
						$("#itemManager div.modify input.itemName").val(msg.result.itemName);
						$("#itemManager div.modify input.percent").val(msg.result.percent);
						$("#itemManager div.modify input.itemId").val(itemId);
						$("#itemManager div.modify button.btn-primary").attr("disabled",false);

					}else{
						alert(msg.status.msg);
						$("#itemManager div.modify").modal("hide");
					}
				},
				error:function(){
					toLogin('<%=request.getContextPath()%>');
				}
			});
		});
		$("#itemManager div.modify").modal("show");
	});
}
</script>