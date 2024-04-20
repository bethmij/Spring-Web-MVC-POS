package lk.ijse.gdse66.spring.repository;

import lk.ijse.gdse66.spring.entity.Order;
import lk.ijse.gdse66.spring.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDetailRepo extends JpaRepository<OrderDetails,String> {

//    Order findFirstByOrderByOrderIdDesc();
//
//    @Query("SELECT o.orderId FROM Order o")
//    List<String> findAllIds();


}
