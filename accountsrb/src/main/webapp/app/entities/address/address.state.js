(function() {
    'use strict';

    angular
        .module('accountsrbApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('address', {
            parent: 'entity',
            url: '/address?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Addresses'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/address/addresses.html',
                    controller: 'AddressController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }]
            }
        })
        .state('address-detail', {
            parent: 'entity',
            url: '/address/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Address'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/address/address-detail.html',
                    controller: 'AddressDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Address', function($stateParams, Address) {
                    return Address.get({id : $stateParams.id}).$promise;
                }]
            }
        })
        .state('address.new', {
            parent: 'address',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/address/address-dialog.html',
                    controller: 'AddressDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                phone: null,
                                city: null,
                                state: null,
                                country: null,
                                pincode: null,
                                address1: null,
                                address2: null,
                                email: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('address', null, { reload: true });
                }, function() {
                    $state.go('address');
                });
            }]
        })
        .state('address.edit', {
            parent: 'address',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/address/address-dialog.html',
                    controller: 'AddressDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Address', function(Address) {
                            return Address.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('address', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('address.delete', {
            parent: 'address',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/address/address-delete-dialog.html',
                    controller: 'AddressDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Address', function(Address) {
                            return Address.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('address', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
