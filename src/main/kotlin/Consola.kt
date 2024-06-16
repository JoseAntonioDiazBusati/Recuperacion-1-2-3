class Consola: IOutputInfo {
    override fun showMessage(message: String, lineBreak: Boolean) {
        if (lineBreak) {
            println(message)
        } else {
            print(message)
        }
    }

    override fun show(alumnosList: List<Alumnos>, message: String) {
        if (alumnosList.isEmpty()) {
            showMessage("¡No se encontraron alumnos!")
        } else {
            showMessage(message)
            alumnosList.forEachIndexed { index, alumno ->
                showMessage("\t${index + 1}. $alumno")
            }
        }
    }
}