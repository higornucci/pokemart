package br.com.digix.pokemart.models.exceptions;

public class TipoInvalidoException extends Exception {

    public TipoInvalidoException() {
        super("Deve ter pelo menos um tipo e no maximo dois");
    }
    
}
