package com.example.lembrete

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.firebase.auth.FirebaseAuth

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.lembrete", appContext.packageName)

        val activityScenario: ActivityScenario<LoginActivity> =
            ActivityScenario.launch(LoginActivity::class.java)

        activityScenario.moveToState(Lifecycle.State.RESUMED)

        onView(withId(R.id.txtViewBemVindo)).check(matches(isDisplayed()))
        onView(withId(R.id.editEmail)).check(matches(isDisplayed()))
        onView(withId(R.id.editSenha)).check(matches(isDisplayed()))
        onView(withId(R.id.btnCadastro)).check(matches(isDisplayed()))
        onView(withId(R.id.btnLogin)).check(matches(isDisplayed()))

        onView(withId(R.id.txtViewBemVindo)).check(matches(withText("Que bom receber você!")))
        onView(withId(R.id.btnCadastro)).perform(click())

        // ------ TELA DE CADASTRO

        onView(withId(R.id.textView)).check(matches(isDisplayed()))
        onView(withId(R.id.editEmail1)).check(matches(isDisplayed()))
        onView(withId(R.id.editSenha1)).check(matches(isDisplayed()))
        onView(withId(R.id.btnCadastrar)).check(matches(isDisplayed()))

        onView(withId(R.id.textView)).check(matches(withText("Bem-vindo ao Lembrete!")))
        onView(withId(R.id.editEmail1)).perform(typeText("teste12@gmail.com"), closeSoftKeyboard())
        onView(withId(R.id.editSenha1)).perform(typeText("1234567"), closeSoftKeyboard())
        onView(withId(R.id.btnCadastrar)).perform(click())

        Thread.sleep(5000)

        // ------- TELA DE LOGIN APÓS O CADASTRO
        onView(withId(R.id.txtViewBemVindo)).check(matches(isDisplayed()))
        onView(withId(R.id.editEmail)).check(matches(isDisplayed()))
        onView(withId(R.id.editSenha)).check(matches(isDisplayed()))
        onView(withId(R.id.btnCadastro)).check(matches(isDisplayed()))
        onView(withId(R.id.btnLogin)).check(matches(isDisplayed()))

        onView(withId(R.id.txtViewBemVindo)).check(matches(withText("Que bom receber você!")))
        onView(withId(R.id.editEmail)).perform(typeText("teste12@gmail.com"), closeSoftKeyboard())
        onView(withId(R.id.editSenha)).perform(typeText("1234567"), closeSoftKeyboard())
        onView(withId(R.id.btnLogin)).perform(click())

        val user = FirebaseAuth.getInstance().currentUser

        Thread.sleep(5000)


        // ------ TELA DE LEMBRETE
        onView(withId(R.id.txtBoasVindas)).check(matches(isDisplayed()))
        onView(withId(R.id.editLembrete)).check(matches(isDisplayed()))
        onView(withId(R.id.textLembreteSalvo)).check(matches(isDisplayed()))
        onView(withId(R.id.buttonSalvar)).check(matches(isDisplayed()))
        onView(withId(R.id.buttonDeletar)).check(matches(isDisplayed()))
        onView(withId(R.id.buttonSair)).check(matches(isDisplayed()))

        onView(withId(R.id.txtBoasVindas)).check(matches(withText("Olá, teste12@gmail.com")))
        onView(withId(R.id.editLembrete)).perform(typeText("Limpar a casa"), closeSoftKeyboard())
        onView(withId(R.id.buttonSalvar)).perform(click())
        onView(withId(R.id.textLembreteSalvo)).check(matches(withText("Limpar a casa")))
        onView(withId(R.id.buttonDeletar)).perform(click())
        onView(withId(R.id.buttonSair)).perform(click())

    }

}