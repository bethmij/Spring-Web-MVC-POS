package lk.ijse.gdse66.spring.service.exception.spring.service.impl;

import lk.ijse.gdse66.spring.service.exception.spring.dto.CustomerDTO;
import lk.ijse.gdse66.spring.service.exception.spring.repository.CustomerRepo;
import lk.ijse.gdse66.spring.service.exception.spring.service.CustomerService;
import lk.ijse.gdse66.spring.service.exception.spring.service.exception.NotFoundException;
import lk.ijse.gdse66.spring.service.exception.spring.service.util.Transformer;
import lk.ijse.gdse66.spring.service.exception.spring.service.util.UtilMatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    Transformer transformer;

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepo.findAll().stream()
                .map(customer -> transformer.fromCustomerEntity(customer))
                .toList();
    }

    @Override
    public CustomerDTO getCustomerDetails(String id) {
        if(!customerRepo.existsById(id)){
            throw new NotFoundException("Customer Id: " + id + " does not exist");
        }
        return transformer.fromCustomerEntity(customerRepo.findById(id).get());
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        customerDTO.setId(UtilMatter.generateId());
        return transformer.fromCustomerEntity(
                customerRepo.save(
                        transformer.toCustomerEntity(customerDTO)));
    }

    @Override
    public void updateCustomer(CustomerDTO customerDTO) {
        if(!customerRepo.existsById(customerDTO.getId())){
            throw new NotFoundException("Update Failed; customer id: " +
                    customerDTO.getId() + " does not exist");
        }
        customerRepo.save(transformer.toCustomerEntity(customerDTO));
    }

    @Override
    public void deleteCustomer(String id) {
        if(!customerRepo.existsById(id)){
            throw new NotFoundException("Delete Failed; customer id: " +
                    id + " does not exist");
        }
        customerRepo.deleteById(id);
    }
}
