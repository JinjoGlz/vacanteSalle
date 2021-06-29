package vacante

class Alumno {

    String nombre
    String correo
    String contraseña
    int semestre 
    String matricula

    static belongsTo =[nivelAcademico: Nivel_Academico]

    static constraints = {
        nombre blank:false
        correo email:true, blank:false, unique:true
        semestre max:1
        matricula matches:"[0-9]{9}"
        nivelAcademico null:false, blank:false
    }
}
