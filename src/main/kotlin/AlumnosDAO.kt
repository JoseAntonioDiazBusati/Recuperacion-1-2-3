import java.sql.SQLException
import java.util.UUID
import javax.sql.DataSource

class AlumnosDAO (private val dataSource: DataSource, private val console: IOutputInfo): IAlumnosDAO {
    override fun create(alumno: Alumnos): Alumnos? {
        val sql = "INSERT INTO Alumnos () VALUES (?)"
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->

                    val rs = stmt.executeUpdate()
                    if (rs == 1) {
                        alumno
                    } else {
                        console.showMessage("**Error** insert query failed! ($rs records inserted)")
                        null
                    }
                }
            }
        } catch (e: SQLException) {
            console.showMessage("**Error** ${e.message}")
            null
        }
    }

    override fun getById(id: UUID): Alumnos? {
        val sql = "SELECT * FROM Alumnos WHERE id = ?"
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, id.toString())
                    val rs = stmt.executeQuery()
                    if (rs.next()) {
                        Alumnos(
                            id = UUID.fromString(rs.getString("id")),
                            name = rs.getString("name"),
                        )
                    } else {
                        null
                    }
                }
            }
        } catch (e: SQLException) {
            console.showMessage("**Error** ${e.message}")
            null
        }
    }

    override fun getAll(): List<Alumnos>? {
        val sql = "SELECT * FROM Alumnos"
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    val rs = stmt.executeQuery()
                    val alumnos = mutableListOf<Alumnos>()
                    while (rs.next()) {
                        alumnos.add(
                            Alumnos(
                                id = UUID.fromString(rs.getString("id")),
                                name = rs.getString("name"),
                            )
                        )
                    }
                    alumnos
                }
            }
        } catch (e: SQLException) {
            console.showMessage("**Error** ${e.message}")
            null
        }
    }

    override fun update(alumno: Alumnos): Alumnos? {
        val sql = "UPDATE tuser SET name = ?, email = ? WHERE id = ?"
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    val rs = stmt.executeUpdate()
                    if (rs == 1) {
                        alumno
                    } else {
                        null
                    }
                }
            }
        } catch (e: SQLException) {
            console.showMessage("**Error** ${e.message}")
            null
        }
    }

    override fun delete(id: UUID): Boolean {
        val sql = "DELETE FROM Alumnos WHERE id = ?"
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, id.toString())
                    val rs = stmt.executeUpdate()
                    rs == 1
                }
            }
        } catch (e: SQLException) {
            console.showMessage("**Error** ${e.message}")
            false
        }
    }
}