angular.module('bookingApp')
.controller('carritoCtrl', ['carritoFactory','reservaFactory','$scope', '$location',function(carritoFactory,reservaFactory, $scope, $location){
    $scope.$parent.cssVar1 = "../css/carrito.css";
    $scope.$parent.cssVar2 = "";
    $scope.$parent.titleVar = "Carrito | Booking";

    
    var carritoViewModel = this;
    carritoViewModel.errorMessage = "";
    carritoViewModel.user = $scope.$parent.user;
    carritoViewModel.habitaciones = [];
    carritoViewModel.totalprice = 0; // Inicializamos el precio total en 0
	if($scope.$parent.user==undefined){
		$location.path('/');
        console.log("notLogged");
	}

    carritoViewModel.functions = {
        getcompra : function() {
            carritoFactory.getcompra()
                .then(function(response){
                    console.log("Getting reserva data ", " Response: ", response )
                    carritoViewModel.habitaciones = response;
                    // Calculamos el precio total al recibir las habitaciones
                    carritoViewModel.functions.calcularPrecioTotal();
                }, function(response){
                    console.log("Error reading reserva data");
                });
        },

        // Función para calcular el precio total de todas las habitaciones
        calcularPrecioTotal: function() {
            var total = 0;
            angular.forEach(carritoViewModel.habitaciones, function(habitacion) {
                total += habitacion.price;
            });
            carritoViewModel.totalprice = total;
        },
		deleteincarrito : function(ida) {
            carritoFactory.deleteincarrito(ida)
                .then(function(response){
                    console.log("delete de reseva habitacion data ", " Response: ", response )
					carritoViewModel.functions.getcompra();
					$location.path('/carrito');
                }, function(response){
                    console.log("Error deleting a habitacion reserva data");
                });
        },
		hacercompra : function() {
            reservaFactory.hacercompra(carritoViewModel.totalprice)
                .then(function(response){
                    console.log("Compra realizada", " Response: ", response )
					$location.path('/');
                }, function(response){
                    console.log("Error haciendo compra");
                });
        },

    };
    carritoViewModel.functions.getcompra();

}]);
