<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/require/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no"/>
<!-- css start -->
<!-- <link rel="shortcut icon" href="../favicon.ico" type="image/x-icon"/> -->
<link rel="stylesheet" type="text/css" href="../javascripts/thirdpart/easyui142/themes/default/easyui.css"/>
<link rel="stylesheet" type="text/css" href="../javascripts/thirdpart/easyui142/themes/icon.css"/>
<link rel="stylesheet" type="text/css" href="../themes/defualt/main.css"/>
<style type="text/css">
	.l-page-top{ height:60px; background:#f8f8f8; margin-bottom:0px;}
	html{
		padding:0px;
		height:100%;
	}
	body{
		padding:0px;
		height:100%;
		margin:0px;
	}
	body{
		scrollbar-3dlight-color:#D4D0C8; /*- 最外左 -*/  
		scrollbar-highlight-color:#fff; /*- 左二 -*/  
		scrollbar-face-color:#E4E4E4; /*- 面子 -*/  
		scrollbar-arrow-color:#666; /*- 箭头 -*/  
		scrollbar-shadow-color:#808080; /*- 右二 -*/  
		scrollbar-darkshadow-color:#D7DCE0; /*- 右一 -*/  
		scrollbar-base-color:#D7DCE0; /*- 基色 -*/  
		scrollbar-track-color:#;/*- 滑道 -*/  
	}
</style>
<!-- css stop -->
<!-- script start -->
<!-- jquery -->
<script type="text/javascript" src="../javascripts/jquery.min.js"></script>
<!-- easy ui -->
<script type="text/javascript" src="../javascripts/thirdpart/easyui142/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../javascripts/thirdpart/easyui142/locale/easyui-lang-zh_CN.js"></script>
<!-- 自定义公共js -->
<script type="text/javascript" src="../javascripts/admin/common.js"></script>
<script type="text/javascript">
	//tabs页面建立
function tabsManager(e,params)
{
	var  currentTarget=e.currentTarget;//绑定点击事件的元素
	var title=$(currentTarget).linkbutton("options").text;
	var tab=$("#main").tabs('getTab',title);//根据标题判断tab是否存在
	if(tab)
	{
		$("#main").tabs('select',title);//刷新tab内容
	}else{
		//添加新tab
		$("#main").tabs('add',{
			id: params.id || "",
			title: title,
			content : createFrame(params.url),
			closable: true,
			selected: true
		});
	}
}
</script>
<!-- script stop -->
<title>后台管理</title>
</head>
<body scrolling="auto" style="overflow:hidden;">
	<div id="cc" class="easyui-layout" style="width:100%;height:100%;padding:0px;margin:0px;overflow:hidden;">
	    <div id="north" data-options="region:'north',split:false" style="height:60px;">
	    	<div id="topcontent" class="banner">
	    		<div id="test"></div>
	    		<span class="title">后台管理</span>
	    		<div id="userInfo" class="userInfo"> 
	    			<table style="right: 5px; bottom: 3px; color: rgb(255, 255, 255); position: absolute;">
						<tr>
							<td><span>欢迎:&nbsp;</span><span>测试人员</span></td>
							<td><a class="update_password">修改密码</a></td>
							<td><a class="logout" href="logout.htm">退出</a></td>
						</tr>
					</table>
	    		</div>
	    	</div>
	    </div>
	    <div id="west" data-options="region:'west',title:'配置导航',split:true" style="width:180px;">
	    	<div id="acrd" class="easyui-accordion" data-options="fit:true,border:false"> 
	    		<div title="基础管理" data-options="selected:true" style="padding:10px;">   
			        <a href="#" class="easyui-linkbutton c"  data-options="text:'用户管理'"  style="width:100%;margin-bottom:5px;" onclick="tabsManager(event,{id:'userManager',url:'index.htm'})">用户管理</a>  
			        <a href="#" class="easyui-linkbutton c"  data-options="text:'部门管理'"  style="width:100%;margin-bottom:5px;" onclick="tabsManager(event,{id:'depManager',url:'login.html'})">部门管理</a> 
			        <a href="#" class="easyui-linkbutton c"  data-options="text:'角色管理'"  style="width:100%;margin-bottom:5px;" onclick="tabsManager(event,{id:'roleManager',url:'index.htm'})">角色管理</a>
			    </div>
			    <div title="子系统管理" data-options="selected:false" style="padding:5px;">   
			        <a href="#" class="easyui-linkbutton c"  data-options="text:'子系统定义'"  style="width:100%;margin-bottom:5px;" onclick="tabsManager(event,{id:'sebSysManager',url:'index.htm'})">子系统定义</a>
			    </div>
			    <div title="公共管理" data-options="selected:false" style="padding:5px;">   
			        <a href="#" class="easyui-linkbutton c"  data-options="text:'视图定义'"  style="width:100%;margin-bottom:5px;" onclick="tabsManager(event,{id:'viewManager',url:'index.htm'})">视图定义</a>  
			        <a href="#" class="easyui-linkbutton c"  data-options="text:'数据字典'"  style="width:100%;margin-bottom:5px;" onclick="tabsManager(event,{id:'dicManager',url:'index.htm'})">数据字典</a> 
			        <a href="#" class="easyui-linkbutton c"  data-options="text:'系统组件管理'"  style="width:100%;margin-bottom:5px;" onclick="tabsManager(event,{id:'componentManager',url:'index.htm'})">系统组件管理</a>
			    </div>
	    	</div>
	    </div>
	    <div id="center" data-options="region:'center'," style="background:#eee;overflow:hidden;">
	    	<div id="main" class="easyui-tabs" data-options="border:false" style="width:100%;height:100%;overflow:hidden;">
			</div>
	    </div>
	</div>
</body>
</html>