package net.codejava.assets.repository;

import net.codejava.assets.entity.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Repository
public interface ImageRepository extends JpaRepository<ImageData,Long> {

    Optional<ImageData> findByName(String fileName);
    List<ImageData> findByUser_Id(Long userId);
//    @Query("DELETE FROM ImageData i WHERE i.user = '2'")
    ImageData deleteImageById(Long imageId);
    @Transactional
    ImageData deleteImageDataById(Long imageId);

}