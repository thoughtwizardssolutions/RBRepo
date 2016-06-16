(function() {
    'use strict';

    angular
        .module('accountsrbApp')
        .controller('InvoiceItemDetailController', InvoiceItemDetailController);

    InvoiceItemDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'entity', 'InvoiceItem', 'Product', 'Imei', 'Invoice'];

    function InvoiceItemDetailController($scope, $rootScope, $stateParams, entity, InvoiceItem, Product, Imei, Invoice) {
        var vm = this;

        vm.invoiceItem = entity;

        var unsubscribe = $rootScope.$on('accountsrbApp:invoiceItemUpdate', function(event, result) {
            vm.invoiceItem = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
