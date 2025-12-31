package presentation.profile

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.android_news_app.R
import presentation.register.UserPref

class profileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val txtName = findViewById<TextView>(R.id.txtName)
        val txtEmail = findViewById<TextView>(R.id.txtEmail)

        val userPref = UserPref(this)
        val user = userPref.getUser()

        if (user != null) {
            txtName.text = user.name
            txtEmail.text = user.email
        } else {
            txtName.text = "No user found"
            txtEmail.text = ""
        }
    }
}