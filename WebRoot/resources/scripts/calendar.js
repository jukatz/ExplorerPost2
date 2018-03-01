function loadEventsForMonth(month,year,user_id){
myApp.showPleaseWait();
var events = "";
var res = $('.responsive-calendar');

if(typeof month === "undefined" || typeof year === "undefined"){
   month = translateMonth(document.getElementById("head-month").innerHTML);
   year =document.getElementById("head-year").innerHTML;
   user_id =document.getElementById("head-id").innerHTML;
}

$.ajax({
    type: 'GET',
    url: 'getEvents.json',
    data: {month: month, year: year, userId: user_id},
    dataType: 'json',
    success: function(data) { 
    	var updContact = data.updContact;
    	if(updContact==="true"){
    		showEmergencyContact();
    	}
    	res.responsiveCalendar('clearAll');
        res.responsiveCalendar('edit', data);
        
    }, // Success Function 
    error: function(xhr,status,error){
    	myApp.hidePleaseWait();
        //alert(error);
    }
  });

}



function addLeadingZero(num){
  if (num < 10) {
      return "0" + num;
    }

   return "" + num;
}	

function showEventDetails(events){

modalBody = document.getElementById('eventModal-body');

if(document.contains(document.getElementById('parentWell'))){
   parentWell = document.getElementById('parentWell');
   modalBody.removeChild(parentWell);
}

parentWell = document.createElement('div');
   parentWell.id="parentWell";

for (var i = 0, well, name, start; i < events.length; i++) {
    well = document.createElement('div');
    name = document.createElement('legend');
    loc = document.createElement('div');
    start = document.createElement('div');
    end = document.createElement('div');
    notes = document.createElement('div');
    br = document.createElement('br');

    response = document.createElement('div');
    response.className="row";
    accept = document.createElement('div');
    accept.className="btn btn-primary col-xs-3 col-xs-offset-2";
    accept.setAttribute('data-event', events[i][0]);
    accept.setAttribute('data-resp', "P"); //P for present
    accept.setAttribute('onclick', 'inviteResponse(this);');
    accept.appendChild(document.createTextNode("Accept Invitation"));
    decline = document.createElement('div');
    decline.className="btn btn-danger col-xs-3 col-xs-offset-2";
    decline.setAttribute('data-event', events[i][0]);
    decline.setAttribute('data-resp', "A"); //A for absent - we assume unexcused
    decline.setAttribute('onclick', 'inviteResponse(this);');
    decline.appendChild(document.createTextNode("Decline Invitation"));
    response.appendChild(accept);
    response.appendChild(decline);

    if(events[i][6]=="P" || events[i][7]=="X"){
        accept.className = accept.className + " disabled";
        accept.setAttribute('disabled', true);
    }
    if(events[i][6]=="A" || events[i][7]=="X"){
        decline.className = decline.className + " disabled";
        decline.setAttribute('disabled', true);
    }

    name.appendChild(document.createTextNode(events[i][1]));
      name.className="h3 text-center";
    loc.appendChild(document.createTextNode("Location: " + events[i][2]));
    start.appendChild(document.createTextNode("Start Time: " + events[i][3]));
    end.appendChild(document.createTextNode("End Time: " + events[i][4]));
    var noteDtl = events[i][5];
    if(noteDtl.length == 0 ) noteDtl = "None";
    notes.appendChild(document.createTextNode("Special Notes: " + noteDtl));
    well.appendChild(name);
    well.appendChild(loc);
    well.appendChild(start);
    well.appendChild(end);
    well.appendChild(notes);
    well.appendChild(br);
    well.appendChild(response);
    well.className="well well-lg";
    parentWell.appendChild(well);

}

   modalBody.appendChild(parentWell);
   $('#eventDetailModal').modal('show');

}

function translateMonth(month){
var number;

  switch(month){
     case "January":
        number="01";
        break;
     case "February":
        number="02";
        break;
     case "March":
        number="03";
        break;
     case "April":
        number="04";
        break;
     case "May":
        number="05";
        break;
     case "June":
        number="06";
        break;
     case "July":
        number="07";
        break;
     case "August":
        number="08";
        break;
     case "September":
        number="09";
        break;
     case "October":
        number="10";
        break;
     case "November":
        number="11";
        break;
     case "December":
        number="12";
        break;
  }

return number;
}

function init_Calendar(user_id,token) {
    var now = new Date()
    var calendar = $('.responsive-calendar');
    var settings = new Object();
    var month = now.getMonth()+1 ;
    var year = now.getFullYear();
    month = addLeadingZero(month);
    settings.time = now.getFullYear() + '-' + month;
    settings.onMonthChange = loadEventsForMonth;
    settings.onInit = loadEventsForMonth(month,year,user_id);
    //settings.onDayClick = onDay;
    settings.startFromSunday=true;
    settings.onDayClick = function(events) {
         var thisDayEvent, key, eventInfo=[], eventsInfo=[];
          key = $(this).data('year')+'-'+addLeadingZero( $(this).data('month') )+'-'+addLeadingZero( $(this).data('day') );
         thisDayEvent = events[key];
         var day = JSON.parse(thisDayEvent);
         for (i=0; i<day.dayEvents.length; i++){
              eventInfo = [day.dayEvents[i].eventId, day.dayEvents[i].eventName , day.dayEvents[i].eventLocation , 
                           day.dayEvents[i].startTime , day.dayEvents[i].endTime , day.dayEvents[i].eventNote , 
                           day.dayEvents[i].responseCd  , day.dayEvents[i].attendanceTypeCd ];
              eventsInfo.push(eventInfo);
         }
         if(eventsInfo.length > 0 ) {showEventDetails(eventsInfo);}
    };
    calendar.responsiveCalendar(settings);

} //end init_Calendar





function inviteResponse(btn){
    var eventId = btn.getAttribute("data-event");
    var eventResp = btn.getAttribute("data-resp");
 
    var data = {
    		eventId:eventId,
    		responseCd:eventResp,
    	};
    
    if(btn.getAttribute("disabled") != "true"){
    	var success = updateAttendanceUser(btn,JSON.stringify(data));
    }
    
    





} //end inviteResponse


function logEventShow(eventId,token,userId){

     $.ajax({
            type: 'POST',
            url: 'http://post2.netne.net/includes/logData.php',
            data: {logEventId: eventId,
                       logUserId: userId,
                       token: token
                      },
            dataType: 'text',
            success: function(data) { 
               //congrats its logged
            }, // Success Function 
            error(xhr,status,error){
                alert(error);
            }
     });   // Ajax Call


} //logEventShow




function showResponse(data){

   //we need this to remove the previous message
   if(document.contains(document.getElementById('parentWell'))){
      parentWell = document.getElementById('parentWell');
      modalBody.removeChild(parentWell);
   }

   parentWell = document.createElement('div');
   parentWell.id="parentWell";

   message = document.createElement('div');
   message.appendChild(document.createTextNode(data.info.message));

   parentWell.appendChild(message);


   if(data.status == "success"){
       modalBody = document.getElementById('jsonSuccessBody');
       modalBody.appendChild(parentWell);
      $('#jsonSuccessModal').modal('show');
   }
   if(data.status == "error"){
       modalBody = document.getElementById('jsonFailedBody');
       modalBody.appendChild(parentWell);
      $('#jsonFailModal').modal('show');

   }


} //end showResponse