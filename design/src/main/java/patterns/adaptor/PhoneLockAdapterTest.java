package patterns.adaptor;

/**
 * Created by ziheng on 2019-09-04.
 */
public class PhoneLockAdapterTest {
    public static void open(PhoneLockAdapter phoneLockAdapter, Validation validation) {
        if (phoneLockAdapter.support(validation)) {
            phoneLockAdapter.unlock(validation);
        } else {
            System.out.println("unsupported validation type");
        }
    }

    public static void main(String[] args) {
        Validation passwordValidation = new PasswordValidation();
        Validation fingerprintValidation = new FingerprintValidation();
        Validation faceValidation = new FaceValidation();

        PhoneLockAdapter adapter1 = new PasswordPhoneLockAdapter("PassWord");
        PhoneLockAdapter adapter2 = new FingerprintPhoneLockAdapter("fingerprint");
        PhoneLockAdapter adapter3 = new FacePhoneLockAdapter("facepp");

        open(adapter1, passwordValidation);
        open(adapter2, passwordValidation);
//        open(adapter2, fingerprintValidation);
        open(adapter3, faceValidation);

    }
}
