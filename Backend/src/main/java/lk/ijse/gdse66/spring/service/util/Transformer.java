package lk.ijse.gdse66.spring.service.util;

import lk.ijse.gdse66.spring.dto.CustomerDTO;
import lk.ijse.gdse66.spring.dto.ItemDTO;
import lk.ijse.gdse66.spring.entity.Customer;
import lk.ijse.gdse66.spring.entity.Item;
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
}
