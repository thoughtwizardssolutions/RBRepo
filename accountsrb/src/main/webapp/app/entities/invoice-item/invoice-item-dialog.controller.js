(function() {
    'use strict';

    angular
        .module('accountsrbApp')
        .controller('InvoiceItemDialogController', InvoiceItemDialogController);

    InvoiceItemDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', '$q', 'entity', 'InvoiceItem', 'Imei', 'Product', 'Tax'];

    function InvoiceItemDialogController ($timeout, $scope, $stateParams, $uibModalInstance, $q, entity, InvoiceItem, Imei, Product, Tax) {
        var vm = this;

        vm.invoiceItem = entity;
        vm.clear = clear;
        vm.save = save;
        vm.imeis = Imei.query();
        vm.products = Product.query({filter: 'invoiceitem-is-null'});
        $q.all([vm.invoiceItem.$promise, vm.products.$promise]).then(function() {
            if (!vm.invoiceItem.product || !vm.invoiceItem.product.id) {
                return $q.reject();
            }
            return Product.get({id : vm.invoiceItem.product.id}).$promise;
        }).then(function(product) {
            vm.products.push(product);
        });
        vm.taxes = Tax.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.invoiceItem.id !== null) {
                InvoiceItem.update(vm.invoiceItem, onSaveSuccess, onSaveError);
            } else {
                InvoiceItem.save(vm.invoiceItem, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('accountsrbApp:invoiceItemUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
