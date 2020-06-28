package patterns.flyweight;

/**
 * Created by ziheng on 2019-09-09.
 */
public class ChessTest {
    public static void main(String[] args) {
        UnsharedChessCount unsharedChessCount = new UnsharedChessCount();

        ChessPiece chessPiece1 = ChessFactory.getChessPiece("black");
        unsharedChessCount.operate(1);

        ChessPiece chessPiece2 = ChessFactory.getChessPiece("white");

        ChessPiece chessPiece3 = ChessFactory.getChessPiece("black");
        ChessPiece chessPiece4 = ChessFactory.getChessPiece("white");
        unsharedChessCount.operate(4);

        System.out.println(chessPiece1 == chessPiece3);
        System.out.println(chessPiece2 == chessPiece4);
    }
}
