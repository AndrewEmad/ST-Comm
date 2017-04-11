var app = angular.module('main',[]);    	
app.controller('ctrl', function($scope, $http) {
    	

	$(document).ready(function(){
		var passwordMatch =false;
		$("#password, #confirmPassword").keyup(checkPasswordMatch);
		
	    $('#teacher').change(function () {
	        if($(this).is(':checked')) {
	            $("#email").attr("pattern","/^[A-Za-z0-9._]+@[a-z0-9.-]+\.edu+\.eg$/");
	            $("#email").attr("title","An Academic Email Required. e.g. .edu.eg");
	        }
	    });
	    
	    $('#student').change(function () {
	        if($(this).is(':checked')) {
	            $("#email").removeAttr("pattern");
	        }
	    });
	    
	    document.getElementById("signIn").onclick=function(){
	        location.href = "signIn.html";
	    }
	    
	    $("#form1").submit(function(event){
    		var data = {name: $scope.userName , birthdate:$scope.birthdate ,
    				gender:$scope.gender , mail:$scope.email , 
    				country:$scope.country,
    				password:$scope.password ,type:$scope.type};
    		
    		$http({
    		url: "http://localhost:8090/st-comm.com/signup/",
    	    method: "POST",
    	    params: data
   		    }).then(function(response){
   		    	if(response.data && passwordMatch){
   		    		location.href = "congratulation.html";
    	    	}
    	    	else{
    	    		location.href = "signUp.html";
    	    	}
    	    	
    		    });
    		})
	    
	    function checkPasswordMatch() {
	        var password = $("#password").val();
	        var confirmPassword = $("#confirmPassword").val();
	        
	        if (password != confirmPassword || document.getElementById("password").value.length == 0 || document.getElementById("confirmPassword").value.length == 0){
	            $("#passwordMatching").html("Passwords do not match!");
	            document.getElementById("passwordMatching").style.color='red';
	            passwordMatch = false;
	        }   
	        else{
	            $("#passwordMatching").html("Passwords match.");
	            document.getElementById("passwordMatching").style.color='green';
	            passwordMatch = true;
	        }
	            
	    }
	    
	    
	})

	
    		
    	});





 
  
