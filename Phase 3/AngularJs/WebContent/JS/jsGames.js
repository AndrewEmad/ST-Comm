var app = angular.module('main',[]);    	
app.controller('ctrl', function($scope, $http) {
	var gameName;
	var newGameName;
	var questions = [];
	var numOfQuestions;
	var newNumOfQ;
	var questionNum;
	var score;
	var time;
	var copiedGameName;
	var gameVersion;
	
    $scope.username = localStorage.getItem("userName");

        $http({
            url: "http://localhost:8090/st-comm.com/games/courses/list-by-course",
            method: "GET",
            params: {courseName : localStorage.getItem("courseName")}
            }).then(function(response){
                $scope.games = response.data;
                $http({
        	        url: "http://localhost:8090/st-comm.com/query/registrant-type",
        	        method: "GET",
        	        params: {name : localStorage.getItem("userName")}
        	            }).then(function(response){
        		            if(response.data != 1){
        		            	$(".M").show();	    		
        		            }
        	            });
            })
            
        
            
            //$scope.games=["game","game2"];  
            
    $(document).ready(function(){
		
    	//$(".M").show();
    	
        $("#newCourseName , #newGameName").keyup(function(){
            document.getElementById("errorCopy").style.display ="none";
        });
        
        $("#mcq12, #mcq22, #mcq32, #mcq42, #answer2").keyup(function(){
			document.getElementById("invalidAnswer2").style.display ="none";
		});
        
        $("#gameName2").keyup(function(){
			document.getElementById("invalidGameName2").style.display ="none";
		});
        
        $('#mcq2').change(function () {
        	if($(this).is(':checked')) {
            	document.getElementById("mcq32").style.display = "block";
            	document.getElementById("mcq42").style.display = "block";
        	}
    	});
    
    	$('#tf2').change(function () {
        	if($(this).is(':checked')) {
            	document.getElementById("mcq32").style.display = "none";
            	document.getElementById("mcq42").style.display = "none";
        	}
    	});

        $scope.playGame =function(GameName) {
            gameName = GameName;
            questionNum=1;
            score = 0;

            $http({
                url: "http://localhost:8090/st-comm.com/games/play",
                method: "GET",
                params: {gameName : gameName , courseName : localStorage.getItem("courseName")}
                }).then(function(response){
                    numOfQuestions = response.data.numOfQuestions;
                    questions = response.data.questions;
                    $scope.gameName = gameName;

                    document.getElementById("questionNum").innerHTML = "Question "+questionNum+
                                                                        " Out of "+numOfQuestions;
                    document.getElementById("score").innerHTML = "Score 0";

                    $scope.question = questions[questionNum-1].questionStatement;
                    $scope.choices = questions[questionNum-1].choices;

                    time = questions[questionNum-1].time;

                    var counter = setInterval(function(){ 
                        time--;
                        document.getElementById("time").innerHTML = time + " left";
                      if(time == 0){
                        clearInterval(counter);
                        $scope.submitAnswer();
                       }
                    }, 1000);

                })
        };
        $scope.submitAnswer =function(){
            if($("input[name=choices]:checked").val() == 
                questions[questionNum-1].choices[questions[questionNum-1].correctAnswer]){
                score++;
                document.getElementById("score").innerHTML = "Score "+ score;
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

                time = questions[questionNum-1].time;

                var counter = setInterval(function(){ 
                    time--;
                    document.getElementById("time").innerHTML = time + " left";
                  if(time == 0){
                    clearInterval(counter);
                    $scope.submitAnswer();
                   }
                }, 1000);
            }

        };
        
        $scope.editGame = function(GameName){
        	gameName = GameName;
        	$http({
                url: "http://localhost:8090/st-comm.com/games/get",
                method: "GET",
                params: {gameName : gameName}
                }).then(function(response){
                	questionNum = 1;
                	numOfQuestions = response.data.numOfQuestions;
                    questions = response.data.questions;
                    gameVersion = response.version;
                    $('#myModal3').modal('show');
                    $("#gameName2").val(gameName);
                    $("#numOfQuestions2").val(numOfQuestions);
                })
                
            /*questionNum = 1;
        	numOfQuestions = 1;
        	question = { choices : ["Ahmed","Ayman"] , correctAnswer : 0 ,
   				 questionStatement: "what's your name ?",time: 5 };
        	questions[0] = question; 
        	question = { choices : ["choice1","not Good","fine"] , correctAnswer : 2 ,
      				 questionStatement: "how are you ?",time: 6 };
            questions[0] = question;
            
            $('#myModal3').modal('show');
            
            $("#gameName2").val(gameName);
            $("#numOfQuestions2").val(numOfQuestions);*/
        };
        
        $scope.editGameInfo = function(){
        	
        	/*newGameName = $("#gameName2").val();
        	newNumOfQ = $("#numOfQuestions2").val();
        	alert(newGameName);
        	alert(newNumOfQ);
        	
        	$('#myModal3').modal('hide');
			$('#myModal4').modal('show');
			 
            $scope.editQuestions();*/
				
        	$http({
				url: "http://localhost:8090/st-comm.com/games/exists",
    			method: "GET",
    			params: {gameName : newGameName , courseName : localStorage.getItem("courseName")}
	    		}).then(function(response){
	    			if(!response.data){
	    				newGameName = $("#gameName2").val();
	    	        	newNumOfQ = $("#numOfQuestions2").val();
						$('#myModal3').modal('hide');
						$('#myModal4').modal('show');
						$scope.editQuestions();
	    			}
	    			else{
	    				document.getElementById("invalidGameName2").style.display ="block";
	    			}
	    		})
        }
        
        $scope.editQuestions = function(){
        	
        	var choices =[];
	    	var answerIndex;
	    	var time;
	    	var matched;
	    	
	    	if(questionNum != 1){ // to not validate empty boxes for first time
	    		time = document.getElementById("time").value;
		    	choices[0] = $("#mcq12").val();
		    	choices[1] = $("#mcq22").val();
		    	
		    	if($("input[name=Qtype]:checked").val() == "MCQ"){
						    	
		    		var i =2;
		    		
		    		if($("#mcq32").val() != ""){
		    			choices[i] = $("#mcq32").val();
		    			i++;
		    		}
		    		if($("#mcq42").val() != ""){
		    			choices[i] = $("#mcq42").val();
		    		}
		    	}
		    	
		    	for ( var x=0;x<choices.length;x++){
						if(choices[x] == $("#answer2").val()){
							matched= "true";
							answerIndex = x;
							break;
						}
					}
				if(matched != "true"){
					document.getElementById("invalidAnswer2").style.display="block";
					return;
				}
				question = { choices : choices , correctAnswer : answerIndex ,
	    				 questionStatement: $("#Qstatement2").val(),time: time };
       	
				questions[questionNum-2] = question;
				
				/*alert(questions[0].choices[0]);
				alert(questions[0].choices[1]);
				alert(questions[0].correctAnswer);
				alert(questions[0].questionStatement);
				alert(questions[0].choices[2]);
				alert(questions[0].choices.length);*/
	    	}

        	if(questionNum > newNumOfQ){
        		$http({
                    url: "http://localhost:8090/st-comm.com/games/edit",
                    method: "GET",
                    params: {newGameName : gameName ,
                    	oldGameName : newGameName,
                    	courseName : localStorage.getItem("courseName"),
                    	teacherName : localStorage.getItem("userName"),
                    	wrapper : questions,
                    	version : gameVersion
                    }
                    }).then(function(response){
                    	$('#myModal4').modal('hide');
        	    		document.getElementById("editQuestion").value = "Edit";
        	    		return;
                    })
        	}
        	if(questionNum == newNumOfQ){
	    		$("#editQuestion").val("Finish editing");
        	}
        	
        	document.getElementById("questionNum2").innerHTML = "Question "+questionNum+
			" Out of "+newNumOfQ;
        	
        	if(questionNum <= numOfQuestions){
        		$("#Qstatement2").val(questions[questionNum-1].questionStatement);
            	
            	$("#mcq12").val(questions[questionNum-1].choices[0]);
            	$("#mcq22").val(questions[questionNum-1].choices[1]);
            	
            	if(questions[questionNum-1].choices.length > 2){
            		$("#mcq2").attr('checked', 'checked');
            		document.getElementById("mcq32").style.display = "block";
                	document.getElementById("mcq42").style.display = "block";
                	$("#mcq32").val(questions[questionNum-1].choices[2]);
                	$("#mcq42").val(questions[questionNum-1].choices[3]);
            	}
            	
            	$("#answer2").val(questions[questionNum-1].choices
            			[questions[questionNum-1].correctAnswer]);
            	$("#time2").val(questions[questionNum-1].time);
        	}
        	else{
        		$("#Qstatement2").val("");
            	$("#mcq12").val("");
            	$("#mcq22").val("");
            	$("#mcq32").val("");
            	$("#mcq42").val("");
            	$("#answer2").val("");
            	$("#time2").val("1");
        	}
        	
        	questionNum++;
        }

        document.getElementById("closeGame").onclick = function(){
            $('#submitAnswer').prop('disabled', false);

            /* save score only if user is a student*/
            $http({ 
                url: "http://localhost:8090/st-comm.com/query/registrant-type",
                method: "GET",
                params: {name : localStorage.getItem("userName")}
                }).then(function(response){
                    if(response.data == 1){
                        $http({
                            url: "http://localhost:8090/st-comm.com/games/scores/save",
                            method: "GET",
                            params: {name : localStorage.getItem("userName"),
                                score : score , gameName : gameName}
                        })
                    }   		
                })
        }

        $scope.openMenu = function(gameName){
            document.getElementById(gameName).classList.toggle("show");
        }

        $scope.cancelGame = function(gameName){
            $http({ 
                url: "http://localhost:8090/st-comm.com/games/cancel",
                method: "GET",
                params: {gameName : gameName , 
                	courseName : localStorage.getItem("courseName"),
                	teacherName : localStorage.getItem("userName")}
                }).then(function(response){
                    location.reload();   		
                })
        }

        $scope.rememberGame = function(GameName){
            copiedGameName = GameName;
        }

        $scope.copyGame = function(){

            $http({ 
                url: "http://localhost:8090/st-comm.com/games/copy",
                method: "GET",
                params: {oldGameName : copiedGameName , 
                    newGameName : $scope.newGameName,
                    sourceCourse : localStorage.getItem("courseName") ,
                    destinationCourse : $scope.newCourseName , 
                    newTeacherName : localStorage.getItem("userName")}
                }).then(function(response){
                    if(response)
                        $('#myModal2').modal('hide');
                    else{
                        document.getElementById("errorCopy").style.display ="block";
                    }
                })
        }
        
        $scope.getLogs = function(){
        	$http({ 
                url: "http://localhost:8090/st-comm.com/games/log",
                method: "GET"
                }).then(function(response){
                	$("#myModal5").modal('show');    
                	for(var i =0;i<response.data.length;i++){          		
                		if(response.data[i].operation == "Collaborator Added" 
                    		|| response.data[i].operation == "Collaborator Removed"){
                    		response.data[i].operation += " ("+response.data[i].collaboratorName+")";
                    	}
                	}
                    $scope.logs = response.data;
                })
        }
        $scope.undoOperation = function(id){
        	alert(id);
        	$http({ 
                url: "http://localhost:8090/st-comm.com/games/undo",
                method: "GET",
                params:{msgId : id , teacherName : localStorage.getItem("userName")}
                }).then(function(response){
                	document.getElementById(id).disabled = true;
                })
        }
        
        $scope.addColl = function(){
        	$http({ 
                url: "http://localhost:8090/st-comm.com/games/collaborators/new",
                method: "GET",
                params:{teacherName : localStorage.getItem("userName"),
                		collaboratorName : $scope.collName,
                		gameName : copiedGameName , 
                		courseName : localStorage.getItem("courseName")
                }
                }).then(function(response){
                	$("#myModal6").modal('hide');
                })
        }
        
        document.getElementById("signOut").onclick = function(){
            localStorage.removeItem("userName");
            location.href="index.html";
        }
	})
});

window.onclick = function(event) {
  if (!event.target.matches('.dropbtn')) {

    var dropdowns = document.getElementsByClassName("dropdown-content");
    var i;
    for (i = 0; i < dropdowns.length; i++) {
      var openDropdown = dropdowns[i];
      if (openDropdown.classList.contains('show')) {
        openDropdown.classList.remove('show');
      }
    }
  }
}
function check(){
	if ( null == localStorage.getItem("userName"))
		location.href = "index.html";
}