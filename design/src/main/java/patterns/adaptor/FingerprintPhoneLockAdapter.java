package patterns.adaptor;

/**
 * Created by ziheng on 2019-09-04.
 */
public class FingerprintPhoneLockAdapter implements PhoneLockAdapter {
    private String validationType;

    public FingerprintPhoneLockAdapter(String validationType) {
        this.validationType = validationType;
    }

    @Override
    public boolean support(Validation validation) {
        return (validation instanceof FingerprintValidation);
    }

    @Override
    public void unlock(Validation validation) {
        if (validation.validate(validationType)) {
            System.out.println("unlock phone by fingerprint");
        } else {
            System.out.println("unable to unlock phone by fingerprint");
        }
    }
}
