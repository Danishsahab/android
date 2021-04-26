package mega.privacy.android.app.modalbottomsheet.phoneNumber

import android.annotation.SuppressLint
import android.app.Dialog
import android.view.View
import mega.privacy.android.app.R
import mega.privacy.android.app.modalbottomsheet.BaseBottomSheetDialogFragment

class PhoneNumberBottomSheetDialogFragment: BaseBottomSheetDialogFragment() {

    @SuppressLint("RestrictedApi")
    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)

        val phoneNumberCallback = context as PhoneNumberCallback

        contentView = View.inflate(getContext(), R.layout.bottom_sheet_phonenumber, null)
        mainLinearLayout = contentView.findViewById(R.id.phonenumber_bottom_sheet)
        items_layout = contentView.findViewById(R.id.items_layout)

        contentView.findViewById<View>(R.id.modify_phonenumber_layout).setOnClickListener {
            phoneNumberCallback.showConfirmation(true)
        }

        contentView.findViewById<View>(R.id.remove_phonenumber_layout).setOnClickListener {
            phoneNumberCallback.showConfirmation(false)
        }

        dialog.setContentView(contentView)
        setBottomSheetBehavior(HEIGHT_HEADER_LOW, false)
    }
}