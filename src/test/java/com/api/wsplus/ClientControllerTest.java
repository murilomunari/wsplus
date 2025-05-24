package com.api.wsplus;

import com.api.wsplus.controller.ClientController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private ClientController clientController;

    @Autowired
    private ObjectMapper objectMapper;


}
