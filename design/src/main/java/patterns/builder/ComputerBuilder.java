package patterns.builder;

/**
 * Created by ziheng on 2019-09-03.
 */
public abstract class ComputerBuilder {
    abstract ComputerBuilder buildScreen(Integer size);
    abstract ComputerBuilder buildGraphics(String type);
    abstract ComputerBuilder buildMemory(String memory);
    abstract ComputerBuilder buildProcessor(String processor);
    abstract ComputerBuilder buildStorage(String storage);

    abstract Computer build();
}
