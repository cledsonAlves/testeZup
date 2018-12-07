package br.com.testezup.extensions

import android.app.Activity
import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

// mostra um toast
fun Activity.toast(message: CharSequence, length: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, message, length).show()

fun Activity.toast(@StringRes message: Int, length: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, message, length).show()


// Configura o toolbar
fun AppCompatActivity.setupToolbar(
    @IdRes id: Int, title: String? = null,
    upNavigation: Boolean = false
): ActionBar {
    val toolbar = findViewById<Toolbar>(id)
    setSupportActionBar(toolbar)
    if (title != null) {
        supportActionBar?.title = title
    }
    supportActionBar?.setDisplayHomeAsUpEnabled(upNavigation)
    return supportActionBar!!
}

// Adciona o fragment ao layout
fun AppCompatActivity.addFragment(@IdRes layoutId: Int, fragment: Fragment){
    fragment.arguments = intent.extras
    val ft = supportFragmentManager.beginTransaction()
    ft.add(layoutId, fragment)
    ft.commit()
}
// converte JSON para objeto
inline fun <reified T> Any.fromJson(json: String): T{
    val type = object: TypeToken<T>(){}.type
    val gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    return  gson.fromJson<T>(json,type)
}


// findViewById + setOnclickListener
fun AppCompatActivity.onClick(@IdRes viewId: Int, onClick: (v: android.view.View?) -> Unit) {
    val view = findViewById<View>(viewId)
    view.setOnClickListener {
        onClick(it)
    }

    fun Any.toJson(prettyPrinting: Boolean = false): String{
        val builder = GsonBuilder()
        if (prettyPrinting){
            builder.setPrettyPrinting()
        }
        val json = builder.create().toJson(this)
        return json
    }



}