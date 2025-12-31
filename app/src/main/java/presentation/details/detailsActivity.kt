package presentation.details

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.android_news_app.R

class detailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_details)

        // Handle system insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Get data from intent
        val title = intent.getStringExtra("title")
        val subtitle = intent.getStringExtra("subtitle")
        val description = intent.getStringExtra("description")
        val imageUrl = intent.getStringExtra("image")

        // Set to views
        findViewById<android.widget.TextView>(R.id.newsTitle).text = title
        findViewById<android.widget.TextView>(R.id.newsSubtitle).text = subtitle
        findViewById<android.widget.TextView>(R.id.newsDescription).text = description
        Glide.with(this).load(imageUrl).into(findViewById(R.id.newsImage))

        // Back button
        findViewById<android.widget.ImageView>(R.id.btnBack).setOnClickListener { finish() }
    }
}
