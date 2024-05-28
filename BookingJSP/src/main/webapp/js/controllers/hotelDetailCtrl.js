// Definición del controlador 'hotelDetailCtrl' dentro del módulo 'bookingApp'
angular.module('bookingApp')
.controller('hotelDetailCtrl', ['alojamientosFactory','favoritosFactory','habitacionesFactory', 'reviewFactory','carritoFactory', '$routeParams', '$location','$scope','$timeout', '$anchorScroll', function(alojamientosFactory,favoritosFactory,habitacionesFactory, reviewFactory,carritoFactory, $routeParams, $location,$scope, $timeout, $anchorScroll) {
	$scope.$parent.cssVar1 = "../css/alojamiento.css";
	$scope.$parent.cssVar2 = "";
    // ViewModel para el controlador
    var hotelDetailViewModel = this;
    hotelDetailViewModel.alojamiento={};
    hotelDetailViewModel.lugarBusqueda="";
    hotelDetailViewModel.listaReviews=[];
    hotelDetailViewModel.listaAccommodations=[];
    hotelDetailViewModel.listaSimilares=[];
    hotelDetailViewModel.canComment=true;
    hotelDetailViewModel.myReview={};
	hotelDetailViewModel.user=$scope.$parent.user;
    // Obtener el ID del alojamiento de los parámetros de la ruta
    var idAlojamiento = $routeParams.ID;
	
    //SECCION EXCLUSIVA DE LA ALERTA AL DARLE A RESERVER

    $scope.showNotification = false;
    
    $scope.closeNotification = function() {
        $timeout.cancel($scope.notificationTimeout); 
        $scope.showNotification = false;
    };
    

    // Función para mostrar la notificación y luego ocultarla después de un tiempo determinado
    $scope.showAddToCartNotification = function() {
        $scope.showNotification = true;
        // Oculta la notificación después de 3 segundos
        $scope.notificationTimeout = $timeout(function() {
            $scope.showNotification = false;
        }, 1300);
    };
	$scope.scrollToDisponibilidad = function() {
	    $location.hash('disponibilidad');
	    $anchorScroll();
	};
	$scope.scrollToDescripcion = function() {
	    $location.hash('descripcion');
	    $anchorScroll();
	};
	$scope.scrollToComentarios = function() {
	    $location.hash('comentarios');
	    $anchorScroll();
	};
	
	// Definición de variables de estilo y título en el scope padre
	$scope.$parent.titleVar = hotelDetailViewModel.alojamiento.name+" | Booking";

     hotelDetailViewModel.functions = {


    // Obtener el alojamiento para mostrar detalles
    obteneralojamiento: function(idAlojamiento){
    	alojamientosFactory.getAlojamiento(idAlojamiento)
    	.then(function(response) {
        	hotelDetailViewModel.alojamiento = response;
            hotelDetailViewModel.numEstrellas = Math.floor(hotelDetailViewModel.alojamiento.gradesAverage);
            hotelDetailViewModel.functions.obtenerFav();
            hotelDetailViewModel.functions.obtenerReviews();
			$scope.$parent.titleVar = hotelDetailViewModel.alojamiento.name+" | Booking";
            hotelDetailViewModel.functions.comprobarReview();
            hotelDetailViewModel.functions.obtenerAccommodations();
            hotelDetailViewModel.functions.obtenerSimilares();
        	console.log("Alojamiento detallado:", response);
    }, function(response) { 
        console.log("Error obteniendo alojamiento detallado");
    	});
    },
    obtenerAccommodations: function(){
    	habitacionesFactory.getHabitacionesSubidas(idAlojamiento)
    	.then(function(response) {
        	hotelDetailViewModel.listaAccommodations = response;
        	console.log("Habitaciones detalladas:", response);
    }, function(response) { 
        console.log("Error obteniendo Habitaciones detalladas");
    	});
    },
    obtenerSimilares: function(){
        alojamientosFactory.getBusquedaAlojamientos(hotelDetailViewModel.alojamiento.city, 0, 0)
        .then(function(response) {
            if (response.length > 4) {
                // Si tiene más de 4 elementos, cortamos la lista para que tenga solo 4 elementos
                console.log("alojamientos similares 4 o mas asi que corto:", response);
                hotelDetailViewModel.listaSimilares = response.slice(0, 4);
            } else {
                // Si tiene 4 o menos elementos, asignamos la respuesta directamente
                hotelDetailViewModel.listaSimilares = response;
                console.log("alojamientos similares 4 o menos :", response);
            }
    
            // Filtramos la lista para excluir el alojamiento actual
            hotelDetailViewModel.listaSimilares = hotelDetailViewModel.listaSimilares.filter(function(alojamiento) {
                return alojamiento.id !== hotelDetailViewModel.alojamiento.id;
            });
    
            console.log("alojamientos similares (excluyendo alojamiento actual):");
        }, function(response) { 
            console.log("Error obteniendo alojamientos similares");
        });
    },
    
    obtenerReviews: function(){
    	reviewFactory.getReviews(idAlojamiento)
    	.then(function(response) {
        	hotelDetailViewModel.listaReviews = response;
        	console.log("Reviews detalladas:", response);
    }, function(response) {
        console.log("Error obteniendo Reviews detalladas");
    	});
    },
    comprobarReview: function(){
        if ( $scope.$parent.user==undefined || hotelDetailViewModel.alojamiento.idu == $scope.$parent.user.id) {
            hotelDetailViewModel.canComment = false;
        } 
        else {
            for (var i = 0; i < hotelDetailViewModel.listaReviews.length; i++) {
                if (hotelDetailViewModel.listaReviews[i].idu == $scope.$parent.user.id) {
                    hotelDetailViewModel.canComment = false;
                    break; // Deja de recorrer la lista cuando encuentra una review del usuario actual
                }
            }
        }
    },
    obtenerFav: function(){
    	favoritosFactory.isFavorito(idAlojamiento)
    	.then(function(response) {
        	hotelDetailViewModel.fav = response;
        	console.log("favorito:", response);
    }, function(response) {
        if(response.data.userMessage==="notLogin"){
            console.log("Inicia sesión");
        }
        else{
            console.log("Error en isFavorito");
        }
        hotelDetailViewModel.fav = false;
        
    	});
    },
    deleteFav: function() {
        favoritosFactory.delFavorito(idAlojamiento)
        .then(function(response) {
            console.log("Favorito con idp:"+idAlojamiento+" eliminado", response);
            hotelDetailViewModel.fav = false;
        }, function(response) {
            if(response.data.userMessage==="notLogin"){
                console.log("Inicia sesión");
            }
            else{
                console.log("Error en deleteFav");
            }
        });
    },
    addReview: function() {
        if(hotelDetailViewModel.canComment&&hotelDetailViewModel.myReview.review!=""){
            hotelDetailViewModel.myReview.idu=$scope.$parent.user.id;
            hotelDetailViewModel.myReview.idp=hotelDetailViewModel.alojamiento.id;
            reviewFactory.addReview(hotelDetailViewModel.myReview)
                .then(function(response) {
                    console.log("Review añadida", response);
                    hotelDetailViewModel.canComment = false;
                    hotelDetailViewModel.functions.obteneralojamiento(idAlojamiento);
                }, function(response) {
                    if(response.data.userMessage==="notLogin"){
                        console.log("Inicia sesión");
                    }
                    else{
                        console.log("Error en addReview");
                        console.log(response);
                    }
                });
        }
    },
    addFav: function() {
        favoritosFactory.addFavorito(idAlojamiento)
        .then(function(response) {
            console.log("Favorito con idp:"+idAlojamiento+" añadido", response);
            hotelDetailViewModel.fav = true;
        }, function(response) {
            if(response.data.userMessage==="notLogin"){
                console.log("Inicia sesión");
            }
            else{
                console.log("Error en addFavorito");
            }
        });
    },
    buscar : function(){
        if (hotelDetailViewModel.nameLugar==undefined) {
            $location.path('/busqueda/' + hotelDetailViewModel.alojamiento.city+'/0/0');
        }
        var lugar = hotelDetailViewModel.nameLugar.trim();
        // Si está vacío, redirigir sin agregar el nombre del lugar a la URL
        if (lugar === "") {
            $location.path('/busqueda/' + hotelDetailViewModel.alojamiento.city+'/0/0');
        } else {
            // Si no está vacío, redirigir con el nombre del lugar en la URL
            $location.path('/busqueda/' + lugar+'/0/0');
        }
    },
    registerincarrito: function(idhabitacion) {
        carritoFactory.registerincarrito(idhabitacion)
        .then(function(response){
            console.log("Añadiendo reserva al usuario con el email ", " Response: ", response );
            // Muestra la notificación al usuario
            $scope.showAddToCartNotification();
        }, function(response){
            console.log(response.data.error);
            console.log("Error carrito ",  " Response: ", response );
        });
    }
    
    };
	hotelDetailViewModel.functions.obteneralojamiento(idAlojamiento);
}]);
