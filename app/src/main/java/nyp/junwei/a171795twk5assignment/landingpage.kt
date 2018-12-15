package nyp.junwei.a171795twk5assignment

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.media.Image
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_landingpage.*
import java.io.Serializable

class landingpage : AppCompatActivity() {

    lateinit var movieItem:LinearLayout
    var count: Int? = null       //must be null so getId works LINE 97

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landingpage)

        if (MovieCount.listOfMovies.size > 0) {
            val parentLayout: LinearLayout = findViewById(R.id.parentLayout)
            val constraintLayout: ConstraintLayout = findViewById(R.id.constraintLay)
            constraintLayout.visibility = View.GONE     //hide constraint layout if movie has been added

            //add linear layout
            val linearlayout = LinearLayout(this)
            linearlayout.orientation = LinearLayout.VERTICAL
            parentLayout.addView(linearlayout)

            var listOfMovies = MovieCount.listOfMovies      //get all movies from MovieCount class
            var z = 0
            while (z < MovieCount.listOfMovies.size) {
                var singleMovie = listOfMovies[z]       //one movie class object

                //one movie item
                val movieItem = LinearLayout(this)
                linearlayout.addView(movieItem)
                movieItem.orientation = LinearLayout.HORIZONTAL
                movieItem.layoutParams?.height = LinearLayout.LayoutParams.WRAP_CONTENT
                movieItem.layoutParams?.width = LinearLayout.LayoutParams.WRAP_CONTENT
                movieItem.setId(z)
                movieItem.isClickable = true
                //go to view details page when click on movie item
                //System.out.println(z)
//                movieItem.setOnClickListener {
//                    var movie = listOfMovies[z]
//                    var viewMovie = Intent(this, view_movie_detail::class.java)
//                    viewMovie.putExtra("movie", movie as Serializable)
//                    startActivity(viewMovie)
//                }
                //styling for movie item
                val border = GradientDrawable()
                border.setColor(-0x1)       //white background
                border.setStroke(1, -0x1000000)         //black border with full opacity
                movieItem.background = border

                //picture icon
                val pic = ImageView(this)
                movieItem.addView(pic)      //add pic to movie item
                pic.setImageResource(R.drawable.moviephoto)
                pic.layoutParams = LinearLayout.LayoutParams(150, 150)

                //relative layout inside movie item
                val relLay = RelativeLayout(this)
                movieItem.addView(relLay)       //add relative layout to movie item
                relLay.layoutParams?.height = LinearLayout.LayoutParams.MATCH_PARENT
                relLay.layoutParams?.width = LinearLayout.LayoutParams.MATCH_PARENT

                //text view
                val movieItemTitle = TextView(this)
                relLay.addView(movieItemTitle)
                movieItemTitle.setText(singleMovie.title)
                //movieItemTitle.setPadding(30, 50,0,50)
                movieItemTitle.layoutParams?.height = LinearLayout.LayoutParams.MATCH_PARENT
                movieItemTitle.layoutParams?.width = LinearLayout.LayoutParams.MATCH_PARENT
                movieItemTitle.gravity = Gravity.CENTER

                fun createOnClickEvent(v: View?){       //create on click event
                    movieItem.setOnClickListener {
                        var listOfMovies = MovieCount.listOfMovies
                        var fakeCount:Int? = v?.getId()     //get id of movie item
                        var count:Int = fakeCount!!     //converting to non null
                        var movie = listOfMovies[count]
                        var viewMovie = Intent(this, view_movie_detail::class.java)
                        viewMovie.putExtra("movie", movie as Serializable)
                        startActivity(viewMovie)
                    }
                }
                createOnClickEvent(movieItem)

//                movieItem.setOnClickListener(object : View.OnClickListener {
//                    override fun onClick(v: View) {
//                        val picture_intent = Intent(this, view_movie_detail::class.java)
//                        startActivity(picture_intent)
//                    }
//                })

                z += 1      //for the FOR loop

                registerForContextMenu(movieItem)       //context menu for edit
            }
        }

        registerForContextMenu(landingPageText)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)

        if (v?.id == R.id.landingPageText) {
            menu?.add(1, 1001, 1, "Add")
        } else {
            menu?.add(1, 2002, 1, "Edit")
            count = v?.getId()      //get id of movie item
        }
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {

        if (item?.itemId == 1001) {       //add movie context menu btn
            var addMovie = Intent(this, MainActivity::class.java)
            startActivity(addMovie)
        }
        if (item?.itemId == 2002) {         //edit movie context menu btn
            var editMovie = Intent(this, edit_movie::class.java)
            editMovie.putExtra("count", count)
            startActivity(editMovie)
        }

        return super.onContextItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.landingpage, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.landingAdd) {
            var addMovie = Intent(this, MainActivity::class.java)
            startActivity(addMovie)
        }

        return super.onOptionsItemSelected(item)
    }
}
