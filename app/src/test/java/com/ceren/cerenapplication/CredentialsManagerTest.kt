package com.example.mobileapp_project

import com.ceren.cerenapplication.CredentialsManager
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CredentialsManagerTest {
    private val validator = CredentialsManager()

    @Test
    fun `invalid email should return false`() {
        assertFalse(validator.isEmailValid(""))
        assertFalse(validator.isEmailValid("invalid.email"))
        assertFalse(validator.isEmailValid("@domain.com"))
    }

    @Test
    fun `valid email should return true`() {
        assertTrue(validator.isEmailValid("test@domain.com"))
        assertTrue(validator.isEmailValid("user.name@company.org"))
    }

    @Test
    fun `invalid password should return false`() {
        assertFalse(validator.isPasswordValid(""))
        assertFalse(validator.isPasswordValid("weak"))
        assertFalse(validator.isPasswordValid("NoSpecialChar1"))
    }

    @Test
    fun `valid password should return true`() {
        assertTrue(validator.isPasswordValid("StrongPass@123"))
        assertTrue(validator.isPasswordValid("Complex#Pass1"))
    }
}