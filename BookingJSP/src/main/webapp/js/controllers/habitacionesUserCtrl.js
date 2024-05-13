// Definición del controlador 'alojamientosUserCtrl' dentro del módulo 'bookingApp'
angular.module('bookingApp')
.controller('habitacionesUserCtrl', ['habitacionesFactory','$scope', '$location','$routeParams',function(habitacionesFactory, $scope, $location, $routeParams){
    // Definición de variables de estilo y título en el scope padre
	$scope.$parent.cssVar1 = "../css/alojamientosuser.css";
	$scope.$parent.cssVar2 = "";
	$scope.$parent.titleVar = "Habitaciones | Booking";
	if($scope.$parent.user==undefined){
		$location.path('/');
        console.log("notLogged");
	}

    // ViewModel para el controlador
    var HabitacionesViewModel = this;
    var idAlojamiento = $routeParams.ID;
    $scope.$parent.idp =idAlojamiento;
    HabitacionesViewModel.listahabitacion = [];
    HabitacionesViewModel.prelistahabitacion = [];

    // Asignación del usuario del scope padre al ViewModel
    

	
	// Mensaje de error inicial vacío
    HabitacionesViewModel.errorMessage = "";

    // Definición de funciones del ViewModel
    HabitacionesViewModel.functions = {
        // Función para cargar la lista de habitaciones subidas
        cargarhabitaciones: function(){
			habitacionesFactory.getHabitacionesSubidas(idAlojamiento)
	            .then(function(response) {
					HabitacionesViewModel.prelistahabitacion = response;
                    if(HabitacionesViewModel.prelistahabitacion[0].prop.idu!=$scope.$parent.user.id){
                        console.log("notYours");
                        $location.path('/');
                    }
                    else{
                        HabitacionesViewModel.listahabitacion =  HabitacionesViewModel.prelistahabitacion;
                        console.log("habitaciones obtenidos:", response);

                    }
	            }, function(response) {
	                console.log("Error en gethabitaciones");
	            });
		},
        deletehabitacion: function(idp) {
            habitacionesFactory.delhabitaciones(idp)
            .then(function(response) {
                console.log("habitacion con idp:"+idp+" eliminado", response);
    			HabitacionesViewModel.functions.cargarhabitaciones();
            }, function(response) {
                console.log("Error en delhabitacion");
            }); 
        } 
    };
    HabitacionesViewModel.functions.cargarhabitaciones();
}])
 