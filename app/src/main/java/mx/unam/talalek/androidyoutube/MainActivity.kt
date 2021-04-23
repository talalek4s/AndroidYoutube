package mx.unam.talalek.androidyoutube

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

class MainActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {



    val claveYoutube = "AIzaSyApiBqATKcWFENJ_5i2OBDIpjfzt8QVnAA"
    lateinit var youtubePlayerView: YouTubePlayerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val idVideo:String = findViewById<EditText>(R.id.et_youtube).text.toString()
        val boton = findViewById<Button>(R.id.btn_youtube)
        Log.d("id: ", idVideo)
        boton.setOnClickListener {
            if (idVideo == null ){
                Toast.makeText(this, "No escribio correctamente el id del video", Toast.LENGTH_LONG).show()

            }else{
                youtubePlayerView = findViewById(R.id.youtube_view)
                youtubePlayerView.initialize(claveYoutube, this)
            }
        }



    }

    override fun onInitializationSuccess(

            p0: YouTubePlayer.Provider?,
            p1: YouTubePlayer?,
            p2: Boolean) {

       val idVideo = findViewById<EditText>(R.id.et_youtube).text
        Log.d("IDENTIFICADOR: ", idVideo.toString())
        Toast.makeText(this, idVideo, Toast.LENGTH_LONG).show()

        if (!p2){

                p1?.cueVideo(idVideo.toString())
        }
    }

    override fun onInitializationFailure(
            p0: YouTubePlayer.Provider?,
            p1: YouTubeInitializationResult?) {
        if(p1!!.isUserRecoverableError){
            p1.getErrorDialog(this,1).show()
        }else{
            Toast.makeText(this, "Error al cargar ", Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
    fun getYoutubePlayerProvider():YouTubePlayer.Provider{
        return youtubePlayerView

    }
}