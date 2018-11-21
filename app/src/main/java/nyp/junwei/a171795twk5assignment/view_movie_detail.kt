package nyp.junwei.a171795twk5assignment

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_view_movie_detail.*

import nyp.junwei.a171795twk5assignment.Movie

class view_movie_detail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_movie_detail)

        var venom = Movie()
        detailTitle.text = venom.title
        detailOverview.text = venom.description
        detailLang.text = venom.language
        detailDate.text = venom.date
        detailNSFW.text = "Yes"
    }

}
