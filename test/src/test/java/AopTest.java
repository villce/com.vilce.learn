//import com.vilce.test.Application;
//import com.vilce.test.model.MyTransaction;
//import com.vilce.test.model.po.Person;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.aop.MethodBeforeAdvice;
//import org.springframework.aop.framework.ProxyFactory;
//import org.springframework.aop.interceptor.DebugInterceptor;
//import org.springframework.aop.interceptor.SimpleTraceInterceptor;
//import org.springframework.aop.support.DefaultPointcutAdvisor;
//import org.springframework.aop.support.NameMatchMethodPointcut;
//import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.core.annotation.AnnotatedElementUtils;
//import org.springframework.core.annotation.AnnotationAttributes;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.lang.reflect.Method;
//
///**
// * @Description: Description
// * @ProjectName: com.vilce.learn
// * @Package: PACKAGE_NAME.AopTest
// * @Author: 雷才哲
// * @Date: 2020/5/11 17:55
// * @Version: 1.0
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
//public class AopTest {
//    @Test
//    public void testProxyFactory() {
//        Person person = new Person();
//        //生成代理类
//        ProxyFactory proxyFactory = new ProxyFactory(person);
//        NameMatchMethodPointcut nameMatchMethodPointcut = new NameMatchMethodPointcut();
//        nameMatchMethodPointcut.addMethodName("run1");
//        // advisor
//        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
//        advisor.setPointcut(nameMatchMethodPointcut);
//        advisor.setAdvice(new MethodBeforeAdvice() {
//            @Override
//            public void before(Method method, Object[] objects, Object o) throws Throwable {
//                System.out.println("before Advice...");
//            }
//        });
//        // 第二个advisor
//        NameMatchMethodPointcutAdvisor nameMatchMethodPointcutAdvisor = new NameMatchMethodPointcutAdvisor();
//        nameMatchMethodPointcutAdvisor.addMethodName("run1");
//        nameMatchMethodPointcutAdvisor.setAdvice(new SimpleTraceInterceptor());
//        // 第三个advisor
//        NameMatchMethodPointcutAdvisor advisor3 = new NameMatchMethodPointcutAdvisor();
//        advisor3.addMethodName("run2");
//        advisor3.setAdvice(new DebugInterceptor());
//        // 将advisor放入adviced中
//        proxyFactory.addAdvisor(advisor);
//        proxyFactory.addAdvisor(advisor3);
//        proxyFactory.addAdvisor(nameMatchMethodPointcutAdvisor);
//        // 经过代理类生成代理对象
//        Person proxy = (Person)proxyFactory.getProxy();
//        proxy.run1();
//    }
//
//    @Test
//    public void testProxyFactory2() {
//        Person person = new Person();
//        // 面向目标生成代理类
//        ProxyFactory proxyFactory = new ProxyFactory(person);
//        // 经过代理生成代理对象
//        Person proxy = (Person) proxyFactory.getProxy();
//        proxy.run1();
//    }
//}
