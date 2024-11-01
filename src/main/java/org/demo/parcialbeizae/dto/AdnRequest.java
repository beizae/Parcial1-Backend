package org.demo.parcialbeizae.dto;

import lombok.Getter;
import lombok.Setter;
import org.demo.parcialbeizae.validaciones.ValidAdn;

@Getter
@Setter
public class AdnRequest {
    @ValidAdn
    private String[] adn;
}
