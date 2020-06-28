package concurrent.lock;

public class Buffer {
    private Object lock;

    public Buffer() {
        lock = this;  //buffer自身
    }

    public void write() {
        synchronized (lock) {
            long startTime = System.currentTimeMillis();
            System.out.println("往这个buffer写入数据...");
            //死循环模拟要处理很长时间
            for(;;) {
                if (System.currentTimeMillis() - startTime > Integer.MAX_VALUE) {
                    break;
                }
            }

            System.out.println("终于写完了");
        }
    }

    public void read() {
        synchronized (lock) {
            System.out.println("从这个buff读数据");
        }
    }

    public static void main(String[] args) {
        Buffer buffer = new Buffer();

        final Writer writer = new Writer(buffer);
        final Reader reader = new Reader(buffer);

        //启动线程
        writer.start();
        reader.start();

        //尝试启动一个线程去中断reader线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                long start = System.currentTimeMillis();

                for(;;) {
                    //等待五秒钟去中断
                    if (System.currentTimeMillis() - start > 5000) {
                        System.out.println("不等了，尝试中断");
                        reader.interrupt();
                        break;
                    }
                }
            }
        }).start();
    }
}