package patterns.builder;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by ziheng on 2019-09-03.
 */
@Getter
@Setter
public class Computer {
    private Integer screen = 13;
    private String graphics = "600";
    private String memory = "4G";
    private String processor = "Core i5";
    private String storage = "256G";

    @Override
    public String toString() {
        return "Computer{" +
                "screen=" + screen + "-inch" +
                ", graphics='" + graphics + '\'' +
                ", memory='" + memory + '\'' +
                ", processor='" + processor + '\'' +
                ", storage='" + storage + '\'' +
                '}';
    }
}
