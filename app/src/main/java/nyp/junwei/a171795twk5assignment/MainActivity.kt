package nyp.junwei.a171795twk5assignment

import android.app.Activity
import nyp.junwei.a171795twk5assignment.Movie

import android.content.Context
import android.content.Intent

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        if(firstTimeRunning == false){
//            var movie = intent.extras.get("movie") as Movie?    //get intent
//            if (movie != null){     //when back button pressed at view movie details, if movie obj not empty, fill in fields with data
//                movieName.setText(movie.title)
//                movieDescrip.setText(movie.description)
//                movieReleaseDate.setText(movie.date)
//
//                if(movie.violence == true){     //set checkboxes
//                    chkbxVio.isChecked = true
//                    if(movie.nsfw == true){
//                        chkbxNSFW.isChecked = true
//                    }
//                    if(movie.lang == true){
//                        chkbxLang.isChecked = true
//                    }
//                }
//                if(movie.violence == false){
//                    chkbxVio.isChecked = false
//                    chkbxLang.isChecked = false
//                    chkbxNSFW.isChecked = false
//                }
//
//                //set radiobutton
//                if(movie.language == "English"){
//                    rbtn1.isChecked = true
//                }
//                if(movie.language == "Chinese"){
//                    rbtn2.isChecked = true
//                }
//                if(movie.language == "Malay"){
//                    rbtn3.isChecked = true
//                }
//                if(movie.language == "Tamil"){
//                    rbtn4.isChecked = true
//                }
//            }
//        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {       //receive startActivityOnResult (for back btn)
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1){
            if(resultCode == Activity.RESULT_OK){
                //var movie = intent.extras.get("movie") as Movie?    //get intent
                //movieName.setText(movie?.title)
                System.out.println("WORKS")
            }
        }
    }

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

    fun btnValidate(){
        var check: Boolean = false      //check if required fields are filled in

        var title: String
        var descrip : String
        var lang: String = ""
        var date: String
        var nsfw: Boolean = false
        var nsfwVio: Boolean = false
        var nsfwLang: Boolean = false

        title = movieName.text.toString()
        descrip = movieDescrip.text.toString()
        date = movieReleaseDate.text.toString()

        //check if fields are empty
        if(title.isEmpty()){
            movieName.setError("Please fill in the blanks")
            check = false
        }
        if(descrip.isEmpty()){
            movieDescrip.setError("Please fill in the blanks")
            check = false
        }
        if(date.isEmpty()){
            movieReleaseDate.setError("Please fill in the blanks")
            check = false
        }

//        if(date.toRegex().matches("^\\d{2}/\\d{2}/\\d{2}$") == true){
//            System.out.println("REGEX WORKS")
//        }

        if(!title.isEmpty() && !descrip.isEmpty() && !date.isEmpty()){
            check = true
            //set language variable according to radiobutton
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

            //set checkbox variables
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

            //SORRY FOR THE MESSY & INEFFICIENT CODES
            //different toast messages for different checkbox combinations
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
        }   //end of btnValidate

        if(check == true){      //if all required fields filled in, start intent to next page
            var movie = Movie(title, descrip, lang, date, nsfw, nsfwVio, nsfwLang, id=MovieCount.count)      //creating instance of Movie class

            MovieCount.addMovieToList(movie)        //add movie to arraylist in MovieCount

            var viewMovieDetailsIntent = Intent(this, view_movie_detail::class.java)
            viewMovieDetailsIntent.putExtra("movie", movie as Serializable)

            //startActivity(viewMovieDetailsIntent)

            //different start activity used because back button :)
            startActivityForResult(viewMovieDetailsIntent, 1)       //intent to view movie details
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main,menu)          //inflate main menu

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.add){
            btnValidate()       //if add button in menu clicked, btnValidate function called
        }
        if(item?.itemId == R.id.clear){        //if clear button in menu clicked, clear entries
            movieName.setText("")
            movieDescrip.setText("")
            movieReleaseDate.setText("")

            rbtn1.setChecked(true)
            rbtn2.setChecked(false)
            rbtn3.setChecked(false)
            rbtn4.setChecked(false)

            chkbxNSFW.setChecked(false)
            chkbxLang.setChecked(false)
            chkbxVio.setChecked(false)
        }
        if(item?.itemId == R.id.goToLandingPage){      //go to landing page menu button
            var backToLandingPage = Intent(this, landingpage::class.java)
            startActivity(backToLandingPage)
        }

        return super.onOptionsItemSelected(item)
    }
}
