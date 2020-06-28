package patterns.memento;


/**
 * Created by ziheng on 2019-09-26.
 */
public class MementoTest {
    public static void main(String[] args) {
        int saveNumber;

        Originator originator = new Originator();
        CareTaker careTaker = new CareTaker();

        originator.setState("第一章进行中。。。");
        originator.setState("第一章Boss前");
        saveNumber = careTaker.add(originator.saveState()); // 存档
        firstChapterBossBattle(originator, careTaker, saveNumber);

        System.out.println("----------------------");

        originator.setState("第二章进行中。。。");
        originator.setState("第二章Boss前");
        saveNumber = careTaker.add(originator.saveState()); // 存档
        secondChapterBossBattle(originator, careTaker, saveNumber);

        System.out.println("----------------------");
        careTaker.display();
    }

    public static int firstChapterBossBattle(Originator originator, CareTaker careTaker, int saveNumber) {
        originator.setState("第一章Boss战");

        if (Math.random() < 0.5) {
            System.out.println("打败了第一章Boss");
            originator.setState("第一章通过");
            return careTaker.add(originator.saveState()); // 第一章通过存档
        } else {
            System.out.println("胜负乃兵家常事，大侠请重新来过");
            originator.loadState(careTaker.get(saveNumber)); // 读取存档
            return firstChapterBossBattle(originator, careTaker, saveNumber);
        }
    }

    public static int secondChapterBossBattle(Originator originator, CareTaker careTaker, int saveNumber) {
        originator.setState("第二章Boss战");

        if (Math.random() < 0.3) {
            System.out.println("打败了第二章Boss");
            originator.setState("第二章通过");
            return careTaker.add(originator.saveState()); // 第二章通过存档
        } else {
            System.out.println("胜负乃兵家常事，大侠请重新来过");
            originator.loadState(careTaker.get(saveNumber)); // 读取存档
            return secondChapterBossBattle(originator, careTaker, saveNumber);
        }
    }
}
