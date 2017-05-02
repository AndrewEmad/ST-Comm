
var app = angular.module('main',[]);    	
app.controller('ctrl', function($scope, $http) {
        
        var courseName;
        var gameName;
        var questions;
        var question;
        var numOfQuestions;
        var questionNum;
        
        $scope.username = localStorage.getItem("userName");
        
        $("#newCourse").keyup(function(){
			document.getElementById("nameExists").style.display ="none";
		});
		
		$("#mcq1, #mcq2, #mcq3, #mcq4, #answer").keyup(function(){
			document.getElementById("invalidAnswer").style.display ="none";
		});
		
		$("#courseName").keyup(function(){
			document.getElementById("invalidCourseName").style.display ="none";
		});
		
		$("#gameName").keyup(function(){
			document.getElementById("invalidGameName").style.display ="none";
		});
        
        $http({
    		url: "http://localhost:8090/st-comm.com/query/registrant-type",
    	    method: "GET",
    	    params: {name : localStorage.getItem("userName")}
   		    }).then(function(response){
	    		if(response.data != 1){
					document.getElementById("createCourse").style.display = "block";
        			document.getElementById("createGame").style.display = "block";	    		
	    		}
	    	});

        $http({
    		url: "http://localhost:8090/st-comm.com/courses/list-by-registrant",
    	    method: "GET",
    	    params: {registrantName : localStorage.getItem("userName")}
   		    }).then(function(response){
	    		$scope.courses = response.data;
	    	});
	
	    $scope.createCourse = function(){
	    	$http({
    			url: "http://localhost:8090/st-comm.com/courses/new",
    	    	method: "GET",
    	    	params: {courseName : $scope.newCourse
    	    			,teacherName : localStorage.getItem("userName")}
   		    }).then(function(response){
   		    	if(response.data){
        			location.reload();
   		    	}
   		    	else{
   		    		document.getElementById("nameExists").style.display ="block";
   		    	}
	    		});
	    }
	    
	    $scope.createGame=function(){
	    	questionNum = 1;
	    	questions =[];
	    	
	    	courseName = document.getElementById("courseName").value;
	    	gameName = document.getElementById("gameName").value;
	    	
	    	$http({
    			url: "http://localhost:8090/st-comm.com/courses/exists",
    	    	method: "GET",
    	    	params: {courseName : courseName}
   		    }).then(function(response){
   		    	if(response.data){
        			$http({
    					url: "http://localhost:8090/st-comm.com/games/exists",
    	    			method: "GET",
    	    			params: {gameName : gameName}
   		    		}).then(function(response){
   		    			if(!response.data){
   		    				numOfQuestions = document.getElementById("numOfQuestions").value;
	    					document.getElementById("questionNum").innerHTML = "Question "+questionNum+
	    														" Out of "+numOfQuestions;
	    					$('#myModal2').modal('hide');
	    					$('#myModal3').modal('show');
   		    			}
   		    			else{
   		    				document.getElementById("invalidGameName").style.display ="block";
   		    			}
   		    		})
   		    	}
   		    	else{
   		    		document.getElementById("invalidCourseName").style.display ="block";
   		    	}
	    		});
	    }
	    $scope.submitQuestion=function(){
	    	var choices =[];
	    	var answerIndex;
	    	var time;
	    	var matched;
	    	
	    	time = document.getElementById("time").value;
	    	choices[0] = $("#mcq1").val();
	    	choices[1] = $("#mcq2").val();
	    	
	    	if($("input[name=Qtype]:checked").val() == "MCQ"){
					    	
	    		var i =2;
	    		
	    		if($("#mcq3").val() != ""){
	    			choices[i] = $("#mcq3").val();
	    			i++;
	    		}
	    		if($("#mcq4").val() != ""){
	    			choices[i] = $("#mcq4").val();
	    		}
	    	}
	    	
	    	for ( var x=0;x<choices.length;x++){
					if(choices[x] == $("#answer").val()){
						matched= "true";
						answerIndex = x;
						break;
					}
				}
			if(matched != "true"){
				document.getElementById("invalidAnswer").style.display="block";
				return;
			}
				
	    	question = { choices : choices , correctAnswer : answerIndex ,
	    				 questionStatement: $("#Qstatement").val(),time: time };
        	
        	questions[questionNum-1] = question;
       
	    	questionNum++;
	    	if(questionNum == numOfQuestions)
	    		document.getElementById("submitQuestion").value = "save";
	    	
	    	if(questionNum > numOfQuestions){
	    		
	    		$http({
    				url: "http://localhost:8090/st-comm.com/games/new",
    	    		method: "GET",
    	    		params: {gameName : gameName , courseName : courseName
    	    				,teacherName : localStorage.getItem("userName") , 
    	    				wrapper : questions }
   		    	})

	    		$('#myModal3').modal('hide');
	    		document.getElementById("submitQuestion").value = "submit";
	    	}
	    	
	    	document.getElementById("questionNum").innerHTML = "Question "+questionNum+
	    														" Out of "+numOfQuestions;
	    	$("#mcq1").val("");
	    	$("#mcq2").val("");
	    	$("#mcq3").val("");
	    	$("#mcq4").val("");
	    	$("#Qstatement").val("");
	    	$("#answer").val("");
	    }
	    
	    $scope.getAllCourses = function(){
	    	$http({
    			url: "http://localhost:8090/st-comm.com/courses/list-all",
    	    	method: "GET",
    	    	params: {registrantName : localStorage.getItem("userName") }
   		    }).then(function(response){
					$scope.allCourses = response.data;
	    		});
	    }
	    $scope.register = function(newCourse){
	    	$http({
    			url: "http://localhost:8090/st-comm.com/courses/register",
    	    	method: "GET",
    	    	params: {registrantName : localStorage.getItem("userName")
    	    			,courseName : newCourse}
   		    })
	    }   
        $(document).ready(function(){
      
        	$('#mcq').change(function () {
	        	if($(this).is(':checked')) {
	            	document.getElementById("mcq3").style.display = "block";
	            	document.getElementById("mcq4").style.display = "block";
	        	}
	    	});
	    
	    	$('#tf').change(function () {
	        	if($(this).is(':checked')) {
	            	document.getElementById("mcq3").style.display = "none";
	            	document.getElementById("mcq4").style.display = "none";
	        	}
	    	});
        	
        	document.getElementById("signOut").onclick = function(){
        		localStorage.removeItem("userName");
        		location.href="index.html";
        	}
        	document.getElementById("cancelCreateGame").onclick = function(){
        		document.getElementById("submitQuestion").value = "submit";
        	}
        })
    });

function clicked(id){
        var courseName = $(id).attr("name");
        localStorage.setItem("courseName",courseName);
        location.href = "games.html";
    }
function disable(id){
	$(id).attr("disabled","true");
}
function check(){
	if ( null == localStorage.getItem("userName"))
		location.href = "index.html";
}