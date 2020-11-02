package com.bandmeetup;

import com.bandmeetup.controller.LogController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BandmeetupApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private LogController LCont;

    @Test
    public void LogLoads() throws Exception{
        assertThat(LCont).isNotNull();
    }


}
