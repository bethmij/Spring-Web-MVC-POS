package lk.ijse.gdse66.spring.service;

import lk.ijse.gdse66.spring.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getAllOrder();

    OrderDTO getOrderDetails(String id);

    OrderDTO saveOrder(OrderDTO OrderDTO);

    void updateOrder(OrderDTO OrderDTO);

    void deleteOrder(String id);

    List<String> GetOrderIDs();
}
