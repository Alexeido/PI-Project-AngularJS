angular.module('bookingApp')
.factory('reservaFactory',['$http', function($http){
	
	var url = 'https://localhost:8443/BookingJS/rest/reserva/';
	var reservaInterface = {

		hacercompra : function(price){
			return $http.post(url + price)
				.then(function(response){
					return response.status;
				});
		},
		getreservas : function(){
    		return $http.get(url)
    			.then(function(response){
					return response.data;
				});
    	},
    	getcompra : function(idb){
    		return $http.get(url+idb)
    			.then(function(response){
					return response.data;
				});
    	},
		deletereseva : function(id){
    		return $http.delete(url+ id)
    			.then(function(response){
					return response.status;
				});
    	},
		deletereservaHab : function(idb, ida){
    		return $http.delete(url+ idb+'/'+ida)
    			.then(function(response){
					return response.status;
				});
    	}

		
    }
    return reservaInterface;
}])
