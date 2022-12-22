package eu.deysouvik.quiz


import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import eu.deysouvik.quiz.Question_structure
import eu.deysouvik.quiz.R
import eu.deysouvik.quiz.databinding.ActivityMainBinding
import eu.deysouvik.quiz.datasrc
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    var Questions:ArrayList<Question_structure>?=null
    var position=0
    var indicator=0
    var question_no=1
    var question: Question_structure?=null
    var selected_option=0
    var score=0
    var submit=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        Questions = datasrc.getdata()

        position= Random.nextInt(1,45)   //45 questions are here

        question= Questions!![position-1]
        binding.tvQuestion.text=question!!.Question
        binding.image.setImageResource(question!!.image)
        binding.progressBar.setProgress(question_no)
        binding.tvProgress.text="$question_no"+"/"+binding.progressBar.max
        binding.option1.text=question!!.option1
        binding.option2.text=question!!.option2
        binding.option3.text=question!!.option3
        binding.option4.text=question!!.option4

    }

    fun default_option(){
        var options=ArrayList<TextView>()
        options.add(binding.option1)
        options.add(binding.option2)
        options.add(binding.option3)
        options.add(binding.option4)
        for(option in options){
            option.setTextColor(Color.parseColor("#FFFFFFFF"))
            option.background= ContextCompat.getDrawable(this,R.drawable.option_style) }
    }

    fun option1_click(view: View){
        if(submit==0){
            default_option()
            binding.option1.setTextColor(Color.parseColor("#fc7703"))
            binding.option1.background= ContextCompat.getDrawable(this,R.drawable.selected_option)
            selected_option=1}
    }
    fun option2_click(view: View){
        if(submit==0){
            default_option()
            binding.option2.setTextColor(Color.parseColor("#fc7703"))
            binding.option2.background= ContextCompat.getDrawable(this,R.drawable.selected_option)
            selected_option=2}
    }
    fun option3_click(view: View){
        if(submit==0){
            default_option()
            binding.option3.setTextColor(Color.parseColor("#fc7703"))
            binding.option3.background= ContextCompat.getDrawable(this, R.drawable.selected_option)
            selected_option=3}
    }
    fun option4_click(view: View){
        if(submit==0){
            default_option()
            binding.option4.setTextColor(Color.parseColor("#fc7703"))
            binding.option4.background= ContextCompat.getDrawable(this,R.drawable.selected_option)
            selected_option=4}
    }



    fun submit_click(view: View){
        if(indicator==10){
            default_option()
            reset()
        }
        else{
            submit=1
            if(selected_option==question!!.correctAns){
                score++
                when(selected_option){
                    1 -> {
                        binding.option1.background = ContextCompat.getDrawable(this, R.drawable.correct_option)
                        binding.option1.setTextColor(Color.parseColor("#40ff00"))
                    }
                    2 -> {
                        binding.option2.background = ContextCompat.getDrawable(this, R.drawable.correct_option)
                        binding.option2.setTextColor(Color.parseColor("#40ff00"))
                    }
                    3 -> {
                        binding.option3.background = ContextCompat.getDrawable(this, R.drawable.correct_option)
                        binding.option3.setTextColor(Color.parseColor("#40ff00"))
                    }
                    4 -> {
                        binding.option4.background = ContextCompat.getDrawable(this, R.drawable.correct_option)
                        binding.option4.setTextColor(Color.parseColor("#40ff00"))
                    }
                }
            }
            else{
                when(selected_option){
                    1 -> {
                        binding.option1.background = ContextCompat.getDrawable(this, R.drawable.wrong_option)
                        binding.option1.setTextColor(Color.parseColor("#ff0000"))
                    }
                    2 -> {
                        binding.option2.background = ContextCompat.getDrawable(this, R.drawable.wrong_option)
                        binding.option2.setTextColor(Color.parseColor("#ff0000"))
                    }
                    3 -> {
                        binding.option3.background = ContextCompat.getDrawable(this, R.drawable.wrong_option)
                        binding.option3.setTextColor(Color.parseColor("#ff0000"))
                    }
                    4 -> {
                        binding.option4.background = ContextCompat.getDrawable(this, R.drawable.wrong_option)
                        binding.option4.setTextColor(Color.parseColor("#ff0000"))
                    }
                }
                when(question!!.correctAns){
                    1 -> {
                        binding.option1.background = ContextCompat.getDrawable(this, R.drawable.correct_option)
                        binding.option1.setTextColor(Color.parseColor("#40ff00"))
                    }
                    2 -> {
                        binding.option2.background = ContextCompat.getDrawable(this, R.drawable.correct_option)
                        binding.option2.setTextColor(Color.parseColor("#40ff00"))
                    }
                    3 -> {
                        binding.option3.background = ContextCompat.getDrawable(this, R.drawable.correct_option)
                        binding.option3.setTextColor(Color.parseColor("#40ff00"))
                    }
                    4 -> {
                        binding.option4.background = ContextCompat.getDrawable(this, R.drawable.correct_option)
                        binding.option4.setTextColor(Color.parseColor("#40ff00"))
                    }
                }


            }
        }


    }


    fun next_click(view: View){
        position=Random.nextInt(1,45)   //45 questions are here
        question_no++
        indicator++
        submit=0
        if(indicator==10){     //only 10 question will show out of 45
            binding.option1.visibility=View.GONE
            binding.option2.visibility=View.GONE
            binding.option3.visibility=View.GONE
            binding.option4.visibility=View.GONE
            binding.nextBtn.visibility=View.GONE
            binding.image.visibility=View.GONE
            binding.progressBar.visibility=View.GONE
            binding.tvProgress.visibility=View.GONE
            binding.tvQuestion.text="Your Score: "+"${score}/10"
            binding.submitBtn.text="Play Again"
//            val intent_to_result= Intent(this,result::class.java)
//            intent_to_result.putExtra("username",username)
//            intent_to_result.putExtra("score",score)
//            intent_to_result.putExtra("fullScore",10)
//            startActivity(intent_to_result)
//            finish()
        }
        else{
            question=Questions!![position-1]
            binding.tvQuestion.text=question!!.Question
            binding.image.setImageResource(question!!.image)
            binding.progressBar.setProgress(question_no)
            binding.tvProgress.text="$question_no"+"/"+binding.progressBar.max
            binding.option1.text=question!!.option1
            binding.option2.text=question!!.option2
            binding.option3.text=question!!.option3
            binding.option4.text=question!!.option4
            default_option()
        }
    }

    fun reset(){
        binding.option1.visibility=View.VISIBLE
        binding.option2.visibility=View.VISIBLE
        binding.option3.visibility=View.VISIBLE
        binding.option4.visibility=View.VISIBLE
        binding.nextBtn.visibility=View.VISIBLE
        binding.image.visibility=View.VISIBLE
        binding.progressBar.visibility=View.VISIBLE
        binding.tvProgress.visibility=View.VISIBLE
        binding.submitBtn.text="Submit"
        indicator=0
        score=0
        question_no=1
        position= Random.nextInt(1,45)   //45 questions are here
        question= Questions!![position-1]
        binding.tvQuestion.text=question!!.Question
        binding.image.setImageResource(question!!.image)
        binding.progressBar.setProgress(question_no)
        binding.tvProgress.text="$question_no"+"/"+binding.progressBar.max
        binding.option1.text=question!!.option1
        binding.option2.text=question!!.option2
        binding.option3.text=question!!.option3
        binding.option4.text=question!!.option4
    }




}