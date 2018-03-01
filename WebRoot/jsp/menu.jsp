
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/resources/css/nav.css?<%=jsVersion%>">
    </head>
<div class="nav-side-menu">
   	<div class="brand">
   		<p class="welcome side">Lake Zurich Explorer Post 2</p>
   		<hr class="side side-hr"/>
   		<p class="welcome">Welcome ${user.userName}</p>
   		
   		
   	</div> 
    <i class="glyphicon glyphicon-list  toggle-btn" data-toggle="collapse" data-target="#menu-content"></i>
  
        <div class="menu-list">
  
            <ul id="menu-content" class="menu-content collapse out">

                <li href="calendar" class="collapsed">
                  <a href="calendar"><i class="glyphicon glyphicon-calendar"></i>Event Calendar</a>
                </li>

                <li href="event"  class="collapsed">
                  <a href="event"><i class="glyphicon glyphicon-list-alt"></i>Event Control</a>
                </li>  
                <li href="member" class="collapsed">
                  <a href="member"><i class="glyphicon glyphicon-user"></i>Member Control</a>
                </li>
                <li href="reset" class="collapsed">
                  <a href="reset"><i class="glyphicon glyphicon-wrench"></i>Member Password Manager</a>
                </li>

				<li href="equipment" class="collapsed">
                  <a href="equipment"><i class="glyphicon glyphicon-tags"></i>Equipment</a>
                </li>
                
                <li href="report" class="collapsed">
                  <a href="report"><i class="glyphicon glyphicon-folder-open"></i>Reports</a>
                </li>

                <li href="password" class="collapsed">
                  <a href="password"><i class="glyphicon glyphicon-lock"></i>Change Your Password</a>
                </li>

                <li href="logout" class="collapsed">
                  <a href="logout"><i class="glyphicon glyphicon-off"></i>LogOut</a>
                </li>
            </ul>
        </div>
</div> <!-- end side menu -->
</html>