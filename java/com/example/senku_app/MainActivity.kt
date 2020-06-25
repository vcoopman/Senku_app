package cl.ldaravena.testclases

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val t = Tablero()

        val piezas = setJuego(t)

        actualizar(t,piezas)


    }

    //Se actualizan las posiciones disponibles en las piezas
    fun actualizar(t: Tablero, piezas : MutableList<Pieza>){

        for (i in piezas){
            i.norte = t.moverP(i.x, (i.y)-2, 'N')
            i.sur = t.moverP(i.x, (i.y)+2, 'S')
            i.este = t.moverP((i.x)+2, i.y, 'E')
            i.oeste = t.moverP((i.x)-2, i.y, 'O')
        }

    }

    //Se crean las 32 piezas con sus respectivas coordenadas
    fun setJuego(t: Tablero): MutableList<Pieza>{

        val piezas : MutableList<Pieza> = ArrayList()

        val p1 = Pieza(1,3)
        piezas.add(p1)

        val p2 = Pieza(1,4)
        piezas.add(p2)

        val p3 = Pieza(1,5)
        piezas.add(p3)

        val p4 = Pieza(2,3)
        piezas.add(p4)

        val p5 = Pieza(2,4)
        piezas.add(p5)

        val p6 = Pieza(2,5)
        piezas.add(p6)

        val p7 = Pieza(3,1)
        piezas.add(p7)

        val p8 = Pieza(3,2)
        piezas.add(p8)

        val p9 = Pieza(3,3)
        piezas.add(p9)

        val p10 = Pieza(3, 4)
        piezas.add(p10)

        val p11 = Pieza(3,5)
        piezas.add(p11)

        val p12 = Pieza(3,6)
        piezas.add(p12)

        val p13 = Pieza(3,7)
        piezas.add(p13)

        val p14 = Pieza(4,1)
        piezas.add(p14)

        val p15 = Pieza(4,2)
        piezas.add(p15)

        val p16 = Pieza(4,3)
        piezas.add(p16)

        val p17 = Pieza(4,5)
        piezas.add(p17)

        val p18 = Pieza(4,6)
        piezas.add(p18)

        val p19 = Pieza(4,7)
        piezas.add(p19)

        val p20 = Pieza(5,1)
        piezas.add(p20)

        val p21 = Pieza(5,2)
        piezas.add(p21)

        val p22 = Pieza(5,3)
        piezas.add(p22)

        val p23 = Pieza(5,4)
        piezas.add(p23)

        val p24 = Pieza(5,5)
        piezas.add(p24)

        val p25 = Pieza(5,6)
        piezas.add(p25)

        val p26 = Pieza(5,7)
        piezas.add(p26)

        val p27 = Pieza(6,3)
        piezas.add(p27)

        val p28 = Pieza(6,4)
        piezas.add(p28)

        val p29 = Pieza(6,5)
        piezas.add(p29)

        val p30 = Pieza(7,3)
        piezas.add(p30)

        val p31 = Pieza(7,4)
        piezas.add(p31)

        val p32 = Pieza(7,5)
        piezas.add(p32)

        return piezas
    }
}

class Tablero(){

    // Se crean las celdas del tablero como un Mapa donde la key es un par con las
    // coordenadas de la celdas. El value del mapa es un par donde el primer componente indica
    // si es una celda válida que se puede ocupar en el juego y el segundo componente indica si
    // la celda está desocupada

    private val tablero = mutableMapOf(
            Pair(Pair(1,1),Pair(false,false)),
            Pair(Pair(1,2),Pair(false,false)),
            Pair(Pair(1,3),Pair(true,false)),
            Pair(Pair(1,4),Pair(true,false)),
            Pair(Pair(1,5),Pair(true,false)),
            Pair(Pair(1,6),Pair(false,false)),
            Pair(Pair(1,7),Pair(false,false)),

            Pair(Pair(2,1),Pair(false,false)),
            Pair(Pair(2,2),Pair(false,false)),
            Pair(Pair(2,3),Pair(true,false)),
            Pair(Pair(2,4),Pair(true,false)),
            Pair(Pair(2,5),Pair(true,false)),
            Pair(Pair(2,6),Pair(false,false)),
            Pair(Pair(2,7),Pair(false,false)),

            Pair(Pair(3,1),Pair(true,false)),
            Pair(Pair(3,2),Pair(true,false)),
            Pair(Pair(3,3),Pair(true,false)),
            Pair(Pair(3,4),Pair(true,false)),
            Pair(Pair(3,5),Pair(true,false)),
            Pair(Pair(3,6),Pair(true,false)),
            Pair(Pair(3,7),Pair(true,false)),

            Pair(Pair(4,1),Pair(true,false)),
            Pair(Pair(4,2),Pair(true,false)),
            Pair(Pair(4,3),Pair(true,false)),
            Pair(Pair(4,4),Pair(true,true)),
            Pair(Pair(4,5),Pair(true,false)),
            Pair(Pair(4,6),Pair(true,false)),
            Pair(Pair(4,7),Pair(true,false)),

            Pair(Pair(5,1),Pair(true,false)),
            Pair(Pair(5,2),Pair(true,false)),
            Pair(Pair(5,3),Pair(true,false)),
            Pair(Pair(5,4),Pair(true,false)),
            Pair(Pair(5,5),Pair(true,false)),
            Pair(Pair(5,6),Pair(true,false)),
            Pair(Pair(5,7),Pair(true,false)),

            Pair(Pair(6,1),Pair(false,false)),
            Pair(Pair(6,2),Pair(false,false)),
            Pair(Pair(6,3),Pair(true,false)),
            Pair(Pair(6,4),Pair(true,false)),
            Pair(Pair(6,5),Pair(true,false)),
            Pair(Pair(6,6),Pair(false,false)),
            Pair(Pair(6,7),Pair(false,false)),

            Pair(Pair(7,1),Pair(false,false)),
            Pair(Pair(7,2),Pair(false,false)),
            Pair(Pair(7,3),Pair(true,false)),
            Pair(Pair(7,4),Pair(true,false)),
            Pair(Pair(7,5),Pair(true,false)),
            Pair(Pair(7,6),Pair(false,false)),
            Pair(Pair(7,7),Pair(false,false)) )


    // Retorna 'true' si en la posición indicada se puede mover la pieza comprobando si es una
    // celda válida y está desocupada
    fun moverP(x: Int, y: Int, d: Char) : Boolean{

        if(x<1 || x>7 || y<1 || y>7) return false

        var r1 = tablero.getValue(Pair(x,y+1))

        var v1 = false
            // v1 es verdadero cuando la casilla es válida y está ocupada por una pieza
            when (d) {
                'N' -> {v1 = r1.first && !r1.second }

                'S' -> { r1 = tablero.getValue(Pair(x,y-1))
                        v1 = r1.first && !r1.second  }

                'E' -> { r1 = tablero.getValue(Pair(x-1,y))
                        v1 = r1.first && !r1.second  }

                else -> { r1 = tablero.getValue(Pair(x+1,y))
                        v1 = r1.first && !r1.second  }
            }


        var v2 = false

        val r2 = tablero.getValue(Pair(x,y))

        v2 = r2.first && r2.second

        return v1 && v2

    }

    //Actualiza las celdas vacías y ocupadas por las piezas
    fun setTablero(x: Int, y: Int, p: Boolean){

        tablero[Pair(x,y)] = Pair(true,p)
    }
}

class Pieza(val x: Int, val y: Int) {

    var visible = true

    // Variables que indican si la pieza puede movers al norte, sur, este u oeste
    var norte = false
    var sur = false
    var este = false
    var oeste = false

}

