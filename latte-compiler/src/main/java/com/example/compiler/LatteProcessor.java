package com.example.compiler;

import com.example.annotations.AppRegisterGenerator;
import com.example.annotations.EntryGenerator;
import com.example.annotations.PayEntryGenerator;
import com.google.auto.service.AutoService;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.AnnotationValueVisitor;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

/**
 * Created by liWensheng on 2018/2/14.
 */

@SuppressWarnings("unused")
@AutoService(Processor.class)
public class LatteProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        return false;
    }

    /**
     * 传入已注解类
     * @return
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        final Set<String> type = new LinkedHashSet<>();
        final Set<Class<? extends Annotation>> supportAnnotations = getSupportedAnnotations();
        for (Class<? extends Annotation> annotation: supportAnnotations) {
            type.add(annotation.getCanonicalName());
        }
        return type;
    }

    private Set<Class<? extends Annotation>> getSupportedAnnotations() {
        final Set<Class<? extends Annotation>> annotation = new LinkedHashSet<>();
        annotation.add(EntryGenerator.class);
        annotation.add(PayEntryGenerator.class);
        annotation.add(AppRegisterGenerator.class);
        return annotation;
    }

    private void scan(RoundEnvironment env,
                      Class<? extends Annotation> annotation,
                      AnnotationValueVisitor visitor) {
        for (Element typeElement: env.getElementsAnnotatedWith(annotation)) {
            final List<? extends AnnotationMirror> annotationMirrors =
                    typeElement.getAnnotationMirrors();
            for (AnnotationMirror annotationMirror: annotationMirrors) {
                final Map<? extends ExecutableElement, ? extends AnnotationValue> elementValue
                        = annotationMirror.getElementValues();

                for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry:
                        elementValue.entrySet()) {
                    entry.getValue().accept(visitor, null);
                }
            }

        }

    }

    private void generateEntryCode(RoundEnvironment env) {

    }
}
