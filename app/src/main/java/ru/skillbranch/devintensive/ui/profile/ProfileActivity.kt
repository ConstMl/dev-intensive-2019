package ru.skillbranch.devintensive.ui.profile

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.textfield.TextInputLayout
import org.w3c.dom.Text
import ru.skillbranch.devintensive.R
import ru.skillbranch.devintensive.models.Bender
import ru.skillbranch.devintensive.models.Profile
import ru.skillbranch.devintensive.viewmodels.ProfileViewModel
import java.util.*

class ProfileActivity : AppCompatActivity() {

    companion object {
        const val IS_EDIT_MODE = "IS_EDIT_MODE"
    }

    var isEditMode = false
    lateinit var viewFields: Map<String, TextView>
    private lateinit var viewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        //TODO set custom theme this before super and setContentView
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        initViewModel()
        initViews(savedInstanceState)
        Log.d("M_MainActivity", "onCreate")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(IS_EDIT_MODE, isEditMode)
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        viewModel.getProfileData().observe(this, androidx.lifecycle.Observer { updateUI(it) })
        viewModel.getTheme().observe(this, androidx.lifecycle.Observer { updateTheme(it) })
    }

    private fun updateTheme(mode: Int) {
        Log.d("M_ProfileActivity", "updateTheme")
        delegate.localNightMode = mode
    }

    private fun updateUI(profile: Profile?) {
        profile?.toMap().also {
            for ((k,v) in viewFields) {
                v.text = it?.get(k).toString()
            }
        }
    }

    @SuppressLint("WrongViewCast")
    private fun initViews(savedInstanceState: Bundle?) {
        viewFields = mapOf(
            "nickName" to findViewById(R.id.tv_nick_name),
            "rank" to findViewById(R.id.tv_rank),
            "firstName" to findViewById(R.id.et_first_name),
            "lastName" to findViewById(R.id.et_last_name),
            "about" to findViewById(R.id.et_about),
            "repository" to findViewById(R.id.et_repository),
            "rating" to findViewById(R.id.tv_rating),
            "respect" to findViewById(R.id.tv_respect)
        )

        isEditMode = savedInstanceState?.getBoolean(IS_EDIT_MODE, false) ?: false
        showCurrentMode(isEditMode)

        var btnEdit = findViewById<ImageButton>(R.id.btn_edit)
        btnEdit.setOnClickListener {
            if (isEditMode) saveProfileInfo()
            isEditMode = !isEditMode
            showCurrentMode(isEditMode)
        }
        var btnSwitchTheme = findViewById<ImageButton>(R.id.btn_switch_theme)
        btnSwitchTheme.setOnClickListener {
            viewModel.switchTheme()
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun showCurrentMode(isEdit: Boolean) {
        var info = viewFields.filter {
            setOf(
                "firstName",
                "lastName",
                "about",
                "repository"
            ).contains(it.key)
        }
        for ((_, v) in info) {
            v as EditText
            v.isFocusable = isEdit
            v.isFocusableInTouchMode = isEdit
            v.isEnabled = isEdit
            v.background.alpha = if (isEdit) 255 else 0 //TODO not work
        }

        var icEye = findViewById<ImageView>(R.id.ic_eye)
        icEye.visibility = if (isEdit) View.GONE else View.VISIBLE
        var wrAbout = findViewById<TextInputLayout>(R.id.wr_about)
        wrAbout.isCounterEnabled = isEdit

        var btnEdit = findViewById<ImageButton>(R.id.btn_edit)
        with(btnEdit) {
            val filter: ColorFilter? = if (isEdit) {
                PorterDuffColorFilter(
                    resources.getColor(R.color.color_accent, theme),
                    PorterDuff.Mode.SRC_IN
                )
            } else {
                null
            }

            val icon = if (isEdit) {
                resources.getDrawable(R.drawable.ic_baseline_save_24, theme)
            } else {
                resources.getDrawable(R.drawable.ic_baseline_edit_black_24, theme)
            }

            background.colorFilter = filter
            setImageDrawable(icon)
        }
    }

    private fun saveProfileInfo() {
        Profile(
            firstName = findViewById<EditText>(R.id.et_first_name).text.toString(),
            lastName = findViewById<EditText>(R.id.et_last_name).text.toString(),
            about = findViewById<EditText>(R.id.et_about).text.toString(),
            repository = findViewById<EditText>(R.id.et_repository).text.toString()
        ).apply {
            viewModel.saveProfileData(this)
        }
    }
}