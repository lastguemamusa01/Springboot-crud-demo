package com.example.angular.Springbootcruddemo.repository;

import com.example.angular.Springbootcruddemo.model.Flow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface FlowRepository extends JpaRepository<Flow,Integer> {}
