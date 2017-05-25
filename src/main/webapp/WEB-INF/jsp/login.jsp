<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- 项目路径 -->
<% String basePath = request.getScheme()+"://"+
		request.getServerName()+":"+
		request.getServerPort()+request.getContextPath()+"/";%>

<!-- 基链接标记:它只能应用于标记head内。 
你网页上的所有相对路径在链接时都将在前面加上基链接指向的地址。 -->
<base href="<%=basePath%>">

<style type="text/css">
.welcome{
	float:right;
	margin-bottom:10px;
	margin-right:20px;
}
</style>
<%@ include file="head.html"%>
<!-- <script type="text/javascript" src="js/main.js"></script> -->
<script type="text/javascript">
function recycle_bin(title){
console.log(title);
}
</script>
<title>Insert title here</title>
</head>
<body class="easyui-layout">
    <div data-options="region:'north'" style="height:18%;background:url('easyui/themse/images/head1.jpg');" >
    	<!-- <img style="width:100%;height:100%" src="easyui/themse/images/head1.jpg"> -->
    	<p class="welcome">欢迎您，${user.username }!<a href="login" style="margin-left: 20px;">退出</a></p>
    </div>   
    <div data-options="region:'south'" style="height:10%;">
    	<center style="color:#666;">Copyright © 2013-2017 日美开开  hzrmkk.com All Rights Reserved. 备案号：沪ICP备xxxxxxxx</center>
    </div>   
    <div data-options="region:'west',title:'功能导航',split:true" style="width:200px;" class="easyui-accordion">
	    <div title="我的任务" data-options="iconCls:'icon-renwu'" style="overflow:auto;padding:10px;">   
	        <a href="#" class="myui-menu">待处理</a><br>
	        <a href="#" class="myui-menu">进行中</a><br>
	        <a href="#" class="myui-menu">已完成</a><br>
	        <a href="#" class="myui-menu">消息</a>
	    </div>   
	    <div title="申请用印" data-options="iconCls:'icon-shenqing',selected:true" style="padding:10px;">   
	        <a href="into" class="myui-menu">发起申请</a><br>
	        <a href="#" class="myui-menu">我的草稿</a><br>
	        <a href="javascript:recycle_bin(this.value);" class="myui-menu">回收站</a>   
	    </div>   
	    <div title="系统管理">   
	        <a href="#" class="myui-menu">角色管理</a><br>
	        <a href="#" class="myui-menu">机构管理</a><br>
	        <a href="#" class="myui-menu">人员管理</a><br>
	        <a href="#" class="myui-menu">日志管理</a><br>
	        <a href="#" class="myui-menu">参数管理</a>    
	    </div>   
	    <div title="流程管理">   
	        <a href="#" class="myui-menu">刻制流程</a><br>
	        <a href="#" class="myui-menu">登记流程</a><br>
	        <a href="#" class="myui-menu">启用流程</a><br>
	        <a href="#" class="myui-menu">申领流程</a><br>
	        <a href="#" class="myui-menu">使用流程</a><br>
	        <a href="#" class="myui-menu">交接流程</a><br>
	        <a href="#" class="myui-menu">停用流程</a><br>
	        <a href="#" class="myui-menu">上缴流程</a><br>
	        <a href="#" class="myui-menu">废止流程</a><br>
	        <a href="#" class="myui-menu">销毁流程</a><br>
	        <a href="#" class="myui-menu">归档流程</a>    
	    </div>   
	    <div title="印章管理">   
	        <a href="#" class="myui-menu">实物印章管理</a><br>
	        <a href="#" class="myui-menu">电子印章管理</a>    
	    </div>   
	    <div title="印控机管理">   
	        <a href="#" class="myui-menu">增加印控仪</a><br>
	        <a href="#" class="myui-menu">设置印控仪</a><br>
	        <a href="#" class="myui-menu">参数删除印控仪</a>    
	    </div>   
	    <div title="监测中心">   
	        <a href="#" class="myui-menu">用印监测配置</a><br>
	        <a href="#" class="myui-menu">印章监测配置</a><br>
	        <a href="#" class="myui-menu">设备监测配置</a>    
	    </div>   
	    <div title="信息中心">   
	        <a href="#" class="myui-menu">信息查看</a><br>
	        <a href="#" class="myui-menu">信息统计</a>    
	    </div>
    </div>   
    <div id="mainbody" data-options="region:'center',iconCls:'icon-reload',title:'center title'" style="padding:5px;background:#eee;">
    	<center><%@ include file="apply_of.jsp" %></center>
    </div>   
</body> 
</html>