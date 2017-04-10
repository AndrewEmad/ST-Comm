
var app = angular.module('main',[]);    	
app.controller('ctrl', function($scope, $http) {
    	
    	document.getElementById("button").onclick=function(){
    		alert($scope.num);
    		var datax = {number1: $scope.num,number2:2};
    		$http({
    		url: "http://localhost:8090/calculator/",
    	    method: "POST",
    	    params: datax
   		    }).then(function(response){
    	    	alert("successfull");
    		    });}	
    	});
    	//location.href = "home.html";  
    
    





	

	
	//
	//app.controller('ctrl', function ($scope, $http) {
	//
//		var url = 'http://localhost:8091/calculator/',
//			data = "gvghv",config='contenttype';
	//$http.post(url, data, config).then(function (response) {
//		alert("assa");
	//}, function (response) {
	//// this function handles error
	//});
	//});
	//location.href = "home.html";  





