package lk.ijse.gdse66.spring.service.impl;

import lk.ijse.gdse66.spring.dto.CustomerDTO;
import lk.ijse.gdse66.spring.dto.ItemDTO;
import lk.ijse.gdse66.spring.repository.ItemRepo;
import lk.ijse.gdse66.spring.service.CustomerService;
import lk.ijse.gdse66.spring.service.ItemService;
import lk.ijse.gdse66.spring.service.exception.NotFoundException;
import lk.ijse.gdse66.spring.service.util.GenerateID;
import lk.ijse.gdse66.spring.service.util.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepo itemRepo;

    @Autowired
    Transformer transformer;

    @Autowired
    GenerateID generateID;

    @Override
    public List<ItemDTO> getAllItems() {
        return itemRepo.findAll().stream()
                .map(item -> transformer.fromItemEntity(item))
                .toList();
    }

    @Override
    public ItemDTO getItemDetails(String id) {
        if(!itemRepo.existsById(id)){
            throw new NotFoundException("Item Id: " + id + " does not exist");
        }
        return transformer.fromItemEntity(itemRepo.findById(id).get());
    }

    @Override
    public ItemDTO saveItem(ItemDTO itemDTO) {
        itemDTO.setItemCode(generateID.generateItemCode());
        return transformer.fromItemEntity(
                itemRepo.save(
                        transformer.toItemEntity(itemDTO)));
    }

    @Override
    public void updateItem(ItemDTO itemDTO) {
        if(!itemRepo.existsById(itemDTO.getItemCode())){
            throw new NotFoundException("Update Failed; item id: " +
                    itemDTO.getItemCode() + " does not exist");
        }
        itemRepo.save(transformer.toItemEntity(itemDTO));
    }

    @Override
    public void deleteItem(String id) {
        if(!itemRepo.existsById(id)){
            throw new NotFoundException("Delete Failed; item id: " +
                    id + " does not exist");
        }
        itemRepo.deleteById(id);
    }

    @Override
    public List<String> GetItemIDs() {
        return itemRepo.findAllIds();
    }
}
