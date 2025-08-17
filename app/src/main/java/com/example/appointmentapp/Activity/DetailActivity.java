package com.example.appointmentapp.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.bumptech.glide.Glide;
import com.example.appointmentapp.Adapter.DateAdapter;
import com.example.appointmentapp.Adapter.TimeAdapter;
import com.example.appointmentapp.Domain.AppointmentModel;
import com.example.appointmentapp.Domain.DoctorsModel;
import com.example.appointmentapp.R;
import com.example.appointmentapp.ViewModel.MainViewModel;
import com.example.appointmentapp.databinding.ActivityDetailBinding;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private ActivityDetailBinding binding;
    private DoctorsModel item;
    private MainViewModel viewModel;
    private DateAdapter dateAdapter;
    private TimeAdapter timeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        item = (DoctorsModel) getIntent().getSerializableExtra("object");
        viewModel = new MainViewModel();

        setVariable();
        initDate();
        initTime();
        initBookButton();
    }

    private void initTime() {
        binding.timeView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        timeAdapter = new TimeAdapter(generateTimeSlots());
        binding.timeView.setAdapter(timeAdapter);
    }

    private void initDate() {
        binding.dateView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        dateAdapter = new DateAdapter(generateDates());
        binding.dateView.setAdapter(dateAdapter);
    }

    private void initBookButton() {
        binding.button.setOnClickListener(v -> {
            String selectedDate = dateAdapter.getSelectedDate();
            String selectedTime = timeAdapter.getSelectedTime();

            if (selectedDate == null || selectedTime == null) {
                Toast.makeText(this, "Please select a date and time", Toast.LENGTH_SHORT).show();
                return;
            }

            // Assuming userId is obtained from authentication (placeholder for now)
            String userId = "user123"; // Replace with actual user ID from authentication
            AppointmentModel appointment = new AppointmentModel(
                    String.valueOf(item.getId()),
                    item.getName(),
                    userId,
                    selectedDate,
                    selectedTime
            );

            viewModel.saveAppointment(appointment).observe(this, success -> {
                if (success != null && success) {
                    Toast.makeText(this, "Appointment booked successfully!", Toast.LENGTH_SHORT).show();
                    finish(); // Return to previous screen
                } else {
                    Toast.makeText(this, "Failed to book appointment. Try again.", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    public static List<String> generateTimeSlots() {
        List<String> timeSlots = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        for (int i = 0; i < 24; i += 2) {
            LocalTime time = LocalTime.of(i, 0);
            timeSlots.add(time.format(formatter));
        }
        return timeSlots;
    }

    public static List<String> generateDates() {
        List<String> dates = new ArrayList<>();
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE/dd/MMM");
        for (int i = 0; i < 7; i++) {
            dates.add(today.plusDays(i).format(formatter));
        }
        return dates;
    }

    private void setVariable() {
        binding.backBtn.setOnClickListener(v -> finish());

        Glide.with(DetailActivity.this)
                .load(item.getPicture())
                .into(binding.img);

        binding.addressTxt.setText(item.getAddress());
        binding.nameTxt.setText(item.getName());
        binding.specialTxt.setText(item.getSpecial());
        binding.patiensTxt.setText(item.getPatiens() + "");
        binding.experienceTxt.setText(item.getExperience() + " Years");
    }
}