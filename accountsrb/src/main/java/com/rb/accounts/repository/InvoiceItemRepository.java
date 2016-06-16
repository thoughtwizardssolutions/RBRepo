package com.rb.accounts.repository;

import com.rb.accounts.domain.InvoiceItem;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the InvoiceItem entity.
 */
@SuppressWarnings("unused")
public interface InvoiceItemRepository extends JpaRepository<InvoiceItem,Long> {

}
