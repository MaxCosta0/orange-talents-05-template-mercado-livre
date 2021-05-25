package br.com.zupacademy.maxley.mercadolivre.config.validation;

import br.com.zupacademy.maxley.mercadolivre.config.annotation.ValorUnico;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

@Component
public class ValorUnicoValidator implements ConstraintValidator<ValorUnico, Object> {
    private String domainAtribute;
    private Class<?> klass;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(ValorUnico param) {
        domainAtribute = param.fieldName();
        klass = param.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Query query = entityManager.createQuery("select 1 from "+klass.getName()+" where " +domainAtribute+" =:value");
        query.setParameter("value", value);
        List<?> list = query.getResultList();
        Assert.state(list.size() <= 1, "Foi encontrado mais de um "+klass+" com "+domainAtribute+" = "+value);

        return list.isEmpty();
    }
}
