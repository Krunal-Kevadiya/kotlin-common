package com.kotlincommon.sample

import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.kotlinlibrary.snackbar.Snackbar
import com.kotlinlibrary.snackbar.anim.SnackAnim
import com.kotlinlibrary.snackbar.util.SnackBatType
import com.kotlinlibrary.snackbar.util.snackBarMessage
import com.kotlinlibrary.utils.ktx.logs

class SnackBarActivity : AppCompatActivity() {
    lateinit var snackBarMsg: Snackbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snackbar)

        findViewById<Button>(R.id.btn_1).setOnClickListener {
            showSnackBar(basic())
        }
        findViewById<Button>(R.id.btn_2).setOnClickListener {
            showSnackBar(basicDuration())
        }
        findViewById<Button>(R.id.btn_3).setOnClickListener {
            showSnackBar(gravityTop())
        }
        findViewById<Button>(R.id.btn_4).setOnClickListener {
            showSnackBar(gravityBottom())
        }
        findViewById<Button>(R.id.btn_5).setOnClickListener {
            showSnackBar(titleBasic())
        }
        findViewById<Button>(R.id.btn_6).setOnClickListener {
            showSnackBar(titleAdvanced())
        }
        findViewById<Button>(R.id.btn_7).setOnClickListener {
            showSnackBar(messageBasic())
        }
        findViewById<Button>(R.id.btn_8).setOnClickListener {
            showSnackBar(messageAdvanced())
        }
        findViewById<Button>(R.id.btn_9).setOnClickListener {
            showSnackBar(background())
        }
        findViewById<Button>(R.id.btn_10).setOnClickListener {
            showSnackBar(backgroundDrawable())
        }
        findViewById<Button>(R.id.btn_11).setOnClickListener {
            showSnackBar(overlay())
        }
        findViewById<Button>(R.id.btn_12).setOnClickListener {
            showSnackBar(overlayAdvanced())
        }
        findViewById<Button>(R.id.btn_13).setOnClickListener {
            showSnackBar(primaryActionBasic())
        }
        findViewById<Button>(R.id.btn_14).setOnClickListener {
            showSnackBar(primaryActionAdvanced())
        }
        findViewById<Button>(R.id.btn_15).setOnClickListener {
            showSnackBar(primaryActionListener())
        }
        findViewById<Button>(R.id.btn_16).setOnClickListener {
            showSnackBar(positiveNegativeAction())
        }
        findViewById<Button>(R.id.btn_17).setOnClickListener {
            showSnackBar(iconBasic())
        }
        findViewById<Button>(R.id.btn_18).setOnClickListener {
            showSnackBar(iconAdvanced())
        }
        findViewById<Button>(R.id.btn_19).setOnClickListener {
            showSnackBar(progressBasic())
        }
        findViewById<Button>(R.id.btn_20).setOnClickListener {
            showSnackBar(progressAdvanced())
        }
        findViewById<Button>(R.id.btn_21).setOnClickListener {
            showSnackBar(enterExitAnimation())
        }
        findViewById<Button>(R.id.btn_22).setOnClickListener {
            showSnackBar(enterExitAnimationSlide())
        }
        findViewById<Button>(R.id.btn_23).setOnClickListener {
            showSnackBar(iconAnimation())
        }
        findViewById<Button>(R.id.btn_24).setOnClickListener {
            showSnackBar(showListener())
        }
        findViewById<Button>(R.id.btn_25).setOnClickListener {
            showSnackBar(dismissListener())
        }
        findViewById<Button>(R.id.btn_26).setOnClickListener {
            showSnackBar(barTap())
        }
        findViewById<Button>(R.id.btn_27).setOnClickListener {
            showSnackBar(swipeToDismiss())
        }
        findViewById<Button>(R.id.btn_28).setOnClickListener {
            showSnackBar(barShadow())
        }
        findViewById<Button>(R.id.btn_29).setOnClickListener {
            showSnackBar(vibration())
        }
        findViewById<Button>(R.id.btn_30).setOnClickListener {
            showSnackBar(snackBarMessage(SnackBatType.ERROR, "type Based Message").build())
        }
    }

    override fun onPause() {
        super.onPause()
        if (::snackBarMsg.isInitialized)
            snackBarMsg.dismiss()
    }

    private fun showSnackBar(snackBar: Snackbar) {
        if (::snackBarMsg.isInitialized && (snackBarMsg.isShowing() || snackBarMsg.isShown())) {
            snackBarMsg.dismiss()
        }
        snackBarMsg = snackBar
        snackBarMsg.show()
    }

    private fun basic(): Snackbar {
        return Snackbar.Builder(this)
            .gravity(Snackbar.Gravity.BOTTOM)
            .message("This is a basic snackBar")
            .build()
    }

    private fun basicDuration(): Snackbar {
        return Snackbar.Builder(this)
            .gravity(Snackbar.Gravity.TOP)
            .duration(2000)
            .message("This is a snackBar with duration")
            .build()
    }

    private fun gravityTop(): Snackbar {
        return Snackbar.Builder(this)
            .gravity(Snackbar.Gravity.TOP)
            .message("Snackbar is shown at the top")
            .build()
    }

    private fun gravityBottom(): Snackbar {
        return Snackbar.Builder(this)
            .gravity(Snackbar.Gravity.BOTTOM)
            .message("Snackbar is shown at the bottom")
            .build()
    }

    private fun titleBasic(): Snackbar {
        return Snackbar.Builder(this)
            .gravity(Snackbar.Gravity.BOTTOM)
            .title("Hello Snackbar")
            .message("You can put any message of any length here.")
            .build()
    }

    private fun titleAdvanced(): Snackbar {
        return Snackbar.Builder(this)
            .gravity(Snackbar.Gravity.BOTTOM)
            .title("Hello World")
            .titleColorRes(android.R.color.white)
            .titleSizeInSp(28f)
            .message("The font and size of the text is changed here.")
            .titleTypeface(Typeface.createFromAsset(assets, "BeautifulAndOpenHearted.ttf"))
            .build()
    }

    private fun messageBasic(): Snackbar {
        return Snackbar.Builder(this)
            .gravity(Snackbar.Gravity.TOP)
            .title("Hello World")
            .message(
                "This is a short message. But your message can be of any length and the " +
                        "with view will dynamically adjust itself. You can try to put " +
                        "very long messages as well. This can be really useful for " +
                        "putting up a lot of information to the user like feature " +
                        "explanation, tutorials, etc.")
            .build()
    }

    private fun messageAdvanced(): Snackbar {
        return Snackbar.Builder(this)
            .gravity(Snackbar.Gravity.TOP)
            .title("Hello World")
            .message("This is a short message")
            .messageColor(ContextCompat.getColor(this, android.R.color.white))
            .messageSizeInSp(16f)
            .messageTypeface(Typeface.createFromAsset(assets, "BeautifulAndOpenHearted.ttf"))
            .build()
    }

    private fun background(): Snackbar {
        return Snackbar.Builder(this)
            .gravity(Snackbar.Gravity.TOP)
            .title("Hello World!")
            .message("The background color can be changed to any color of your choice.")
            .backgroundColorRes(R.color.colorPrimaryDark)
            .build()
    }

    private fun backgroundDrawable(): Snackbar {
        return Snackbar.Builder(this)
            .gravity(Snackbar.Gravity.TOP)
            .title("Hello World!")
            .message("You can have gradients by setting background Drawable.")
            .backgroundDrawable(R.drawable.bg_gradient)
            .build()
    }

    private fun overlay(): Snackbar {
        return Snackbar.Builder(this)
            .gravity(Snackbar.Gravity.TOP)
            .title("Hello World!")
            .message("You can show a modal overlay to give a dim effect in the entire screen.")
            .backgroundColorRes(R.color.colorPrimaryDark)
            .showOverlay()
            .build()
    }

    private fun overlayAdvanced(): Snackbar {
        return Snackbar.Builder(this)
            .gravity(Snackbar.Gravity.TOP)
            .title("Hello World!")
            .message("You can show a modal overlay to give a dim effect in the entire screen.")
            .backgroundColorRes(R.color.colorPrimaryDark)
            .showOverlay()
            .overlayColorRes(R.color.modal)
            .overlayBlockable()
            .build()
    }

    private fun primaryActionBasic(): Snackbar {
        return Snackbar.Builder(this)
            .gravity(Snackbar.Gravity.TOP)
            .title("Hello World!")
            .message("You can click on the primary action button.")
            .primaryActionText("TRY")
            .build()
    }

    private fun primaryActionAdvanced(): Snackbar {
        return Snackbar.Builder(this)
            .gravity(Snackbar.Gravity.TOP)
            .title("Hello World!")
            .backgroundColorRes(R.color.colorPrimaryDark)
            .message("You can click on the primary action button.")
            .primaryActionText("TRY")
            .primaryActionTextColorRes(R.color.colorAccent)
            .primaryActionTextSizeInSp(20f)
            .build()
    }

    private fun primaryActionListener(): Snackbar {
        return Snackbar.Builder(this)
            .gravity(Snackbar.Gravity.TOP)
            .title("Hello World!")
            .message("You can click on the primary action button.")
            .primaryActionText("TRY")
            .primaryActionTapListener(object : Snackbar.OnActionTapListener {
                override fun onActionTapped(bar: Snackbar) {
                    bar.dismiss()
                }
            })
            .build()
    }

    private fun positiveNegativeAction(): Snackbar {
        return Snackbar.Builder(this)
            .gravity(Snackbar.Gravity.TOP)
            .title("Hello World!")
            .message("You can show either or both of the positive/negative buttons and customize them similar to the primary button.")
            .backgroundColorRes(R.color.colorPrimaryDark)
            .positiveActionText("YES")
            .negativeActionText("NO")
            .positiveActionTapListener(object : Snackbar.OnActionTapListener {
                override fun onActionTapped(bar: Snackbar) {
                    bar.dismiss()
                }
            })
            .negativeActionTapListener(object : Snackbar.OnActionTapListener {
                override fun onActionTapped(bar: Snackbar) {
                    bar.dismiss()
                }
            })
            .positiveActionTextColorRes(R.color.colorAccent)
            .negativeActionTextColorRes(R.color.colorAccent)
            .build()
    }

    private fun iconBasic(): Snackbar {
        return Snackbar.Builder(this)
            .gravity(Snackbar.Gravity.TOP)
            .title("Hello World!")
            .message("You can show a default icon on the left side of the with view.")
            .showIcon()
            .build()
    }

    private fun iconAdvanced(): Snackbar {
        return Snackbar.Builder(this)
            .gravity(Snackbar.Gravity.TOP)
            .title("Hello World!")
            .message("You can show a default icon on the left side of the with view.")
            .backgroundColorRes(R.color.colorPrimaryDark)
            .showIcon()
            .icon(R.drawable.ic_drop)
            .iconColorFilterRes(R.color.colorAccent)
            .build()
    }

    private fun progressBasic(): Snackbar {
        return Snackbar.Builder(this)
            .gravity(Snackbar.Gravity.TOP)
            .title("Hello World!")
            .message("You can show the progress bar on either the left or right side of the view")
            .showProgress(Snackbar.ProgressPosition.LEFT)
            .build()
    }

    private fun progressAdvanced(): Snackbar {
        return Snackbar.Builder(this)
            .gravity(Snackbar.Gravity.TOP)
            .title("Hello World!")
            .message("You can show the progress bar on either the left or right side of the view")
            .backgroundColorRes(R.color.colorPrimaryDark)
            .showProgress(Snackbar.ProgressPosition.RIGHT)
            .progressTintRes(R.color.colorAccent)
            .build()
    }

    private fun enterExitAnimation(): Snackbar {
        return Snackbar.Builder(this)
            .gravity(Snackbar.Gravity.TOP)
            .title("Hello World!")
            .message("You can change the enter/exit animation of the snackBar")
            .backgroundColorRes(R.color.colorPrimaryDark)
            .enterAnimation(
                SnackAnim.with(this)
                .animateBar()
                .duration(750)
                .alpha()
                .overshoot())
            .exitAnimation(SnackAnim.with(this)
                .animateBar()
                .duration(400)
                .accelerateDecelerate())
            .build()
    }

    private fun enterExitAnimationSlide(): Snackbar {
        return Snackbar.Builder(this)
            .gravity(Snackbar.Gravity.TOP)
            .title("Hello World!")
            .message("You can change the enter/exit animation of the snackBar")
            .backgroundColorRes(R.color.colorPrimaryDark)
            .enterAnimation(SnackAnim.with(this)
                .animateBar()
                .duration(400)
                .slideFromLeft()
                .overshoot())
            .exitAnimation(SnackAnim.with(this)
                .animateBar()
                .duration(250)
                .slideFromLeft()
                .accelerate())
            .build()
    }

    private fun iconAnimation(): Snackbar {
        return Snackbar.Builder(this)
            .gravity(Snackbar.Gravity.TOP)
            .title("Hello World!")
            .message("You can show a default icon on the left side of the with view.")
            .backgroundColorRes(R.color.colorPrimaryDark)
            .showIcon()
            .icon(R.drawable.ic_drop)
            .iconColorFilterRes(R.color.colorAccent)
            .iconAnimation(SnackAnim.with(this)
                .animateIcon()
                .pulse()
                .alpha()
                .duration(750)
                .accelerate())
            .build()
    }

    private fun showListener(): Snackbar {
        return Snackbar.Builder(this)
            .gravity(Snackbar.Gravity.BOTTOM)
            .title("Hello World!")
            .message("You can listen to events when the snackBar is shown.")
            .barShowListener(object : Snackbar.OnBarShowListener {
                override fun onShowing(bar: Snackbar) {
                    logs("Snackbar is showing", Log.ERROR)
                }

                override fun onShowProgress(bar: Snackbar, progress: Float) {
                    logs("Snackbar is showing with progress: $progress", Log.ERROR)
                }

                override fun onShown(bar: Snackbar) {
                    logs("Snackbar is shown", Log.ERROR)
                }
            })
            .build()
    }

    private fun dismissListener(): Snackbar {
        return Snackbar.Builder(this)
            .gravity(Snackbar.Gravity.BOTTOM)
            .title("Hello World!")
            .duration(500)
            .message("You can listen to events when the snackBar is dismissed.")
            .barDismissListener(object : Snackbar.OnBarDismissListener {
                override fun onDismissing(bar: Snackbar, isSwiped: Boolean) {
                    logs("Snackbar is dismissing with $isSwiped", Log.ERROR)
                }

                override fun onDismissProgress(bar: Snackbar, progress: Float) {
                    logs("Snackbar is dismissing with progress $progress", Log.ERROR)
                }

                override fun onDismissed(bar: Snackbar,
                                         event: Snackbar.DismissEvent) {
                    logs("Snackbar is dismissed with event $event", Log.ERROR)
                }
            })
            .build()
    }

    private fun barTap(): Snackbar {
        return Snackbar.Builder(this)
            .gravity(Snackbar.Gravity.TOP)
            .title("Hello World!")
            .message("You can listen to tap events inside or outside te bar.")
            .listenBarTaps(object : Snackbar.OnTapListener {
                override fun onTap(snackbar: Snackbar) {
                    logs("Bar tapped", Log.ERROR)
                }
            })
            .listenOutsideTaps(object : Snackbar.OnTapListener {
                override fun onTap(snackbar: Snackbar) {
                    logs("Outside tapped", Log.ERROR)
                }
            })
            .build()
    }

    private fun swipeToDismiss(): Snackbar {
        return Snackbar.Builder(this)
            .gravity(Snackbar.Gravity.TOP)
            .title("Hello World!")
            .message("You can swipe the snackBar to dismiss it.")
            .enableSwipeToDismiss()
            .build()
    }

    private fun barShadow(): Snackbar {
        return Snackbar.Builder(this)
            .gravity(Snackbar.Gravity.BOTTOM)
            .title("Hello World!")
            .message("You can swipe the snackBar to dismiss it.")
            .castShadow(true, 4)
            .build()
    }

    private fun vibration(): Snackbar {
        return Snackbar.Builder(this)
            .gravity(Snackbar.Gravity.BOTTOM)
            .title("Hello World!")
            .message("You can swipe the snackBar to dismiss it.")
            .vibrateOn(Snackbar.Vibration.SHOW, Snackbar.Vibration.DISMISS)
            .build()
    }
}