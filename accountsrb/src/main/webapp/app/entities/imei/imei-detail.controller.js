(function() {
    'use strict';

    angular
        .module('accountsrbApp')
        .controller('ImeiDetailController', ImeiDetailController);

    ImeiDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'entity', 'Imei'];

    function ImeiDetailController($scope, $rootScope, $stateParams, entity, Imei) {
        var vm = this;

        vm.imei = entity;

        var unsubscribe = $rootScope.$on('accountsrbApp:imeiUpdate', function(event, result) {
            vm.imei = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
