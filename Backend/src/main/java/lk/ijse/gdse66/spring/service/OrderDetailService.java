package lk.ijse.gdse66.spring.service;

import lk.ijse.gdse66.spring.dto.OrderDTO;
import lk.ijse.gdse66.spring.dto.OrderDetailsDTO;

import java.util.List;

public interface OrderDetailService {

    OrderDetailsDTO saveOrderDetails(OrderDetailsDTO orderDetailsDTO);

    void updateOrderDetails(OrderDetailsDTO orderDetailsDTO);

}
