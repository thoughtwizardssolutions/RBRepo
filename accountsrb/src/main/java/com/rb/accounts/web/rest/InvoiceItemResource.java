package com.rb.accounts.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.rb.accounts.domain.InvoiceItem;
import com.rb.accounts.repository.InvoiceItemRepository;
import com.rb.accounts.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing InvoiceItem.
 */
@RestController
@RequestMapping("/api")
public class InvoiceItemResource {

    private final Logger log = LoggerFactory.getLogger(InvoiceItemResource.class);
        
    @Inject
    private InvoiceItemRepository invoiceItemRepository;
    
    /**
     * POST  /invoice-items : Create a new invoiceItem.
     *
     * @param invoiceItem the invoiceItem to create
     * @return the ResponseEntity with status 201 (Created) and with body the new invoiceItem, or with status 400 (Bad Request) if the invoiceItem has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/invoice-items",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<InvoiceItem> createInvoiceItem(@Valid @RequestBody InvoiceItem invoiceItem) throws URISyntaxException {
        log.debug("REST request to save InvoiceItem : {}", invoiceItem);
        if (invoiceItem.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("invoiceItem", "idexists", "A new invoiceItem cannot already have an ID")).body(null);
        }
        InvoiceItem result = invoiceItemRepository.save(invoiceItem);
        return ResponseEntity.created(new URI("/api/invoice-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("invoiceItem", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /invoice-items : Updates an existing invoiceItem.
     *
     * @param invoiceItem the invoiceItem to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated invoiceItem,
     * or with status 400 (Bad Request) if the invoiceItem is not valid,
     * or with status 500 (Internal Server Error) if the invoiceItem couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/invoice-items",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<InvoiceItem> updateInvoiceItem(@Valid @RequestBody InvoiceItem invoiceItem) throws URISyntaxException {
        log.debug("REST request to update InvoiceItem : {}", invoiceItem);
        if (invoiceItem.getId() == null) {
            return createInvoiceItem(invoiceItem);
        }
        InvoiceItem result = invoiceItemRepository.save(invoiceItem);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("invoiceItem", invoiceItem.getId().toString()))
            .body(result);
    }

    /**
     * GET  /invoice-items : get all the invoiceItems.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of invoiceItems in body
     */
    @RequestMapping(value = "/invoice-items",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<InvoiceItem> getAllInvoiceItems() {
        log.debug("REST request to get all InvoiceItems");
        List<InvoiceItem> invoiceItems = invoiceItemRepository.findAll();
        return invoiceItems;
    }

    /**
     * GET  /invoice-items/:id : get the "id" invoiceItem.
     *
     * @param id the id of the invoiceItem to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the invoiceItem, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/invoice-items/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<InvoiceItem> getInvoiceItem(@PathVariable Long id) {
        log.debug("REST request to get InvoiceItem : {}", id);
        InvoiceItem invoiceItem = invoiceItemRepository.findOne(id);
        return Optional.ofNullable(invoiceItem)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /invoice-items/:id : delete the "id" invoiceItem.
     *
     * @param id the id of the invoiceItem to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/invoice-items/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteInvoiceItem(@PathVariable Long id) {
        log.debug("REST request to delete InvoiceItem : {}", id);
        invoiceItemRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("invoiceItem", id.toString())).build();
    }

}
