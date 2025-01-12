package com.ceren.cerenapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.ceren.cerenapplication.databinding.LoginFragmentBinding

class LoginFragment : Fragment() {
    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var credentialsManager: CredentialsManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        credentialsManager = CredentialsManager(requireContext())

        binding.loginButton.setOnClickListener {
            val email = binding.emailInput.text.toString()
            val password = binding.passwordInput.text.toString()

            if (validateInputs(email, password)) {
                credentialsManager.login(email, password) { success ->
                    if (success) {
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    } else {
                        Toast.makeText(requireContext(), "Login failed", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        binding.registerButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun validateInputs(email: String, password: String): Boolean {
        if (!credentialsManager.isEmailValid(email)) {
            binding.emailInput.error = "Please enter a valid email address"
            return false
        }

        if (!credentialsManager.isPasswordValid(password)) {
            binding.passwordInput.error = "Password must be at least 6 characters"
            return false
        }

        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 