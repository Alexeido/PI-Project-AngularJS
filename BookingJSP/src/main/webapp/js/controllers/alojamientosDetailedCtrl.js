// Definición del controlador 'alojamientosDetailedCtrl' dentro del módulo 'bookingApp'
angular.module('bookingApp')
.controller('alojamientosDetailedCtrl', ['alojamientosFactory', '$routeParams', '$location','$scope', function(alojamientosFactory, $routeParams, $location,$scope) {
    // ViewModel para el controlador
    var alojamientosDetailedViewModel = this;
    alojamientosDetailedViewModel.Property={};
    alojamientosDetailedViewModel.preProperty={};

	if($scope.$parent.user==undefined){
		$location.path('/');
        console.log("notLogged");
	}
    
    // Obtener el ID del alojamiento de los parámetros de la ruta
    var idAlojamiento = $routeParams.ID;
	
	
	// Definición de variables de estilo y título en el scope padre
	$scope.$parent.cssVar1 = "../css/registroAlojamiento.css";
	$scope.$parent.cssVar2 = "";
	$scope.$parent.titleVar = "Alojamiento | Booking";

     alojamientosDetailedViewModel.functions = {


    // Obtener el alojamiento para mostrar detalles
    obteneralojamiento: function(idAlojamiento){
        
        if(idAlojamiento!=undefined){
            alojamientosFactory.getAlojamiento(idAlojamiento)
            .then(function(response) {
                alojamientosDetailedViewModel.preProperty = response;

                
                if(alojamientosDetailedViewModel.preProperty.idu!=$scope.$parent.user.id){
                    console.log("notYours");
                    $location.path('/');
                }
                else{
                    alojamientosDetailedViewModel.Property=alojamientosDetailedViewModel.preProperty
                    console.log("Alojamiento detallado:", response);
                }
        }, function(response) {
            console.log("Error obteniendo alojamiento detallado");
            $location.path('/');
            });
    }
    },
    // registrar alojamiento 
    registraralojamiento: function(){
    	alojamientosFactory.registraralojamiento(alojamientosDetailedViewModel.Property)
    	.then(function(response) {
        	console.log("Alojamiento añadido detallado:" + alojamientosDetailedViewModel.Property.name , response);
        	$location.path('/');

    }, function(response) {
        console.log("Error al añadir alojamiento");
    	});
    },
    editaralojamiento: function() {
        alojamientosFactory.editaralojamiento(alojamientosDetailedViewModel.Property)
            .then(function(response){
                console.log("Alojamiento update" )
                $location.path('/alojamientosUsuario');
            }, function(response){
                console.log(response.data.userMessage);
                $location.path('/');
            })

    }
    };
	alojamientosDetailedViewModel.functions.obteneralojamiento(idAlojamiento);
}]);
