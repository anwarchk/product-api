package com.anwar.web.data.converter;

import java.util.Collection;

/**
 * @author Anwar
 */
public interface DomainToDtoConverter<T, U> {

    U convert(T pojo);

    Collection<U> convertAll(Collection<T> pojos);
}
