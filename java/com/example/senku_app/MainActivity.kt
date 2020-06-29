package com.example.senku_app

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var cantidadMovimientosRealizados: Int = 0
    var isGameOver: Boolean = false
    var timeToSuggest = object : CountDownTimer(10000, 1000) {

        // Se muestra sugerencia al usuario
        override fun onFinish() {
            //mostrarSugerencia() - FALTA IMPLEMENTAR
            Toast.makeText(applicationContext," SUGERENCIA AHORA! ", Toast.LENGTH_SHORT).show()
        }

        // No es utilizada aun
        override fun onTick(millisUntilFinished: Long) {
        }
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Se obtienen los LinearLayout

        val v13 = findViewById<LinearLayout>(R.id.v1_3)
        val v14 = findViewById<LinearLayout>(R.id.v1_4)
        val v15 = findViewById<LinearLayout>(R.id.v1_5)

        val v23 = findViewById<LinearLayout>(R.id.v2_3)
        val v24 = findViewById<LinearLayout>(R.id.v2_4)
        val v25 = findViewById<LinearLayout>(R.id.v2_5)

        val v31 = findViewById<LinearLayout>(R.id.v3_1)
        val v32 = findViewById<LinearLayout>(R.id.v3_2)
        val v33 = findViewById<LinearLayout>(R.id.v3_3)
        val v34 = findViewById<LinearLayout>(R.id.v3_4)
        val v35 = findViewById<LinearLayout>(R.id.v3_5)
        val v36 = findViewById<LinearLayout>(R.id.v3_6)
        val v37 = findViewById<LinearLayout>(R.id.v3_7)

        val v41 = findViewById<LinearLayout>(R.id.v4_1)
        val v42 = findViewById<LinearLayout>(R.id.v4_2)
        val v43 = findViewById<LinearLayout>(R.id.v4_3)
        val v44 = findViewById<LinearLayout>(R.id.v4_4)
        val v45 = findViewById<LinearLayout>(R.id.v4_5)
        val v46 = findViewById<LinearLayout>(R.id.v4_6)
        val v47 = findViewById<LinearLayout>(R.id.v4_7)

        val v51 = findViewById<LinearLayout>(R.id.v5_1)
        val v52 = findViewById<LinearLayout>(R.id.v5_2)
        val v53 = findViewById<LinearLayout>(R.id.v5_3)
        val v54 = findViewById<LinearLayout>(R.id.v5_4)
        val v55 = findViewById<LinearLayout>(R.id.v5_5)
        val v56 = findViewById<LinearLayout>(R.id.v5_6)
        val v57 = findViewById<LinearLayout>(R.id.v5_7)

        val v63 = findViewById<LinearLayout>(R.id.v6_3)
        val v64 = findViewById<LinearLayout>(R.id.v6_4)
        val v65 = findViewById<LinearLayout>(R.id.v6_5)

        val v73 = findViewById<LinearLayout>(R.id.v7_3)
        val v74 = findViewById<LinearLayout>(R.id.v7_4)
        val v75 = findViewById<LinearLayout>(R.id.v7_5)


        val f1 = findViewById<ImageView>(R.id.f1)
        val f2 = findViewById<ImageView>(R.id.f2)
        val f3 = findViewById<ImageView>(R.id.f3)
        val f4 = findViewById<ImageView>(R.id.f4)
        val f5 = findViewById<ImageView>(R.id.f5)
        val f6 = findViewById<ImageView>(R.id.f6)
        val f7 = findViewById<ImageView>(R.id.f7)
        val f8 = findViewById<ImageView>(R.id.f8)
        val f9 = findViewById<ImageView>(R.id.f9)

        val f10 = findViewById<ImageView>(R.id.f10)
        val f11 = findViewById<ImageView>(R.id.f11)
        val f12 = findViewById<ImageView>(R.id.f12)
        val f13 = findViewById<ImageView>(R.id.f13)
        val f14 = findViewById<ImageView>(R.id.f14)
        val f15 = findViewById<ImageView>(R.id.f15)
        val f16 = findViewById<ImageView>(R.id.f16)
        val f17 = findViewById<ImageView>(R.id.f17)
        val f18 = findViewById<ImageView>(R.id.f18)
        val f19 = findViewById<ImageView>(R.id.f19)

        val f20 = findViewById<ImageView>(R.id.f20)
        val f21 = findViewById<ImageView>(R.id.f21)
        val f22 = findViewById<ImageView>(R.id.f22)
        val f23 = findViewById<ImageView>(R.id.f23)
        val f24 = findViewById<ImageView>(R.id.f24)
        val f25 = findViewById<ImageView>(R.id.f25)
        val f26 = findViewById<ImageView>(R.id.f26)
        val f27 = findViewById<ImageView>(R.id.f27)
        val f28 = findViewById<ImageView>(R.id.f28)

        val f29 = findViewById<ImageView>(R.id.f29)
        val f30 = findViewById<ImageView>(R.id.f30)
        val f31 = findViewById<ImageView>(R.id.f31)
        val f32 = findViewById<ImageView>(R.id.f32)
        val f33 = findViewById<ImageView>(R.id.f33)

        // Arreglo de todas las celdas
        val celdas = arrayOf(
            v13, v14, v15, v23, v24, v25, v31, v32, v33, v34,
            v35, v36, v37, v41, v42, v43, v44, v45, v46, v47,
            v51, v52, v53, v54, v55, v56, v57, v63, v64, v65,
            v73, v74, v75
        )

        //Arreglo de todas las fichas
        val fichas = arrayOf(
            f1, f2, f3, f4, f5, f6, f7, f8, f9, f10,
            f11, f12, f13, f14, f15, f16, f17, f18, f19, f20,
            f21, f22, f23, f24, f25, f26, f27, f28, f29, f30,
            f31, f32, f33
        )


        //Mapa<Celda, Ficha> que relaciona las celdas con las fichas, donde cada celda tien una ficha

        val pares = mapOf(
            Pair(v13, f1), Pair(v14, f2), Pair(v15, f3), Pair(v23, f4),
            Pair(v24, f5), Pair(v25, f6), Pair(v31, f7), Pair(v32, f8),
            Pair(v33, f9), Pair(v34, f10), Pair(v35, f11), Pair(v36, f12),
            Pair(v37, f13), Pair(v41, f14), Pair(v42, f15), Pair(v43, f16),
            Pair(v44, f33), Pair(v45, f17), Pair(v46, f18), Pair(v47, f19),
            Pair(v51, f20), Pair(v52, f21), Pair(v53, f22), Pair(v54, f23),
            Pair(v55, f24), Pair(v56, f25), Pair(v57, f26), Pair(v63, f27),
            Pair(v64, f28), Pair(v65, f29), Pair(v73, f30), Pair(v74, f31),
            Pair(v75, f32)
        )

        // A todas las fichas se le muestra la imagen "pin"
        for (i in fichas) {

            i.setImageResource(R.drawable.pin)
        }


        // La ficha del medio del tablero es invisible
        f33.visibility = View.INVISIBLE

        // Este mapa es interesante, almacena cada ficha con todos los posibles movimientos que puede tener en el tablero.
        // La key del mapa es la ImageView correspondiente a la ficha, el value es un arreglo de pares, donde el primero elemento del par
        // Corresponde a la ficha contigua a la ficha key y el segundo elemento es la ficha a donde puede llegar la ficha si se la come.
        val movimientos = mapOf<ImageView, Array<Pair<ImageView, ImageView>>>(
            Pair(f1, arrayOf(Pair(f4, f9), Pair(f2, f3))),
            Pair(f2, arrayOf(Pair(f5, f10))),
            Pair(f3, arrayOf(Pair(f6, f11), Pair(f2, f1))),
            Pair(f4, arrayOf(Pair(f5, f6), Pair(f9, f16))),
            Pair(f5, arrayOf(Pair(f10, f33))),
            Pair(f6, arrayOf(Pair(f5, f4), Pair(f11, f17))),
            Pair(f7, arrayOf(Pair(f14, f20), Pair(f8, f9))),
            Pair(f8, arrayOf(Pair(f15, f21), Pair(f9, f10))),
            Pair(f9, arrayOf(Pair(f8, f7), Pair(f16, f22), Pair(f4, f1), Pair(f10, f11))),
            Pair(f10, arrayOf(Pair(f9, f8), Pair(f5, f2), Pair(f33, f23), Pair(f11, f12))),
            Pair(f11, arrayOf(Pair(f10, f9), Pair(f6, f3), Pair(f17, f24), Pair(f12, f13))),
            Pair(f12, arrayOf(Pair(f11, f10), Pair(f18, f25))),
            Pair(f13, arrayOf(Pair(f12, f11), Pair(f19, f26))),
            Pair(f14, arrayOf(Pair(f15, f16))),
            Pair(f15, arrayOf(Pair(f16, f33))),
            Pair(f16, arrayOf(Pair(f15, f14), Pair(f33, f17), Pair(f9, f4), Pair(f22, f27))),
            Pair(f33, arrayOf(Pair(f16, f15), Pair(f10, f5), Pair(f23, f28), Pair(f17, f18))),
            Pair(f17, arrayOf(Pair(f33, f16), Pair(f11, f6), Pair(f24, f29), Pair(f18, f19))),
            Pair(f18, arrayOf(Pair(f17, f33))),
            Pair(f19, arrayOf(Pair(f18, f17))),
            Pair(f20, arrayOf(Pair(f14, f7), Pair(f21, f22))),
            Pair(f21, arrayOf(Pair(f15, f8), Pair(f22, f23))),
            Pair(f22, arrayOf(Pair(f21, f20), Pair(f16, f9), Pair(f23, f24), Pair(f27, f30))),
            Pair(f23, arrayOf(Pair(f22, f21), Pair(f33, f10), Pair(f24, f25), Pair(f28, f31))),
            Pair(f24, arrayOf(Pair(f23, f22), Pair(f17, f11), Pair(f25, f26), Pair(f29, f32))),
            Pair(f25, arrayOf(Pair(f24, f23), Pair(f18, f12))),
            Pair(f26, arrayOf(Pair(f25, f24), Pair(f19, f13))),
            Pair(f27, arrayOf(Pair(f22, f16), Pair(f28, f29))),
            Pair(f28, arrayOf(Pair(f23, f33))),
            Pair(f29, arrayOf(Pair(f28, f27), Pair(f24, f17))),
            Pair(f30, arrayOf(Pair(f27, f22), Pair(f31, f32))),
            Pair(f31, arrayOf(Pair(f28, f23))),
            Pair(f32, arrayOf(Pair(f31, f30), Pair(f29, f24)))
        )

        // Mapa<Ficha, Booleano> para saber si las fichas están visibles o no
        val vistas = mutableMapOf(
            Pair(f1, true),
            Pair(f2, true),
            Pair(f3, true),
            Pair(f4, true),
            Pair(f5, true),
            Pair(f6, true),
            Pair(f7, true),
            Pair(f8, true),
            Pair(f9, true),
            Pair(f10, true),
            Pair(f11, true),
            Pair(f12, true),
            Pair(f13, true),
            Pair(f14, true),
            Pair(f15, true),
            Pair(f16, true),
            Pair(f33, false),
            Pair(f17, true),
            Pair(f18, true),
            Pair(f19, true),
            Pair(f20, true),
            Pair(f21, true),
            Pair(f22, true),
            Pair(f23, true),
            Pair(f24, true),
            Pair(f25, true),
            Pair(f26, true),
            Pair(f27, true),
            Pair(f28, true),
            Pair(f29, true),
            Pair(f30, true),
            Pair(f31, true),
            Pair(f32, true)
        )


        //Variables auxiliares para las fichas
        var view1: ImageView? = null
        var view2: ImageView? = null

        // Variable auxiliar para saber si se realiza el primer toque o segundo toque en el tablero
        var flag = true


        // Cada celda tiene un TouchListener
        for (i in celdas) {

            i.setOnTouchListener { v, event ->
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {

                        //Si es el primer toque en la celda
                        flag = if (flag) {

                            // Obtiene la ficha correspondiente la celda tocada
                            view1 = pares[v]

                            // Inicia CountDown para sugerencia
                            timeToSuggest.start()

                            // Cambia el color de fondo a rojo para destacar que está seleccionado
                            view1?.setBackgroundColor(Color.rgb(204, 0, 0))

                            // Cambia la variable flag a false
                            false
                        // Si es el segundo toque en el tablero
                        } else {

                            // Obtiene la ficha de la celda tocada
                            view2 = pares[v]



                            // Llama a la función para ver si se come a la ficha o no
                            verMovimientos(view1, view2, movimientos, vistas)

                            // Actualiza numero de movimientos en pantalla
                            buttonPanel_button0.text = cantidadMovimientosRealizados.toString()

                            // Chequea el termino del juego
                            isGameOver = gameOver(movimientos,vistas)

                            if(isGameOver){
                                Toast.makeText(applicationContext," GAME OVER ", Toast.LENGTH_LONG).show()
                            }

                            // Vuelve el color de fondo normal de las fichas
                            view1?.setBackgroundColor(Color.rgb(172, 110, 90))

                            //Actualiza visibilidad de las fichas
                            for (j in fichas) {

                                if (vistas[j] == true) {

                                    j.visibility = View.VISIBLE

                                } else {

                                    j.visibility = View.INVISIBLE
                                }
                            }

                            // Cambia la variable flag a true
                            true
                        }
                    }
                }
                // Retorno obligatorio del touchListener
                v?.onTouchEvent(event) ?: true
            }
        }
    }


    // Busca las fichas aun visibles en el tablero
    fun buscarFichasVisibles(
        vistas: MutableMap<ImageView, Boolean>
    ): Map<ImageView, Boolean> {
        val fichasVisibles: Map<ImageView, Boolean> = vistas.filter { (k,v) -> v == true }
        return fichasVisibles
    }

    // Recoleta movimientos posibles por las fichas aun visibles
    fun buscaMovimientosFichasVisibles(
        Movimientos: Map<ImageView, Array<Pair<ImageView, ImageView>>>,
        fichasVisibles: Map<ImageView, Boolean>
    ): MutableMap<ImageView, Array<Pair<ImageView, ImageView>>> {

        val movimientosFichasVisibles = mutableMapOf<ImageView, Array<Pair<ImageView, ImageView>>>()
        val keysFichasVisibles = fichasVisibles.keys

        for (key in keysFichasVisibles) {

            if(Movimientos.keys.contains(key)){

                // arreglo auxiliar
                val array = Movimientos[key]

                // Chequeo solicitado por android studio
                if (array != null) {
                    movimientosFichasVisibles[key] = array
                }
            }
        }
        return movimientosFichasVisibles
    }

    fun buscaMovimientosPosibles(
        movimientosFichasVisibles: MutableMap<ImageView, Array<Pair<ImageView, ImageView>>>,
        fichasVisibles: Map<ImageView, Boolean>
    ): Int{

        var cantMovimientosPosibles:Int = 0
        val listMovimientos = movimientosFichasVisibles.values.toList()

        // Para cada arreglo en la lista
        for(array in listMovimientos) {

            //Para cada movimiento en cada arreglo
            for (movimiento in array) {

                // Si la ficha contigua es visible, y la ficha destino no lo es
                if (fichasVisibles.contains(movimiento.first) && !fichasVisibles.contains(movimiento.second)) {

                    //Entonces el movimiento es posible
                    cantMovimientosPosibles++
                }
            }
        }
        return cantMovimientosPosibles
    }


    // Funcion para detener o continuar el juego
    fun gameOver( Movimientos: Map<ImageView, Array<Pair<ImageView, ImageView>>>, vistas: MutableMap<ImageView, Boolean> ): Boolean {
        val fichasVisibles = buscarFichasVisibles(vistas)
        val movimientosFichasVisibles = buscaMovimientosFichasVisibles(Movimientos,fichasVisibles)
        val cantMovimientosRestantes :Int = buscaMovimientosPosibles(movimientosFichasVisibles,fichasVisibles)

        // Si no quedan movimientos se acaba el juego
        if(cantMovimientosRestantes == 0){
            return true
        }
        return false
    }


    // Esta función es la que comprueba si se hace una jugada que come a una ficha, recibe las dos fichas que se tocaron
    // El mapa con los movimientos válidos de la ficha y el mapa con los estados visibles de las fichas

    fun verMovimientos(
        i: ImageView?,
        f: ImageView?,
        m: Map<ImageView, Array<Pair<ImageView, ImageView>>>,
        v: MutableMap<ImageView, Boolean>
    ) {
        // Obtiene el arreglo de Pares con las fichas contiguas a la ficha "i"
        val arreglo = m[i]



        // Esta condición la pide android studio para asegurarse de que no se revisa un arreglo nulo, pero como está
        // programado eso nunca ocurre
        if (arreglo != null) {

            // Por cada par en el arreglo (mínimo 1, máximo 4)
            for (k in arreglo) {

                // Obtiene visibilidad de la ficha contigua a "i" Si es visible retorna true, en caso contrario false
                // Se tiene el operador elvis en caso de que sea null, pero eso no ocurre (cosas que pide android studio)
                val v1: Boolean = v[k.first] ?: false

                // Obtiene visibilidad de la ficha contigua al ficha contigua Si es visible retorna true, en caso contrario false
                // Acá debe ser false para que pueda comerse la ficha contigua
                val v2: Boolean = v[f] ?: true

                // Si la ficha contigua es visible, la contigua a esta es invisible y resulta que la ficha "f" que se tocó es igual a esta:
                if (v1 && !v2 && f == k.second) {

                    // Se come la ficha contigua y "avanza" la ficha "i" a la posición de "f"
                    // Para eso se quita la visibilidad a "i"
                    if (i != null) v[i] = false

                    // Se quita la visibilidad a la ficha contigua que se comió
                    v[k.first] = false

                    // y se hace visible la ficha "f"
                    v[f] = true

                    // añade un movimiento al contador
                    cantidadMovimientosRealizados++
                }
            }
        }
    }
}


