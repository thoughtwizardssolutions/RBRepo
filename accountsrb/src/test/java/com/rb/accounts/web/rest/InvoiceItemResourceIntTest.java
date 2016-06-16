package com.rb.accounts.web.rest;

import com.rb.accounts.AccountsrbApp;
import com.rb.accounts.domain.InvoiceItem;
import com.rb.accounts.repository.InvoiceItemRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.math.BigDecimal;;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the InvoiceItemResource REST controller.
 *
 * @see InvoiceItemResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AccountsrbApp.class)
@WebAppConfiguration
@IntegrationTest
public class InvoiceItemResourceIntTest {


    private static final Integer DEFAULT_QUANTITY = 1;
    private static final Integer UPDATED_QUANTITY = 2;

    private static final BigDecimal DEFAULT_MRP = new BigDecimal(1);
    private static final BigDecimal UPDATED_MRP = new BigDecimal(2);

    private static final BigDecimal DEFAULT_DISCOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_DISCOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_AMOUNT = new BigDecimal(2);
    private static final String DEFAULT_TAX_TYPE = "AAAAA";
    private static final String UPDATED_TAX_TYPE = "BBBBB";

    private static final Double DEFAULT_TAX_RATE = 1D;
    private static final Double UPDATED_TAX_RATE = 2D;

    @Inject
    private InvoiceItemRepository invoiceItemRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restInvoiceItemMockMvc;

    private InvoiceItem invoiceItem;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        InvoiceItemResource invoiceItemResource = new InvoiceItemResource();
        ReflectionTestUtils.setField(invoiceItemResource, "invoiceItemRepository", invoiceItemRepository);
        this.restInvoiceItemMockMvc = MockMvcBuilders.standaloneSetup(invoiceItemResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        invoiceItem = new InvoiceItem();
        invoiceItem.setQuantity(DEFAULT_QUANTITY);
        invoiceItem.setMrp(DEFAULT_MRP);
        invoiceItem.setDiscount(DEFAULT_DISCOUNT);
        invoiceItem.setAmount(DEFAULT_AMOUNT);
        invoiceItem.setTaxType(DEFAULT_TAX_TYPE);
        invoiceItem.setTaxRate(DEFAULT_TAX_RATE);
    }

    @Test
    @Transactional
    public void createInvoiceItem() throws Exception {
        int databaseSizeBeforeCreate = invoiceItemRepository.findAll().size();

        // Create the InvoiceItem

        restInvoiceItemMockMvc.perform(post("/api/invoice-items")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(invoiceItem)))
                .andExpect(status().isCreated());

