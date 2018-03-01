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
<title>Members</title>
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
<script type="text/javascript"
	src="<%=path%>/resources/scripts/datepicker.min.js?<%=jsVersion%>"></script>
<script language="javascript"
	src="<%=path%>/resources/scripts/util.js?<%=jsVersion%>"></script>
<script language="javascript"
	src="<%=path%>/resources/scripts/home.js?<%=jsVersion%>"></script>
<script type="text/JavaScript"
	src="<%=path%>/resources/scripts/responsive-calendar.js?<%=jsVersion%>"></script>
<script type="text/JavaScript"
	src="<%=path%>/resources/scripts/calendar.js?<%=jsVersion%>"></script>
<script type="text/JavaScript"
	src="<%=path%>/resources/scripts/ajax.js?<%=jsVersion%>"></script>
	
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=path%>/resources/bootstrap/css/bootstrap.css?<%=jsVersion%>">
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=path%>/resources/bootstrap/css/bootstrap-theme.css?<%=jsVersion%>" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=path%>/resources/css/bootstrap-formhelpers.css?<%=jsVersion%>">
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=path%>/resources/css/bussys.css?<%=jsVersion%>">

<link rel="stylesheet" type="text/css" media="screen"
	href="<%=path%>/resources/css/calendar/responsive-calendar.css?<%=jsVersion%>"">


<style>
.overlimit {
	color: red;
}
</style>


</head>

