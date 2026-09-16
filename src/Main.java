import employee.Funcionario;
import employee.FuncionarioRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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

    // 3.5 e 3.6 Agrupamento de funcionários pela função
    System.out.println("---------------------------------------------------");
    System.out.println(
      "3.5 e 3.6 Agrupando funcionários pela função e listando:"
    );
    System.out.println();

    Map<String, List<Funcionario>> groupedEmployees =
      funcionarioRepository.groupByRole();
    for (var group : groupedEmployees.entrySet()) {
      System.out.println("Função: " + group.getKey());
      for (Funcionario funcionario : group.getValue()) {
        System.out.println(funcionario);
      }
      System.out.println();
    }

    // 3.8 Listagem dos funcionários que fazem aniversário nos meses 10 e 12
    System.out.println("---------------------------------------------------");
    System.out.println(
      "3.8 Listagem dos funcionários que fazem aniversário nos meses 10 e 12:"
    );
    System.out.println();

    for (Funcionario funcionario : employeeList) {
      int employeeBirthdayMonth = funcionario
        .getDataNascimento()
        .getMonthValue();
      if (
        employeeBirthdayMonth == 10 || employeeBirthdayMonth == 12
      ) System.out.println(funcionario);
    }

    // 3.9 Buscando funcionário mais velho
    System.out.println("---------------------------------------------------");
    System.out.println("3.9 Iniciada busca de funcionário mais velho:");
    System.out.println();

    try {
      Funcionario oldestEmployee = funcionarioRepository.findOldestEmployee();
      System.out.println(
        "Funcionário mais velho: " +
          oldestEmployee.getNome() +
          ". Idade: " +
          Period.between(
            oldestEmployee.getDataNascimento(),
            LocalDate.now()
          ).getYears() +
          "."
      );
    } catch (IllegalArgumentException e) {
      System.out.println(
        "Erro ao buscar funcionário mais velho: " + e.getMessage()
      );
    }

    // 3.10 Listagem em ordem alfabética
    System.out.println("---------------------------------------------------");
    System.out.println("3.10 Listagem de funcionários em ordem alfabética:");
    System.out.println();

    employeeList.sort(Comparator.comparing(Funcionario::getNome));
    for (Funcionario funcionario : employeeList) {
      System.out.println(funcionario);
    }
  }
}
