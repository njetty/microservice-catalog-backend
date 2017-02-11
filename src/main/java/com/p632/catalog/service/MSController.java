package com.p632.catalog.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * This controller provides the public API that is used to manage the information
 * of todo entries.
 * @author Petri Kainulainen
 */
@RestController
@RequestMapping("/api/catalog")
final class MSController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MSController.class);

    private final MSService service;

    @Autowired
    MSController(MSService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    MSDTO create(@RequestBody @Valid MSDTO MSEntry) {
        LOGGER.info("Creating a new microservice entry with information: {}", MSEntry);

        MSDTO created = service.create(MSEntry);
        LOGGER.info("Created a new microservice entry with information: {}", created);

        return created;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    MSDTO delete(@PathVariable("id") String id) {
        LOGGER.info("Deleting microservice entry with id: {}", id);

        MSDTO deleted = service.delete(id);
        LOGGER.info("Deleted microservice entry with information: {}", deleted);

        return deleted;
    }

    @RequestMapping(method = RequestMethod.GET)
    List<MSDTO> findAll() {
        LOGGER.info("Finding all microservice entries");

        List<MSDTO> msEntries = service.findAll();
        LOGGER.info("Found {} microservice entries", msEntries.size());

        return msEntries;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    MSDTO findById(@PathVariable("id") String id) {
        LOGGER.info("Finding microservice entry with id: {}", id);

        MSDTO msEntry = service.findById(id);
        LOGGER.info("Found microservice entry with information: {}", msEntry);

        return msEntry;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    MSDTO update(@RequestBody @Valid MSDTO msEntry) {
        LOGGER.info("Updating microservice entry with information: {}", msEntry);

        MSDTO updated = service.update(msEntry);
        LOGGER.info("Updated microservice entry with information: {}", updated);

        return updated;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleTodoNotFound(ServiceNotFoundException ex) {
        LOGGER.error("Handling error with message: {}", ex.getMessage());
    }
}
