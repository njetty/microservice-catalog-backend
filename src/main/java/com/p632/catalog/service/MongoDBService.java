package com.p632.catalog.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * This service class saves {@link com.p632.catalog.service.MS} objects
 * to MongoDB database.
 * @author Naveen Jetty
 */

@Service
final class MongoDBService implements MSService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MongoDBService.class);

    private final MSRepository repository;

    @Autowired
    MongoDBService(MSRepository repository) {
        this.repository = repository;
    }

    @Override
    public MSDTO create(MSDTO ms) {
        LOGGER.info("Creating a new microservice entry with information: {}", ms);

        MS persisted = MS.getBuilder()
                .title(ms.getTitle())
                .description(ms.getDescription())
                .url(ms.getUrl())
                .build();

        persisted = repository.save(persisted);
        LOGGER.info("Created a new microservice entry with information: {}", persisted);

        return convertToDTO(persisted);    }

    @Override
    public MSDTO delete(String id) {
        LOGGER.info("Deleting a microservice entry with id: {}", id);

        MS deleted = findMSById(id);
        repository.delete(deleted);

        LOGGER.info("Deleted microservice entry with informtation: {}", deleted);

        return convertToDTO(deleted);    }

    @Override
    public List<MSDTO> findAll() {
        LOGGER.info("Finding all microservice entries.");

        List<MS> msEntries = repository.findAll();

        LOGGER.info("Found {} microservice entries", msEntries.size());

        return convertToDTOs(msEntries);
    }

    private List<MSDTO> convertToDTOs(List<MS> models) {
        return models.stream()
                .map(this::convertToDTO)
                .collect(toList());
    }


    @Override
    public MSDTO findById(String id) {
        LOGGER.info("Finding microservice entry with id: {}", id);

        MS found = findMSById(id);

        LOGGER.info("Found microservice entry: {}", found);

        return convertToDTO(found);    }

    @Override
    public MSDTO update(MSDTO ms) {
        LOGGER.info("Updating microservice entry with information: {}", ms);

        MS updated = findMSById(ms.getId());
        updated.update(ms.getTitle(), ms.getDescription(), ms.getUrl());
        updated = repository.save(updated);

        LOGGER.info("Updated microservice entry with information: {}", updated);

        return convertToDTO(updated);    }


    private MS findMSById(String id) {
        Optional<MS> result = repository.findOne(id);
        return result.orElseThrow(() -> new ServiceNotFoundException(id));

    }

    private MSDTO convertToDTO(MS model) {
        MSDTO dto = new MSDTO();

        dto.setId(model.getId());
        dto.setTitle(model.getTitle());
        dto.setDescription(model.getDescription());
        dto.setUrl(model.getUrl());

        return dto;
    }
}
