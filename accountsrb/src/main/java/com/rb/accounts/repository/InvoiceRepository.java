package com.rb.accounts.repository;

import com.rb.accounts.domain.Invoice;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Invoice entity.
 */
@SuppressWarnings("unused")
public interface InvoiceRepository extends JpaRepository<Invoice,Long> {

}
