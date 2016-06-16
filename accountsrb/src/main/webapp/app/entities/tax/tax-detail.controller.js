(function() {
    'use strict';

    angular
        .module('accountsrbApp')
        .controller('TaxDetailController', TaxDetailController);

    TaxDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'entity', 'Tax'];

    function TaxDetailController($scope, $rootScope, $stateParams, entity, Tax) {
        var vm = this;

        vm.tax = entity;

        var unsubscribe = $rootScope.$on('accountsrbApp:taxUpdate', function(event, result) {
            vm.tax = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
