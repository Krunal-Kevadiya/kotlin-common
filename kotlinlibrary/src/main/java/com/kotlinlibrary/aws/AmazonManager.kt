package com.kotlinlibrary.aws

import android.content.Context
import com.amazonaws.ClientConfiguration
import com.amazonaws.Protocol
import com.amazonaws.auth.CognitoCachingCredentialsProvider
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtilityOptions
import com.amazonaws.regions.Region
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3Client
import java.lang.ref.WeakReference

class AmazonManager private constructor(
    internal val context: WeakReference<Context>,
    internal val option: AmazonOption
) {
    private lateinit var amazons3Client: AmazonS3Client
    private lateinit var transferUtility: TransferUtility

    init {
        context.get()?.let { application ->
            amazons3Client = amazonS3Client(application.applicationContext)
            transferUtility = transferUtility(application.applicationContext)
        }
    }

    companion object {
        private var ourInstance: AmazonManager? = null
        fun getInstance(context: Context, option: AmazonOption.Builder): AmazonManager {
            if (ourInstance == null) {
                synchronized(AmazonManager::class.java) {
                    ourInstance = AmazonManager(WeakReference(context), option.build())
                }
            }
            return ourInstance!!
        }
    }

    private fun clientConfiguration(): ClientConfiguration {
        return ClientConfiguration().apply {
            maxErrorRetry = 3
            connectionTimeout = 5 * 1000
            socketTimeout = 5 * 1000
            protocol = Protocol.HTTP
        }
    }

    private fun amazonS3Client(context: Context): AmazonS3Client {
        val credentialsProvider = CognitoCachingCredentialsProvider(
            context.applicationContext, option.awsPoolId, Regions.US_EAST_1)
        val snsClient = AmazonS3Client(credentialsProvider, clientConfiguration())
        snsClient.setRegion(Region.getRegion(Regions.US_EAST_1))
        snsClient.endpoint = option.awsEndPoint
        return snsClient
    }

    private fun transferUtility(context: Context): TransferUtility {
        val tuOptions = TransferUtilityOptions()
        tuOptions.transferServiceCheckTimeInterval = 5 * 60 * 1000
        tuOptions.transferThreadPoolSize = 10
        return TransferUtility.builder()
            .context(context.applicationContext)
            .s3Client(amazonS3Client(context))
            .defaultBucket(option.defaultBucket)
            .transferUtilityOptions(tuOptions)
            .build()
    }

    fun getAmazonS3Client() = amazons3Client

    fun getTransferUtility() = transferUtility
}
