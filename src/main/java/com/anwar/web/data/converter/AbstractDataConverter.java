package com.anwar.web.data.converter;

import java.util.*;

/**
 * @author Anwar
 */

public abstract class AbstractDataConverter<T, U> implements DomainToDtoConverter<T, U> {

    @Override
    public Collection<U> convertAll(Collection<T> pojos) {
        List<U> dtos = new ArrayList<>(pojos.size());
        for (T t : pojos) {
            dtos.add(this.convert(t));
        }
        return dtos;
    }
}
