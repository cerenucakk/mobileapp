package com.ceren.cerenapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ceren.cerenapplication.databinding.RegisterFragmentBinding

class RegisterFragment : Fragment() {
    private var _binding: RegisterFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var credentialsManager: CredentialsManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RegisterFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        credentialsManager = CredentialsManager(requireContext())

        binding.registerButton.setOnClickListener {
            val fullName = binding.fullNameInput.text.toString()
            val email = binding.emailInput.text.toString()
            val password = binding.passwordInput.text.toString()

            if (validateInputs(fullName, email, password)) {
                credentialsManager.registerUser(fullName, email, password) { success ->
                    if (success) {
                        Toast.makeText(
                            requireContext(),
                            "Registration successful! You can now login.",
                            Toast.LENGTH_LONG
                        ).show()
                        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "This email is already in use!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

        binding.loginButton.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }

    private fun validateInputs(fullName: String, email: String, password: String): Boolean {
        if (fullName.isEmpty()) {
            binding.fullNameInput.error = "Name field cannot be empty"
            Toast.makeText(
                requireContext(),
                "Please enter your name",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        if (!credentialsManager.isEmailValid(email)) {
            binding.emailInput.error = "Please enter a valid email address"
            Toast.makeText(
                requireContext(),
                "Please enter a valid email address",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        if (!credentialsManager.isPasswordValid(password)) {
            binding.passwordInput.error = "Password must be at least 6 characters"
            Toast.makeText(
                requireContext(),
                "Password must be at least 6 characters",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 