package employee;

import java.math.BigDecimal;
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
      .orElseThrow(() ->
        new IllegalArgumentException("Funcionário não encontrado: " + name)
      );

    funcionarios.remove(employee);

    return "Funcionário: " + employee + " removido com sucesso.";
  }

  public void giveRaise(int percentage) {
    if (percentage > 0) {
      BigDecimal raiseRate = BigDecimal.valueOf(percentage).divide(
        BigDecimal.valueOf(100)
      );

      for (Funcionario funcionario : funcionarios) {
        BigDecimal raiseAmount = funcionario.getSalario().multiply(raiseRate);
        funcionario.setSalario(funcionario.getSalario().add(raiseAmount));
      }
    } else {
      throw new IllegalArgumentException(
        "Porcentagem de aumento deve ser maior que zero."
      );
    }
  }
}
