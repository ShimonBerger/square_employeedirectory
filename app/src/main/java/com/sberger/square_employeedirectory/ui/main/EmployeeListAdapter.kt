package com.sberger.square_employeedirectory.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sberger.square_employeedirectory.R
import com.sberger.square_employeedirectory.data.network.model.Employee
import com.sberger.square_employeedirectory.databinding.ViewEmployeeListItemBinding

class EmployeeListAdapter(private var employees: List<Employee>, private var onItemClicked: (Employee) -> Unit) :
    RecyclerView.Adapter<EmployeeListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewEmployeeListItemBinding>(inflater, R.layout.view_employee_list_item, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return employees.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(employees[position], onItemClicked)
    }

    fun setData(employees: List<Employee>) {
        this.employees = employees
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ViewEmployeeListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(employee: Employee, onItemClicked: (Employee) -> Unit) {
            binding.employee = employee

            binding.root.setOnClickListener {
                onItemClicked(employee)
            }

            // forces the binding to execute immediately, which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }

    }
}