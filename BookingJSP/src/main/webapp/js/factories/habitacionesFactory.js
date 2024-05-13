angular.module('bookingApp')
.factory('habitacionesFactory',['$http', function($http){
	
	var url = 'https://localhost:8443/BookingJS/rest/habitaciones/';
	var habitacionesInterface = {
		getHabitacionesSubidas : function(idp){
    		return $http.get(url + idp) // Aquí concatenamos el ID a la URL
    			.then(function(response){
					return response.data;
				});
    	},
		getHabitacion : function(idp){
    		return $http.get(url +'one/'+ idp)
    			.then(function(response){
					return response.data;
				});
    	},
		delhabitaciones : function(idp){
			// Pasa el usuario como parámetro en la solicitud DELETE, en el Resources habra un @PATH que nos hará diferenciar entre cerrarSesion y eliminarUser
			return $http.delete(url + idp) // También aquí
				.then(function(response){
					return response.status;
				});
    	},
		registrarhabitacion : function(Accommodation){
			return $http.post(url,Accommodation)
    			.then(function(response){
					return response.status;
				});
		},
		editaralhabitacion : function(Accommodation){
			return $http.put(url ,Accommodation)
    			.then(function(response){
					return response.status;
				});
		}
    }
    return habitacionesInterface;
}])
