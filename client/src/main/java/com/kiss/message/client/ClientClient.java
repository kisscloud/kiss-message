package com.kiss.message.client;

import com.kiss.message.input.CreateClientInput;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import output.ResultOutput;

@RequestMapping
public interface ClientClient {

    @PostMapping("/client")
    ResultOutput createClient(CreateClientInput createClientInput);

    @GetMapping("/clients")
    ResultOutput getClients(Integer page,Integer size);
}
