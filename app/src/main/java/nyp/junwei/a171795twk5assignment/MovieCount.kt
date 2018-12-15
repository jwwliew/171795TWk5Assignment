package nyp.junwei.a171795twk5assignment

import androidx.versionedparcelable.VersionedParcelize
import java.io.Serializable

//@Parcelize
@VersionedParcelize

class MovieCount{
    companion object test{
        var count = 0
        var listOfMovies = arrayListOf<Movie>()
        fun addMovieToList(movie: Movie){
            count+=1
            listOfMovies.add(movie)
        }

        fun addRating(index:Int, movie:Movie){      //add movie with rating by replacing
            listOfMovies.set(index, movie)
        }
    }
}