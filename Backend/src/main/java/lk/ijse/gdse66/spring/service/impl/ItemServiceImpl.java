package lk.ijse.gdse66.spring.service.impl;

import lk.ijse.gdse66.spring.dto.ItemDTO;
import lk.ijse.gdse66.spring.repository.ItemRepo;
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
        return ItemRepo.findAll().stream()
                .map(Item -> transformer.fromItemEntity(Item))
                .toList();
    }

    @Override
    public ItemDTO getItemDetails(String id) {
        if(!ItemRepo.existsById(id)){
            throw new NotFoundException("Item Id: " + id + " does not exist");
        }
        return transformer.fromItemEntity(ItemRepo.findById(id).get());
    }

    @Override
    public ItemDTO saveItem(ItemDTO ItemDTO) {
        ItemDTO.setItemCode(generateID.generateCusId());
        return transformer.fromItemEntity(
                ItemRepo.save(
                        transformer.toItemEntity(ItemDTO)));
    }

    @Override
    public void updateItem(ItemDTO ItemDTO) {
        if(!ItemRepo.existsById(ItemDTO.getItemCode())){
            throw new NotFoundException("Update Failed; Item id: " +
                    ItemDTO.getItemCode() + " does not exist");
        }
        ItemRepo.save(transformer.toItemEntity(ItemDTO));
    }

    @Override
    public void deleteItem(String id) {
        if(!ItemRepo.existsById(id)){
            throw new NotFoundException("Delete Failed; Item id: " +
                    id + " does not exist");
        }
        ItemRepo.deleteById(id);
    }

    @Override
    public List<String> GetItemIDs() {
        return ItemRepo.findAllIds();
    }
}
