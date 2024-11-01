package org.demo.parcialbeizae.entidades;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Adn extends Base implements Serializable {

    private String dna;
    private boolean isMutant;
}
