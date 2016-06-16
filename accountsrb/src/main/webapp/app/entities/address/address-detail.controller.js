(function() {
    'use strict';

    angular
        .module('accountsrbApp')
        .controller('AddressDetailController', AddressDetailController);

    AddressDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'entity', 'Address'];

    function AddressDetailController($scope, $rootScope, $stateParams, entity, Address) {
        var vm = this;

        vm.address = entity;

        var unsubscribe = $rootScope.$on('accountsrbApp:addressUpdate', function(event, result) {
            vm.address = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
