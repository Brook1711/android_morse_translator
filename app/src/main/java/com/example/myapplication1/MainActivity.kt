package com.example.myapplication1

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
//import com.
import com.google.android.material.textfield.TextInputLayout
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //使用activity_main.xml文件作为程序主界面
    }
    fun clickHandler(source: View)
    {
        //获取UI界面中ID为R.id.show的文本框
        var tv:TextView = findViewById<TextView>(R.id.show)
        var input_text:TextInputEditText = findViewById<TextInputEditText>(R.id.text_ready_to_morse_encode)
        tv.text = morse_encode_text(input_text.getText().toString())
    }
    fun morse_encode_text(string_ready_to_morse_encode: String):String{
        val morse_table:Map<Char,String> = mapOf('A' to ".-/",'B' to "-.../",'C' to "-.-./",'D' to "-../",
            'E' to "./",'F' to "..-./",'G' to "--./",'H' to "..../",'I' to "../",'J' to ".---/",'K' to "-.-/",
            'L' to ".-../",'M' to "--/",'N' to "-./",'O' to "---/",'P' to ".--./", 'Q' to "--.-/",'R' to ".-./",
            'S' to ".../",'T' to "-/",'U' to "..-/",'V' to "...-/",'W' to ".--/",'X' to "-..-/",'Y' to "-.--/", 'Z' to "--../",
            '0' to "-----/",'1' to ".----/",'2' to "..---/",'3' to "...--/",'4' to "....-/",'5' to "...../",
            '6' to "-..../",'7' to "--.../", '8' to "---../",'9' to "----./",
            '?' to "..--../",'/' to "-..-./", '(' to "-.--./" ,')' to "-.--.-/",'-' to "-....-/",
            '.' to ".-.-.-/",',' to "..-../", '!' to "..--./",':' to "---.../",';' to "-.-.-/",
            '+' to ".-.-./",'=' to "-...-/")
        var result_morse_string:String = ""
        for (char in string_ready_to_morse_encode){
            result_morse_string = result_morse_string.plus(morse_table[char])
            }
        return result_morse_string
    }

    fun clickHandler_voice(view: View) {
        //获取UI界面中ID为R.id.show的文本框
        var tv:TextView = findViewById<TextView>(R.id.show)
        var input_text:TextInputEditText = findViewById<TextInputEditText>(R.id.text_ready_to_morse_encode)

        val string_ready_to_morse_encode = input_text.getText().toString()
        morse_encode_voice(string_ready_to_morse_encode)
    }
    fun morse_encode_voice(string_ready_to_morse_encode: String){
        val morse_string = morse_encode_text(string_ready_to_morse_encode)
        val mPlayer_dit = MediaPlayer.create(this,R.raw.dit)
        val mPlayer_line = MediaPlayer.create(this,R.raw.line)
        for (char in morse_string){
            when(char) {
                '.' -> {
                    mPlayer_dit.start()
                    Thread.currentThread()
                    Thread.sleep(300)
                }
                '-' -> {
                    mPlayer_line.start()
                    Thread.currentThread()
                    Thread.sleep(500)
                }
                '/' -> {
                    Thread.currentThread()
                    Thread.sleep(500)
                }
            }
        }
    }
}
