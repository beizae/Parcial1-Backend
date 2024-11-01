package org.demo.parcialbeizae.servicios;

import org.demo.parcialbeizae.entidades.Adn;
import org.demo.parcialbeizae.repositorios.AdnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AdnService {

    private final AdnRepository adnRepository;
    private static final int SEQUENCE_LENGTH = 4;

    @Autowired
    public AdnService(AdnRepository adnRepository) {
        this.adnRepository = adnRepository;
    }

    public static boolean esMutante(String[] adn) {
        int n = adn.length;
        int cantCoincidencias = 0;

        // Verifico filas
        int fila = 0;
        while (fila < n) {
            int col = 0;
            while (col <= n - 4) {
                if (adn[fila].charAt(col) == adn[fila].charAt(col+1) &&
                        adn[fila].charAt(col) == adn[fila].charAt(col+2) &&
                        adn[fila].charAt(col) == adn[fila].charAt(col+3) ) {
                    //System.out.printf("Fila [%d, %d]\n", fila, col);
                    cantCoincidencias++;
                }
                col++;
            }
            fila++;
        }
        if (cantCoincidencias >= 2) return true;

        // Verifico columnas
        int col = 0;
        while (col < n) {
            fila = 0;
            while (fila <= n - 4) {
                if (adn[fila].charAt(col) == adn[fila+1].charAt(col) &&
                        adn[fila].charAt(col) == adn[fila+2].charAt(col) &&
                        adn[fila].charAt(col) == adn[fila+3].charAt(col)) {
                    //System.out.printf("Columna [%d, %d]\n", fila, col);
                    cantCoincidencias++;
                }
                fila++;
            }
            col++;
        }
        if (cantCoincidencias >= 2) return true;

        // Verifico diagonal ppal
        fila = 0;
        while (fila <= n - 4) {
            col = 0;
            while (col <= n - 4) {
                if (adn[fila].charAt(col) == adn[fila+1].charAt(col+1) &&
                        adn[fila].charAt(col) == adn[fila+2].charAt(col+2) &&
                        adn[fila].charAt(col) == adn[fila+3].charAt(col+3)) {
                    //System.out.printf("Diagonal ppal [%d, %d]\n", fila, col);
                    cantCoincidencias++;
                }
                col++;
            }
            fila++;
        }
        if (cantCoincidencias >= 2) return true;

        // Verifico diagonal inversa
        fila = 0;
        while (fila <= n - 4) {
            col = 3;
            while (col < n) {
                if (adn[fila].charAt(col) == adn[fila+1].charAt(col-1) &&
                        adn[fila].charAt(col) == adn[fila+2].charAt(col-2) &&
                        adn[fila].charAt(col) == adn[fila+3].charAt(col-3) ) {
                    //System.out.printf("Diagonal inver [%d, %d]\n", fila, col);
                    cantCoincidencias++;
                }
                col++;
            }
            fila++;
        }
        return cantCoincidencias >= 2;





    }



    private static boolean checkDiagonal(String[] dna, int x, int y, int dx, int dy, int n) {
        char first = dna[x].charAt(y);
        for (int i = 1; i < SEQUENCE_LENGTH; i++) {
            if (x + i * dx >= n || y + i * dy >= n || y + i * dy < 0 || dna[x + i * dx].charAt(y + i * dy) != first) {
                return false;
            }
        }
        return true;
    }

    public boolean analyzeDna(String[] adn) {
        String adnrec = String.join(",", adn);

        // Verificamos si el ADN existe en la bd
        Optional<Adn> existingDna = adnRepository.findByDna(adnrec);
        if (existingDna.isPresent()) {
            // Si el ADN existe devolvemos el resultado
            return existingDna.get().isMutant();
        }

        // Determinamos si el ADN es mutante y lo guardamos en bd
        boolean esMutante = esMutante(adn);
        Adn adnEntity = Adn.builder()
                .dna(adnrec)
                .isMutant(esMutante)
                .build();
        adnRepository.save(adnEntity);

        return esMutante(adn);
    }
}
