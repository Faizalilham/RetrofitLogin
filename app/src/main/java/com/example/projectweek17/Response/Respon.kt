package com.example.projectweek17.Response

data class ListRespon<T> (
    var msg: String,
    var status : Int,
    var data : List<T>
        )
data class SingleRespon<T> (
    var msg: String,
    var status : Int,
    var data : T
)