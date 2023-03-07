package io.github.luizfarias.clientservice.resource;


import io.github.luizfarias.clientservice.domain.Client;
import io.github.luizfarias.clientservice.dto.ClientSaveRequest;
import io.github.luizfarias.clientservice.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClientResource {

    private final ClientService clientService;

    @PostMapping(value = "/salvar")
    public ResponseEntity save(@RequestBody ClientSaveRequest request){
        Client client = request.toClient();
        clientService.save(client);
        URI headerLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(client.getCpf())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
    }
    @GetMapping(params = "cpf")
    public ResponseEntity clientData(@RequestParam String cpf){
        var client = clientService.getByCpf(cpf);

        if(client.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(client);

    }
}
