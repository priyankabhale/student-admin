package com.administration.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.administration.student.entity.Address;


@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
