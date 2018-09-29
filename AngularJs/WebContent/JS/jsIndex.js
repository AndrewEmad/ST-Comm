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

(function ($) {
    "use strict";
    var mainApp = {
        scrollAnimation_fun: function () {
    
            window.scrollReveal = new scrollReveal();

        },
         scroll_fun: function () {

            /*====================================
                 EASING PLUGIN SCRIPTS 
                ======================================*/
            $(function () {
                $('.move-me a').bind('click', function (event) { //just pass move-me in design and start scrolling
                    var $anchor = $(this);
                    $('html, body').stop().animate({
                        scrollTop: $($anchor.attr('href')).offset().top
                    }, 1000, 'easeInOutQuad');
                    event.preventDefault();
                });
            });

        },

         top_flex_slider_fun:function()
         {
             /*====================================
              FLEX SLIDER SCRIPTS 
             ======================================*/
             $('#main-section').flexslider({
                 animation: "fade", //String: Select your animation type, "fade" or "slide"
                 slideshowSpeed: 5000,           //Integer: Set the speed of the slideshow cycling, in milliseconds
                 animationSpeed: 1000,           //Integer: Set the speed of animations, in milliseconds
                 startAt: 0,    //Integer: The slide that the slider should start on. Array notation (0 = first slide)

             });
         }

    }
   
   
    $(document).ready(function () {
        mainApp.scrollAnimation_fun();
        mainApp.scroll_fun();
        mainApp.top_flex_slider_fun();
        mainApp.custom_fun();
    });
}(jQuery));


