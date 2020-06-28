package patterns.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ziheng on 2019-09-09.
 */
public class ChessFactory {
    private static Map<String, ChessPiece> chessPool = new HashMap<>();

    public static ChessPiece getChessPiece(String key) {
        ChessPiece chessPiece = null;

        if (chessPool.containsKey(key)) {
            chessPiece = chessPool.get(key);
            System.out.println("具体享元" + key + "被调用");
        } else {
            chessPiece = new ChessPiece(key);
            chessPool.put(key, chessPiece);
            System.out.println("具体享元" + key + "被创建！");
        }

        return chessPiece;
    }


}
