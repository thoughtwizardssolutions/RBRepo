(function() {
    'use strict';
    angular
        .module('accountsrbApp')
        .factory('Tax', Tax);

    Tax.$inject = ['$resource', 'DateUtils'];

    function Tax ($resource, DateUtils) {
        var resourceUrl =  'api/taxes/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.creationDate = DateUtils.convertLocalDateFromServer(data.creationDate);
                        data.modificationDate = DateUtils.convertLocalDateFromServer(data.modificationDate);
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
