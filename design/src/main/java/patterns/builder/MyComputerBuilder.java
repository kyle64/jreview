package patterns.builder;

/**
 * Created by ziheng on 2019-09-03.
 */
public class MyComputerBuilder extends ComputerBuilder {
    private Computer computer;

    public MyComputerBuilder() {
        computer = new Computer();
    }

    public MyComputerBuilder(Computer computer) {
        this.computer = computer;
    }

    @Override
    ComputerBuilder buildScreen(Integer size) {
        computer.setScreen(size);
        return this;
    }

    @Override
    ComputerBuilder buildGraphics(String type) {
        computer.setGraphics(type);
        return this;
    }

    @Override
    ComputerBuilder buildMemory(String memory) {
        computer.setMemory(memory);
        return this;
    }

    @Override
    ComputerBuilder buildProcessor(String processor) {
        computer.setProcessor(processor);
        return this;
    }

    @Override
    ComputerBuilder buildStorage(String storage) {
        computer.setStorage(storage);
        return this;
    }

    @Override
    Computer build() {
        return computer;
    }
}
