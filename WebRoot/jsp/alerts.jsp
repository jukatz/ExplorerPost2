<%@ page contentType="text/html" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/spring.tld" prefix="spring" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String jsVersion = (String)session.getAttribute("jsVersion");

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
	<title>Business Systems App Template</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">	
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	
	<script type="text/javascript" src="<%=path%>/resources/scripts/en-US.js"></script>	
	<script type="text/javascript" src="<%=path%>/resources/scripts/datepicker.min.js"></script>	
	<script language="javascript" src="<%=path%>/resources/scripts/util.js?<%=jsVersion%>"></script>
	<script language="javascript" src="<%=path%>/resources/scripts/home.js?<%=jsVersion%>"></script>
	<script language="javascript" src="<%=path%>/resources/scripts/alerts.js?<%=jsVersion%>"></script>
	
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/resources/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/resources/css/bootstrap-formhelpers.css">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/resources/css/bussys.css">
	
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="<%=path%>/bootstrap/js/html5shiv.js"></script>
      <script src="<%=path%>/bootstrap/js/respond.min.js"></script>
    <![endif]-->
    
<style>
	.overlimit{color: red;}    
</style>
    
    
</head>

<script language="javascript">

	function init()
	{
		$(function () {
		    $("[data-toggle='tooltip']").tooltip();
		});      
		
		$(function () {
		    $("[data-toggle='popover']").popover();
		});
	}
	


</script>

<body onload="init();">
	<!-- MENU -->
	<%@ include file="/jsp/header.jsp"%>
	
	<!-- MESSAGES -->
	<%@ include file="/jsp/showMessages.jsp"%>
	
	<script type="text/javascript" src="<%=path%>/resources/bootstrap/js/jquery-1.9.1.js"></script>	
	<script type="text/javascript" src="<%=path%>/resources/bootstrap/js/bootstrap.js"></script>	
	<script type="text/javascript" src="<%=path%>/resources/scripts/bootstrap-formhelpers.js"></script>	
	<script type="text/javascript" src="<%=path%>/resources/scripts/bootbox.min.js"></script>
	

	<div class="container well">
		<div class="form-group row">
			<div class="col-sm-10">
			These alert boxes are generated using a bootstrap-friendly javascript plugin called "bootbox". A note about the alert boxes.
			Default browser alert boxes are synchronous meaning that all javascript code flow and processing halts until the user performs
			and action with the alert box. These custom boxes are asynchronous meaning that all javascript processing will continue even when
			invoked. For this reason the alert boxes require a callback handler. This can be done in a separate function or inline as is
			demonstrated in the examples.
			</div>
			
		</div>
	
		<div class="form-group row">
			<div class="col-sm-1">
				<button data-toggle="tooltip" title="Test Alert" id="btnAlert" type="button" class="btn btn-success" onclick="alertExample();">Alert</button><br>			
			</div>
		    <div class="col-sm-5 help-block">
			    Click to try out the alert box
		    </div>
			
		</div>
		<div class="form-group row">
			<div class="col-sm-1">
				<button data-toggle="tooltip" title="Test Confirm" id="btnConfirm" type="button" class="btn btn-success" onclick="confirmExample();">Confirm</button>
			</div>
		    <div class="col-sm-3 help-block">
			    Click to try out the confirm box
		    </div>
		    <div class="col-sm-5 help-block">
			    <span id="spanConfirmResult"></span>
		    </div>
		</div>
		<div class="form-group row">
			<div class="col-sm-1">
				<button data-toggle="tooltip" title="Test Prompt" id="btnPrompt" type="button" class="btn btn-success" onclick="promptExample();">Prompt</button>
			</div>
		    <div class="col-sm-3 help-block">
			    Click to try out the prompt box
		    </div>
		    <div class="col-sm-5 help-block">
			    <span id="spanPromptResult"></span>
		    </div>
		</div>
		<div class="form-group row">
			<div class="col-sm-2">
				<button data-toggle="tooltip" title="Test Prompt With Default" id="btnPromptDefault" type="button" class="btn btn-success" onclick="promptDefaultExample();">Prompt With Default</button>
			</div>
		    <div class="col-sm-3 help-block">
			    Click to try out the prompt box
		    </div>
		    <div class="col-sm-5 help-block">
			    <span id="spanPromptDefaultResult"></span>
		    </div>
		</div>
	</div>
	
	
</body>
</html>
