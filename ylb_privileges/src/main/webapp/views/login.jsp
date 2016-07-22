<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户权限管理</title>
<style type="text/css">
	body{margin-left: 0px;margin-top: 0px;margin-right: 0px;margin-bottom: 0px;background-color: #FFFFF0;}
	.header{width:100%;height:41px;background: url(images/login-top-bg.gif) repeat-x;}
	.login_left{float:left;width:800px;height:100%; no-repeat;}
	.login_title{margin-left:565px;font-family: Arial, Helvetica, sans-serif;font-size: 14px;height:36px;line-height: 36px;color: #666666;font-weight: bold;}
	.login_info{margin-left:565px;font-family: Arial, Helvetica, sans-serif;font-size: 12px;height:36px;line-height: 36px;color: #333333;}
	.login_input{width:150px;height:20px;margin-left:30px;border:1px solid #7F9DB9;vertical-align: middle;}
	.login_info img{vertical-align: middle;cursor: pointer;}
	
	.errInfo{display:none;color:red;}
	
</style>
<script type="text/javascript" src="js/jquery-1.5.1.min.js"></script>
</head>
<body>
<div style="width:100%;height:645px;position: absolute;top:50%;left:50%;margin-top:-320px;margin-left:-50%;">
		<div class="login_left">
			<div style="width:100%;height:auto;margin-top:150px;">
			<form action="login.do" method="post" name="loginForm" onsubmit="return check();">
				<div class="login_title">
					管理员登录
				</div>
				<div class="login_info">
					<label>用户名：</label><input type="text" name="loginname" id="loginname" class="login_input" value="admin" />
					&nbsp;<span id="nameerr" class="errInfo"></span>
				</div>
				<div class="login_info">
					<label>密　码：</label><input type="password" name="password" id="password" class="login_input" value="1"/>
					&nbsp;<span id="pwderr" class="errInfo"></span>
				</div>
				<div class="login_info">
					<input type="submit" name="loginBtn" value="登录" />
					<input type="reset" name="cancelBtn" value="取消" />
				</div>
			</form>
			</div>
		</div>
</div>
	<script type="text/javascript">
		var errInfo = "${errInfo}";
		$(document).ready(function(){
			if(errInfo!=""){
					alert(errInfo);
			}
			$("#loginname").focus();
		});
	
	
		
		function resetErr(){
			$("#nameerr").hide();
			$("#nameerr").html("");
			$("#pwderr").hide();
			$("#pwderr").html("");
		}
		
		function check(){
			resetErr();
			if($("#loginname").val()==""){
				alert("用户名不得为空！");
				$("#loginname").focus();
				return false;
			}
			if($("#password").val()==""){
				alert("密码不得为空！");
				$("#password").focus();
				return false;
			}
			return true;
		}
	</script>
</body>
</html>