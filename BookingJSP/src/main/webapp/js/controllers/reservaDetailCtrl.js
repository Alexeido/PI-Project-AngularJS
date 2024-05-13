angular.module('bookingApp')
.controller('reservaDetailCtrl', ['reservaFactory','reservaFactory','$scope', '$location','$routeParams',function(reservaFactory,reservaFactory, $scope, $location,$routeParams){
    $scope.$parent.cssVar1 = "../css/carrito.css";
    $scope.$parent.cssVar2 = "";
    $scope.$parent.titleVar = "Reserva | Booking";

    var idb = $routeParams.ID;
    
    var reservaDetailViewModel = this;
    reservaDetailViewModel.errorMessage = "";
    reservaDetailViewModel.user = $scope.$parent.user;
    reservaDetailViewModel.habitaciones = [];
    reservaDetailViewModel.totalprice = 0; // Inicializamos el precio total en 0

    reservaDetailViewModel.functions = {
        getcompra : function() {
            reservaFactory.getcompra(idb)
                .then(function(response){
                    console.log("Getting reserva data ", " Response: " )
                    reservaDetailViewModel.habitaciones = response;
                    // Calculamos el precio total al recibir las habitaciones
                    reservaDetailViewModel.functions.calcularPrecioTotal();
                }, function(response){
                    console.log("Error reading reserva data");
                    console.log(response.data.userMessage);
					$location.path('/reservasUsuario');
                });
        },

        // Función para calcular el precio total de todas las habitaciones
        calcularPrecioTotal: function() {
            var total = 0;
            angular.forEach(reservaDetailViewModel.habitaciones, function(habitacion) {
                total += habitacion.price;
            });
            reservaDetailViewModel.totalprice = total;
        },
		deleteinreserva : function(ida) {
            reservaFactory.deletereservaHab(idb, ida)
                .then(function(response){
                    console.log("delete de reseva habitacion data ")
					reservaDetailViewModel.functions.getcompra();
                }, function(response){
                    console.log("Error deleting a habitacion reserva data");
                    console.log(response.data.userMessage);
                });
        },
		deletereseva : function() {
            reservaFactory.deletereseva(idb)
                .then(function(response){
                    console.log("delete de reseva ", " Response: " )
					$location.path('/reservasUsuario');
                }, function(response){
                    console.log("Error deleting a reserva data");
                    console.log(response.data.userMessage);
                });
        },
		hacercompra : function() {
            reservaFactory.hacercompra(reservaDetailViewModel.totalprice)
                .then(function(response){
                    console.log("Compra realizada" )
					$location.path('/');
                }, function(response){
                    console.log("Error haciendo compra");
                    console.log(response.data.userMessage);
                });
        },

    };
    reservaDetailViewModel.functions.getcompra();

}]);
