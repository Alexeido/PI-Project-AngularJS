// Definición del controlador 'alojamientosDetailedCtrl' dentro del módulo 'bookingApp'
angular.module('bookingApp')
.controller('habitacionesDetailedCtrl', ['habitacionesFactory', '$routeParams', '$location','$scope', function(habitacionesFactory, $routeParams, $location,$scope) {
    // ViewModel para el controlador
    var habitacionDetailedViewModel = this;
    habitacionDetailedViewModel.Accommodation={};
    habitacionDetailedViewModel.preAccommodation={};
    var idhabitacion = $routeParams.ID;

	if($scope.$parent.user==undefined){
		$location.path('/');
        console.log("notLogged");
	}
	
	// Definición de variables de estilo y título en el scope padre
	$scope.$parent.cssVar1 = "../css/registroAlojamiento.css";
	$scope.$parent.cssVar2 = "";
	$scope.$parent.titleVar = "Habitacion | Booking";

    habitacionDetailedViewModel.functions = {

        obtenerhabitacion: function(idhabitacion){
            
            if(idhabitacion!=undefined){
                
                habitacionesFactory.getHabitacion(idhabitacion)
                .then(function(response) {
                    
                    habitacionDetailedViewModel.preAccommodation = response;
                
                    if(habitacionDetailedViewModel.preAccommodation.prop.idu!=$scope.$parent.user.id){
                        console.log("notYours");
                        $location.path('/');
                    }
                    else{
                        habitacionDetailedViewModel.Accommodation=habitacionDetailedViewModel.preAccommodation
                        console.log("habitacion detallado:", response);
                    }
                }, function(response) {
                        console.log(response.data.userMessage);
                        $location.path('/');
                    
                    });
        }
        },
    // registrar habitacion 
    registrarhabitacion: function(){
    	habitacionesFactory.registrarhabitacion(habitacionDetailedViewModel.Accommodation)
    	.then(function(response) {
        	console.log("habitcion añadido detallado:" + habitacionDetailedViewModel.Accommodation.name , response);
            if($scope.$parent.idp==undefined){
                $location.path('/alojamientosUsuario');
            }
            $location.path('/habitacionesUsuario/'+$scope.$parent.idp);
    }, function(response) {
        console.log("Error al añadir alojamiento");
    	});
    },
    editaralojamiento: function() {
        habitacionesFactory.editaralhabitacion(habitacionDetailedViewModel.Accommodation)
            .then(function(response){
                console.log("habitacion update" )
                $location.path('/habitacionesUsuario/'+habitacionDetailedViewModel.Accommodation.idp);
            }, function(response){
                console.log("No hay update de habitacion que valga");
            })

    }
    };
    habitacionDetailedViewModel.functions.obtenerhabitacion(idhabitacion);
}]);
