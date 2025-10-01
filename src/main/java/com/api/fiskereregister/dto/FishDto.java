package com.api.fiskereregister.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FishDto {
    private int id;
    @NotBlank(message="Navn må oppgis") String navn;
    @NotBlank(message = "Art må oppgis") String art;
    @NotNull @Positive(message = "Vekta må være mer enn 0") Double vekt;
    @NotNull @Positive(message = "Lengde mp være positiv") Double lengde;
}
