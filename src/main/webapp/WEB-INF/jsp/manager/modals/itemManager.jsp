<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="row managerGrade" style="display:none" id="itemManager">
			<div class="col-sm-12 col-md-12 main">
				
				<table class="table" id="itemList">
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
		</div>
<script>
function itemManagerInit(){
  $("div.managerGrade").hide();
  $("#itemManager").show();
}
</script>