package com.sj.service;

import java.util.List;

import com.sj.Model.OrderResponse;
import com.sj.entity.Order;

public interface OrderService {

	Order createData(Order order);

	OrderResponse getOrdersById(Long id);

	List<OrderResponse> getOrders();

	Order updateOrder(Long id, Order request);

	Order updateOrderPatch(Long id, Order request);

}
