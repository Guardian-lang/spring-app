package by.ahmed.springapp.service;

import by.ahmed.springapp.entity.Image;
import by.ahmed.springapp.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {

    @Value("${app.image.bucket}")
    private String bucket;
    private final ImageRepository imageRepository;

//    @Autowired
//    public void setBucket(@Value("${app.image.backet}")
//                                  String bucket) {
//        this.bucket = bucket;
//    }

    @SneakyThrows
    Long upload(MultipartFile multipartImage) throws Exception {
        Image image = new Image();
        image.setName(multipartImage.getName());
        image.setContent(multipartImage);

        return imageRepository.save(image)
                .getId();
    }


    @SneakyThrows
    public Optional<byte[]> get(String imagePath) {
        Path fullImagePath = Path.of(bucket, imagePath);

        return Files.exists(fullImagePath)
                ? Optional.of(Files.readAllBytes(fullImagePath))
                : Optional.empty();
    }
}
