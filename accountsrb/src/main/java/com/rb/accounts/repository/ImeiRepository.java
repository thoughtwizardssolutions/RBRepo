package com.rb.accounts.repository;

import com.rb.accounts.domain.Imei;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Imei entity.
 */
@SuppressWarnings("unused")
public interface ImeiRepository extends JpaRepository<Imei,Long> {

}
