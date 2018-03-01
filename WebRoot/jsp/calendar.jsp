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
	<title>Calendar Events</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">	
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/resources/css/loading.css?<%=jsVersion%>">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/resources/bootstrap/css/bootstrap.css?<%=jsVersion%>">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/resources/bootstrap/css/bootstrap-theme.css?<%=jsVersion%>" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/resources/css/bootstrap-formhelpers.css?<%=jsVersion%>">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/resources/css/bussys.css?<%=jsVersion%>">
	
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/resources/css/calendar/responsive-calendar.css?<%=jsVersion%>"">
	
	<script type="text/javascript" src="<%=path%>/resources/scripts/en-US.js?<%=jsVersion%>"></script>
	<script type="text/javascript" src="<%=path%>/resources/bootstrap/js/jquery-1.9.1.js?<%=jsVersion%>"></script>	
	<script type="text/javascript" src="<%=path%>/resources/bootstrap/js/bootstrap.js?<%=jsVersion%>"></script>	
	<script type="text/javascript" src="<%=path%>/resources/scripts/datepicker.min.js?<%=jsVersion%>"></script>	
	<script language="javascript" src="<%=path%>/resources/scripts/util.js?<%=jsVersion%>"></script>
	<script language="javascript" src="<%=path%>/resources/scripts/home.js?<%=jsVersion%>"></script>
	<script language="javascript" src="<%=path%>/resources/scripts/ajax.js?<%=jsVersion%>"></script>
	<script type="text/JavaScript" src="<%=path%>/resources/scripts/jquery.maskedinput.js?<%=jsVersion%>"></script>
	<script type="text/JavaScript" src="<%=path%>/resources/scripts/responsive-calendar.js?<%=jsVersion%>"></script>
	<script type="text/JavaScript" src="<%=path%>/resources/scripts/calendar.js?<%=jsVersion%>"></script>
	

	
    
<style>
	.overlimit{color: red;}    
</style>
    
    
</head>

<script language="javascript">

</script>

<body>
	<!-- MENU -->
	<%@ include file="/jsp/menu.jsp"%>
	
	<!-- MESSAGES -->
	<%@ include file="/jsp/showMessages.jsp"%>
	
	<!-- Emergency Contact -->
	<%@ include file="/jsp/contact.jsp"%>
	
	<!-- event details modal -->
	<%@ include file="/jsp/modals.jsp"%>
	
				  	
		
	<script type="text/javascript" src="<%=path%>/scripts/bootstrap-formhelpers.js?<%=jsVersion%>"></script>	

	<div class="screenBody">
<center>

<h1>My Calendar Events</h1>

    <div class="container-fluid">

      <!-- Responsive calendar - START -->
    	<div class="responsive-calendar">
        <div class="controls">
            <a class="pull-left" data-go="prev"><div class="btn btn-primary">Prev</div></a>
            <h4>
                     <span class="head-month" id="head-month" data-head-month></span>
                     <span class="head-year" id="head-year" data-head-year></span> 
                     <span class="hidden" id="head-id">${command.username}</span>
           </h4>
            <a class="pull-right" data-go="next"><div class="btn btn-primary">Next</div></a>
        </div><hr/>
        <div class="day-headers">
          <div class="day header">Sun</div>
          <div class="day header">Mon</div>
          <div class="day header">Tue</div>
          <div class="day header">Wed</div>
          <div class="day header">Thu</div>
          <div class="day header">Fri</div>
          <div class="day header">Sat</div>
        </div>
        <div class="days" data-group="days">
          
        </div>
      </div>
      <!-- Responsive calendar - END -->
    </div>

<br>


</center>
</div>

<script type="text/javascript">
      $(document).ready(function () {
          init_Calendar( );

      });
</script>
	
</body>
</html>
