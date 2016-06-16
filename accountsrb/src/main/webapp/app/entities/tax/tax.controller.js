(function() {
    'use strict';

    angular
        .module('accountsrbApp')
        .controller('TaxController', TaxController);

    TaxController.$inject = ['$scope', '$state', 'Tax'];

    function TaxController ($scope, $state, Tax) {
        var vm = this;
        
        vm.taxes = [];

        loadAll();

        function loadAll() {
            Tax.query(function(result) {
                vm.taxes = result;
            });
        }
    }
})();
