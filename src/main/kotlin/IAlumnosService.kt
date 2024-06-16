import java.util.UUID

interface IAlumnosService {
    fun create(alumno: Alumnos): Alumnos?
    fun getById(id: UUID): Alumnos?
    fun update(alumno: Alumnos): Alumnos?
    fun delete(id: UUID)
    fun getAll(): List<Alumnos>?
}