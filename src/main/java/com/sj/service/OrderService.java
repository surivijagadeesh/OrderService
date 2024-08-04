package com.sj.service;

import java.util.List;

import com.sj.entity.Order;

public interface OrderService {

	Order createData(Order order);

	Order getOrdersById(Long id);

	List<Order> getOrders();

	Order updateOrder(Long id, Order request);

	Order updateOrderPatch(Long id, Order request);

}
