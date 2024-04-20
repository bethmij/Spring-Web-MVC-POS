package lk.ijse.gdse66.spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "item")
public class Item {
    @Id
    @Column(name = "item_code")
    private String itemCode;

    private String description;

    @Column(name = "qty_on_hand")
    private int qtyOnHand;

    @Column(name = "unit_price")
    private double unitPrice;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "item")
    private List<OrderDetails> orderDetails = new ArrayList<>();

    public Item(String itemCode, String description, int qtyOnHand, double unitPrice) {
        this.itemCode = itemCode;
        this.description = description;
        this.qtyOnHand = qtyOnHand;
        this.unitPrice = unitPrice;
    }

}
