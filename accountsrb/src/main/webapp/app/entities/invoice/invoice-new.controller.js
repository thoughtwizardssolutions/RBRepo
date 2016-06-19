(function() {
	'use strict';

	angular.module('accountsrbApp').controller('InvoiceNewController',
			InvoiceNewController);

	InvoiceNewController.$inject = [ '$scope', '$state', '$uibModal', '$timeout', 'Invoice', 'Dealer',
			'AlertService', 'Product', 'Tax' ];

	function InvoiceNewController($scope, $state, $uibModal, $timeout, Invoice, Dealer,
			AlertService, Product, Tax) {
		var vm = this;

		vm.doNotMatch = null;
		vm.error = null;
		vm.errorUserExists = null;
		vm.success = null;
		vm.dealers = [];
		vm.products = [];
		vm.taxes = [];
		vm.saveInvoice = saveInvoice;
		vm.invoice = {};
		vm.invoice.creationDate = new Date();
		vm.invoice.invoiceItems = [];
		vm.invoice.dealer = null;
		vm.selectedContact = {};
		vm.selectedContact.firmName = null;
		vm.loadDealers = loadDealers;
		vm.loadProducts = loadProducts;
		vm.loadTaxes = loadTaxes;
		vm.selectContact = selectContact;
		vm.addInvoiceitem = addInvoiceitem;
		vm.removeInvoiceItem = removeInvoiceItem;
		vm.selectInvoiceItemProduct = selectInvoiceItemProduct
		vm.selectInvoiceItemTax = selectInvoiceItemTax

		loadDealers();
		loadProducts();
		loadTaxes();

		function saveInvoice() {
			console.log('inside save method...,....');
			Invoice.save(vm.invoice);
		}

		function addInvoiceitem() {
			var invoiceItem = {};
			invoiceItem.index = vm.invoice.invoiceItems.length + 1;
			vm.invoice.invoiceItems.push(invoiceItem);
		}
		
		function removeInvoiceItem(invoiceItem) {
			var index = vm.invoice.invoiceItems.indexOf(invoiceItem);
			vm.invoice.invoiceItems.splice(index, 1);
		}
		
		
		function loadDealers() {
			Dealer.query({}, onSuccess, onError);
			function onSuccess(data, headers) {
				console.log(data);
				vm.dealers = data;
				var dealer = {};
				dealer.firmName = 'Add new contact+';
				dealer.id = vm.dealers.length + 1;
				vm.dealers.push(dealer);
			}
		}
		
		function loadProducts() {
			Product.query({}, onSuccess, onError);

			function onSuccess(data, headers) {
				console.log(data);
				vm.products = data;
				var product = {};
				product.productName = 'Add new Product+';
				product.id = vm.products.length + 1;
				vm.products.push(product);
			}
		}
		
		function loadTaxes() {
			Tax.query({}, onSuccess, onError);

			function onSuccess(data, headers) {
				console.log(data);
				vm.taxes = data;
				var tax = {};
				tax.name = 'Add new Tax+';
				tax.id = vm.taxes.length + 1;
				vm.taxes.push(tax);
			}
		}

		function onError(error) {
			AlertService.error(error.data.message);
		}
		
		function selectContact(dealer) {
			if(dealer.firmName === 'Add new contact+') {
                $uibModal.open({
                    templateUrl: 'app/entities/dealer/dealer-dialog.html',
                    controller: 'DealerDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                creationDate: null,
                                modificationDate: null,
                                firmName: null,
                                ownerName: null,
                                tin: null,
                                termsAndConditions: null,
                                openingBalance: null,
                                currentBalance: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                	loadDealers();
                }, function() {
                	loadDealers();
                });
			} else {
				vm.selectedContact = dealer;
				vm.invoice.dealer = dealer.id;
				console.log('Selected contact : ');
				console.log(dealer);
				console.log('Selected firmName : ');
				console.log(vm.selectedContact.firmName);
				console.log('Selected invoice : ');
				console.dir(vm.invoice);
			}
		}
		
		function selectInvoiceItemProduct(invoiceItem, product) {
			if(product.productName === 'Add new Product+') {
                $uibModal.open({
                    templateUrl: 'app/entities/product/product-dialog.html',
                    controller: 'ProductDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                creationDate: null,
                                modificationDate: null,
                                firmName: null,
                                ownerName: null,
                                tin: null,
                                termsAndConditions: null,
                                openingBalance: null,
                                currentBalance: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                	loadProducts();
                }, function() {
                	loadProducts();
                });
			} else {
				// invoiceItem.selectedProduct = product;
				invoiceItem.productName = product.productName;
				invoiceItem.mrp = product.mrp;
				invoiceItem.color = product.color;
				invoiceItem.desciption = product.desciption;
				console.log('Selected product : ');
				console.log(product);
				console.log('Selected invoice : ');
				console.dir(vm.invoice);
			}
		}
		
		function selectInvoiceItemTax(invoiceItem , tax) {
			if(tax.name === 'Add new Tax+') {
                $uibModal.open({
                    templateUrl: 'app/entities/tax/tax-dialog.html',
                    controller: 'TaxDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                creationDate: null,
                                modificationDate: null,
                                firmName: null,
                                ownerName: null,
                                tin: null,
                                termsAndConditions: null,
                                openingBalance: null,
                                currentBalance: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                   loadTaxes();
                }, function() {
                   loadTaxes();
                });
			} else {
				invoiceItem.tax = tax;
				console.log('Selected tax : ');
				console.log(tax);
				console.log('Selected invoice : ');
				console.log(vm.invoice);
			}
		}
		
		function addImei(invoiceItem, imei) {
			invoiceItem.imeis.push(imei);
			
		}
		
	}
})();