package com.example.i_am_open

enum class TutorialType {
    VIDEO, READABLE
}
data class TutorialModel(var id: Int, var title: String, var image:String , var description : String, var isVideo : Boolean , var productId :Int  ){

}
