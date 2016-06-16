package com.rb.accounts.repository;

import com.rb.accounts.domain.Dealer;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Dealer entity.
 */
@SuppressWarnings("unused")
public interface DealerRepository extends JpaRepository<Dealer,Long> {

}
