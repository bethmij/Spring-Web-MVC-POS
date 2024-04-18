package lk.ijse.gdse66.spring.service.exception.spring.repository;

import lk.ijse.gdse66.spring.service.exception.spring.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<CustomerEntity, String> {
}
