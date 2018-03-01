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
<title>Equipment</title>
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

</script>

<body>
	<!-- MENU -->
	<%@ include file="/jsp/menu.jsp"%>

	<!-- MESSAGES -->
	<%@ include file="/jsp/showMessages.jsp"%>
	
	<!-- event details modal -->
	<%@ include file="/jsp/modals.jsp"%>



	<script type="text/javascript"
		src="<%=path%>/scripts/bootstrap-formhelpers.js?<%=jsVersion%>"></script>

	<div class="screenBody">
		<center>

			<div class="jumbotron1">
				</br>


				<div class="container-fluid">

					<ul class="nav nav-tabs">
						<li class="active"><a data-toggle="tab" href="#addEquip">Create New Equipment</a></li>
						<li><a data-toggle="tab" href="#assignEquip">Assign and Un-Assign</a></li>
						<li><a data-toggle="tab" href="#updEquip">Update Equipment Information</a></li>
					</ul>

					<div class="tab-content jumbotron">
						
						<div id="addEquip" class="tab-pane fade in active">
						<form:form id="command" method="POST" action="equipment" >
							<h3>Add Equipment</h3>
							<br>
							<div class="container">
								<div class="form-group form-inline row">
									<label for="equipId" class="col-sm-2 text-right">Equipment Id</label> 
									<form:input cssClass="form-control col-sm-2" path="equipId"
										id="equipId" /> 
									<label for="equipType" class="col-sm-1 text-right">Type</label>
									<form:select cssClass="form-control input-sm pull-left col-sm-2"
										id="equipType" path="equipType">
										<form:option value="" label="" />
										<form:option value="Personal">Personal (Take Home)</form:option>
										<form:option value="Post">Post (Returned Daily)</form:option>
									</form:select> 
									<label for="equipStatus" class="col-sm-1 text-right">Status</label>	
									<form:select cssClass="form-control input-sm pull-left col-sm-2" 
										id="equipStatus" path="status">
										<form:option value="" label="" />
										<form:options items="${statusList}" itemValue="key" itemLabel="value" />
									</form:select>
								</div><!-- end row 1 -->
								
								<div class="form-group form-inline row">
									<label for="equipBrand" class="col-sm-2 text-right">Brand</label> 
									<form:input cssClass="form-control col-sm-2" path="brand" id="equipBrand" /> 
									<label for="equipModel" class="col-sm-2 text-right">Model</label> 
									<form:input cssClass="form-control col-sm-2" path="model" id="equipModel" />
								
								</div><!-- end row 2 -->
								</br>
								<button class="btn btn-sm btn-default" type="submit">Create Equipment</button>

							</div>
							<!-- end container -->
						</form:form>
						</div>
						<!-- end add equipment -->
						


						
						<div id="assignEquip" class="tab-pane fade">
						<form:form id="command" method="POST" action="equipment/assign" >
							<h3>Assign or Un-Assign Equipment</h3>
							<br>
							<div class="container">
								<div class="form-group form-inline row">
									<label for="equipIdAssign" class="col-sm-3 col-sm-offset-1 text-right">Equipment Id</label> 
									<form:select cssClass="form-control input-sm pull-left col-sm-3" 
										id="equipIdAssign" path="serial" onchange="selEquipAssign(this.options[this.selectedIndex].value)">
										<form:option value="" label="" />
										<form:options items="${equipList}" itemValue="key" itemLabel="value" />
									</form:select> 
									<label for="equipMemberAssign" class="col-sm-3 text-right">Member</label>	
									<form:select cssClass="form-control input-sm pull-left col-sm-3" 
										id="equipMemberAssign" path="userId">
										<form:options items="${userList}" itemValue="key" itemLabel="value" />
									</form:select>
								
								</div><!-- end row 2 -->
								

								</br>
								<button class="btn btn-default"type="submit">Assign or Un-Assign</button>

							</div>
							<!-- end container -->
						</form:form>
						</div>
						<!-- end assign or unassign -->




						
						<div id="updEquip" class="tab-pane fade">
						<form:form id="command" method="POST" action="equipment/information" >
							<h3>Update Equipment</h3>
							<br>
							<div class="container">
								<div class="form-group form-inline row">
									<label for="equipIdUpd" class="col-sm-2 col-sm-offset-4 text-right">Equipment Id</label> 
									<form:select cssClass="form-control input-sm pull-left col-sm-3" 
										id="equipIdUpd" path="serial" onchange="selEquipInfo(this.options[this.selectedIndex].value)">
										<form:option value="" label="" />
										<form:options items="${equipList}" itemValue="key" itemLabel="value" />
									</form:select> 
								</div>
								</br>
								<div class="form-group form-inline row"> 
									<label for="equipTypeUpd" class="col-sm-2 col-sm-offset-1 text-right">Type</label>
									<form:select cssClass="form-control input-sm pull-left col-sm-2"
										id="equipTypeUpd" path="equipType">
										<form:option value="" label="" />
										<form:option value="Personal">Personal (Take Home)</form:option>
										<form:option value="Post">Post (Returned Daily)</form:option>
									</form:select> 
									<label for="equipStatusUpd" class="col-sm-2 text-right">Status</label>	
									<form:select cssClass="form-control input-sm pull-left col-sm-2" 
										id="equipStatusUpd" path="status">
										<form:option value="" label="" />
										<form:options items="${statusList}" itemValue="key" itemLabel="value" />
									</form:select>
								</div><!-- end row 1 -->
								
								<div class="form-group form-inline row">
									<label for="equipBrandUpd" class="col-sm-2 col-sm-offset-1 text-right">Brand</label> 
									<form:input cssClass="form-control col-sm-2" path="brand" id="equipBrandUpd" /> 
									<label for="equipModelupd" class="col-sm-2 text-right">Model</label> 
									<form:input cssClass="form-control col-sm-2" path="model" id="equipModelUpd" />
								
								</div><!-- end row 2 -->
								</br>
								<button class="btn btn-sm btn-default" type="submit">Update Equipment</button>

							</div>
							<!-- end container -->
						</form:form>
						</div>
						<!-- end add equipment -->
						
						
					</div>
				</div>



			</div>
			<!-- jumbotron -->



		</center>
	</div>

</body>
</html>
