(function() {
    'use strict';
    angular
        .module('accountsrbApp')
        .factory('Pdf', Pdf);

    Pdf.$inject = ['$resource'];

    function Pdf ($resource, DateUtils) {
        var resourceUrl =  'api/invoices/pdf/:invoice';

        var data = $resource(resourceUrl, {}, {
            createPdf:{
                method:'POST'
                }
            });
            return data;
    }
})();
