(function() {
    'use strict';

    angular
        .module('accountsrbApp')
        .controller('InvoiceItemDetailController', InvoiceItemDetailController);

    InvoiceItemDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'entity', 'InvoiceItem', 'Imei', 'Product', 'Tax'];

    function InvoiceItemDetailController($scope, $rootScope, $stateParams, entity, InvoiceItem, Imei, Product, Tax) {
        var vm = this;

        vm.invoiceItem = entity;

        var unsubscribe = $rootScope.$on('accountsrbApp:invoiceItemUpdate', function(event, result) {
            vm.invoiceItem = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
