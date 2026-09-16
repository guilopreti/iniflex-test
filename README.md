# Desafio Iniflex - Gestão de Funcionários

Projeto desenvolvido em Java para o processo seletivo da Iniflex. A aplicação gerencia uma lista de funcionários e executa operações de negócio como agrupamentos, cálculos salariais, ordenação e manipulação de datas, atendendo rigorosamente a todos os requisitos do teste.

---

## 🛠️ Tecnologias e Dependências

* **Java**: JDK 17+ (ou superior).
* **Biblioteca JSON (`org.json`)**: Localizada no diretório `lib/json-20260814.jar`.
  * **Portabilidade garantida:** A biblioteca está inclusa diretamente no repositório (`lib/`) e já configurada para os projetos do IntelliJ IDEA e VS Code. Isso dispensa a instalação de ferramentas de build pesadas (como Maven/Gradle) e permite executar o projeto imediatamente em qualquer máquina, sem configurações adicionais ou necessidade de conexão com a internet.

---

## 📁 Estrutura do Projeto

```text
iniflex-java/
├── .idea/                      # Configurações do projeto para IntelliJ IDEA
├── lib/
│   └── json-20260814.jar       # Biblioteca org.json para parsing de dados
├── src/
│   ├── employee/
│   │   ├── Funcionario.java            # Extensão de Pessoa com salário e função
│   │   └── FuncionarioRepository.java  # Regras de negócio e operações na coleção
│   ├── person/
│   │   └── Pessoa.java                 # Classe base com nome e data de nascimento
│   ├── data.json                       # Base de dados inicial dos funcionários
│   └── Main.java                       # Ponto de entrada e execução dos requisitos
├── iniflex-java.iml
└── README.md
```

---

## 📋 Requisitos Atendidos

A classe `Main` orquestra a execução das seguintes etapas:

1. **3.1:** Inserção de todos os funcionários a partir de `src/data.json`.
2. **3.2:** Remoção do funcionário "João".
3. **3.3:** Impressão de todos os funcionários com formatação personalizada de data (`dd/MM/yyyy`) e moeda brasileira (`1.234,56`).
4. **3.4:** Aplicação de aumento salarial de 10% para todos os funcionários.
5. **3.5 e 3.6:** Agrupamento dos funcionários por função (`Map<String, List<Funcionario>>`) e exibição agrupada.
6. **3.8:** Filtragem e exibição dos aniversariantes dos meses 10 (Outubro) e 12 (Dezembro).
7. **3.9:** Identificação e exibição do funcionário com maior idade (nome e idade em anos).
8. **3.10:** Ordenação e listagem alfabética dos funcionários.
9. **3.11:** Cálculo do valor total da folha salarial.
10. **3.12:** Cálculo da quantidade de salários mínimos (R$ 1.212,00) que cada funcionário recebe.

---

## 🚀 Como Executar

O projeto foi projetado para execução imediata (*zero-config*).

### Opção 1: IntelliJ IDEA
1. Abra a pasta do projeto no IntelliJ IDEA (`File > Open...`).
2. Aguarde a indexação (o módulo e a biblioteca `lib/` serão reconhecidos automaticamente).
3. Abra o arquivo `src/Main.java` e clique no botão verde de **Run** (ou `Shift + F10`).

### Opção 2: Visual Studio Code
1. Abra a pasta do projeto no VS Code.
2. Certifique-se de ter a extensão **Extension Pack for Java** instalada.
3. Abra `src/Main.java` e clique em **Run** no topo do método `main`.

### Opção 3: Linha de Comando (Terminal)

Na raiz do projeto:

**Windows (PowerShell/CMD):**
```powershell
# Compilar
javac -cp "lib/json-20260814.jar" -d out src/person/*.java src/employee/*.java src/Main.java

# Executar
java -cp "out;lib/json-20260814.jar" Main
```

**Linux / macOS:**
```bash
# Compilar
javac -cp "lib/json-20260814.jar" -d out src/person/*.java src/employee/*.java src/Main.java

# Executar
java -cp "out:lib/json-20260814.jar" Main
```
