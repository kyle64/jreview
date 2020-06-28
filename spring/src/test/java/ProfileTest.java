import bean.PersonBean;
import bean.ProfileBean;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Created by ziheng on 2019-08-01.
 */
public class ProfileTest {

    @Test
    public void testName() {
//        Resource resource = new ClassPathResource("profile-bean.xml");
//        BeanFactory bf = new DefaultListableBeanFactory();
//        BeanDefinitionReader bdr = new XmlBeanDefinitionReader((BeanDefinitionRegistry) bf);
//        bdr.loadBeanDefinitions(resource);

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("profile-bean.xml");
//        ProfileBean profileBean = (ProfileBean) applicationContext.getBean("profileBean");
        PersonBean person = (PersonBean) applicationContext.getBean("person");
        Assert.assertEquals(23L, person.getAge().longValue());
        Assert.assertNotNull(person.readName());
    }
}
