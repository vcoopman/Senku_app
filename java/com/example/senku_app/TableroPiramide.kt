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
import kotlinx.android.synthetic.main.activity_tablero_piramide.*
import java.util.*

class TableroPiramide : AppCompatActivity() {

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

        setContentView(R.layout.activity_tablero_piramide)

        // Botón para deshacer jugadas
        val des = findViewById<ImageButton>(R.id.oneBackp)
        // Texto puntaje
        val viewPuntaje = findViewById<TextView>(R.id.puntajeNumerop)

        val reset = findViewById<ImageButton>(R.id.resetp)

        val ff1 = findViewById<ImageView>(R.id.ff1)
        val ff2 = findViewById<ImageView>(R.id.ff2)
        val ff3 = findViewById<ImageView>(R.id.ff3)
        val ff4 = findViewById<ImageView>(R.id.ff4)
        val ff5 = findViewById<ImageView>(R.id.ff5)
        val ff6 = findViewById<ImageView>(R.id.ff6)
        val ff7 = findViewById<ImageView>(R.id.ff7)
        val ff8 = findViewById<ImageView>(R.id.ff8)
        val ff9 = findViewById<ImageView>(R.id.ff9)

        val ff10 = findViewById<ImageView>(R.id.ff10)
        val ff11 = findViewById<ImageView>(R.id.ff11)
        val ff12 = findViewById<ImageView>(R.id.ff12)
        val ff13 = findViewById<ImageView>(R.id.ff13)
        val ff14 = findViewById<ImageView>(R.id.ff14)
        val ff15 = findViewById<ImageView>(R.id.ff15)


        // Este mapa es interesante, almacena cada ficha con todos los posibles movimientos que puede tener en el tablero.
        // La key del mapa es la ImageView correspondiente a la ficha, el value es un arreglo de pares, donde el primero elemento del par
        // Corresponde a la ficha contigua a la ficha key y el segundo elemento es la ficha a donde puede llegar la ficha si se la come.

        val movimientos = mapOf<ImageView, Array<Pair<ImageView, ImageView>>>(

            Pair(ff1, arrayOf(Pair(ff2, ff4), Pair(ff3, ff6))),
            Pair(ff2, arrayOf(Pair(ff4, ff7), Pair(ff5, ff9))),
            Pair(ff3, arrayOf(Pair(ff6, ff10), Pair(ff5, ff8))),
            Pair(ff4, arrayOf(Pair(ff2, ff1), Pair(ff5, ff6), Pair(ff7, ff11), Pair(ff8, ff13))),
            Pair(ff5, arrayOf(Pair(ff8, ff12), Pair(ff9, ff14))),
            Pair(ff6, arrayOf(Pair(ff3, ff1), Pair(ff5, ff4), Pair(ff9, ff13), Pair(ff10, ff15))),
            Pair(ff7, arrayOf(Pair(ff4, ff2), Pair(ff8, ff9))),
            Pair(ff8, arrayOf(Pair(ff5, ff3), Pair(ff9, ff10))),
            Pair(ff9, arrayOf(Pair(ff5, ff2), Pair(ff8, ff7))),
            Pair(ff10, arrayOf(Pair(ff6, ff3), Pair(ff9, ff8))),
            Pair(ff11, arrayOf(Pair(ff7, ff4), Pair(ff12, ff13))),
            Pair(ff12, arrayOf(Pair(ff8, ff5), Pair(ff13, ff14))),
            Pair(ff13, arrayOf(Pair(ff8, ff4), Pair(ff9, ff6), Pair(ff12, ff11), Pair(ff14, ff15))),
            Pair(ff14, arrayOf(Pair(ff9, ff5), Pair(ff13, ff12))),
            Pair(ff15, arrayOf(Pair(ff14, ff13), Pair(ff10, ff6)))
        )

        // Mapa<Ficha, Booleano> para saber si las fichas están visibles o no
        vistas = mutableMapOf<ImageView?, Boolean?>(
            Pair(ff1, false),
            Pair(ff2, true),
            Pair(ff3, true),
            Pair(ff4, true),
            Pair(ff5, true),
            Pair(ff6, true),
            Pair(ff7, true),
            Pair(ff8, true),
            Pair(ff9, true),
            Pair(ff10, true),
            Pair(ff11, true),
            Pair(ff12, true),
            Pair(ff13, true),
            Pair(ff14, true),
            Pair(ff15, true)

        )


        //Arreglo de todas las fichas
        val fichas = arrayOf(
            ff1, ff2, ff3, ff4, ff5, ff6, ff7, ff8, ff9, ff10,
            ff11, ff12, ff13, ff14, ff15)

        // A todas las fichas se le muestra la imagen "pin" con el color de fondo
        for (i in fichas) {
            i.setBackgroundColor(colorFondo)
            refresh(fichas,vistas)
        }

        //Variables auxiliares para las fichas
        var view1: ImageView? = null
        var view2: ImageView? = null

        // Variable para almacenar la view de la sugerencia jugada
        var viewSugerencia: ImageView? = null

        // Timer para sugerencia
        var timeToSuggest = object : CountDownTimer(5000, 1000) {
            override fun onFinish() {
                // Se muestra sugerencia al usuario
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
                    movimientosNumerop.text = cantidadMovimientosRealizados.toString()

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

            vistas[ff1] = false

            pilaJugadas.clear()

            resetValores()

            viewPuntaje.text = puntaje.toString()
            movimientosNumerop.text = cantidadMovimientosRealizados.toString()

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

                            // Inicia CountDown para sugerencia
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
                            isGameOver = gameOver(movimientos,vistas, ff1, this)

                            // Actualiza puntaje en pantalla
                            viewPuntaje.text = puntaje.toString()

                            // Actualiza numero de movimientos en pantalla
                            movimientosNumerop.text = cantidadMovimientosRealizados.toString()

                            if(isGameOver){
                                play(this, game_over)
                            }

                            // Vuelve el color de fondo normal de las fichas
                            view1?.setBackgroundColor(colorFondo)

                            //Actualiza visibilidad de las fichas
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
            movimientosNumerop.text = cantidadMovimientosRealizados.toString()

            isGameOver = savedInstanceState.getBoolean("isGameOver")

            if(isGameOver){
                Toast.makeText(applicationContext," GAME OVER ", Toast.LENGTH_LONG).show()
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

        outState.putInt("cantidadMovimientosRealizados", cantidadMovimientosRealizados)
        outState.putInt("puntaje", puntaje)
        outState.putBoolean("isGameOver", isGameOver)

        super.onSaveInstanceState(outState)
    }
}

