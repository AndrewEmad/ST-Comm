var app = angular.module('main',[]);    	
app.controller('ctrl', function($scope, $http) {
	$(document).ready(function(){
		$("#password, #userName").keyup(function(){
			document.getElementById("errorMsg").style.display ="none";
		});
		document.getElementById("signUp").onclick=function(){
	        location.href = "signUp.html";
	    } 
	
    	$("#form1").submit(function(event){
    		var data = {name: $scope.userName , password:$scope.password};
    		$http({
    		url: "http://localhost:8090/st-comm.com/login/",
    	    method: "POST",
    	    params: data
   		    }).then(function(response){
   		    	if(response.data){
   		    		var username = $("#userName").val(); 
   		    		localStorage.setItem("userName",username);
   		    		location.href = "home.html";
   		    	}
   		    	else{
   		    		document.getElementById("errorMsg").style.display ="block";
   		    	}
   		    		
    		    });})	})
    	});
