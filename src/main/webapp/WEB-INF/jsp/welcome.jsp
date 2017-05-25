<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎登陆印控仪操作管理系统</title>
<%@ include file="head.html"%>
<script type="text/javascript">
$(function(){
	$('#login-div').panel({   
		  width:500,    
		  height:150,    
		  title: '登陆', 
		  tools: [{    
		    iconCls:'icon-add',    
		    handler:regist  
		  },{    
		    iconCls:'icon-edit',    
		    handler:function(){$("#login-form")[0].reset();}    
		  }]    
		}); 
	$('#register-div').panel({   
		  width:500,    
		  height:200,    
		  title: '注册', 
		  tools: [{    
		    iconCls:'icon-add',    
		    handler:function(){$("#register-form")[0].reset();}
		  },{    
		    iconCls:'icon-edit',    
		    handler:login  
		  }]    
		});
	$('.username').textbox({    
	    iconCls:'icon-man', 
	    iconAlign:'right'       
	})
	$('.password').textbox({ 
	    iconCls:'icon-lock', 
	    iconAlign:'right'       
	})
})
function regist(){
	$("#register-form")[0].reset();
	$("#login").css("display","none");
	$("#register").css("display","block");
}
function login(){
	$("#login-form")[0].reset();
	$("#register").css("display","none");
	$("#login").css("display","block");
}
/*检查新用户是否合法*/
function checkAccount(){
	var first=$("#pwd1").val();
	var again=$("#pwd2").val();
	if(first===again){
		$.ajax({
			url:"user/checknew",
			data:{"userid":$("#name").val()},
			dataType:"json",
			success:function(data){
				return true;
			},
			error:function(){
				return false;
			}
		})
	}
	return false;
}
</script>
</head>
<body>
<center>
	
	<div id="login">
	<div id="login-div" style="text-align:center;">
		<form id="login-form" action="into" method="post" class="p30"><br>
		   <span>账 号：<input class="username" name="username" type="text" style="width:300px"></span><br><br>
		   <span>密 码：<input class="password" name="password" type="password" style="width:300px"></span><br><br>
		   <button type="submit">登陆</button>&nbsp;&nbsp;&nbsp;&nbsp;<button type="reset">重置</button>
		   <span style="color:red;">${msg}</span>
		</form>
	</div>
	</div>
	<div id="register" style="display:none;">
	<div id="register-div" style="text-align:center;">
		<form id="register-form" action="user/register" method="post" class="p30"><br>  
		   <span>&nbsp;账&nbsp;&nbsp;号&nbsp;：<input class="username" name="userid" id="name" type="text" style="width:300px"></span><br><br>
		   <span>&nbsp;密&nbsp;&nbsp;码&nbsp;：<input class="password" id="pwd1" name="password" type="password" style="width:300px"></span><br><br>
		   <span>确认密码：<input class="password" type="password" id="pwd2" style="width:300px"></span><br><br>
		   <button type="submit" onclick="return checkAccount();">注册</button>&nbsp;&nbsp;&nbsp;&nbsp;<button type="reset">重置</button>
		</form>
	</div>
	</div>
</center>
</body>
</html>