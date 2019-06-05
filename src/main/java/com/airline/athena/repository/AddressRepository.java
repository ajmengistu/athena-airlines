package com.airline.athena.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airline.athena.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
	public Address findTopByOrderByAddressIdDesc();
}
