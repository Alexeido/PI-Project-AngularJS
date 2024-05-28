angular.module('bookingApp')
.controller('registerCtrl', ['usersFactory','$scope', '$location',function(usersFactory, $scope, $location){
	$scope.$parent.cssVar1 = "../css/iniciosesion.css";
	$scope.$parent.cssVar2 = "";
	$scope.$parent.titleVar = "Registrate en Booking";
    var registerViewModel = this;
    registerViewModel.user={};
	registerViewModel.errorMessage = "";
    registerViewModel.functions = {
		
		registerUser : function() {
        	usersFactory.register(registerViewModel.user)
        		.then(function(response){
					registerViewModel.functions.readUser();
					console.log("Registrando al user con el email ", registerViewModel.user.email, " Response: ", response )
					
					$location.path('/');
				}, function(response){
					console.log(response.data.error);
                    registerViewModel.errorMessage = response.data.error;
				})

		},
		readUser : function() {
        	usersFactory.getUser()
        		.then(function(response){
					$scope.$parent.user = response;
					console.log("Getting user by id ", registerViewModel.user.id, " Response: ", response )
				}, function(response){
					console.log("Error reading user data");
				})

		}
    }
}])