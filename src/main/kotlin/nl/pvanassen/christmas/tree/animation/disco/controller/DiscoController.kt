package nl.pvanassen.christmas.tree.animation.disco.controller

import io.micronaut.http.annotation.Controller
import nl.pvanassen.christmas.tree.animation.common.controller.AnimationController
import nl.pvanassen.christmas.tree.animation.disco.animation.Disco

@Controller
class DiscoController(private val disco: Disco): AnimationController() {

    override fun getFrame() = disco.tick()
}