package com.cinemas.dto.request;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WaterCornRequest {
    private int id;
    @NotEmpty(message = "VALIDATION")
    private String name;
    private Long price;
    private String description;
    private MultipartFile file;
}
