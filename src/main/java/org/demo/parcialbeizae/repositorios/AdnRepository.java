package org.demo.parcialbeizae.repositorios;

import org.demo.parcialbeizae.entidades.Adn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AdnRepository extends JpaRepository<Adn, Long> {
    Optional<Adn> findByDna(String dnaSequence);

    long countByIsMutant(boolean isMutant);
}
