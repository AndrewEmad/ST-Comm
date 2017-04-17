
var app = angular.module('main',[]);    	
app.controller('ctrl', function($scope, $http) {
        /*var data = [{"name" : "course1"},{"name" : "course2"}];
        $scope.courses = data;*/
        
        var courseName;
        var gameName;
        var questions;
        var question;
        var numOfQuestions;
        var questionNum;
        
        $scope.username = localStorage.getItem("userName");
        
        $http({
    		url: "http://localhost:8090/st-comm.com/query/registrant-type",
    	    method: "GET",
    	    params: {name : localStorage.getItem("userName")}
   		    }).then(function(response){
	    		if(response.data == "teacher"){
					document.getElementById("createCourse").style.display = "block";
        			document.getElementById("createGame").style.display = "block";	    		
	    		}
	    	});
    		
    		/*if(true){
        		document.getElementById("createCourse").style.display = "block";
        		document.getElementById("createGame").style.display = "block";
    		}*/
        
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
   		    		// if not unique 
   		    	}
	    		
	    		});
	    }
	    
	    $scope.createGame=function(){
	    	questionNum = 1;
	    	questions =[];
	    	
	    	courseName = document.getElementById("courseName").value;
	    	gameName = document.getElementById("gameName").value;
	    	numOfQuestions = document.getElementById("numOfQuestions").value;
	    	document.getElementById("questionNum").innerHTML = "Question "+questionNum+
	    														" Out of "+numOfQuestions;
	    	$('#myModal2').modal('hide');
	    	$('#myModal3').modal('show');
			
	    }
	    $scope.submitQuestion=function(){
	    	var choices =[];
	    	var i =0;
	    	if($("input[name=Qtype]:checked").val() == "MCQ"){
	    		if($("#mcq1").val() != ""){
	    			choices[i] = $("#mcq1").val();
	    			i++;
	    		}
	    		if($("#mcq2").val() != ""){
	    			choices[i] = $("#mcq2").val();
	    			i++;
	    		}
	    		if($("#mcq3").val() != ""){
	    			choices[i] = $("#mcq3").val();
	    			i++;
	    		}
	    		if($("#mcq4").val() != ""){
	    			choices[i] = $("#mcq4").val();
	    		}
	    	}
	    	else{
	    		choices[0]="true";
	    		choices[1]="false";
	    	}
	    	question = { choices : choices , correctAnswer : $("#answer").val() ,
	    				 questionStatement: $("#Qstatement").val() };
        	
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
    	    				questions : questions }
   		    	})
	  
	    		/*for(var x=0;x<questions.length;x++){
	    			alert(questions[x].type);
	    			alert(questions[x].questionStatement);
	    			alert(questions[x].correctAnswer);
	    			alert(questions[x].choices[0]);
	    		}*/
	    		alert("the game saved successfully");
	    		$('#myModal3').modal('hide');
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
    	    	params: {registrantName : localStorage.getItem("userName")}
   		    }).then(function(response){
					$scope.allCourses = response.data;
	    		});
	    	
	    	var dataa =["course3","course4","course5"];
	    	$scope.allCourses = dataa;
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
	        		document.getElementById("choices").style.display = "block"
	            	document.getElementById("mcqChoices").style.display = "block"
	        	}
	    	});
	    
	    	$('#tf').change(function () {
	        	if($(this).is(':checked')) {
	            	document.getElementById("mcqChoices").style.display = "none"
	            	document.getElementById("choices").style.display = "none"
	        	}
	    	});
        	
        	document.getElementById("signOut").onclick = function(){
        		localStorage.removeItem("userName");
        		location.href="index.html";
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