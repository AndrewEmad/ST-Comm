$(document).ready(function(){
	var app = angular.module('main',[]);
	app.controller('ctrl',function($scope,$http){
	    $http.get('http://localhost:8090/st-comm.com/getCourses').
	    	then(function(response){
	    		$scope.courses = response.data;
	    });
	});
    
})