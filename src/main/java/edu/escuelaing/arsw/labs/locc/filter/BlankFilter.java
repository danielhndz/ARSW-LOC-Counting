package edu.escuelaing.arsw.labs.locc.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un filtro de líneas en blanco.
 */
public class BlankFilter implements Filter {

    /**
     * Filtra las líneas en blanco
     * 
     * @param lines Lista de líneas que se quieren filtrar.
     * @return Lista de líneas filtradas.
     */
    @Override
    public List<String> filter(List<String> lines) {
        List<String> filtredLines = new ArrayList<>();
        for (String line : lines) {
            if (!line.isBlank()) {
                filtredLines.add(line);
            }
        }
        return filtredLines;
    }

}
