(function() {
    'use strict';

    angular
        .module('accountsrbApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('invoice', {
            parent: 'entity',
            url: '/invoice?page&sort&search',
            data: {
                authorities: ['ROLE_USER', 'ROLE_ORG_ADMIN'],
                pageTitle: 'Invoices'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/invoice/invoices.html',
                    controller: 'InvoiceController',
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
        .state('invoice-detail', {
            parent: 'entity',
            url: '/invoice/{id}',
            data: {
                authorities: ['ROLE_USER', 'ROLE_ORG_ADMIN'],
                pageTitle: 'Invoice'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/invoice/invoice-detail.html',
                    controller: 'InvoiceDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Invoice', function($stateParams, Invoice) {
                    return Invoice.get({id : $stateParams.id}).$promise;
                }]
            }
        })
        .state('invoice.new', {
            parent: 'invoice',
            url: '/new',
            data: {
                authorities: ['ROLE_USER', 'ROLE_ORG_ADMIN'],
                pageTitle: 'Invoice'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/invoice/invoice-new.html',
                    controller: 'InvoiceNewController',
                    controllerAs: 'vm'
                }
            }
        })
        .state('invoice.edit', {
            parent: 'invoice',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER', 'ROLE_ORG_ADMIN']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/invoice/invoice-dialog.html',
                    controller: 'InvoiceDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Invoice', function(Invoice) {
                            return Invoice.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('invoice', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('invoice.delete', {
            parent: 'invoice',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER', 'ROLE_ORG_ADMIN']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/invoice/invoice-delete-dialog.html',
                    controller: 'InvoiceDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Invoice', function(Invoice) {
                            return Invoice.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('invoice', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
