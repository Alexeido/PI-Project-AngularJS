angular.module('bookingApp')
.controller('perfilCtrl', ['usersFactory','$scope', '$location',function(usersFactory, $scope, $location){
	$scope.$parent.cssVar1 = "../css/perfil.css";
	$scope.$parent.cssVar2 = "";
	$scope.$parent.titleVar = "Mi perfil | Booking";
    var perfilViewModel = this;
    perfilViewModel.user=$scope.$parent.user;
	perfilViewModel.errorMessage = "";
	if($scope.$parent.user==undefined){
		$location.path('/');
        console.log("notLogged");
	}
    perfilViewModel.functions = {
		
		editUser : function() {
        	usersFactory.editPerfil(perfilViewModel.user)
        		.then(function(response){
					perfilViewModel.functions.readUser();
					$scope.$parent.user=perfilViewModel.user;
					console.log("Actualizando al user con el id ", perfilViewModel.user.id, " Response: ", response );
					$location.path('/perfil');
				}, function(response){
					console.log(response.data.userMessage);
                    perfilViewModel.errorMessage = response.data.userMessage;
					perfilViewModel.functions.readUser();
					$scope.$parent.user=perfilViewModel.user;
				})

		},
		cerrarSesion :function(){
			usersFactory.cerrarSesion()
				.then(function(response){
					perfilViewModel.user = response;
					console.log("Cerrada la sesión" );
					$scope.$parent.user=undefined;
					$location.path('/');
				}, function(response){
					console.log("Nunca hubo una sesión abierta");
					$scope.$parent.user=undefined;
					$location.path('/');
				})
			
		},
		eliminarUser :function(){
			usersFactory.eliminarUser($scope.$parent.user)
				.then(function(response){
					perfilViewModel.user = response;
					console.log("Usuario eliminado" );
					perfilViewModel.functions.cerrarSesion();
				}, function(response){
					console.log("Ha habido un error al borrar la cuenta");
					$scope.$parent.user=undefined;
					$location.path('/');
				})
			
		},
		readUser : function() {
        	usersFactory.getUser()
        		.then(function(response){
					perfilViewModel.user = response;
					console.log("Getting user by id ", perfilViewModel.user.id, " Response: ", response )
				}, function(response){
					console.log("Error reading user data");
				})

		}
    }
}])