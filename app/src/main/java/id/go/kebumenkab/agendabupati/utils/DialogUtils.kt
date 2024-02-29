package id.go.kebumenkab.agendabupati.utils

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.provider.Settings
import android.view.LayoutInflater
import androidx.core.text.HtmlCompat
import id.go.kebumenkab.agendabupati.R
import id.go.kebumenkab.agendabupati.databinding.LayoutDialogLoginFailedBinding

class DialogUtils {
    private var dialogBuilder: AlertDialog? = null

    fun showProgressDialog(context: Context) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.layout_dialog_loading, null)
        dialogBuilder = AlertDialog.Builder(context)
            .setView(dialogView)
            .setCancelable(false)
            .create()
        dialogBuilder?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialogBuilder?.show()
    }
    fun showProgressDialogCancelable(context: Context) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.layout_dialog_loading, null)
        dialogBuilder = AlertDialog.Builder(context)
            .setView(dialogView)
            .setCancelable(true)
            .create()
        dialogBuilder?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialogBuilder?.show()
    }

    fun hideDialog() {
        if (dialogBuilder != null){
            dialogBuilder?.dismiss()
        }
    }
}

private var dialogBuilder: AlertDialog? = null

fun showProgressDialog(context: Context) {
    val dialogView = LayoutInflater.from(context).inflate(R.layout.layout_dialog_loading, null)
    dialogBuilder = AlertDialog.Builder(context)
        .setView(dialogView)
        .setCancelable(false)
        .create()
    dialogBuilder?.window?.setBackgroundDrawableResource(android.R.color.transparent)
    dialogBuilder?.show()
}

fun hideDialog() {
    if (dialogBuilder != null){
        dialogBuilder?.dismiss()
    }
}

//fun dialogNoConnection(context: Context) {
//    val inflater = LayoutInflater.from(context)
//    val binding: LayoutDialogLoginFailedBinding = LayoutDialogLoginFailedBinding.inflate(inflater)
//    val builder: androidx.appcompat.app.AlertDialog.Builder = androidx.appcompat.app.AlertDialog.Builder(inflater.context)
//    builder.setView(binding.root)
//    val dialog: androidx.appcompat.app.AlertDialog = builder.create()
//    binding.apply {
//        labelMessage.text = context.getString(R.string.not_connected_to_the_internet)
//        buttonOk.setOnClickListener {
//            dialog.dismiss()
//        }
//    }
//    dialog.setCancelable(true)
//    dialog.show()
//    dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//}

fun dialogAirPlaneModeEnable(context: Context) {
    val inflater = LayoutInflater.from(context)
    val binding: LayoutDialogLoginFailedBinding = LayoutDialogLoginFailedBinding.inflate(inflater)
    val builder: androidx.appcompat.app.AlertDialog.Builder = androidx.appcompat.app.AlertDialog.Builder(inflater.context)
    builder.setView(binding.root)
    val dialog: androidx.appcompat.app.AlertDialog = builder.create()
    binding.apply {
        labelMessage.text = "Mode pesawat anda terdeteksi aktif. Harap matikan terlebih dahulu."
        buttonOk.setOnClickListener {
            dialog.dismiss()
        }
    }
    dialog.setCancelable(true)
    dialog.show()
    dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
}

//fun dialogPassphraseKurang(context: Context) {
//    val inflater = LayoutInflater.from(context)
//    val binding: LayoutDialogLoginFailedBinding = LayoutDialogLoginFailedBinding.inflate(inflater)
//    val builder: androidx.appcompat.app.AlertDialog.Builder = androidx.appcompat.app.AlertDialog.Builder(inflater.context)
//    builder.setView(binding.root)
//    val dialog: androidx.appcompat.app.AlertDialog = builder.create()
//    binding.apply {
//        labelMessage.text = context.getString(R.string.message_minimal_8_karakter)
//        buttonOk.setOnClickListener {
//            dialog.dismiss()
//        }
//    }
//    dialog.setCancelable(true)
//    dialog.show()
//    dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//}

//fun dialogAutoDateTime(context: Context) {
//    val binding: LayoutDialogWarningBinding = LayoutDialogWarningBinding.inflate(LayoutInflater.from(context))
//    val builder: AlertDialog.Builder = AlertDialog.Builder(context)
//    builder.setView(binding.root)
//    val dialog: AlertDialog = builder.create()
//    binding.apply {
//        labelTitle.text = context.getString(R.string.warning)
//        labelMessage.text = context.getString(R.string.message_auto_date_time)
//        buttonNo.text = context.getString(R.string.next_time)
//        buttonNo.setOnClickListener {
//            dialog.dismiss()
//        }
//        buttonYes.text = context.getString(R.string.setting)
//        buttonYes.setOnClickListener {
//            val intent = Intent(Settings.ACTION_DATE_SETTINGS)
//            context.startActivity(intent)
//            dialog.dismiss()
//        }
//    }
//    dialog.setCancelable(true)
//    dialog.show()
//    dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//}

