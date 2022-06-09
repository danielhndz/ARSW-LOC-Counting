package edu.escuelaing.arsw.labs.locc.counter;

import edu.escuelaing.arsw.labs.locc.filter.BlankFilter;
import edu.escuelaing.arsw.labs.locc.filter.JavaCommentFilter;

/**
 * Contador de líneas de código Java. Filtra las líneas en blanco y los
 * comentarios.
 */
public class LOCCounter extends CounterImpl {

    public LOCCounter() {
        // Filtros necesarios
        filters.add(new BlankFilter());
        filters.add(new JavaCommentFilter());
    }

}
