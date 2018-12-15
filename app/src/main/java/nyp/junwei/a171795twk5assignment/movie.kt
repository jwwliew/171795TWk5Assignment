package nyp.junwei.a171795twk5assignment

import androidx.versionedparcelable.VersionedParcelize
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

//@Parcelize
@VersionedParcelize

class Movie(title:String, description:String, language:String, date:String, nsfw:Boolean, violence:Boolean, lang:Boolean, rating:Double = 0.0, ratingText:String="", id:Int=0):Serializable{

    var title: String = "Venom"
    var description: String = "When Eddie Brock acquires the new powers of a symbiote, he will have to release his alter-ego Venom to save his life"
    var language: String = "English"
    var date: String = "03-10-2018"
    var nsfw: Boolean= false
    var violence: Boolean = false
    var lang: Boolean = false
    var rating: Double = 0.0
    var ratingText: String = ""
    var id:Int = 0

    init{
        this.title = title
        this.description = description
        this.language = language
        this.date = date
        this.nsfw = nsfw
        this.violence = violence
        this.lang = lang
        this.rating = rating
        this.ratingText = ratingText
        this.id = id
    }

    override fun toString(): String {
        return "Movie(id=$id, title='$title', description='$description', language='$language', date='$date', nsfw=$nsfw, violence=$violence, lang=$lang, rating=$rating, ratingText='$ratingText')"
    }

}