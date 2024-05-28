package com.example.lesson20april

import android.hardware.Sensor
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson20april.databinding.SensorItemBinding

class SensorAdapter : ListAdapter<Sensor, SensorAdapter.SensorViewHolder>(SensorDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SensorViewHolder {
        val binding = SensorItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SensorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SensorViewHolder, position: Int) {
        val sensor = getItem(position)
        holder.bind(sensor)
    }

    class SensorViewHolder(private val binding: SensorItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(sensor: Sensor) {
            binding.sensorName.text = sensor.name
            binding.sensorType.text = sensor.stringType
        }
    }
}

class SensorDiffCallback : DiffUtil.ItemCallback<Sensor>() {
    override fun areItemsTheSame(oldItem: Sensor, newItem: Sensor): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Sensor, newItem: Sensor): Boolean {
        return oldItem.name == newItem.name && oldItem.type == newItem.type
    }
}
