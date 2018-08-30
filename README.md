This project provides a simple and intuitive API for functional testing of logs, resulting in tests 
that are compact, easy to write, and read like a specification. Tests written using AssertJ Log are also robust. 

## Example

```java
    public class ExampleTest {
        @Rule
        public final LogAssertRule<A> logs = new LogAssertRule<>(LOG4J);
        //public final LogAssertRule<A> logs = new LogAssertRule<>(LOG4J2);
        //public final LogAssertRule<A> logs = new LogAssertRule<>(LOGBACK);
        //public final LogAssertRule<A> logs = new LogAssertRule<>(JUL);

        @Test
        public final void testLog() throws Exception {
            // call a method that should log something
            
            // verify logs
            assertThat(logs).hasSize(1);
        }
    }
```

## Another Example (with JUL)

```java
    import java.util.logging.Level;
    import java.util.logging.Logger;

    public class Math {

        private static Logger logger = Logger.getLogger(Math.class.getName());
        
        public static int safeDiv(int dividend, int divisor){
            try {
                return dividend / divisor;
            }
            catch (ArithmeticException ae) {
                logger.log(Level.WARNING, "Invalid divisor. Retuning 0.", ae);
                return 0;
            }
        }
        
    }


    import static org.assertj.core.api.Assertions.assertThat;
    import static org.assertj.jul.JulLoggerFacade.JUL;

    import org.assertj.log.LogAssertRule;
    import org.assertj.log.LogAssertions;
    import org.assertj.log.LogEvent;
    import org.assertj.log.LogLevel;
    import org.junit.Rule;
    import org.junit.Test;
    public class TestMath {

        @Rule
        public final LogAssertRule<?> logs = new LogAssertRule<>(JUL);

        @Test
        public void invalid_divisor() {
            Math.safeDiv(1, 0);
            LogAssertions.assertThat(logs).hasSize(1);//from assertj-log
            LogEvent logEvent = logs.getEvents().get(0);
            assertThat(logEvent.getMessage()).startsWith("Invalid divisor");//assertions from assertj
            assertThat(logEvent.getLevel()).isSameAs(LogLevel.WARN);
            assertThat(logEvent.getThrowable()).isInstanceOf(ArithmeticException.class);
        }
...
    
```

