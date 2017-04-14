
var app = angular.module('main',[]);    	
app.controller('ctrl', function($scope, $http) {
    	
    	document.getElementById("button").onclick=function(){
    		var arr =[];
    		var datax = {sum: 1,sub: 2,mul: 3,div: 4,pow: 5};
    		arr[0] = datax;
    		$http({
    		url: "http://localhost:8090/calculator/",
    	    method: "POST",
    	    params: datax
   		    }).then(function(response){
    	    	alert("successfull");
    		    });}	
    	});  
    
    





	

	
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





