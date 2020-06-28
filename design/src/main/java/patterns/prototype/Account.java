package patterns.prototype;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by ziheng on 2019-09-02.
 */
@Getter
@Setter
@AllArgsConstructor
public class Account implements Serializable {
    private String username;
    private Integer age;
    private transient Integer level;
}
