package employee;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioRepository {

  private final List<Funcionario> funcionarios = new ArrayList<>();

  public List<Funcionario> getFuncionarios() {
    return this.funcionarios;
  }

  public void add(Funcionario funcionario) {
    funcionarios.add(funcionario);
  }

  public String remove(String name) {
    Funcionario employee = funcionarios
      .stream()
      .filter(f -> f.getNome().equals(name))
      .findFirst()
      .orElse(null);

    funcionarios.remove(employee);

    return "Funcionário: " + employee + " removido com sucesso.";
  }
}
