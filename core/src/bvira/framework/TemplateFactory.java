package bvira.framework;

import bvira.framework.Presenter;
import bvira.framework.TemplateWriter;

public interface TemplateFactory {

    TemplateWriter templateFor(Class<? extends Presenter> aClass);
}
