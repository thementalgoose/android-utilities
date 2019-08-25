package tmg.utilities.about

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import tmg.utilities.extensions.takeWhen
import tmg.utilities.mvvm.MVVMViewModel

//region Inputs

interface AboutThisAppVMInputs {
    fun setupLinks(github: String, website: String, email: String, play: String)
    fun setupData(name: String, nameDesc: String, imageUrl: String, footnote: String, thankYou: String, appVersion: String)
    fun setupDependencies(dependencies: List<AboutThisAppDependency>)

    fun clickGithub()
    fun clickWebsite()
    fun clickEmail()
    fun clickPlay()
}

//endregion

//region Outputs

interface AboutThisAppVMOutputs {

    fun dependencies(): Observable<List<AboutThisAppDependency>>

    fun clickedGithub(): Observable<String>
    fun cilckedWebsite(): Observable<String>
    fun clickedEmail(): Observable<String>
    fun clickedPlay(): Observable<String>

    fun name(): Observable<String>
    fun nameDesc(): Observable<String>
    fun imageUrl(): Observable<String>

    fun thankyou(): Observable<String>
    fun footnote(): Observable<String>
    fun appVersion(): Observable<String>
}

//endregion

class AboutThisAppVM: MVVMViewModel(), AboutThisAppVMInputs, AboutThisAppVMOutputs {

    private val githubLinkEvent: BehaviorSubject<String> = BehaviorSubject.create()
    private val websiteLinkEvent: BehaviorSubject<String> = BehaviorSubject.create()
    private val emailLinkEvent: BehaviorSubject<String> = BehaviorSubject.create()
    private val playLinkEvent: BehaviorSubject<String> = BehaviorSubject.create()

    private val nameTextEvent: BehaviorSubject<String> = BehaviorSubject.create()
    private val nameDescTextEvent: BehaviorSubject<String> = BehaviorSubject.create()
    private val imageUrlEvent: BehaviorSubject<String> = BehaviorSubject.create()
    private val footnoteTextEvent: BehaviorSubject<String> = BehaviorSubject.create()
    private val thankyouTextEvent: BehaviorSubject<String> = BehaviorSubject.create()
    private val appVersionTextEvent: BehaviorSubject<String> = BehaviorSubject.create()

    private val dependenciesEvent: BehaviorSubject<List<AboutThisAppDependency>> = BehaviorSubject.createDefault(listOf())

    private val githubEvent: PublishSubject<Boolean> = PublishSubject.create()
    private val websiteEvent: PublishSubject<Boolean> = PublishSubject.create()
    private val emailEvent: PublishSubject<Boolean> = PublishSubject.create()
    private val playEvent: PublishSubject<Boolean> = PublishSubject.create()

    var inputs: AboutThisAppVMInputs = this
    var outputs: AboutThisAppVMOutputs = this

    init {

    }

    // Inputs

    override fun setupLinks(github: String, website: String, email: String, play: String) {
        githubLinkEvent.onNext(github)
        websiteLinkEvent.onNext(website)
        emailLinkEvent.onNext(email)
        playLinkEvent.onNext(play)
    }

    override fun setupDependencies(dependencies: List<AboutThisAppDependency>) {
        dependenciesEvent.onNext(dependencies)
    }

    override fun setupData(
        name: String,
        nameDesc: String,
        imageUrl: String,
        footnote: String,
        thankYou: String,
        appVersion: String
    ) {
        nameTextEvent.onNext(name)
        nameDescTextEvent.onNext(nameDesc)
        imageUrlEvent.onNext(imageUrl)
        footnoteTextEvent.onNext(footnote)
        thankyouTextEvent.onNext(thankYou)
        appVersionTextEvent.onNext(appVersion)
    }

    override fun clickGithub() {
        githubEvent.onNext(true)
    }

    override fun clickWebsite() {
        websiteEvent.onNext(true)
    }

    override fun clickEmail() {
        emailEvent.onNext(true)
    }

    override fun clickPlay() {
        playEvent.onNext(true)
    }

    // Outputs

    override fun dependencies(): Observable<List<AboutThisAppDependency>> {
        return dependenciesEvent
    }

    override fun clickedGithub(): Observable<String> {
        return githubLinkEvent.takeWhen(githubEvent)
    }

    override fun cilckedWebsite(): Observable<String> {
        return websiteLinkEvent.takeWhen(websiteEvent)
    }

    override fun clickedEmail(): Observable<String> {
        return emailLinkEvent.takeWhen(emailEvent)
    }

    override fun clickedPlay(): Observable<String> {
        return playLinkEvent.takeWhen(playEvent)
    }

    override fun name(): Observable<String> {
        return nameTextEvent
    }

    override fun nameDesc(): Observable<String> {
        return nameDescTextEvent
    }

    override fun imageUrl(): Observable<String> {
        return imageUrlEvent
    }

    override fun thankyou(): Observable<String> {
        return thankyouTextEvent
    }

    override fun footnote(): Observable<String> {
        return footnoteTextEvent
    }

    override fun appVersion(): Observable<String> {
        return appVersionTextEvent
    }
}