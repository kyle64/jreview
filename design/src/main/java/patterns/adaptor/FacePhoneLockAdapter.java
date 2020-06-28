package patterns.adaptor;

/**
 * Created by ziheng on 2019-09-04.
 */
public class FacePhoneLockAdapter implements PhoneLockAdapter {
    private String validationType;

    public FacePhoneLockAdapter(String validationType) {
        this.validationType = validationType;
    }

    @Override
    public boolean support(Validation validation) {
        return (validation instanceof FaceValidation);
    }

    @Override
    public void unlock(Validation validation) {
        if (validation.validate(validationType)) {
            System.out.println("unlock phone by face");
        } else {
            System.out.println("unable to unlock phone by face");
        }
    }
}
