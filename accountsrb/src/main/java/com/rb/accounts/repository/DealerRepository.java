package com.rb.accounts.repository;

import com.rb.accounts.domain.Dealer;

import org.springframework.data.jpa.repository.*;

import java.util.List;
import java.lang.String;

/**
 * Spring Data JPA repository for the Dealer entity.
 */
@SuppressWarnings("unused")
public interface DealerRepository extends JpaRepository<Dealer,Long> {
  
  List<Dealer> findByFirmName(String firmname);
  List<Dealer> findByTin(String tin);
  
}
