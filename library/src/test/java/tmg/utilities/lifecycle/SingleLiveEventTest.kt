package tmg.utilities.lifecycle

import androidx.lifecycle.Observer
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import tmg.utilities.TestingTaskExecutor

@ExtendWith(TestingTaskExecutor::class)
internal class SingleLiveEventTest {

    @Test
    fun `onChanged is still called when new value is set and is being actively observed`() {

        val liveData = SingleLiveEvent<Int>()

        val testObserver = getObserver()
        liveData.observeForever(testObserver)

        liveData.value = 101

        assertEquals(1, testObserver.count)

        liveData.value = 101

        assertEquals(2, testObserver.count)
    }

    private fun getObserver() = object : Observer<Int> {
        var count: Int = 0
        override fun onChanged(t: Int) {
            count++
        }
    }
}