package com.example.ahmedmakaty.data.executor

import android.provider.SyncStateContract
import com.example.ahmedmakaty.data.Constants
import com.example.ahmedmakaty.domain.executor.ThreadExecutor
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadFactory
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class JobExecutor : ThreadExecutor {

    private object LazyHolder {
        val INSTANCE = JobExecutor()
    }

    private val workQueue: LinkedBlockingQueue<Runnable>
    private val threadPoolExecutor: ThreadPoolExecutor
    private val threadFactory: ThreadFactory

    companion object {
        private val INITIAL_POOL_SIZE: Int = 3
        private val MAX_POOL_SIZE: Int = 5
        // Sets the amount of time an idle thread waits before terminating
        private val KEEP_ALIVE_TIME: Long = 10
        // Sets the Time Unit to seconds
        private val KEEP_ALIVE_TIME_UNIT: TimeUnit = TimeUnit.SECONDS

        fun getInstance(): JobExecutor {
            return LazyHolder.INSTANCE
        }
    }

    init {
        workQueue = LinkedBlockingQueue()
        threadFactory = JobThreadFactory()
        threadPoolExecutor = ThreadPoolExecutor(
            INITIAL_POOL_SIZE, MAX_POOL_SIZE,
            KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT, this.workQueue, this.threadFactory
        )
    }

    override fun execute(command: Runnable?) {
        if (command == null) {
            throw IllegalArgumentException("Runnable to execute cannot be null")
        }
        threadPoolExecutor.execute(command)
    }

    private class JobThreadFactory : ThreadFactory {
        private var counter = 0

        override fun newThread(r: Runnable): Thread {
            return Thread(r, THREAD_NAME + counter++)
        }

        companion object {
            private val THREAD_NAME = "android_"
        }
    }
}