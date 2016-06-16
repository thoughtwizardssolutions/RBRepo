package com.rb.accounts.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Imei.
 */
@Entity
@Table(name = "imei")
public class Imei implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "imei_1", nullable = false)
    private String imei1;

    @NotNull
    @Column(name = "imei_2", nullable = false)
    private String imei2;

    @OneToOne(cascade = {CascadeType.ALL})
    private InvoiceItem invoiceItem;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImei1() {
        return imei1;
    }

    public void setImei1(String imei1) {
        this.imei1 = imei1;
    }

    public String getImei2() {
        return imei2;
    }

    public void setImei2(String imei2) {
        this.imei2 = imei2;
    }

    public InvoiceItem getInvoiceItem() {
      return invoiceItem;
    }

    public void setInvoiceItem(InvoiceItem invoiceItem) {
      this.invoiceItem = invoiceItem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Imei imei = (Imei) o;
        if(imei.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, imei.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Imei{" +
            "id=" + id +
            ", imei1='" + imei1 + "'" +
            ", imei2='" + imei2 + "'" +
            '}';
    }
}
