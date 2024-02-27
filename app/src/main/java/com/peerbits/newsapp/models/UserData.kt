package com.peerbits.newsapp.models

data class LoginResponse(
    val status: String,
    val message: String,
    val data: LoginData
)

data class LoginData(
    val access: String,
    val refresh: String,
    val user: UserData
)

data class UserData(
    val id: Int,
    val username: String,
    val email: String,
    val mobile_number: String,
    val first_name: String,
    val last_name: String,
    val role: String,
    val gender: String,
    val grade: GradeData?,
    val dob: String,
    val section: String,
    val total_gems: Int,
    val rank: Int,
    val daily_challenge: Boolean,
    val qualification: String?,
    val is_superteacher: Boolean?,
    val institute: String?,
    val photo: String?,
    val avatar: String?,
    val thumbnail: String?,
    val has_completed_profile: Boolean,
    val status: String,
    val is_active: Boolean,
    val is_deleted: Boolean,
    val badge: String?
)

data class GradeData(
    val id: Int?,
    val name: String?
)

data class LoginRequest(
    val username: String,
    val password: String
)
