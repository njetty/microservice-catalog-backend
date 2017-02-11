package com.p632.catalog.service;

import org.assertj.core.api.AbstractAssert;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by naveenjetty on 2/11/17.
 */
class MSDTOAssert extends AbstractAssert<MSDTOAssert, MSDTO> {

    private MSDTOAssert(MSDTO actual) {
        super(actual, MSDTOAssert.class);
    }

    static MSDTOAssert assertThatServiceDTO(MSDTO actual) {
        return new MSDTOAssert(actual);
    }

    public MSDTOAssert hasDescription(String expectedDescription) {
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

    public MSDTOAssert hasId(String expectedId) {
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

    public MSDTOAssert hasNoDescription() {
        isNotNull();

        String actualDescription = actual.getDescription();
        assertThat(actualDescription)
                .overridingErrorMessage("expected description to be <null> but was <%s>", actualDescription)
                .isNull();

        return this;
    }

    public MSDTOAssert hasNoId() {
        isNotNull();

        String actualId = actual.getId();
        assertThat(actualId)
                .overridingErrorMessage("Expected id to be <null> but was <%s>", actualId)
                .isNull();

        return this;
    }

    public MSDTOAssert hasTitle(String expectedTitle) {
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

    public MSDTOAssert hasUrl(String expectedUrl){
        isNotNull();

        String actualUrl = actual.getUrl();
        assertThat(actualUrl)
                .overridingErrorMessage("Expected url to be <%s> but was <%s>",
                        expectedUrl,
                        actualUrl
                )
                .isEqualTo(expectedUrl);

        return this;
    }
}
