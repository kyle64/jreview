package patterns.facade;

/**
 * Created by ziheng on 2019-09-06.
 */
public class FacadeLobbyManagerTest {
    public static void main(String[] args) {
        LobbyManager lobbyManager = new LobbyManager();

        lobbyManager.checkin();
        System.out.println("--------------------------");

        lobbyManager.cancel();
        System.out.println("--------------------------");

        lobbyManager.checkout();
        System.out.println("--------------------------");

    }
}
