package com.rb.accounts.repository;

import com.rb.accounts.domain.Tax;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Tax entity.
 */
@SuppressWarnings("unused")
public interface TaxRepository extends JpaRepository<Tax,Long> {

}
