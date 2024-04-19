package lk.ijse.gdse66.spring.service.util;

import lk.ijse.gdse66.spring.repository.CustomerRepo;
import lk.ijse.gdse66.spring.repository.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenerateID {
    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    ItemRepo itemRepo;

    public  String generateCusId() {

        try {
            String last_id =  customerRepo.findFirstByOrderByIdDesc().getId();
            int latest_id = Integer.parseInt(last_id.split("C00-")[1])+1;
            return "C00-"+String.format("%03d",latest_id);

        } catch (NullPointerException e) {
            return "C00-001";
        }

    }

    public  String generateItemCode() {

        try {
            String last_id =  itemRepo.findFirstByOrderByItemCodeDesc().getItemCode();
            int latest_id = Integer.parseInt(last_id.split("I00-")[1])+1;
            return "I00-"+String.format("%03d",latest_id);

        } catch (NullPointerException e) {
            return "I00-001";
        }

    }

}
