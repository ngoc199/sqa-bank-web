package com.banking.banking.repository.interestRepo;

import com.banking.banking.model.interest.Interest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestRepository extends JpaRepository<Interest, Integer> {

}
