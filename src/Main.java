import employee.Funcionario;
import employee.FuncionarioRepository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws IOException {
        String content = Files.readString(Path.of("src/data.json"));

        JSONArray contentArray = new JSONArray(content);

        var funcionarioRepository = new FuncionarioRepository();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // 3.1 Inserção de funcionários
        try {
            for (int i = 0; i < contentArray.length(); i++) {
                JSONObject contentObject = contentArray.getJSONObject(i);

                Funcionario employee = new Funcionario(
                        contentObject.getString("nome"),
                        LocalDate.parse(contentObject.getString("dataNascimento"), dateFormatter),
                        contentObject.getBigDecimal("salario"),
                        contentObject.getString("funcao")
                );

                funcionarioRepository.add(employee);
            }

            System.out.println("3.1 Inserção de funcionários concluída com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao inserir funcionários: " + e.getMessage());
        }

    }
}
