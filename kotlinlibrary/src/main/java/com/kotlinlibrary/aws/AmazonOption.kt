package com.kotlinlibrary.aws

import android.os.Parcelable
import org.jetbrains.annotations.NotNull
import androidx.annotation.NonNull
import kotlinx.android.parcel.Parcelize

class AmazonOption {
    final var awsPoolId: String
    final var awsEndPoint: String
    final var defaultBucket: String

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

    @Parcelize
    class Builder : Parcelable {
        internal var awsPoolId = ""
        internal var awsEndPoint = ""
        internal var defaultBucket = ""

        fun setPoolId(@NotNull awsPoolId: String): Builder {
            if (!awsPoolId.isEmpty()) {
                this.awsPoolId = awsPoolId
            }
            return this
        }

        fun setEndPoint(@NotNull awsEndPoint: String): Builder {
            if (!awsEndPoint.isEmpty()) {
                this.awsEndPoint = awsEndPoint
            }
            return this
        }

        fun setDefaultBucket(@NotNull defaultBucket: String): Builder {
            if (!defaultBucket.isEmpty()) {
                this.defaultBucket = defaultBucket
            }
            return this
        }

        fun build(): AmazonOption {
            return AmazonOption(this)
        }
    }
}