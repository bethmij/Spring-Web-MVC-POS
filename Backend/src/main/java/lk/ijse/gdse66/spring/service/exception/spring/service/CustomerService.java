package lk.ijse.gdse66.spring.service.exception.spring.service;

import lk.ijse.gdse66.spring.service.exception.spring.dto.CustomerDTO;

import java.util.List;


public interface CustomerService {
    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerDetails(String id);

    CustomerDTO saveCustomer(CustomerDTO customerDTO);

    void updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer(String id);
}
