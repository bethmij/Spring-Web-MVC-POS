package lk.ijse.gdse66.spring.service.util;

import lk.ijse.gdse66.spring.dto.CustomerDTO;
import lk.ijse.gdse66.spring.entity.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Transformer {

    private final ModelMapper mapper;

    public Transformer(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public CustomerDTO fromCustomerEntity(Customer customer){
        CustomerDTO customerDTO = mapper.map(customer, CustomerDTO.class);
        return customerDTO;
    }

    public Customer toCustomerEntity(CustomerDTO customerDTO){
        Customer customer = mapper.map(customerDTO, Customer.class);
        return customer;
    }
}
