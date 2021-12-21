package com.devtika.devtikaspringdocker;

import com.devtika.devtikaspringdocker.controller.DevtikaController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ControllerTest {

    @Autowired
    private DevtikaController controller;

    @Test
    public void contextLoads() throws Exception {
        Assertions.assertThat(controller).isNotNull();
    }
}
