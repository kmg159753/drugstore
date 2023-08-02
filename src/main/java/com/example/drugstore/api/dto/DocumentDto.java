package com.example.drugstore.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class DocumentDto {
    @JsonProperty("place_name")
    private String placeName;

    @JsonProperty("address_name")
    private String addressName;
    @JsonProperty("x")
    private double latitude;
    @JsonProperty("y")
    private double longitude;

    @JsonProperty("distance")
    private double distance;
}
