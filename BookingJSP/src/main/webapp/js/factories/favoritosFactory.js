angular.module('bookingApp')
.factory('favoritosFactory',['$http', function($http){
	
	var url = 'https://localhost:8443/BookingJS/rest/favoritos/';
	var favoritosInterface = {
    	getFavoritos : function(){
    		return $http.get(url)
    			.then(function(response){
					return response.data;
				});
    	},		
    	isFavorito : function(idp){
    		return $http.get(url+idp)
    			.then(function(response){
					return response.data;
				});
    	},		
    	delFavorito : function(idp){
			// Pasa el usuario como parámetro en la solicitud DELETE, en el Resources habra un @PATH que nos hará diferenciar entre cerrarSesion y eliminarUser
			return $http.delete(url + 'delete/' + idp)
				.then(function(response){
					return response.status;
				});
    	},		
    	addFavorito : function(idp){
			// Pasa el usuario como parámetro en la solicitud DELETE, en el Resources habra un @PATH que nos hará diferenciar entre cerrarSesion y eliminarUser
			return $http.post(url + idp)
				.then(function(response){
					return response.status;
				});
    	}
    }
    return favoritosInterface;
}])
