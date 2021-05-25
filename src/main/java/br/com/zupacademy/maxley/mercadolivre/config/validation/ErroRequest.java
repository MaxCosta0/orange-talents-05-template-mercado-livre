package br.com.zupacademy.maxley.mercadolivre.config.validation;

public class ErroRequest {
    private String campo;
    private String mensagem;

    public ErroRequest(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    /*
    * Utilizado para serializar as informalçoes da classe
    * */
    public String getCampo() {
        return campo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
