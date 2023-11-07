package com.example.drill.config;

import org.mapstruct.Builder;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@org.mapstruct.MapperConfig(
        componentModel = "spring"
        , unmappedTargetPolicy = ReportingPolicy.IGNORE
        , nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
        , builder = @Builder(disableBuilder = true)
)
public class MapperConfig {
}
