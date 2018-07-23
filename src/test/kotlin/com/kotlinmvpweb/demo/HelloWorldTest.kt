import mu.KotlinLogging
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import kotlin.test.assertTrue

class ExampleTests {

    private val logger = KotlinLogging.logger {}

    @Before
    fun prepareYourTest() {
        logger.debug { "We are preparing our test" }
    }

    @Test
    fun exampleTestInline() {
        logger.debug { "We are executing our test" }
        assertTrue {
            logger.debug { "Here an assertion will take place" }
            true
        }
    }

    @Test
    fun testOk() {
        Assert.assertEquals("A string", "A different string" )
    }

    @After
    fun after() {
        logger.debug { "We finished executing our test" }
    }
}