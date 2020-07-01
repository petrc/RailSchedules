package com.example.irelandtrainapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.irelandtrainapp.databinding.StationListItemBinding
import com.example.irelandtrainapp.dtos.StationDTO

class StationsAdapter(private val clickListener: (StationDTO) -> Unit) :
    RecyclerView.Adapter<StationsAdapter.StationSearchViewHolder>() {

    private var stations = listOf<StationDTO>()

    class StationSearchViewHolder(val binding: StationListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationSearchViewHolder {
        val itemBinding =
            StationListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StationSearchViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: StationSearchViewHolder, position: Int) {
        holder.binding.stationDTO = stations[position]
        holder.itemView.setOnClickListener { clickListener(stations[position]) }
    }

    override fun getItemCount() = stations.size

    fun updateStations(newStations: List<StationDTO>?) {
        newStations?.let {
            this.stations = it
            notifyDataSetChanged()
        }
    }
}