package lk.ijse.gdse66.spring.repository;

import lk.ijse.gdse66.spring.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer,String> {

    List<Customer> findCustomersByName(String name); //query methods

    Customer searchByNameAndAddress(String name, String address);

    int countCustomersByAddressStartsWith(String letters);

    Customer findFirstByOrderByIdDesc();

    @Query("SELECT c.id FROM Customer c")
    List<String> findAllIds();

    /* native queries (SQL) */
    @Query(value="select * from customers",nativeQuery = true)
    List<Customer> getAllCustomersWithSQL();

    @Query(value = "select * from customers where name=?1 and address=?2",
            nativeQuery = true)
    List<Customer> getAllCustomersByNameAndAddress(String name, String address);

}
