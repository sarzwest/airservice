/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package airservice.resources.tests;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.ws.rs.ext.Provider;
import org.apache.cxf.common.util.StringUtils;

/**
 *
 * @author Tomas "sarzwest" Jiricek
 */
//@Provider
public class UserInputValidator implements ConstraintValidator<InputUserValidation, UserEntity>{

    private InputUserValidation validation;
    
    @Override
    public void initialize(InputUserValidation constraintAnnotation) {
        validation = constraintAnnotation;
    }

    @Override
    public boolean isValid(UserEntity value, ConstraintValidatorContext context) {
        if(!StringUtils.isEmpty(value.getNullableInput())){
            return false;
        }
        return true;
    }
    
}
