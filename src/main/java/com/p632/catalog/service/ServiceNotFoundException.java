package com.p632.catalog.service;

/**
 * Created by naveenjetty on 2/1/17.
 */
public class ServiceNotFoundException extends RuntimeException{

    public ServiceNotFoundException(String id) {
        super(String.format("No todo entry found with id: <%s>", id));
    }
}
