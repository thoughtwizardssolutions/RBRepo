(function() {
    'use strict';
    angular
        .module('accountsrbApp')
        .factory('Invoice', Invoice);

    Invoice.$inject = ['$resource', 'DateUtils'];

    function Invoice ($resource, DateUtils) {
        var resourceUrl =  'api/invoices/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.creationDate = DateUtils.convertLocalDateFromServer(data.creationDate);
                        data.modficationDate = DateUtils.convertLocalDateFromServer(data.modficationDate);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    data.creationDate = DateUtils.convertLocalDateToServer(data.creationDate);
                    data.modficationDate = DateUtils.convertLocalDateToServer(data.modficationDate);
                    return angular.toJson(data);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    data.creationDate = DateUtils.convertLocalDateToServer(data.creationDate);
                    data.modficationDate = DateUtils.convertLocalDateToServer(data.modficationDate);
                    return angular.toJson(data);
                }
            }
        });
    }
})();
