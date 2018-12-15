package nyp.junwei.a171795twk5assignment

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable

class edit_movie : AppCompatActivity() {

    var listOfMovies = MovieCount.listOfMovies      //get all movies from MovieCount class
    var selectedMovieId:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_movie)

        selectedMovieId = intent.getIntExtra("count", 0)

        var selectedMovie = listOfMovies.get(selectedMovieId)
        //System.out.println(selectedMovie.title)
        movieName.setText(selectedMovie.title)
        movieDescrip.setText(selectedMovie.description)
        movieReleaseDate.setText(selectedMovie.date)

        if (selectedMovie.language == "English") {
            rbtn1.isChecked = true
        }
        if (selectedMovie.language == "Chinese") {
            rbtn2.isChecked = true
        }
        if (selectedMovie.language == "Malay") {
            rbtn3.isChecked = true
        }
        if (selectedMovie.language == "Tamil") {
            rbtn4.isChecked = true
        }

        if (selectedMovie.nsfw == true) {
            chkbxNSFW.isChecked = true
            chkbxVio.visibility = View.VISIBLE
            chkbxLang.visibility = View.VISIBLE

            if (selectedMovie.lang == true) {
                chkbxLang.isChecked = true
            }
            if (selectedMovie.violence == true) {
                chkbxVio.isChecked = true
            }
            if (selectedMovie.lang == false) {
                chkbxLang.isChecked = false
            }
            if (selectedMovie.violence == false) {
                chkbxVio.isChecked = false
            }
        }

        if (selectedMovie.nsfw == false) {
            chkbxNSFW.isChecked = false

            if (selectedMovie.lang == true) {
                chkbxLang.isChecked = false
            }
            if (selectedMovie.violence == true) {
                chkbxVio.isChecked = false
            }
            if (selectedMovie.lang == false) {
                chkbxLang.isChecked = false
            }
            if (selectedMovie.violence == false) {
                chkbxVio.isChecked = false
            }
        }

    }

    fun showChkbx(v: View) {
        //show checkbox for "not suitable for all audience" chkbx
        if (chkbxNSFW.isChecked == true) {
            if (chkbxVio.visibility == View.GONE || chkbxLang.visibility == View.GONE) {
                chkbxVio.visibility = View.VISIBLE
                chkbxLang.visibility = View.VISIBLE
            }
        } else {
            if (chkbxVio.visibility == View.VISIBLE || chkbxLang.visibility == View.GONE) {
                chkbxVio.visibility = View.GONE
                chkbxLang.visibility = View.GONE
            }
        }
    }

    fun btnValidate() {
        var check: Boolean = false      //check if required fields are filled in

        var title: String
        var descrip: String
        var lang: String = ""
        var date: String
        var nsfw: Boolean = false
        var nsfwVio: Boolean = false
        var nsfwLang: Boolean = false

        title = movieName.text.toString()
        descrip = movieDescrip.text.toString()
        date = movieReleaseDate.text.toString()

        //check if fields are empty
        if (title.isEmpty()) {
            movieName.setError("Please fill in the blanks")
            check = false
        }
        if (descrip.isEmpty()) {
            movieDescrip.setError("Please fill in the blanks")
            check = false
        }
        if (date.isEmpty()) {
            movieReleaseDate.setError("Please fill in the blanks")
            check = false
        }

        if (!title.isEmpty() && !descrip.isEmpty() && !date.isEmpty()) {
            check = true
            //set language variable according to radiobutton
            if (rbtn1.isChecked) {
                lang = "English"
            } else if (rbtn2.isChecked) {
                lang = "Chinese"
            } else if (rbtn3.isChecked) {
                lang = "Malay"
            } else if (rbtn4.isChecked) {
                lang = "Tamil"
            }

            //set checkbox variables
            if (chkbxNSFW.isChecked == false) {
                nsfw = false
            } else {
                nsfw = true
                if (chkbxVio.isChecked) {
                    nsfwVio = true
                }

                if (chkbxLang.isChecked) {
                    nsfwLang = true
                }
            }
        }

        if (check == true) {      //if all required fields filled in, start intent to next page
            var movie = Movie(title, descrip, lang, date, nsfw, nsfwVio, nsfwLang)      //creating instance of Movie class

            listOfMovies.add(selectedMovieId, movie)
            listOfMovies.removeAt(selectedMovieId+1)

            var saveEditMovie = Intent(this, landingpage::class.java)
            startActivity(saveEditMovie)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.editmovie, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.save) {
            btnValidate()
        }
        if (item?.itemId == R.id.cancel) {
            var goingback = Intent(this, landingpage::class.java)
            startActivity(goingback)
        }

        return super.onOptionsItemSelected(item)
    }
}
