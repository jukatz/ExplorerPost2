function clearField(elId){
	var field = document.getElementById(elId);
	if(field != undefined){
		document.getElementById(elId).value = '';
	}
}

function selEventInfo(sel){
	myApp.showPleaseWait();
	var key = sel;
	$.ajax({
	    type: 'GET',
	    url: 'event.json',
	    data: {key:key},
	    dataType: 'json',
	    success: function(data) { 
	    	//alert(JSON.stringify(data));
	    	document.getElementById("updName").value = data.eventName;
	    	document.getElementById("updComment").value = data.eventNote;
	    	document.getElementById("updLoc").value = data.eventLocation;
	    	document.getElementById("updStrtDt").value = data.startDate;
	    	document.getElementById("updStrtTm").value = data.startTime;
	    	document.getElementById("updRmnd").value = data.eventReminder;
	    	document.getElementById("updEndDt").value = data.endDate;
	    	document.getElementById("updGrp").value = data.eventGroup;
	    	document.getElementById("updEndTm").value = data.endTime;

	    	$('#cancelEvent').prop('disabled', false);
	    	
	        
	    }, // Success Function 
	    error: function(xhr,status,error){
	        alert(error);
	    }
	  });
	myApp.hidePleaseWait();
}

function selEventAttendance(sel){
	myApp.showPleaseWait();
	var key = sel;

	$.ajax({
	    type: 'GET',
	    //url: 'attendance.json',
	    url: 'event/'+key+'/attendance',
	    //data: {key:key},
	    dataType: 'json',
	    success: function(data) { 

	    	$.each(data, function(key,obj) {
	    		$('#attendanceTable > tbody:last-child').append(''+
	    				'<tr class="row"> '+
						'	<td class="col-sm-3"> ' + obj.userId + '</td> '+
						'	<td class="col-sm-4"> '+
						'		<label class="radio-inline"> '+
					    '  			<input class="radio-btn" type="radio" name="att['+key+']" id="P['+key+']" value="P" data-id="'+key+'">Present '+
					    '  		</label> '+
					    '  		<label class="radio-inline"> '+
					    '    		<input class="radio-btn" type="radio" name="att['+key+']" id="E['+key+']" value="E" data-id="'+key+'">Excused '+
					    '  		</label> '+
					    '  		<label class="radio-inline"> '+
					    '    		<input class="radio-btn" type="radio" name="att['+key+']" id="A['+key+']" value="A" data-id="'+key+'">Absent '+
					    ' 		 </label>'+
					    '	</td> '+
						'	<td class="col-sm-2 text-center"> ' + obj.attendanceTypeCd + '</td> '+
						'	<td class="col-sm-3 text-center"> ' + 
						'		<input id="notes['+key+']" value="'+ obj.notes +'" type="text" class="form-control"></input> '+
						'	</td> '+
						'</tr>' );
	    		$('input:radio[id="'+obj.responseCd+'['+key+']'+'"][value="'+obj.responseCd+'"]')
	    	    	.attr('checked', true);
	    		  //alert(key+':'+JSON.stringify(value));
	    		});

	    	$('#updAttendance').prop('disabled', false);
	    	
	    	$('input[type="radio"]').on('click change', function(){
				alert("test");
				//var key = $(this).attr("data-id");
					//alert($('input[name="att['+key+']"]:checked', '#myForm').val()); 
			});
	    	
	        
	    }, // Success Function 
	    error: function(xhr,status,error){
	        alert(error);
	    }
	  });
	myApp.hidePleaseWait();
}

function attendanceToJson(form){
	myApp.showPleaseWait();
	var table = $('#attendanceTable tr:has(td)').map(function(i, v) {
	    var $td =  $('td', this);
	    var $in = $('input',$td);
	    
	    //find the index of the checked radio button
	    //if we get more than 5 buttons you need to increase the max ix value in the loop
	    var index = (function(el){
	    	for(ix = 0; ix < 5; ix++) { 
	    	    if(el.eq(ix).is(":checked")) return ix;
	    	}
	    })($in);

	        return {
	                 userId: $td.eq(0).text(),
	                 responseCd: $in.eq(index).val(),
	                 notes: $in.eq(3).val()
	               }
	}).get();
	
	document.getElementById("attendance").value = JSON.stringify(table);
	
	
}