<script language="javascript">
$(document).ready(function () {
	var url = document.location.toString();
	if (url.match('#')) {
    	$('.nav-tabs a[href="#'+url.split('#')[1]+'"]').tab('show') ;
	} 
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
		<center>

			<div class="jumbotron1">
				</br>


				<div class="container-fluid">

					<ul class="nav nav-tabs">
						<li class="active"><a data-toggle="tab" href="#addUser">Create New User</a></li>
						<li><a data-toggle="tab" href="#updUserPref">Update User Preferences</a></li>
						<li><a data-toggle="tab" href="#updUserRole">Update User Roles</a></li>
						<li><a data-toggle="tab" href="#updUserStat">Update User Status</a></li>
						<li><a data-toggle="tab" href="#contacts">Contact Information</a></li>
					</ul>

					<div class="tab-content jumbotron">
						
						<div id="addUser" class="tab-pane fade in active">
						<form:form id="command" method="POST" action="member" >
							<h3>Add User</h3>
							<br>
							<div class="container">

								<div class="form-group form-inline row">
									<label for="createUsername" class="col-xs-2 text-right">Username</label>
									<form:input cssClass="form-control col-xs-2"
										id="createUsername" path="username"/> 
										<label for="createEmail" class="col-xs-2 text-right">Email</label> 
									<form:input cssClass="form-control col-xs-2" id="createEmail"
										path="email"/>
								</div>
								<!--end username and email-->


								<div class="form-group form-inline row">
									<label for="createPhone" class="col-xs-2 text-right">Phone Number</label> 
									<form:input cssClass="form-control col-xs-2"
										id="phoneNumber" path="phoneNumber"/>

									<label for="createCarrier" class="col-xs-2 text-right">Carrier</label>
									<form:select cssClass="form-control input-sm pull-left col-xs-2"
										id="createCarrier" path="carrier">
										<form:option value="" label="Pick A Carrier" disabled="true"/>
										<form:option value="" label="" />
										<form:options items="${carrierList}" itemValue="value"
											itemLabel="key" />
									</form:select>
								</div>
								<!-- end phone number -->

								<div class="form-group form-inline row">
									<label for="pass" class="col-xs-2 text-right" data-toggle="tooltip" data-placement="top"
										title="Password must contain at least 1 uppercase, 1 lowercase, and 1 number. It may not contain special characters.">
										<span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>
										Password
									</label> 
									<form:password cssClass="form-control col-xs-2" id="password"
										 path="password" /> 
									<label for="confirm" class="col-xs-2 text-right">Confirm</label> 
									<form:password cssClass="form-control col-xs-2" id="passwordConfirm"
										 path="passwordCnfm"/>
								</div>
								<!--end password and confirm-->

								</br>

								<div class="form-group form-inline row">
									<label for="createRole" class="col-xs-3 text-right"
										data-toggle="tooltip" data-placement="top"
										title="What security level should the user have access to use? This is unrelated to notification settings.">
										<span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>
										Application Role
									</label> 
									<form:select cssClass="form-control input-sm pull-left col-xs-2"
										id="createRole" path="appRole">
										<form:option value="" label="Pick A Role" disabled="true"/>
										<form:option value="" label="" />
										<form:options items="${memberRoleList}" itemValue="key"
											itemLabel="value" />
									</form:select> 
									<label for="createGroup" class="col-xs-3 text-right"
										data-toggle="tooltip" data-placement="top"
										title="What type of member is this user going to be? This is used for determining what events are visible.">
										<span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>
										Membership Role
									</label>
									<form:select cssClass="form-control input-sm pull-left col-xs-2"
										id="createGroup" path="memRole">
										<form:option value="" label="Pick A Type" disabled="true"/>
										<form:option value="" label="" />
										<form:options items="${memberTypeList}" itemValue="key"
											itemLabel="value" />
									</form:select> 
								</div>
								<div class="form-group form-inline row">
									<label for="notif" class="col-xs-3 text-right"
										data-toggle="tooltip" data-placement="top"
										title="The explorer must specify their preference explicitly for accountability. 'No Notifications' is only for system accounts.">
										<span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>
										Notification Preference
									</label> 
									<form:select cssClass="form-control input-sm pull-left col-xs-2"
										id="notif" path="notifyPref">
										<form:option value="" label="Pick A Type" disabled="true"/>
										<form:option value="" label="" />
										<form:options items="${notificationList}" itemValue="value"
											itemLabel="key" />
									</form:select> 
								</div>
								<!-- end dropdowns-->

								</br>
								<button class="btn btn-sm btn-default" type="submit">Create User</button>

							</div>
							<!-- end container -->
						</form:form>
						</div>
						<!-- end add user -->
						


						
						<div id="updUserPref" class="tab-pane fade">
						<form:form id="command" method="POST" action="member/preference" >
							<h3>Update Member Preferences</h3>
							<br>
							<div class="container">

								<div class="form-group form-inline row">
									<label for="updateUsername" class="col-sm-3 text-right">Username</label>
									<form:select cssClass="form-control input-sm pull-left col-xs-2"
										id="updateUsername" path="username" onchange='getUserPref()'>
										<form:option value="" label="Pick A User" disabled="true"/>
										<form:option value="" label="" />
										<form:options items="${userList}" itemValue="value" itemLabel="key" />
									</form:select>
								</div>
								<!--end username -->

								<div class="form-group form-inline row">
									<label for="updateEmail" class="col-sm-3 text-right">Email</label>


									<form:input cssClass="form-control col-xs-3"
										id="updateEmail" path="email"/>
								</div>
								<!--end email -->

								<div class="form-group form-inline row">
									<label for="updatePhone" class="col-sm-3 text-right">Phone Number</label> 
									<form:input cssClass="form-control col-xs-3"
										id="updatePhone" path="phoneNumber"/>

									<label for="updateCarrier" class="col-sm-3 text-right">Carrier</label>
									<form:select cssClass="form-control input-sm pull-left col-xs-2"
										id="updateCarrier" path="carrier">
										<form:option value="" label="Pick A Carrier" disabled="true"/>
										<form:option value="" label="" />
										<form:options items="${carrierList}" itemValue="value"
											itemLabel="key" />
									</form:select>
								</div>
								<!-- end phone number -->

								<div class="form-group form-inline row">
									<label for="updateNotif" class="col-sm-3 text-right">Notification
										Preference</label> 
									<form:select cssClass="form-control input-sm pull-left col-xs-2"
										id="updateNotif" path="notifyPref">
										<form:option value="" label="Pick A Type" disabled="true"/>
										<form:option value="" label="" />
										<form:options items="${notificationList}" itemValue="value"
											itemLabel="key" />
									</form:select>
								</div>
								<!-- end dropdowns-->

								</br>
								<button class="btn btn-default"type="submit">Update User</button>

							</div>
							<!-- end container -->
						</form:form>
						</div>
						<!-- end update user pref -->




						
						<div id="updUserRole" class="tab-pane fade">
						<form:form id="command" method="POST" action="member/role" >
							<h3>Update User Role</h3>
							<br>
							<div class="container">

								<div class="form-group form-inline row">
									<label for="roleUsername" class="col-sm-3 text-right">Username</label>
									<form:select cssClass="form-control input-sm pull-left col-xs-2"
										id="roleUsername" path="username" onchange='getUserRole()'>
										<form:option value="" label="Pick A User" disabled="true"/>
										<form:option value="" label="" />
										<form:options items="${userList}" itemValue="key"
											itemLabel="value" />
									</form:select>
								</div>
								<!--end username -->

								<div class="form-group form-inline row">
									<label for="updateRole" class="col-sm-3 text-right">Application
										Role</label> 
									<form:select cssClass="form-control input-sm pull-left col-xs-2"
										id="updateRole" path="appRole">
										<form:option value="" label="Pick A Role" disabled="true"/>
										<form:option value="" label="" />
										<form:options items="${memberRoleList}" itemValue="key"
											itemLabel="value" />
									</form:select> 
									 
								</div>
								<!-- end dropdowns-->

								<div class="form-group form-inline row">
									<label for="updateGroup" class="col-sm-3 text-right">Membership
										Role</label> 
									<form:select cssClass="form-control input-sm pull-left col-xs-2"
										id="updateGroup" path="memRole">
										<form:option value="" label="Pick A Type" disabled="true"/>
										<form:option value="" label="" />
										<form:options items="${memberTypeList}" itemValue="key"
											itemLabel="value" />
									</form:select>
								</div>
								<!-- end dropdowns-->

								</br>
								<button class="btn btn-default" type="submit" >Update User</button>

							</div>
							<!-- end container -->
						</form:form>
						</div>
						


						
						<div id="updUserStat" class="tab-pane fade">
						<form:form id="command" method="POST" action="member/status">
							<h3>Update User Status</h3>
							<br>
							<div class="container">

								<div class="form-group form-inline row">
									<label for="statusUsername" class="col-sm-3 text-right">Username</label>
									<form:select cssClass="form-control input-sm pull-left col-xs-2"
										id="statusUsername" path="username" onchange='getUserStatus()'>
										<form:option value="" label="Pick A User" disabled="true"/>
										<form:option value="" label="" />
										<form:options items="${userList}" itemValue="value"
											itemLabel="key" />
									</form:select>
								</div>
								<!--end username -->

								<div class="form-group form-inline row">
									<label for="updateStatus" class="col-sm-3 text-right"
										data-toggle="tooltip" data-placement="top"
										title="Setting to 'N' will suspend all notifications and the ability to login immediately.">
										<span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>
										Active Status
									</label> 
									<form:select cssClass="form-control input-sm pull-left col-sm-3"
										id="updateStatus" path="status">
										<form:option value=""></form:option>
										<form:option value="Y">Yes</form:option>
										<form:option value="N">No</form:option>
									</form:select>
								</div>
								<!-- end dropdowns-->

								</br>
								<button class="btn btn-default" type="submit">Update User</button>

							</div>
							<!-- end container -->
						</form:form>
						</div>
						
						<div id="contacts" class="tab-pane fade">
						<form id="command">
							<h3>Emergency Contact Information</h3>
							<br>
							<div class="container">
								<table id="emergencyContacts" class="table table-striped">
									<thead>
      									<tr class="row">
      										<th class="col-xs-2 text-center">Username</th>
      										<th class="col-xs-3 text-center">Name</th>
      										<th class="col-xs-1 text-center">Relation</th>
      										<th class="col-xs-3 text-center">Phone</th>
      										<th class="col-xs-3 text-center">Email</th>
      										<th class="col-xs-1 text-center">Last Updated</th>
      										<th class="col-xs-1 text-center">Request Update</th>
      									</tr>
      								</thead>
      								<tbody>
      									<c:forEach var="contact" items="${contactList}">
      										<tr class="row">
      											<td class="text-center">${contact.userId}</td>
      											<td class="text-center">${contact.firstName} ${contact.lastName}</td>
      											<td class="text-center">${contact.relationship}</td>
      											<td class="text-center">${contact.phone}</td>
      											<td class="text-center">${contact.email}</td>
      											<td class="text-center">${contact.lastUpdate}</td>
      											<td class="text-center">
      												<button type="button" id="upd${contact.userId}" class="btn btn-default btn-sm" 
      													onclick="requestContactUpdate('${contact.userId}');">
  														<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
													</button>
												</td>
      										</tr>
      									</c:forEach>
      								</tbody>
								</table>
							</div>
							<!-- end container -->
						</form>
						</div>
						
					</div>
				</div>



			</div>
			<!-- jumbotron -->



		</center>
	</div>

</body>
</html>