//fun dialogDevOption(context: Context) {
//    val binding: LayoutDialogLoginFailedBinding = LayoutDialogLoginFailedBinding.inflate(
//        LayoutInflater.from(context))
//    val builder: androidx.appcompat.app.AlertDialog.Builder = androidx.appcompat.app.AlertDialog.Builder(context)
//    builder.setView(binding.root)
//    val dialog: androidx.appcompat.app.AlertDialog = builder.create()
//    binding.apply {
//        ivAlert.setImageResource(R.drawable.ic_warning)
//        labelMessage.text = HtmlCompat.fromHtml(context.getString(R.string.third_parties), HtmlCompat.FROM_HTML_MODE_LEGACY)
//        buttonOk.setOnClickListener {
//            dialog.dismiss()
//        }
//    }
//    dialog.setCancelable(true)
//    dialog.show()
//    dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//}

fun dialogLoginFailed(context: Context) {
    val binding: LayoutDialogLoginFailedBinding = LayoutDialogLoginFailedBinding.inflate(
        LayoutInflater.from(context))
    val builder: androidx.appcompat.app.AlertDialog.Builder = androidx.appcompat.app.AlertDialog.Builder(context)
    builder.setView(binding.root)
    val dialog: androidx.appcompat.app.AlertDialog = builder.create()
    binding.apply {
        buttonOk.setOnClickListener {
            dialog.dismiss()
        }
    }
    dialog.setCancelable(true)
    dialog.show()
    dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
}

//fun dialogFaceRecord(context: Context) {
//    val binding: LayoutDialogWarningBinding = LayoutDialogWarningBinding.inflate(LayoutInflater.from(context))
//    val builder: AlertDialog.Builder = AlertDialog.Builder(context)
//    builder.setView(binding.root)
//    val dialog: AlertDialog = builder.create()
//    binding.apply {
//        labelTitle.text = context.getString(R.string.warning)
//        labelMessage.text = context.getString(R.string.message_face_record)
//        buttonNo.text = context.getString(R.string.next_time)
//        buttonNo.setOnClickListener {
//            dialog.dismiss()
//        }
//        buttonYes.text = context.getString(R.string.yes)
//        buttonYes.setOnClickListener {
//            val intent = Intent(context, FaceRecordActivity::class.java)
//            context.startActivity(intent)
//            dialog.dismiss()
//        }
//    }
//    dialog.setCancelable(true)
//    dialog.show()
//    dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//}

//fun dialogLocation(context: Context) {
//    val binding: LayoutDialogWarningBinding = LayoutDialogWarningBinding.inflate(LayoutInflater.from(context))
//    val builder: AlertDialog.Builder = AlertDialog.Builder(context)
//    builder.setView(binding.root)
//    val dialog: AlertDialog = builder.create()
//    binding.apply {
//        labelTitle.text = context.getString(R.string.warning)
//        labelMessage.text = context.getString(R.string.enable_location_service)
//        buttonNo.text = context.getString(R.string.next_time)
//        buttonNo.setOnClickListener {
//            dialog.dismiss()
//        }
//        buttonYes.text = context.getString(R.string.setting)
//        buttonYes.setOnClickListener {
//            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
//            context.startActivity(intent)
//            dialog.dismiss()
//        }
//    }
//    dialog.setCancelable(true)
//    dialog.show()
//    dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//}
//
//fun dialogNotFaceRecord(context: Context) {
//    val binding: LayoutDialogExitAppBinding = LayoutDialogExitAppBinding.inflate(LayoutInflater.from(context))
//    val builder: AlertDialog.Builder = AlertDialog.Builder(context)
//    builder.setView(binding.root)
//    val dialog: AlertDialog = builder.create()
//    binding.apply {
//        labelTitle.text = context.getString(R.string.warning)
//        labelMessage.text = context.getString(R.string.not_face_record)
//        buttonYes.text = context.getString(R.string.ok)
//        buttonYes.setOnClickListener {
//            dialog.dismiss()
//        }
//    }
//    dialog.setCancelable(true)
//    dialog.show()
//    dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//}