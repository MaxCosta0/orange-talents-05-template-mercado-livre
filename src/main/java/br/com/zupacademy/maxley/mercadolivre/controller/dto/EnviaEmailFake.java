package br.com.zupacademy.maxley.mercadolivre.controller.dto;

import org.springframework.stereotype.Component;

@Component
public class EnviaEmailFake {

    public void enviar(String destinatario, String remetente, String texto){
        System.out.println("De: " + remetente + "\n" +
                "Para: " + destinatario + "\n" +
                "Texto: " + texto);
    }
}
