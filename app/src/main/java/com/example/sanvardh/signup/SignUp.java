package com.example.sanvardh.signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.sanvardh.MainActivity;
import com.example.sanvardh.R;
import com.example.sanvardh.signin.SignIn;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class SignUp extends AppCompatActivity {

    private EditText usNameSignUp,emailSignUp,passwordSignUp,numberSignUp;
    private Button nextBtn;
    private String userName,email,password,number;
    private LinearLayout inputLayout;

    private EditText otpSignUp;
    private Button otpBtn;
    private LinearLayout otpLayout;
    private String otp;

    private String verificationCodeBySystem;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private static final String TAG = "PhoneAuthActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        usNameSignUp = findViewById(R.id.usnameSignUp);
        emailSignUp = findViewById(R.id.emailSignUp);
        passwordSignUp = findViewById(R.id.passwordSignUp);
        numberSignUp = findViewById(R.id.numberSignUp);

        inputLayout = findViewById(R.id.inputLayout);
        otpLayout = findViewById(R.id.otpLayout);

        nextBtn = findViewById(R.id.nextBtn);

        otpSignUp = findViewById(R.id.otpSignUp);
        otpBtn = findViewById(R.id.otpBtn);

        mAuth = FirebaseAuth.getInstance();

        final Intent intent = new Intent(this, MainActivity.class);

        //---------------------------------------------------------------------------------------------------------------------------------------------------------------
            nextBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    userName = usNameSignUp.getText().toString().trim();
                    email = emailSignUp.getText().toString().trim();
                    password = passwordSignUp.getText().toString().trim();
                    number = "+91" + numberSignUp.getText().toString().trim();


                    inputLayout.setVisibility(View.GONE);
                    otpLayout.setVisibility(View.VISIBLE);


                    sendOTPCodeToUser(number);

                }
            });

            //------------------------------------------------------------------------------------------------------------------------------------------------------------
                otpBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        otp = otpSignUp.getText().toString().trim();

                        createUser();
                    }

                    private void createUser() {

                        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                            @Override
                            public void onVerificationCompleted(PhoneAuthCredential credential) {

                                signInWithPhoneAuthCredential(credential);
                            }

                            @Override
                            public void onVerificationFailed(FirebaseException e) {
                                // This callback is invoked in an invalid request for verification is made,
                                // for instance if the the phone number format is not valid.
                                Log.w(TAG, "onVerificationFailed", e);

                                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                                    // Invalid request
                                } else if (e instanceof FirebaseTooManyRequestsException) {
                                    // The SMS quota for the project has been exceeded
                                }

                                // Show a message and update the UI
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationId,
                                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                                // The SMS verification code has been sent to the provided phone number, we
                                // now need to ask the user to enter the code and then construct a credential
                                // by combining the code with a verification ID.

                                // Save verification ID and resending token so we can use them later
                                mVerificationId = verificationId;
                                mResendToken = token;
                            }
                        };



                    }
                });

         //---------------------------------------------------------------------------------------------------------------------------------------------------------------
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.


                signInWithPhoneAuthCredential(credential);

                startActivity(intent);

            }

            private void verifyPhoneNumberWithCode(String verificationId, String code) {
                // [START verify_with_code]
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
                // [END verify_with_code]
            }



            @Override
            public void onVerificationFailed(FirebaseException e) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.


                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                }

                // Show a message and update the UI
            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.


                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;
            }
        };
    }



    @Override
    protected void onStart() {
        super.onStart();

        //checkCurrentUser();
    }

    private void verifyPhoneNumberWithCode(String verificationId, String code) {
        // [START verify_with_code]
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        // [END verify_with_code]
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = task.getResult().getUser();
                            addData();
                            // Update UI
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }


    private void sendOTPCodeToUser(String number) {


        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(number)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }


    public void addData(){

        HashMap<String, Object> link = new HashMap<>();
        link.put( "username", userName );
        link.put( "email" , email );
        link.put( "password" , password );
        link.put( "mobNo" , number );


        FirebaseDatabase.getInstance().getReference( number.toString().trim() )
                .setValue(link)
                .addOnCompleteListener( new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        startActivity( new Intent( SignUp.this, SignIn.class ) );
                        finish();

                    }
                } ).addOnFailureListener( new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        } );
    }

}