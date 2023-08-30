package fr.antotor.myapplication

data class Post(val userId: Int, val id: Int, val title: String, val body: String)

data class Todos(val userId: Int, val id: Int, val title: String, val completed: Boolean)