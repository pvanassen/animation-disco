package nl.pvanassen.led.animation.disco

import nl.pvanassen.led.animation.common.canvas.Canvas
import nl.pvanassen.led.animation.common.model.Animation
import nl.pvanassen.led.animation.common.util.ColorUtils
import java.awt.Image
import java.awt.image.BufferedImage
import kotlin.math.max
import kotlin.math.min

class Disco(private val canvas: Canvas, private val pixels: List<Int>): Animation<Any> {
    private var zAxis = Math.random()

    private val buffer = BufferedImage(pixels.size * 2, pixels.max() * 2, BufferedImage.TYPE_INT_RGB)

    override fun getFrame(seed: Long, frame: Int, nsPerFrame: Int, helper: Any): ByteArray {
        zAxis += Math.random()

        (0 until buffer.width).forEach { x ->
            (0 until buffer.height).forEach { y ->
                val hue = SimplexNoise.sumOctave(8, x.toDouble(), y.toDouble(), zAxis, 0.5, SCALE, -0.2f, 1.2f)
                buffer.setRGB(x, y, ColorUtils.makeColorHSB(max(0.0, min(1.0, hue)).toFloat(), 1f, 1f))
            }
        }
        val scaled = buffer.getScaledInstance(canvas.getWidth(), canvas.getHeight(), Image.SCALE_FAST) as BufferedImage

        canvas.drawImage(scaled)

        return canvas.getValues()
    }

    companion object {
        private const val SCALE = 0.002
    }
}