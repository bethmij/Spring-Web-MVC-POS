package lk.ijse.gdse66.spring.service.impl;

import lk.ijse.gdse66.spring.dto.ItemDTO;
import lk.ijse.gdse66.spring.dto.OrderDTO;
import lk.ijse.gdse66.spring.repository.ItemRepo;
import lk.ijse.gdse66.spring.repository.OrderRepo;
import lk.ijse.gdse66.spring.service.ItemService;
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
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    Transformer transformer;

    @Autowired
    GenerateID generateID;

    @Override
    public List<OrderDTO> getAllOrder() {
        return orderRepo.findAll().stream()
                .map(order -> transformer.fromOrderEntity(order))
                .toList();
    }

    @Override
    public OrderDTO getOrderDetails(String id) {
        if(!orderRepo.existsById(id)){
            throw new NotFoundException("Order Id: " + id + " does not exist");
        }
        return transformer.fromOrderEntity(orderRepo.findById(id).get());
    }

    @Override
    public OrderDTO saveOrder(OrderDTO orderDTO) {
        orderDTO.setOrderId(generateID.generateOrderID());
        return transformer.fromOrderEntity(
                orderRepo.save(
                        transformer.toOrderEntity(orderDTO)));
    }

    @Override
    public void updateOrder(OrderDTO orderDTO) {
        if(!orderRepo.existsById(orderDTO.getOrderId())){
            throw new NotFoundException("Update Failed; order id: " +
                    orderDTO.getOrderId() + " does not exist");
        }
        orderRepo.save(transformer.toOrderEntity(orderDTO));
    }

    @Override
    public void deleteOrder(String id) {
        if(!orderRepo.existsById(id)){
            throw new NotFoundException("Delete Failed; order id: " +
                    id + " does not exist");
        }
        orderRepo.deleteById(id);
    }

    @Override
    public List<String> GetOrderIDs() {
        return orderRepo.findAllIds();
    }
}
