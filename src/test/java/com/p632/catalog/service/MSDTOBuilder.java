package com.p632.catalog.service;

/**
 * Created by naveenjetty on 2/11/17.
 */
class MSDTOBuilder {

    private String description;
    private String id;
    private String title;
    private String url;

    MSDTOBuilder() {

    }

    MSDTOBuilder description(String description) {
        this.description = description;
        return this;
    }

    MSDTOBuilder id(String id) {
        this.id = id;
        return this;
    }

    MSDTOBuilder title(String title) {
        this.title = title;
        return this;
    }

    MSDTOBuilder url(String url) {
        this.url = url;
        return  this;
    }

    MSDTO build() {
        MSDTO dto = new MSDTO();

        dto.setDescription(description);
        dto.setId(id);
        dto.setTitle(title);
        dto.setUrl(url);

        return dto;
    }
}
