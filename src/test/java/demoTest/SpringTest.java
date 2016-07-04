package demoTest;

import demo.beans.HelloWorld;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by _dani on 05.07.2016.
 */
public class SpringTest {
    private static ApplicationContext context;

    @BeforeClass
    public static void init() {
        context = new ClassPathXmlApplicationContext("bean.xml");
    }

    @Test
    public void firstTest() {
        HelloWorld obj = (HelloWorld) context.getBean("helloWorld");
        Assert.assertEquals(1, obj.getCount());
        obj.setCount(2);

        HelloWorld obj2 = (HelloWorld) context.getBean("helloWorld");
        Assert.assertEquals(2, obj2.getCount());
    }


    @Test
    public void secondTest() {
        HelloWorld obj = (HelloWorld) context.getBean("helloWorld");

        Assert.assertEquals(1, obj.getCount());

        String message = obj.getMessage();

        System.out.println(message);

        Assert.assertNotNull(message);
    }
}
