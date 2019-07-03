package me.intelligence.intelapi.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandInfo {
    String name();

    String[] aliases() default {};

    String description() default "";

    String usage() default "";
}
