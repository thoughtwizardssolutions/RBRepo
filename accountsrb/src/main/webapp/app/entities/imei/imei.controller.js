(function() {
    'use strict';

    angular
        .module('accountsrbApp')
        .controller('ImeiController', ImeiController);

    ImeiController.$inject = ['$scope', '$state', 'Imei'];

    function ImeiController ($scope, $state, Imei) {
        var vm = this;
        
        vm.imeis = [];

        loadAll();

        function loadAll() {
            Imei.query(function(result) {
                vm.imeis = result;
            });
        }
    }
})();
