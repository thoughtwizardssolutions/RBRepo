package com.rb.accounts.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A Invoice.
 */
@Entity
@Table(name = "invoice")
public class Invoice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @Column(name = "modfication_date")
    private LocalDate modficationDate;

    @NotNull
    @Column(name = "invoice_number", nullable = false)
    private String invoiceNumber;

    @Column(name = "order_number")
    private String orderNumber;

    @Column(name = "sales_person_name")
    private String salesPersonName;

    @NotNull
    @Column(name = "subtotal", precision=10, scale=2, nullable = false)
    private BigDecimal subtotal;

    @Column(name = "taxes", precision=10, scale=2)
    private BigDecimal taxes;

    @Column(name = "shipping_charges", precision=10, scale=2)
    private BigDecimal shippingCharges;

    @Column(name = "adjustments", precision=10, scale=2)
    private BigDecimal adjustments;

    @NotNull
    @Column(name = "total_amount", precision=10, scale=2, nullable = false)
    private BigDecimal totalAmount;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy="invoice")
    private List<InvoiceItem> invoiceItems = new ArrayList();
    
    @Column(name="dealer_id")
    private Long dealerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getModficationDate() {
        return modficationDate;
    }

    public void setModficationDate(LocalDate modficationDate) {
        this.modficationDate = modficationDate;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getSalesPersonName() {
        return salesPersonName;
    }

    public void setSalesPersonName(String salesPersonName) {
        this.salesPersonName = salesPersonName;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTaxes() {
        return taxes;
    }

    public void setTaxes(BigDecimal taxes) {
        this.taxes = taxes;
    }

    public BigDecimal getShippingCharges() {
        return shippingCharges;
    }

    public void setShippingCharges(BigDecimal shippingCharges) {
        this.shippingCharges = shippingCharges;
    }

    public BigDecimal getAdjustments() {
        return adjustments;
    }

    public void setAdjustments(BigDecimal adjustments) {
        this.adjustments = adjustments;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<InvoiceItem> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(List<InvoiceItem> saveInvoiceItems) {
        this.invoiceItems = saveInvoiceItems;
    }

    public Long getDealer() {
      return dealerId;
    }

    public void setDealer(Long dealerId) {
      this.dealerId = dealerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Invoice invoice = (Invoice) o;
        if(invoice.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, invoice.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
      return "Invoice [id=" + id + ", creationDate=" + creationDate + ", modficationDate="
          + modficationDate + ", invoiceNumber=" + invoiceNumber + ", orderNumber=" + orderNumber
          + ", salesPersonName=" + salesPersonName + ", subtotal=" + subtotal + ", taxes=" + taxes
          + ", shippingCharges=" + shippingCharges + ", adjustments=" + adjustments
          + ", totalAmount=" + totalAmount + ", invoiceItems=" + invoiceItems + ", dealerId="
          + dealerId + "]";
    }

   
}
