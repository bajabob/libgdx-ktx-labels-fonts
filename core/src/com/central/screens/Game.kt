package com.central.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.central.Application
import ktx.app.KtxScreen


class Game(val application: Application) : KtxScreen {

    private lateinit var stage: Stage
    private val width = Gdx.graphics.width.toFloat()
    private val height = Gdx.graphics.height.toFloat()
    private val helpGuides = 12

    companion object {
        fun addBackgroundGuide(columns: Int, width: Float, height: Float): Actor {
            val texture = Texture(Gdx.files.internal("background.jpg"))
            texture.setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat)

            val textureRegion = TextureRegion(texture)
            textureRegion.setRegion(0, 0, texture.width * columns, texture.width * columns)
            val background = Image(textureRegion)
            background.setSize(width, height)
            background.setPosition(0f, Gdx.graphics.height - background.getHeight())
            return background
        }
    }

    override fun show() {
        super.show()

        stage = Stage(ScreenViewport())
        val rowHeight = Gdx.graphics.width / 12f
        val colWidth = Gdx.graphics.width / 12f
        stage.addActor(addBackgroundGuide(helpGuides, width, height))

        val label1Style = Label.LabelStyle()
        val myFont = BitmapFont(Gdx.files.internal("bitmapfont/Amble-Regular-26.fnt"))
        label1Style.font = myFont
        label1Style.fontColor = Color.RED

        val label1 = Label("Title (BitmapFont): Hello World", label1Style)
        label1.setSize(width, rowHeight)
        label1.setPosition(0f, height - rowHeight * 2)
        label1.setAlignment(Align.center)
        stage.addActor(label1)

        val generator = FreeTypeFontGenerator(Gdx.files.internal("truetypefont/Amble-Light.ttf"))
        val parameter = FreeTypeFontGenerator.FreeTypeFontParameter()
        parameter.size = 30
        parameter.borderWidth = 1f
        parameter.color = Color.YELLOW
        parameter.shadowOffsetX = 3
        parameter.shadowOffsetY = 3
        parameter.shadowColor = Color(0f, 0.5f, 0f, 0.75f)
        val font24 = generator.generateFont(parameter) // font size 24 pixels
        generator.dispose()

        val labelStyle = Label.LabelStyle()
        labelStyle.font = font24

        val label2 = Label("True Type Font (.ttf) - Gdx FreeType", labelStyle)
        label2.setSize(width, rowHeight)
        label2.setPosition(0f, height - rowHeight * 4)
        label2.setAlignment(Align.center)
        stage.addActor(label2)

        val mySkin = Skin(Gdx.files.internal("skin/glassy-ui.json"))

        val label3 = Label("This is a Label (skin) on  5 columns ", mySkin, "black")
        label3.setSize(width / helpGuides, rowHeight)
        label3.setPosition(colWidth * 2, height - rowHeight * 6)
        stage.addActor(label3)

        val label4 = Label("This is a Label (skin) with a 5 columns width but WITH wrap as you can see it continues to wrap even when running on multiple lines.", mySkin, "black")
        label4.setSize(width / helpGuides * 5, rowHeight)
        label4.setPosition(colWidth * 2, height - rowHeight * 7)
        label4.setWrap(true)
        stage.addActor(label4)

    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        stage.act()
        stage.draw()
    }
}
