package com.p632.catalog.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.p632.catalog.service.MSDTOAssert.assertThatServiceDTO;
import static com.p632.catalog.service.ServiceAssert.assertThatService;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;


/**
 * Created by naveenjetty on 2/11/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class MongoDBServiceTest {
    private static final String DESCRIPTION = "description";
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String URL = "http://naveenjetty.com";

    @Mock
    private MSRepository repository;

    private MongoDBService service;

    @Before
    public void setUp() {
        this.service = new MongoDBService(repository);
    }

    @Test
    public void create_ShouldSaveNewServiceEntry() {
        MSDTO newTodo = new MSDTOBuilder()
                .title(TITLE)
                .description(DESCRIPTION)
                .url(URL)
                .build();

        when(repository.save(isA(MS.class))).thenAnswer(invocation -> (MS) invocation.getArguments()[0]);

        service.create(newTodo);

        ArgumentCaptor<MS> savedTodoArgument = ArgumentCaptor.forClass(MS.class);

        verify(repository, times(1)).save(savedTodoArgument.capture());
        verifyNoMoreInteractions(repository);

        MS savedService = savedTodoArgument.getValue();
        assertThatService(savedService)
                .hasTitle(TITLE)
                .hasDescription(DESCRIPTION)
                .hasUrl(URL);
    }

    @Test
    public void create_ShouldReturnTheInformationOfCreatedServiceEntry() {
        MSDTO newTodo = new MSDTOBuilder()
                .title(TITLE)
                .description(DESCRIPTION)
                .url(URL)
                .build();

        when(repository.save(isA(MS.class))).thenAnswer(invocation -> {
            MS persisted = (MS) invocation.getArguments()[0];
            ReflectionTestUtils.setField(persisted, "id", ID);
            return persisted;
        });

        MSDTO returned = service.create(newTodo);

        assertThatServiceDTO(returned)
                .hasId(ID)
                .hasTitle(TITLE)
                .hasDescription(DESCRIPTION)
                .hasUrl(URL);
    }

}