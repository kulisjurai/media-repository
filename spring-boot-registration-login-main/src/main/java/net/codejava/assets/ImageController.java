package net.codejava.assets;

import net.codejava.assets.entity.ImageData;
import net.codejava.assets.service.ImageService;
import net.codejava.user.entity.User;
import net.codejava.user.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:3000"})
@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ImageService service;
    @Autowired
    private CustomUserDetailsService userService;

    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file,@RequestParam("username") String username) throws IOException {
        User user = userService.loadUserByEmail(username);
        String uploadImage = service.uploadImage(file, user);

        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }
    @GetMapping
    public ResponseEntity<List<ImageData>> getAll(){
        return ResponseEntity.ok(service.getRepository().findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ImageData>> getAllById(@PathVariable("id")Long id){
        return ResponseEntity.ok(service.getRepository().findByUser_Id(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long>deleteImage(@PathVariable("id") Long id){
        Optional<ImageData> image = service.findById(id);
        service.deleteImage(image.get());
        return ResponseEntity.ok(4L);
    }
}
