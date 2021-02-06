package lombok;

import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Log4j2
public class CarTest {

    @Test
    public void carTest() {


        Car car4 = Car.builder()
                .model("Tesla")
                .version("model 3")
                .build();
        Car car1 = new Car("Tesla", "model 3", 200);
        Car car2 = new Car("Tesla", "model 3", 200);
        Car car3 = new Car();
        car3.setModel("BMW");
        car3.setVersion("X5");
        car3.setSpeed(300);

        log.fatal("Fatal");
        log.error("Error");
        log.warn("Warn");
        log.info("Info");
        log.debug("Debug");
        log.trace("Trace");

        System.out.println(car1);
        System.out.println(car2);


        assertEquals(car1, car2);

    }
}
