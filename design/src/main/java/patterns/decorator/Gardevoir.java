package patterns.decorator;

/**
 * Created by ziheng on 2019-09-06.
 */
public class Gardevoir extends Evolution {
    private static final String GARDEVOIR_NAME = "Gardevoir";
    private static final String GARDEVOIR_EVOLVE_ITEM = "Gardevorite";

    public Gardevoir() {
        super();
        this.name = GARDEVOIR_NAME;
    }

    public Gardevoir(Integer level, Integer gender) {
        super(GARDEVOIR_NAME, level, gender);
    }

    public Gardevoir(Pokemon pokemon) {
        super(pokemon);
        this.name = GARDEVOIR_NAME;
    }

    @Override
    void showEvolutionInfo() {
        System.out.println(this.name + " is evolved from " + getOrigin());
    }

    @Override
    void display() {
        System.out.println(this);
    }

    @Override
    public boolean isEvolutionAvailable() {
        return false;
    }

    @Override
    public Pokemon evolve() {
        return null;
    }

    /**
     * @Description: 新增的方法意味着半透明的装饰模式，因为既增强了功能，也改变了接口
     * 标准的装饰模式只增强功能，不改变接口；而适配器模式只改变接口，不改变功能
     *
     * @date 2019-09-06 13:55
     * @param
     * @return void
     */
    public void Mega() {
        System.out.println("Gardevoir Mega");
    }
}
