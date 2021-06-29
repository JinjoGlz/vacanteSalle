package vacante

class Nivel_Academico {

    String nivel
    String nombre

    static hasMany=[alumnos:Alumno]

    static constraints = {
        nivel blank:false
        nombre blank:false
    }
    
}
