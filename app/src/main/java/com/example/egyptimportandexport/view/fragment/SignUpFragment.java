package com.example.egyptimportandexport.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.egyptimportandexport.R;
import com.example.egyptimportandexport.view.activity.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpFragment extends Fragment {

    FirebaseAuth authSignUp;
    EditText emailET, passwordET;
    Button saveBTN;
    TextView haveAccountTV;

    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        authSignUp = FirebaseAuth.getInstance();
        emailET = view.findViewById(R.id.email_ET_SignUpFragment);
        passwordET = view.findViewById(R.id.password_ET_SignUpFragment);
        saveBTN = view.findViewById(R.id.save_BTN_SignUpFragment);
        haveAccountTV = view.findViewById(R.id.haveAccount_TV_SignInFragment);

        saveBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailET.getText().toString();
                String password = passwordET.getText().toString();

                if (TextUtils.isEmpty(email)){
                    emailET.setError("Email is Required");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    passwordET.setError("password is Required");
                    return;
                }
                if (password.length() < 6){
                    passwordET.setError("Password Must be >= 6 Characters");
                    return;
                }
                authSignUp.createUserWithEmailAndPassword(email , password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getActivity(), "User Created", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity() , HomeActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(getActivity(), "error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        haveAccountTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignInFragment fragment = new SignInFragment();
                FragmentTransaction transactionsale = getFragmentManager().beginTransaction();
                transactionsale.replace(R.id.signInAndSignUpActivity_layout, fragment);
                transactionsale.commit();
            }
        });
        return view;
    }
}