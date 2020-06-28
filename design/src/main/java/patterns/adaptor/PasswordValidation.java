package patterns.adaptor;

/**
 * Created by ziheng on 2019-09-04.
 */
public class PasswordValidation implements Validation {
    private static final String PASSWORD = "password";

    @Override
    public boolean validate(String type) {
        if (type.equalsIgnoreCase(PASSWORD)) {
            System.out.println("password verified");
            return true;
        }

        return false;
    }
}
