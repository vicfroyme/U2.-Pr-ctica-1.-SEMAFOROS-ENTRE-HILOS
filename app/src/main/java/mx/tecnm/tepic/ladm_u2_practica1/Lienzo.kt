package mx.tecnm.tepic.ladm_u2_practica1

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View
import java.util.concurrent.Semaphore


class Lienzo (act : MainActivity) : View(act) {
    var estado = true
    var mov1 = 0
    var mov2 = 0
    var mov3 = 0
    var mov4 = 0
    var para = true
    var para2 = true
    var para3 = true
    var para4 = true
    var sema1 = 0
    var sema2 = 0

    override fun onDraw(c: Canvas) {
        var hilo = Hilo1 (this)
        var hilo2 = Hilo2 (this)
        var hilo3 = Hilo3 (this)
        var hilo4 = Hilo4 (this)
        var hiloS = HiloS (this)
        if(estado){
            hilo.start()
            hilo2.start()
            hilo3.start()
            hilo4.start()
            hiloS.start()
            estado=false
        }

        invalidate()

        super.onDraw(c)
        val p = Paint()

        //720 x 1080

        var carro1 = Carro(this,250f,30f+mov1.toFloat(),330f,130f+mov1.toFloat(), Color.RED)
        var carro2 = Carro(this,390f,1180f+mov2.toFloat(),470f,1080f+mov2.toFloat(), Color.BLACK)
        var carro3 = Carro(this,-30f+mov3.toFloat(),465f,70f+mov3.toFloat(),545f, Color.BLUE)
        var carro4 = Carro(this,730f+mov4.toFloat(),605f,830f+mov4.toFloat(),685f, Color.GREEN)

        //CAMINO HORIZONTAL
        p.color=Color.GRAY
        c.drawRect(0f, 435f, 720f, 715f, p)
        p.color=Color.WHITE
        p.strokeWidth=5f
        c.drawLine(10f,575f,70f,575f,p)
        c.drawLine(90f,575f,160f,575f,p)
        c.drawLine(640f,575f,710f,575f,p)
        c.drawLine(550f,575f,620f,575f,p)

        //CAMINO VERTICAL
        p.color=Color.GRAY
        c.drawRect(220f, 0f, 500f, 1440f, p)
        p.color=Color.WHITE
        p.strokeWidth=5f
        c.drawLine(360f,10f,360f,70f,p)
        c.drawLine(360f,100f,360f,160f,p)
        c.drawLine(360f,190f,360f,250f,p)
        c.drawLine(360f,1120f,360f,1180f,p)
        c.drawLine(360f,1040F,360f,1100f,p)
        c.drawLine(360f,960F,360f,1020f,p)

        carro1.dibujar(c)
        carro2.dibujar(c)
        carro3.dibujar(c)
        carro4.dibujar(c)

        //SEMAFORO
        p.color = Color.BLACK
        c.drawRect(550f, 800f,690f, 1150f, p)
        p.color = Color.GRAY
        c.drawCircle(620f, 870f, 50f, p)
        p.color=Color.GRAY
        c.drawCircle(620f, 980f, 50f, p)
        p.color=Color.GRAY
        c.drawCircle(620f, 1090f, 50f, p)
        if (sema1 in 0..45){
            p.color = Color.RED
            c.drawCircle(620f, 870f, 50f, p)}
        if (sema1 in 91..100){
            p.color = Color.YELLOW
            c.drawCircle(620f, 980f, 50f, p)}
        if (sema1 in 46..90){
            p.color = Color.GREEN
            c.drawCircle(620f, 1090f, 50f, p)}

        //SEMAFORO2
        p.color = Color.BLACK
        c.drawRect(30f, 20f,170f, 370f, p)
        p.color = Color.GRAY
        c.drawCircle(100f, 90f, 50f, p)
        p.color=Color.GRAY
        c.drawCircle(100f, 200f, 50f, p)
        p.color=Color.GRAY
        c.drawCircle(100f, 310f, 50f, p)
        if (sema2 in 56..100){
            p.color = Color.RED
            c.drawCircle(100f, 90f, 50f, p)}
        if (sema2 in 46..55){
            p.color = Color.YELLOW
            c.drawCircle(100f, 200f, 50f, p)}
        if (sema2 in 1..45){
            p.color = Color.GREEN
            c.drawCircle(100f, 310f, 50f, p)}



    }

}

class Hilo1 (et: Lienzo) :Thread() {
    var etiGlobal = et
    override fun run() {
        super.run()
        while (etiGlobal.para){
            if (etiGlobal.mov1 == 1500){
                etiGlobal.estado = false
                etiGlobal.mov1 = 0
            }

            if (etiGlobal.mov1 == 1000){
                etiGlobal.para3 = true
            }

            if (etiGlobal.mov1 == 300 && etiGlobal.sema2 in 56..100){
                etiGlobal.para = false
                sleep(2)
                etiGlobal.estado = true
            }

            etiGlobal.mov1++
            sleep(2)
        }
    }
}

class Hilo2 (et: Lienzo) :Thread() {
    var etiGlobal = et
    override fun run() {
        super.run()
        while (etiGlobal.para2){
            if (etiGlobal.mov2 == -1500){
                etiGlobal.estado = false
                etiGlobal.mov2 = 0
            }

            if (etiGlobal.mov2 == -1000){
                etiGlobal.para4 = true
            }

            if (etiGlobal.mov2 == -350 && etiGlobal.sema2 in 56..100){
                etiGlobal.para2 = false
                sleep(2)
                etiGlobal.estado = true
            }

            etiGlobal.mov2--
            sleep(2)
        }
    }
}

class Hilo3 (et: Lienzo) :Thread() {
    var etiGlobal = et
    override fun run() {
        super.run()
        while (etiGlobal.para3){
            if (etiGlobal.mov3 == 800){
                etiGlobal.estado = false
                etiGlobal.mov3 = 0
            }

            if (etiGlobal.mov3 == 300){
                etiGlobal.para = true
            }

            if (etiGlobal.mov3 == 130 && etiGlobal.sema1 in 0..45){
                etiGlobal.para3 = false
                sleep(2)
                etiGlobal.estado = true
            }

            etiGlobal.mov3++
            sleep(2)
        }
    }
}

class Hilo4 (et: Lienzo) :Thread() {
    var etiGlobal = et
    override fun run() {
        super.run()
        while (etiGlobal.para4){
            if (etiGlobal.mov4 == -800){
                etiGlobal.estado = false
                etiGlobal.mov4 = 0
            }

            if (etiGlobal.mov4 == -300){
                etiGlobal.para2 = true
            }

            if (etiGlobal.mov4 == -215 && etiGlobal.sema1 in 0..45){
                etiGlobal.para4 = false
                sleep(2)
                etiGlobal.estado = true
            }

            etiGlobal.mov4--
            sleep(2)
        }
    }
}

class HiloS(et: Lienzo) : Thread(){
    var etiGlobal = et
    override fun run(){
        super .run()
        while (true) {
            etiGlobal.run {
                if (true) {
                    sleep(500)
                    etiGlobal.sema1++
                    etiGlobal.sema2++
                    etiGlobal.invalidate()
                    if (sema1 == 100) {
                        sema1 = 1
                    }
                    if (sema2 == 100) {
                        sema2 = 1
                    }
                }
            }
            sleep(500)
        }
    }
}








