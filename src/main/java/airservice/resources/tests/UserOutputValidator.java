/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package airservice.resources.tests;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.cxf.common.util.StringUtils;

/**
 *
 * @author Tomas "sarzwest" Jiricek
 */
public class UserOutputValidator implements ConstraintValidator<OutputUserValidation, UserEntity>{

    private OutputUserValidation validation;

    @Override
    public void initialize(OutputUserValidation constraintAnnotation) {
        validation = constraintAnnotation;
    }

    @Override
    public boolean isValid(UserEntity value, ConstraintValidatorContext context) {
        if(!StringUtils.isEmpty(value.getNullableOutput())){
            return false;
        }
        return true;
    }
    
}
