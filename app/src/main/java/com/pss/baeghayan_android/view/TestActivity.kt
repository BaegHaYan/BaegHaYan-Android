package com.pss.baeghayan_android.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.pss.baeghayan_android.R
import com.pss.baeghayan_android.base.BaseActivity
import com.pss.baeghayan_android.databinding.ActivityTestBinding

class TestActivity : BaseActivity<ActivityTestBinding>(R.layout.activity_test) {
    private lateinit var speechRecognizer: SpeechRecognizer
    private lateinit var recognitionListener: RecognitionListener

    override fun init() {
        requestPermission()
        var intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, packageName)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR")
        setListener()
        binding.btnStart.setOnClickListener {
            speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)
            speechRecognizer.setRecognitionListener(recognitionListener)
            speechRecognizer.startListening(intent)
        }
    }

    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.RECORD_AUDIO
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), 0)
        }
    }

    private fun setListener(){
        recognitionListener = object : RecognitionListener{
            override fun onReadyForSpeech(p0: Bundle?) {
               shortShowToast("음성인식 시작")
            }

            override fun onBeginningOfSpeech() {

            }

            override fun onRmsChanged(p0: Float) {
            }

            override fun onBufferReceived(p0: ByteArray?) {
            }

            override fun onEndOfSpeech() {
            }

            override fun onError(p0: Int) {
                shortShowToast("에러발생")
            }

            override fun onResults(p0: Bundle?) {
                var matches: ArrayList<String> = p0?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION) as ArrayList<String>
                for (i in 0 until matches.size) {
                    binding.tvResult.text = matches[i]
                }

            }

            override fun onPartialResults(p0: Bundle?) {
                Log.d("로그","partialresult : $p0")
            }

            override fun onEvent(p0: Int, p1: Bundle?) {
                Log.d("로그","event : $p0, $p1")
            }

        }
    }
}