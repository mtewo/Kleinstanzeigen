package de.hsrm.mi.web.webprojekt.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GutesPasswortValidator implements ConstraintValidator<GutesPasswort, String>{

    protected String passwort;

    
    @Override
    public boolean isValid(String obj, ConstraintValidatorContext ctx){
        if(obj == null || obj.isEmpty()) return true;
        // toLowerCase > equalsIgnoreCase, wegen "equals", da 42 auch nicht als Teilstring vorkommen darf
        String lower = obj.toLowerCase();

        return !lower.contains("zweiundvierzig") && !lower.contains("42");
         
    }
    
}
