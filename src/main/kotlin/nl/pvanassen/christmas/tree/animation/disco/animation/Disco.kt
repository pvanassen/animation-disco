package nl.pvanassen.christmas.tree.animation.disco.animation

import nl.pvanassen.christmas.tree.animation.common.model.Animation
import nl.pvanassen.christmas.tree.animation.common.model.TreeModel
import nl.pvanassen.christmas.tree.animation.common.util.ColorUtils
import nl.pvanassen.christmas.tree.animation.disco.SimplexNoise
import nl.pvanassen.christmas.tree.canvas.Canvas
import javax.inject.Singleton

@Singleton
class Disco(private val canvas: Canvas, treeModel: TreeModel): Animation {
    private var zAxis = Math.random()

    private val strips:Int = treeModel.strips

    override fun getFrame(): ByteArray {
        zAxis += Math.random()
        val n = System.currentTimeMillis() * 0.001f * 200 / 100f
        val intensity = SimplexNoise.sumOctave(16, n.toDouble(), zAxis, zAxis, 0.5, Z_SCALE, 0f, 1f).toFloat()

        for (x in 0 until strips) {
            for (y in 1..59) {
                val hue = SimplexNoise.sumOctave(16, x.toDouble(), y.toDouble(), zAxis, 0.5, SCALE, 0f, 1f).toFloat()
                canvas.setValue(x, y, ColorUtils.makeColorHSB(hue, 1f, intensity))
            }
        }
        return canvas.getValues()
    }

    companion object {
        private const val SCALE = 0.04
        private const val Z_SCALE = 0.00005
    }
}
