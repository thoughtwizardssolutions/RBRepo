(function() {
	'use strict';

	angular.module('accountsrbApp').controller('InvoiceNewController',
			InvoiceNewController);

	InvoiceNewController.$inject = [ '$scope', '$timeout', 'Invoice', 'Dealer',
			'AlertService' ];

	function InvoiceNewController($scope, $timeout, Invoice, Dealer,
			AlertService) {
		var vm = this;

		vm.doNotMatch = null;
		vm.error = null;
		vm.errorUserExists = null;
		vm.success = null;
		vm.saveInvoice = saveInvoice;
		vm.invoice = {};
		vm.invoice.invoiceItem = [];
		vm.invoice.invoiceItem.imeis = [];
		vm.dealers = [];
		vm.selectedContact = {};
		vm.selectedContact.firmName = null;
		vm.loadDealers = loadDealers;
		vm.selectContact = selectContact;
		vm.addNewContact = addNewContact;
		vm.selectedContactInline = selectedContactInline;

		/*		$scope.$watch('selectedIndex', function(val) {
		 $scope.selectedContact = vm.dealers[val];      
		
		 if (val == vm.dealers.length - 1) {
		 vm.addNewContact();
		 $scope.selectedIndex = vm.selectedContact= null;
		 }
		 });*/

		loadDealers();

		function saveInvoice() {

			console.log('inside controller...,....');
		}

		function addNewContact() {

			console.log('add new contact');

		}

		function loadDealers() {
			Dealer.query({}, onSuccess, onError);

			function onSuccess(data, headers) {
				console.log(data);
				vm.dealers = data;

				console.dir(vm.dealers);
				var dealer = {};
				dealer.firmName = 'Add new contact+';
				dealer.id = vm.dealers.length + 1;
				vm.dealers.push(dealer);
			}
		}

		function onError(error) {
			AlertService.error(error.data.message);
		}

		function selectedContactInline() {
			if (vm.selectedContact) {
				var result = vm.selectedContact.firmName + '' + vm.selectedContact.address.address1;
				if (vm.selectedContact.address.address2) {
					return result + '' + vm.selectedContact.address.address2;
				}
				return result;
			}
		}

		function selectContact(dealer) {
			vm.selectedContact = dealer;
			vm.invoice.dealer = dealer.id;

			console.log('Selected contact : ');
			console.log(dealer);
			console.log('Selected firmName : ');
			console.log(vm.selectedContact.firmName);

		}
	}
})();