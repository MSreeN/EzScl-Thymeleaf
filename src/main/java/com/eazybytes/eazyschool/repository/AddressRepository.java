package com.eazybytes.eazyschool.repository;

import com.eazybytes.eazyschool.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
