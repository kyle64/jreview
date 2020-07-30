package algorithm;

import java.util.Stack;

/**
 * Created by ziheng on 2020/7/30.
 */
public class TwoStackQueue<E> {
    /**
     * @Description: 两个stack实现queue
     *
     * @date 2020/7/30 下午11:31
     * @param
     * @return
     */
    private Stack<E> stack1; // store stack
    private Stack<E> stack2; // temp stack, should be empty
    private E top;

    public TwoStackQueue() {
        this.stack1 = new Stack<>();
        this.stack2 = new Stack<>();
    }

    public boolean offer(E element) {
        // stack1中的元素出栈，并加入到stack2中
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        // stack1栈底压入新的元素
        stack1.push(element);
        // 将stack2中的元素压回stack1
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }

        return true;
    }

    public E poll() {
        return stack1.pop();
    }

    public E peek() {
        return stack1.peek();
    }

    public boolean offer1(E element) {
        // s1只管入栈
        stack1.push(element);
        return true;
    }

    public E poll1() {
        // s2只管出栈
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }

        E pop = stack2.pop();
        top = stack2.peek();
        return pop;
    }

    public E peek1() {
        // 需要额外维护队列头
        return top;
    }

}
