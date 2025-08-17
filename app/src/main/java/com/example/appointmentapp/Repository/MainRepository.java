package com.example.appointmentapp.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.appointmentapp.Domain.AppointmentModel;
import com.example.appointmentapp.Domain.CategoryModel;
import com.example.appointmentapp.Domain.DoctorsModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class MainRepository {
    private final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    public LiveData<List<CategoryModel>> loadCategory() {
        final MutableLiveData<List<CategoryModel>> listData = new MutableLiveData<>();
        DatabaseReference ref = firebaseDatabase.getReference("Category");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<CategoryModel> lists = new ArrayList<>();
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    CategoryModel item = childSnapshot.getValue(CategoryModel.class);
                    if (item != null) {
                        lists.add(item);
                    }
                }
                listData.setValue(lists);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error
            }
        });
        return listData;
    }

    public LiveData<List<DoctorsModel>> loadDoctor() {
        final MutableLiveData<List<DoctorsModel>> liveData = new MutableLiveData<>();
        DatabaseReference ref = firebaseDatabase.getReference("Doctors");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<DoctorsModel> lists = new ArrayList<>();
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    DoctorsModel item = childSnapshot.getValue(DoctorsModel.class);
                    if (item != null) {
                        lists.add(item);
                    }
                }
                liveData.setValue(lists);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error
            }
        });
        return liveData;
    }

    // New method to save appointment
    public LiveData<Boolean> saveAppointment(AppointmentModel appointment) {
        MutableLiveData<Boolean> result = new MutableLiveData<>();
        DatabaseReference ref = firebaseDatabase.getReference("Appointments").push();
        appointment.setAppointmentId(ref.getKey());
        ref.setValue(appointment).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                result.setValue(true);
            } else {
                result.setValue(false);
            }
        });
        return result;
    }
}