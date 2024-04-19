package lk.ijse.gdse66.spring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO implements Serializable {

    @NotBlank(message = "item id can not be null")
    @Pattern(regexp = "I00-[0-9]{3}", message = "item id is not valid")
    private String itemCode;

    @NotBlank(message = "description can not be null")
    private String description;

    @NotBlank(message = "qtyOnHand can not be null")
    @Pattern(regexp = "\\d+", message = "unit price id is not valid")
    private String qtyOnHand;

    @NotBlank(message = "unitPrice can not be null")
    @Pattern(regexp = "[0-9]{0,2}", message = "unit price id is not valid")
    private String unitPrice;


}
