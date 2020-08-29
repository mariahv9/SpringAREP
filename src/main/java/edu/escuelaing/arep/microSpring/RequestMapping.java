package edu.escuelaing.arep.microSpring;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Class implements java's interface Request Mapping
 * @author Maria Fernanda Hernandez Vargas
 */
@Retention (RetentionPolicy.RUNTIME)
@Target (ElementType.METHOD)
public @interface RequestMapping {
        String value();
}
