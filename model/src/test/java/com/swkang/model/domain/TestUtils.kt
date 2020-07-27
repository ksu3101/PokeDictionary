package com.swkang.model.domain

import org.junit.Assert
import org.mockito.ArgumentMatchers.nullable
import org.mockito.Mockito
import org.mockito.stubbing.OngoingStubbing

inline fun <reified T : Any> mock() = Mockito.mock(T::class.java)

infix fun <T : Any> T.given(call: T?): OngoingStubbing<T> = Mockito.`when`(call)

fun <T : Any> given(call: T?): OngoingStubbing<T> = Mockito.`when`(call)

fun anyNullableString(): String? = nullable(String::class.java)
fun anyNullableLong(): Long? = nullable(Long::class.java)

infix fun <T : Any> OngoingStubbing<T>.willReturn(returnValue: T?): OngoingStubbing<T> =
    this.thenReturn(returnValue)

fun <T : Any> T.verify(): T = Mockito.verify(this)
fun <T: Any> T.hasCalled(): T = this.verify()

fun <T : Any> T.verifyNever(): T = Mockito.verify(this, Mockito.never())
fun <T: Any> T.hasNotCalled(): T = this.verifyNever()

infix fun <T : Any> T?.shouldBe(expected: T?) = this.apply { Assert.assertEquals(expected, this) }

infix fun <T : Any> T?.shouldNotBe(expected: T?) = this.apply {
    Assert.assertNotSame(
        expected,
        this
    )
}
