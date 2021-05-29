package br.com.zupacademy.maxley.mercadolivre.controller.dto;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UploaderFake implements Uploader {

    /*
    * @param imagens
    * @return links para imagens que ja sofreram upload
    * */
    public Set<String> envia(List<MultipartFile> imagens) {
        return imagens.stream().map(imagem ->  "http://bucket.io/"
                + imagem.getOriginalFilename()).collect(Collectors.toSet());
    }
}
