package lk.ijse.gdse66.spring.service;

import lk.ijse.gdse66.spring.dto.CustomerDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CustomerService {
    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerDetails(String id);

    CustomerDTO saveCustomer(CustomerDTO customerDTO);

    void updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer(String id);
}
