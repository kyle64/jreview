package patterns.adaptor;

/**
 * Created by ziheng on 2019-09-04.
 */
public class FaceValidation implements Validation {
    private static final String FACE = "face";

    @Override
    public boolean validate(String type) {
        if (type.equalsIgnoreCase(FACE)) {
            System.out.println("face verified");
            return true;
        }

        return false;
    }
}
