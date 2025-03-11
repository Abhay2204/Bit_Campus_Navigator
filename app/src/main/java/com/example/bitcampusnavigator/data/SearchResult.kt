package com.example.bitcampusnavigator.data
data class SearchResult(
    val buildingId: Int,
    val buildingName: String,
    val floorNumber: Int,
    val roomName: String,
    val roomId: String, // Room ID
    val roomPosition: Int // Optional, not used in this example
)


