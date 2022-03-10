package tmg.utilities.lifecycle

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import org.junit.Rule
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.rules.TestRule
import tmg.testutils.BaseTest

internal class SingleLiveEventTest: BaseTest() {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

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
        override fun onChanged(t: Int?) {
            count++
        }
    }
}