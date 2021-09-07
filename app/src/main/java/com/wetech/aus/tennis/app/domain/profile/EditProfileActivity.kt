package com.wetech.aus.tennis.app.domain.profile

import android.graphics.Color
import coil.load
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.sdk.android.oss.OSSClient
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.blankj.utilcode.util.TimeUtils
import com.google.gson.Gson
import com.jakewharton.rxbinding4.widget.textChanges
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.listener.OnResultCallbackListener
import com.poker.common.base.BaseActivity
import com.poker.oss.OssManager
import com.wetech.aus.tennis.app.R
import com.wetech.aus.tennis.app.databinding.ActivityEditProfileBinding
import com.wetech.aus.tennis.app.domain.RoutePath
import com.wetech.aus.tennis.app.domain.login.repository.bean.UserInfoDao
import com.wetech.aus.tennis.app.domain.profile.vm.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


@Route(path = RoutePath.Profile.EditProfileActivity)
@AndroidEntryPoint
class EditProfileActivity : BaseActivity<ActivityEditProfileBinding>() {
    @Inject
    lateinit var userInfoDao: UserInfoDao


    private val viewModel by getViewModel(UserViewModel::class.java) {
        getOssTokenVOLD.observe(mLifecycleOwner, {
            val tokenCre = OSSStsTokenCredentialProvider(
                it?.accessKeyId,
                it?.accessKeySecret,
                it?.securityToken
            )
            val oss = OSSClient(applicationContext, "http://oss-cn-beijing.aliyuncs.com", tokenCre)
            OssManager().simpleUpload(it?.imgPath ?: "", oss, lifecycleSupportedScope) {
                onLoad { progress ->
                    progress.currentSize
                }
                onSuccess { objKey ->
                    val url: String = oss.presignPublicObjectURL(it?.bucket, objKey)
                    binding.imgAvatar.load(url)
                    updateUserAvatar(url)
                }
                onFailed { e ->
                    e.printStackTrace()
                }
            }
        })

        updateUserAvatarLD.observe(mLifecycleOwner, {
            getUserInfo()
        })

        updateUserInfoLD.observe(mLifecycleOwner, {
            showToast("Success!")
            finish()
        })
    }

    override fun init() {
        binding.apply {

            toolBar.btnBack.setOnClickListener { finish() }
            toolBar.tvTitle.text = getString(R.string.edit_profile)

            lifecycleSupportedScope.launch(Dispatchers.Main) {
                val userInfo = withContext(Dispatchers.IO) { userInfoDao.query() }
                userInfo?.apply {
                    imgAvatar.load(avatar)
                }
            }

            Observable.combineLatest(
                edFirstName.textChanges(),
                edLastName.textChanges(),
                edPhone.textChanges(),
                edGender.textChanges(),
                edBirth.textChanges(), { t1, t2, t3, t4, t5 ->
                    !t1.isNullOrBlank() &&
                            !t2.isNullOrBlank() &&
                            !t3.isNullOrBlank() &&
                            !t4.isNullOrBlank() &&
                            !t5.isNullOrBlank()
                }).subscribe { flag: Boolean ->
                if (flag) {
                    btnDown.setBackgroundColor(Color.parseColor("#3291F8"))
                    btnDown.isEnabled = true
                } else {
                    btnDown.setBackgroundColor(Color.GRAY)
                    btnDown.isEnabled = false
                }
            }

            imgAvatar.setOnClickListener {
                selectPic()
            }

            tvEdit.setOnClickListener {
                selectPic()
            }

            edBirth.setOnClickListener {
                val pvTime = TimePickerBuilder(this@EditProfileActivity) { date, v ->
                    Timber.d(TimeUtils.date2String(date))
                    edBirth.setText(
                        TimeUtils.date2String(
                            date,
                            SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                        )
                    )
                }.build()
                pvTime.show()
            }

            btnDown.setOnClickListener {
                if (edFirstName.text.isNullOrBlank() ||
                    edLastName.text.isNullOrBlank() ||
                    edPhone.text.isNullOrBlank() ||
                    edGender.text.isNullOrBlank() ||
                    edBirth.text.isNullOrBlank()
                ) {
                    showToast("please fill in the form ")
                    return@setOnClickListener
                }
                lifecycleSupportedScope.launch(Dispatchers.Main) {
                    val userInfo = withContext(Dispatchers.IO) { userInfoDao.query() }
                    val newUserInfo = userInfo?.copy()?.apply {
                        firstName = edFirstName.text.toString()
                        lastName = edLastName.text.toString()
                        phone = edPhone.text.toString()
                        gender = edGender.text.toString()
                        birthday = edBirth.text.toString()
                    }
                    viewModel.updateUserInfo(newUserInfo)
                }
            }

        }
    }


    private fun selectPic() {
        PictureSelector.create(this)
            .openGallery(PictureMimeType.ofImage())
            .imageEngine(ColiEngine)
            .isPageStrategy(true, true)
            .selectionMode(PictureConfig.SINGLE)
            .isSingleDirectReturn(true)
            .isWeChatStyle(true)
            .isCamera(true)//列表是否显示拍照按钮
            .isPreviewImage(true)//是否预览图片
            .forResult(object : OnResultCallbackListener<LocalMedia> {
                override fun onResult(result: MutableList<LocalMedia>?) {
                    Timber.d(Gson().toJson(result))
                    viewModel.getOssTokenVO(result?.get(0)?.realPath ?: "")
                }

                override fun onCancel() {
                    showToast("cancel")
                }

            })
    }
}