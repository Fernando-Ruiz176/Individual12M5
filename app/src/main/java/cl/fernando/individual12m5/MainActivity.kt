package cl.fernando.individual12m5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

fun main(){
    var usuarios = mutableListOf<Usuario>()
    println("Ingresar cantidad de usuarios")
    val cantidadUsuarios = readln().toInt()
    for (i in 1..cantidadUsuarios){

        println("Ingresar el nombre")
        var nombre = readln()
        while(!validarNombre(nombre)){
            println("Nombre invalido. Ingresar un nombre valido")
            nombre = readln()
        }

        println("Ingresar el apellido")
        var apellido = readln()
        while(!validarApellido(apellido)){
            println("Apellido invalido. Ingresar un apellido valido")
            apellido = readln()
        }

        print("Ingresar la edad\n")
        var edad = readLine()?.toIntOrNull()
        while (edad == null || !validarEdad(edad)){
            println("Edad invalida. Ingresar edad solo con numeros")
            edad = readLine()?.toIntOrNull()
        }

        println("Ingresar el correo")
        var correo = readln()
        while(!validarCorreo(correo)){
            println("Correo invalido. Ingresar un correo valido")
            correo = readln()
        }

        println("Ingresar el sistema de salud")
        var sistemaSalud = readln()
        while(!validarSistemaSalud(sistemaSalud)){
            println("La informacion no es valida. Ingresar un sistema de salud valido")
            sistemaSalud = readln()
        }

        var usuario = Usuario(nombre, apellido, edad, correo, sistemaSalud)
        usuarios.add(usuario)
    }
    for (u in usuarios.sortedBy {
        usuario -> usuario.edad
    }){
        println("$u")
    }
}

fun validarSistemaSalud(sistemaSalud: String): Boolean {
    return when (sistemaSalud.toString()) {
        "fonasa" -> true
        "isapre" -> true
        else -> false
    }
}

fun validarCorreo(correo: String): Boolean {
    val regex = Regex("^([a-zA-Z0-9_.+-])+@([a-zA-Z0-9-])+\\.([a-zA-Z0-9-.])+$")
    return regex.matches(correo)
}

fun validarEdad(edad: Int): Boolean {
    val regex = Regex("^[0-9]+$")
    return edad in 1..99 && regex.matches(edad.toString())
}

fun validarApellido(apellido: String): Boolean {
    return apellido.matches("^[a-zA-Z]+$".toRegex())
}

fun validarNombre(nombre: String): Boolean {
    return nombre.length in 1..20 && nombre.matches("[a-zA-Z]+".toRegex())
}

data class Usuario (var nombre: String, var apellido: String, var edad: Int, var correo: String, var sistemaSalud: String)
