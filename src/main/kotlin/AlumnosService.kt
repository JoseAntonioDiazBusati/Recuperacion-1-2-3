import java.util.UUID

class AlumnosService(private val alumnoDao: IAlumnosDAO):IAlumnosService{
    override fun create(alumno: Alumnos): Alumnos? {
        return alumnoDao.create(alumno)
    }

    override fun getById(id: UUID): Alumnos? {
        return alumnoDao.getById(id)
    }

    override fun update(alumno: Alumnos): Alumnos? {
        return alumnoDao.update(alumno)
    }

    override fun delete(id: UUID) {
        alumnoDao.delete(id)
    }
    override fun getAll(): List<Alumnos>? {
        return alumnoDao.getAll()
    }
}