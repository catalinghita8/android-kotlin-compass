package com.inspiringteam.reactivecompass.di.scopes;


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Replacement scope for @Singleton to improve readability
 */
@Documented
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface AppScoped {
}