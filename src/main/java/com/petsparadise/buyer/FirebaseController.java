package com.petsparadise.buyer;

import com.petsparadise.store.Store;
import com.petsparadise.product.Accessories;
import com.petsparadise.product.Pet;
import com.petsparadise.product.Product;
import com.petsparadise.seller.Seller;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class FirebaseController {
    private String nicFrontImageUrl = "", nicBackImageUrl = "", bankStatementImageUrl = "";
    private String petImageUrl = "", itemImageUrl = "";
    
    @Autowired
    FirebaseService firebaseService;
    
    /**
     * 
     * @param person
     * @return
     * @throws InterruptedException
     * @throws ExecutionException 
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/create-buyer")
    public String createBuyer(@RequestBody Buyer person) throws InterruptedException, ExecutionException{
        return firebaseService.createBuyer(person);
    }
    
    /**
     * 
     * @param seller
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException 
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/create-seller")
    public Seller createSeller(@RequestBody Seller seller) throws InterruptedException, ExecutionException, IOException{
        seller.setApprovalStatus(0);
        for (;;) {
            if (!nicFrontImageUrl.equals("") && !nicBackImageUrl.equals("") && !bankStatementImageUrl.equals("")) {
                seller.setsNicFrontImageUrl(nicFrontImageUrl);
                seller.setsNicBackImageUrl(nicBackImageUrl);
                seller.setsBankStatementImageUrl(bankStatementImageUrl);
                break;
            }
        }
        
        return firebaseService.createSeller(seller);
    }
    
    /**
     * 
     * @param file
     * @return
     * @throws IOException 
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/upload-nic-front-image")
    public String uploadNicFrontImage(@RequestParam("file") MultipartFile file) throws IOException {
        String s = "";
        s = firebaseService.uploadNicFrontImage(file);
        
        for (;;) {
            if (!s.equals("")) {
                nicFrontImageUrl = s;
                break;
            }
        }

        return s;
    }
    
    /**
     * 
     * @param file
     * @return
     * @throws IOException 
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/upload-nic-back-image")
    public String uploadNicBackImage(@RequestParam("file") MultipartFile file) throws IOException {
        String s = "";
        s = firebaseService.uploadNicBackImage(file);
        
        for (;;) {
            if (!s.equals("")) {
                nicBackImageUrl = s;
                break;
            }
        }

        return s;
    }
    
    /**
     * 
     * @param file
     * @return
     * @throws IOException 
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/upload-bank-statement-image")
    public String uploadBankStatementImage(@RequestParam("file") MultipartFile file) throws IOException {
        String s = "";
        s = firebaseService.uploadBankStatementImage(file);
        
        for (;;) {
            if (!s.equals("")) {
                bankStatementImageUrl = s;
                break;
            }
        }

        return s;
    }
    
    /**
     * 
     * @param store
     * @return
     * @throws InterruptedException
     * @throws ExecutionException 
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/create-store")
    public Store createStore(@RequestBody Store store) throws InterruptedException, ExecutionException{
        return firebaseService.createStore(store);
    }
    
    /**
     * 
     * @param pet
     * @return 
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/add-pet")
    public Pet addPet(@RequestBody Pet pet){
        for (;;) {
            if (!petImageUrl.equals("")) {
                pet.setImageUrl(petImageUrl);
                break;
            }
        }
        
        return firebaseService.addPet(pet);
    }
    
    /**
     * 
     * @param file
     * @return
     * @throws IOException 
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/upload-pet-image", method = RequestMethod.POST)
    public String uploadPetImage(@RequestParam("file") MultipartFile file) throws IOException{
        String s = "";
        s = firebaseService.uploadPetImages(file);
        
        for (;;) {
            if (!s.equals("")) {
                petImageUrl = s;
                break;
            }
        }

        return s;
    }
    
    /**
     * 
     * @param item
     * @return 
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/add-item")
    public Accessories addItem(@RequestBody Accessories item){
        for (;;) {
            if (!itemImageUrl.equals("")) {
                item.setImageUrl(itemImageUrl);
                break;
            }
        }
            
        return firebaseService.addItem(item);
    }
    
    /**
     * 
     * @param file
     * @return
     * @throws IOException 
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/upload-item-image", method = RequestMethod.POST)
    public String uploadItemImage(@RequestParam("file") MultipartFile file) throws IOException{
        String s = "";
        s = firebaseService.uploadItemImages(file);
        
        for (;;) {
            if (!s.equals("")) {
                itemImageUrl = s;
                break;
            }
        }

        return s;
    }
    
    /**
     * 
     * @param buyer
     * @return 
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/login")
    public Buyer loginUser(@RequestBody Buyer buyer){
        return firebaseService.validateLogin(buyer);
    }
    
    /**
     * 
     * @param seller
     * @return 
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/seller-login")
    public Seller loginUser(@RequestBody Seller seller){
        return firebaseService.validateLogin(seller);
    }
    
    /**
     * 
     * @param sellerId
     * @return 
     */
    @PostMapping("/get-stores")
    public List<Store> getStores(@RequestBody String sellerId) {
        return firebaseService.getStores(sellerId);
    }
    
    /**
     * 
     * @param storeId
     * @return 
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/get-pets-list")
    public List<Pet> getPetsList(@RequestBody String storeId) {
        return firebaseService.getPets(storeId);
    }
    
    /**
     * 
     * @param storeId
     * @return 
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/get-item-list")
    public List<Accessories> getItemList(@RequestBody String storeId) {
        return firebaseService.getItems(storeId);
    }
    
    /**
     * 
     * @param keyword
     * @return 
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/search-product")
    public List<Product> searchProduct(@RequestBody String keyword) {
        return firebaseService.searchProduct(keyword);
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/predict")
    public List<Product> predict(@RequestBody String predictedKeyword) {
        return firebaseService.searchPredictedProduct(predictedKeyword);
    }
    
    /**
     * 
     * @param selectedCategory
     * @return 
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/filter-category")
    public List<Product> filterCategory(@RequestBody String selectedCategory) {
        return firebaseService.filterCategor(selectedCategory);
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/get-item-details")
    public Accessories getItemDetails(@RequestBody String itemId) {
        return firebaseService.getItemDetails(itemId);
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/get-pet-details")
    public Product getPetDetails(@RequestBody String petId) {
        return firebaseService.getPetDetails(petId);
    }
    
    /**
     * 
     * @return 
     */
    @GetMapping("/get-all-pets")
    public List<Pet> getAllPets() {
        return firebaseService.getAllPets();
    }
    
    /**
     * 
     * @return 
     */
    @GetMapping("/get-all-items")
    public List<Accessories> getAllItems() {
        return firebaseService.getAllItems();
    }
    
    /**
     * 
     * @return 
     */
    @GetMapping("/get-buyers")
    public List<Buyer> getBuyer() {
        return firebaseService.getBuyers();
    }
    
//    @CrossOrigin(origins = "http://localhost:4200")
//    @PutMapping("/update-buyer")
//    public String putExample(@RequestBody Buyer person){
//        return "Update user"+person.getFname();
//    }
//    
//    @CrossOrigin(origins = "http://localhost:4200")
//    @DeleteMapping("/remove-item")
//    public Pet removeItem(@RequestHeader Pet pet){
//        return firebaseService.removeItem(pet);
//    }
//    
//    @CrossOrigin(origins = "http://localhost:4200")
//    @DeleteMapping("/remove-item")
//    public Accessories removeItem(@RequestHeader Accessories accessories){
//        return firebaseService.removeItem(accessories);
//    }
//    
//    @DeleteMapping("/delete-buyer")
//    public String deleteExample(@RequestHeader String fname){
//        return "Delete user"+fname;
//    }
}
