<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="row managerGrade" style="display:none" id="userManager">
			<div class="col-sm-12 col-md-12 main">
				
				<table class="table" id="userList">
					<thead>
						<tr>
							<th width="60%">用户名称</th>
							<th width="40%">操作</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
				
			</div>
		</div>
<script>
function userManagerInit(){
  $("div.managerGrade").hide();
  $("#userManager").show();
}
</script>