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
<title>Reset Password</title>
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

<script language="javascript">

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

				<div id="resetUserPassword">
					<form:form id="command" method="POST" action="reset">
						<h3 class="col-sm-offset-4">Reset Member Password</h3>
						<br>
						<div class="container">

							<div class="form-group form-inline row">
								<label for="username"
									class="col-sm-3 col-sm-offset-2 text-right">Username</label>
								<form:select cssClass="form-control input-sm pull-left col-sm-4"
									id="username" path="username">
									<form:option value="" label="Pick A User" disabled="true" />
									<form:option value="" label="" />
									<form:options items="${userList}" itemValue="value"
										itemLabel="key" />
								</form:select>

							</div>
							<!--end username -->
							<div class="form-group form-inline row">
								<label for="password"
									class="col-sm-3 col-sm-offset-2 text-right">New
									Password</label>
								<form:password
									cssClass="form-control input-sm pull-left col-sm-4"
									id="password" path="password"/>
							</div>
							<div class="form-group form-inline row">
								<label for="confirm" class="col-sm-3 col-sm-offset-2 text-right">Confirm
									Password</label>
								<form:password
									cssClass="form-control input-sm pull-left col-sm-4"
									id="confirm" path="confirm"/>
							</div>


							</br>
							<div class="form-group form-inline row">
								<button class="btn btn-default col-sm-offset-5" type="submit">Update
									Password</button>
							</div>

						</div>
						<!-- end container -->
					</form:form>
				</div>
				<!-- end update user password -->



			</div>
		</div>
	</div>

</body>
</html>
