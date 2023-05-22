package com.adebisi.task_seven.service_interface;

import com.adebisi.task_seven.DTO.OrderDTO;
import com.adebisi.task_seven.model.Order;

import java.util.List;

public interface OrderService {

    List<Order> findAllOder();


    void deleteOrder(Long id);

    void changeOrderStatus(OrderDTO orderDTO);
}
