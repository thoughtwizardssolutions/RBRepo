(function() {
    'use strict';
    angular
        .module('accountsrbApp')
        .factory('InvoiceItem', InvoiceItem);

    InvoiceItem.$inject = ['$resource'];

    function InvoiceItem ($resource) {
        var resourceUrl =  'api/invoice-items/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
