package com.ryctabo.demo.secjwt.converter;

import java.io.Serializable;

/**
 * @author Gustavo Pacheco (ryctabo at gmail.com)
 * @version 1.0-SNAPSHOT
 */
public interface DtoConverter<E extends Serializable, R, S> {

    S fromDomain(E source);

    E fromRequest(R source);
}
