angular.module('bookingApp')
.factory('carritoFactory',['$http', function($http){
	
	var url = 'https://localhost:8443/BookingJS/rest/carrito/';
	var carritoInterface = {
    	getcompra : function(){
    		return $http.get(url)
    			.then(function(response){
					return response.data;
				});
    	},
    	
    	registerincarrito : function(ida){
			return $http.post(url + ida)
				.then(function(response){
					return response.status;
				});
		},
		deleteincarrito : function(ida){
			return $http.delete(url + ida)
				.then(function(response){
					return response.status;
				});
		},

    }
    return carritoInterface;
}])
