package com.central.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
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

    fun addBackgroundGuide(columns: Int) {
        val texture = Texture(Gdx.files.internal("background.jpg"))
        texture.setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat)

        val textureRegion = TextureRegion(texture)
        textureRegion.setRegion(0, 0, texture.width * columns, texture.width * columns)
        val background = Image(textureRegion)
        background.setSize(width, height)
        background.setPosition(0f, Gdx.graphics.height - background.getHeight())
        stage.addActor(background)
    }

    override fun show() {
        super.show()

        stage = Stage(ScreenViewport())
        val row_height = Gdx.graphics.width / 12f
        val col_width = Gdx.graphics.width / 12f
        addBackgroundGuide(helpGuides)

        val label1Style = Label.LabelStyle()
        val myFont = BitmapFont(Gdx.files.internal("bitmapfont/Amble-Regular-26.fnt"))
        label1Style.font = myFont
        label1Style.fontColor = Color.RED

        val label1 = Label("Title (BitmapFont)", label1Style)
        label1.setSize(width, row_height)
        label1.setPosition(0f, height - row_height * 2)
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
        label2.setSize(width / helpGuides * 5, row_height)
        label2.setPosition(col_width * 2, Gdx.graphics.height - row_height * 4)
        stage.addActor(label2)

        val mySkin = Skin(Gdx.files.internal("skin/glassy-ui.json"))

        val label3 = Label("This is a Label (skin) on  5 columns ", mySkin, "black")
        label3.setSize(width / helpGuides, row_height)
        label3.setPosition(col_width * 2, Gdx.graphics.height - row_height * 6)
        stage.addActor(label3)

        val label4 = Label("This is a Label (skin) with a 5 columns width but WITH wrap", mySkin, "black")
        label4.setSize(width / helpGuides * 5, row_height)
        label4.setPosition(col_width * 2, Gdx.graphics.height - row_height * 7)
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
