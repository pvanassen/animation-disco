package nl.pvanassen.led.animation.disco

import nl.pvanassen.led.animation.common.canvas.Canvas
import nl.pvanassen.led.animation.common.model.Animation
import nl.pvanassen.led.animation.common.util.ColorUtils
import java.awt.image.BufferedImage
import kotlin.math.max
import kotlin.math.min

class Disco(private val canvas: Canvas): Animation<Any> {
    private var zAxis = Math.random()

    override fun getFrame(seed: Long, frame: Int, nsPerFrame: Int, helper: Any): ByteArray {
        zAxis += Math.random()

        (0 .. canvas.getWidth()).forEach { x ->
            (0 .. canvas.getHeight()).forEach { y ->
                val hue = SimplexNoise.sumOctave(8, x.toDouble(), y.toDouble(), zAxis, 0.5, SCALE, -0.2f, 1.2f)
                canvas.setRGB(x, y, ColorUtils.makeColorHSB(max(0.0, min(1.0, hue)).toFloat(), 1f, 1f))
            }
        }
        return canvas.getValues()
    }

    companion object {
        private const val SCALE = 0.04
    }
}