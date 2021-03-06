package com.example.senku_app

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.widget.*
import kotlinx.android.synthetic.main.activity_tablero_cruz.*
import java.util.*

class TableroCruz : AppCompatActivity() {

    var vistas = mutableMapOf<ImageView?, Boolean?>()

    //Stack para guardar jugadas realizadas
    var pilaJugadas : Stack<ImageView> = Stack()

    // Flag retroceso jugada
    var puedeVolver: Boolean = false

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        getSupportActionBar()?.hide()

        setContentView(R.layout.activity_tablero_cruz)

        // Botón para deshacer jugadas
        val des = findViewById<ImageButton>(R.id.oneBack)

        // Texto puntaje
        val viewPuntaje = findViewById<TextView>(R.id.puntajeNumero)

        val reset = findViewById<ImageButton>(R.id.reset)

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
        vistas = mutableMapOf<ImageView?, Boolean?>(
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

        //Arreglo de todas las fichas
        val fichas = arrayOf(
            f1, f2, f3, f4, f5, f6, f7, f8, f9, f10,
            f11, f12, f13, f14, f15, f16, f17, f18, f19, f20,
            f21, f22, f23, f24, f25, f26, f27, f28, f29, f30,
            f31, f32, f33 )


        // A todas las fichas se le muestra la imagen "pin" con el color de fondo
        for (i in fichas) {
            i.setBackgroundColor(colorFondo)
            refresh(fichas,vistas)
        }

        // Variables auxiliares para las fichas
        var view1: ImageView? = null
        var view2: ImageView? = null

        // Variable para almacenar la view de la sugerencia jugada
        var viewSugerencia: ImageView? = null

        // Timer para sugerencia
        val timeToSuggest = object : CountDownTimer(5000, 1000) {

            // Se muestra sugerencia al usuario
            override fun onFinish() {
                if(view1 != null) {
                    viewSugerencia = mostrarSugerencia(view1!!, buscaMovimientosFichasVisibles(movimientos, buscarFichasVisibles(vistas)))
                }
            }
            // No es utilizada
            override fun onTick(millisUntilFinished: Long) {}
        }

        // Variable auxiliar para saber si se realiza el primer toque o segundo toque en el tablero
        var flag = true

        // Método onClickListener del botón para deshacer jugadas
        des.setOnClickListener(View.OnClickListener {
            if(puedeVolver) {
                var count = 3
                // Si hay elementos en el stack
                if (!pilaJugadas.isEmpty()) {
                    while (count > 0) {

                        // Saca el elemento top del stack y se le cambia la visibilidad
                        val t = pilaJugadas.pop()
                        if (vistas.containsKey(t)) {
                            if (vistas[t] == true) {
                                vistas[t] = false
                            } else if (vistas[t] == false) {
                                vistas[t] = true
                            }

                        }
                        --count
                    }

                    // Vacia el stack, deberia ayudar al rendimiento
                    while (!pilaJugadas.isEmpty()) {
                        pilaJugadas.pop()
                    }

                    puedeVolver = false

                    // Se actualizan las visibilidades de las fichas
                    refresh(fichas, vistas)

                    // Resta a la cantidad de movimientos realizados
                    --cantidadMovimientosRealizados
                    movimientosNumero.text = cantidadMovimientosRealizados.toString()

                    // Resta al puntaje
                    puntaje -= 15
                    viewPuntaje.text = puntaje.toString()

                    play(this, go_back)

                    // Quitar sugerencia
                    if (viewSugerencia != null) {
                        quitarSugerencia(viewSugerencia)
                    }

                }
            }
        })


        reset.setOnClickListener(View.OnClickListener {
            for(i in vistas.keys){
                vistas[i] = true
            }

            vistas[f33] = false

            pilaJugadas.clear()

            resetValores()

            viewPuntaje.text = puntaje.toString()
            movimientosNumero.text = cantidadMovimientosRealizados.toString()

            refresh(fichas, vistas)

            play(this, R.raw.start)
        })

        // Cada ficha tiene un TouchListener
        for (i in fichas) {

            i.setOnTouchListener { v, event ->
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {

                        //Si es el primer toque en la celda
                        flag = if (flag) {

                            // Obtiene la ficha correspondiente la celda tocada
                            view1 = v as ImageView?

                            // Evitar seleccionar espacios vacios
                            if(vistas[view1] == false) {
                                return@setOnTouchListener true
                            }

                            
                            play(this, select)

                            // Inicia CountDown para la sugerencia
                            timeToSuggest.start()

                            // Cambia el color de fondo a rojo para destacar que está seleccionado
                            view1?.setBackgroundColor(Color.rgb(204, 0, 0))

                            // Cambia la variable flag a false
                            false

                            // Si es el segundo toque en el tablero
                        } else {
                            // Obtiene la ficha de la celda tocada
                            view2 = v as ImageView?

                            // Se cancela el CountDown para la sugerencia
                            timeToSuggest.cancel()

                            // Llama a la función para ver si se come a la ficha o no
                            puedeVolver = verMovimientos(view1, view2, movimientos, vistas, pilaJugadas, this)

                            // Quita la sugerencia de jugada
                            quitarSugerencia(viewSugerencia)

                            // Chequea el termino del juego
                            isGameOver = gameOver(movimientos,vistas, f33, this)

                            // Actualiza puntaje en pantalla
                            viewPuntaje.text = puntaje.toString()

                            // Actualiza numero de movimientos en pantalla
                            movimientosNumero.text = cantidadMovimientosRealizados.toString()

                            if(isGameOver){
                                play(this, game_over)
                            }

                            // Vuelve el color de fondo normal de las fichas
                            view1?.setBackgroundColor(colorFondo)

                            //Actualiza visibilidad de las fichas en el tablero
                            refresh(fichas, vistas)

                            // Cambia la variable flag a true
                            true
                        }
                    }
                }
                // Retorno obligatorio del touchListener
                v?.onTouchEvent(event) ?: true
            }
        }

