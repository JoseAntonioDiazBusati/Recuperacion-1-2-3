interface IOutputInfo {
    fun showMessage(message: String, lineBreak: Boolean = true)

    fun show(alumnosList: List<Alumnos>, message: String = "Todos los alumnos:")
}