angular.module('bookingApp')
.controller('trendingCtrl', ['$scope','$location',function($scope, $location){
    $scope.$parent.cssVar1 = "../css/trending.css";
    $scope.$parent.titleVar = "Trending Booking.com";

}]);
