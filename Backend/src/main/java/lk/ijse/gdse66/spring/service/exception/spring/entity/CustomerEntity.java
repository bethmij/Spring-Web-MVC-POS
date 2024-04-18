package lk.ijse.gdse66.spring.service.exception.spring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class CustomerEntity {

    @Id
    private String id;
    private String name;
    private String address;
    @Column(columnDefinition = "LONGTEXT")
    private String profilePic;

}
