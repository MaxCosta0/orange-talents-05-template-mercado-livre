package br.com.zupacademy.maxley.mercadolivre.config.validation;

import br.com.zupacademy.maxley.mercadolivre.controller.dto.NovoProdutoRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProibeCaracteristicaComNomeIgualValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return NovoProdutoRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        if(errors.hasErrors()){
            return;
        }

        NovoProdutoRequest request = (NovoProdutoRequest) obj;
        if(request.temCaracteristicasIguais()){
            errors.rejectValue("caracteristicas", null, "Nao é permitido caracteristicas com nomes iguais");
        }
    }
}
