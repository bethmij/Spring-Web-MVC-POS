package lk.ijse.gdse66.spring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO implements Serializable {

    @NotBlank(message = "id can not be null")
    @Pattern(regexp = "OR00-[0-9]{3}", message = "id is not valid")
    private String orderId;

    @NotBlank(message = "order date can not be null")
    private LocalDate orderDate;

    @NotBlank(message = "customer id can not be null")
    @Pattern(regexp = "C00-[0-9]{3}", message = "id is not valid")
    private String customerId;

    @NotBlank(message = "order details can not be null")
    private List<OrderDetailsDTO> orderDetails;
}
