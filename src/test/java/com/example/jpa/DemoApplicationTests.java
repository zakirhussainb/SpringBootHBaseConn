package com.example.jpa;

import Utils.CrudRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    CrudRepository crudRepository = new CrudRepository();

    public DemoApplicationTests() throws IOException {
    }

    @Test
	public void contextLoads() throws IOException {
        crudRepository.put("dev_courses","C1","user_view_counter","U1","10");
        crudRepository.put("dev_courses","C2","user_view_counter","U2","11");
	}

}
