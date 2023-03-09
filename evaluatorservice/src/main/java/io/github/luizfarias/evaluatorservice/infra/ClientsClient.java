package io.github.luizfarias.evaluatorservice.infra;

import io.github.luizfarias.evaluatorservice.dto.ClientData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "clientservice", path = "/clientes")
public interface ClientsClient {

    @GetMapping(params = "cpf")
    ResponseEntity<ClientData> clientData(@RequestParam String cpf);

}
