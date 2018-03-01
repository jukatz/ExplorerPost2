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
<title>Events</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<script type="text/javascript" src="<%=path%>/resources/scripts/en-US.js?<%=jsVersion%>"></script>
<script type="text/javascript" src="<%=path%>/resources/bootstrap/js/jquery-1.9.1.js?<%=jsVersion%>"></script>
<script type="text/javascript" src="<%=path%>/resources/bootstrap/js/bootstrap.js?<%=jsVersion%>"></script>
<script type="text/javascript" src="<%=path%>/resources/scripts/bootstrap-formhelpers.js?<%=jsVersion%>"></script>
<script type="text/javascript" src="<%=path%>/resources/scripts/jquery.tabletojson.js?<%=jsVersion%>"></script>
<script type="text/javascript" src="<%=path%>/resources/scripts/util.js?<%=jsVersion%>"></script>
<script type="text/javascript" src="<%=path%>/resources/scripts/events.js?<%=jsVersion%>"></script>
<script type="text/JavaScript" src="<%=path%>/resources/scripts/responsive-calendar.js?<%=jsVersion%>"></script>
<script type="text/javascript" src="<%=path%>/resources/datepicker/js/bootstrap-datepicker.js?<%=jsVersion%>"></script>
<script type="text/JavaScript" src="<%=path%>/resources/clockpicker/clockpicker.js?<%=jsVersion%>"></script>


<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/resources/css/loading.css?<%=jsVersion%>">
<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/resources/bootstrap/css/bootstrap.css?<%=jsVersion%>">
<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/resources/bootstrap/css/bootstrap-theme.css?<%=jsVersion%>" />
<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/resources/css/bootstrap-formhelpers.css?<%=jsVersion%>">
<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/resources/css/bussys.css?<%=jsVersion%>">
<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/resources/css/calendar/responsive-calendar.css?<%=jsVersion%>"">
<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/resources/datepicker/css/bootstrap-datepicker.css?<%=jsVersion%>">
<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/resources/datepicker/css/bootstrap-datepicker.standalone.css?<%=jsVersion%>">
<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/resources/datepicker/css/bootstrap-datepicker3.css?<%=jsVersion%>">
<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/resources/clockpicker/clockpicker.css?<%=jsVersion%>">



<style>
.overlimit {
	color: red;
}
</style>


<script type="text/javascript">
  	
      $(document).ready(function () {
      			var url = document.location.toString();
				if (url.match('#')) {
    				$('.nav-tabs a[href="#'+url.split('#')[1]+'"]').tab('show') ;
				} 
				
                $('#strtDt').datepicker({
                        format: "yyyy-mm-dd",
                        autoclose : true,
                        todayHighlight: true,
                        todayBtn: true
                });
                
                $("#strtDt").on('changeDate', function (ev) {
                	clearField('endDt');
            		var minDate = new Date(ev.date.valueOf());
            		$('#endDt').datepicker('setStartDate', minDate);        			
       			});
                
                 $('#endDt').datepicker({
                        format: "yyyy-mm-dd",
                        autoclose : true,
                        todayHighlight: true,
                        todayBtn: true
                });
                
                $("#updStrtDt").on('changeDate', function (ev) {
                	clearField('endDt');
            		var minDate = new Date(ev.date.valueOf());
            		$('#updEndDt').datepicker('setStartDate', minDate);        			
       			});
                
                 $('#updEndDt').datepicker({
                        format: "yyyy-mm-dd",
                        autoclose : true,
                        todayHighlight: true,
                        todayBtn: true
                });

                $('.clockpicker').clockpicker({
                        placement: 'left',
                        align: 'left',
                        autoclose:'true'
                });

                $(".nav-tabs a").click(function(){
                        $(this).tab('show');
                });

                $('[data-toggle="tooltip"]').tooltip();

                $(function() {
                      $('li[href^="/' + location.pathname.split("/")[2].split(".")[0] + '"]').addClass('active');
                });
                
                $('#cancelEvent').prop('disabled', true);
				$('#updAttendance').prop('disabled', true);
				

      });

</script>


</head>

