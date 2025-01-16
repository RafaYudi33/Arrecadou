<h1 align="center" style="font-weight: bold;">Arrecadou 💰</h1>

<div align="center">
  <img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white" alt="java">
  <img src="https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white" alt="hibernate">
  <img src="https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white" alt="spring">
  <img src="https://img.shields.io/badge/Spring%20Data%20JPA-6DB33F?style=for-the-badge&logo=spring&logoColor=white" alt="Spring Data JPA">
  <img src="https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL">
</div>

<br> 
<p align="center">
 <a href="#started">Como Começar</a> • 
 <a href="#features">Funcionalidades</a> •
 <a href="#libraries">Bibliotecas Externas</a>
</p>

<br>

<p align="center">
  <b>Um sistema desktop para gerenciamento de ações de arrecadação por entidades e grupos.</b>
</p>

---

<h2 id="started">🚀 Como Começar</h2>

### Pré-requisitos

Garanta que você tem instalado:

- Java 17 ou superior
- Maven para gerenciamento de dependências
- MySQL para o banco de dados

### Clonando o Projeto

Clone o repositório:

```bash
git clone https://github.com/RafaYudi33/Arrecadou.git
cd Arrecadou
```

### Configuração do Banco de Dados

Antes de rodar o aplicativo, configure o banco de dados no arquivo **`application.properties`**:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/arrecadou
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```

### Executando a Aplicação

1. Compile o projeto com Maven:
   ```bash
   mvn clean install
   ```

2. Execute a aplicação:
   ```bash
   java -jar target/Arrecadou.jar
   ```

---

<h2 id="features">📍 Funcionalidades</h2>

> ### 🔹 **1. Cadastro de Entidades**
> - Ao iniciar o sistema pela primeira vez, solicita **dados da entidade/grupo** que realizará as arrecadações.

---

> ### 🧑‍💼 **2. Cadastro de Coordenadores**
> - Permite cadastrar **coordenadores** responsáveis pelas ações, informando:
>   - Nome
>   - CPF
>   - Telefone

---

> ### 🎭 **3. Tipos de Ações**
> - **Ação de Produção de Evento:**
>   - Eventos para arrecadação de dinheiro, como **festas beneficentes**.
>   - Cadastro de **insumos esperados**.
>   - Definição de **colaboradores** (cozinheiros, atendentes, etc.).
>   - Informações cadastradas:
>     - Nome do evento
>     - Objetivo
>     - Descrição
>     - Data de início e fim
> - **Ação de Contribuição Direta:**
>   - Ligações para **solicitação de doações financeiras**.

---

> ### 💸 **4. Cadastro de Doações**
> - **Para Contribuições Diretas:**
>   - Seleciona a ação para a qual a doação será destinada.
>   - Dados do doador:
>     - Nome
>     - Telefone
>     - Valor da doação
>     - Opção para **doação anônima**.
> - **Para Produção de Evento:**
>   - Doações podem ser **em dinheiro** ou **em itens**.
>   - Para doações de itens:
>     - Nome do item
>     - Quantidade (kg/unidades)
>     - Nome do doador
>     - Telefone
>     - Opção para **doação anônima**.

---

> ### 📊 **5. Relatórios de Arrecadação**
> - Geração de **relatórios finais para ações de contribuição direta**, contendo:
>   - Nome da ação, descrição, datas de início e fim, objetivo.
>   - Coordenadores e seus contatos.
>   - Nome dos doadores (se não anônimo).
>   - Valor total arrecadado.
>   - Lista das doações e seus valores.
> - Relatórios para **eventos beneficentes** incluem:
>   - Nome da ação, descrição, datas de início e fim, objetivo.
>   - Coordenadores e seus contatos.
>   - Nome dos doadores (se não anônimo).
>   - Valor total em vendas.
>   - Lucro real (com as doações em dinheiro e de item).
>   - Lista de colaboradores cadastrados.
>   - Insumos necessários.
>   - Lucro caso não houvesse nenhuma doação (nem de dinheiro, nem de item).
>   - Lista detalhada das doações recebidas (dinheiro e itens).

---

<h2 id="libraries">🔌 Bibliotecas Externas</h2>

Para aprimorar as funcionalidades e desempenho do sistema, as seguintes bibliotecas externas foram integradas:

- **Apache PDFBox**: Utilizada para a geração de relatórios em PDF.
---

📌 *Projeto desenvolvido para facilitar a arrecadação de fundos por entidades e grupos organizados.* 🚀
