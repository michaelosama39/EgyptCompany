package com.example.egyptimportandexport.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.egyptimportandexport.R;
import com.example.egyptimportandexport.data.model.Doctor;
import com.example.egyptimportandexport.data.model.Hospital;
import com.example.egyptimportandexport.data.model.Order;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddDataFragment extends Fragment implements View.OnClickListener {

    DatabaseReference databaseHospital , databaseDoctor , databaseOrder ;
    EditText hospitalET , doctorET , orderET;
    Button hospitalBTN , doctorBTN , orderBTN;

    public AddDataFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_data, container, false);
        hospitalET = view.findViewById(R.id.hospital_ET_AddData);
        hospitalBTN = view.findViewById(R.id.hospital_BTN_AddData);
        doctorET = view.findViewById(R.id.doctor_ET_AddData);
        doctorBTN = view.findViewById(R.id.doctor_BTN_AddData);
        orderET = view.findViewById(R.id.order_ET_AddData);
        orderBTN = view.findViewById(R.id.order_BTN_AddData);
        databaseHospital = FirebaseDatabase.getInstance().getReference("Hospitals");
        databaseDoctor = FirebaseDatabase.getInstance().getReference("Doctors");
        databaseOrder = FirebaseDatabase.getInstance().getReference("Orders");

        hospitalBTN.setOnClickListener(this);

        doctorBTN.setOnClickListener(this);

        orderBTN.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == hospitalBTN.getId()){
            addHospital();
        }else if (view.getId() == doctorBTN.getId()){
            addDoctor();
        }else if (view.getId() == orderBTN.getId()){
            addOrder();
        }
    }

    private void addOrder() {
        String order = orderET.getText().toString().trim();;
        if (!TextUtils.isEmpty(order)){
            String id =databaseOrder.push().getKey();
            Order orderModel = new Order(id , order);
            databaseOrder.child(order).setValue(orderModel);
            Toast.makeText(getContext(), "Save", Toast.LENGTH_SHORT).show();
        }else {
            orderET.setError("Empty");
        }
    }

    private void addDoctor() {
        String doctor = doctorET.getText().toString().trim();
        if (!TextUtils.isEmpty(doctor)){
            String id = databaseDoctor.push().getKey();
            Doctor doctorModel = new Doctor(id , doctor);
            databaseDoctor.child(doctor).setValue(doctorModel);
            Toast.makeText(getContext(), "Save", Toast.LENGTH_SHORT).show();
        }else {
            doctorET.setError("Empty");
        }
    }

    private void addHospital() {
        String hospital = hospitalET.getText().toString().trim();;
        if (!TextUtils.isEmpty(hospital)){
            String id =databaseHospital.push().getKey();
            Hospital hospitalModel = new Hospital(id , hospital);
            databaseHospital.child(hospital).setValue(hospitalModel);
            Toast.makeText(getContext(), "Save", Toast.LENGTH_SHORT).show();
        }else {
            hospitalET.setError("Empty");
        }
    }
}