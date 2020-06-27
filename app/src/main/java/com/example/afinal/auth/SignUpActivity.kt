package com.example.afinal.auth

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.afinal.R
import kotlinx.android.synthetic.main.activity_signup.*
import java.util.regex.Matcher
import java.util.regex.Pattern


class SignUpActivity : AppCompatActivity() {

    var displayStatus = true

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_signup)

        signInBtn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java)) // Open Sign Up Page
            overridePendingTransition(R.anim.fadeout, R.anim.fadein) // Animation for switching layouts
        }

        signUpPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if(!passwordValidation(s)) {
                    passStrength.text = "Poor"
                    passStrength.setTextColor(Color.parseColor("#ec583f"))
                } else {
                    passStrength.text = "Strong"
                    passStrength.setTextColor(Color.parseColor("#39c26d"))
                }
                if (s.isNotEmpty()) {
                    passStrengthText.text = "Password Strength: "
                } else {
                    passStrengthText.text = ""
                    passStrength.text = ""
                }
            }
        })

        signUpPassword.setOnTouchListener(View.OnTouchListener { _, event ->
            val drawableRight = 2
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= signUpPassword.right - signUpPassword.compoundDrawables[drawableRight].bounds.width()
                ) {
                    if (displayStatus) {
                        signUpPassword.transformationMethod = null // Show
                        signUpPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_remove_red_eye_24_active, 0);
                        signUpConfirmPassword.transformationMethod = null // Show
                        signUpConfirmPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_remove_red_eye_24_active, 0);
                        displayStatus = false
                    } else {
                        signUpPassword.transformationMethod = PasswordTransformationMethod() // Hide
                        signUpPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_remove_red_eye_24_default, 0);
                        signUpConfirmPassword.transformationMethod = PasswordTransformationMethod() // Hide
                        signUpConfirmPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_remove_red_eye_24_default, 0);
                        displayStatus = true
                    }
                }
            }
            false
        })

        signUpConfirmPassword.setOnTouchListener(View.OnTouchListener { _, event ->
            val drawableRight = 2
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= signUpConfirmPassword.right - signUpConfirmPassword.compoundDrawables[drawableRight].bounds.width()
                ) {
                    if (displayStatus) {
                        signUpPassword.transformationMethod = null // Show
                        signUpPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_remove_red_eye_24_active, 0);
                        signUpConfirmPassword.transformationMethod = null // Show
                        signUpConfirmPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_remove_red_eye_24_active, 0);
                        displayStatus = false
                    } else {
                        signUpPassword.transformationMethod = PasswordTransformationMethod() // Hide
                        signUpPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_remove_red_eye_24_default, 0);
                        signUpConfirmPassword.transformationMethod = PasswordTransformationMethod() // Hide
                        signUpConfirmPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_remove_red_eye_24_default, 0);
                        displayStatus = true
                    }
                }
            }
            false
        })


    }
    fun passwordValidation(password: CharSequence): Boolean {
        val pattern: Pattern
        val matcher: Matcher
        val passwordPattern =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,}$"
        pattern = Pattern.compile(passwordPattern)
        matcher = pattern.matcher(password)
        return matcher.matches()
    }

}