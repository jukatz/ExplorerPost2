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
	
	<script type="text/javascript" src="<%=path%>/scripts/en-US.js?<%=jsVersion%>"></script>	
	<script type="text/javascript" src="<%=path%>/scripts/datepicker.min.js?<%=jsVersion%>"></script>	
	<script language="javascript" src="<%=path%>/scripts/util.js?<%=jsVersion%>"></script>
	<script language="javascript" src="<%=path%>/scripts/home.js?<%=jsVersion%>"></script>
	
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/bootstrap/css/bootstrap.css?<%=jsVersion%>">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/bootstrap-formhelpers.css?<%=jsVersion%>">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/bussys.css?<%=jsVersion%>">
	
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="<%=path%>/bootstrap/js/html5shiv.js?<%=jsVersion%>"></script>
      <script src="<%=path%>/bootstrap/js/respond.min.js?<%=jsVersion%>"></script>
    <![endif]-->
    
<style>
	.overlimit{color: red;}    
</style>
    
    
</head>

<script language="javascript">

</script>

<body>
	<!-- MENU -->
	<%@ include file="/jsp/header.jsp"%>
	
	<!-- MESSAGES -->
	<%@ include file="/jsp/showMessages.jsp"%>
	
				  	
	<script type="text/javascript" src="<%=path%>/bootstrap/js/jquery-1.9.1.js?<%=jsVersion%>"></script>	
	<script type="text/javascript" src="<%=path%>/bootstrap/js/bootstrap.js?<%=jsVersion%>"></script>	
	<script type="text/javascript" src="<%=path%>/scripts/bootstrap-formhelpers.js?<%=jsVersion%>"></script>	

	<div class="container well">
		Bootstrap has some nice alert bars which can be used to show an error. Different colors can be used depending on the importance / severity.
		Error messages are set using the store[messageType]Message methods of the SessionSvc. Simply include the showMessages.jsp after the header.jsp.
		The errors shown on this page are actually being set in the HomeController.java file in the processErrorsExample method.
	</div>
	
	
</body>
</html>
