package employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;
import person.Pessoa;

public class Funcionario extends Pessoa {

  private BigDecimal salario;
  private String funcao;

  public Funcionario(
    String nome,
    LocalDate dataNascimento,
    BigDecimal salario,
    String funcao
  ) {
    super(nome, dataNascimento);
    this.salario = salario;
    this.funcao = funcao;
  }

  @Override
  public String toString() {
    return (
      "{" +
      "nome='" +
      getNome() +
      '\'' +
      ", dataNascimento=" +
      String.format(
        "%02d/%02d/%d",
        getDataNascimento().getDayOfMonth(),
        getDataNascimento().getMonthValue(),
        getDataNascimento().getYear()
      ) +
      ", salario=" +
      String.format(Locale.of("pt", "BR"), "%,.2f", getSalario()) +
      ", funcao='" +
      getFuncao() +
      '\'' +
      "}"
    );
  }

  public BigDecimal getSalario() {
    return salario;
  }

  public void setSalario(BigDecimal salario) {
    this.salario = salario;
  }

  public String getFuncao() {
    return funcao;
  }

  public void setFuncao(String funcao) {
    this.funcao = funcao;
  }
}
