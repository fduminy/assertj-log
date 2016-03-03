This project provides a simple and intuitive API for functional testing of logs, resulting in tests 
that are compact, easy to write, and read like a specification. Tests written using AssertJ Log are also robust. 

## Example

```java
    public class ExampleTest {
        @Rule
        public final LogAssertRule<A> rule = new LogAssertRule<>(LOG4J);
        //public final LogAssertRule<A> rule = new LogAssertRule<>(LOG4J2);
        //public final LogAssertRule<A> rule = new LogAssertRule<>(LOGBACK);
        //public final LogAssertRule<A> rule = new LogAssertRule<>(JUL);

        @Test
        public final void testLog() throws Exception {
            // do something that should log
            
            // verify logs
            new LogAssert(rule).as("logs").hasSize(1);
        }
    }
```
