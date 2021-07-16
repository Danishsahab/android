package mega.privacy.android.app.smsVerification.usecase

import com.jeremyliao.liveeventbus.LiveEventBus
import io.reactivex.rxjava3.core.Single
import mega.privacy.android.app.constants.EventConstants.EVENT_REFRESH_PHONE_NUMBER
import mega.privacy.android.app.di.MegaApi
import mega.privacy.android.app.listeners.OptionalMegaRequestListenerInterface
import mega.privacy.android.app.utils.ErrorUtils.toThrowable
import nz.mega.sdk.MegaApiAndroid
import nz.mega.sdk.MegaError.API_ENOENT
import nz.mega.sdk.MegaError.API_OK
import javax.inject.Inject

class ResetPhoneNumberUseCase @Inject constructor(
    @MegaApi private val megaApi: MegaApiAndroid
) {

    /**
     * Launches a request to remove current verified phone number.
     *
     * @return Single<Boolean> True if the request finished with success, error if not.
     */
    fun reset(): Single<Boolean> =
        Single.create { emitter ->
            megaApi.resetSmsVerifiedPhoneNumber(
                OptionalMegaRequestListenerInterface(onRequestFinish = { _, error ->
                    when (error.errorCode) {
                        API_OK, API_ENOENT -> {
                            LiveEventBus.get(EVENT_REFRESH_PHONE_NUMBER, Boolean::class.java)
                                .post(true)

                            emitter.onSuccess(true)
                        }
                        else -> emitter.onError(error.toThrowable())
                    }
                })
            )
        }
}