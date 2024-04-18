package lk.ijse.gdse66.spring.service.exception.spring.service.util;

import lk.ijse.gdse66.spring.service.exception.spring.dto.CustomerDTO;
import lk.ijse.gdse66.spring.service.exception.spring.entity.CustomerEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Transformer {

    private final ModelMapper mapper;

    public Transformer(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public CustomerDTO fromCustomerEntity(CustomerEntity customerEntity){
        return mapper.map(customerEntity, CustomerDTO.class);
    }

    public CustomerEntity toCustomerEntity(CustomerDTO customerDTO){
        return mapper.map(customerDTO, CustomerEntity.class);
    }
}
