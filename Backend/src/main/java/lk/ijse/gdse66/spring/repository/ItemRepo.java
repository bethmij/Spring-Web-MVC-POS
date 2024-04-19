package lk.ijse.gdse66.spring.repository;

import lk.ijse.gdse66.spring.entity.Customer;
import lk.ijse.gdse66.spring.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepo extends JpaRepository<Item,String> {

    @Query("SELECT i.itemCode FROM Item i")
    List<String> findAllIds();


}
