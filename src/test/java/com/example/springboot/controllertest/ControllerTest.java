package com.example.springboot.controllertest;

import com.example.springboot.controller.UserController;
import com.example.springboot.entities.User;
import com.example.springboot.exception.ControllerExceptionHandler;
import com.example.springboot.service.ServiceClass;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceClass serviceClass;

    private ObjectMapper objectMapper = new ObjectMapper();


    // #1 Verifying HTTP Request Matching
    @Test
    public void sendRequestAndTheExpectedStatusIsOK() throws Exception {
        mockMvc.perform(get("/api")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk());
    }

    // #2 Verifying Input Serialization
    @Test
    public void senValidInputAndTheExpectedStatusIsOK() throws Exception {
        User user = new User("joska", "pista", "joska@pista.hu");

        mockMvc.perform(post("/api")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(user))) // The request body is generated using the ObjectMapper provided by Spring Boot,
                .andExpect(status().isOk());                     // serializing a UserResource object to a JSON string.
    }

    // #3 Verifying Input Validation
    @Test
    public void senInValidInputAndTheExpectedStatusBadRequest() throws Exception {
        User user = new User();

        mockMvc.perform(post("/api")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest());
    }

    // #4 Verifying Business Logic Calls
    @Test
    public void sendValidInputThenMapsToBusinessModel() throws Exception {
        User user = new User("joska", "pista", "joska@pista.hu");

        mockMvc.perform(post("/api")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class); // we use an ArgumentCaptor to capture the User object that was passed to the create() method
        verify(serviceClass, times(1)).create(userCaptor.capture()); // The verify call checks that create() has been called exactly once.
        assertThat(userCaptor.getValue().getFirstName()).isEqualTo("joska"); // and assert that it contains the expected values.
        assertThat(userCaptor.getValue().getLastName()).isEqualTo("pista");
        assertThat(userCaptor.getValue().getEmail()).isEqualTo("joska@pista.hu");
    }

    // #5 Verifying Output Serialization
    @Test
    public void sendValidInputThenReturnsAnUser() throws Exception {

        User actualUser = new User("joska", "pista", "joska@pista.hu");
        User expectedResponseBody = actualUser;

        when(serviceClass.delete(1)).thenReturn(actualUser);

        MvcResult mvcResult = mockMvc.perform( delete("/api/{id}", 1).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        // comparing the JSON as string
        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        assertThat(objectMapper.writeValueAsString(expectedResponseBody)).isEqualToIgnoringWhitespace(actualResponseBody);

        // comparing the JSON mapped to Objects
        User givenResponseBody = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), User.class);
        assertThat(givenResponseBody.getFirstName()).isEqualTo(actualUser.getFirstName());
        assertThat(givenResponseBody.getLastName()).isEqualTo(actualUser.getLastName());
        assertThat(givenResponseBody.getEmail()).isEqualTo(actualUser.getEmail());


        // using this shorter form with custom responsebody matcher
//        mockMvc.perform(delete("/api/{id}", 1).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//                .andExpect(status().isOk())
//                .andExpect(containsObjectAsJson(expectedResponseBody, User.class));
    }

        // custom responsebody matcher
//    private static <T> ResultMatcher containsObjectAsJson(Object expectedObject, Class<T> targetClass) {
//        return mvcResult -> {
//            ObjectMapper objectMapper = new ObjectMapper();
//            String json = mvcResult.getResponse().getContentAsString();
//            T actualObject = objectMapper.readValue(json, targetClass);
//            assertThat(expectedObject).isEqualToComparingFieldByField(actualObject);
//        };
//    }


    // #6 Verifying Exception Handling
    @Test
    public void sendNullValueThenReturnsBadRequestAndErrorResult() throws Exception {

        User invalidUser = new User("joska", "pista", null);


        MvcResult mvcResult = mockMvc.perform(
                post("/api")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(objectMapper.writeValueAsString(invalidUser)))
                .andExpect(status().isBadRequest())
                .andReturn();



        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        ControllerExceptionHandler.ErrorResult expectedErrorResult = new ControllerExceptionHandler.ErrorResult("email", "Email field must not be empty field!!");
        String expectedResponseBody = objectMapper.writeValueAsString(expectedErrorResult);

        assertThat(expectedResponseBody).isEqualToIgnoringWhitespace(actualResponseBody);

    }

}