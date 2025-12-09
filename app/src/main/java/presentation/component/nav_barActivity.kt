package presentation.component

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.android_news_app.R
import android.widget.ImageView
import presentation.favorites.favouriteActivity
import presentation.home.homeActivity
import  presentation.profile.profileActivity

import android.content.Intent



class nav_barActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_bar)

        // Home
        findViewById<ImageView>(R.id.nav_dashboard).setOnClickListener {
            startActivity(Intent(this, homeActivity::class.java))
        }

        // Favourite
        findViewById<ImageView>(R.id.nav_love).setOnClickListener {
            startActivity(Intent(this, favouriteActivity::class.java))
        }

        // Account
        findViewById<ImageView>(R.id.nav_user).setOnClickListener {
            startActivity(Intent(this, profileActivity::class.java))
        }
    }
}