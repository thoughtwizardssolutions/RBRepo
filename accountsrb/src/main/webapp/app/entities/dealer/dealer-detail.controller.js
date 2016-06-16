(function() {
    'use strict';

    angular
        .module('accountsrbApp')
        .controller('DealerDetailController', DealerDetailController);

    DealerDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'entity', 'Dealer', 'Address'];

    function DealerDetailController($scope, $rootScope, $stateParams, entity, Dealer, Address) {
        var vm = this;

        vm.dealer = entity;

        var unsubscribe = $rootScope.$on('accountsrbApp:dealerUpdate', function(event, result) {
            vm.dealer = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
