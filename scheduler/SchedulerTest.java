package scheduler;

import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SchedulerTest {
    @Test
    public void test() {
        final Date now = new Date();
        Scheduler scheduler = new Scheduler("Mickey Mouse", new FakeDisplay());
        scheduler.addEvent(new Event(now, DayTime.Time1PM));

        Assertions.assertTrue(scheduler.hasEvents(now));
    }

    private static class FakeDisplay implements Display {

        @Override
        public void showEvent(Event event) {

        }
    }
}
