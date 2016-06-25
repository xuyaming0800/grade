<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="cn.com.hzbank.grade.constant.GradeConstant.USER_INFO_TYPE"%>
<div class="row managerGrade" style="display:none" id="userManager">
			<div class="col-sm-12 col-md-12 main">
				
				<table class="table table-striped" id="userList">
					<caption class="form-inline">
						<input type="text"  class="form-control query" placeholder="用户名称">&nbsp;&nbsp;
						<button type="button" class="btn btn-primary btn-xs search">检索</button>  &nbsp;&nbsp;<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
					</caption>
					<thead>
						<tr>
							<th width="30%">用户名称</th>
							<th width="30%">部门名称</th>
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
					<h4 class="modal-title">添加用户</h4>
				</div>
				<div class="modal-body">
					<table class="table table-striped">
						<tr>
							<td width="20%" style="line-height:35px">用户名称</td>
							<td width="80%"><input type="text" name="name" class="form-control userName"></td>
						</tr>
						<tr>
							<td width="20%" style="line-height:35px">用户UID</td>
							<td width="80%"><input type="text" name="name" class="form-control uid"></td>
						</tr>
						<tr>
							<td width="20%" style="line-height:35px">用户类型</td>
							<td width="80%">
								<select class="userType">
									<option value="<%=USER_INFO_TYPE.COMMON.getCode()%>">普通员工</option>
									<option value="<%=USER_INFO_TYPE.LEADOR.getCode()%>">负责人</option>
								</select>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<table class="table table-striped" id="orgUserList">
									<caption class="form-inline">
										<input type="text"  class="form-control orgQuery" placeholder="部门名称">&nbsp;&nbsp;
										<button type="button" class="btn btn-primary btn-xs orgSearch">检索</button> </span>
									</caption>
									<thead>
									<tr>
										<th width="70%">部门名称</th>
										<th width="30%">操作&nbsp;&nbsp;</th>
									</tr>
									</thead>
									<tbody>
									</tbody>
									<thead>
									<tr>
										<td colspan="2">
											<div style="width:100%;text-align:center;padding-right:2px;">
												<ul id="akUserListPage" class="pagination pagination-sx">
												</ul>
											</div>
										</td>
									</tr>
									</thead>

								</table>
							</td>
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
					<h4 class="modal-title">修改用户</h4>
				</div>
				<div class="modal-body">
					<table class="table table-striped">
						<tr>
							<td width="20%" style="line-height:35px">用户名称</td>
							<td width="80%"><input type="text" name="name" class="form-control userName"></td>
						</tr>
						<tr>
							<td width="20%" style="line-height:35px">用户UID</td>
							<td width="80%"><input type="text" name="name" class="form-control uid"></td>
						</tr>
						<tr>
							<td width="20%" style="line-height:35px">用户类型</td>
							<td width="80%">
								<select class="userType">
									<option value="<%=USER_INFO_TYPE.COMMON.getCode()%>">普通员工</option>
									<option value="<%=USER_INFO_TYPE.LEADOR.getCode()%>">负责人</option>
								</select>
							</td>
						</tr>
						<tr>
							<td width="20%" style="line-height:35px">修改部门</td>
							<td width="80%">
								<input type="hidden" class="orgId">
								<input type="hidden" class="userId">
								<select class="changeOrg">
									<option value="0">是</option>
									<option value="1" selected="selected">否</option>
								</select>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<table class="table table-striped" id="orgUserList">
									<caption class="form-inline">
										<input type="text"  class="form-control orgQuery" placeholder="部门名称">&nbsp;&nbsp;
										<button type="button" class="btn btn-primary btn-xs orgSearch">检索</button> </span>
									</caption>
									<thead>
									<tr>
										<th width="70%">部门名称</th>
										<th width="30%">操作&nbsp;&nbsp;</th>
									</tr>
									</thead>
									<tbody>
									</tbody>
									<thead>
									<tr>
										<td colspan="2">
											<div style="width:100%;text-align:center;padding-right:2px;">
												<ul id="akUserListPage" class="pagination pagination-sx">
												</ul>
											</div>
										</td>
									</tr>
									</thead>

								</table>
							</td>
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
function userManagerInit(){
    $("div.managerGrade").hide();
    $("#userManager").show();
	$("#userManager button").unbind("click");
	$("#userManager input.query").val("");
	pageFlag=false;
	options.limit=5;
	options.onPageClicked=function(event, originalEvent, type, page) {
		getUserInfoList(page);
	};
	getUserInfoList();
	$("#userManager button.search").on("click",function(){
		pageFlag=false;
		options.onPageClicked=function(event, originalEvent, type, page) {
			getUserInfoList(page,$("#userManager input.query").val());
		};
		getUserInfoList(1,$("#userManager input.query").val());
	});
	$("#userManager span.glyphicon-plus").on("click",function(){
		$("#userManager div.add").modal("show");
		pageOrgFlag=false;
		optionsOrg.limit=5;
		optionsOrg.onPageClicked=function(event, originalEvent, type, page) {
			getUserOrgInfoList("div.add",page);
		};
		$("#userManager button.orgSearch").on("click",function(){
			pageOrgFlag=false;
			optionsOrg.onPageClicked=function(event, originalEvent, type, page) {
				getUserOrgInfoList("div.add",page,$("#userManager input.orgQuery").val());
			};
			getUserOrgInfoList("div.add",1,$("#userManager input.orgQuery").val());
		});
		getUserOrgInfoList("div.add");


	});
	$("#userManager div.add button.btn-primary").on("click",function(){
		var userName=$("#userManager div.add input.userName ").val();
		var uid=$("#userManager div.add input.uid ").val();
		var orgId=$("#userManager div.add input.orgId:checked ").val();
		var userType=$("#userManager div.add select.userType option:selected ").val();
		$.ajax({
			type: "POST",
			url: "../manager/addUserInfo",
			dataType:"json",
			data:{"userName":userName,"uid":uid,"orgId":orgId,"userType":userType,"userPass":"12345"} ,
			success: function(msg){
				if(msg.status.code==0){
					$("#userManager div.add").modal("hide");
					userManagerInit();
				}else{
					alert(msg.status.msg)
				}
			},
			error:function(e){
				alert(e)
				toLogin('<%=request.getContextPath()%>');
			}
		});
	});
	$("#userManager div.modify button.btn-primary").on("click",function(){
		var userName=$("#userManager div.modify input.userName ").val();
		var uid=$("#userManager div.modify input.uid ").val();
		var userType=$("#userManager div.modify select.userType option:selected ").val();
		var orgId=$("#userManager div.modify input:hidden.orgId ").val();
		if($("#userManager div.modify select.changeOrg option:selected ").val()==0){
			orgId=$("#userManager div.modify input.orgId:checked ").val();
		}
		var id=$("#userManager div.modify input:hidden.userId ").val();

		$.ajax({
			type: "POST",
			url: "../manager/modifyUserInfo",
			dataType:"json",
			data:{"userName":userName,"uid":uid,"orgId":orgId,"userType":userType,"id":id} ,
			success: function(msg){
				if(msg.status.code==0){
					$("#userManager div.modify").modal("hide");
					userManagerInit();
				}else{
					alert(msg.status.msg)
				}
			},
			error:function(){
				toLogin('<%=request.getContextPath()%>');
			}
		});
	});
	$("#userManager div.modify").on("hide.bs.modal",function(){
		$("#userManager div.modify input.orgName").val("");
		$("#userManager div.modify input.orgDesc").val("");
		$("#userManager div.modify button.btn-primary").attr("disabled",true);
	});
}
function getUserInfoList(page,query){
	if(page==null||page== undefined){
		page=1;
	}
	if(query==null||query==undefined){
		query="";
	}

	$.ajax({
		type: "POST",
		url: "../manager/getUserInfoList",
		dataType:"json",
		data:{"pageNum":page,"pageSize":options.limit,"query":query} ,
		success: function(msg){
			if(msg.status.code==0){
				initUserInfoListTable(msg)

			}else{
				alert("出现错误,请联系管理员")
			}
		},
		error:function(){
			toLogin('<%=request.getContextPath()%>');
		}
	});
}
function initUserInfoListTable(msg){
	$("#userManager #userList tbody tr").remove();
	var count=0;
	if(msg.result.length==0){
		var html="<tr><td colspan='3'>查询结果为空</td></tr>";
		$("#userList").find("tbody").append(html);
		count++;
	}
	else{
		for(var i=0;i<msg.result.length;i++){
			var html="";
			html="<tr><td>"+msg.result[i].userName+"</td><td>"+msg.result[i].orgName+"</td><td><input type='hidden' value='"+msg.result[i].id+"'><span class='glyphicon glyphicon-pencil' aria-hidden='true' title='修改'></span>&nbsp;&nbsp;<span class='glyphicon glyphicon-remove' aria-hidden='true' title='删除'></span></tr>";
			$("#userList").find("tbody").append(html);
			count++;
		}
	}
	for(var i=count;i<options.limit;i++){
		var html="<tr style='height:37px'><td colspan='3'></td></tr>";
		$("#userList").find("tbody").append(html);
	}
	//初始化
	if(!pageFlag){
		initPage(options, "akListPage",msg.totalCount,options.limit);
	}


	$("#userList span.glyphicon-pencil").on("click",function(){
		var userId=$(this).siblings("input:hidden").val();
		$("#userManager div.modify").unbind("show.bs.modal");
		$("#userManager div.modify").on("show.bs.modal",function(){
			$.ajax({
				type: "POST",
				url: "../manager/getUserInfo",
				dataType:"json",
				data:{"userId":userId} ,
				success: function(msg){
					if(msg.status.code==0){
						$("#userManager div.modify button.btn-primary").attr("disabled",false);
						$("#userManager div.modify input.userName").val(msg.result.userName);
						$("#userManager div.modify input.uid").val(msg.result.uid);
						$("#userManager div.modify input:hidden.orgId").val(msg.result.orgId);
						$("#userManager div.modify input:hidden.userId").val(msg.result.id);
						$("#userManager div.modify select.userType option[value="+msg.result.userType+"]").attr("selected",true);

						pageOrgFlag=false;
						optionsOrg.limit=5;
						optionsOrg.onPageClicked=function(event, originalEvent, type, page) {
							getUserOrgInfoList("div.modify",page);
						};
						$("#userManager button.orgSearch").on("click",function(){
							pageOrgFlag=false;
							optionsOrg.onPageClicked=function(event, originalEvent, type, page) {
								getUserOrgInfoList("div.modify",page,$("#userManager input.orgQuery").val());
							};
							getUserOrgInfoList("div.modify",1,$("#userManager input.orgQuery").val());
						});
						getUserOrgInfoList("div.modify");


					}else{
						alert(msg.status.msg);
						$("#userManager div.modify").modal("hide");
					}
				},
				error:function(){
					toLogin('<%=request.getContextPath()%>');
				}
			});
		});
		$("#userManager div.modify").modal("show");
	});
	$("#userManager  #userList span.glyphicon-remove").on("click",function(){
		var userId=$(this).siblings("input:hidden").val();
		var gnl=confirm("确定要删除吗？");
		if(gnl){
			$.ajax({
				type: "POST",
				url: "../manager/deleteUserInfo",
				dataType:"json",
				data:{"id":userId} ,
				success: function(msg){
					if(msg.status.code==0){
						alert("操作成功");
						userManagerInit();

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
}



var pageOrgFlag=false;
var optionsOrg = {
	bootstrapMajorVersion : 3,
	limit : 5
}

function initOrgPage(options, container,id, totalCount, limit) {
	//分页显示
	var pageElement = $(container +" #" + id + "");
	options.currentPage=1;
	options.totalPages = Math
			.floor(totalCount % limit == 0 ? (totalCount / limit)
					: (totalCount / limit + 1));
	pageElement.bootstrapPaginator(options);
	pageOrgFlag=true;
}

function getUserOrgInfoList(container,page,query){
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
				initUserOrgInfoListTable(container,msg)

			}else{
				alert("出现错误,请联系管理员")
			}
		},
		error:function(){
			toLogin('<%=request.getContextPath()%>');
		}
	});
}
function initUserOrgInfoListTable(container,msg){
	$("#userManager "+container+" #orgUserList tbody tr").remove();
	var count=0;
	if(msg.result.length==0){
		var html="<tr><td colspan='2'>查询结果为空</td></tr>";
		$("#userManager "+container+" #orgUserList").find("tbody").append(html);
		count++;
	}
	else{
		for(var i=0;i<msg.result.length;i++){
			var html="";
			html="<tr><td>"+msg.result[i].orgName+"</td><td><input type='radio' name='orgId' class='orgId' value='"+msg.result[i].id+"'></td></tr>";
			$("#userManager "+container+" #orgUserList").find("tbody").append(html);
			count++
		}
	}
	for(var i=count;i<options.limit;i++){
		var html="<tr style='height:37px'><td colspan='2'></td></tr>";
		$("#userManager "+container+" #orgUserList").find("tbody").append(html);
	}
	//初始化
	if(!pageOrgFlag){
		initOrgPage(optionsOrg,"#userManager "+container+" #orgUserList", "akUserListPage",msg.totalCount,options.limit);
	}

}
</script>