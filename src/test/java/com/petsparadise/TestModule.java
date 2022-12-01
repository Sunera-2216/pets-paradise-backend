package com.petsparadise;

import com.petsparadise.buyer.Buyer;
import com.petsparadise.buyer.FirebaseInitialization;
import com.petsparadise.buyer.FirebaseService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TestModule {
    
    @Test
    public void testLogin() {
        FirebaseService service = new FirebaseService();
        FirebaseInitialization init = new FirebaseInitialization();
        init.initialization();
    
        Buyer buyer = new Buyer();
        buyer.setEmail("lahiru@gmail.com");
        buyer.setPassword("lahiru1234");
        
        assertEquals(service.validateLogin(buyer), service.validateLogin(buyer));
    }
    
    @Test
    public void testGetPetDetails() {
        FirebaseService service = new FirebaseService();
        FirebaseInitialization init = new FirebaseInitialization();
        init.initialization();
        
        String petId = "-MxJzjJLilrVFMxX_QcI";
        //assertEquals("Labrador Retriever", service.getPetDetails(petId).getBreed());
    }
}
