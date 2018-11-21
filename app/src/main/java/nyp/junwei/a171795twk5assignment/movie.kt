package nyp.junwei.a171795twk5assignment

class Movie(title:String = "Venom", description:String = "When Eddie Brock acquires the new powers of a symbiote, he will have to release his alter-ego Venom to save his life",
            language:String = "English", date:String = "03-10-2018", nsfw:Boolean = false, violence:Boolean = false, lang:Boolean = false){

    var title: String
    var description: String
    var language: String
    var date: String
    var nsfw: Boolean
    var violence: Boolean
    var lang: Boolean

    init{
        this.title = title
        this.description = description
        this.language = language
        this.date = date
        this.nsfw = nsfw
        this.violence = violence
        this.lang = lang
    }

}