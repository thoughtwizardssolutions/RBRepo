(function() {
    'use strict';

    angular
        .module('accountsrbApp')
        .controller('ImeiDialogController', ImeiDialogController);

    ImeiDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Imei'];

    function ImeiDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Imei) {
        var vm = this;

        vm.imei = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.imei.id !== null) {
                Imei.update(vm.imei, onSaveSuccess, onSaveError);
            } else {
                Imei.save(vm.imei, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('accountsrbApp:imeiUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
