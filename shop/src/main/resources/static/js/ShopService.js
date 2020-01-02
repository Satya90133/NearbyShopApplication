'use strict';
 
angular.module('crudApp').factory('shopService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {
 
            var factory = {
                loadAllshops: loadAllshops,
                getAllshops: getAllShops,
                createShop: createShop,
            };
 
            return factory;
 
            function loadAllShops() {
                console.log('Fetching all shops');
                var deferred = $q.defer();
                $http.get(urls.SHOP_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all shops');
                            $localStorage.shops = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading shops');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
            function getAllShops(){
                return $localStorage.shops;
            }
 
       
 
            function createShop(shop) {
                console.log('Creating shop');
                var deferred = $q.defer();
                $http.post(urls.shop_SERVICE_API, shop)
                    .then(
                        function (response) {
                            loadAllShops();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating shop : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
        }
    ]);