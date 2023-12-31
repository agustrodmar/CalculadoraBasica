package com.dam23_24.calculadora

@Suppress("SpellCheckingInspection")
class Calculo {

    var num1: Float = 0f
    var num2: Float = 0f
    var op: Int = 0
    var result: Float = 0f

    var primerNum: Boolean = true // true -> Esperando num1 / false -> Esperando num2
    var numTemp1: String = ""
    var numTemp2: String = ""
    var numCalculos: Int = 0
    
    // Variable para rastrear si se acaba de introducir una operación.
    // Se establece en 'true' cuando se introduce una operación y se restablece en 'false' cuando se introduce un número.
     
    var operacionRecienIntroducida: Boolean = false

    //
    // Variable booleana que indica si se ha mostrado un resultado.
    // Se establece en true cuando se muestra un resultado y en false cuando se introduce un nuevo dígito o una nueva operación.
    
    var controlC : Boolean = false
    /**
     * Realiza la llamada al método adecuado para realizar el cálculo solicitado en la calculadora.
     */
    fun calcular() {
        when (this.op) {
            0 -> this.suma()
            1 -> this.resta()
            2 -> this.multiplica()
            3 -> this.divide()
        }
        this.numCalculos += 1
        this.controlC = true
    }

    /**
     * Realiza la suma de num1 y num2 y lo almacena en result.
     */
    private fun suma() {
        this.result = this.num1 + this.num2
    }

    /**
     * Realiza la resta de num1 y num2 y lo almacena en result
     */
    private fun resta() {
        this.result = this.num1 - this.num2
    }

    /**
     * Realiza la multiplicación de num1 y num2 y lo almacena en result
     */
    private fun multiplica() {
        this.result = this.num1 * this.num2
    }

    /**
     * Realiza la división de num1 y num2 y lo almacena en result
     */
    private fun divide() {
        this.result = this.num1 / this.num2
    }

    /**
     * Según el parámetro num, retornará el símbolo correspondiente a la operación
     *
     * @param num operación (0 -> + / 1 -> - / 2 -> * / 3 -> /
     * @return String con el símbolo de la operación
     */
    fun operadorTxt(num: Int): String {
        return when (num) {
            0 -> "+"
            1 -> "-"
            2 -> "*"
            3 -> "/"
            else -> ""
        }
    }

    /**
     * Según this.op, retornará el símbolo correspondiente a la operación
     *
     * @return String con el símbolo de la operación
     */
    fun operadorTxt(): String {
        return when (this.op) {
            0 -> "+"
            1 -> "-"
            2 -> "*"
            3 -> "/"
            else -> ""
        }
    }

    /**
     * Actualiza y almacena la información en cadenas de caracteres (this.numTemp1 y this.numTemp2) de cada pulsación de los dígitos de los números que se usarán posteriormente en el cálculo y acabarán siendo almacenados como this.num1 y this.num2.
     *
     * @param num que corresponde al dígito del 0 al 9 pulsado o al punto decimal (10)
     */
    fun tecleaDigito(num : Int) {
        //Si es menor que 10, se trata de un dígito del 0 al 9.
        //Sino, es el punto decimal.
        this.controlC = false
        if (num < 10){
            if (this.primerNum) this.numTemp1 += num.toString()
            else this.numTemp2 += num.toString()
        }
        else {
            if (this.primerNum) {
                //Si no se tecleo ningún dígito antes del punto decimal, establecemos el valor 0.
                //Sino, lo agregamos a la cadena siempre que no exista ya con anterioridad
                if (this.numTemp1 == "") this.numTemp1 = "0."
                else this.numTemp1 += if (this.numTemp1.contains('.')) "" else "."
            }
            else {
                //Mismas acciones, pero con la cadena que va recogiendo el número 2
                if (this.numTemp2 == "") this.numTemp2 = "0."
                else this.numTemp2 += if (this.numTemp2.contains('.')) "" else "."
            }

        }
    }

    /**
     * Inicializar las características del objeto calc.
     *
     * @param resetNumCalculos indica si ponemos a 0 o no el valor de numCalculos
     */
    fun iniValores(resetNumCalculos: Boolean = true, resetResult: Boolean = true){
        this.num1 = 0f
        this.num2 = 0f
        this.op = 0
        if (resetResult) this.result = 0f
        this.primerNum = true
        this.numTemp1 = ""
        this.numTemp2 = ""
        if (resetNumCalculos) this.numCalculos = 0
    }

    /**
     * Esta función se encarga de borrar el último número introducido en la calculadora.
     * Si no hay más dígitos para borrar, muestra un mensaje de error.
     *
     * @param mainActivity La instancia de MainActivity necesaria para llamar a la función 'mensajeError'.
     */
    fun botonLimpiarC(mainActivity: MainActivity) {
        if (controlC) {
            mainActivity.mensajeError("Error: Ya se realizó el cálculo, no hay nada para borrar.")
        } else {
            if (primerNum) {
                if (numTemp1.isNotEmpty()) {
                    numTemp1 = ""
                    num1 = 0f
                } else {
                    mainActivity.mensajeError("Error: No hay dígitos en el primer número para borrar.")
                }
            } else {
                if (numTemp2.isNotEmpty()) {
                    numTemp2 = ""
                    num2 = 0f
                } else if (operacionRecienIntroducida) {
                    mainActivity.mensajeError("Error: Acabas de introducir la operación, no hay nada para borrar.")
                    operacionRecienIntroducida = false
                } else {
                    mainActivity.mensajeError("Error: No hay dígitos en el segundo número para borrar.")
                }
            }
        }
    }

    }
