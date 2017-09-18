'use strict';

var app = angular
    .module('mercearia', [
        'ngAnimate',
        'ngCookies',
        'ngResource',
        'ngRoute',
        'ngSanitize',
        'ngTouch'
    ]);
    app.config(function ($routeProvider) {
        $routeProvider
            .when('/pedidos', {
                templateUrl: 'app/views/pedidos.html'
            })

            .when('/produtos', {
                templateUrl: 'app/views/produtos.html',
            })

            .otherwise({
                redirectTo: '/'
            });
    });
