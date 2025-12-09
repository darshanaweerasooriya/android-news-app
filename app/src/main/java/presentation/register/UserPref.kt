package presentation.register

import android.content.Context
import android.content.SharedPreferences

data class User(val name: String, val email: String, val password: String)



class UserPref(context: Context) {
    private val sharepref: SharedPreferences =
        context.getSharedPreferences ("user_pref", Context.MODE_PRIVATE)

    fun saveUser(user: User) {
        val editor = sharepref.edit()
        editor.putString("name", user.name)
        editor.putString("email", user.email)
        editor.putString("password", user.password)
        editor.apply()

    }

    fun getUser(): User? {
        val name = sharepref.getString("name", null)
        val email = sharepref.getString("email", null)
        val password = sharepref.getString("password", null)
        return if (name != null && email != null && password != null){
            User(name, email, password)
        }else{
            null
        }
    }

    fun clearUser() {
        sharepref.edit().clear().apply()
    }
}