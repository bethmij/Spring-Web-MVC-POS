package lk.ijse.gdse66.spring.service.util;

import lk.ijse.gdse66.spring.dto.CustomerDTO;
import lk.ijse.gdse66.spring.dto.ItemDTO;
import lk.ijse.gdse66.spring.dto.OrderDTO;
import lk.ijse.gdse66.spring.dto.OrderDetailsDTO;
import lk.ijse.gdse66.spring.entity.Customer;
import lk.ijse.gdse66.spring.entity.Item;
import lk.ijse.gdse66.spring.entity.Order;
import lk.ijse.gdse66.spring.entity.OrderDetails;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Transformer {

    private final ModelMapper mapper;

    public Transformer(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public CustomerDTO fromCustomerEntity(Customer customer){
        return mapper.map(customer, CustomerDTO.class);
    }

    public Customer toCustomerEntity(CustomerDTO customerDTO){
        return mapper.map(customerDTO, Customer.class);
    }

    public ItemDTO fromItemEntity(Item item){
        return mapper.map(item, ItemDTO.class);
    }

    public Item toItemEntity(ItemDTO itemDTO){
        return mapper.map(itemDTO, Item.class);
    }

    public OrderDTO fromOrderEntity(Order order){
        return mapper.map(order, OrderDTO.class);
    }

    public Order toOrderEntity(OrderDTO orderDTO){
        return mapper.map(orderDTO, Order.class);
    }

    public OrderDetailsDTO fromOrderDetailEntity(OrderDetails orderDetails){
        return mapper.map(orderDetails, OrderDetailsDTO.class);
    }

    public OrderDetails toOrderDetailEntity(OrderDetailsDTO orderDetailsDTO){
        return mapper.map(orderDetailsDTO, OrderDetails.class);
    }
}
