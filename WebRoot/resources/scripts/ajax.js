function getUserPref(){
	myApp.showPleaseWait();
	var userId = document.getElementById("updateUsername").value;
	$.ajax({
        type: 'POST',
        url: 'getUserPref.do',
        data: {username: userId},
        dataType: 'json',
        success: function(data) { 
            document.getElementById("updateEmail").value = data.email;
            document.getElementById("updatePhone").value = data.phoneNumber;
            document.getElementById("updateCarrier").value = data.carrier;
            document.getElementById("updateNotif").value = data.notifyPref;
            
        }, // Success Function 
        error: function(xhr,status,error){
            alert(error);
        }
      });   // Ajax Call
	myApp.hidePleaseWait();

} //end getUserPref()



function getUserRole(){
	myApp.showPleaseWait();
	var userId = document.getElementById("roleUsername").value;
	$.ajax({
        type: 'POST',
        url: 'getUserRole.do',
        data: {username: userId},
        dataType: 'json',
        success: function(data) { 
            document.getElementById("updateRole").value = data.appRole;
            document.getElementById("updateGroup").value = data.memRole;
            
        }, // Success Function 
        error: function(xhr,status,error){
            alert(error);
        }
      });   // Ajax Call
	myApp.hidePleaseWait();

} //end getUserRole()



function getUserStatus(){
	myApp.showPleaseWait();
	var userId = document.getElementById("statusUsername").value;
	$.ajax({
        type: 'POST',
        url: 'getUserStatus.do',
        data: {username: userId},
        dataType: 'json',
        success: function(data) { 
            document.getElementById("updateStatus").value = data.activeStatus;
            
        }, // Success Function 
        error: function(xhr,status,error){
            alert(error);
        }
      });   // Ajax Call
	myApp.hidePleaseWait();

} //end getUserRole()

function requestContactUpdate(userId){

	$.ajax({
        type: 'PUT',
        url: 'user/'+userId+'/contact/request',
        dataType: 'json',
        success: function(data) { 
        	document.getElementById("upd"+userId).disabled = true;
            
        }, // Success Function 
        error: function(xhr,status,error){
            alert(error);
        }
      });   // Ajax Call
}

function updateAttendanceUser(btn,json){
	$.ajax({
        type: 'POST',
        url: 'attendance',
        data: {attendanceInfo:json},
        dataType: 'json',
        success: function(data) { 
        	btn.className = btn.className + " disabled";
        	icon = document.createElement('i');
        	icon.className = "glyphicon glyphicon-ok-circle text-success";
        	btn.insertBefore(icon, btn.childNodes[0]);
        	btn.setAttribute('disabled', true);
            
        }, // Success Function 
        error: function(xhr,status,error){
           return "false";
        }
      });   // Ajax Call
}

function selEquipAssign(equipId){
	$.ajax({
        type: 'GET',
        url: 'equipment/'+equipId,
        dataType: 'json',
        success: function(data) { 
        	document.getElementById("equipMemberAssign").value = data.userId;
            
        }, // Success Function 
        error: function(xhr,status,error){
           //return "false";
        }
      });   // Ajax Call
}

function selEquipInfo(equipId){
	$.ajax({
        type: 'GET',
        url: 'equipment/'+ equipId + '/information',
        dataType: 'json',
        success: function(data) { 
        	var isUnassigned = (data.userId === undefined);

        	if(isUnassigned){
        		document.getElementById("equipTypeUpd").value = data.equipType;
        		document.getElementById("equipStatusUpd").value = data.status;
        		document.getElementById("equipBrandUpd").value = data.brand;
        		document.getElementById("equipModelUpd").value = data.model;
        	}else{
        		document.getElementById("alertModalLabel").innerHTML = "Warning";
        		document.getElementById("alertModalMessage").innerHTML = "You may not not alter equipment that is currently checked out.";
        		document.getElementById("equipIdUpd").value = "-1";
        		$('#alertModal').modal('show');
        		
        	}
            
        }, // Success Function 
        error: function(xhr,status,error){
           //return "false";
        }
      });   // Ajax Call
}