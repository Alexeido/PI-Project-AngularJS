// Definición del controlador 'alojamientosUserCtrl' dentro del módulo 'bookingApp'
angular.module('bookingApp')
.controller('alojamientosUserCtrl', ['favoritosFactory','alojamientosFactory','$scope', '$location',function(favoritosFactory, alojamientosFactory, $scope, $location){
    // Definición de variables de estilo y título en el scope padre
	$scope.$parent.cssVar1 = "../css/alojamientosuser.css";
	$scope.$parent.cssVar2 = "";
	$scope.$parent.titleVar = "Mis alojamientos | Booking";

    // ViewModel para el controlador
    var alojamientoUserViewModel = this;
    
    // Asignación del usuario del scope padre al ViewModel
    alojamientoUserViewModel.user = $scope.$parent.user;
	if($scope.$parent.user==undefined){
		$location.path('/');
        console.log("notLogged");
	}
    
    // Inicialización de listas
    alojamientoUserViewModel.listaFavs = [];
    alojamientoUserViewModel.listaReservas = [];
    alojamientoUserViewModel.listaPropiedades = [];
	
	// Mensaje de error inicial vacío
    alojamientoUserViewModel.errorMessage = "";

    // Definición de funciones del ViewModel
    alojamientoUserViewModel.functions = {
        // Función para cargar la lista de favoritos
		cargarListaFav: function() {
            favoritosFactory.getFavoritos()
            .then(function(response) {
                alojamientoUserViewModel.listaFavs = response;
                console.log("Favoritos obtenidos:", response);
            }, function(response) {
                console.log("Error en getFavoritos");
            });
        },
        
        // Función para eliminar un favorito
        deleteFav: function(idp) {
            favoritosFactory.delFavorito(idp)
            .then(function(response) {
                console.log("Favorito con idp:"+idp+" eliminado", response);
    			alojamientoUserViewModel.functions.cargarListaFav();
            }, function(response) {
                console.log("Error en deleteFav");
            });
        },
        
        // Función para cargar la lista de propiedades subidas
        cargarListaSubidos: function(){
			alojamientosFactory.getAlojamientosSubidos()
	            .then(function(response) {
					alojamientoUserViewModel.listaPropiedades = response;
	                console.log("Subidos obtenidos:", response);
	            }, function(response) {
	                console.log("Error en getAlojamientosSubidos");
	            });
		},
		
        // Función para eliminar una propiedad
        deleteAlojamiento: function(idp) {
            alojamientosFactory.delAlojamiento(idp)
            .then(function(response) {
                console.log("Alojamiento con idp:"+idp+" eliminado", response);
    			alojamientoUserViewModel.functions.cargarListaSubidos();
            }, function(response) {
                console.log("Error en deleteAlojamiento");
            });
        },
        
        // Función para cambiar la disponibilidad de una propiedad
        cambiarDisponibilidadAlojamiento: function(idp) {
            alojamientosFactory.cambiarDisponibilidad(idp)
            .then(function(response) {
                console.log("Alojamiento con idp:"+idp+" alterado", response);
    			alojamientoUserViewModel.functions.cargarListaSubidos();
            }, function(response) {
                console.log("Error en cambiar Disponibilidad");
            });
        },

		// Función para obtener datos del usuario
        readUser: function() {
        	usersFactory.getUser()
        		.then(function(response){
					alojamientoUserViewModel.user = response;
					console.log("Getting user by id ", alojamientoUserViewModel.user.id, " Response: ", response )
				}, function(response){
					console.log("Error reading user data");
				})

		}
    };

    // Carga inicial de las listas de favoritos y propiedades subidas
    alojamientoUserViewModel.functions.cargarListaFav();
    alojamientoUserViewModel.functions.cargarListaSubidos();
}])
