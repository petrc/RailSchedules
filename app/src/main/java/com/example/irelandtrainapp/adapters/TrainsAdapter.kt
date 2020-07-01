package com.example.irelandtrainapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.irelandtrainapp.databinding.TrainListItemBinding
import com.example.irelandtrainapp.dtos.TrainDTO

class TrainsAdapter(private val clickListener: (TrainDTO) -> Unit) :
    RecyclerView.Adapter<TrainsAdapter.TrainDetailViewHolder>() {

    private var trains = listOf<TrainDTO>()

    class TrainDetailViewHolder(val binding: TrainListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainDetailViewHolder {
        val itemBinding =
            TrainListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrainDetailViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: TrainDetailViewHolder, position: Int) {
        holder.binding.trainDTO = trains[position]
        holder.itemView.setOnClickListener {
            clickListener(trains[position])
        }
    }

    override fun getItemCount() = trains.size

    fun updateTrains(newTrains: List<TrainDTO>?) {
        newTrains?.let {
            trains = it
            notifyDataSetChanged()
        }
    }
}