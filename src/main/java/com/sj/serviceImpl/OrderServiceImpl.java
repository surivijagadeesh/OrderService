package com.sj.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sj.Model.OrderResponse;
import com.sj.entity.Order;
import com.sj.exception.CustomException;
import com.sj.repository.OrderRepository;
import com.sj.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Order createData(Order order) {
		Order orderData=new Order();
		orderData.setProductId(order.getProductId());
		orderData.setQuantity(order.getQuantity());
		orderData.setOrderDate(order.getOrderDate());
		orderData.setOrderStatus(order.getOrderStatus());
		orderData.setAmount(order.getAmount());
		return repository.save(orderData);
	}

	@Override
	public OrderResponse getOrdersById(Long id) {
		
        // Fetch the Order entity from the repository
        Order order = repository.findById(id).get(); 
        OrderResponse orderResponse=modelMapper.map(order,OrderResponse.class);
        
        return orderResponse;
        // Convert Order entity to OrderResponse model
       // return OrderResponse.fromEntity(orderData);
    }

	 @Override
	 public List<OrderResponse> getOrders() {
	        // Fetch list of Order entities from the repository
	        List<Order> orderData = repository.findAll();

	        // Convert list of Order entities to list of OrderResponse
	        return orderData.stream()
	                .map(order -> modelMapper.map(order, OrderResponse.class))
	                .collect(Collectors.toList());
	    }

	@Override
	public Order updateOrder(Long id, Order request) {
		Optional<Order> orderId = repository.findById(id);
		if(orderId.isPresent()) {
			Order orderData = orderId.get();
			orderData.setProductId(request.getProductId());
			orderData.setQuantity(request.getQuantity());
			orderData.setOrderDate(request.getOrderDate());
			orderData.setOrderStatus(request.getOrderStatus());
			orderData.setAmount(request.getAmount());
			
			return repository.save(orderData);
		}
		else {
			throw new CustomException("PRODUCT_NOT_FOUND", "Product not found with id: " + id, HttpStatus.NOT_FOUND.value());
		}
		
	}

	@Override
	public Order updateOrderPatch(Long id, Order request) {
		Optional<Order> orderId = repository.findById(id);
		if(!orderId.isPresent()) {
			throw new CustomException("PRODUCT_NOT_FOUND", "Product not found with id: " + id, HttpStatus.NOT_FOUND.value());
		}
		else {
			Order orderData = orderId.get();
			
			if(request.getProductId()!=0) {
				orderData.setProductId(request.getProductId());
			}
			if(request.getQuantity()!=0) {
				orderData.setQuantity(request.getQuantity());		
			}
			if(request.getOrderDate()!=null) {
				orderData.setOrderDate(request.getOrderDate());
			}
			if(request.getOrderStatus()!=null) {
				orderData.setOrderStatus(request.getOrderStatus());
			}
			if(request.getAmount()!=0) {
				orderData.setAmount(request.getAmount());
			}
			
			return repository.save(orderData);
		}
	}

}
