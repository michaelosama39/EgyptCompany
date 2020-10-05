package com.example.egyptimportandexport.view.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SignInFragment extends Fragment {

    private FirebaseAuth authSignIn;
    EditText emailET , passwordET ;
    Button enterBTN;
    TextView forgetPasswordTV , newAccountTV ;

    public SignInFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        emailET = view.findViewById(R.id.email_ET_SignInFragment);
        passwordET = view.findViewById(R.id.password_ET_SignInFragment);
        enterBTN = view.findViewById(R.id.enter_BTN_SignInFragment);
        forgetPasswordTV = view.findViewById(R.id.forgetPassword_TV_SignInFragment);
        newAccountTV = view.findViewById(R.id.newAccount_TV_SignInFragment);
        authSignIn = FirebaseAuth.getInstance();

        enterBTN.setOnClickListener(new View.OnClickListener() {
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

                authSignIn.signInWithEmailAndPassword(email , password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getActivity(), "Loggin in Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity() , HomeActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(getActivity(), "error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        newAccountTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUpFragment fragment = new SignUpFragment();
                FragmentTransaction transactionsale = getFragmentManager().beginTransaction();
                transactionsale.replace(R.id.signInAndSignUpActivity_layout, fragment);
                transactionsale.commit();
            }
        });

        forgetPasswordTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText resetMail = new EditText(view.getContext());
                final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Reset Password");
                builder.setMessage("Enter Your Email To Received Reset Link.");
                builder.setView(resetMail);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String mail = resetMail.getText().toString();
                        authSignIn.sendPasswordResetEmail(mail).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(getActivity(), "Reset Link Sent To Your Email" , Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getActivity(), "Error ! Reset Link is Not Sent" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                builder.create().show();
            }
        });
        return view;
    }
}