function showEmergencyContact(){

	$.ajax({
	    type: 'GET',
	    url: 'user/contact',
	    dataType: 'json',
	    success: function(data) { 
	    	document.getElementById("contactFirst").value = data.firstName;
	    	document.getElementById("contactLast").value = data.lastName;
	    	document.getElementById("relationship").value = data.relationship;
	    	document.getElementById("contactPhone").value = data.phone;
	    	document.getElementById("contactEmail").value = data.email;
    		$('#updContact').modal({show:true,backdrop:'static',keyboard:false});	        
	    }, // Success Function 
	    error: function(xhr,status,error){
	        //alert(error);
	    }
	  });

}

function updContactForm(){
	var first = document.getElementById("contactFirst").value;
	var last = document.getElementById("contactLast").value;
	var relationship = document.getElementById("relationship").value;
	var phone = document.getElementById("contactPhone").value;
	var email = document.getElementById("contactEmail").value;
	
	$.ajax({
	    type: 'POST',
	    url: 'user/contact',
	    data: {firstName:first, lastName:last, relationship:relationship, phone:phone, email:email},
	    dataType: 'json',
	    success: function(data) {
	    	var er = data.errors;
	    	if(er!=undefined){
	    		var json = JSON.parse(data.errors);
	    		document.getElementById("contactErrorList").innerHTML = "";
	    		for (var i = 0; i < json.length; i++) { 
	    			var errorList = document.getElementById("contactErrorList");
	    			document.getElementById("contactErrorList").innerHTML = errorList.innerHTML + "<li>" + json[i]+ "</li> ";
	    		}
	    	}else{
	    		$('#updContact').modal('hide');	
	    	}
	    }, // Success Function 
	    error: function(xhr,status,error){
	        //alert(error);
	    }
	  });
	
}


function showWarnings(warnText, divName, spanName)
{
	var divWarnings = document.getElementById(divName);
	var spanWarnings = document.getElementById(spanName);
	
	if (warnText == "")
	{
		divWarnings.style.display = "none";
	}
	else
	{
		divWarnings.style.display = "inline";
	}
	spanWarnings.innerHTML = warnText; 
	
	
}

function showErrors(errText, divName, spanName)
{
	var divErrors = document.getElementById(divName);
	var spanErrors = document.getElementById(spanName);
	
	if (errText == "")
	{
		divErrors.style.display = "none";
	}
	else
	{
		divErrors.style.display = "inline";
	}
	spanErrors.innerHTML = errText; 

}

function numValue(str)
{
	var num = +str;
	
	return num;
}

function random() {
	var D = new Date();
	return (Math.floor(Math.random() * D.getTime()));
}


function IsNumeric(sText) {
	//Decimal IS valid
	var ValidChars = "0123456789.";
	var IsNumber=true;
	var Char;
 
	for (i = 0; i < sText.length && IsNumber == true; i++) { 
		Char = sText.charAt(i); 
		if (ValidChars.indexOf(Char) == -1) {
	         IsNumber = false;
     	}
  	}
	return IsNumber;
}

//Validation functions
function IsInteger(sText) {
	//Decimal is NOT valid
	var ValidChars = "0123456789";
	var IsNumber=true;
	var Char;
 
	for (i = 0; i < sText.length && IsNumber == true; i++) { 
		Char = sText.charAt(i); 
		if (ValidChars.indexOf(Char) == -1) {
	         IsNumber = false;
     	}
  	}
	return IsNumber;
}



var isNN = (navigator.appName.indexOf("Netscape")!=-1);
function autoTab(input,len, e)
{
   if (!isNN)
      e = window.event;

   var keyCode = (isNN)?e.which:e.keyCode;
   var filter = (isNN)?[0,8,9]:[0,8,9,16,17,18,37,38,39,40,46];

   if (input.value.length >= len && !containsElement(filter,keyCode))
   {
   	  try {
      	input.value = input.value.slice(0,len);
      	input.form[(getIndex(input)+1)%input.form.length].focus();
      	input.form[(getIndex(input)+1)%input.form.length].select();
      }
      catch( err ) {
      	//The error is likely do to setting focus after entering a mobile number because it is the
      	//last field and the control is now the save button.  Since there is no way to test for 
      	//"acceptsFocus", just use the try block.  
      }
   }
  return true;  
}


function containsElement(arr, ele)
{
   var found = false, index = 0;
   while(!found && index < arr.length)
     if (arr[index]==ele)
        found = true;
     else
        index++;
   return found;
}


function getIndex(input)
{
   var index = -1, i = 0, found = false;
   while (i < input.form.length && index==-1)
     if (input.form[i] == input)
        index = i;
     else
        i++;
   return index;
}

function random() {
	var dt = new Date();
	return (Math.floor(Math.random() * dt.getTime()));
}

function formatPhone(input){
	//var value = input.value;
	input.value = input.value
    .match(/\d*/g).join('')
    .match(/(\d{0,3})(\d{0,3})(\d{0,4})/).slice(1).join('-')
    .replace(/-*$/g, '')
    ;
	
}

function extractPhoneNumber(phoneNumberString) {

	var retVal = phoneNumberString;
	if (phoneNumberString != null) {
		retVal = phoneNumberString.replace(/[^0-9]/g, "");
	}
	
	return retVal;
}

function breakUpPhoneNumber(phoneNumberString) {

	var phoneNumber = extractPhoneNumber(phoneNumberString);

	var retVal = null;
	if (phoneNumber != null && phoneNumber.length == 10) 
		retVal = /(\d{3})(\d{3})(\d{4})/.exec(phoneNumber);

	if(retVal == null) {
		retVal = new Array(0);
		retVal.push("");
		retVal.push("");
		retVal.push("");
		retVal.push("");
	}
	
	return retVal;
} 


function validateEmail(email) { 
	var emails = email.split(',');
	
	var bOK = true;
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	
	for (var x = 0; x < emails.length; x++)
	{
		if (!re.test(emails[x]))
			bOK = false;
	}
    return bOK;
} 

function checkMatchingReset(){
	var password = document.getElementById("password");
	var confirm_password = document.getElementById("confirm");
	
	if(password.value != confirm_password.value) {
		alert("ee");
	    //confirm_password.className="form-control input-sm pull-left col-sm-4 has-error";
	    $(confirm_password).addClass("error");
	  } else {
	    confirm_password.setCustomValidity('');
	  }
}

var myApp;
myApp = myApp || (function () {
    //var pleaseWaitDiv = $('<div class="modal hide" id="pleaseWaitDialog"><div class="modal-header"><h1>Processing...</h1></div><div class="modal-body"><div class="progress progress-striped active"><div class="bar" style="width: 100%;"></div></div></div></div>');
    var pleaseWaitDiv = $('<div class="modal fade" tabindex="-1" role="dialog">'+
    		'  <div class="modal-dialog" role="document">'+
    		'	   <div class="loadingText">Loading...</div>'+
    		'      <div class="modal-body" id="loading" />'+
    		'  </div>'+
    		'</div>');
	return {
        showPleaseWait: function() {
        	pleaseWaitDiv.modal({
        		  keyboard: false,
        		  backdrop: 'static'
        	});
            pleaseWaitDiv.modal('show');
        },
        hidePleaseWait: function () {
            pleaseWaitDiv.modal('hide');
        },

    };
})();

