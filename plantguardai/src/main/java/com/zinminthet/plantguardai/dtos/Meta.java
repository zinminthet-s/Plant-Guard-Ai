package com.zinminthet.plantguardai.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Meta {
    private String requestId;
    private String path;
    private String timestamp;
    private String method;
}
