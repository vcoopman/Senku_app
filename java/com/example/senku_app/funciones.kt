package com.example.senku_app

import android.content.Context
import android.graphics.Color
import android.media.MediaPlayer
import android.widget.ImageView
import java.util.*

val eat_ficha = R.raw.eat_ficha
val select = R.raw.click
val go_back = R.raw.go_back
val game_over = R.raw.game_over

var cantidadMovimientosRealizados: Int = 0
var isGameOver: Boolean = false
var puntaje: Int = 0

// Busca las fichas aun visibles en el tablero
fun buscarFichasVisibles(
    vistas: MutableMap<ImageView?, Boolean?>
): Map<ImageView?, Boolean?> {
    val fichasVisibles: Map<ImageView?, Boolean?> = vistas.filter { (k,v) -> v == true }
    return fichasVisibles
}

// Recoleta movimientos posibles por las fichas aun visibles
fun buscaMovimientosFichasVisibles(
    Movimientos: Map<ImageView, Array<Pair<ImageView, ImageView>>>,
    fichasVisibles: Map<ImageView?, Boolean?>
): MutableMap<ImageView?, Array<Pair<ImageView, ImageView>>> {

    val movimientosFichasVisibles = mutableMapOf<ImageView?, Array<Pair<ImageView, ImageView>>>()
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
    movimientosFichasVisibles: MutableMap<ImageView?, Array<Pair<ImageView, ImageView>>>,
    fichasVisibles: Map<ImageView?, Boolean?>
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
fun gameOver(Movimientos: Map<ImageView, Array<Pair<ImageView, ImageView>>>, vistas: MutableMap<ImageView?, Boolean?>, ficha: ImageView?): Boolean {
    val fichasVisibles = buscarFichasVisibles(vistas)
    val movimientosFichasVisibles = buscaMovimientosFichasVisibles(Movimientos,fichasVisibles)
    val cantMovimientosRestantes :Int = buscaMovimientosPosibles(movimientosFichasVisibles,fichasVisibles)

    // Si no quedan movimientos se acaba el juego
    if(cantMovimientosRestantes == 0){

        // Bonificacion especial por terminar solo con la ficha central visible
        if(fichasVisibles.size == 1 && fichasVisibles.containsKey(ficha)){
            puntaje += 100
        }
        return true
    }
    return false
}


// Esta función es la que comprueba si se hace una jugada que come a una ficha, recibe las dos fichas que se tocaron
// El mapa con los movimientos válidos de la ficha y el mapa con los estados visibles de las fichas

fun verMovimientos(
    i: ImageView?,
    f: ImageView?,
    movimientos: Map<ImageView, Array<Pair<ImageView, ImageView>>>,
    vistas: MutableMap<ImageView?, Boolean?>,
    pilaJugadas : Stack<Triple<ImageView?, ImageView, ImageView>>,
    c: Context
) {
    // Obtiene el arreglo de Pares con las fichas contiguas a la ficha "i"
    val arreglo = movimientos[i]



    // Esta condición la pide android studio para asegurarse de que no se revisa un arreglo nulo, pero como está
    // programado eso nunca ocurre
    if (arreglo != null) {

        // Por cada par en el arreglo (mínimo 1, máximo 4)
        for (k in arreglo) {

            // Obtiene visibilidad de la ficha contigua a "i" Si es visible retorna true, en caso contrario false
            // Se tiene el operador elvis en caso de que sea null, pero eso no ocurre (cosas que pide android studio)
            val v1: Boolean = vistas[k.first] ?: false

            // Obtiene visibilidad de la ficha contigua al ficha contigua Si es visible retorna true, en caso contrario false
            // Acá debe ser false para que pueda comerse la ficha contigua
            val v2: Boolean = vistas[f] ?: true

            // Si la ficha contigua es visible, la contigua a esta es invisible y resulta que la ficha "f" que se tocó es igual a esta:
            if (v1 && !v2 && f == k.second) {

                // Se come la ficha contigua y "avanza" la ficha "i" a la posición de "f"
                // Para eso se quita la visibilidad a "i"
                //
                //
                if (i != null) vistas[i] = false

                // Se quita la visibilidad a la ficha contigua que se comió
                vistas[k.first] = false

                // y se hace visible la ficha "f"
                vistas[f] = true

                // Agrega las fichas que cambiaron su visibilidad al stack para deshacer las jugadas
                pilaJugadas.push(Triple(i,k.first,f))

                play(c, eat_ficha)

                // añade un movimiento al contador
                cantidadMovimientosRealizados++

                // añade puntaje
                puntaje += 15
            }
        }
    }
}

// Funcion para actualizar las visibilidades de las fichas
fun refresh(f: Array<ImageView>, v: MutableMap<ImageView?, Boolean?>){

    // Para cada ficha
    for(i in f ){

        // Si es visible se muestra la imagen
        if(v[i] == true){

            i.setImageResource(R.drawable.red_ball)
        }

        // Sino, no se muestra la imagen, solo el color de fondo
        else{

            i.setImageDrawable(null)
        }
    }
}

// Muestra sugerencia de posible jugada
// se asume que la ficha seleccionada es una fiche visible.
// retorna la view seleccionada para la sugerencia

fun mostrarSugerencia(
    fichaSelecionada: ImageView,
    movimientosFichasVisibles: MutableMap<ImageView?, Array<Pair<ImageView, ImageView>>>
): ImageView? {
    var index: Int = 0
    val cantidadMovimientosFicha: Int = movimientosFichasVisibles[fichaSelecionada]?.size!!

    // Chequear que el movimiento es permitido
    while( index < cantidadMovimientosFicha) {

        // si la ficha contigua es visible
        if (movimientosFichasVisibles.containsKey(
                movimientosFichasVisibles[fichaSelecionada]?.get(index)?.first)) {

            // si la ficha destino es invisible
            if (!movimientosFichasVisibles.containsKey(
                    movimientosFichasVisibles[fichaSelecionada]?.get(index)?.second)) {

                // pinta amarillo el fondo de la ficha destino
                movimientosFichasVisibles[fichaSelecionada]?.get(index)?.second?.setBackgroundColor(
                    Color.rgb(255, 255, 0)
                )
                return movimientosFichasVisibles[fichaSelecionada]?.get(index)?.second
            }
        }
        ++index
    }

    // No quedan movimientos para la ficha
    return null
}

fun quitarSugerencia(
    fichaSelecionada: ImageView?
){
    if(fichaSelecionada == null){
        return
    }

    fichaSelecionada.setBackgroundColor(Color.rgb(172, 110, 90))
}

fun resetValores(){

    cantidadMovimientosRealizados = 0
    isGameOver = false
    puntaje = 0
}

fun play(c: Context, p : Int) {

    val mp = MediaPlayer.create(c, p)
    mp.start()
    mp.setOnCompletionListener { mp -> mp?.release() }
}