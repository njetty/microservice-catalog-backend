package com.p632.catalog.service;

import org.assertj.core.api.AbstractAssert;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by naveenjetty on 2/11/17.
 */

class ServiceAssert extends AbstractAssert<ServiceAssert, MS> {

    private ServiceAssert(MS actual) {
        super(actual, ServiceAssert.class);
    }

    static ServiceAssert assertThatService(MS actual) {
        return new ServiceAssert(actual);
    }

    ServiceAssert hasDescription(String expectedDescription) {
        isNotNull();

        String actualDescription = actual.getDescription();
        assertThat(actualDescription)
                .overridingErrorMessage("Expected description to be <%s> but was <%s>",
                        expectedDescription,
                        actualDescription
                )
                .isEqualTo(expectedDescription);

        return this;
    }

    ServiceAssert hasId(String expectedId) {
        isNotNull();

        String actualId = actual.getId();
        assertThat(actualId)
                .overridingErrorMessage("Expected id to be <%s> but was <%s>",
                        expectedId,
                        actualId
                )
                .isEqualTo(expectedId);

        return this;
    }

    ServiceAssert hasNoDescription() {
        isNotNull();

        String actualDescription = actual.getDescription();
        assertThat(actualDescription)
                .overridingErrorMessage("Expected description to be <null> but was <%s>", actualDescription)
                .isNull();

        return this;
    }

    ServiceAssert hasNoId() {
        isNotNull();

        String actualId = actual.getId();
        assertThat(actualId)
                .overridingErrorMessage("Expected id to be <null> but was <%s>", actualId)
                .isNull();

        return this;
    }

    ServiceAssert hasTitle(String expectedTitle) {
        isNotNull();

        String actualTitle = actual.getTitle();
        assertThat(actualTitle)
                .overridingErrorMessage("Expected title to be <%s> but was <%s>",
                        expectedTitle,
                        actualTitle
                )
                .isEqualTo(expectedTitle);

        return this;
    }

    ServiceAssert hasUrl(String expectedUrl) {
        isNotNull();

        String actualUrl = actual.getUrl();
        assertThat(actualUrl)
                .overridingErrorMessage("Expected url to be <%s> but was <%s",
                        expectedUrl,
                        actualUrl
                )
                .isEqualTo(expectedUrl);
        return this;
    }
}
