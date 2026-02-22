package com.zinminthet.plantguardai.dtos.responses.shopResponseDto;


import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.Nullable;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopResponseDto {
    private Long id;
    private String shopName;
    private String shopAddress;
    private String description;
}
