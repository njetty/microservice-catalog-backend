package com.p632.catalog.service;

import java.util.List;

/**
 * This interface declares the methods that provides CRUD operations for
 * {@link com.p632.catalog.service.MS} objects.
 * @author Petri Kainulainen
 */
interface MSService {

    /**
     * Creates a new microservice entry.
     * @param ms  The information of the created microservice entry.
     * @return      The information of the created microservice entry.
     */
    MSDTO create(MSDTO ms);

    /**
     * Deletes a ms entry.
     * @param id    The id of the deleted microservice entry.
     * @return      THe information of the deleted microservice entry.
     * @throws com.p632.catalog.service.ServiceNotFoundException  if no MS entry is found.
     */
    MSDTO delete(String id);

    /**
     * Finds all microservice entries.
     * @return      The information of all microservice entries.
     */
    List<MSDTO> findAll();

    /**
     * Finds a single microservice entry.
     * @param id    The id of the requested microservice entry.
     * @return      The information of the requested microservice entry.
     * @throws com.p632.catalog.service.ServiceNotFoundException if no microservice entry is found.
     */
    MSDTO findById(String id);

    /**
     * Updates the information of a microservice entry.
     * @param ms  The information of the updated microservice entry.
     * @return      The information of the updated microservice entry.
     * @throws com.p632.catalog.service.ServiceNotFoundException if no microservice entry is found.
     */
    MSDTO update(MSDTO ms);
}
