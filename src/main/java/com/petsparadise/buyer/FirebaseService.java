package com.petsparadise.buyer;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.petsparadise.cart.Cart;
import com.petsparadise.admin.Admin;
import com.petsparadise.store.Store;
import com.petsparadise.product.Accessories;
import com.petsparadise.product.Pet;
import com.petsparadise.product.Product;
import com.petsparadise.request.Request;
import com.petsparadise.seller.Seller;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FirebaseService {
    private Buyer person, loggedUser;
    private Seller loggedSeller, seller;
    private Admin loggedAdmin;
    private Pet pett;
   
    
    @Autowired
    FirebaseInitialization firebaseInitialization;
    
    /**
     * 
     * @param person
     * @return
     * @throws InterruptedException
     * @throws ExecutionException 
     */
    public Buyer createBuyer(Buyer buyer) throws InterruptedException, ExecutionException{
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("buyer");
        
        String hashedPassword;
         try {
            hashedPassword = HashPassword.toHexString(HashPassword.getSHA(buyer.getPassword()));
            buyer.setPassword(hashedPassword);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(FirebaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String key = database.push().getKey();
        buyer.setbId(key);
        
        database.child(key).setValue(buyer, (DatabaseError de, DatabaseReference dr) -> {
            
        });
        
        return buyer;
    }
    
    /**
     * 
     * @param seller
     * @return 
     */
    public Seller createSeller(Seller seller) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("seller");
        
        String hashedPassword;
        try {
            hashedPassword = HashPassword.toHexString(HashPassword.getSHA(seller.getsPassword()));
            seller.setsPassword(hashedPassword);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(FirebaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String key = database.push().getKey();
        seller.setsId(key);
        
        database.child(key).setValue(seller, (DatabaseError de, DatabaseReference dr) -> {
            
        });
        
        return seller;
    }
    
    /**
     * 
     * @param file
     * @return
     * @throws IOException 
     */
    public String uploadNicFrontImage(MultipartFile file) throws IOException {
        return firebaseInitialization.uploadNicFrontImage(file);
    }
    
    /**
     * 
     * @param file
     * @return
     * @throws IOException 
     */
    public String uploadNicBackImage(MultipartFile file) throws IOException {
        return firebaseInitialization.uploadNicBackImage(file);
    }
    
    /**
     * 
     * @param file
     * @return
     * @throws IOException 
     */
    public String uploadBankStatementImage(MultipartFile file) throws IOException {
        return firebaseInitialization.uploadBankStatementImage(file);
    }
    
    /**
     * 
     * @param store
     * @return 
     */
     public Store createStore(Store store) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("stores");
        
        Date dateWithoutTime;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dateWithoutTime = sdf.parse(sdf.format(new Date()));
            store.setDateCreated(dateWithoutTime.toString());
        } catch (ParseException ex) {
            Logger.getLogger(FirebaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String key = database.push().getKey();
        store.setStoreId(key);
        
        database.child(key).setValue(store, (DatabaseError de, DatabaseReference dr) -> {
            
        });
        
        return store;
    }
     
    /**
     * 
     * @param pet
     * @return 
     */
    public Pet addPet(Pet pet) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("products/pets");
        
        String key = database.push().getKey();
        pet.setId(key);
        
        database.child(key).setValue(pet, (DatabaseError de, DatabaseReference dr) -> {
            
        });
        
        return pet;
    }
    
    /**
     * 
     * @param file
     * @return
     * @throws IOException 
     */
    public String uploadPetImages(MultipartFile file) throws IOException {
        return firebaseInitialization.uploadPetImage(file);
    }
    
    /**
     * 
     * @param accessories
     * @return 
     */
    public Accessories addItem(Accessories accessories) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("products/pets-accessories");
        
        String key = database.push().getKey();
        accessories.setId(key);
        
        database.child(key).setValue(accessories, (DatabaseError de, DatabaseReference dr) -> {
            
        });
        
        return accessories;
    }
    
    /**
     * 
     * @param file
     * @return
     * @throws IOException 
     */
    public String uploadItemImages(MultipartFile file) throws IOException {
        return firebaseInitialization.uploadItemImage(file);
    }
    
    
    /**
     * 
     * @param cart
     * @return 
     */
    public Cart addCart(Cart cart) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("cart");
        
        String key = database.push().getKey();
        cart.setCartId(key);
        
        database.child(key).setValue(cart, (DatabaseError de, DatabaseReference dr) -> {
            
        });
        
        return cart;
    }
    
    public Request requestPet(Request request) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("request");
        
        String key = database.push().getKey();
        request.setRequestId(key);
        
        database.child(key).setValue(request, (DatabaseError de, DatabaseReference dr) -> {
            
        });
        
        return request;
    }
    
     /**
     * 
     * @param file
     * @return
     * @throws IOException 
     */
    public String uploadRequestPetImages(MultipartFile file) throws IOException {
        return firebaseInitialization.uploadRequestPetImage(file);
    }
    
    /**
     * 
     * @param buyer
     * @return 
     */
    public Buyer validateLogin(Buyer buyer) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("buyer");
        
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {
                for (DataSnapshot snapshot : ds.getChildren()) {
                    loggedUser = null;
                    
                    Buyer buy = snapshot.getValue(Buyer.class);
                    
                    String hashedPassword = null;
                    try {
                        hashedPassword = HashPassword.toHexString(HashPassword.getSHA(buyer.getPassword()));
                    } catch (NoSuchAlgorithmException ex) {
                        Logger.getLogger(FirebaseService.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    if (buy != null) {
                        if (buy.getEmail().equals(buyer.getEmail()) && 
                                buy.getPassword().equals(hashedPassword)) {
                            loggedUser = buy;
                        }
                    }
                }
            }
            
            @Override
            public void onCancelled(DatabaseError de) {
                
            }
        });
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(FirebaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return loggedUser;
    }
    
    /**
     * 
     * @param seller
     * @return 
     */
    public Seller validateLogin(Seller seller) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("seller");
        
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {
                loggedSeller = null;
                
                for (DataSnapshot snapshot : ds.getChildren()) {
                    Seller sell = snapshot.getValue(Seller.class);
                    
                    String hashedPassword = null;
                    try {
                        hashedPassword = HashPassword.toHexString(HashPassword.getSHA(seller.getsPassword()));
                    } catch (NoSuchAlgorithmException ex) {
                        Logger.getLogger(FirebaseService.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    if (sell != null) {
                        if (sell.getsEmail().equals(seller.getsEmail()) && 
                                sell.getsPassword().equals(hashedPassword)) {
                            loggedSeller = sell;
                        }
                    }
                }
            }
            
            @Override
            public void onCancelled(DatabaseError de) {
                
            }
        });
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(FirebaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return loggedSeller;
    }
    
    
       /**
     * 
     * @param admin
     * @return 
     */
    public Admin validateLogin(Admin admin) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("admin");
        
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {
                for (DataSnapshot snapshot : ds.getChildren()) {
                    loggedAdmin = null;
                    
                    Admin admin = snapshot.getValue(Admin.class);
                                    
                    if (admin != null) {
                        if (admin.getEmail().equals(admin.getEmail()) && 
                                admin.getPassword().equals(admin.getPassword())) {
                            loggedAdmin = admin;
                        }
                    }
                }
            }
            
            @Override
            public void onCancelled(DatabaseError de) {
                
            }
        });
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(FirebaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return loggedAdmin;
    }
    
    
    /**
     * 
     * @return 
     */
    public List<Buyer> getBuyers() {
        List<Buyer> list = new ArrayList<>();
        
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("buyer");
        
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {
                list.clear();
                
                for (DataSnapshot snapshot : ds.getChildren()) {
                    person = snapshot.getValue(Buyer.class);
                    list.add(person);
                }
            }

            @Override
            public void onCancelled(DatabaseError de) {
                
            }
        });
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(FirebaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        return list;
    }
    
      /**
     * 
     * @return 
     */
    public List<Seller> getSeller() {
        List<Seller> sellerList = new ArrayList<>();
        
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("seller");
        
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {
                sellerList.clear();
                
                for (DataSnapshot snapshot : ds.getChildren()) {
                    seller = snapshot.getValue(Seller.class);
                    sellerList.add(seller);
                }
            }

            @Override
            public void onCancelled(DatabaseError de) {
                
            }
        });
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(FirebaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        return sellerList;
    }
    
    /**
     * 
     * @param sellerId
     * @return 
     */
    public List<Store> getStores(String sellerId) {
        List<Store> list = new ArrayList<>();
        
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("stores");
        
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {
                list.clear();
                
                for (DataSnapshot snapshot : ds.getChildren()) {
                    Store store = snapshot.getValue(Store.class);
                    
                    if (store != null && store.getSellerId().equals(sellerId))
                        list.add(store);
                }
            }

            @Override
            public void onCancelled(DatabaseError de) {
                
            }
        });
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(FirebaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
    /**
     * 
     * @param storeId
     * @return 
     */
    public List<Pet> getPets(String storeId) {
        List<Pet> petsList = new ArrayList<>();
        
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("products/pets");
        
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {
                petsList.clear();
                for (DataSnapshot snapshot : ds.getChildren()) {
                    Pet pet = snapshot.getValue(Pet.class);
                    
                    if (pet != null && pet.getStoreId().equals(storeId)) {
                        petsList.add(pet);
                    }
                }
            }
          
            @Override
            public void onCancelled(DatabaseError de) {
                
            }
        });
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(FirebaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return petsList;
    }
    
    /**
     * 
     * @param storeId
     * @return 
     */
    public List<Accessories> getItems(String storeId) {
        List<Accessories> itemList = new ArrayList<>();
        
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("products/pets-accessories");
        
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {
                itemList.clear();
                
                for (DataSnapshot snapshot : ds.getChildren()) {
                    Accessories item = snapshot.getValue(Accessories.class);
                    
                    if (item != null && item.getStoreId().equals(storeId)) {
                        itemList.add(item);
                    }
                }
            }
          
            @Override
            public void onCancelled(DatabaseError de) {
                
            }
        });
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(FirebaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return itemList;
    }
   

    public List<Cart> getCartList(String bId) {
        List<Cart> cartList = new ArrayList<>();
        
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("cart");
        
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {
                cartList.clear();
                for (DataSnapshot snapshot : ds.getChildren()) {
                    Cart cart = snapshot.getValue(Cart.class);
                    System.out.println(cart.getBuyer().getbId());
                    
                    if (cart != null && cart.getBuyer().getbId().equals(bId)) {
                        cartList.add(cart);
                        System.out.println(cart.getBuyer().getFname());
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError de) {
                
            }
        });
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(FirebaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cartList;
    }
    
    public Cart removeItem(Cart cart) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("cart");
        
        database.child(cart.getCartId()).removeValue((DatabaseError de, DatabaseReference dr) -> {
            System.out.println(cart.getCartId());
        });
//        
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(FirebaseService.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        return cart;
    }
    
    public List<Request> getRequestDetails() {
        List<Request> requestList = new ArrayList<>();
        
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("request");
        
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {
                requestList.clear();
                
                for (DataSnapshot snapshot : ds.getChildren()) {
                    Request request = snapshot.getValue(Request.class);
                    requestList.add(request);
                }
            }

            @Override
            public void onCancelled(DatabaseError de) {
                
            }
        });
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(FirebaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        return requestList;
    }
          
    
    public List<Product> searchProduct(String keyword) {
        List<Product> productList = new ArrayList<>();
        
        DatabaseReference petRef = FirebaseDatabase.getInstance().getReference("products/pets");
        DatabaseReference itemRef = FirebaseDatabase.getInstance().getReference("products/pets-accessories");
        
        petRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {
                for (DataSnapshot snapshot : ds.getChildren()) {
                    Pet pet = snapshot.getValue(Pet.class);
                    
                    if (pet != null) {
                        if (pet.getName().toLowerCase().contains(keyword.toLowerCase())) {
                            productList.add(pet);
                        }else if (pet.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                            productList.add(pet);
                        }else if (pet.getBreed().toLowerCase().contains(keyword.toLowerCase())) {
                            productList.add(pet);
                        }
                    }
                }
            }
          
            @Override
            public void onCancelled(DatabaseError de) {
                
            }
        });
        
        itemRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {
                for (DataSnapshot snapshot : ds.getChildren()) {
                    Accessories item = snapshot.getValue(Accessories.class);
                    
                    if (item != null) {
                        if (item.getName().toLowerCase().contains(keyword.toLowerCase())) {
                            productList.add(item);
                        }else if (item.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                            productList.add(item);
                        }else if (item.getManufacturer().toLowerCase().contains(keyword.toLowerCase())) {
                            productList.add(item);
                        }
                    }
                }
            }
          
            @Override
            public void onCancelled(DatabaseError de) {
                
            }
        });
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(FirebaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return productList;
    }
    
    public List<Product> filterCategor(String selectedCategory) {
        DatabaseReference ref = null;
        List<Product> filterList = new ArrayList<>();
        
        if (selectedCategory.equals("pets")) {
            ref = FirebaseDatabase.getInstance().getReference("products/pets");
        }else if (selectedCategory.equals("accessories")) {
            ref = FirebaseDatabase.getInstance().getReference("products/pets-accessories");
        }
        
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {
                filterList.clear();
                
                for (DataSnapshot snapshot : ds.getChildren()) {
                    Product product = snapshot.getValue(Product.class);
                    
                    if (product != null) {
                        filterList.add(product);
                    }
                }
            }
          
            @Override
            public void onCancelled(DatabaseError de) {
                
            }
        });
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(FirebaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return filterList;
    }
    
    public List<Product> searchPredictedProduct(String predictedKeyword) {
        String keyword = predictedKeyword.split(" ")[0];
        System.out.println(keyword);
        
        keyword = keyword.replace("_", " ");
        System.out.println(keyword);
        
        //keyword = keyword.split(" ")[0].toLowerCase();
        //System.out.println(keyword);

        List<Product> list = searchProduct(keyword);
        return list;
    }
    
    public Pet getPetDetails(String petId) {
        DatabaseReference ref;
        
        ref = FirebaseDatabase.getInstance().getReference("products/pets");
        
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {
                for (DataSnapshot snapshot : ds.getChildren()) {
                    Pet pet = snapshot.getValue(Pet.class);
                    
                    if (pet != null) {
                        if (pet.getId().equals(petId)) {
                            pett = pet;
                        }
                    }
                }
            }
          
            @Override
            public void onCancelled(DatabaseError de) {
                
            }
        });
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(FirebaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return pett;
    }
    
    public List<Pet> getAllPets() {
        List<Pet> allPets = new ArrayList<>();

        DatabaseReference petRef = FirebaseDatabase.getInstance().getReference("products/pets");

        petRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {
                for (DataSnapshot snapshot : ds.getChildren()) {
                    Pet pet = snapshot.getValue(Pet.class);
                    allPets.add(pet);
                }
            }

            @Override
            public void onCancelled(DatabaseError de) {

            }
        });
            
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(FirebaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return allPets;
    }
    
    public List<Accessories> getAllItems() {
        List<Accessories> allItems = new ArrayList<>();

        DatabaseReference petRef = FirebaseDatabase.getInstance().getReference("products/pets-accessories");

        petRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {
                for (DataSnapshot snapshot : ds.getChildren()) {
                    Accessories item = snapshot.getValue(Accessories.class);
                    allItems.add(item);
                }
            }

            @Override
            public void onCancelled(DatabaseError de) {

            }
        });
            
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(FirebaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return allItems;
    }

     
  
}

