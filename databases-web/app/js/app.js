(function () {
    angular.module('databasesApp', []).
        controller('dbController', function ($scope, $http) {
            $scope.users = [];
            $scope.error = false;
            $http.get('http://localhost:8080/users').then(function (fetched) {
                    $scope.users = fetched.data;
                },
                function () {
                    $scope.error = true;
                });
        });

})();
