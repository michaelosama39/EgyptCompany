package com.example.egyptimportandexport.view.fragment;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.egyptimportandexport.R;
import com.example.egyptimportandexport.adapter.RecyclerViewAdapter_ShowData;
import com.example.egyptimportandexport.data.local.DBHelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public class HomeFragment extends Fragment implements AdapterView.OnItemSelectedListener , View.OnClickListener {
    SQLiteDatabase mDatabase;
    String dateNow , orderList;
    String[] stringOrder;
    TextView dateTV  , dateCenter;
    Spinner hospitalSPN , doctorSPN , orderSPN ;
    Button saveBTN , plusOrderBTN;
    EditText quantityET , timeET;
    RecyclerView recyclerView;
    private DatabaseReference databaseHospitalSpinner , databaseDoctorSpinner , databaseOrderSpinner  ;
    private ArrayList<String> listHospital , listDoctor , listOrder;
    String hospital , doctor , order;
    private RecyclerViewAdapter_ShowData mAdapter;
    ArrayList<String> list = new ArrayList<>();
    ArrayAdapter<String> arrayAdapterOrder , arrayAdapterDoctor , arrayAdapterHospital ;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        DBHelper dbHelper = new DBHelper(getContext());
        mDatabase = dbHelper.getWritableDatabase();
        dateTV = view.findViewById(R.id.date_TV_HomeFragment);
        timeET = view.findViewById(R.id.time_ET_HomeFragment);
        hospitalSPN = view.findViewById(R.id.hospital_SPN_HomeFragment);
        doctorSPN = view.findViewById(R.id.doctor_SPN_HomeFragment);
        orderSPN = view.findViewById(R.id.order_SPN_HomeFragment);
        saveBTN = view.findViewById(R.id.save_BTN_HomeFragment);
        plusOrderBTN = view.findViewById(R.id.plusOrder_BTN_HomeFragment);
        quantityET = view.findViewById(R.id.ET_HomeFragment);
        recyclerView = view.findViewById(R.id.recyclerView_HomeFragment);
        dateCenter = view.findViewById(R.id.dateCenter_TV_HomeFragment);

        // Show Date Now
        Calendar calendar = Calendar.getInstance();
        dateNow = DateFormat.getDateInstance().format(calendar.getTime());
        dateTV.setText(dateNow);
        dateCenter.setText(dateNow);

        // Show Hospital Spinner Data
        databaseHospitalSpinner = FirebaseDatabase.getInstance().getReference();
        listHospital = new ArrayList<>();
        ShowDataSpinnerHospital();

        // Show Doctor Spinner Data
        databaseDoctorSpinner = FirebaseDatabase.getInstance().getReference();
        listDoctor = new ArrayList<>();
        ShowDataSpinnerDoctor();

        // Show Order Spinner Data
        databaseOrderSpinner = FirebaseDatabase.getInstance().getReference();
        listOrder = new ArrayList<>();
        ShowDataSpinnerOrder();

        // get data from Hospital Spinner
        hospitalSPN.setOnItemSelectedListener(this);

        // get data from Doctor Spinner
        doctorSPN.setOnItemSelectedListener(this);

        // get data from Order Spinner
        orderSPN.setOnItemSelectedListener(this);

        // plus Order
        plusOrderBTN.setOnClickListener(this);

        // save BTN
        saveBTN.setOnClickListener(this);

        // RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new RecyclerViewAdapter_ShowData(getContext() , getAllItem());
        recyclerView.setAdapter(mAdapter);

        // delete item
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0 , ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                removeItem((long) viewHolder.itemView.getTag());
            }
        }).attachToRecyclerView(recyclerView);


        return view;
    }

    private void ShowDataSpinnerOrder() {
        databaseOrderSpinner.child("Orders").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listOrder.clear();
                for (DataSnapshot item : snapshot.getChildren()){
                    listOrder.add(item.child("orderName").getValue(String.class));
                }
                arrayAdapterOrder = new ArrayAdapter<>(getContext() , R.layout.style_spinner, listOrder);
                orderSPN.setAdapter(arrayAdapterOrder);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void ShowDataSpinnerDoctor() {
        databaseDoctorSpinner.child("Doctors").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listDoctor.clear();
                for (DataSnapshot item : snapshot.getChildren()){
                    listDoctor.add(item.child("doctorName").getValue(String.class));
                }
                arrayAdapterDoctor = new ArrayAdapter<>(getContext() , R.layout.style_spinner_gray, listDoctor);
                doctorSPN.setAdapter(arrayAdapterDoctor);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void ShowDataSpinnerHospital() {
        databaseHospitalSpinner.child("Hospitals").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listHospital.clear();
                for (DataSnapshot item : snapshot.getChildren()){
                    listHospital.add(item.child("hospitalName").getValue(String.class));
                }
                arrayAdapterHospital = new ArrayAdapter<>(getContext() , R.layout.style_spinner, listHospital);
                hospitalSPN.setAdapter(arrayAdapterHospital);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

        if (adapterView.getId() == hospitalSPN.getId()) {
            if (adapterView.getItemAtPosition(position).equals("choose hospital")) {
                // do nathing
            } else {
                hospital = "Hospital: " + adapterView.getItemAtPosition(position).toString();
            }

        } else if (adapterView.getId() == doctorSPN.getId()) {
            if (adapterView.getItemAtPosition(position).equals("choose doctor")) {
                // do nathing
            } else {
                doctor = "Doctor: " + adapterView.getItemAtPosition(position).toString();
            }

        } else if (adapterView.getId() == orderSPN.getId()) {
            if (adapterView.getItemAtPosition(position).equals("choose order")) {
                // do nathing
            } else {
                String q = quantityET.getText().toString();
                String item = adapterView.getItemAtPosition(position).toString();
                order = q + " " + item;
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == saveBTN.getId()){
            // Order
            stringOrder = convertToString(list);
            // Save on SQLite
            ContentValues cv = new ContentValues();
            cv.put("hospital" , hospital);
            cv.put("doctor" , doctor);
            cv.put("orrder" , Arrays.toString(stringOrder));
            cv.put("time" , "Time: " + timeET.getText().toString());

            mDatabase.insert("orderName" , null , cv);
            mAdapter.SwapCursor(getAllItem());
            list = new ArrayList<>();

        }else if (view.getId() == plusOrderBTN.getId()){
            list.add(order);
        }
    }

    private Cursor getAllItem(){
        return mDatabase.query("orderName" , null , null , null , null , null , null);
    }

    public static String[] convertToString(List<String> list){
        String[] result = new String[list.size()];
        Iterator iterator = list.iterator();
        for (int i = 0; i < result.length; i++) {
            result[i] = (String) iterator.next();
        }
        return result;
    }

    private void removeItem(long id){
        mDatabase.delete("orderName" , "id=" + id , null );
        mAdapter.SwapCursor(getAllItem());
    }

}