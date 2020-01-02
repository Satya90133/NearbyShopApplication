var app = angular.module('crudApp',['ui.router','ngStorage']);

app.constant('urls', {
    BASE: 'http://localhost:8080/ShopApplication/   ',
    SHOP_SERVICE_API : 'http://localhost:8080/ShopApplication/'
});

app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: 'partials/list',
                controller:'ShopController',
                controllerAs:'ctrl',
                resolve: {
                    shops: function ($q, shopService) {
                        console.log('Load all shops');
                        var deferred = $q.defer();
                        shopService.loadAllShops().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            });
        $urlRouterProvider.otherwise('/');
    }]);