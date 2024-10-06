import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    public void shouldReturnListOfEmployees() throws Exception {
        List<Employee> employees = Arrays.asList(
            new Employee(1L, "John", "Doe"),
            new Employee(2L, "Jane", "Doe")
        );

        given(employeeService.getAllEmployees()).willReturn(employees);

        mockMvc.perform(get("/employees"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].firstName", is("John")))
            .andExpect(jsonPath("$[1].lastName", is("Doe")));
    }

    @Test
    public void shouldReturnEmployeeById() throws Exception {
        Employee employee = new Employee(1L, "John", "Doe");

        given(employeeService.getEmployeeById(1L)).willReturn(employee);

        mockMvc.perform(get("/employees/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.firstName", is("John")))
            .andExpect(jsonPath("$.lastName", is("Doe")));
    }

    @Test
    public void shouldReturn404WhenEmployeeNotFound() throws Exception {
        given(employeeService.getEmployeeById(1L)).willReturn(null);

        mockMvc.perform(get("/employees/1"))
            .andExpect(status().isNotFound());
    }

    @Test
    public void shouldAddNewEmployee() throws Exception {
        Employee employee = new Employee(1L, "John", "Doe");

        given(employeeService.saveEmployee(any(Employee.class))).willReturn(employee);

        mockMvc.perform(post("/employees")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"firstName\": \"John\", \"lastName\": \"Doe\"}"))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.firstName", is("John")));
    }
}
