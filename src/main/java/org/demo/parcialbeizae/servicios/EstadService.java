package org.demo.parcialbeizae.servicios;

import org.demo.parcialbeizae.dto.EstadResponse;
import org.demo.parcialbeizae.repositorios.AdnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadService {

    private final AdnRepository adnRepository;

    @Autowired
    public EstadService(AdnRepository adnRepository) {
        this.adnRepository = adnRepository;
    }

    public EstadResponse getStats() {
        long countMutantDna = adnRepository.countByIsMutant(true);
        long countHumanDna = adnRepository.countByIsMutant(false);
        double ratio = countHumanDna == 0 ? 0 : (double) countMutantDna / countHumanDna;
        return new EstadResponse(countMutantDna, countHumanDna, ratio);
    }
}