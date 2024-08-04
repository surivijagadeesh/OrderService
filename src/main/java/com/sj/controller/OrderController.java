package com.sj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sj.entity.Order;
import com.sj.service.OrderService;

@RestController
@RequestMapping("/api")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/createData")
	public ResponseEntity<Order> createData (@RequestBody Order order) {
		Order orderData=orderService.createData(order);
		return new ResponseEntity<Order>(orderData,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/getOrdersbyID/{id}")
	public ResponseEntity<Order> getOrdersById(@PathVariable("id") Long id){
		Order orderData=orderService.getOrdersById(id);
		return new ResponseEntity<Order>(orderData,HttpStatus.OK);
	}
	@GetMapping("/getOrders")
	public ResponseEntity<List<Order>> getOrders(){
		List<Order> orderData=orderService.getOrders();
		return new ResponseEntity<List<Order>>(orderData,HttpStatus.OK);
	}
	
	@PutMapping("/updateOrder/{id}")
	public ResponseEntity<Order> updateOrder(@PathVariable("id") Long id, @RequestBody Order request){
		Order orderData=orderService.updateOrder(id,request);
		return new ResponseEntity<Order>(orderData,HttpStatus.CREATED);
	}
	@PatchMapping("/updateOrderPatch/{id}")
	public ResponseEntity<Order> updateOrderPatch(@PathVariable("id") Long id, @RequestBody Order request){
		Order orderData=orderService.updateOrderPatch(id,request);
		return new ResponseEntity<Order>(orderData,HttpStatus.CREATED);
	}
	
	
	
}
