package com.petsparadise.buyer;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FirebaseInitialization {
    
    private Storage storage;
    
    @PostConstruct
    public void initialization(){
        FileInputStream serviceAccount;
        
        try{
          serviceAccount = new FileInputStream("src/main/java/com/petsparadise/buyer/serviceAccountKey.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://pets-paradise-527b8-default-rtdb.firebaseio.com")
                .build();

            FirebaseApp.initializeApp(options);
            
            serviceAccount = new FileInputStream("src/main/java/com/petsparadise/buyer/serviceAccountKey.json");
            storage = StorageOptions.newBuilder()
                .setProjectId("pets-paradise-527b8")
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build()
                .getService();

        }catch(IOException e){
        }
    }
    
    /**
     * 
     * @param file
     * @return
     * @throws IOException 
     */
    public String uploadNicFrontImage(MultipartFile file) throws IOException{
        String imageName = generateFileName(file.getOriginalFilename());
        
        Map<String, String> map = new HashMap<>();
        map.put("firebaseStorageDownloadTokens", imageName);
        
        BlobId blobId = BlobId.of("pets-paradise-527b8.appspot.com", "nic-images/" + imageName);
        
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setMetadata(map)
                .setContentType(file.getContentType())
                .build();
        storage.create(blobInfo, file.getInputStream());
        
        URL url = storage.signUrl(blobInfo, 5, TimeUnit.DAYS, Storage.SignUrlOption.withV4Signature());
        String signedPath = url.toString();
        
        return signedPath;
    }
    
    /**
     * 
     * @param file
     * @return
     * @throws IOException 
     */
    public String uploadNicBackImage(MultipartFile file) throws IOException{
        String imageName = generateFileName(file.getOriginalFilename());
        
        Map<String, String> map = new HashMap<>();
        map.put("firebaseStorageDownloadTokens", imageName);
        
        BlobId blobId = BlobId.of("pets-paradise-527b8.appspot.com", "nic-images/" + imageName);
        
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setMetadata(map)
                .setContentType(file.getContentType())
                .build();
        storage.create(blobInfo, file.getInputStream());
        
        URL url = storage.signUrl(blobInfo, 5, TimeUnit.DAYS, Storage.SignUrlOption.withV4Signature());
        String signedPath = url.toString();
        
        return signedPath;
    }
    
    /**
     * 
     * @param file
     * @return
     * @throws IOException 
     */
    public String uploadBankStatementImage(MultipartFile file) throws IOException{
        String imageName = generateFileName(file.getOriginalFilename());
        
        Map<String, String> map = new HashMap<>();
        map.put("firebaseStorageDownloadTokens", imageName);
        
        BlobId blobId = BlobId.of("pets-paradise-527b8.appspot.com", "bank-statement-images/" + imageName);
        
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setMetadata(map)
                .setContentType(file.getContentType())
                .build();
        storage.create(blobInfo, file.getInputStream());
        
        URL url = storage.signUrl(blobInfo, 5, TimeUnit.DAYS, Storage.SignUrlOption.withV4Signature());
        String signedPath = url.toString();
        
        return signedPath;
    }
    
    /**
     * 
     * @param file
     * @return
     * @throws IOException 
     */
    public String uploadPetImage(MultipartFile file) throws IOException{
        String imageName = generateFileName(file.getOriginalFilename());
        
        Map<String, String> map = new HashMap<>();
        map.put("firebaseStorageDownloadTokens", imageName);
        
        BlobId blobId = BlobId.of("pets-paradise-527b8.appspot.com", "pets-images/" + imageName);
        
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setMetadata(map)
                .setContentType(file.getContentType())
                .build();
        storage.create(blobInfo, file.getInputStream());
        
        URL url = storage.signUrl(blobInfo, 5, TimeUnit.DAYS, Storage.SignUrlOption.withV4Signature());
        String signedPath = url.toString();
        
        return signedPath;
    }
    
    /**
     * 
     * @param file
     * @return
     * @throws IOException 
     */
    public String uploadRequestPetImage(MultipartFile file) throws IOException{
        String imageName = generateFileName(file.getOriginalFilename());
        
        Map<String, String> map = new HashMap<>();
        map.put("firebaseStorageDownloadTokens", imageName);
        
        BlobId blobId = BlobId.of("pets-paradise-527b8.appspot.com", "request-pet-images/" + imageName);
        
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setMetadata(map)
                .setContentType(file.getContentType())
                .build();
        storage.create(blobInfo, file.getInputStream());
        
        URL url = storage.signUrl(blobInfo, 5, TimeUnit.DAYS, Storage.SignUrlOption.withV4Signature());
        String signedPath = url.toString();
        
        return signedPath;
    }
    
    /**
     * 
     * @param file
     * @return
     * @throws IOException 
     */
    public String uploadItemImage(MultipartFile file) throws IOException{
        String imageName = generateFileName(file.getOriginalFilename());
        
        Map<String, String> map = new HashMap<>();
        map.put("firebaseStorageDownloadTokens", imageName);
        
        BlobId blobId = BlobId.of("pets-paradise-527b8.appspot.com", "accessories-images/" + imageName);
        
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setMetadata(map)
                .setContentType(file.getContentType())
                .build();
        storage.create(blobInfo, file.getInputStream());
        
        URL url = storage.signUrl(blobInfo, 5, TimeUnit.DAYS, Storage.SignUrlOption.withV4Signature());
        String signedPath = url.toString();
        
        return signedPath;
    }
    
    /**
     * 
     * @param originalFileName
     * @return 
     */
    private String generateFileName(String originalFileName) {
        return UUID.randomUUID().toString() + "." + getExtension(originalFileName);
    }

    /**
     * 
     * @param originalFileName
     * @return 
     */
    private String getExtension(String originalFileName) {
        return StringUtils.getFilenameExtension(originalFileName);
    }
        
}
