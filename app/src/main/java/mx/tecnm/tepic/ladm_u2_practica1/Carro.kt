package mx.tecnm.tepic.ladm_u2_practica1

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class Carro(l:Lienzo, posX:Float, posY:Float, posX2:Float, posY2:Float, col:Int){
    var x = posX
    var y= posY
    var x2 = posX2
    var y2= posY2
    var color = col

    fun dibujar(c: Canvas){
        c.drawRect(x, y, x2, y2, Paint(color))
    }

}
