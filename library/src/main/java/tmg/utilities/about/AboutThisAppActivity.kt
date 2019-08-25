package tmg.utilities.about

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_about_this_app.*
import tmg.utilities.R
import tmg.utilities.extensions.bindText
import tmg.utilities.extensions.click
import tmg.utilities.extensions.subscribeNoError
import tmg.utilities.mvvm.MVVMActivity

class AboutThisAppActivity: MVVMActivity<AboutThisAppVM>(), AboutThisAppDependencyCallback {

    private var isDarkMode: Boolean = false

    private lateinit var name: String
    private lateinit var nameDesc: String
    private lateinit var imageUrl: String
    private lateinit var github: String
    private lateinit var email: String
    private lateinit var website: String
    private var play: String? = null
    private var appPackageName: String? = null
    private lateinit var dependencies: List<AboutThisAppDependency>
    private lateinit var appName: String
    private lateinit var footnote: String
    private lateinit var thankYou: String
    private lateinit var appVersion: String

    private lateinit var adapter: AboutThisAppDependencyAdapter

    override fun viewModelClass(): Class<AboutThisAppVM> {
        return AboutThisAppVM::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_about_this_app
    }

    override fun arguments(bundle: Bundle) {
        super.arguments(bundle)
        intent.extras?.let {
            isDarkMode = it.getBoolean(INTENT_IS_DARK_MODE)
            github = it.getString(INTENT_GITHUB)!!
            email = it.getString(INTENT_EMAIL)!!
            website = it.getString(INTENT_WEBSITE)!!
            play = it.getString(INTENT_PLAY)
            appPackageName = it.getString(INTENT_PACKAGE_NAME)
            dependencies = it.getParcelableArray(INTENT_DEPENDENCIES)
                ?.map { it as AboutThisAppDependency }
                ?.toList() ?: listOf()
            appName = it.getString(INTENT_APP_NAME)!!
            name = it.getString(INTENT_NAME)!!
            nameDesc = it.getString(INTENT_NAME_DESC)!!
            imageUrl = it.getString(INTENT_IMAGE_URL)!!
            footnote = it.getString(INTENT_FOOTNOTE)!!
            thankYou = it.getString(INTENT_THANK_YOU)!!
            appVersion = it.getString(INTENT_APP_VERSION)!!
        }
    }

    override fun initViews() {
        super.initViews()

        viewModel.inputs.setupLinks(
            github = github,
            website = website,
            email = email,
            play = play,
            appPackage = appPackageName
        )
        viewModel.inputs.setupData(
            name = name,
            nameDesc = nameDesc,
            imageUrl = imageUrl,
            footnote = footnote,
            thankYou = thankYou,
            appVersion = appVersion
        )
        viewModel.inputs.setupDependencies(dependencies)

        if (isDarkMode) {
            llAboutThisAppBackground.setBackgroundColor(ContextCompat.getColor(this, R.color.aboutThisApp_backgroundDark))

            toolbar?.setBackgroundColor(ContextCompat.getColor(this, R.color.aboutThisApp_headerDark))
            rlToolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.aboutThisApp_headerDark))
            tvAboutThisAppName.setTextColor(ContextCompat.getColor(this, R.color.aboutThisApp_textLight))
            tvAboutThisAppDesc.setTextColor(ContextCompat.getColor(this, R.color.aboutThisApp_textLightSecondary))

