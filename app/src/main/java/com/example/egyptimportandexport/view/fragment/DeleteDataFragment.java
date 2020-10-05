package com.example.egyptimportandexport.view.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.egyptimportandexport.R;
import com.example.egyptimportandexport.data.model.DoctorDelete;
import com.example.egyptimportandexport.data.model.HospitalDelete;
import com.example.egyptimportandexport.data.model.OrderDelete;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DeleteDataFragment extends Fragment {
    Button hospitalBTN , doctorBTN , orderBTN ;
    ListView listView_Delete;
    ArrayList<String> arrayListHospital , arrayListDoctor , arrayListOrder , arrayList ;
    ArrayAdapter<String> arrayAdapterHospital , arrayAdapterDoctor , arrayAdapterOrder ;
    DatabaseReference databaseReferenceHospital , databaseReferenceDoctor , databaseReferenceOrder;
    HospitalDelete hospitalDelete ;
    DoctorDelete doctorDelete;
    OrderDelete orderDelete;

    public DeleteDataFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_delete_data, container, false);
        hospitalBTN = view.findViewById(R.id.hospital_BTN_DeleteData);
        doctorBTN = view.findViewById(R.id.doctor_BTN_DeleteData);
        orderBTN = view.findViewById(R.id.order_BTN_DeleteData);
        listView_Delete = view.findViewById(R.id.listView_DeleteData);
        arrayList = new ArrayList<>();
        hospitalDelete = new HospitalDelete();
        doctorDelete = new DoctorDelete();
        orderDelete = new OrderDelete();

        // ListView Hospital
        hospitalBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayListHospital = new ArrayList<>();
                arrayAdapterHospital = new ArrayAdapter<>(getContext() , android.R.layout.simple_list_item_1 , arrayListHospital);
                listView_Delete.setAdapter(arrayAdapterHospital);

                databaseReferenceHospital = FirebaseDatabase.getInstance().getReference().child("Hospitals");
                databaseReferenceHospital.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        Map<String , Object> map = (Map<String, Object>) snapshot.getValue();
                        arrayAdapterHospital.add(map.get("hospitalName") + "");
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        arrayAdapterHospital.notifyDataSetChanged();
                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
//                    public void upDateHospital(client:List<>) {
//                        this.clients = client;
//                        arrayAdapterHospital.notifyDataSetChanged();
//                    }
                });

                // Delete Data
                listView_Delete.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                        hospitalDelete.setHospitalId(arrayListHospital.get(i));
                        hospitalDelete.setHospitalName(arrayListHospital.get(i));

                        final String strH = hospitalDelete.getHospitalId();
                        databaseReferenceHospital.child("Hospitals").child(strH).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                databaseReferenceHospital.child(strH).removeValue();
                                arrayAdapterHospital.notifyDataSetChanged();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        Toast.makeText(getContext(), "Done", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });

            }
        });

        // ListView Doctor
        doctorBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayListDoctor = new ArrayList<>();
                arrayAdapterDoctor = new ArrayAdapter<>(getContext() , android.R.layout.simple_list_item_1 , arrayListDoctor);
                listView_Delete.setAdapter(arrayAdapterDoctor);

                databaseReferenceDoctor = FirebaseDatabase.getInstance().getReference().child("Doctors");
                databaseReferenceDoctor.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        Map<String , Object> map = (Map<String, Object>) snapshot.getValue();
                        arrayAdapterDoctor.add(map.get("doctorName") + "");
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        arrayAdapterDoctor.notifyDataSetChanged();
                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                // Delete Data
                listView_Delete.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                        doctorDelete.setDoctorId(arrayListDoctor.get(i));
                        doctorDelete.setDoctorName(arrayListDoctor.get(i));

                        final String strD = doctorDelete.getDoctorId();
                        databaseReferenceDoctor.child("Doctors").child(strD).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                databaseReferenceDoctor.child(strD).removeValue();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        Toast.makeText(getContext(), "Done", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });
            }
        });

        // ListView Order
        orderBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayListOrder = new ArrayList<>();
                arrayAdapterOrder = new ArrayAdapter<>(getContext() , android.R.layout.simple_list_item_1 , arrayListOrder);
                listView_Delete.setAdapter(arrayAdapterOrder);

                databaseReferenceOrder = FirebaseDatabase.getInstance().getReference().child("Orders");
                databaseReferenceOrder.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        Map<String , Object> map = (Map<String, Object>) snapshot.getValue();
                        arrayAdapterOrder.add(map.get("orderName") + "");
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        arrayAdapterOrder.notifyDataSetChanged();
                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                // Delete Data
                listView_Delete.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(final AdapterView<?> adapterView, View view, int i, long l) {
                        orderDelete.setOrderId(arrayListOrder.get(i));
                        orderDelete.setOrderName(arrayListOrder.get(i));

                        final String strO = orderDelete.getOrderId();
                        databaseReferenceOrder.child("Orders").child(strO).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                databaseReferenceOrder.child(strO).removeValue();

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        Toast.makeText(getContext(), "Done", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });
            }

        });
        return view;
    }

}
