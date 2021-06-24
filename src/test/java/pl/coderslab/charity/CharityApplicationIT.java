package pl.coderslab.charity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CharityApplicationIT {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    void shouldCreateWarnMeApplicationBeat() {
        assertThat(applicationContext.containsBeanDefinition("charityApplication")).isTrue();
    }

}