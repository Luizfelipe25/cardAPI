package io.github.luizfarias.evaluatorservice.resources;


import io.github.luizfarias.evaluatorservice.dto.*;
import io.github.luizfarias.evaluatorservice.exceptions.ClientDataNotFound;
import io.github.luizfarias.evaluatorservice.exceptions.MissMsComunicationException;
import io.github.luizfarias.evaluatorservice.exceptions.RequestCardErrorException;
import io.github.luizfarias.evaluatorservice.services.EvaluatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("avaliacoes-credito")
@RequiredArgsConstructor
public class EvaluatorResource {

    private final EvaluatorService evaluatorService;

    @GetMapping
    public String ok(){
        return "ok";
    }

    @GetMapping(params = "cpf", value = "situacao-cliente")
    public ResponseEntity consultaSituacaoCliente(@RequestParam("cpf") String cpf) {
        try {
            ClientStatus situacaoCliente = situacaoCliente = evaluatorService.getClientSituation(cpf);
            return ResponseEntity.ok(situacaoCliente);
        } catch (ClientDataNotFound e) {
           return ResponseEntity.notFound().build();
        } catch (MissMsComunicationException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity evaluationAcomplish(@RequestBody DadosAvaliacao dados){
        try {
            ClientEvaluationResponse clientEvaluationResponse = evaluatorService.acomplishEvaluation(dados.getCpf(), dados.getRenda());
            return ResponseEntity.ok(clientEvaluationResponse);
        } catch (ClientDataNotFound e) {
            return ResponseEntity.notFound().build();
        } catch (MissMsComunicationException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @PostMapping("solicitacoes-cartao")
    public ResponseEntity requestCard(@RequestBody RequestEmissionCardData data){
        try {
            ProtocoloSolicitacaoCartao protocoloSolicitacaoCartao = evaluatorService.solicitarEmissaoCartao(data);
            return ResponseEntity.ok(protocoloSolicitacaoCartao);
        }catch (RequestCardErrorException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