<body>
	<!-- MENU -->
	<%@ include file="/jsp/menu.jsp"%>

	<!-- MESSAGES -->
	<%@ include file="/jsp/showMessages.jsp"%>



	

	<div class="screenBody">
		<center>

			<div class="jumbotron1">
				</br>


				<div class="container-fluid">

					<ul class="nav nav-tabs">
						<li class="active"><a data-toggle="tab" href="#addEvent">Create Event</a></li>
						<li><a data-toggle="tab" href="#updEvent">Update Event</a></li>
						<li><a data-toggle="tab" href="#viewAttend">Attendance</a></li>
					</ul>

					<div class="tab-content jumbotron">
						<div id="addEvent" class="tab-pane fade in active">
						<form:form id="command" commandName="command" method="POST" action="event" >
							<h3>Add Event</h3>
							<br>
							<div class="container">
								<div class="form-group form-inline row">
									<label for="name" class="col-sm-3 text-right">Event Title</label> 
									<form:input cssClass="form-control col-sm-3" path="nameTx"
										id="name" /> 
									<label for="strtDt" class="col-sm-2 text-right">Date</label>
									<div class="input-group date col-sm-3 pull-left" id="date">
										<form:input cssClass="form-control" id="strtDt"
											 path="startDt"/>
										<span class="input-group-addon">
											<i class="glyphicon glyphicon-calendar"></i>
										</span>
									</div>

								</div>
								<div class="form-group form-inline row">
									<label for="loc" class="col-sm-3 text-right">Location</label> 
									<form:input cssClass="form-control col-sm-3" id="loc"
										 path="location"/> 
									<label for="srtrTm" class="col-sm-2 text-right">Start Time</label>
									<div class="input-group clockpicker col-sm-3 pull-left"
										id="strtTmParent">
										<form:input cssClass="form-control" id="strtTm"
											path="startTm" /> 
										<span class="input-group-addon">
										<span class="glyphicon glyphicon-time"></span>
										</span>
									</div>
								</div>

								<div class="form-group form-inline row">
									<label for="rmnd_cd" class="col-sm-3 text-right">Reminders</label>
									<form:select cssClass="form-control input-sm pull-left col-sm-3"
										id="rmnd_cd" path="reminder">
										<form:option value="Y">Yes, Send Reminders</form:option>
										<form:option value="N">No, Dont Send Reminders</form:option>
									</form:select> 
									<!-- 
									<label for="endDt" class="col-sm-2 text-right">End Date</label>
									<div class="input-group date col-sm-3 pull-left" id="date">
										<form:input cssClass="form-control" id="endDt"
											 path="endDt"/>
										<span class="input-group-addon">
											<i class="glyphicon glyphicon-calendar"></i>
										</span>
									</div>
									-->
									<label for="endTm" class="col-sm-2 text-right">End Time</label>
									<div class="input-group clockpicker col-sm-3 pull-left"
										id="endTmParent">
										<form:input id="endTm" cssClass="form-control"
											path="endTm"/> 
										<span class="input-group-addon">
											<span class="glyphicon glyphicon-time"></span>
										</span>
									</div>
								</div>

								<div class="form-group form-inline row">
									<label for="grp_cd" class="col-sm-3 text-right">Group</label> 
									<form:select cssClass="form-control input-sm pull-left col-sm-3" 
										id="grp_cd" path="group">
										<form:option value="0">Invite Everyone</form:option>
										<form:option value="1">Explorers & Advisors Only</form:option>
										<form:option value="2">Striped & Advisors Only</form:option>
										<form:option value="3">Advisors Only</form:option>
									</form:select> 
									<!-- 
									<label for="endTm" class="col-sm-2 text-right">End Time</label>
									<div class="input-group clockpicker col-sm-3 pull-left"
										id="endTmParent">
										<form:input id="endTm" cssClass="form-control"
											path="endTm"/> 
										<span class="input-group-addon">
											<span class="glyphicon glyphicon-time"></span>
										</span>
									</div>
									-->
								</div>

								<div class="row">
									<div class="col-sm-8 col-sm-offset-2">
										<label for="comment">Comments:</label>
										<form:textarea cssClass="form-control" rows="3" id="comment"
											path="notes" >
										</form:textarea>
									</div>
								</div>

								</br>
								<button class="btn btn-sm btn-default" type="submit">Create Event</button>



							</div>
							<!-- end container -->
						</form:form>
						</div>
						<!-- end add event -->




						<div id="updEvent" class="tab-pane fade">
						<form:form id="command" method="POST" action="event/update" >
							<h3>Update Event</h3>
							<br>
							<div class="container">

								<div class="form-group form-inline row">
									<label for="selEvent" class="col-sm-3 col-sm-offset-2 text-right">
										Choose An Event</label>
									<form:select cssClass="form-control input-sm pull-left col-sm-3" 
										id="selEvent" path="eventId" onchange="selEventInfo(this.options[this.selectedIndex].value)">
										<form:option value="" label="Pick An Event" disabled="true"/>
										<form:option value="" label="" />
										<form:options items="${eventList}" itemValue="key" itemLabel="value" />
									</form:select>
									<button
										class="form-control col-sm-1 col-sm-offset-1 btn btn-danger glyphicon glyphicon-remove-circle"
										id="cancelEvent" data-toggle="tooltip" data-placement="top"
										title="Cancel the selected event."
										onclick='cancelEvent()' >
									</button>
								</div>


								<div class="form-group form-inline row">
									<label for="updName" class="col-sm-3 text-right">Event Title</label> 
									<form:input cssClass="form-control col-sm-3" id="updName" path="nameTx"/> 
									<label for="updStrtDt" class="col-sm-2 text-right">Start Date</label>
									<div class="input-group date col-sm-3 pull-left" id="updStartDate">
										<form:input cssClass="form-control" id="updStrtDt" path="startDt" />
										<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
									</div>
								</div>
								<div class="form-group form-inline row">
									<label for="updLoc" class="col-sm-3 text-right">Location</label>
									<form:input cssClass="form-control col-sm-3" id="updLoc" path="location"/>
									<label for="updStrtTm" class="col-sm-2 text-right">Start Time</label>
									<div class="input-group clockpicker col-sm-3 pull-left" id="updStrtTmParent">
										<form:input cssClass="form-control" id="updStrtTm" path="startTm"/>
										<span class="input-group-addon"> <span class="glyphicon glyphicon-time"></span>
										</span>
									</div>
								</div>

								<div class="form-group form-inline row">
									<label for="updRmnd_cd" class="col-sm-3 text-right">Reminders</label>
									<form:select cssClass="form-control input-sm pull-left col-sm-3" id="updRmnd" path="reminder">
										<form:option value=""></form:option>
										<form:option value="Y">Yes, Send Reminders</form:option>
										<form:option value="N">No, Dont Send Reminders</form:option>
									</form:select> 
									<label for="updEndDt" class="col-sm-2 text-right">End Date</label>
									<div class="input-group date col-sm-3 pull-left" id="updEndDate">
										<form:input cssClass="form-control" id="updEndDt" path="endDt"/>
										<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
									</div>
								</div>

								<div class="form-group form-inline row">
									<label for="updGrp_cd" class="col-sm-3 text-right">Group</label>
									<form:select cssClass="form-control input-sm pull-left col-sm-3" id="updGrp" path="group">
										<form:option value=""></form:option>
										<form:option value="0">Invite Everyone</form:option>
										<form:option value="1">Explorers & Advisors Only</form:option>
										<form:option value="2">Striped & Advisors Only</form:option>
										<form:option value="3">Advisors Only</form:option>
									</form:select> 
									<label for="updEndTm" class="col-sm-2 text-right">End Time</label>
									<div class="input-group clockpicker col-sm-3 pull-left" id="updEndTmParent">
										<form:input id="updEndTm" cssClass="form-control" path="endTm"/>
										<span class="input-group-addon"> 
											<span class="glyphicon glyphicon-time"></span>
										</span>
									</div>
								</div>


								<div class="row">
									<div class="col-sm-8 col-sm-offset-2">
										<label for="updComment">Comments:</label>
										<form:textarea cssClass="form-control" rows="3" id="updComment" path="notes"></form:textarea>
									</div>
								</div>

								</br>
								<button class="btn btn-sm btn-default" type="submit">Update Event</button>
								
							</div>
							<!-- end container -->
						</form:form>
						</div>
						<!-- end update event -->





						<div id="viewAttend" class="tab-pane fade">
						<form:form id="commandAtt" commandName="command" action="event/attendance" method="POST">
						
							<h3>View Event Attendance</h3>
							<br>
							<div class="container">

								<div class="form-group form-inline row">
									<label for="selEvent" class="col-sm-3 col-sm-offset-2 text-right">Choose An Event</label> 
									<form:select cssClass="form-control input-sm pull-left col-sm-3" 
										id="selEvent" path="eventId" onchange="selEventAttendance(this.options[this.selectedIndex].value)">
										<form:option value="" label="Pick An Event" disabled="true"/>
										<form:option value="" label="" />
										<form:options items="${eventList}" itemValue="key" itemLabel="value" />
									</form:select>
									<button
										class="form-control col-sm-1 col-sm-offset-1 btn btn-success glyphicon glyphicon-ok"
										id="updAttendance" onclick="attendanceToJson(this);" data-toggle="tooltip" data-placement="top"
										title="Update Attendance." >
									</button>
								</div>
								<form:hidden id="attendance" path="attendance"/>
								<div class="form-group form-inline row">
									<table path="details" id="attendanceTable"  class="table table-striped">
										<thead>
      										<tr class="row">
      											<th class="col-sm-3 text-center" data-override="userId">Username</th>
      											<th class="col-sm-4 text-center" data-override="ResponseCd">Response</th>
      											<th class="col-sm-2 text-center" data-override="attendanceTypeCd">Edited?</th>
      											<th class="col-sm-3 text-center" data-override="notes">Comments</th>
      										</tr>
      									</thead>
      									<tbody>
      										<!-- jQuery will fill the table with Ajax call -->
      									</tbody>
									
									</table>
	

								</div>

							</div>
							<!-- end container -->
							
						</form:form>
						</div>
						<!-- end update event -->

					
					</div>
				</div>

			</div>
			<!-- jumbotron -->



		</center>
	</div>

</body>

</html>
