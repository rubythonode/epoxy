package com.airbnb.epoxy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Use this annotation on any class in your package to specify default behavior for the Epoxy
 * annotation processor for that package. You can only have one instance of this annotation per
 * package.
 * <p>
 * If an instance of this annotation is not found in a package then the default values are used.
 */
@Target(ElementType.PACKAGE)
@Retention(RetentionPolicy.CLASS)
public @interface PackageEpoxyConfig {
  boolean REQUIRE_HASHCODE_DEFAULT = false;
  boolean REQUIRE_ABSTRACT_MODELS = false;
  /**
   * If true, all fields marked with {@link com.airbnb.epoxy.EpoxyAttribute} must have a type that
   * implements hashCode (besides the default Object implementation), or the attribute must set
   * hash=false.
   * <p>
   * Setting this to true is useful for ensuring that all model attributes correctly implement
   * hashCode, or use hash=false (eg for click listeners). It is a common mistake to miss these,
   * which leads to invalid model state and incorrect diffing.
   * <p>
   * The check is done at compile time and compilation will fail if a hashCode validation fails.
   * <p>
   * Since it is done at compile time this can only check the direct type of the field. Interfaces
   * or classes will pass the check if they either have an abstract hashCode method (since it is
   * assumed that the object at runtime will implement it) or their class hierarchy must have an
   * implementation of hashCode besides the default Object implementation.
   * <p>
   * If an attribute is an Iterable or Array then the type of object in that collection must
   * implement hashCode.
   */
  boolean requireHashCode() default REQUIRE_HASHCODE_DEFAULT;
  /**
   * If true, all classes that contains {@link com.airbnb.epoxy.EpoxyAttribute} or {@link
   * com.airbnb.epoxy.EpoxyModelClass} annotations in your project must be abstract. Otherwise
   * compilation will fail.
   * <p>
   * Forcing models to be abstract can prevent the mistake of using the original model class instead
   * of the generated class.
   */
  boolean requireAbstractModels() default REQUIRE_ABSTRACT_MODELS;
}
