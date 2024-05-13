angular.module('bookingApp')
.factory('alojamientosFactory',['$http', function($http){
	
	var url = 'https://localhost:8443/BookingJS/rest/alojamientos/';
	var alojamientosInterface = {
    	getAlojamiento : function(idp){
    		return $http.get(url + idp)
    			.then(function(response){
					return response.data;
				});
    	},		
    	getAlojamientoBooked : function(idb){
    		return $http.get(url +'reserva/' +idb)
    			.then(function(response){
					return response.data;
				});
    	},	
    	getAlojamientosSubidos : function(){
    		return $http.get(url)
    			.then(function(response){
					return response.data;
				});
    	},		
    	getBusquedaAlojamientos : function(lugar, order, filter){
            console.log(url+'list/'+lugar+'/'+order+'/'+filter);
    		return $http.get(url+'list/'+lugar+'/'+order+'/'+filter)
    			.then(function(response){
					return response.data;
				});
    	},		
    	delAlojamiento : function(idp){
			// Pasa el usuario como parámetro en la solicitud DELETE, en el Resources habra un @PATH que nos hará diferenciar entre cerrarSesion y eliminarUser
			return $http.delete(url + 'delete/' + idp)
				.then(function(response){
					return response.status;
				});
    	},
    	cambiarDisponibilidad : function(idp){
			// Pasa el usuario como parámetro en la solicitud DELETE, en el Resources habra un @PATH que nos hará diferenciar entre cerrarSesion y eliminarUser
			return $http.put(url + 'put/' + idp)
				.then(function(response){
					return response.status;
				});
    	},
    	registraralojamiento : function(Property){
			return $http.post(url,Property)
    			.then(function(response){
					return response.status;
				});
		},
		editaralojamiento : function(Property){
			return $http.put(url ,Property)
    			.then(function(response){
					return response.status;
				});
		}
		
    }
    return alojamientosInterface;
}])
