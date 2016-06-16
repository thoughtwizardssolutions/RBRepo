package com.rb.accounts.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.rb.accounts.domain.Imei;
import com.rb.accounts.repository.ImeiRepository;
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
 * REST controller for managing Imei.
 */
@RestController
@RequestMapping("/api")
public class ImeiResource {

    private final Logger log = LoggerFactory.getLogger(ImeiResource.class);
        
    @Inject
    private ImeiRepository imeiRepository;
    
    /**
     * POST  /imeis : Create a new imei.
     *
     * @param imei the imei to create
     * @return the ResponseEntity with status 201 (Created) and with body the new imei, or with status 400 (Bad Request) if the imei has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/imeis",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Imei> createImei(@Valid @RequestBody Imei imei) throws URISyntaxException {
        log.debug("REST request to save Imei : {}", imei);
        if (imei.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("imei", "idexists", "A new imei cannot already have an ID")).body(null);
        }
        Imei result = imeiRepository.save(imei);
        return ResponseEntity.created(new URI("/api/imeis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("imei", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /imeis : Updates an existing imei.
     *
     * @param imei the imei to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated imei,
     * or with status 400 (Bad Request) if the imei is not valid,
     * or with status 500 (Internal Server Error) if the imei couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/imeis",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Imei> updateImei(@Valid @RequestBody Imei imei) throws URISyntaxException {
        log.debug("REST request to update Imei : {}", imei);
        if (imei.getId() == null) {
            return createImei(imei);
        }
        Imei result = imeiRepository.save(imei);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("imei", imei.getId().toString()))
            .body(result);
    }

    /**
     * GET  /imeis : get all the imeis.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of imeis in body
     */
    @RequestMapping(value = "/imeis",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Imei> getAllImeis() {
        log.debug("REST request to get all Imeis");
        List<Imei> imeis = imeiRepository.findAll();
        return imeis;
    }

    /**
     * GET  /imeis/:id : get the "id" imei.
     *
     * @param id the id of the imei to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the imei, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/imeis/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Imei> getImei(@PathVariable Long id) {
        log.debug("REST request to get Imei : {}", id);
        Imei imei = imeiRepository.findOne(id);
        return Optional.ofNullable(imei)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /imeis/:id : delete the "id" imei.
     *
     * @param id the id of the imei to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/imeis/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteImei(@PathVariable Long id) {
        log.debug("REST request to delete Imei : {}", id);
        imeiRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("imei", id.toString())).build();
    }

}
