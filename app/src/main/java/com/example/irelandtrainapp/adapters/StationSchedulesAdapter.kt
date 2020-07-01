package com.example.irelandtrainapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.irelandtrainapp.databinding.StationScheduleListItemBinding
import com.example.irelandtrainapp.dtos.StationScheduleDTO

class StationSchedulesAdapter(private val clickListener: (StationScheduleDTO) -> Unit) :
    RecyclerView.Adapter<StationSchedulesAdapter.ScheduleViewHolder>() {

    private var schedules = listOf<StationScheduleDTO>()

    class ScheduleViewHolder(val binding: StationScheduleListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val itemBinding = StationScheduleListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ScheduleViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        holder.binding.scheduleDTO = schedules[position]
        holder.itemView.setOnClickListener {
            clickListener(schedules[position])
        }
    }

    override fun getItemCount() = schedules.size

    fun updateSchedules(newSchedules: List<StationScheduleDTO>?) {
        newSchedules?.let {
            this.schedules = it
            notifyDataSetChanged()
        }
    }
}