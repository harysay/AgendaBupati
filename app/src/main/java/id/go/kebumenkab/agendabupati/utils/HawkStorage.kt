package id.go.kebumenkab.agendabupati.utils

import android.content.Context
import com.orhanobut.hawk.Hawk
import id.go.kebumenkab.agendabupati.model.ResponseUser

class HawkStorage {
    fun setUser(user: ResponseUser){
        Hawk.put(USER_KEY, user)
    }

    fun getUser(): ResponseUser{
        return Hawk.get(USER_KEY)
    }


    fun isLogin(): Boolean{
        if (Hawk.contains(USER_KEY)){
            return true
        }
        return false
    }

    fun deleteAll(){
        Hawk.deleteAll()
    }

    companion object{
        private const val USER_KEY = "user_keyeletter"
        private val hawkStorage = HawkStorage()

        fun instance(context: Context?): HawkStorage{
            Hawk.init(context).build()
            return hawkStorage
        }
    }
}