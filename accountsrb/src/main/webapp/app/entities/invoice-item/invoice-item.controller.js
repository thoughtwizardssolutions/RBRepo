(function() {
    'use strict';

    angular
        .module('accountsrbApp')
        .controller('InvoiceItemController', InvoiceItemController);

    InvoiceItemController.$inject = ['$scope', '$state', 'InvoiceItem'];

    function InvoiceItemController ($scope, $state, InvoiceItem) {
        var vm = this;
        
        vm.invoiceItems = [];

        loadAll();

        function loadAll() {
            InvoiceItem.query(function(result) {
                vm.invoiceItems = result;
            });
        }
    }
})();
