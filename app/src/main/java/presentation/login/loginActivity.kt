package presentation.login

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
import presentation.register.UserPref


class loginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val userPref = UserPref(this)

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener {
            val email = findViewById<EditText>(R.id.editEmail).text.toString().trim()
            val password = findViewById<EditText>(R.id.editPassword).text.toString().trim()

            val  savedUser = userPref.getUser()

            if(savedUser == null){
                Toast.makeText(this, "No user found", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(email == savedUser.email && password == savedUser.password){
                Toast.makeText(this, "Login Successfull", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, nav_barActivity::class.java))
                finish()
            }else{
                Toast.makeText(this, "Invalid User name or Password", Toast.LENGTH_SHORT).show()
            }
        }

    }
}