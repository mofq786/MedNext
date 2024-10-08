package com.jsp.MedNext.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.MedNext.entity.Orders;

public interface OrdersRepo extends JpaRepository<Orders, Integer> {

}
