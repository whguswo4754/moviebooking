function confirmToHomePage() {
	  
	  var theAnswer = confirm("Are you sure, go back to Home page?");
		  
	  if (theAnswer){
	     return true;
	  }
        return false;
	}

function confirmToGateway() {
	  
	  var theAnswer = confirm("Direct you to home page?");
		  
	  if (theAnswer){
	     return true;
	  }
      return false;
	}