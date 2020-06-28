package patterns.facade;

/**
 * Created by ziheng on 2019-09-06.
 */
public class LobbyManager {
    public void checkin() {
        ReservationSystem reservationSystem = new ReservationSystem();
        PaymentSystem paymentSystem = new PaymentSystem();
        CleaningSystem cleaningSystem = new CleaningSystem();

        reservationSystem.reserve();
        paymentSystem.pay();
        cleaningSystem.cleanUp();
    }

    public void cancel() {
        ReservationSystem reservationSystem = new ReservationSystem();
        PaymentSystem paymentSystem = new PaymentSystem();

        reservationSystem.cancel();
        paymentSystem.refund();
    }

    public void checkout() {
        PaymentSystem paymentSystem = new PaymentSystem();
        CleaningSystem cleaningSystem = new CleaningSystem();

        paymentSystem.refund();
        cleaningSystem.cleanUp();
    }
}
