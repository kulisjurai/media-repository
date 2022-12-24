package net.codejava.assets.service;

import net.codejava.assets.entity.ImageData;
import net.codejava.assets.utils.ImageUtils;
import net.codejava.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import net.codejava.assets.repository.ImageRepository;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;
@Service
public class ImageService {
    @Autowired
    private ImageRepository repository;

    public String uploadImage(MultipartFile file, User user) throws IOException {

        ImageData imageData = repository.save(ImageData.builder()
                .user(user)
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());
        if (imageData != null) {
            return "file uploaded successfully : " + file.getOriginalFilename();
        }
        return null;
    }

    public ImageRepository getRepository() {
        return repository;
    }

    public void deleteImage(ImageData imageData){
         repository.delete(imageData);
    }

    public Optional<ImageData> findById(Long id) {
        return this.repository.findById(id);
    }
}
