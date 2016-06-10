package cdio.client.validate;
public interface IValidator {

    String validate(String value, Object... parameters);
}