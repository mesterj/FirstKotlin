package tries.joco.kite.com.firstkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showToast("Próba")
    }

    fun showToast(s: String) {
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show()
    }
}
