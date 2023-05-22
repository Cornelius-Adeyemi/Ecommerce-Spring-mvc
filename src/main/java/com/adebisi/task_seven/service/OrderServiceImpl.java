package com.adebisi.task_seven.service;

import com.adebisi.task_seven.DTO.OrderDTO;
import com.adebisi.task_seven.enumPackage.Status;
import com.adebisi.task_seven.model.Order;
import com.adebisi.task_seven.repository.OrderRepo;
import com.adebisi.task_seven.service_interface.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

   private OrderRepo orderRepo;

    @Autowired
    public OrderServiceImpl(OrderRepo orderRepo){
        this.orderRepo=orderRepo;
    }


    @Override
    public List<Order> findAllOder() {
        return orderRepo.findAll();
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepo.deleteById(id);
    }

    @Override
    public void changeOrderStatus(OrderDTO orderDTO) {

        Optional<Order> optionalOrder = orderRepo.findById(orderDTO.getId());

        if(optionalOrder.isPresent()){
            Order order = optionalOrder.get();
            if(orderDTO.getStatus().equalsIgnoreCase("make order")){

                order.setStatus(Status.MAKE_ORDER);

            } else if (orderDTO.getStatus().equalsIgnoreCase("pending")) {

                order.setStatus(Status.ORDER_PENDING);
            }else if(orderDTO.getStatus().equalsIgnoreCase("fulfil")){
                order.setStatus(Status.ORDER_FULFILL);
            }

            orderRepo.save(order);

        }



    }
}
