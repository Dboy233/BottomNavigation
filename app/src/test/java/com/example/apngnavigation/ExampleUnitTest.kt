package com.example.apngnavigation

import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val b= B()
        b.print("哈哈")
    }


   open interface A {
        fun print(msg: String, tag: String = "DJC")
    }

   open class B : A {

       @JvmOverloads
        override fun print(msg: String, tag: String) {
            println("tag = $tag msg=$msg")
        }

    }

}