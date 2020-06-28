package patterns.flyweight;

/**
 * Created by ziheng on 2019-09-09.
 */
public class ChessPiece implements ChessFlyweight {
    private String color;

    public ChessPiece(String color) {
        this.color = color;
    }

    @Override
    public void operate(Object param) {
    }
}
