package presentation.register

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.android_news_app.R
import presentation.component.nav_barActivity
import presentation.login.loginActivity

class registerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val userPref = UserPref(this)

        val btnRegister = findViewById<Button>(R.id.btnRegister)
        btnRegister.setOnClickListener {
            val name = findViewById<EditText>(R.id.editName).text.toString().trim()
            val email = findViewById<EditText>(R.id.editEmail).text.toString().trim()
            val password = findViewById<EditText>(R.id.editPassword).text.toString().trim()
            val confirmPassword = findViewById<EditText>(R.id.editPasswordConfirm).text.toString().trim()

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            userPref.saveUser(User(name, email, password))
            Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show()


            startActivity(Intent(this, nav_barActivity::class.java))
            finish()
        }

//        val loginBtn = findViewById<Button>(R.id.loginBtn)
//        loginBtn.setOnClickListener {
//            startActivity(Intent(this, loginActivity::class.java))
//            finish()
//        }
    }

}