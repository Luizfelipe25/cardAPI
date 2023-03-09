package io.github.luizfarias.evaluatorservice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ClientEvaluationResponse {

    private List<ApprovedCards> cartoes;
}
