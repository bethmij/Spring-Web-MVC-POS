package lk.ijse.gdse66.spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "order_details")
public class OrderDetails {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "order_detail_id")
   private int orderDetailId;

   @Column (name = "quantity")
   private int qtyOnHand;

   @Column (name = "unit_price")
   private double unitPrice;

   @ManyToOne
   @JoinColumn(name = "item_code", referencedColumnName = "item_code", nullable = false)
   private Item item;

   @ManyToOne
   @JoinColumn(name = "order_id", referencedColumnName = "order_id", nullable = false)
   private Order orders;


   public OrderDetails(int qtyOnHand, double unitPrice, Item item, Order orders) {
      this.qtyOnHand = qtyOnHand;
      this.unitPrice = unitPrice;
      this.item = item;
      this.orders = orders;
   }
}

