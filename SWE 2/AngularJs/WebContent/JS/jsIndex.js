var app = angular.module('main',[]);    	
app.controller('ctrl', function($scope, $http) {
	
	$scope.sendMsg = function(){
		$http({
			url: "https://api.elasticemail.com/v2/email/send",
	   		method: "POST",
	    	params: {apikey : "3658913e-3875-4480-abe4-b54bc00576b5" , subject : $scope.subject ,
	    	from : $scope.email ,  to : "stcomm.education@gmail.com" , 
	    			  bodyHTML : $scope.msg , isTransactional : "true"}
			}).then(function(response){
    			$('#myModal').modal('hide');
			})	
	}	
})
