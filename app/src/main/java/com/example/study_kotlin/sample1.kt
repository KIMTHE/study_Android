package com.example.study_kotlin

fun main()
{
    println(add(4,5))

    //3. String Template
    val name = "MIN su"
    val lastname = "kim"
    println("my name is ${name+lastname}.")

    forAndWhile()
    ignoreNull("a")
}

//1. 함수

fun helloworld()
{
    println("Hello world!")
}

fun add(a : Int, b : Int) : Int{
    return a+b
}

//2. var, val

fun hi()
{
    val a: Int = 10 //value 상수
    var b : Int = 9 //variable 변수

    b = 100
    var c= 100
    var name = "minsu"
}

//4. 조건식

// 기본 if else는 동일

// c언어 에서의 a?b:c
fun maxBy(a : Int, b:Int) = if (a<b) a else b

//switch
fun checkNum(score : Int) {
    when(score){
        0 -> println("0")
        1,2 -> println("1 or 2")
        in 10..29 -> println("good")
    }

    var a = when(score){
        0 -> 0
        1,2 -> 1
        else -> 2
    }

}

// ※Expression vs Statement
//
//Expression은 return값이 있는것, kotlin에서 모든 함수는 Expression인데, return형이 없는 것처럼 보여도 Unit형을 return한다고 친다.
//하지만 java에서는 void형을 return하는 함수는 Statement로 취급한다.
//Statement는 return이 없는것, 예를 들면 값을 할당하지않는 when
//java에서는 if문이 Statement였지만, kotlin에서는 Expression으로도 사용가능함

//5. Array and List

//Array는 처음에 크기를 할당하면 변경불가

//List는 2가지 1.immutable 수정불가능 2. mutable 수정가능으로 나뉨

fun array(){
    val array : Array<Int> = arrayOf(1,2,3)
    val list: List<Int> = listOf(1,2,3) //immutable

    val array2 = arrayOf(1,"d",3.4f)
    val list2 = listOf(1,"d",11L)

    array[0]=3
    var result = list.get(0)

    val arraylist = arrayListOf<Int>() //mutable
    arraylist.add(10)
    arraylist.add(20)
}

//6. For/ While

fun forAndWhile(){
    val students = arrayListOf("joyce","james","jenny","jenifer")

    for(name in students){
        println("${name}")
    }

    for((index,name) in students.withIndex()){
        println("${index+1}번째 학생: ${name}")
    }

    var sum : Int = 0
    for(i in 1..10 step 2) { //1,3,5,7,9
        sum += i
    }
    println(sum)

    sum  = 0
    for(i in 10 downTo 1) { //10 9 8 7 ... 1
        sum += i
    }
    println(sum)

    sum  = 0
    for(i in 1 until 100) { //1... 99 (<100)
        sum += i
    }
    println(sum)

    var index = 0
    while(index<10){
        println("current index : ${index}")
        index++
    }

}

//7. Nullable / NonNull

fun nullcheck(){
    //NPE : null pointer Exception -> java에서는 compile 에서는 잡아내지못하고, run-time에서 나옴
    //kotlin에서는 이를 보완

    var nullName : String? = null //type에 ?을 붙이면 nullable type이 된다

    var name : String= "minsu"
    var nameInUpperCase = name.toUpperCase()

    var nullNameInUpperCase = nullName?.toUpperCase() //?을 붙임으로서 null인지 아닌지 검사하게함
    //nullable일 경우 null값을 반환함, ?을 많이 사용하도록 하자

    // ?:

    val lastName : String? = null
    val fullName = name+" "+(lastName?: "No lastName")
    //lastName이 null인지 체크해서, null이면 뒤에것을 반환
    println(fullName)

}

//!!
fun ignoreNull(str :  String?){
    val mNotNull = str!! //!!을 추가함으로써, str은 nullable 일리가 없다는것을 컴파일러에게 각인함
    val upper = mNotNull.toUpperCase() //하지만, 확실하지 않은이상, !!사용은 지양

    val email : String? = "kcms2369@nana.vom"
    email?.let{ //email이 null type이 아닐때, 안의 내용을 수행
        println("my email is ${email}")
    }
}