angular.module('bookingApp')
.factory('reviewFactory',['$http', function($http){
	
	var url = 'https://localhost:8443/BookingJS/rest/reviews/';
	var reviewsInterface = {
    	getReviews : function(idp){
    		return $http.get(url+idp)
    			.then(function(response){
					return response.data;
				});
    	},		
    	delReview : function(idp){
			// Pasa el usuario como parámetro en la solicitud DELETE, en el Resources habra un @PATH que nos hará diferenciar entre cerrarSesion y eliminarUser
			return $http.delete(url + 'delete/' + idp)
				.then(function(response){
					return response.status;
				});
    	},		
    	addReview : function(myReview){
			// Pasa el usuario como parámetro en la solicitud DELETE, en el Resources habra un @PATH que nos hará diferenciar entre cerrarSesion y eliminarUser
			return $http.post(url, myReview)
				.then(function(response){
					return response.status;
				});
    	}
    }
    return reviewsInterface;
}])
