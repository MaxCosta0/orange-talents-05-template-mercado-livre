package br.com.zupacademy.maxley.mercadolivre.controller.dto;

import br.com.zupacademy.maxley.mercadolivre.model.Compra;
import br.com.zupacademy.maxley.mercadolivre.model.PerguntaProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CentralEmail {

    @Autowired
    private EnviaEmailFake enviaEmailFake;

    public void enviarEmailPergunta(PerguntaProduto pergunta){
        String destinatario = pergunta.getEmailDonoProduto();
        String remetente = pergunta.getEmailUsuario();
        String texto = pergunta.getTitulo();

        enviaEmailFake.enviar(destinatario, remetente, texto);
    }

    public void enviarEmailCompra(Compra compra){
        String destinatario = compra.getProduto().getEmailDono();
        String remetente = compra.getComprador().getUsername();
        String texto = "O usuario "+compra.getComprador().getUsername()
                +" comprou "+compra.getQuantidade()
                +" unidade(s) do seu produto "+compra.getProduto().getNome();
        enviaEmailFake.enviar(destinatario, remetente, texto);
    }
}
