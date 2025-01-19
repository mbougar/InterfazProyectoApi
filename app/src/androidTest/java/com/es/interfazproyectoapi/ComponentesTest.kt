package com.es.interfazproyectoapi

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.NavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.es.interfazproyectoapi.data.model.Book
import com.es.interfazproyectoapi.data.model.Character
import com.es.interfazproyectoapi.data.model.House
import com.es.interfazproyectoapi.data.model.Spell
import com.es.interfazproyectoapi.ui.components.ApiButton
import com.es.interfazproyectoapi.ui.components.BookItem
import com.es.interfazproyectoapi.ui.components.ButtonGrid
import com.es.interfazproyectoapi.ui.components.CharacterItem
import com.es.interfazproyectoapi.ui.components.DrawerContent
import com.es.interfazproyectoapi.ui.components.DrawerItem
import com.es.interfazproyectoapi.ui.components.ErrorScreen
import com.es.interfazproyectoapi.ui.components.Header
import com.es.interfazproyectoapi.ui.components.HouseItem
import com.es.interfazproyectoapi.ui.components.SpellItem
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ComponentesTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    val navController = mockk<NavController>(relaxed = true)

    @Test
    fun apiButtonTest() {
        var wasClicked = false

        composeTestRule.setContent {
            ApiButton(label = "Test Button") { wasClicked = true }
        }

        composeTestRule.onNodeWithText("Test Button").assertIsDisplayed()
        composeTestRule.onNodeWithText("Test Button").performClick()

        assert(wasClicked)
    }

    @Test
    fun bookItemTest() {
        val sampleBook = Book(
            title = "Harry Potter",
            originalTitle = "Harry Potter and the Philosopher's Stone",
            pages = 223,
            releaseDate = "1997",
            description = "Portada de Harry Potter",
            cover = "https://picsum.photos/200",
            number = 1,
            index = 1
        )

        composeTestRule.setContent {
            BookItem(book = sampleBook)
        }

        composeTestRule.onNodeWithText("Harry Potter").assertIsDisplayed()
        composeTestRule.onNodeWithText("(Harry Potter and the Philosopher's Stone)").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Portada de Harry Potter").assertExists()
    }

    @Test
    fun buttonGridTest() {

        composeTestRule.setContent {
            ButtonGrid(navController = navController)
        }

        composeTestRule.onNodeWithText("Personajes").assertIsDisplayed()
        composeTestRule.onNodeWithText("Hechizos").assertIsDisplayed()
        composeTestRule.onNodeWithText("Libros").assertIsDisplayed()
        composeTestRule.onNodeWithText("Casas").assertIsDisplayed()

        composeTestRule.onNodeWithText("Personajes").performClick()
    }

    @Test
    fun characterItemTest() {
        val sampleCharacter = Character(
            fullName = "Harry Potter",
            nickname = "El Elegido",
            hogwartsHouse = "Gryffindor",
            interpretedBy = "Actor",
            children = null,
            image = "https://picsum.photos/200",
            birthdate = "23/02/1999",
            index = 1
        )

        composeTestRule.setContent {
            CharacterItem(character = sampleCharacter)
        }

        composeTestRule.onNodeWithText("Harry Potter").assertIsDisplayed()
        composeTestRule.onNodeWithText("Apodo: El Elegido").assertIsDisplayed()
        composeTestRule.onNodeWithText("Casa: Gryffindor").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Imagen de Harry Potter").assertExists()
    }

    @Test
    fun errorScreenTest() {
        var retryClicked = false

        composeTestRule.setContent {
            ErrorScreen(message = "Error de red", onRetry = { retryClicked = true })
        }

        composeTestRule.onNodeWithText("Error de red").assertIsDisplayed()
        composeTestRule.onNodeWithText("Reintentar").performClick()

        assert(retryClicked)
    }

    @Test
    fun headerTest() {

        composeTestRule.setContent {
            Header(title = "Pantalla de Prueba", navController = navController)
        }

        composeTestRule.onNodeWithText("Pantalla de Prueba").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Go back").performClick()
    }

    @Test
    fun houseItemTest() {
        val sampleHouse = House(
            house = "Gryffindor",
            founder = "Godric Gryffindor",
            animal = "León",
            colors = listOf("Rojo", "Dorado"),
            emoji = "🦁",
            index = 1
        )

        composeTestRule.setContent {
            HouseItem(house = sampleHouse)
        }

        composeTestRule.onNodeWithText("🦁 Gryffindor").assertIsDisplayed()
        composeTestRule.onNodeWithText("Fundador: Godric Gryffindor").assertIsDisplayed()
        composeTestRule.onNodeWithText("Colores: Rojo, Dorado").assertIsDisplayed()
    }

    @Test
    fun drawerContentTest() {

        composeTestRule.setContent {
            DrawerContent(navController = navController, onCloseDrawer = {})
        }

        composeTestRule.onNodeWithText("Menú").assertIsDisplayed()
        composeTestRule.onNodeWithText("Home").performClick()
    }

    @Test
    fun drawerItemTest() {
        var itemClicked = false

        composeTestRule.setContent {
            DrawerItem(label = "Prueba") { itemClicked = true }
        }

        composeTestRule.onNodeWithText("Prueba").assertIsDisplayed()
        composeTestRule.onNodeWithText("Prueba").performClick()

        assert(itemClicked)
    }

    @Test
    fun spellItemTest() {
        val sampleSpell = Spell(
            spell = "Expelliarmus",
            use = "Desarma al oponente",
            index = 1
        )

        composeTestRule.setContent {
            SpellItem(spell = sampleSpell)
        }

        composeTestRule.onNodeWithText("Expelliarmus").assertIsDisplayed()
        composeTestRule.onNodeWithText("Uso: Desarma al oponente").assertIsDisplayed()
    }
}