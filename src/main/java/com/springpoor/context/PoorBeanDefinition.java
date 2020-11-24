package com.springpoor.context;

import com.springpoor.annotations.PoorComponent;
import com.springpoor.annotations.ScopeType;

public class PoorBeanDefinition {
    private Class clazz;
    private ScopeType scopeType;
    private boolean lazy;
    private String path;

    public PoorBeanDefinition(Class<?> clazz) {
        this.clazz = clazz;
        this.scopeType = clazz.getAnnotation(PoorComponent.class).scope();
        this.lazy = clazz.getAnnotation(PoorComponent.class).lazy();
        this.path = clazz.getCanonicalName();
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return new String("class: " + clazz.getCanonicalName() + "\nscope: " + scopeType + "\nlazy: " + lazy + "\n");
    }
}
