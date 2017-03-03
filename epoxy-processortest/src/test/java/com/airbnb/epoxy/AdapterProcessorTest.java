package com.airbnb.epoxy;

import com.google.testing.compile.JavaFileObjects;

import org.junit.Test;

import javax.tools.JavaFileObject;

import static com.google.common.truth.Truth.assert_;
import static com.google.testing.compile.JavaSourceSubjectFactory.javaSource;
import static com.google.testing.compile.JavaSourcesSubjectFactory.javaSources;
import static java.util.Arrays.asList;

public class AdapterProcessorTest {

  @Test
  public void adapterWithAutoModel() {
    JavaFileObject model = JavaFileObjects
        .forResource("BasicModelWithAttribute.java");

    JavaFileObject adapter = JavaFileObjects
        .forResource("AdapterWithAutoModel.java");

    JavaFileObject generatedHelper = JavaFileObjects
        .forResource("AdapterWithAutoModel_EpoxyHelper.java");

    assert_().about(javaSources())
        .that(asList(model, adapter))
        .processedWith(new EpoxyProcessor())
        .compilesWithoutError()
        .and()
        .generatesSources(generatedHelper);
  }

  @Test
  public void autoModelNotInAutoAdapterFails() {
    JavaFileObject badClass = JavaFileObjects
        .forResource("AutoModelNotInAutoAdapter.java");

    assert_().about(javaSource())
        .that(badClass)
        .processedWith(new EpoxyProcessor())
        .failsToCompile();
  }

  @Test
  public void autoModelAnnotationNotOnModelFails() {
    JavaFileObject badClass = JavaFileObjects
        .forResource("AutoModelNotOnModelField.java");

    assert_().about(javaSource())
        .that(badClass)
        .processedWith(new EpoxyProcessor())
        .failsToCompile();
  }
}