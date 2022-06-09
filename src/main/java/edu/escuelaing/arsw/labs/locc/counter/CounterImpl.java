package edu.escuelaing.arsw.labs.locc.counter;

import java.util.List;

import edu.escuelaing.arsw.labs.locc.filter.Filter;

public class CounterImpl implements Counter {

    /**
     * Aplica los filtros, en orden, de filters a lines y retorna el número de lines
     * que quedaron.
     * 
     * @param lines Lista de líneas (String).
     * @return Cantidad de líneas que quedaron después de aplicar los filtros.
     */
    @Override
    public int count(List<String> lines) {
        if (filters != null && !filters.isEmpty()) {
            for (Filter filter : filters) {
                if (filter != null) {
                    lines = filter.filter(lines);
                }
            }
        }
        return lines.size();
    }

}
