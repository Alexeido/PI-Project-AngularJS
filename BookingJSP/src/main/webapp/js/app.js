angular.module('bookingApp', ['ngRoute'])
.config(function($routeProvider){
	$routeProvider
    	.when("/", {
    		controller: "indexCtrl",
    		controllerAs: "indexVM",
    		templateUrl: "indexinfo.html",
    		resolve: {
    			// produce 500 miliseconds (0,5 seconds) of delay that should be enough to allow the server
    			//does any requested update before reading the orders.
    			// Extracted from script.js used as example on https://docs.angularjs.org/api/ngRoute/service/$route
    			delay: function($q, $timeout) {
    			var delay = $q.defer();
    			$timeout(delay.resolve, 500);
    			return delay.promise;
    			}
    		}
    	})
    	.when("/registrarse", {
    		controller: "registerCtrl",
    		controllerAs: "registerVM",
    		templateUrl: "registrarse.html"
    	})
    	.when("/perfil", {
    		controller: "perfilCtrl",
    		controllerAs: "perfilVM",
    		templateUrl: "perfil.html"
    	})
		
    	.when("/reservasUsuario", {
    		controller: "reservaCtrl",
    		controllerAs: "reservaVM",
    		templateUrl: "reservasUsuario.html"
    	})
    	.when("/perfilAjustes", {
    		controller: "perfilCtrl",
    		controllerAs: "perfilVM",
    		templateUrl: "EditarPerfil.html"
    	})
    	.when("/favoritosUsuario", {
    		controller: "alojamientosUserCtrl",
    		controllerAs: "alojamientosUserVM",
    		templateUrl: "alojamientosfavoritos.html"
    	})
		.when("/carrito", {
    		controller: "carritoCtrl",
    		controllerAs: "carritoVM",
    		templateUrl: "carrito.html"
    	})
		.when("/reserva/:ID", {
    		controller: "reservaDetailCtrl",
    		controllerAs: "reservaDetailVM",
    		templateUrl: "reservasDetalle.html"
    	})
    	.when("/alojamientosUsuario", {
    		controller: "alojamientosUserCtrl",
    		controllerAs: "alojamientosUserVM",
    		templateUrl: "alojamientosusuario.html"
    	})
		.when("/trending", {
    		controller: "trendingCtrl",
    		controllerAs: "trendingVM",
    		templateUrl: "trending.html"
    	})
		.when("/habitacionesUsuario/:ID", {
    		controller: "habitacionesUserCtrl",
    		controllerAs: "habitacionesUserVM",
    		templateUrl: "habitacionesuser.html"
    	})
	    .when('/editar-alojamiento/:ID', {
    		controller: "alojamientosDetailedCtrl",
    		controllerAs: "alojamientosDetailedVM",
    		templateUrl: "editaralojamiento.html"
	    })
	    .when('/editar-habitacion/:ID', {
    		controller: "habitacionesDetailedCtrl",
    		controllerAs: "habitacionesDetailedVM",
    		templateUrl: "editarhabitacion.html"
	    })
	    .when('/add-alojamiento', {
    		controller: "alojamientosDetailedCtrl",
    		controllerAs: "alojamientosDetailedVM",
    		templateUrl: "registroAlojamiento.html"
	    })
		.when('/add-habitacion', {
    		controller: "habitacionesDetailedCtrl",
    		controllerAs: "habitacionesDetailedVM",
    		templateUrl: "registroHabitacion.html"
	    })
		.when('/hotel/:ID', {
    		controller: "hotelDetailCtrl",
    		controllerAs: "hotelDetailVM",
    		templateUrl: "alojamiento.html"
	    })
    	
	    .when('/busqueda/:Lugar/:Orden/:Filtro', {
    		controller: "busquedaCtrl",
    		controllerAs: "busquedaVM",
    		templateUrl: "listaalojamientos.html"
	    })

    	.when("/deleteOrder/:ID", {
   
        	controller: "orderHandlerCtrl.js",
    		controllerAs: "orderHandlerVM",
    		templateUrl: "orderHandlerTemplate.html"
        	
        })
		.otherwise({
	        redirectTo: '/'
	    });

})
