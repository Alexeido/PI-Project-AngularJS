// Definición del controlador 'busquedaCtrl' dentro del módulo 'bookingApp'
angular.module('bookingApp')
.controller('busquedaCtrl', ['alojamientosFactory', '$routeParams', '$location','$scope', function(alojamientosFactory, $routeParams, $location,$scope) {
    // ViewModel para el controlador
    var busquedaViewModel = this;
    busquedaViewModel.listaPropiedades = [];

    // Obtener el ID del alojamiento de los parámetros de la ruta
    if($routeParams.Lugar!=="all"){
    	busquedaViewModel.nameLugar = $routeParams.Lugar;
    	busquedaViewModel.actnameLugar =$routeParams.Lugar;
	}
	else{
    	busquedaViewModel.nameLugar = "Todos";
    	busquedaViewModel.actnameLugar = "Todos";
	}
    busquedaViewModel.order = $routeParams.Orden;
    busquedaViewModel.filter = $routeParams.Filtro;
    busquedaViewModel.actorder = $routeParams.Orden;
    busquedaViewModel.actfilter = $routeParams.Filtro;
	
	
	// Definición de variables de estilo y título en el scope padre
	$scope.$parent.cssVar1 = "../css/listaalojamientos.css";
	$scope.$parent.cssVar2 = "../css/buscador.css";
	$scope.$parent.titleVar = "Hoteles en "+busquedaViewModel.actnameLugar+" | Booking";

     busquedaViewModel.functions = {
	    buscar: function(){
	        // Comprobar si el cuadro de texto está vacío
	        var lugar = busquedaViewModel.nameLugar.trim();
	        // Si está vacío, redirigir sin agregar el nombre del lugar a la URL
	        if (lugar === "" || lugar==="Todos") {
	            busquedaViewModel.nameLugar="all";
	        }
	        // Convertir a números si son cadenas
	        busquedaViewModel.order = parseInt(busquedaViewModel.order);
	        busquedaViewModel.filter = parseInt(busquedaViewModel.filter);
	        // Establecer valores predeterminados si están vacíos
	        if (isNaN(busquedaViewModel.order)) {
	            busquedaViewModel.order = "0";
	        }
	        if (isNaN(busquedaViewModel.filter)) {
	            busquedaViewModel.filter = "0";
	        }
	        
	        
	        
	        alojamientosFactory.getBusquedaAlojamientos(busquedaViewModel.nameLugar, busquedaViewModel.order, busquedaViewModel.filter)
	            .then(function(response) {
					busquedaViewModel.listaPropiedades = response;
	                console.log("Subidos obtenidos:", response);
	        		console.log(busquedaViewModel.listaPropiedades);
				    if(busquedaViewModel.nameLugar!=="all"){
				    	busquedaViewModel.actnameLugar =busquedaViewModel.nameLugar ;
					}
					else{
				    	busquedaViewModel.nameLugar = "Todos";
				    	busquedaViewModel.actnameLugar = "Todos";
					}
				    busquedaViewModel.actorder = busquedaViewModel.order;
				    busquedaViewModel.actfilter = busquedaViewModel.filter;
	            }, function(response) {
	                console.log("Error en getBusquedaAlojamientos");
	            });
	    }
    
    
    };

    busquedaViewModel.functions.buscar();
}]);
