angular.module('bookingApp')
.controller('indexCtrl', ['$scope','$location',function($scope, $location){
    $scope.$parent.cssVar1 = "../css/paginaprincipal.css";
    $scope.$parent.cssVar2 = "../css/buscador.css";
    $scope.$parent.titleVar = "Booking.com";

    var indexViewModel = this;
    indexViewModel.nameLugar = "";

    indexViewModel.functions = {
        buscar: function(){
            // Comprobar si el cuadro de texto está vacío
            var lugar = indexViewModel.nameLugar.trim();
            // Si está vacío, redirigir sin agregar el nombre del lugar a la URL
            if (lugar === "") {
                $location.path('/busqueda/all/0/0');
            } else {
                // Si no está vacío, redirigir con el nombre del lugar en la URL
                $location.path('/busqueda/' + lugar+'/0/0');
            }
        }
    };
}]);
