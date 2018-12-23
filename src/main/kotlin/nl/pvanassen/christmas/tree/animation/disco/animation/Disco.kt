package nl.pvanassen.christmas.tree.animation.disco.animation

import nl.pvanassen.christmas.tree.animation.common.model.Animation
import nl.pvanassen.christmas.tree.animation.common.model.TreeModel
import nl.pvanassen.christmas.tree.animation.common.util.ColorUtils
import nl.pvanassen.christmas.tree.animation.disco.SimplexNoise
import nl.pvanassen.christmas.tree.canvas.Canvas
import javax.inject.Singleton

@Singleton
class Disco(private val canvas: Canvas, private val treeModel: TreeModel): Animation {
    private var zAxis = Math.random()

    private val strips:Int = treeModel.strips

    override fun getFrame(seed:Long, frame:Int, nsPerFrame:Int): ByteArray {
        zAxis += Math.random()

        for (x in 0 until strips) {
            for (y in 0 until treeModel.ledsPerStrip) {
                val hue = SimplexNoise.sumOctave(8, x.toDouble(), y.toDouble(), zAxis, 0.5, SCALE, 0f, 1f).toFloat()
                canvas.setValue(x, y, ColorUtils.makeColorHSB(hue, 1f, 1f))
            }
        }
        return canvas.getValues()
    }

    companion object {
        private const val SCALE = 0.04
    }
}