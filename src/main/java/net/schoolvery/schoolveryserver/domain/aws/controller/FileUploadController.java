package net.schoolvery.schoolveryserver.domain.aws.controller;

import lombok.RequiredArgsConstructor;
import net.schoolvery.schoolveryserver.domain.aws.service.FileUploadService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class FileUploadController {

    private final FileUploadService fileUploadService;

    @PostMapping("/api/v1/upload")
    public String uploadImage(@RequestPart(value = "file", required = false) MultipartFile multipartFile) throws IllegalAccessException {
        return fileUploadService.uploadImage(multipartFile);
    }
}
