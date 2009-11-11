package bvira.web;

import bvira.model.Presenter;

public interface TemplateFactory {

    TemplateWriter templateFor(Class<? extends Presenter> aClass);
}
