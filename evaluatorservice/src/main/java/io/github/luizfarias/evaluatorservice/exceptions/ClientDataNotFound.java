package io.github.luizfarias.evaluatorservice.exceptions;

public class ClientDataNotFound extends Exception{

    public ClientDataNotFound() {
        super("Dados nao encontrados para cpf informado");
    }
}
