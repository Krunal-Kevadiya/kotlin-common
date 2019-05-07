package com.kotlinlibrary.aws

import android.os.Parcelable
import org.jetbrains.annotations.NotNull
import androidx.annotation.NonNull
import kotlinx.android.parcel.Parcelize

class AmazonOption {
    var awsPoolId: String
    var awsEndPoint: String
    var defaultBucket: String

    constructor(awsPoolId: String, awsEndPoint: String, defaultBucket: String) {
        this.awsPoolId = awsPoolId
        this.awsEndPoint = awsEndPoint
        this.defaultBucket = defaultBucket
    }

    constructor(@NonNull builder: Builder) {
        this.awsPoolId = builder.awsPoolId
        this.awsEndPoint = builder.awsEndPoint
        this.defaultBucket = builder.defaultBucket
    }

    @Suppress("PLUGIN_WARNING")
    @Parcelize
    class Builder : Parcelable {
        internal var awsPoolId = ""
        internal var awsEndPoint = ""
        internal var defaultBucket = ""

        fun setPoolId(@NotNull awsPoolId: String): Builder {
            if (awsPoolId.isNotEmpty()) {
                this.awsPoolId = awsPoolId
            }
            return this
        }

        fun setEndPoint(@NotNull awsEndPoint: String): Builder {
            if (awsEndPoint.isNotEmpty()) {
                this.awsEndPoint = awsEndPoint
            }
            return this
        }

        fun setDefaultBucket(@NotNull defaultBucket: String): Builder {
            if (defaultBucket.isNotEmpty()) {
                this.defaultBucket = defaultBucket
            }
            return this
        }

        fun build(): AmazonOption {
            return AmazonOption(this)
        }
    }
}