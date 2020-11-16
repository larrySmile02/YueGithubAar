package com.xgmn.annotation_service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface MicroService {
    //默认方法，使用时不必@MicroService(value="xxx"),而是直接@MicroService("xxx")
    String value();
}
