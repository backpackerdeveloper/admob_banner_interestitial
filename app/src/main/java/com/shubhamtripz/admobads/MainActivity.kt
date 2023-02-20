package com.shubhamtripz.admobads

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class MainActivity : AppCompatActivity() {

    private var mInterstitialAd: InterstitialAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adRequest = AdRequest.Builder().build()
        MobileAds.initialize(this) {}
        val mAdView: AdView = findViewById(R.id.adView)
        mAdView.loadAd(adRequest)

        val btn = findViewById<Button>(R.id.adbtn)
        btn.setOnClickListener {
            loadinter()
        }

        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {

                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {

                mInterstitialAd = interstitialAd
            }
        })
    }

private fun loadinter(){
    if (mInterstitialAd != null) {
        mInterstitialAd?.show(this)
    } else {
        Log.d("TAG", "The interstitial ad wasn't ready yet.")
    }

}

}