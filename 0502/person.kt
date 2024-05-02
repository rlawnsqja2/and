class Person {

    // 속성 property
    var name: String? = null // 타입 뒤에 ?는 해당 변수에 null 허용한다.
    // 코틀린에서는 기본 자료형은 non-null-type

    init{
        name = "장길산"
    }
    fun getName(): String? {
        return name;
    }
    fun setName(name:String){
        this.name = name
    }
}
