package com.springpoor.context;

import com.springpoor.annotations.PoorComponent;
import com.springpoor.annotations.ScopeType;

public class PoorBeanDefinition {
    private Class clazz;
    private ScopeType scopeType;
    private boolean lazy;

    public PoorBeanDefinition(Class<?> clazz) {
        this.clazz = clazz;
        scopeType = clazz.getAnnotation(PoorComponent.class).scope();
        lazy = clazz.getAnnotation(PoorComponent.class).lazy();
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public ScopeType getScopeType() {
        return scopeType;
    }

    public void setScopeType(ScopeType scopeType) {
        this.scopeType = scopeType;
    }

    public boolean isLazy() {
        return lazy;
    }

    public void setLazy(boolean lazy) {
        this.lazy = lazy;
    }

    @Override
    public String toString() {
        return new String("class: " + clazz.getCanonicalName() + "\nscope: " + scopeType + "\nlazy: " + lazy + "\n");
    }
}
