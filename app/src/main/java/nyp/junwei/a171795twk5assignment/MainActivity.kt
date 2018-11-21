package nyp.junwei.a171795twk5assignment

import nyp.junwei.a171795twk5assignment.Movie

import android.content.Context

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun Context.toast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    fun showChkbx(v:View){
        //show checkbox for "not suitable for all audience" chkbx
        if (chkbxNSFW.isChecked == true){
            if (chkbxVio.visibility == View.GONE || chkbxLang.visibility == View.GONE){
                chkbxVio.visibility = View.VISIBLE
                chkbxLang.visibility = View.VISIBLE
            }
        }
        else{
            if(chkbxVio.visibility == View.VISIBLE || chkbxLang.visibility == View.GONE){
                chkbxVio.visibility = View.GONE
                chkbxLang.visibility = View.GONE
            }
        }
    }

//    fun test(v:View){
//        Toast.makeText(this, "TEST", Toast.LENGTH_SHORT).show()
//    }

    fun btnValidate(v:View){
        var title: String
        var descrip : String
        var lang: String = ""
        var date: String
        var nsfw: Boolean
        var nsfwVio: Boolean = false
        var nsfwLang: Boolean = false

        title = movieName.text.toString()
        descrip = movieDescrip.text.toString()
        date = movieReleaseDate.text.toString()

        //check if fields are empty
        if(title.isEmpty()){
            movieName.setError("Please fill in the blanks")
        }
        if(descrip.isEmpty()){
            movieDescrip.setError("Please fill in the blanks")
        }
        if(date.isEmpty()){
            movieReleaseDate.setError("Please fill in the blanks")
        }
        if(rbtn1.isChecked == false && rbtn2.isChecked == false && rbtn3.isChecked == false && rbtn4.isChecked == false){
            rbtn4.setError("Please fill in the blanks")
        }

//        if(date.toRegex().matches("^\\d{2}/\\d{2}/\\d{2}$") == true){
//            System.out.println("REGEX WORKS")
//        }

        else{       //set language variable according to radiobutton
            if(rbtn1.isChecked) {
                lang = "English"
            }
            else if(rbtn2.isChecked){
                lang = "Chinese"
            }
            else if (rbtn3.isChecked){
                lang = "Malay"
            }
            else if (rbtn4.isChecked){
                lang = "Tamil"
            }

            if (chkbxNSFW.isChecked == false){
                nsfw = false
            }
            else{
                nsfw = true
                if(chkbxVio.isChecked){
                    nsfwVio = true
                }

                if(chkbxLang.isChecked){
                    nsfwLang = true
                }
            }
            var movie = Movie(title, descrip, lang, date, nsfw, nsfwVio, nsfwLang)

            if(nsfw == false){
                Toast.makeText(this, "Title = ${title} \n Overview = ${descrip} \n Release date = ${date} \n Language = ${lang} \n Not suitable for all audience = ${nsfw}", Toast.LENGTH_LONG).show()
            }
            else{
                if(nsfwVio == true && nsfwLang == true){
                    Toast.makeText(this, "Title = ${title} \n Overview = ${descrip} \n Release date = ${date} \n Language = ${lang} \n Not suitable for all audience = ${nsfw} \n Reason: \n Violence \n Language", Toast.LENGTH_LONG).show()
                }
                else if(nsfwVio == true && nsfwLang == false){
                    Toast.makeText(this, "Title = ${title} \n Overview = ${descrip} \n Release date = ${date} \n Language = ${lang} \n Not suitable for all audience = ${nsfw} \n Reason: \n Violence", Toast.LENGTH_LONG).show()
                }
                else if(nsfwVio == false && nsfwLang == true){
                    Toast.makeText(this, "Title = ${title} \n Overview = ${descrip} \n Release date = ${date} \n Language = ${lang} \n Not suitable for all audience = ${nsfw} \n Reason: \n Language", Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(this, "Title = ${title} \n Overview = ${descrip} \n Release date = ${date} \n Language = ${lang} \n Not suitable for all audience = ${nsfw} \n Reason: \n Not selected :)", Toast.LENGTH_LONG).show()
                }
            }

        }

    }
}
