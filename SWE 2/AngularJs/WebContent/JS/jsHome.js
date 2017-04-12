
var app = angular.module('main',[]);    	
app.controller('ctrl', function($scope, $http) {
        //var data = [{"name" : "course1"},{"name" : "course2"}];
        //$scope.courses = data;
        
        $http({
    		url: "http://localhost:8090/st-comm.com/courses/list-by-registrant",
    	    method: "GET",
    	    params: {registrantName : localStorage.getItem("userName")}
   		    }).then(function(response){
	    		$scope.courses = response.data;
	    	});
	
	    $scope.submit = function(){
	    	$http({
    			url: "http://localhost:8090/st-comm.com/courses/new",
    	    	method: "GET",
    	    	params: {courseName : $scope.newCourse
    	    			,teacherName : localStorage.getItem("userName")}
   		    }).then(function(response){
   		    	if(response.data){
   		    		alert($scope.newCourse);
   		    		$scope.courses = [$scope.newCourse];
        			document.getElementById("courseForm").style.display="none";
   		    	}
   		    	else{
   		    		
   		    	}
	    		
	    		});
	    }
        
        $(document).ready(function(){
        	document.getElementById("signOut").onclick = function(){
        		localStorage.removeItem("userName");
        	}
        	
        	// first call service to get type
    
    		if(/*localStorage("type") == "teacher"*/true){
        		document.getElementById("createCourse").style.display = "block";
        		document.getElementById("createGame").style.display = "block";
    		}
    		
    		/*$('#form1').submit(function (event)
        		{
            		// call service to check if unique
            		
            		
        		});*/
        		
    		document.getElementById("createCourse").onclick = function(){
        		document.getElementById("courseForm").style.display="block";
    		}
    		document.getElementById("cancel").onclick = function(){
        		document.getElementById("courseForm").style.display="none";
    		}
    		document.getElementById("createGame").onclick = function(){
        		// go to form of create game ( div)
    		}
    
        })
    	});

function clicked(id){
        var courseName = $(id).attr("name");
        localStorage.setItem("CourseName",courseName);
        location.href = "games.html";
    }