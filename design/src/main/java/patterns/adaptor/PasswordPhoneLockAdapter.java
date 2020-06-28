package patterns.adaptor;

/**
 * Created by ziheng on 2019-09-04.
 */
public class PasswordPhoneLockAdapter implements PhoneLockAdapter {
    private String validationType;

    public PasswordPhoneLockAdapter(String validationType) {
        this.validationType = validationType;
    }

    @Override
    public boolean support(Validation validation) {
        return (validation instanceof PasswordValidation);
    }

    @Override
    public void unlock(Validation validation) {
        if (validation.validate(validationType)) {
            System.out.println("unlock phone by password");
        } else {
            System.out.println("unable to unlock phone by password");
        }
    }
}
