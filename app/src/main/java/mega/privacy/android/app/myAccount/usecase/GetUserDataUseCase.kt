package mega.privacy.android.app.myAccount.usecase

import android.content.Intent
import io.reactivex.rxjava3.core.Single
import mega.privacy.android.app.MegaApplication
import mega.privacy.android.app.di.MegaApi
import mega.privacy.android.app.listeners.OptionalMegaRequestListenerInterface
import mega.privacy.android.app.utils.Constants.BROADCAST_ACTION_INTENT_UPDATE_USER_DATA
import nz.mega.sdk.MegaApiAndroid
import nz.mega.sdk.MegaError.API_OK
import javax.inject.Inject

class GetUserDataUseCase @Inject constructor(
    @MegaApi private val megaApi: MegaApiAndroid
) {

    fun get(): Single<Boolean> =
        Single.create { emitter ->
            megaApi.getUserData(
                OptionalMegaRequestListenerInterface(onRequestFinish = { _, error ->
                    val success = error.errorCode == API_OK

                    if (success) {
                        MegaApplication.getInstance()
                            .sendBroadcast(Intent(BROADCAST_ACTION_INTENT_UPDATE_USER_DATA))
                    }

                    emitter.onSuccess(success)
                })
            )
        }
}