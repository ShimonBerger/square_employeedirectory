package com.sberger.square_employeedirectory.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.facebook.shimmer.ShimmerFrameLayout
import com.sberger.square_employeedirectory.R
import com.sberger.square_employeedirectory.data.network.model.Employee
import com.sberger.square_employeedirectory.databinding.FragmentEmployeeListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmployeeListFragment : Fragment() {
    private lateinit var binding: FragmentEmployeeListBinding
    private lateinit var viewModel: EmployeeListViewModel
    private lateinit var shimmerFrame: ShimmerFrameLayout
    private lateinit var adapter: EmployeeListAdapter

    companion object {
        fun newInstance() = EmployeeListFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EmployeeListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentEmployeeListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner

        shimmerFrame = binding.shimmerFrame

        adapter = EmployeeListAdapter(mutableListOf(), ::onEmployeeClicked)
        binding.employeeListRecyclerView.adapter = adapter
        binding.employeeListRecyclerView.setEmptyView(binding.errorText)

        // set up swipe to refresh
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            viewModel.getEmployeeList()
        }

        setupObservers()
    }

    private fun setupObservers() {
        viewModel.employees.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }

        // set proper message if data is empty due to an error or empty (valid) data set
        viewModel.isError.observe(viewLifecycleOwner) {
            if (it) binding.errorText.text = resources.getString(R.string.employee_list_error)
            else binding.errorText.text = resources.getString(R.string.employee_list_empty)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) {
                // hide everything besides shimmer frame when loading
                binding.employeeListRecyclerView.visibility = View.INVISIBLE
                binding.errorText.visibility = View.INVISIBLE
                shimmerFrame.visibility = View.VISIBLE
                shimmerFrame.startShimmer()
            } else {
                // stop and hide shimmer, then show error/empty state if there is no data, otherwise show the data we have
                shimmerFrame.stopShimmer()
                shimmerFrame.visibility = View.GONE
                binding.employeeListRecyclerView.checkIfEmpty()
            }
        }
    }

    // click listener when an employee is selected (passed into adapter)
    private fun onEmployeeClicked(employee: Employee) {
        Toast.makeText(requireContext(), getString(R.string.employee_clicked, employee.fullName), Toast.LENGTH_SHORT).show()
    }
}