package lk.ijse.gdse66.spring.api;

import jakarta.validation.Valid;
import lk.ijse.gdse66.spring.dto.ItemDTO;
import lk.ijse.gdse66.spring.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
@CrossOrigin
public class ItemController {

    @Autowired
    ItemService ItemService;

    @GetMapping(value = {"/getAll"},produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ItemDTO> getAllItems(){
        return ItemService.getAllItems();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDTO saveItem(@RequestPart("itemCode") String itemCode,
                                    @RequestPart("description") String description,
                                    @RequestPart("qtyOnHand") String qtyOnHand,
                                    @RequestPart("unitPrice") String unitPrice){
        ItemDTO Item = new ItemDTO(itemCode, description, qtyOnHand, unitPrice);
        return ItemService.saveItem(Item);
    }

    @DeleteMapping("/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteItem(@PathVariable("code") String code){
        ItemService.deleteItem(code);
    }

    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateItem(@Valid @RequestBody ItemDTO Item){
        ItemService.updateItem(Item);
    }

    @GetMapping(value = "/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ItemDTO GetItemDetails(@PathVariable("code") String code){
        return ItemService.getItemDetails(code);
    }

    @GetMapping(value = {"/getIds"})
    public List<String> GetItemIDs(){
        return ItemService.GetItemIDs();
    }
}
