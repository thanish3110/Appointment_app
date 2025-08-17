package com.example.appointmentapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appointmentapp.R;
import com.example.appointmentapp.databinding.ViewholderTimeBinding;
import java.util.List;

public class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.TimeViewholder> {
    private final List<String> timeSlots;
    private int selectedPosition = -1;
    private int lastSelectedPosition = -1;

    public TimeAdapter(List<String> timeSlots) {
        this.timeSlots = timeSlots;
    }

    @NonNull
    @Override
    public TimeViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewholderTimeBinding binding = ViewholderTimeBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new TimeViewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeViewholder holder, int position) {
        holder.bind(timeSlots.get(position), position, this);
    }

    @Override
    public int getItemCount() {
        return timeSlots.size();
    }

    // Method to get selected time
    public String getSelectedTime() {
        if (selectedPosition >= 0 && selectedPosition < timeSlots.size()) {
            return timeSlots.get(selectedPosition);
        }
        return null;
    }

    public class TimeViewholder extends RecyclerView.ViewHolder {
        private final ViewholderTimeBinding binding;

        public TimeViewholder(ViewholderTimeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(String time, int position, TimeAdapter adapter) {
            binding.timeTxt.setText(time);
            if (adapter.selectedPosition == position) {
                binding.timeTxt.setBackgroundResource(R.drawable.blue_btn_bg);
                binding.timeTxt.setTextColor(ContextCompat.getColor(binding.getRoot().getContext(), R.color.white));
            } else {
                binding.timeTxt.setBackgroundResource(R.drawable.light_gray_bg);
                binding.timeTxt.setTextColor(ContextCompat.getColor(binding.getRoot().getContext(), R.color.black));
            }

            binding.getRoot().setOnClickListener(view -> {
                adapter.lastSelectedPosition = adapter.selectedPosition;
                adapter.selectedPosition = position;
                adapter.notifyItemChanged(adapter.lastSelectedPosition);
                adapter.notifyItemChanged(adapter.selectedPosition);
            });
        }
    }
}