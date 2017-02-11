package com.p632.catalog.service;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

/**
 * This repository provides CRUD operations for {@link MS}
 * objects.
 * @author Naveen Jetty
 */

interface MSRepository extends Repository<MS, String>{
    /**
     * Deletes a Microservice entry from the database.
     * @param deleted   The deleted MS entry.
     */
    void delete(MS deleted);

    /**
     * Finds all Microservice entries from the database.
     * @return  The information of all Microservice entries that are found from the database.
     */
    List<MS> findAll();

    /**
     * Finds the information of a single Microservice entry.
     * @param id    The id of the requested Microservice entry.
     * @return      The information of the found Microservice entry. If no Microservice entry
     *              is found, this method returns an empty {@link java.util.Optional} object.
     */
    Optional<MS> findOne(String id);

    /**
     * Saves a new Microservice entry to the database.
     * @param saved The information of the saved Microservice entry.
     * @return      The information of the saved Microservice entry.
     */
    MS save(MS saved);
}
