package presentation.register



import android.content.Context
import android.content.SharedPreferences

class UserPref(context: Context) {

    private val sharepref: SharedPreferences =
        context.getSharedPreferences("user_pref", Context.MODE_PRIVATE)

    fun saveUser(user: User) {
        sharepref.edit()
            .putString("name", user.name)
            .putString("email", user.email)
            .putString("password", user.password)
            .apply()
    }

    fun getUser(): User? {
        val name = sharepref.getString("name", null)
        val email = sharepref.getString("email", null)
        val password = sharepref.getString("password", null)

        return if (name != null && email != null && password != null) {
            User(name, email, password)
        } else {
            null
        }
    }

    fun clearUser() {
        sharepref.edit().clear().apply()
    }
}