        // Cargar variables guardadas
        if (savedInstanceState != null) {
            cantidadMovimientosRealizados = savedInstanceState.getInt("cantidadMovimientosRealizados")
            puntaje = savedInstanceState.getInt("puntaje")

            // Actualiza puntaje en pantalla
            viewPuntaje.text = puntaje.toString()

            // Actualiza numero de movimientos en pantalla
            movimientosNumero.text = cantidadMovimientosRealizados.toString()

            isGameOver = savedInstanceState.getBoolean("isGameOver")

            if(isGameOver){
                play(this, game_over)
            }

            var i: Int = 0
            for(key in vistas.keys){
                vistas[key] = savedInstanceState.getBoolean(i.toString())
                ++i
            }

            puedeVolver = savedInstanceState.getBoolean("puedeVolver")

            if(pilaJugadas.isEmpty()){
                pilaJugadas.push(findViewById(savedInstanceState.getInt("view1")))
                pilaJugadas.push(findViewById(savedInstanceState.getInt("view2")))
                pilaJugadas.push(findViewById(savedInstanceState.getInt("view3")))
            }

            refresh(fichas, vistas)
        }

    }

    // Funcion llamada al ser destruida la actividad, en outState se guardan variables a ser
    // utilizadas en la siguiente creacion de la actividad.

    override fun onSaveInstanceState(outState: Bundle) {

        var i: Int = 0

        for(entry in vistas.entries){
            outState.putBoolean(i.toString(), entry.value!!)
            ++i
        }
        if(!pilaJugadas.isEmpty()){
            outState.putInt("view1",pilaJugadas.pop().id)
            outState.putInt("view2",pilaJugadas.pop().id)
            outState.putInt("view3",pilaJugadas.pop().id)
        }

        outState.putBoolean("puedeVolver",puedeVolver)
        outState.putInt("cantidadMovimientosRealizados", cantidadMovimientosRealizados)
        outState.putInt("puntaje", puntaje)
        outState.putBoolean("isGameOver", isGameOver)

        super.onSaveInstanceState(outState)
    }
}
