package com.aarreaza.montask.model.dao;

import java.util.List;

public interface GenericDAO<E> {

    enum operationResult {
        SUCCESS,
        DUPLICATE_KEY,
        UKNOWN,
        ERROR
    }

    operationResult create(E object);

    List<E> getAll();

    E getById(int number);

    operationResult update(E object);

}
