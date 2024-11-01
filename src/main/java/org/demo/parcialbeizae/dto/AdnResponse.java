package org.demo.parcialbeizae.dto;

import lombok.AllArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
public class AdnResponse {
    private boolean esMutante;

    public boolean isEsMutante() {
        return esMutante;
    }
}
