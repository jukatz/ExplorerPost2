<%@ page contentType="text/html" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/spring.tld" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tld/spring-form.tld" prefix="form"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String jsVersion = (String)session.getAttribute("jsVersion");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>Reports</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<script type="text/javascript"
	src="<%=path%>/resources/scripts/en-US.js?<%=jsVersion%>"></script>
<script type="text/javascript"
	src="<%=path%>/resources/bootstrap/js/jquery-1.9.1.js?<%=jsVersion%>"></script>
<script type="text/javascript"
	src="<%=path%>/resources/bootstrap/js/bootstrap.js?<%=jsVersion%>"></script>
<script language="javascript"
	src="<%=path%>/resources/scripts/util.js?<%=jsVersion%>"></script>


<link rel="stylesheet" type="text/css" media="screen"
	href="<%=path%>/resources/bootstrap/css/bootstrap.css?<%=jsVersion%>">
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=path%>/resources/bootstrap/css/bootstrap-theme.css?<%=jsVersion%>" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=path%>/resources/css/bootstrap-formhelpers.css?<%=jsVersion%>">
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=path%>/resources/css/bussys.css?<%=jsVersion%>">


<style>
.overlimit {
	color: red;
}
</style>


</head>

<script type="text/javascript">
  	
      $(document).ready(function () {
                $('input[type="radio"]').click(function(){
                	var target = $(this).attr("value");
                	var level = $(this).attr("dir");
                	for(i=level; i<5; i++){
                		var clear =  parseInt(i)+1;
                		$('div[data-level="'+clear+'"]').hide();
                		$('input[type="radio" dir="'+clear+'"]').prop('checked', false);
                	}
                	$('div[data-show="'+target+'"]').show();
                });
      });

</script>


<body>
	<!-- MENU -->
	<%@ include file="/jsp/menu.jsp"%>

	<!-- MESSAGES -->
	<%@ include file="/jsp/showMessages.jsp"%>



	<script type="text/javascript"
		src="<%=path%>/scripts/bootstrap-formhelpers.js?<%=jsVersion%>"></script>

	<div class="screenBody">

		</br>
		<div class="jumbotron">
			<div class="container-fluid">

				<div id="report">
					<form:form id="command" method="POST" action="report">
						<h3 class="col-sm-offset-5">Report Builder</h3>
						<br>
						<div class="container">
							<div class="row" id="searchType" data-level="1"> <!-- start search type -->
								<div class="col-md-4">
									<label class="radio-inline">
										<form:radiobutton path="type" dir="1" value="Member"/>Show Member Information
									</label>
								</div>
								<div class="col-md-4">
									<label class="radio-inline">
										<form:radiobutton path="type" dir="1" value="Event"/>Show Event Information
									</label>
								</div>
								<div class="col-md-4">
									<label class="radio-inline">
										<form:radiobutton path="type" dir="1" value="Equipment"/>Show Equipment Information
									</label>
								</div>
							</div> <!-- end search type -->
							
							<!-- Level 2 -->
							</br>
							<div class="row" id="memberQuery" data-show="Member" data-level="2" hidden> <!-- start member search details -->
								<div class="col-md-3 col-md-offset-2">
									<label class="radio-inline">
										<form:radiobutton path="subType" dir="2" value="UserList"/>Show Training Stats
									</label>
								</div>
								<div class="col-md-3">
									<label class="radio-inline">
										<form:radiobutton path="subType" dir="2" value="UserList"/>Show Contact Information
									</label>
								</div>
								<div class="col-md-3">
									<label class="radio-inline">
										<form:radiobutton path="subType" dir="2" value="UserList"/>Show Online Activity
									</label>
								</div>
							</div> <!-- end member search details -->
							<div class="row" id="eventQuery" data-show="Event" data-level="2" hidden> <!-- start member search details -->
								<div class="col-md-3 col-md-offset-2">
									<label class="radio-inline">
										<form:radiobutton path="subType" dir="2" value="EventList"/>Show Member Attendance
									</label>
								</div>
								<div class="col-md-3">
									<label class="radio-inline">
										<form:radiobutton path="subType" dir="2" value="EventList"/>Show Event Information
									</label>
								</div>
								<div class="col-md-3">
									<label class="radio-inline">
										<form:radiobutton path="subType" dir="2" value="EventList"/>Show Total Hours
									</label>
								</div>
							</div> <!-- end event search details -->
							<div class="row" id="equipQuery" data-show="Equipment" data-level="2" hidden> <!-- start member search details -->
								<div class="col-md-3 col-md-offset-3">
									<label class="radio-inline">
										<form:radiobutton path="subType" dir="2" value="EquipList"/>Show Equipment By User
									</label>
								</div>
								<div class="col-md-3">
									<label class="radio-inline">
										<form:radiobutton path="subType" dir="2" value="EquipList"/>Show Equipment By Status
									</label>
								</div>
							</div> <!-- end event search details -->
							
							<!-- Level 3 -->
							</br>
							<div class="form-group form-inline row" id="eventList" data-show="EventList" data-level="3" hidden>
								<form:select cssClass="form-control input-sm pull-left col-sm-3 col-sm-offset-5" 
										id="selEvent" path="term">
									<form:option value="" label="Select An Event" disabled="true"/>
									<form:option value="" label="" />
									<form:options items="${eventList}" itemValue="key" itemLabel="value" />
								</form:select>
							</div><!-- end event list -->
							<div class="form-group form-inline row" id="userList" data-show="UserList" data-level="3" hidden>
								<form:select cssClass="form-control input-sm pull-left col-sm-3 col-sm-offset-5" 
										id="selEvent" path="term">
									<form:option value="" label="Select An User" disabled="true"/>
									<form:option value="" label="" />
									<form:options items="${userList}" itemValue="key" itemLabel="value" />
								</form:select>
							</div><!-- end user list -->
							<div class="form-group form-inline row" id="equipList" data-show="EquipList" data-level="3" hidden>
								<form:select cssClass="form-control input-sm pull-left col-sm-3 col-sm-offset-5" 
										id="selEvent" path="term">
									<form:option value="" label="Select Equipment" disabled="true"/>
									<form:option value="" label="" />
									<form:options items="${equipList}" itemValue="key" itemLabel="value" />
								</form:select>
							</div><!-- end equip list -->
							

							</br></br>
							<div class="form-group form-inline row">
								<button class="btn btn-default col-sm-offset-5" type="submit">Generate Report</button>
							</div>

						</div>
						<!-- end container -->
					</form:form>
				</div>
				<!-- end reports -->



			</div>
		</div>
	</div>

</body>
</html>
