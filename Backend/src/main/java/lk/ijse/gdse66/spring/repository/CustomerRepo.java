package lk.ijse.gdse66.spring.repository;

import lk.ijse.gdse66.spring.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepo extends JpaRepository<CustomerEntity,String> {

    List<CustomerEntity> findCustomerByName(String name); //query methods

    CustomerEntity searchByNameAndAddress(String name, String address);

    int countCustomerByAddressStartsWith(String letters);

    /* native queries (SQL) */
//    @Query(value="select * from CustomerEntity",nativeQuery = true)
//    List<CustomerEntity> getAllCustomersWithSQL();

    /* JPQL queries */
//    @Query(value = "select c from CustomerEntity c")
//    List<CustomerEntity> getAllCustomerWithJPQL();

    @Query(value="from CustomerEntity c")
    List<CustomerEntity> getAllCustomerWithHQL();

    /* positional parameters*/
    @Query(value = "select * from CustomerEntity where name=?1 and address=?2",
            nativeQuery = true)
    List<CustomerEntity> getAllCustomersByNameAndAddress(String name, String address);

    /* named parameters */
    @Query(value = "select * from CustomerEntity where name=:name and address=:address",
            nativeQuery = true)
    List<CustomerEntity>
    getAllCustomerByNameAndAddress2(@Param("name") String name,
                                     @Param("address") String address);
}
