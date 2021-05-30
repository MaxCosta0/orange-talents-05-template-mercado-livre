package br.com.zupacademy.maxley.mercadolivre.controller.dto;

import br.com.zupacademy.maxley.mercadolivre.model.PerguntaProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CentralEmail {

    @Autowired
    private EnviaEmailFake enviaEmailFake;

    public void enviarEmail(PerguntaProduto pergunta){
        String destinatario = pergunta.getEmailDonoProduto();
        String remetente = pergunta.getEmailUsuario();
        String  texto = pergunta.getTitulo();

        enviaEmailFake.enviar(destinatario, remetente, texto);
    }
}
