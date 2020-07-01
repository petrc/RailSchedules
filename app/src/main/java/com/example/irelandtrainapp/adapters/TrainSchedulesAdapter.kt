package com.example.irelandtrainapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.irelandtrainapp.R
import com.example.irelandtrainapp.databinding.*
import com.example.irelandtrainapp.dtos.TrainScheduleDTO

class TrainSchedulesAdapter(private val clickListener: (TrainScheduleDTO) -> Unit) :
    RecyclerView.Adapter<TrainSchedulesAdapter.ScheduleItemViewHolder>() {

    private var schedules = listOf<TrainScheduleDTO>()

    open class ScheduleItemViewHolder(val view: View) :
        RecyclerView.ViewHolder(view)

    class StationViewHolder(val binding: TrainScheduleStationListItemBinding) :
        ScheduleItemViewHolder(binding.root)

    class TimingViewHolder(val binding: TrainScheduleTimingListItemBinding) :
        ScheduleItemViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleItemViewHolder {

        return when (viewType) {
            TYPE_STATION ->
                StationViewHolder(
                    TrainScheduleStationListItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            TYPE_TIMING ->
                TimingViewHolder(
                    TrainScheduleTimingListItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            else -> {
                ScheduleItemViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.train_schedule_unknown_list_item, parent, false)
                )
            }
        }
    }

    override fun onBindViewHolder(holder: ScheduleItemViewHolder, position: Int) {

        when (holder) {
            is StationViewHolder -> holder.binding.trainScheduleDTO = schedules[position]
            is TimingViewHolder -> holder.binding.trainScheduleDTO = schedules[position]
        }

        if (holder !is TimingViewHolder) {
            holder.itemView.setOnClickListener {
                clickListener(schedules[position])
            }
        }
    }

    override fun getItemCount() = schedules.size

    override fun getItemViewType(position: Int): Int {
        return when (schedules[position].locationType) {
            "O", "D", "S" -> TYPE_STATION
            "T" -> TYPE_TIMING
            else -> TYPE_UNKNOWN
        }
    }

    fun updateSchedules(newSchedules: List<TrainScheduleDTO>?) {
        newSchedules?.let {
            schedules = newSchedules
            notifyDataSetChanged()
        }
    }

    companion object {
        private const val TYPE_UNKNOWN = 0
        private const val TYPE_STATION = 1
        private const val TYPE_TIMING = 2
    }
}