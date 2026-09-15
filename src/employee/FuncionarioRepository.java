package employee;

import java.util.List;
import java.util.ArrayList;

public class FuncionarioRepository {
    private final List<Funcionario> funcionarios = new ArrayList<>();

    public List<Funcionario> getFuncionarios() {
        return this.funcionarios;
    }

    public void add(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }
}
