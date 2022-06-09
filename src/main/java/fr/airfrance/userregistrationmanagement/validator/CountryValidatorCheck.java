package fr.airfrance.userregistrationmanagement.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CountryValidatorCheck implements ConstraintValidator<CountryValidator, String> {

  private String country;

  @Override
  public void initialize(CountryValidator constraintAnnotation) {
    this.country = constraintAnnotation.value();
  }

  @Override
  public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
    if (s.equalsIgnoreCase(this.country)) {
      return true;
    }
    return false;
  }
}
