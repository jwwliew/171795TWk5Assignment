package nyp.junwei.a171795twk5assignment

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_view_movie_detail.*

import nyp.junwei.a171795twk5assignment.Movie
import java.io.Serializable
import android.support.v4.app.NavUtils
import android.view.Menu


class view_movie_detail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_movie_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)     //back button on menu

        //actionBar?.setDisplayHomeAsUpEnabled(true)        //old syntax??

        registerForContextMenu(reviewText)

        //get intent data
        var movie = intent.extras.get("movie") as Movie?

        //set text views with data submitted from prev page
        if (movie != null) {
            detailTitle.text = movie.title
            detailOverview.text = movie.description
            detailLang.text = movie.language
            detailDate.text = movie.date

            if (movie.nsfw == false) {
                displayDetailNSFW.text = "Yes"
            } else if (movie.nsfw == true) {
                displayDetailNSFW.text = "No"

                if (movie.lang == true) {
                    displayDetailNSFWLang.text = " (Language)"
                }

                if (movie.violence == true) {
                    displayDetailNSFWVio.text = " (Violence)"
                }
            }

            if (movie.rating != 0.0 && movie.ratingText != "") {         //check if rating form has been submitted
                //System.out.println(movie.rating)
                ratingBarDisplay.setRating(movie.rating.toFloat())
                ratingBarDisplay.visibility = View.VISIBLE
                reviewText.text = movie.ratingText
            } else {       //if no review added, default text displayed
                reviewText.text = "No Reviews yet. \n Long press here to add your review"
            }
        }
    }

//    override fun onSupportNavigateUp(): Boolean {
//        finish()
//        return true
//    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {       //receive startActivityOnResult (for back btn)
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                //var movie = intent.extras.get("movie") as Movie?    //get intent
                //movieName.setText(movie?.title)
                System.out.println("2 WORKS")
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.goToLandingPage) {       //go to landing page menu button
            var movie = intent.extras.get("movie") as Movie?    //get intent
            var backToLandingPage = Intent(this, landingpage::class.java)
            backToLandingPage.putExtra("movie", movie as Serializable)
            startActivity(backToLandingPage)
        }
        if (item?.itemId == android.R.id.home) {
            var movie = intent.extras.get("movie") as Movie?    //get intent

            var backBtnIntent = Intent(applicationContext, MainActivity::class.java)
            backBtnIntent.putExtra("movie", movie as Serializable)
            //startActivityForResult(backBtnIntent, 1)

            setResult(Activity.RESULT_OK, backBtnIntent)
            this.finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onContextItemSelected(item: MenuItem?): Boolean {
        var movie = intent.extras.get("movie") as Movie?    //get intent
        if (item?.itemId == 2001) {     //add rating review context btn
            var addReview = Intent(this, rate_movie::class.java)
            addReview.putExtra("movie", movie as Serializable)
            startActivityForResult(addReview, 2)
        }

        return super.onContextItemSelected(item)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)

        if (v?.id == R.id.reviewText) {
            menu?.add(1, 2001, 1, "Add Review")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.viewmoviedetails,menu)
        return super.onCreateOptionsMenu(menu)
    }
}
