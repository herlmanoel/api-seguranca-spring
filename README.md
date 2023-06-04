# **API de Gerenciamento de Usuários com Segurança**

Esta API é responsável pelo gerenciamento de usuários, fornecendo operações CRUD (Create, Read, Update, Delete). Ela permite que você crie, atualize, recupere e exclua usuários do sistema.

## **Pré-requisitos**

Antes de executar o projeto, certifique-se de ter os seguintes componentes instalados:

- **[Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)**
- **[Apache Maven](https://maven.apache.org/download.cgi)**
- **[Banco de dados configurado](https://chat.openai.com/c/link-para-configuracao-do-banco-de-dados)**

## **Configuração**

Siga estas etapas para configurar e executar o projeto:

1. Faça o clone deste repositório: **`git clone https://github.com/seu-usuario/nome-do-repositorio.git`**
2. Navegue até o diretório do projeto: **`cd nome-do-repositorio`**
3. Configure as informações do banco de dados no arquivo **`application.properties`**.
4. Execute o seguinte comando para compilar o projeto: **`mvn compile`**
5. Inicie o servidor da aplicação: **`mvn spring-boot:run`**

## **Rotas**

A API possui as seguintes rotas disponíveis:

### **GET /users**

Retorna uma lista paginada de todos os usuários.

### **GET /users/{id}**

Retorna os detalhes de um usuário específico com base no ID fornecido.

### **POST /users**

Cria um novo usuário com base nos dados fornecidos no corpo da solicitação.

### **PUT /users/{id}**

Atualiza os dados de um usuário existente com base no ID fornecido e nos dados fornecidos no corpo da solicitação.

### **DELETE /users/{id}**

Exclui um usuário específico com base no ID fornecido.

## **Modelo de Dados**

O modelo de dados utilizado pela API é baseado na classe **`UserDTO`**. Aqui está uma descrição dos campos:

- **`id`**: ID do usuário (gerado automaticamente).
- **`name`**: Nome do usuário.
- **`email`**: E-mail do usuário.
- **`password`**: Senha do usuário.

## **Exemplos de Solicitação e Resposta**

### **Exemplo de solicitação POST /users**

```bash
POST /users

{
  "name": "John Doe",
  "email": "johndoe@example.com",
  "password": "secretpassword"
}
```

### **Exemplo de resposta POST /users**

```bash
HTTP/1.1 201 Created
Location: /users/1

{
  "id": 1,
  "name": "John Doe",
  "email": "johndoe@example.com"
}
```

## **Contribuição**

As contribuições são bem-vindas! Se você deseja contribuir para este projeto, siga estas etapas:

1. Faça um fork deste repositório.
2. Crie uma nova branch: **`git checkout -b minha-nova-feature`**
3. Faça as alterações desejadas e commit: **`git commit -m 'Adicionando nova feature'`**
4. Envie as alterações para o repositório remoto: **`git push origin minha-nova-feature`**
5. Abra um pull request no repositório original.
