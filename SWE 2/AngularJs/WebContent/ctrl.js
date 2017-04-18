
var app = angular.module('main',[]);    	
app.controller('ctrl', function($scope, $http) {
    	
    	document.getElementById("button").onclick=function(){
    		var result = {sum:1,sub:2};
    		var result2 = {sum:3,sub:4};
    		var arr =[];
    		arr[0] = result;
    		arr[1] = result2;
    		
    		$http({
    		url: "http://localhost:8090/calculator/",
    	    method: "POST",
    	    params: { arr : arr}
   		    }).then(function(response){
    	    	alert("successfull");
    	    	})
    	}
    
})