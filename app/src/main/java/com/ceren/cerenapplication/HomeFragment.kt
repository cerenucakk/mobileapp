package com.ceren.cerenapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ceren.cerenapplication.databinding.HomeFragmentBinding

class HomeFragment : Fragment() {
    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ItemAdapter
    private lateinit var credentialsManager: CredentialsManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        credentialsManager = CredentialsManager(requireContext())
        setupRecyclerView()
        loadItems()

        binding.logoutButton.setOnClickListener {
            credentialsManager.logout()
            findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
        }
    }

    private fun setupRecyclerView() {
        adapter = ItemAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@HomeFragment.adapter
        }
    }

    private fun loadItems() {
        val currentUser = credentialsManager.getLoggedInUser()
        
        val items = listOf(
            Item(
                title = "Name",
                description = "${currentUser?.name}"
            ),
            Item(
                title = "Email",
                description = "${currentUser?.email}"
            )
        )
        adapter.submitList(items)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 