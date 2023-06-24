package by.ahmed.springapp.validator;

public interface    Validator<T> {
    ValidationResult isValid(T object);
}