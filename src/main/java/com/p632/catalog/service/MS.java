package com.p632.catalog.service;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.data.annotation.Id;

import static com.p632.catalog.util.PreCondition.*;

/**
 * @author Naveen Jetty
 */

final class MS {

    static final int MAX_LENGTH_DESCRIPTION = 250;
    static final int MAX_LENGTH_TITLE = 50;

    @Id
    private String id;

    private String description;

    private String title;

    private String url;

    public MS(){}

    private MS(Builder builder) {
        this.description = builder.description;
        this.title = builder.title;
        this.url = builder.url;
    }

    static Builder getBuilder() {
        return new Builder();
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {return url; }

    public void update(String title, String description, String url) {
        checkTitleAndDescriptionAndUrl(title, description, url);

        this.title = title;
        this.description = description;
        this.url = url;
    }

    @Override
    public String toString() {
        return String.format(
                "MS[id=%s, description=%s, title=%s, url=%s]",
                this.id,
                this.description,
                this.title,
                this.url
        );
    }

    /**
     * We don't have to use the builder pattern here because the constructed class has only two String fields.
     * However, I use the builder pattern in this example because it makes the code a bit easier to read.
     */
    static class Builder {

        private String description;

        private String title;

        private String url;

        private Builder() {}

        Builder description(String description) {
            this.description = description;
            return this;
        }

        Builder title(String title) {
            this.title = title;
            return this;
        }

        Builder url(String url) {
            this.url = url;
            return this;
        }

        MS build() {
            MS build = new MS(this);

            build.checkTitleAndDescriptionAndUrl(build.getTitle(), build.getDescription(), build.getUrl());

            return build;
        }
    }

    private void checkTitleAndDescriptionAndUrl(String title, String description, String url) {
        notNull(title, "Title cannot be null");
        notEmpty(title, "Title cannot be empty");

        notNull(description, "Description cannot be null");
        notEmpty(description, "Description cannot be empty");

        isTrue(title.length() <= MAX_LENGTH_TITLE,
                "Title cannot be longer than %d characters",
                MAX_LENGTH_TITLE
        );

        if (description != null) {
            isTrue(description.length() <= MAX_LENGTH_DESCRIPTION,
                    "Description cannot be longer than %d characters",
                    MAX_LENGTH_DESCRIPTION
            );
        }

        notNull(url, "Url cannot be null");
        notEmpty(url, "Url cannot be empty");
        isValidUrl(url, "Invalid URL Format");
    }
}
