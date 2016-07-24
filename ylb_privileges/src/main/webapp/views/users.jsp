<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My Test</title>
<link type="text/css" rel="stylesheet" href="css/main.css"/>
</head>
<body>
	<form action="user.do" method="post" name="userForm" id="userForm">
	<div class="search_div">
		用户名：<input type="text" name="loginname" value="${user.loginname }"/>
		<a href="javascript:search();" class="myBtn"><em>查询</em></a>
	</div>
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="main_table">
		<tr class="main_head">
			<th>序号</th>
			<th>用户名</th>
			<th>名称</th>
			<th>角色</th>
			<th>操作</th>
		</tr>
		<c:choose>
			<c:when test="${not empty userList}">
				<c:forEach items="${userList}" var="user" varStatus="vs">
				<tr class="main_info">
				<td>${vs.index+1}</td>
				<td>${user.loginname }</td>
				<td>${user.username }</td>
				<td>${user.rolename }</td>
				<td><a href="javascript:editUser(${user.userId });">修改</a> | <a href="javascript:delUser(${user.userId });">删除</a></td>
				</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr class="main_info">
					<td colspan="7">没有相关数据</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	<div class="page_and_btn">
		<div>
			<a href="javascript:addUser();" class="myBtn"><em>新增</em></a>
		</div>
	</div>
	</form>
	<script type="text/javascript" src="js/jquery-1.5.1.min.js"></script>
	<script type="text/javascript" src="js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$(".main_info:even").addClass("main_table_even");
		});
		
		
		function addUser(){
			var dg = new $.dialog({
				title:'新增用户',
				id:'user_new',
				width:330,
				height:300,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				xButton:true,
				resize:false,
				page:'user/add.do'
				});
    		dg.ShowDialog();
		}
		
		function editUser(userId){
			var dg = new $.dialog({
				title:'修改用户',
				id:'user_edit',
				width:330,
				height:300,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				resize:false,
				page:'user/edit.do?userId='+userId
				});
    		dg.ShowDialog();
		}
		
		function delUser(userId){
			if(confirm("确定要删除该记录？")){
				var url = "user/delete.do?userId="+userId;
				$.get(url,function(data){
					if(data=="success"){
						document.location.reload();
					}
				});
			}
		}
		
		
		function search(){
			$("#userForm").submit();
		}
		
	</script>
</body>
</html>