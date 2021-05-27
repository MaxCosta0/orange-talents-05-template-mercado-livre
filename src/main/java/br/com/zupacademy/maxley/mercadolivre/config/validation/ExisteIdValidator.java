package br.com.zupacademy.maxley.mercadolivre.config.validation;

import br.com.zupacademy.maxley.mercadolivre.config.annotation.ExisteId;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExisteIdValidator implements ConstraintValidator<ExisteId, Object> {

    private String domainAttribute;
    private Class<?> klass;

    @PersistenceContext
    private EntityManager entityManager;

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

        Query query = entityManager.createQuery("select 1 from " +klass.getName()+ " where "+domainAttribute+" =:value");
        query.setParameter("value", value);
        List<?> list = query.getResultList();
        Assert.state(list.size() <= 1, "Problema: Tem mais de um "+klass+" com o mesmo"+domainAttribute);

        return !list.isEmpty();
    }
}
