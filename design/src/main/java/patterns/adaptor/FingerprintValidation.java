package patterns.adaptor;

/**
 * Created by ziheng on 2019-09-04.
 */
public class FingerprintValidation implements Validation {
    private static final String FINGER_PRINT = "fingerprint";

    @Override
    public boolean validate(String type) {
        if (type.equalsIgnoreCase(FINGER_PRINT)) {
            System.out.println("fingerprint verified");
            return true;
        }

        return false;
    }
}
