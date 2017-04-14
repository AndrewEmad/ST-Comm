
var app = angular.module('main',[]);    	
app.controller('ctrl', function($scope, $http) {
	var questions;
	var numOfQuestions;
	var questionNum;
	var score;
	/*$http({
		url: "http://localhost:8090/st-comm.com/getgames",
	    method: "GET",
	    params: {registrantName : localStorage.getItem("userName")}
		}).then(function(response){
    		$scope.courses = response.data;
		})*/
	
	/*var name =localStorage.getItem("CourseName");
	alert(name);*/
	$scope.games = ["subway","taken"];
	
	$scope.playGame =function(gameName) {
		questionNum=1;
		numOfQuestions =2;
		score = 0;
		// call getgame service and numOfquestions
		questions = [{questionStatement: "how are you?" , correctAnswer: "fine" ,
			choices : ["fine","bad"]},{questionStatement: "what's your name?" , 
				correctAnswer: "ahmed" , choices : ["ahmed","omar"]}];
		//alert(gameName);
		
		
        document.getElementById("playGameForm").style.display = "block";
        document.getElementById("questionNum").innerHTML = "Question "+questionNum+
        													" Out of "+numOfQuestions;
        document.getElementById("score").innerHTML = "Score 0";
        
        $scope.question = questions[questionNum-1].questionStatement;
        $scope.choices = questions[questionNum-1].choices;
    };
    $scope.submitAnswer =function(){
    	if($("input[name=choices]:checked").val() == questions[questionNum-1].correctAnswer){
    		score++;
    		document.getElementById("score").innerHTML = "Score "+ score;
    	}
    	else{
    		//document.getElementById("answer").innerHTML = "correct answer is " 
    				//						+ questions[0].correctAnswer; 
    	}
    	if(questionNum == numOfQuestions){
    		$('#submitAnswer').prop('disabled', true);
    		//document.getElementById("playGameForm").style.display = "none";
    	}
    	else{
    		questionNum++;
    		document.getElementById("questionNum").innerHTML = "Question " + questionNum+
    															" Out of "+numOfQuestions;
    		$scope.question = questions[questionNum-1].questionStatement;
            $scope.choices = questions[questionNum-1].choices;
    	}
    	
    };
    document.getElementById("closeGame").onclick = function(){
    	document.getElementById("playGameForm").style.display = "none";
    	$('#submitAnswer').prop('disabled', false);
    	//call save score service
    }
});
