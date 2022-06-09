package edu.escuelaing.arsw.labs.locc.counter;

import java.util.ArrayList;
import java.util.List;

import edu.escuelaing.arsw.labs.locc.filter.Filter;

public interface Counter {

    public List<Filter> filters = new ArrayList<>();

    public int count(List<String> lines);

}
