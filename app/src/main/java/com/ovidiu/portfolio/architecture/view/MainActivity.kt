package com.ovidiu.portfolio.architecture.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ovidiu.portfolio.MainApplication
import com.ovidiu.portfolio.R
import com.ovidiu.portfolio.architecture.model.data_source.local.LocalDataBase
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.Study
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.Experience
import com.ovidiu.portfolio.architecture.model.data_source.local.entity.Professional
import org.threeten.bp.ZonedDateTime
import java.util.*
import java.util.concurrent.Executors
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.activity_main)
{
    @Inject
    lateinit var db: LocalDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as MainApplication).applicationComponent.inject(this)

        super.onCreate(savedInstanceState)

        Executors.newSingleThreadExecutor().execute {
            /*createProfessional()
            createExperiences()
            createStudies()*/
        }
    }

    private fun createProfessional() {
        val professional = Professional(
            "1",
            "Ovidiu",
            "Balaban",
            23,
            "Soy un programador Full-Stack, especializado en el desarrollo de aplicaciones móviles Android. Mis habilidades van desde la administración de bases de datos, pasando por el Back-End y llegando hasta el Front-End.",
            "Programador Android",
            "631178319",
            "b.ovidiu.2312@gmail.com"
        )

        db.professionalDao().insert(professional)
    }

    private fun createExperiences() {
        val experience = Experience(
                UUID.randomUUID().toString(),
                "Overtel Technology Systems",
                "Programador Android - Full Stack",
                "Encargado de los proyectos de movilidad Android y del mantenimiento y desarrollo de páginas web utilizando ASP.NET MVC y VUE.JS. Los proyectos Android que he realizado han estado programados tanto en Java como en Kotlin y estructurados por varias arquitecturas, siendo la más promimente, MVVM. He estado involucrado en todas las etapas del desarrollo del software, desde el análisis, pasando por la implentación, hasta llegar al despliegue del apk en la Play Store. Para desarrollar las aplicaciones Android me he valido de varías aplicaciones de terceros, entre las más utilizadas, las librerías de las Architecture Componentes y Lifecycles, Android KTX, Retrofit, Gson, Koin, EventBus, Picasso, Glide, Room, RxJava, Chuck, LeakCanary. Otras tareas que he realizado han sido la de administrar bases de datos tales como migrar datos de una base de datos a otra, hacer copias de seguridad, cambiar la estructura de las tablas y columnas.",
                ZonedDateTime.parse("2018-03-15T00:00:00+00:00").toEpochSecond(),
                null,
                "1"
            )

        db.experienceDao().insert(experience)
    }

    private fun createStudies() {
        val study = Study(
            UUID.randomUUID().toString(),
            "I.E.S. Carlos III",
            "Desarrollo de aplicaciones multiplataforma",
            "Este grado superior me ha ayudado para aprender los conceptos técnicos de la programación y cómo ponerlos en práctia. Aprendí lo que es la programación orientada a objetos utilizando Java, a crear y validar archivos XML, crear y administrar bases de datos Oracle, realizar consultas SQL y PL/SQL para  para crear procedimientos almacenados, así como triggers, programación de dispositivos multimedia utilizando Android Studio, administración de sistemas operativos, arquitectura cliente-servidor, HTTP, TCP/IP y diseño de interfaces.",
            ZonedDateTime.parse("2016-09-30T00:00:00+00:00").toEpochSecond(),
            ZonedDateTime.parse("2018-06-07T00:00:00+00:00").toEpochSecond(),
            "1"
        )

        db.degreeDao().insert(study)
    }
}
