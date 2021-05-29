package br.com.zupacademy.maxley.mercadolivre.controller.dto;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class NovaImagenRequest {

    @Size(min = 3)
    @NotNull
    public List<MultipartFile> imagens = new ArrayList<>();

    @Deprecated
    public NovaImagenRequest(){}

    public NovaImagenRequest(@NotNull @Size(min = 3) List<MultipartFile> imagens) {
        this.imagens = imagens;
    }

    public List<MultipartFile> getImagens() {
        return imagens;
    }
}
