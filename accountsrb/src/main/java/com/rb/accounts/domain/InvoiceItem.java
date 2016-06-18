package com.rb.accounts.domain;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A InvoiceItem.
 */
@Entity
@Table(name = "invoice_item")
public class InvoiceItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @NotNull
    @Column(name = "mrp", precision=10, scale=2, nullable = false)
    private BigDecimal mrp;

    @Column(name = "discount", precision=10, scale=2)
    private BigDecimal discount;
    
    @Column(name = "item_description", precision=10, scale=2)
    private String itemDesciption;

    @NotNull
    @Column(name = "amount", precision=10, scale=2, nullable = false)
    private BigDecimal amount;

    @Column(name = "tax_type")
    private String taxType;

    @Column(name = "tax_rate")
    private Double taxRate;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(unique = true)
    private Product product;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "invoiceItem")
    private Set<Imei> imeis = new HashSet<>();

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="invoice_id")
    private Invoice invoice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getMrp() {
        return mrp;
    }

    public void setMrp(BigDecimal mrp) {
        this.mrp = mrp;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Set<Imei> getImeis() {
        return imeis;
    }

    public void setImeis(Set<Imei> imeis) {
        this.imeis = imeis;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InvoiceItem invoiceItem = (InvoiceItem) o;
        if(invoiceItem.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, invoiceItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "InvoiceItem{" +
            "id=" + id +
            ", quantity='" + quantity + "'" +
            ", mrp='" + mrp + "'" +
            ", discount='" + discount + "'" +
            ", amount='" + amount + "'" +
            ", taxType='" + taxType + "'" +
            ", taxRate='" + taxRate + "'" +
            '}';
    }
}
