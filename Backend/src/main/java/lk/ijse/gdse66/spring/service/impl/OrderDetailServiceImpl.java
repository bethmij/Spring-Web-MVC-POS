package lk.ijse.gdse66.spring.service.impl;

import lk.ijse.gdse66.spring.dto.OrderDTO;
import lk.ijse.gdse66.spring.dto.OrderDetailsDTO;
import lk.ijse.gdse66.spring.repository.OrderDetailRepo;
import lk.ijse.gdse66.spring.repository.OrderRepo;
import lk.ijse.gdse66.spring.service.OrderDetailService;
import lk.ijse.gdse66.spring.service.OrderService;
import lk.ijse.gdse66.spring.service.exception.NotFoundException;
import lk.ijse.gdse66.spring.service.util.GenerateID;
import lk.ijse.gdse66.spring.service.util.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    OrderDetailRepo orderDetailRepo;

    @Autowired
    Transformer transformer;

    @Autowired
    GenerateID generateID;



    @Override
    public OrderDetailsDTO saveOrderDetails(OrderDetailsDTO orderDetailsDTO) {
        return transformer.fromOrderDetailEntity(
                orderDetailRepo.save(
                        transformer.toOrderDetailEntity(orderDetailsDTO)));
    }

    @Override
    public void updateOrderDetails(OrderDetailsDTO orderDetailsDTO) {
        if(!orderDetailRepo.existsById(orderDetailsDTO.getOrderId())){
            throw new NotFoundException("Update Failed; order_detail id: " +
                    orderDetailsDTO.getOrderId() + " does not exist");
        }
        orderDetailRepo.save(transformer.toOrderDetailEntity(orderDetailsDTO));
    }

}
