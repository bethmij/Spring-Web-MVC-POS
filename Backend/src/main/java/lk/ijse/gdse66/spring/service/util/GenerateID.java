package lk.ijse.gdse66.spring.service.util;

import lk.ijse.gdse66.spring.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenerateID {
    @Autowired
    CustomerRepo customerRepo;
    public  String generateId() {

        try {
            String last_id =  customerRepo.findFirstByOrderByIdDesc().getId();
            int latest_id = Integer.parseInt(last_id.split("C00-")[1])+1;
            return "C00-"+String.format("%03d",latest_id);

        } catch (NullPointerException e) {
            return "C00-001";
        }

    }

}