            tvAboutThisAppThankYou.setTextColor(ContextCompat.getColor(this, R.color.aboutThisApp_textLightSecondary))
            tvAboutThisAppFootnotes.setTextColor(ContextCompat.getColor(this, R.color.aboutThisApp_textLightSecondary))
            tvAboutThisAppAppVersion.setTextColor(ContextCompat.getColor(this, R.color.aboutThisApp_textLightSecondary))
        }
        else {
            llAboutThisAppBackground.setBackgroundColor(ContextCompat.getColor(this, R.color.aboutThisApp_backgroundLight))

            toolbar?.setBackgroundColor(ContextCompat.getColor(this, R.color.aboutThisApp_headerLight))
            rlToolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.aboutThisApp_headerLight))
            tvAboutThisAppName.setTextColor(ContextCompat.getColor(this, R.color.aboutThisApp_textLight))
            tvAboutThisAppDesc.setTextColor(ContextCompat.getColor(this, R.color.aboutThisApp_textLightSecondary))

            tvAboutThisAppThankYou.setTextColor(ContextCompat.getColor(this, R.color.aboutThisApp_textDarkSecondary))
            tvAboutThisAppFootnotes.setTextColor(ContextCompat.getColor(this, R.color.aboutThisApp_textDarkSecondary))
            tvAboutThisAppAppVersion.setTextColor(ContextCompat.getColor(this, R.color.aboutThisApp_textDarkSecondary))
        }

        initToolbar(R.id.toolbar, true, R.drawable.ic_util_icon_back)

        adapter = AboutThisAppDependencyAdapter(this, isDarkMode)
        rvAboutThisAppDependencies.layoutManager = LinearLayoutManager(this)
        rvAboutThisAppDependencies.adapter = adapter
    }

    override fun observeViewModel() {

        // Inputs

        disposables += btnGithubLight
            .click()
            .subscribeNoError {
                viewModel.inputs.clickGithub()
            }

        disposables += btnEmailLight
            .click()
            .subscribeNoError {
                viewModel.inputs.clickEmail()
            }

        disposables += btnPlayLight
            .click()
            .subscribeNoError {
                viewModel.inputs.clickPlay()
            }

        disposables += btnWebsiteLight
            .click()
            .subscribeNoError {
                viewModel.inputs.clickWebsite()
            }

        disposables += btnGithubDark
            .click()
            .subscribeNoError {
                viewModel.inputs.clickGithub()
            }

        disposables += btnEmailDark
            .click()
            .subscribeNoError {
                viewModel.inputs.clickEmail()
            }

        disposables += btnPlayDark
            .click()
            .subscribeNoError {
                viewModel.inputs.clickPlay()
            }

        disposables += btnWebsiteDark
            .click()
            .subscribeNoError {
                viewModel.inputs.clickWebsite()
            }



        // Outputs

        disposables += viewModel.outputs
            .clickedWebsite()
            .subscribeNoError {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
            }

        disposables += viewModel.outputs
            .clickedEmail()
            .subscribeNoError {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/html"
                intent.putExtra(Intent.EXTRA_EMAIL, it)
                intent.putExtra(Intent.EXTRA_SUBJECT, appName)
                startActivity(Intent.createChooser(intent, getString(R.string.about_this_app_send_email)))
            }

        disposables += viewModel.outputs
            .clickedGithub()
            .subscribeNoError {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
            }

        disposables += viewModel.outputs
            .clickedPlay()
            .subscribeNoError {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
            }



        disposables += viewModel.outputs
            .name()
            .bindText(tvAboutThisAppName)

        disposables += viewModel.outputs
            .nameDesc()
            .bindText(tvAboutThisAppDesc)

        disposables += viewModel.outputs
            .imageUrl()
            .subscribeNoError {
                Glide.with(this)
                    .load(it)
                    .into(imgAboutAvatar)
            }



        disposables += viewModel.outputs
            .appVersion()
            .subscribeNoError {
                tvAboutThisAppAppVersion.text = getString(R.string.app_version_version_name, it)
            }

        disposables += viewModel.outputs
            .thankyou()
            .bindText(tvAboutThisAppThankYou)

        disposables += viewModel.outputs
            .footnote()
            .bindText(tvAboutThisAppFootnotes)



        disposables += viewModel.outputs
            .dependencies()
            .subscribeNoError {
                adapter.replaceAll(it)
            }

    }

    //region AboutThisAppDependencyCallback

    override fun dependencyItemClicked(item: AboutThisAppDependency) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(item.url)))
    }

    //endregion

    companion object {

        private const val INTENT_NAME: String = "INTENT_NAME"
        private const val INTENT_NAME_DESC: String = "INTENT_NAME_DESC"
        private const val INTENT_IMAGE_URL: String = "INTENT_IMAGE_URL"
        private const val INTENT_GITHUB: String = "INTENT_GITHUB"
        private const val INTENT_EMAIL: String = "INTENT_EMAIL"
        private const val INTENT_WEBSITE: String = "INTENT_WEBSITE"
        private const val INTENT_PLAY: String = "INTENT_PLAY"
        private const val INTENT_PACKAGE_NAME: String = "INTENT_PACKAGE_NAME"
        private const val INTENT_APP_NAME: String = "INTENT_APP_NAME"
        private const val INTENT_APP_VERSION: String = "INTENT_APP_VERSION"
        private const val INTENT_FOOTNOTE: String = "INTENT_FOOTNOTE"
        private const val INTENT_THANK_YOU: String = "INTENT_THANK_YOU"
        private const val INTENT_DEPENDENCIES: String = "INTENT_DEPENDENCIES"
        private const val INTENT_IS_DARK_MODE: String = "INTENT_IS_DARK_MODE"

        @JvmStatic
        fun intent(context: Context,
                   isDarkMode: Boolean,
                   name: String,
                   nameDesc: String,
                   imageUrl: String,
                   github: String,
                   email: String,
                   website: String,
                   packageName: String? = null,
                   play: String? = null,
                   dependencies: List<AboutThisAppDependency>,
                   appName: String,
                   appVersion: String,
                   footnote: String,
                   thankYou: String
        ): Intent {
            if (play == null && packageName == null) {
                throw Exception("You must set either a play store link or give the package name")
            }
            val intent = Intent(context, AboutThisAppActivity::class.java)
            intent.putExtra(INTENT_IS_DARK_MODE, isDarkMode)
            intent.putExtra(INTENT_NAME, name)
            intent.putExtra(INTENT_NAME_DESC, nameDesc)
            intent.putExtra(INTENT_IMAGE_URL, imageUrl)
            intent.putExtra(INTENT_GITHUB, github)
            intent.putExtra(INTENT_EMAIL, email)
            intent.putExtra(INTENT_WEBSITE, website)
            intent.putExtra(INTENT_PACKAGE_NAME, packageName)
            intent.putExtra(INTENT_PLAY, play)
            intent.putExtra(INTENT_DEPENDENCIES, dependencies.toTypedArray())
            intent.putExtra(INTENT_APP_NAME, appName)
            intent.putExtra(INTENT_APP_VERSION, appVersion)
            intent.putExtra(INTENT_FOOTNOTE, footnote)
            intent.putExtra(INTENT_THANK_YOU, thankYou)
            return intent
        }
    }

}