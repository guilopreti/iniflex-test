import employee.Funcionario;
import employee.FuncionarioRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;

public class Main {

  public static void main(String[] args) throws IOException {
    String content = Files.readString(Path.of("src/data.json"));

    JSONArray contentArray = new JSONArray(content);

    var funcionarioRepository = new FuncionarioRepository();

    // 3.1 Inserção de funcionários
    try {
      System.out.println("3.1 Inserção de funcionários iniciada!");

      DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(
        "dd/MM/yyyy"
      );
      for (int i = 0; i < contentArray.length(); i++) {
        JSONObject contentObject = contentArray.getJSONObject(i);

        Funcionario employee = new Funcionario(
          contentObject.getString("nome"),
          LocalDate.parse(
            contentObject.getString("dataNascimento"),
            dateFormatter
          ),
          contentObject.getBigDecimal("salario"),
          contentObject.getString("funcao")
        );

        funcionarioRepository.add(employee);
      }

      System.out.println("3.1 Inserção de funcionários concluída com sucesso!");
    } catch (Exception e) {
      System.out.println("Erro ao inserir funcionários: " + e.getMessage());
    }

    // 3.2 Remoção do funcionário "João"
    System.out.println("---------------------------------------------------");
    try {
      System.out.println("3.2 Remoção do funcionário iniciada!");
      System.out.println(funcionarioRepository.remove("João"));
    } catch (IllegalArgumentException e) {
      System.out.println("Erro ao remover funcionário: " + e.getMessage());
    }

    // 3.3 Listagem dos funcionários
    System.out.println("---------------------------------------------------");
    System.out.println("3.3 Listagem dos funcionários:");
    System.out.println();

    List<Funcionario> employeeList = funcionarioRepository.getFuncionarios();

    for (Funcionario funcionario : employeeList) {
      System.out.println(funcionario);
    }

    // 3.4 Aumento de 10% para os funcionários
    System.out.println("---------------------------------------------------");
    System.out.println("3.4 Aplicação de aumento de 10% para os funcionários:");
    System.out.println();

    try {
      funcionarioRepository.giveRaise(10);

      for (Funcionario funcionario : employeeList) {
        var salario = String.format(
          Locale.of("pt", "BR"),
          "%,.2f",
          funcionario.getSalario()
        );

        System.out.println(
          "Nome: " +
            funcionario.getNome() +
            ". Salário atualizado: " +
            salario +
            "."
        );
      }
    } catch (IllegalArgumentException e) {
      System.out.println(
        "Erro ao aplicar aumento de salário: " + e.getMessage()
      );
    }
  }
}
