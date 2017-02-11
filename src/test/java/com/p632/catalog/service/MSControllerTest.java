package com.p632.catalog.service;

import com.p632.catalog.error.RestErrorHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.ExceptionHandlerMethodResolver;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod;

import java.lang.reflect.Method;
import java.nio.charset.Charset;

import static com.p632.catalog.service.MSDTOAssert.assertThatServiceDTO;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Created by naveenjetty on 2/11/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class MSControllerTest {

    private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8")
    );

    private static final String DESCRIPTION = "description";
    private static final String ID = "id";
    private static final String TITLE = "title";

    private static final int MAX_LENGTH_DESCRIPTION = 500;
    private static final int MAX_LENGTH_TITLE = 100;

    @Mock
    private MSService service;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new MSController(service))
                .setHandlerExceptionResolvers(withExceptionControllerAdvice())
                .build();
    }

    /**
     * For some reason this does not work. The correct error handler method is invoked but when it tries
     * to return the validation errors as json, the following error appears to log:
     *
     * Failed to invoke @ExceptionHandler method:
     * public com.javaadvent.bootrest.error.ValidationErrorDTO com.javaadvent.bootrest.error.RestErrorHandler.processValidationError(org.springframework.web.bind.MethodArgumentNotValidException)
     * org.springframework.web.HttpMediaTypeNotAcceptableException: Could not find acceptable representation
     *
     * I have to figure out how to fix this before I can write the unit tests that ensure that validation is working.
     */
    private ExceptionHandlerExceptionResolver withExceptionControllerAdvice() {
        final ExceptionHandlerExceptionResolver exceptionResolver = new ExceptionHandlerExceptionResolver() {
            @Override
            protected ServletInvocableHandlerMethod getExceptionHandlerMethod(final HandlerMethod handlerMethod,
                                                                              final Exception exception) {
                Method method = new ExceptionHandlerMethodResolver(RestErrorHandler.class).resolveMethod(exception);
                if (method != null) {
                    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
                    messageSource.setBasename("messages");
                    return new ServletInvocableHandlerMethod(new RestErrorHandler(messageSource), method);
                }
                return super.getExceptionHandlerMethod(handlerMethod, exception);
            }
        };
        exceptionResolver.afterPropertiesSet();
        return exceptionResolver;
    }

    @Test
    public void create_ServiceEntryWithMaxLengthTitleAndDescription_ShouldReturnResponseStatusCreated() throws Exception {
        String maxLengthTitle = StringTestUtil.createStringWithLength(MAX_LENGTH_TITLE);
        String maxLengthDescription = StringTestUtil.createStringWithLength(MAX_LENGTH_DESCRIPTION);

        MSDTO newTodoEntry = new MSDTOBuilder()
                .title(maxLengthTitle)
                .description(maxLengthDescription)
                .url("http://sample.url")
                .build();

        when(service.create(isA(MSDTO.class))).then(invocationOnMock -> {
            MSDTO saved = (MSDTO) invocationOnMock.getArguments()[0];
            saved.setId(ID);
            return saved;
        });

        mockMvc.perform(post("/api/catalog")
                .contentType(APPLICATION_JSON_UTF8)
                .content(WebTestUtil.convertObjectToJsonBytes(newTodoEntry))
        )
                .andExpect(status().isCreated());
    }

    @Test
    public void create_TodoEntryWithMaxLengthTitleAndDescription_ShouldReturnTheInformationOfCreatedTodoEntryAsJson() throws Exception {
        String maxLengthTitle = StringTestUtil.createStringWithLength(MAX_LENGTH_TITLE);
        String maxLengthDescription = StringTestUtil.createStringWithLength(MAX_LENGTH_DESCRIPTION);
        String sampleUrl = "https://sample.url";

        MSDTO newTodoEntry = new MSDTOBuilder()
                .title(maxLengthTitle)
                .description(maxLengthDescription)
                .url(sampleUrl)
                .build();

        when(service.create(isA(MSDTO.class))).then(invocationOnMock -> {
            MSDTO saved = (MSDTO) invocationOnMock.getArguments()[0];
            saved.setId(ID);
            return saved;
        });

        mockMvc.perform(post("/api/catalog")
                .contentType(APPLICATION_JSON_UTF8)
                .content(WebTestUtil.convertObjectToJsonBytes(newTodoEntry))
        )
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(ID)))
                .andExpect(jsonPath("$.title", is(maxLengthTitle)))
                .andExpect(jsonPath("$.description", is(maxLengthDescription)))
                .andExpect(jsonPath("$.url", is(sampleUrl)));
    }


    @Test
    public void findAll_ShouldReturnResponseStatusOk() throws Exception {
        mockMvc.perform(get("/api/catalog"))
                .andExpect(status().isOk());
    }


}