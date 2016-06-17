package com.rb.accounts.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "modification_date")
    private LocalDateTime modificationDate;

    @NotNull
    @Column(name = "firm_name", nullable = false)
    private String firmName;

    @Column(name = "owner_name")
    private String ownerName;

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
    
    @Column(name = "created_by")
    private String created_by;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(LocalDateTime modificationDate) {
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

    public String getCreated_by() {
      return created_by;
    }

    public void setCreated_by(String created_by) {
      this.created_by = created_by;
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
            ", address='" + address + "'" +
            '}';
    }
}
