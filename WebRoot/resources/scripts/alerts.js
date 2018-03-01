function alertExample(message) {
	bootbox.alert(message);
}

function confirmExample()
{
	bootbox.confirm("Are you sure?", function(result) {
		var spanConfirmResult = document.getElementById("spanConfirmResult");
		spanConfirmResult.innerHTML = "<b>Confirm Result: </b>" + result;
		}); 
}

function promptExample()
{
	bootbox.prompt("What is your name?", function(result) {
		var spanPromptResult = document.getElementById("spanPromptResult");
		
		  if (result === null) {                                             
				spanPromptResult.innerHTML = "<b>Prompt Result: </b>Cancelled";
		  } else {
			  spanPromptResult.innerHTML = "<b>Prompt Result: </b>" + result;
		  }
		});	

}

function promptDefaultExample()
{
	bootbox.prompt({
		  title: "What is your real name?",
		  value: "Keyser Söze",
		  callback: function(result) {
			var spanPromptDefaultResult = document.getElementById("spanPromptDefaultResult");
			  
		    if (result === null) {
		    	spanPromptDefaultResult.innerHTML = "<b>Prompt Result: </b>Cancelled";
		    } else {
		    	spanPromptDefaultResult.innerHTML = "<b>Prompt Result: </b>" + result;
		    }
		  }
		});	
}