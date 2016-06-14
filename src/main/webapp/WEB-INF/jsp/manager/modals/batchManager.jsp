<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="row managerGrade" style="display:none" id="batchManager">
			<div class="col-sm-12 col-md-12 main">
				
				<table class="table" id="batchList">
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
		</div>
<script>
function batchManagerInit(){
  $("div.managerGrade").hide();
  $("#batchManager").show();
}
</script>