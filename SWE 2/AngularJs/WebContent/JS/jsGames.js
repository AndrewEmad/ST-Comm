var app = angular.module('main',[]);    	
app.controller('ctrl', function($scope, $http) {
	var gameName;
	var questions;
	var numOfQuestions;
	var questionNum;
	var score;
	
	$scope.username = localStorage.getItem("userName");
	
	$http({
		url: "http://localhost:8090/st-comm.com/games/courses/list-by-course",
	    method: "GET",
	    params: {courseName : localStorage.getItem("courseName")}
		}).then(function(response){
    		$scope.games = response.data;
		})
	
	/*$scope.games = ["game1","game2","game3"];*/
	
	$scope.playGame =function(GameName) {
		gameName = GameName;
		questionNum=1;
		//numOfQuestions =2;
		score = 0;
		
		$http({
			url: "http://localhost:8090/st-comm.com/games/play",
		    method: "GET",
		    params: {gameName : gameName}
			}).then(function(response){
				numOfQuestions = response.data.numOfQuestions;
				questions = response.data.questions;
			})
		
		
		/*questions = [{questionStatement: "how are you?" , correctAnswer: 0 ,
			choices : ["fine","bad"]},{questionStatement: "what's your name?" , 
				correctAnswer: 1 , choices : ["ahmed","omar"]}];*/
			
		$scope.gameName = gameName;
		
        document.getElementById("questionNum").innerHTML = "Question "+questionNum+
        													" Out of "+numOfQuestions;
        document.getElementById("score").innerHTML = "Score 0";
        
        $scope.question = questions[questionNum-1].questionStatement;
        $scope.choices = questions[questionNum-1].choices;
    };
    $scope.submitAnswer =function(){
    	if($("input[name=choices]:checked").val() == 
    		questions[questionNum-1].choices[questions[questionNum-1].correctAnswer]){
    		score++;
    		document.getElementById("score").innerHTML = "Score "+ score;
    	}
    	else{
    		 
    	}
    	if(questionNum == numOfQuestions){
    		$('#submitAnswer').prop('disabled', true);
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
    	$('#submitAnswer').prop('disabled', false);
    	
    	/* save score only if user is a student*/
    	$http({ 
    		url: "http://localhost:8090/st-comm.com/query/registrant-type",
    	    method: "GET",
    	    params: {name : localStorage.getItem("userName")}
   		    }).then(function(response){
	    		if(response.data == "student"){
	    			$http({
	    				url: "http://localhost:8090/st-comm.com/games/scores/save",
	    			    method: "GET",
	    			    params: {name : localStorage.getItem("userName"),
	    			    	score : score , gameName : gameName}
	    			})
	    		}   		
	    	})
    }
    document.getElementById("signOut").onclick = function(){
		localStorage.removeItem("userName");
		location.href="index.html";
	}
});
