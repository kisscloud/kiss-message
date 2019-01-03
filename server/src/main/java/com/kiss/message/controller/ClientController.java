package com.kiss.message.controller;

import com.kiss.message.client.ClientClient;
import com.kiss.message.input.CreateClientInput;
import com.kiss.message.service.ClientService;
import com.kiss.message.validator.ClientValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import output.ResultOutput;

@RestController
@Api(tags = "Client", description = "客户端相关接口")
public class ClientController implements ClientClient {

    @Autowired
    private ClientValidator clientValidator;

    @Autowired
    private ClientService clientService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {

        binder.setValidator(clientValidator);
    }

    @Override
    @ApiOperation(value = "创建客户端")
    public ResultOutput createClient(@Validated @RequestBody CreateClientInput createClientInput) {

        return clientService.createClient(createClientInput);
    }

    @Override
    @ApiOperation(value = "获取客户端")
    public ResultOutput getClients(@RequestParam("page") Integer page,@RequestParam("size") Integer size) {

        return clientService.getClients(page,size);
    }
}
