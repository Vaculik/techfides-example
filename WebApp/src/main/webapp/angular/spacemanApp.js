/**
 * Created by Vaculik on 03/02/2016.
 */

var app = angular.module('spacemanApp', ['ngRoute', 'ngResource', 'controllers']);
var controllers = angular.module('controllers', []);

app.constant('ENUMS', {
    specialization: ['PHYSICS', 'BIOLOGY', 'MECHANICS', 'MEDICINE']
});

app.config(['$routeProvider', function ($routeProvider) {
        $routeProvider.
            when('/home', {
                templateUrl: 'partials/spacemen.html',
                controller: 'SpacemenController'
            }).
            when('/spaceman/create', {
                templateUrl: 'partials/new-spaceman.html',
                controller: 'CreateSpacemanController'
            }).
            when('/spaceman/:id', {
                templateUrl: 'partials/update-spaceman.html',
                controller: 'UpdateSpacemanController'
            }).
            otherwise({redirectTo: '/home'});
    }]);


app.run(function ($rootScope) {
    //$rootScope.hideSuccessAlert = function () {
    //    $rootScope.successAlert = undefined;
    //};
    //$rootScope.hideErrorAlert = function () {
    //    $rootScope.errorAlert = undefined;
    //};
});


controllers.controller('SpacemenController', function ($scope, $http) {
    console.log('GET all spacemen request.');
    $http.get('rest/spacemen').then(function success(response) {
        console.log('All spacemen has been loaded.');
        if ('_embedded' in response.data && 'spacemen' in response.data._embedded) {
            $scope.spacemen = response.data._embedded.spacemen;
        }
    }, function error(response) {
        console.log('Error has occured when loading all spacemen.');
    });
});


controllers.controller('CreateSpacemanController', function($scope, $http, ENUMS, $location) {
    console.log('PUT new spaceman request.');
    $scope.specializations = ENUMS.specialization;
    $scope.spaceman = {
        firstName: '',
        lastName: '',
        dateOfBirth: new Date(),
        specialization: $scope.specializations[0]
    };

    $scope.create = function(spacemanDTO) {
        $http.put('rest/spacemen/create', spacemanDTO).then(function success(response) {
            console.log('Spaceman was created.');
            $location.path('/home');
        }, function error(resposne) {
            console.log('Error has occured when creating new spaceman.');
        })
    }
});


controllers.controller('UpdateSpacemanController', function($scope, $http, $routeParams, $location, ENUMS){
    console.log('POST spaceman update request.');
    var id = $routeParams.id;
    $scope.specializations = ENUMS.specialization;

    $http.get('rest/spacemen/'+id).then(function success(response) {
        console.log('Particular spaceman has been loaded.');
        $scope.spaceman = response.data;
        $scope.name = response.data.firstName;
        $scope.spaceman.dateOfBirth = new Date($scope.spaceman.dateOfBirth);
    }, function error(resopnse) {
        console.log('Error has occured when loading particular spaceman.');
    });

    $scope.update = function(spacemanDTO) {
        $http.post('rest/spacemen', spacemanDTO).then(function success(response) {
            console.log('Spaceman has been updated.');
            $location.path('/home');
        }, function error(response) {
            console.log('Error has occured when updating spaceman.');
        })
    };

    $scope.delete = function(id) {
        $http.delete('rest/spacemen/'+id).then(function success(response) {
            console.log('Spaceman has been deleted.');
            $location.path('/home');
        }, function error(response) {
            console.log('Error has occured when deleting spaceman.');
        })
    }
});