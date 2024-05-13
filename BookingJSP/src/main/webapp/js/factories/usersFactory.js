angular.module('bookingApp')
.factory('usersFactory',['$http', function($http){
	
	var url = 'https://localhost:8443/BookingJS/rest/users/';
	var usersInterface = {
    	getUser : function(){
    		return $http.get(url)
    			.then(function(response){
					return response.data;
				});
    	},
    	
    	register : function(user){
			return $http.post(url,user)
				.then(function(response){
					return response.status;
				});
		},

		cerrarSesion : function(){
			return $http.delete(url)
				.then(function(response){
					return response.status;
				});
		},

		eliminarUser : function(user){
			// Pasa el usuario como parámetro en la solicitud DELETE, en el Resources habra un @PATH que nos hará diferenciar entre cerrarSesion y eliminarUser
			return $http.delete(url + 'delete/' + user.id)
				.then(function(response){
					return response.status;
				});
		},
    	
    	editPerfil : function(user){
			return $http.put(url,user)
				.then(function(response){
					return response.status;
				});
		}			
    }
    return usersInterface;
}])
