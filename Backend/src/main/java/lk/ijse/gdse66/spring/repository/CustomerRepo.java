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

    /* native queries (SQL) */
    @Query(value="select * from customers",nativeQuery = true)
    List<Customer> getAllCustomersWithSQL();

    /* JPQL queries */
    @Query(value = "select c from Customer c")
    List<Customer> getAllCustomersWithJPQL();

    @Query(value="from Customer c")
    List<Customer> getAllCustomersWithHQL();

    /* positional parameters*/
    @Query(value = "select * from customers where name=?1 and address=?2",
            nativeQuery = true)
    List<Customer> getAllCustomersByNameAndAddress(String name, String address);

    /* named parameters */
    @Query(value = "select * from customers where name=:name and address=:address",
            nativeQuery = true)
    List<Customer>
    getAllCustomersByNameAndAddress2(@Param("name") String name,
                                     @Param("address") String address);
}
