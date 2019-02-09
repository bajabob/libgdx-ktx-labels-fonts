package com.central.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.central.Application
import ktx.app.KtxScreen


class Game(val application: Application) : KtxScreen {

    private lateinit var stage: Stage
    private val width = Gdx.graphics.width.toFloat()
    private val height = Gdx.graphics.height.toFloat()

    override fun show() {
        super.show()

        stage = Stage(ScreenViewport())

        val texture = Texture(Gdx.files.internal("image.jpg"))

        fun addDot (x: Float, y: Float) {
            val dot = Image(texture)
            dot.width = 12f
            dot.height = 12f
            dot.setOrigin(Align.center)
            dot.setPosition(x, y, Align.center)
            stage.addActor(dot)
        }

        // bottom left corner
        addDot(0f, 0f)

        // top left corner
        addDot(0f, height)

        // bottom right corner
        addDot(width, 0f)

        // top right corner
        addDot(width, height)

        val image1 = Image(texture)
        image1.setPosition(width * 1 / 4, height * 3 / 4, Align.center)
        stage.addActor(image1)

        val image2 = Image(texture)
        image2.originX = image2.width / 2
        image2.originY = image2.height / 2
        image2.rotateBy(45f)
        image2.setPosition(width * 3 / 4, height * 3 / 4, Align.center)
        stage.addActor(image2)

        val image3 = Image(texture)
        image3.originX = image3.width / 2
        image3.originY = image3.height / 2
        image3.scaleX = 0.25f
        image3.scaleY = 0.25f
        image3.setPosition(width * 1 / 4, height * 1 / 4, Align.center)
        stage.addActor(image3)

        texture.setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat)
        val textureRegion = TextureRegion(texture)
        textureRegion.setRegion(0, 0, texture.width * 8, texture.height * 4)
        val image4 = Image(textureRegion)
        image4.setSize(200f, 100f)
        image4.setPosition(width * 3 / 4, height * 1 / 4, Align.center)
        stage.addActor(image4)
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        stage.act()
        stage.draw()
    }
}
