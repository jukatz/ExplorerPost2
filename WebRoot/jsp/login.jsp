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
	<title>Explorer Login</title>
	<link rel="stylesheet" href="<%=path%>/resources/bootstrap/css/bootstrap.css?<%=jsVersion%>" />
	<link rel="stylesheet" href="<%=path%>/resources/bootstrap/css/bootstrap-theme.css?<%=jsVersion%>" />
	<link rel="stylesheet" href="<%=path%>/resources/css/loading.css?<%=jsVersion%>">
	<script type="text/JavaScript" src="<%=path%>/resources/bootstrap/js/jquery-1.9.1.js?<%=jsVersion%>"></script>
	<script type="text/JavaScript" src="<%=path%>/resources/bootstrap/js/bootstrap.js?<%=jsVersion%>"></script>
	<script type="text/javascript" src="<%=path%>/resources/scripts/util.js?<%=jsVersion%>"></script>
	
	<script type="text/javascript">
  	
      	$(document).ready(function () {
      	
     		$( "#login" ).click(function() {
             	myApp.showPleaseWait();
			});
          
      	});

	</script>
	
	
</head>

<body class="container">

	<div class="container">
		</br> </br>
		<div class="jumbotron col-xs-6 col-xs-offset-3">
			<div class="container-fluid">
				<center>

					<legend id="legend" class="standard_legend">Explorer Login</legend>

					<form:form modelAttribute="command" action="login" method="POST" id="command">
						<div class="form-group">
							<span class="text-danger">${command.message}</span>
							<div class="row">
								<div class="col-xs-8 col-xs-offset-2">
									<form:input id="username" path="username" cssClass="form-control" />
								</div>
							</div>
							<div class=row" ><br/></div>
							<div class="row">
								<div class="col-xs-8 col-xs-offset-2">
									<form:password path="password" cssClass="form-control" id="password"/>
								</div>
							</div>
							</br>
							<div class="row">
								<div class="col-sm-4 col-sm-offset-4">
									<input type="submit" value="Login" id="login"
										class="btn btn-sm btn-primary" />

								</div>
							</div>
							</br>
						</div>
					</form:form>
				</center>
			</div>
		</div>
	</div>
</body>

</html>
