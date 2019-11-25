package com.project.template;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@SpringBootTest
class TemplateApplicationTests {

    @Test
    void contextLoads() {

        ReentrantLock lock = new ReentrantLock(true);

        lock.lock();
        lock.unlock();

        Condition condition = lock.newCondition();
        try {
            condition.await();
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
