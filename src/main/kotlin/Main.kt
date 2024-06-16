import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
@Preview
fun App() {
    var selectedStudent by remember { mutableStateOf("") }
    var gradesText by remember { mutableStateOf("Selecciona un alumno para ver las calificaciones.") }
    val students = remember { mutableStateListOf<String>() }

    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch(Dispatchers.IO) {
        }
    }

    MaterialTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Seleccione un Alumno:", style = MaterialTheme.typography.h6)

            Spacer(modifier = Modifier.height(8.dp))

            var expanded by remember { mutableStateOf(false) }
            Box {
                Button(onClick = { expanded = true }) {
                    Text(if (selectedStudent.isEmpty()) "Seleccionar Alumno" else selectedStudent)
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    students.forEach { student ->
                        DropdownMenuItem(onClick = {
                            selectedStudent = student
                            scope.launch(Dispatchers.IO) {}
                            expanded = false
                        }) {
                            Text(student)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Calificaciones:", style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(8.dp))
            Text(gradesText, modifier = Modifier.fillMaxWidth().padding(8.dp))
        }
    }
}
private fun getStudentGrades(studentName: String, dataSource: DataSourceFactory){}


fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
