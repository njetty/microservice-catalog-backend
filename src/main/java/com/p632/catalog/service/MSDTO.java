package com.p632.catalog.service;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * This data transfer object contains the information of a single Microservice
 * entry and specifies validation rules that are used to ensure that only
 * valid information can be saved to the used database.
 * @author Naveen Jetty
 */
public final class MSDTO {

    private String id;
    private String description;

    @NotEmpty
    private String title;

    @NotEmpty
    private String url;

    public MSDTO() {

    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(String id) { this.id = id; }

    @Override
    public String toString() {
        return String.format(
                "MSDTO[url=%s, description=%s, title=%s, id=%s]",
                this.url,
                this.description,
                this.title,
                this.id
        );
    }
}
