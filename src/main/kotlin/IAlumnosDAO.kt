import java.util.UUID

interface IAlumnosDAO {
    fun create(alumno: Alumnos): Alumnos?
    fun getAll(): List<Alumnos>?
    fun getById(id: UUID): Alumnos?
    fun update(alumno: Alumnos): Alumnos?
    fun delete(id: UUID): Boolean
}