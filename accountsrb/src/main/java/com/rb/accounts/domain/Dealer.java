package com.rb.accounts.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Dealer.
 */
@Entity
@Table(name = "dealer")
public class Dealer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @Column(name = "modification_date")
    private LocalDate modificationDate;

    @NotNull
    @Column(name = "firm_name", nullable = false)
    private String firmName;

    @Column(name = "owner_name")
    private String ownerName;

    @NotNull
    @Column(name = "tin", nullable = false)
    private String tin;

    @Column(name = "terms_and_conditions")
    private String termsAndConditions;

    @Column(name = "opening_balance", precision=10, scale=2)
    private BigDecimal openingBalance;

    @Column(name = "current_balance", precision=10, scale=2)
    private BigDecimal currentBalance;

    @OneToOne
    @JoinColumn(unique = true)
    private Address address;

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

    public LocalDate getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(LocalDate modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public String getTermsAndConditions() {
        return termsAndConditions;
    }

    public void setTermsAndConditions(String termsAndConditions) {
        this.termsAndConditions = termsAndConditions;
    }

    public BigDecimal getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(BigDecimal openingBalance) {
        this.openingBalance = openingBalance;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Dealer dealer = (Dealer) o;
        if(dealer.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, dealer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Dealer{" +
            "id=" + id +
            ", creationDate='" + creationDate + "'" +
            ", modificationDate='" + modificationDate + "'" +
            ", firmName='" + firmName + "'" +
            ", ownerName='" + ownerName + "'" +
            ", tin='" + tin + "'" +
            ", termsAndConditions='" + termsAndConditions + "'" +
            ", openingBalance='" + openingBalance + "'" +
            ", currentBalance='" + currentBalance + "'" +
            '}';
    }
}
