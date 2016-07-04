package demo.beans;

/**
 * Created by _dani on 05.07.2016.
 */
public class HelloWorld {

    private int count;
    private MessageGenerator generator;

    public HelloWorld(int count, MessageGenerator generator) {
        this.count = count;
        this.generator = generator;
    }

    public void initialize() {
        System.out.println("initialize");
    }

    public void destroy() {
        System.out.println("destroy");
    }

    public String getMessage() {
        return generator.generateMessage();
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public MessageGenerator getGenerator() {
        return generator;
    }

    public void setGenerator(MessageGenerator generator) {
        this.generator = generator;
    }
}
