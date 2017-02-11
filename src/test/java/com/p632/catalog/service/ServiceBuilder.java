package com.p632.catalog.service;

import org.springframework.test.util.ReflectionTestUtils;

/**
 * Created by naveenjetty on 2/11/17.
 */
class ServiceBuilder {
    private String description;
    private String id;
    private String title = "NOT_IMPORTANT";
    private String url = "http://url.extn";

    ServiceBuilder() {

    }

    ServiceBuilder description(String description) {
        this.description = description;
        return this;
    }

    ServiceBuilder id(String id) {
        this.id = id;
        return this;
    }

    ServiceBuilder title(String title) {
        this.title = title;
        return this;
    }

    ServiceBuilder url(String url){
        this.url = url;
        return this;
    }

    MS build() {
        MS ms = MS.getBuilder()
                .title(title)
                .description(description)
                .url(url)
                .build();

        ReflectionTestUtils.setField(ms, "id", id);

        return ms;
    }
}