        // Validate the InvoiceItem in the database
        List<InvoiceItem> invoiceItems = invoiceItemRepository.findAll();
        assertThat(invoiceItems).hasSize(databaseSizeBeforeCreate + 1);
        InvoiceItem testInvoiceItem = invoiceItems.get(invoiceItems.size() - 1);
        assertThat(testInvoiceItem.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testInvoiceItem.getMrp()).isEqualTo(DEFAULT_MRP);
        assertThat(testInvoiceItem.getDiscount()).isEqualTo(DEFAULT_DISCOUNT);
        assertThat(testInvoiceItem.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testInvoiceItem.getTaxType()).isEqualTo(DEFAULT_TAX_TYPE);
        assertThat(testInvoiceItem.getTaxRate()).isEqualTo(DEFAULT_TAX_RATE);
    }

    @Test
    @Transactional
    public void checkQuantityIsRequired() throws Exception {
        int databaseSizeBeforeTest = invoiceItemRepository.findAll().size();
        // set the field null
        invoiceItem.setQuantity(null);

        // Create the InvoiceItem, which fails.

        restInvoiceItemMockMvc.perform(post("/api/invoice-items")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(invoiceItem)))
                .andExpect(status().isBadRequest());

        List<InvoiceItem> invoiceItems = invoiceItemRepository.findAll();
        assertThat(invoiceItems).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMrpIsRequired() throws Exception {
        int databaseSizeBeforeTest = invoiceItemRepository.findAll().size();
        // set the field null
        invoiceItem.setMrp(null);

        // Create the InvoiceItem, which fails.

        restInvoiceItemMockMvc.perform(post("/api/invoice-items")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(invoiceItem)))
                .andExpect(status().isBadRequest());

        List<InvoiceItem> invoiceItems = invoiceItemRepository.findAll();
        assertThat(invoiceItems).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = invoiceItemRepository.findAll().size();
        // set the field null
        invoiceItem.setAmount(null);

        // Create the InvoiceItem, which fails.

        restInvoiceItemMockMvc.perform(post("/api/invoice-items")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(invoiceItem)))
                .andExpect(status().isBadRequest());

        List<InvoiceItem> invoiceItems = invoiceItemRepository.findAll();
        assertThat(invoiceItems).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllInvoiceItems() throws Exception {
        // Initialize the database
        invoiceItemRepository.saveAndFlush(invoiceItem);

        // Get all the invoiceItems
        restInvoiceItemMockMvc.perform(get("/api/invoice-items?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(invoiceItem.getId().intValue())))
                .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)))
                .andExpect(jsonPath("$.[*].mrp").value(hasItem(DEFAULT_MRP.intValue())))
                .andExpect(jsonPath("$.[*].discount").value(hasItem(DEFAULT_DISCOUNT.intValue())))
                .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.intValue())))
                .andExpect(jsonPath("$.[*].taxType").value(hasItem(DEFAULT_TAX_TYPE.toString())))
                .andExpect(jsonPath("$.[*].taxRate").value(hasItem(DEFAULT_TAX_RATE.doubleValue())));
    }

    @Test
    @Transactional
    public void getInvoiceItem() throws Exception {
        // Initialize the database
        invoiceItemRepository.saveAndFlush(invoiceItem);

        // Get the invoiceItem
        restInvoiceItemMockMvc.perform(get("/api/invoice-items/{id}", invoiceItem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(invoiceItem.getId().intValue()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY))
            .andExpect(jsonPath("$.mrp").value(DEFAULT_MRP.intValue()))
            .andExpect(jsonPath("$.discount").value(DEFAULT_DISCOUNT.intValue()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.intValue()))
            .andExpect(jsonPath("$.taxType").value(DEFAULT_TAX_TYPE.toString()))
            .andExpect(jsonPath("$.taxRate").value(DEFAULT_TAX_RATE.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingInvoiceItem() throws Exception {
        // Get the invoiceItem
        restInvoiceItemMockMvc.perform(get("/api/invoice-items/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInvoiceItem() throws Exception {
        // Initialize the database
        invoiceItemRepository.saveAndFlush(invoiceItem);
        int databaseSizeBeforeUpdate = invoiceItemRepository.findAll().size();

        // Update the invoiceItem
        InvoiceItem updatedInvoiceItem = new InvoiceItem();
        updatedInvoiceItem.setId(invoiceItem.getId());
        updatedInvoiceItem.setQuantity(UPDATED_QUANTITY);
        updatedInvoiceItem.setMrp(UPDATED_MRP);
        updatedInvoiceItem.setDiscount(UPDATED_DISCOUNT);
        updatedInvoiceItem.setAmount(UPDATED_AMOUNT);
        updatedInvoiceItem.setTaxType(UPDATED_TAX_TYPE);
        updatedInvoiceItem.setTaxRate(UPDATED_TAX_RATE);

        restInvoiceItemMockMvc.perform(put("/api/invoice-items")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedInvoiceItem)))
                .andExpect(status().isOk());

        // Validate the InvoiceItem in the database
        List<InvoiceItem> invoiceItems = invoiceItemRepository.findAll();
        assertThat(invoiceItems).hasSize(databaseSizeBeforeUpdate);
        InvoiceItem testInvoiceItem = invoiceItems.get(invoiceItems.size() - 1);
        assertThat(testInvoiceItem.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testInvoiceItem.getMrp()).isEqualTo(UPDATED_MRP);
        assertThat(testInvoiceItem.getDiscount()).isEqualTo(UPDATED_DISCOUNT);
        assertThat(testInvoiceItem.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testInvoiceItem.getTaxType()).isEqualTo(UPDATED_TAX_TYPE);
        assertThat(testInvoiceItem.getTaxRate()).isEqualTo(UPDATED_TAX_RATE);
    }

    @Test
    @Transactional
    public void deleteInvoiceItem() throws Exception {
        // Initialize the database
        invoiceItemRepository.saveAndFlush(invoiceItem);
        int databaseSizeBeforeDelete = invoiceItemRepository.findAll().size();

        // Get the invoiceItem
        restInvoiceItemMockMvc.perform(delete("/api/invoice-items/{id}", invoiceItem.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<InvoiceItem> invoiceItems = invoiceItemRepository.findAll();
        assertThat(invoiceItems).hasSize(databaseSizeBeforeDelete - 1);
    }
}
