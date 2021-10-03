package com.example.test_ocr

import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.googlecode.tesseract.android.TessBaseAPI
import java.io.*

class MainActivity : AppCompatActivity() {
    private lateinit var ocr_start_button: Button
    lateinit var ocr_result_view: TextView
    lateinit var tess: TessBaseAPI //Tesseracts API 객체 생성
    var dataPath: String = "" //데이터 경로 변수 선언

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dataPath = "$filesDir/tesseract/" //언어데이터의 경로 미리 지정

        //checkFile(File(dataPath + "tessdata/"), "kor") //사용할 언어파일의 이름 지정
        checkFile(File(dataPath + "tessdata/"), "eng")

        val lang: String = "eng"
        tess = TessBaseAPI() //api준비
        tess.init(dataPath, lang) //해당 사용할 언어데이터로 초기화

        // OCR 동작 버튼
        ocr_start_button = findViewById(R.id.ocr_start_button)
        ocr_start_button.setOnClickListener {

            processImage(
                BitmapFactory.decodeResource(
                    resources,
                    R.drawable.sample_burrito
                )
            ) //이미지 가공후 텍스트뷰에 띄우기

        }

    }

    private fun copyFile(lang: String) {
        try {
            //언어데이타파일의 위치
            var filePath: String = dataPath + "/tessdata/" + lang + ".traineddata"

            //AssetManager를 사용하기 위한 객체 생성
            var assetManager: AssetManager = assets;

            //byte 스트림을 읽기 쓰기용으로 열기
            var inputStream: InputStream = assetManager.open("tessdata/" + lang + ".traineddata")
            var outStream: OutputStream = FileOutputStream(filePath)


            //위에 적어둔 파일 경로쪽으로 해당 바이트코드 파일을 복사한다.
            var buffer: ByteArray = ByteArray(1024)

            var read: Int = 0
            read = inputStream.read(buffer)
            while (read != -1) {
                outStream.write(buffer, 0, read)
                read = inputStream.read(buffer)
            }
            outStream.flush()
            outStream.close()
            inputStream.close()

        } catch (e: FileNotFoundException) {
            Log.v("오류발생", e.toString())
        } catch (e: IOException) {
            Log.v("오류발생", e.toString())
        }
    }

    private fun checkFile(dir: File, lang: String) {

        //파일의 존재여부 확인 후 내부로 복사
        if (!dir.exists() && dir.mkdirs()) {
            copyFile(lang)
        }

        if (dir.exists()) {
            var datafilePath: String = dataPath + "/tessdata/" + lang + ".traineddata"
            var dataFile: File = File(datafilePath)
            if (!dataFile.exists()) {
                copyFile(lang)
            }
        }

    }

    private fun processImage(bitmap : Bitmap){
        ocr_result_view = findViewById(R.id.ocr_result_view)

        Toast.makeText(applicationContext,"잠시 기다려 주세요",Toast.LENGTH_SHORT).show()
        var ocrResult : String? = null;
        tess.setImage(bitmap)
        ocrResult = tess.utF8Text
        ocr_result_view.text = ocrResult
    }
}
