package br.com.zupacademy.maxley.mercadolivre.config.validation;

import br.com.zupacademy.maxley.mercadolivre.config.annotation.ExisteId;
import br.com.zupacademy.maxley.mercadolivre.model.Categoria;
import br.com.zupacademy.maxley.mercadolivre.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class ExisteIdValidator implements ConstraintValidator<ExisteId, Object> {

    private String domainAttribute;
    private Class<?> klass;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public void initialize(ExisteId params) {
        domainAttribute = params.fieldName();
        klass = params.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Long id = (Long) value;

        if(id == null){
            return true;
        }

        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return (categoria.isPresent());
    }
}
