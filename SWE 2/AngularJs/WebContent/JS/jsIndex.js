var app = angular.module('main',[]);    	
app.controller('ctrl', function($scope, $http) {
	
	$scope.sendMsg = function(){
		$http({
			url: "https://api.elasticemail.com/v2/email/send",
	   		method: "POST",
	    	params: {subject : $scope.subject , from : $scope.email , bodyHTML : $scope.msg}
			}).then(function(response){
    			$('#myModal').modal('hide');
			})	
	
	}
	
	
})

