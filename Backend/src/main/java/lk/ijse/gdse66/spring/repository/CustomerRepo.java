package lk.ijse.gdse66.spring.repository;

import lk.ijse.gdse66.spring.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<CustomerEntity, String> {
}
