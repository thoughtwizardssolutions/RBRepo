(function() {
    'use strict';
    angular
        .module('accountsrbApp')
        .factory('Dealer', Dealer);

    Dealer.$inject = ['$resource', 'DateUtils', 'Principal'];

    function Dealer ($resource, DateUtils, Principal) {
        var resourceUrl =  'api/dealers/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.creationDate = DateUtils.convertLocalDateFromServer(data.creationDate);
                        data.modificationDate = DateUtils.convertLocalDateFromServer(data.modificationDate);
                        console.log('fecthing contacts for logged in user...')
                        console.log(Principal._identity);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    data.creationDate = DateUtils.convertLocalDateToServer(data.creationDate);
                    data.modificationDate = DateUtils.convertLocalDateToServer(data.modificationDate);
                    return angular.toJson(data);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    data.creationDate = DateUtils.convertLocalDateToServer(data.creationDate);
                    data.modificationDate = DateUtils.convertLocalDateToServer(data.modificationDate);
                    return angular.toJson(data);
                }
            }
        });
    }
})();
