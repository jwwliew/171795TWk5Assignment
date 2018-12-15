package nyp.junwei.a171795twk5assignment

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_rate_movie.*
import java.io.Serializable

class rate_movie : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rate_movie)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)     //back button on menu

        var movie = intent.extras.get("movie") as Movie?    //get intent
        ratingTitleText.text = "Enter your review of the movie: " + movie?.title.toString()     //set movie name in title based on name submitted on prev page
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.ratingmenu,menu)        //get rating menu instead of main menu

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.submit){
            getRating()         //when submit button in menu clicked, call getRating func
        }
        if(item?.itemId == android.R.id.home){
            var movie = intent.extras.get("movie") as Movie?    //get intent

            var backBtnIntent = Intent(applicationContext, MainActivity::class.java)
            backBtnIntent.putExtra("movie", movie as Serializable)

            setResult(Activity.RESULT_OK, backBtnIntent)
            this.finish()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    fun getRating(){
        var movie = intent.extras.get("movie") as Movie    //get intent

        var check:Boolean = false       //check if required fields are filled(only rating text box)

        if(ratingReviewText.text.isEmpty()){
            check = false
            ratingReviewText.setError("Please fill in this field")
        }
        else if(!ratingReviewText.text.isEmpty()){
            check = true
        }

        if(check == true){      //if fields filled in, start intent back to prev page
            movie?.rating = ratingBar.getRating().toDouble()
            movie?.ratingText = ratingReviewText.text.toString()

            MovieCount.addRating(movie.id, movie)       //replace movie at index
            //System.out.println(movie)

            var backToViewMovieDetailsIntent = Intent(this, view_movie_detail::class.java)
            backToViewMovieDetailsIntent.putExtra("movie", movie as Serializable)
            startActivity(backToViewMovieDetailsIntent)
        }
    }
}
