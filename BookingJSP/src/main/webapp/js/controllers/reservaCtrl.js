angular.module('bookingApp')
.controller('reservaCtrl', ['reservaFactory','alojamientosFactory','$scope', '$location',function(reservaFactory,alojamientosFactory, $scope, $location){
    $scope.$parent.cssVar1 = "../css/alojamientosuser.css";
    $scope.$parent.cssVar2 = "";
    $scope.$parent.titleVar = "Mis reservas | Booking";
    var reservaViewModel = this;
    reservaViewModel.errorMessage = "";
    reservaViewModel.Booking = [];
    reservaViewModel.BookingProperty = [];
	if($scope.$parent.user==undefined){
		$location.path('/');
        console.log("notLogged");
	}

    reservaViewModel.functions = {
        getReservas : function() {
            reservaFactory.getreservas()
                .then(function(response){
                    console.log("Getting reserva  de user data ", " Response: ", response )
                    // Calculamos el precio total al recibir las habitaciones
                    reservaViewModel.Booking = response;
                    reservaViewModel.functions.getPropertys();
                    
                }, function(response){
                    console.log("Error reading reserva data");
                    console.log(response.data.userMessage);
                });
        },
        getPropertys : function() {
            reservaViewModel.Booking.forEach(function(book, index) {
                
                alojamientosFactory.getAlojamientoBooked(book.id)
                .then(function(response){
                    console.log("Getting Property de book ",book," Response: ", response )
                    // Calculamos el precio total al recibir las habitaciones
                    reservaViewModel.BookingProperty[index]=response;
                    
                }, function(response){
                    console.log("Error reading reserva data");
                    console.log(response.data.userMessage);
                });
                
              });

        },
        deletereseva : function(id) {
            reservaFactory.deletereseva(id)
                .then(function(response){
                    console.log("deleteada la reserva  reserva  de user data ", " Response: ", response )
                    reservaViewModel.functions.getReservas();
                    reservaViewModel.Booking = response;
                }, function(response){
                    console.log("Error eliminado la  reserva resevada data");
                    console.log(response.data.userMessage);
                });
        }


    };
    reservaViewModel.functions.getReservas();
}]);
