angular.module('bookingApp')
.controller('headerCtrl', ['usersFactory','$scope',function(usersFactory, $scope){
	$scope.cssVar1 = "";
	$scope.cssVar2 = "";
	$scope.titleVar = "";
    $scope.user=undefined;
    $scope.idp =undefined;
    var headerViewModel = this;
    headerViewModel.functions = {
		readUser : function() {
        	usersFactory.getUser()
        		.then(function(response){
                	$scope.user = response;
					console.log("Usuario obtenido con el id ", $scope.user.id, " Response: ", response )
				}, function(response){
					console.log("No esta iniciada la sesión de usuario");
				})

		}
    }
	headerViewModel.functions.readUser();
}])