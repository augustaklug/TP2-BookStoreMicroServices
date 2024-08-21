package com.example.BookStorePedidosMicrosservice.service;

import com.example.BookStorePedidosMicrosservice.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String CLIENTE_SERVICE_URL = "http://localhost:8081/customers/";

    public CustomerDTO getCustomerById(Long id) {
        return restTemplate.getForObject(CLIENTE_SERVICE_URL + id, CustomerDTO.class);
    }
}
