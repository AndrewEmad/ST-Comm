
var app = angular.module('main',[]);    	
app.controller('ctrl', function($scope, $http) {
        
	/*$http({
		url: "http://localhost:8090/st-comm.com/getgames",
	    method: "GET",
	    params: {registrantName : localStorage.getItem("userName")}
		}).then(function(response){
    		$scope.courses = response.data;
		})*/
	
	var name =localStorage.getItem("CourseName");
	alert(name);
    	});

function clicked(id){
        var gameName = $(id).attr("name");
    }
$(document).ready(function(){
    
})

