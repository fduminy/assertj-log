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
