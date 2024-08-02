package com.ioc.coupling;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IOCExample {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationIoCLooseCouplingExample.xml");

        UserManager userManagerWithDB = context.getBean("userManagerWithUserDatabaseProvider", UserManager.class);
        System.out.println(userManagerWithDB.getUserInfo());

        UserManager userManagerWithWS = context.getBean("userManagerWithNewDatabaseProvider", UserManager.class);
        System.out.println(userManagerWithWS.getUserInfo());

        UserManager userManagerWithNewDB = context.getBean("userManagerWithWebServiceProvider", UserManager.class);
        System.out.println(userManagerWithNewDB.getUserInfo());
    }
}
